package com.exuelogin.utils;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Toast;

import com.exuelogin.R;


/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月18日
 * @description 应用程序的公共工具类
 */
public class CommonTools {
    static MediaPlayer mpalyer;
    static MediaRecorder mr;
    static ProgressDialog progressDialog;

//    public static void dismissProgressDialog() {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
//    }

    private static Toast toast;

    //由于实现沉浸式状态栏，导致系统自带的Toast出现问题，所以自定义Toast的View
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = new Toast(context);
            toast.setView(View.inflate(context, R.layout.layout_toast, null));
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(content);
        toast.show();
    }

    public static void showToast(Context context, int resId) {
        if (toast == null) {
            toast = new Toast(context);
            toast.setView(View.inflate(context, R.layout.layout_toast, null));
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(resId);
        toast.show();
    }


}
