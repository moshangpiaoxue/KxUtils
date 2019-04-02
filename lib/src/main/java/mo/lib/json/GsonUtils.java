package mo.lib.json;

import com.google.gson.Gson;
import com.google.gson.JsonNull;

import java.lang.reflect.Type;

/**
 * @ author：mo
 * @ data：2019/4/2:11:10
 * @ 功能：
 */
public enum GsonUtils {
    /**
     * 枚举单例
     */
    INSTANCE;
    private Gson mGson;

     GsonUtils() {
        mGson = new Gson();
    }

    public Gson getGson() {
        return mGson;
    }

    /**
     * 将对象转为JSON串，此方法能够满足大部分需求
     *
     * @param src :将要被转化的对象
     * @return :转化后的JSON串
     */
    public String toJson(Object src) {
        if (src == null) {
            return mGson.toJson(JsonNull.INSTANCE);
        }
        return mGson.toJson(src);
    }

    /**
     * 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     *
     * @param json     json串
     * @param classOfT 需要转换的类class
     * @return
     */
    public <T> Object fromJson(String json, Class<T> classOfT) {
        return mGson.fromJson(json, (Type) classOfT);
    }
}
