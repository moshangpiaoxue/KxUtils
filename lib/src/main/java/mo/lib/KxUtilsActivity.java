package mo.lib;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.xutils.x;

/**
 * @ author：mo
 * @ data：2019/3/21:11:24
 * @ 功能：很重要！！！！！！或继承此类。或在自己的Activity里调用 x.view().inject(this);不然用不了注解
 */
//@ContentView(R.layout.activity_main)//注入布局
public class KxUtilsActivity extends Activity {
    //注入控件
//    @ViewInject(R.id.btn_get)
//    Button btn_get;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
    //点击事件  修饰符必须private！！！！！！！！！
    //等同于@Event(value={R.id.btn_get,R.id.btn_post},type=View.OnClickListener.class)
//    @Event(value={R.id.btn_get,R.id.btn_post})
//    private void getEvent(View view){
//        switch(view.getId()){
//            case R.id.btn_get:
//                Toast.makeText(MainActivity.this, btn_get.getText().toString().trim(), 0).show();
//                break;
//            case R.id.btn_post:
//                Toast.makeText(MainActivity.this, btn_post.getText().toString().trim(), 0).show();
//                break;
//        }
}
