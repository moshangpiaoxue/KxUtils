package mo.lib.http;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;

import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @ author：mo
 * @ data：2018/9/19
 * @ 功能：
 */
public  class KCallBack<T> implements Callback.CommonCallback<String> {
    /**
     * 请求结果
     */
    protected String httpReault = "";
    /**
     * 是否报错
     */
    protected boolean isError = false;
    /**
     * log标识，打印信息用
     */
    protected String tag = "";
    /**
     * 是否解析数据，默认true，（在有些时候，不需要知道返回的数据是什么，只要成功就可以了，但是T给String的话解析会报错）
     */
    protected boolean isJson = true;

    public KCallBack() {
    }

    public KCallBack(String tag) {
        this.tag = tag;
    }

    public KCallBack(boolean isJson) {
        this.isJson = isJson;
    }

    public KCallBack(String tag, boolean isJson) {
        this.tag = tag;
        this.isJson = isJson;
    }

    /**
     * 取消
     */
    @Override
    public void onCancelled(CancelledException arg0) {
        LogUtil.i(tag + ":网络请求取消");
    }

    /**
     * 报错
     */
    @Override
    public void onError(Throwable ex, boolean arg1) {
        LogUtil.i(tag + ":网络请求失败=" + ex != null ? ex.getMessage().toString() : "自定义的");
        isError = true;
        ex.printStackTrace();
    }

    /**
     * 完成
     */
    @Override
    public void onFinished() {
        if (!isError && httpReault != null) {
            LogUtil.i(tag + ":网络请求完成");
        }
    }


    /**
     * 成功
     */
    @Override
    public void onSuccess(String result) {
        this.httpReault = result;
        LogUtil.i(tag + ":网络请求成功=" + result);

    }

    /**
     * 获取类class（只在当前类里生效，外部调用无效）
     */
    private Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

}
