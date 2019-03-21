package mo.lib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * @ author：mo
 * @ data：2019/3/21:11:27
 * @ 功能：很重要！！！！！！或继承此类。或在自己的Fragment里调用x.view().inject(this, inflater, container);不然用不了注解
 */

//@ContentView(R.layout.activity_main)//注入布局
public class KxUtilsFragment extends Fragment {
    //注入控件
//    @ViewInject(R.id.btn_get)
//    Button btn_get;
    /**
     * 注入
     */
    private boolean injected = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //判断如果没有注入则进行初始注入
        if (!injected) {
            x.view().inject(this, this.getView());
        }
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
