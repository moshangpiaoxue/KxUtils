package mo.lib.http;


import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author：mo
 * @ data：2018/9/19
 * @ 功能：xUtils网络请求工具类
 */
public class KHttpUtils {
    /**
     * GET请求
     *
     * @param interfaceStr 接口名称或想要干什么的提示
     * @param params       请求参数
     * @param callback     请求回调
     * @return Cancelable  (可取消此请求，一般不用)
     */
    public static Callback.Cancelable Get(String interfaceStr, RequestParams params, Callback.CommonCallback callback) {
        //打印请求信息
        LogUtil.i("GET请求==" + interfaceStr + "==" + params.toString());
        return x.http().get(params, callback);
    }

    /**
     * POST请求
     *
     * @param interfaceStr 接口名称或想要干什么的提示
     * @param params       请求参数
     * @param callback     请求回调
     * @return Cancelable  (可取消此请求，一般不用)
     */
    public static Callback.Cancelable Post(String interfaceStr, RequestParams params, Callback.CommonCallback callback) {
        //打印请求信息
        LogUtil.i("POST请求==" + interfaceStr + "==" + params.toString());
        return x.http().post(params, callback);
    }

    /**
     * 上传文件
     *
     * @param params   请求参数
     * @param callback 请求回调
     * @return (可取消此请求)
     */
    public static Callback.Cancelable UpLoadFile(RequestParams params, Callback.CommonCallback callback) {
        //设置表单上传
        params.setMultipart(true);
        params.setAsJsonContent(true);
        //打印请求信息
        LogUtil.i("上传文件==" + params.toString());
        return x.http().post(params, callback);
    }

    /**
     * 上传文件
     *
     * @param params   请求参数
     * @param file     要串的文件
     * @param callback 回调
     * @return
     */
    public static Callback.Cancelable UpLoadFile(RequestParams params, File file, Callback.CommonCallback callback) {
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file", file));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        return UpLoadFile(params, callback);
    }

    /**
     * 下载文件
     *
     * @param url      请求地址
     * @param filepath 保存地址
     * @param callback 回调
     * @return (可取消此请求)
     */
    public static Callback.Cancelable DownLoadFile(String url, String filepath, Callback.CommonCallback callback) {
        RequestParams params = new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        LogUtil.i("下载文件==" + params.toString());
        return x.http().get(params, callback);
    }
}
