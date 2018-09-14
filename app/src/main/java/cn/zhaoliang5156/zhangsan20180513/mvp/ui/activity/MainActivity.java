package cn.zhaoliang5156.zhangsan20180513.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.zhaoliang5156.zhangsan20180513.R;
import cn.zhaoliang5156.zhangsan20180513.mvp.contract.MainContract;
import cn.zhaoliang5156.zhangsan20180513.mvp.presenter.MainPresenter;
import cn.zhaoliang5156.zhangsan20180513.mvp.ui.adapter.MainAdapter;
import cn.zhaoliang5156.zhangsan20180513.mvp.ui.bean.MainBean;

/**
 * 首界面
 *
 * @author zhaoliang
 * @version 1.0
 * @create 2018/9/14
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.MainView {

    private static final int CODE_REQUEST_LOGIN = 1000;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView ivHead;

    private RecyclerView recyclerView;

    private MainPresenter presenter;
    private List<MainBean.Pois> datas = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }

        ivHead = findViewById(R.id.iv_head);
        ivHead.setOnClickListener(this);

        Glide.with(this).load(getResources()
                .getDrawable(R.drawable.head))
                .apply(new RequestOptions().circleCrop())
                .into(ivHead);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, datas);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        presenter = new MainPresenter();
        presenter.attach(this);
        presenter.loadData("https://restapi.amap.com/v3/place/around?key=d78f39012867929dc6ad174dd498f51f&location=116.473168,39.993015&keywords=%E7%BE%8E%E9%A3%9F&types=&radius=1000&offset=20&page=1&extensions=all");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:
                toLogin();
                break;
        }
    }

    private void toLogin() {
        startActivityForResult(new Intent(this, LoginActivity.class), CODE_REQUEST_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取头像并展示
        UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            // SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            // SocializeUtils.safeCloseDialog(dialog);
            // Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            // finish();
            //notifyDataSetChanged();

            if (data != null) {
                String icon = data.get("iconurl");
                if (!TextUtils.isEmpty(icon)) {
                    Glide.with(MainActivity.this).load(icon).into(ivHead);
                }
            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            // SocializeUtils.safeCloseDialog(dialog);
            // Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            // SocializeUtils.safeCloseDialog(dialog);
            // Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void showList(String result) {
        Log.i(TAG, "result:" + result);
        Gson gson = new Gson();
        MainBean mainBean = gson.fromJson(result, MainBean.class);

        datas.addAll(mainBean.getPois());

        adapter.notifyDataSetChanged();
    }
}
