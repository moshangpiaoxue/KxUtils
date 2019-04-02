package mo.lib.json;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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
    /**
     * 用来将 JSON串 转为对象，此方法可用来转带泛型的集合，
     * 如：Type 为 new TypeToken<List<T>>(){}.getType()，
     * 其它类也可以用此方法调用，就是将List<T>替换为你想要转成的类
     *
     * @param json    json串
     * @param typeOfT 需要转换的类class
     * @return
     */
    public Object fromJson(String json, Type typeOfT) {
        return mGson.fromJson(json, typeOfT);
    }
    public <T>Object  fromJson(String json) {
        return mGson.fromJson(json, new TypeToken<T>() {
        }.getType());
    }
    /**
     * 用来将 JSON串 转为对象，此方法可用来转带泛型的集合
     * 如：Type为 new TypeToken<List<T>>(){}.getType()其它类也可以用此方法调用，
     * 就是将List<T>替换为你想要转成的类
     *
     * @param json
     * @param typeOfT
     * @return
     */
    public List<Object> fromJsonArray(String json, Type typeOfT) {
        return mGson.fromJson(json, typeOfT);
    }

}
