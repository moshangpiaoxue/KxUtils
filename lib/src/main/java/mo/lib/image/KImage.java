package mo.lib.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;

import mo.lib.R;

/**
 * @ author：mo
 * @ data：2019/3/21:10:53
 * @ 功能：
 */
public class KImage {
//    x.image().bind(imageView, url, imageOptions);
//x.image().bind(imageView, "file:///sdcard/test.gif", imageOptions);
//x.image().bind(imageView, "assets://test.gif", imageOptions);
//x.image().bind(imageView, url, imageOptions, new Callback.CommonCallback<Drawable>() {...});
//x.image().loadDrawable(url, imageOptions, new Callback.CommonCallback<Drawable>() {...});
//x.image().loadFile(url, imageOptions, new Callback.CommonCallback<File>() {...});




    public static ImageOptions CircularImageOptions() {
        return new ImageOptions.Builder()
                //设置加载过程中的图片
//                .setLoadingDrawableId(R.mipmap.ic_launcher)
//                //设置加载失败后的图片
//                .setFailureDrawableId(R.mipmap.ic_launcher)
                //                .setSize(62,58) //设置大小
                .setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP)
                //imageView 缩放类型
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setConfig(Bitmap.Config.RGB_565)
                //设置使用缓存
                .setUseMemCache(true)
                //设置显示圆形图片
                .setCircular(true)
                //设置支持gif（GIF看人品）
                .setIgnoreGif(false)
                .build();
    }
}
