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
public abstract class KCallBack<T> implements Callback.CommonCallback<String> {
    /**
     * 请求结果
     */
    private String httpReault = "";
    /**
     * 是否报错
     */
    private boolean isError = false;
    /**
     * log标识，打印信息用
     */
    private String tag = "";
    /**
     * 是否解析数据，默认true，（在有些时候，不需要知道返回的数据是什么，只要成功就可以了，但是T给String的话解析会报错）
     */
    private boolean isJson = true;

    public KCallBack() {
    }

    public KCallBack(String tag) {
        this.tag = tag;
    }

    public KCallBack(boolean isJson) {
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
        if (ex instanceof HttpException) {
            HttpException httpException = (HttpException) ex;
            int code = httpException.getCode();
            if (code == 401) {
//              清除本地缓存 190320 嘉哥说不退出登录，改成提示
//                cleanAcount();
                LogUtil.i("您没有此功能的操作权限！");
            }
            if (code == 402) {
//                new IosAlertDialog(k.app()).builder().setCancelable(false).setTitle("温馨提示").setMsg("该账户已被冻结，请联系客服！").setPositiveButton("确定", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                LogUtil.i("该账户已被冻结，请联系客服！");
//                        Intent intent = new Intent(k.app(), LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        k.app().startActivity(intent);
//                    }
//                }).show();
//                SpfModle.cleanLoginInfo();
            }
            if (code == 403) {
                LogUtil.i("没权限");
            }
            if (code == 404) {
                LogUtil.i("呼叫后台！" + tag + "404了");
            }
            if (code == 405) {
                LogUtil.i("请求被拒绝，切换POST或GET请求");
            }
            //被顶号
            if (code == 417) {
                LogUtil.i("被顶号");
            }
            if (code == 500) {
                LogUtil.i("呼叫后台！500了");
            }
            if (code == 504) {
                LogUtil.i("网络请求超时,请重试");

            }
        } else if (ex instanceof ConnectException) {
            LogUtil.i("端口被占用，请重启应用");
        } else if (ex instanceof UnknownHostException) {
            LogUtil.i("当前网络已断开,请检查您的网络连接");
        } else if (ex instanceof SocketTimeoutException) {
            LogUtil.i("网络请求超时");
        }
    }

    /**
     * 完成
     */
    @Override
    public void onFinished() {
        if (!isError && httpReault != null) {
            LogUtil.i(tag + ":网络请求完成");
            if (isJson) {
                onSuccess1((T) new Gson().fromJson(httpReault, getTClass()));
            } else {
                onSuccess1((T) httpReault);
            }
        }
    }

    /**
     * 抽象方法，对外必现
     */
    protected abstract void onSuccess1(T t);

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
