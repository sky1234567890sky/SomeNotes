package com.rongbao.jyjy.rongbao.activity;

import android.content.Intent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.apiservice.Content;
import com.rongbao.jyjy.rongbao.base.BaseActivity;
import com.rongbao.jyjy.rongbao.base.BaseApp;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.MyDB_Login_Bean;
import com.rongbao.jyjy.rongbao.bean.Register_Bean;
import com.rongbao.jyjy.rongbao.bean.Veridity_Bean;
import com.rongbao.jyjy.rongbao.db.MyDB_Login_BeanDao;
import com.rongbao.jyjy.rongbao.present.acticity_presnet.Register_Present;
import com.rongbao.jyjy.rongbao.view.activity.Register_View;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<BasePresent, BaseView> implements Register_View {
    @BindView(R.id.register_title)
    TextView mRegisterTitle;
    @BindView(R.id.register_follow)
    ImageView mRegisterFollow;
    @BindView(R.id.register_ed_username)
    EditText mRegisterEdUsername;
    @BindView(R.id.register_erroy_username)
    TextView mRegisterErroyUsername;
    @BindView(R.id.register_linear)
    LinearLayout mRegisterLinear;
    @BindView(R.id.register_ed_psd)
    EditText mRegisterEdPsd;
    @BindView(R.id.register_erroy_psd)
    TextView mRegisterErroyPsd;
    @BindView(R.id.register_psd)
    LinearLayout mRegisterPsd;
    @BindView(R.id.register_agin_ed_psd)
    EditText mRegisterAginEdPsd;
    @BindView(R.id.register_agin_erroy_psd)
    TextView mRegisterAginErroyPsd;
    @BindView(R.id.register_agin_psd)
    LinearLayout mRegisterAginPsd;
    @BindView(R.id.register_ed_phone)
    EditText mRegisterEdphone;
    @BindView(R.id.register_agin_ed_verifi)
    EditText mRegisterAginEdVerifi;
    @BindView(R.id.register_tv_verifi)
    TextView mRegisterTvVerifi;
    @BindView(R.id.register_erroy_verifi)
    TextView mRegisterErroyVerifi;
    @BindView(R.id.register_verifi)
    LinearLayout mRegisterVerifi;
    @BindView(R.id.register_bunregister)
    Button mRegisterBunRegister;
    private String data;
    private Register_Bean bean;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected BasePresent getPresent() {
        return new Register_Present();
    }

    @OnClick({R.id.register_tv_verifi, R.id.register_bunregister, R.id.register_erroy_username, R.id.register_erroy_psd, R.id.register_agin_erroy_psd,
            R.id.register_agin_erroy_phone, R.id.register_erroy_verifi})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.register_tv_verifi:
                getVerifi();
                break;
            case R.id.register_bunregister:
                getRegister();
                break;
            case R.id.register_erroy_username:
                mRegisterEdUsername.setText("");
                break;
            case R.id.register_erroy_psd:
                mRegisterEdPsd.setText("");
                break;
            case R.id.register_agin_erroy_psd:
                mRegisterAginEdPsd.setText("");
                break;
            case R.id.register_agin_erroy_phone:
                mRegisterEdphone.setText("");
                break;
            case R.id.register_erroy_verifi:
                mRegisterAginEdVerifi.setText("");
                break;
        }
    }

    @Override
    protected void initother() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0, 1.0f);
        scaleAnimation.setDuration(2000);
        mRegisterFollow.startAnimation(scaleAnimation);
    }
    private void getVerifi() {
        basepresnet.getVerifity();
    }
    private void getRegister() {
        basepresnet.getData();

    }


    @Override
    public String getUserName() {
        return mRegisterEdUsername.getText().toString().trim();
    }

    @Override
    public String getUserPsd() {
        return  mRegisterEdPsd.getText().toString().trim();
    }

    @Override
    public String getUserAginPsd() {
        return  mRegisterAginEdPsd.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return  mRegisterEdphone.getText().toString().trim();
    }
    @Override
    public void OnFaild(String messge) {
        ShowTaost(messge);
    }
    @Override
    public void Onsuccessful1(Veridity_Bean bean) {
        if(bean.getCode()==200){
            data = bean.getData();
            mRegisterAginEdVerifi.setText(bean.getData());
        }
    }
    @Override
    public String etVeridity() {;
        return data;
    }



    @Override
    public void Onsuccessful(Register_Bean bean) {
        this.bean=bean;
        if (bean.getCode() == 200 && bean != null) {
            final String username = mRegisterEdUsername.getText().toString().trim();
            final String psd = mRegisterEdPsd.getText().toString().trim();
            MyDB_Login_BeanDao myDB_login_beanDao = BaseApp.getInstance().getDaoSession().getMyDB_Login_BeanDao();
            myDB_login_beanDao.insertOrReplace(new MyDB_Login_Bean(null,username,0,psd));
            Intent intent = new Intent();
            intent.putExtra(Content.USERNAEM,username);
            intent.putExtra(Content.PSd,psd);
            setResult(Content.register,intent);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(username, psd);//同步方法
                        ShowTaost("注册成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        ShowTaost("注册失败");
                    }

                }
            }).start();
            finish();
        }
    }
    public void ShowTaost(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
