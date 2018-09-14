package cn.zhaoliang5156.zhangsan20180513.mvp.model;

import cn.zhaoliang5156.zhangsan20180513.mvp.contract.MainContract;
import cn.zhaoliang5156.zhangsan20180513.mvp.model.net.OKHttpUtils;

public class MainModel implements MainContract.MainModel {

    private OKHttpUtils utils;

    public MainModel() {
        utils = OKHttpUtils.getInstance();
    }

    @Override
    public void loadData(String url, final NetCallback callback) {
        utils.get(url, new OKHttpUtils.NetCallback() {
            @Override
            public void success(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void error(String errorMsg) {
                callback.onError(errorMsg);
            }
        });
    }
}
