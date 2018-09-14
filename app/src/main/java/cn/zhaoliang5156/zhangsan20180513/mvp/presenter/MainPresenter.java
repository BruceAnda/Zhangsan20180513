package cn.zhaoliang5156.zhangsan20180513.mvp.presenter;

import java.lang.ref.WeakReference;

import cn.zhaoliang5156.zhangsan20180513.mvp.base.IModel;
import cn.zhaoliang5156.zhangsan20180513.mvp.base.IView;
import cn.zhaoliang5156.zhangsan20180513.mvp.contract.MainContract;
import cn.zhaoliang5156.zhangsan20180513.mvp.model.MainModel;

public class MainPresenter implements MainContract.MainPresenter {

    private WeakReference<MainContract.MainView> viewWeakReference;
    private WeakReference<MainModel> modelWeakReference;

    @Override
    public void loadData(String url) {
        modelWeakReference.get().loadData(url, new MainContract.MainModel.NetCallback() {
            @Override
            public void onSuccess(String result) {
                viewWeakReference.get().showList(result);
            }

            @Override
            public void onError(String meg) {

            }
        });
    }

    @Override
    public void attach(MainContract.MainView mainView) {
        viewWeakReference = new WeakReference(mainView);
        modelWeakReference = new WeakReference(new MainModel());
    }

    @Override
    public void detach() {
        if (viewWeakReference != null) {
            viewWeakReference.clear();
            viewWeakReference = null;
            modelWeakReference.clear();
            modelWeakReference = null;
        }
    }
}
