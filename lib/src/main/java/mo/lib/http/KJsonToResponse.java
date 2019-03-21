package mo.lib.http;

import com.google.gson.Gson;

import org.xutils.common.util.LogUtil;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

/**
 * Created by mo
 * Date 2016/7/11  18:08
 * Describe 使用gson进行解析json数据
 */
public class KJsonToResponse implements ResponseParser {


    private String des;

    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        LogUtil.i(request + "");
    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, final String result) throws Throwable {
        LogUtil.i("result" + result);
        //进行判断是否为code 是否为0,进行json解析，
//        JSONObject jsonObject=new JSONObject(result);
//        String code=jsonObject.getString("code");
//        if(!code.equals("0")){
//            //即为code!=0的时候
//            JSONObject data=jsonObject.getJSONObject("data");
//            des = data.getString("des");
//            Looper.prepare();
//            UtilToast.showShort(x.app(),des);
//            Looper.loop();
//        }
        return new Gson().fromJson(result, resultType);
    }
}
