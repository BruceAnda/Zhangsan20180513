package cn.zhaoliang5156.zhangsan20180513.mvp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

import cn.zhaoliang5156.zhangsan20180513.R;

/**
 * 第三方登录 页面
 *
 * @author zhaoliang
 * @version 1.0
 * @create 2018/9/14
 */
public class LoginActivity extends AppCompatActivity {

    private ImageView ivQQLogin;
    private SHARE_MEDIA qq = SHARE_MEDIA.QQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivQQLogin = findViewById(R.id.iv_qq_login);

        ivQQLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // boolean isauth = UMShareAPI.get(LoginActivity.this).isAuthorize(LoginActivity.this, qq);
                //if (isauth) {
                   // UMShareAPI.get(LoginActivity.this).deleteOauth(LoginActivity.this, qq, authListener);
               // } else {
                    UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, qq, authListener);
               // }
            }
        });
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            // SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            finish();
            //notifyDataSetChanged();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
