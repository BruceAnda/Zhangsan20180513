package cn.zhaoliang5156.zhangsan20180513.mvp.contract;

import cn.zhaoliang5156.zhangsan20180513.mvp.base.IModel;
import cn.zhaoliang5156.zhangsan20180513.mvp.base.IPresenter;
import cn.zhaoliang5156.zhangsan20180513.mvp.base.IView;

public interface MainContract {

    interface MainView extends IView {
        void showList(String result);
    }

    interface MainModel extends IModel {

        interface NetCallback {
            void onSuccess(String result);

            void onError(String meg);
        }

        void loadData(String url, NetCallback callback);
    }

    interface MainPresenter extends IPresenter<MainView> {

        void loadData(String url);
    }
}
