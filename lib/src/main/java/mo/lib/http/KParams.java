package mo.lib.http;


import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;

import java.net.HttpCookie;
import java.util.List;

/**
 * @ author：mo
 * @ data：2018/9/19
 * @ 功能：基础请求参数
 */
public class KParams {
    public static RequestParams getRequestParams(String url) {
        //传URL
        RequestParams params = new RequestParams(url);
        //设置超时时间
        params.setConnectTimeout(15000);
        //设置读取时间
        params.setReadTimeout(15000);
        //设置最大请求错误重试次数 只请求一次的情况下，必须设置超时时间
        params.setMaxRetryCount(0);
        //设置cookie 注意value的格式，SESSION=加上cookie的值，不同的后台框架，格式不一样
        //params.addHeader("cookie", "SESSION=" + SPFUtil.getString("m_cookie"));
        return params;
    }

    /**
     * 获取cookie 此方法要在请求成功或完成后调用，不然拿到的值就看人品了
     */
    public static String getCookie() {
        String cookie = "";
        DbCookieStore instance = DbCookieStore.INSTANCE;
        List<HttpCookie> cookies = instance.getCookies();
        for (int i = 0; i < cookies.size(); i++) {
            cookie = cookies.get(i).getValue();
        }
        return cookie;
    }
}
