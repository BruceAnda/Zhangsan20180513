package cn.zhaoliang5156.zhangsan20180513.mvp.base;

/**
 * mvp p 层规范
 *
 * @author zhaoliang
 * @version 1.0
 * @create 2018/9/14
 */
public interface IPresenter<V extends IView> {

    void attach(V v);

    void detach();
}
