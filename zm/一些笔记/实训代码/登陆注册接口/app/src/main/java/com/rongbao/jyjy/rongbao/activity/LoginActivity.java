package com.rongbao.jyjy.rongbao.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.apiservice.Content;
import com.rongbao.jyjy.rongbao.base.BaseActivity;
import com.rongbao.jyjy.rongbao.base.BaseApp;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.Login_Bean;
import com.rongbao.jyjy.rongbao.bean.MyDB_Login_Bean;
import com.rongbao.jyjy.rongbao.db.MyDB_Login_BeanDao;
import com.rongbao.jyjy.rongbao.present.acticity_presnet.Login_Presnet;
import com.rongbao.jyjy.rongbao.util.SpUtil;
import com.rongbao.jyjy.rongbao.util.ToastUtil;
import com.rongbao.jyjy.rongbao.view.activity.Login_View;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<BasePresent, BaseView> implements Login_View {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_title)
    TextView mLoginTitle;
    @BindView(R.id.login_follow)
    ImageView mLoginFollow;
    @BindView(R.id.login_ed_username)
    EditText mLoginEdUsername;
    @BindView(R.id.login_erroy_username)
    TextView mLoginErroyUsername;
    @BindView(R.id.login_linear)
    LinearLayout mLoginLinear;
    @BindView(R.id.login_ed_psd)
    EditText mLoginEdPsd;
    @BindView(R.id.login_erroy_psd)
    TextView mLoginErroyPsd;
    @BindView(R.id.login_psd)
    LinearLayout mLoginPsd;
    @BindView(R.id.login_bun_login)
    Button mLoginBunLogin;
    @BindView(R.id.login_forget_psd)
    TextView mLoginForgetPsd;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.login_image_qq)
    ImageView mLoginImageQq;
    @BindView(R.id.login_image_linear)
    LinearLayout mLoginImageLinear;
    @BindView(R.id.login_text_register)
    TextView mLoginTextRegister;

    private PopupWindow popupWindow;
    private MyDB_Login_BeanDao myDB_login_beanDao;
    private ProgressBar progrss;

    //5cbc8808570df39cca001731  友盟
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresent getPresent() {
        return new Login_Presnet();
    }

    @Override
    protected void initview() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        myDB_login_beanDao = BaseApp.getInstance().getDaoSession().getMyDB_Login_BeanDao();
        //判断之前有没有登陆过
        boolean loggedInBefore = EMClient.getInstance().isLoggedInBefore();
        if (loggedInBefore) {
            GoMainActviity();
        }


    }

    @OnClick({R.id.login_bun_login, R.id.login_forget_psd, R.id.login_text_register, R.id.login_image_qq, R.id.login_erroy_username, R.id.login_erroy_psd})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_bun_login:
                getLogin();
                break;
            case R.id.login_forget_psd:
                getForget_psd();
                break;
            case R.id.login_text_register:
                getregister();
                break;
            case R.id.login_image_qq:
                getQQlogin();
                break;
            case R.id.login_erroy_username:
                mLoginEdUsername.setText("");
                break;
            case R.id.login_erroy_psd:
                mLoginEdPsd.setText("");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == Content.login && resultCode == Content.register) {
            String username = data.getStringExtra(Content.USERNAEM);
            String psd = data.getStringExtra(Content.PSd);
            mLoginEdUsername.setText(username);
            mLoginEdPsd.setText(psd);
        }
    }

    @Override
    protected void initother() {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mLoginFollow, "translationX", 0, 35);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mLoginFollow, "rotation", 20);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mLoginFollow, "alpha", 0.1f, 0.5f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotation).after(translationX).after(alpha);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }

    private void getForget_psd() {
        ToastUtil.showLong("忘记密码了是吧！我也忘了（~ V ~）");
    }

    private void getLogin() {
        final String name = mLoginEdUsername.getText().toString().trim();
        final String psd = mLoginEdPsd.getText().toString().trim();
        final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(psd)) {
            basepresnet.getData();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EMClient.getInstance().login(name, psd, new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            SpUtil.setParam(Content.USERNAEM, name);  //保存用户名
                            MyDB_Login_Bean myDB_login_bean = new MyDB_Login_Bean();
                            myDB_login_bean.setUsername(name);
                            myDB_login_bean.setImage(R.id.login_image_qq);
                            myDB_login_beanDao.insertOrReplace(myDB_login_bean);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.d("main", "登录聊天服务器失败！");
                            ShowTaost("失败");
                        }
                    });
                }
            }).start();


            final View inflate = View.inflate(LoginActivity.this, R.layout.popitem, null);
            progrss = inflate.findViewById(R.id.progrss);
            popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.showAsDropDown(progrss, 0, 0);
        }
    }

    private void GoMainActviity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void getQQlogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("提示:")
                .setMessage("使用第三方登陆会限制聊天功能")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UMShareAPI umShareAPI = UMShareAPI.get(LoginActivity.this);
                        umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();


    }

    UMAuthListener umAuthListener = new UMAuthListener() {

        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            public String  unionid;  public String  is_yellow_vip;public String  screen_name;public String  msg;public String  vip;
//            public String  city;public String accessToken;public String gender;public String province;public String is_yellow_year_vip;
//            public String openid;public String profile_image_url;public String  yellow_vip_level;public String  access_token;
//            public String  iconurl;public String  name;public String  uid;public String  expiration;public String  expires_in;public String  level;
//            public String  ret;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            String profile_image_url = data.get("profile_image_url");
            String screen_name = data.get("screen_name");
            SpUtil.setParam(Content.UMENG_NAEM, screen_name);  //保存用户名.
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            intent.putExtra(Content.UMENG_HEADER, profile_image_url);
            intent.putExtra(Content.UMENG_NAEM, screen_name);
            startActivity(intent);
            finish();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    private void getregister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(intent, Content.login);
    }


    @Override
    public void Onsuccessful(Login_Bean bean) {
        if (bean.getCode() == 200) {
            popupWindow.dismiss();
            ShowTaost("登陆成功");
        } else {
            ShowTaost("账号或密码错误");
            popupWindow.dismiss();

        }
    }

    @Override
    public void OnFaild(String meaage) {
        Log.d("请求错误数据", "OnFaild: " + meaage);
        ToastUtil.ShowToast(meaage);
    }

    @Override
    public String getusername() {
        return mLoginEdUsername.getText().toString().trim();
    }

    @Override
    public String getPsd() {
        return mLoginEdPsd.getText().toString().trim();
    }

    public void ShowTaost(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
