package com.rongbao.jyjy.rongbao.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.adapter.activity.SecondAdapter;
import com.rongbao.jyjy.rongbao.apiservice.Content;
import com.rongbao.jyjy.rongbao.base.BaseActivity;
import com.rongbao.jyjy.rongbao.base.BaseApp;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.MyDB_Login_Bean;
import com.rongbao.jyjy.rongbao.db.MyDB_Login_BeanDao;
import com.rongbao.jyjy.rongbao.present.Empty_Present;
import com.rongbao.jyjy.rongbao.util.AudioUtil;
import com.rongbao.jyjy.rongbao.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity<BasePresent, BaseView> implements BaseView {
    private static final String TAG = "SecondActivity";
    @BindView(R.id.second_toolbar_imageback)
    ImageView mSecondToolbarImageback;
    @BindView(R.id.second_toolbar_title)
    TextView mSecondToolbarTitle;
    @BindView(R.id.second_toolbar)
    Toolbar mSecondToolbar;
    @BindView(R.id.second_smart)
    SmartRefreshLayout mSecondSmart;
    @BindView(R.id.second_recy)
    RecyclerView mSecondRecy;
    @BindView(R.id.second_buttom_image)
    ImageView mSecondButtomImage;
    @BindView(R.id.second_buttom_image2)
    ImageView mSecondButtomImage2;
    @BindView(R.id.second_button_ed)
    EditText mSecondButtonEd;
    @BindView(R.id.second_button_ed2)
    Button mSecondButtonEd2;
    @BindView(R.id.second_bun_send)
    TextView mSecondBunSend;
    @BindView(R.id.second_bun_send_voice)
    TextView mSecondBunSendVoice;
    private String id;
    private SecondAdapter secondAdapter;
    private ArrayList<EMMessage> messagemlist;
    private MyDB_Login_BeanDao myDB_login_beanDao;
    private String username;
    private String mAudioPath;
    private long mDuration;
    @Override
    protected int getLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected BasePresent getPresent() {
        return new Empty_Present();
    }


    @Override
    protected void initview() {
        myDB_login_beanDao = BaseApp.getInstance().getDaoSession().getMyDB_Login_BeanDao();
        List<MyDB_Login_Bean> myDB_login_beans = myDB_login_beanDao.loadAll();    //查出数据库中的值  从数具库中找出name
        for (MyDB_Login_Bean myDB_login_bean : myDB_login_beans) {
            username = myDB_login_bean.getUsername();   //数据库的值
            Log.d(TAG, "initview: "+ username);
        }


        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        Intent intent = getIntent();
        id = intent.getStringExtra(Content.MYCLASS);
        Log.d(TAG, "已传id: " + id);
        mSecondToolbarTitle.setText(""+id);
        mSecondRecy.setLayoutManager(new LinearLayoutManager(this));
        messagemlist = new ArrayList<>();
        secondAdapter = new SecondAdapter(this, messagemlist,id,username);
        mSecondRecy.setAdapter(secondAdapter);

    }

    @OnClick({R.id.second_toolbar_imageback, R.id.second_buttom_image, R.id.second_buttom_image2, R.id.second_button_ed2, R.id.second_bun_send,R.id.second_bun_send_voice})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.second_toolbar_imageback:
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.second_buttom_image:
                mSecondButtomImage.setVisibility(View.GONE);
                mSecondButtomImage2.setVisibility(View.VISIBLE);
                mSecondButtonEd.setVisibility(View.GONE);
                mSecondButtonEd2.setVisibility(View.VISIBLE);
                mSecondBunSendVoice.setVisibility(View.VISIBLE);
                mSecondBunSend.setVisibility(View.GONE);
                break;
            case R.id.second_buttom_image2:
                mSecondButtomImage.setVisibility(View.VISIBLE);
                mSecondButtomImage2.setVisibility(View.GONE);
                mSecondButtonEd.setVisibility(View.VISIBLE);
                mSecondButtonEd2.setVisibility(View.GONE);
                mSecondBunSend.setVisibility(View.VISIBLE);
                mSecondBunSendVoice.setVisibility(View.GONE);
                break;
            case R.id.second_button_ed2:
                //当前是否是在录音
                if (AudioUtil.isRecording){
                    AudioUtil.stopRecord();
                    mSecondButtonEd2.setText("点击录音");
                }else {
                    startVoice();
                    mSecondButtonEd2.setText("停止录音");
                }
                break;
            case R.id.second_bun_send:
                setmessage();
                break;
            case R.id.second_bun_send_voice:
                sendVoiceMsg();
                break;
        }
    }

    private void startVoice() {
        AudioUtil.startRecord(new AudioUtil.ResultCallBack() {
            @Override
            public void onSuccess(String path, long time) {
                mAudioPath = path;
                mDuration = time;
            }

            @Override
            public void onFail(String msg) {
                Log.d(TAG, "onFail: "+msg);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       ToastUtil.ShowToast("录音失败");
                   }
               });
            }
        });
    }
    //发送语音
    private void sendVoiceMsg() {

        if (TextUtils.isEmpty(mAudioPath)){
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //filePath为语音文件路径，length为录音时间(秒)
                final EMMessage message = EMMessage.createVoiceSendMessage(mAudioPath, (int) mDuration, id);

                EMClient.getInstance().chatManager().sendMessage(message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        secondAdapter.serVoice_Message(message);
                        mSecondRecy.scrollToPosition(secondAdapter.messagemlist.size() - 1);
                    }
                });

                mAudioPath = "";
                mDuration = 0;
            }
        }).start();
    }

    @Override
    protected void initClick() {
        secondAdapter.setOnItemClickListener(new SecondAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String path) {
                if (!TextUtils.isEmpty(path)){
                    playVoice(path);
                }
            }
        });

    }

    private void playVoice(String path) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);

            mediaPlayer.prepare();
            //mediaPlayer.prepareAsync();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setmessage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String content = mSecondButtonEd.getText().toString().trim();
            if(TextUtils.isEmpty(content)){
                ToastUtil.ShowToast("发送内容为空");
                return;

            }
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content,id);
                Log.d(TAG, "要发送的"+id);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
                // 添加数据 到适配器中
                secondAdapter.serMessage(message);
                mSecondRecy.scrollToPosition(secondAdapter.messagemlist.size() - 1);
                mSecondButtonEd.setText("");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   记得在不需要的时候移除listener，如在activity的onDestroy()时
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
    EMMessageListener msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                if (messages != null && messages.size() > 0) {
                    final ArrayList<EMMessage> list = new ArrayList<>();
                    for (EMMessage msg : messages) {
                        String from = msg.getFrom();
                        //区分出来是不是当前和你聊天的人发送的消息
                        if (id.equals(from)) {
                            list.add(msg);
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            secondAdapter.getData(list);
                            mSecondRecy.scrollToPosition(secondAdapter.messagemlist.size() - 1);
                        }
                    });
                }

            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }
            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };

}