package com.luck.picture.lib.model;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Wpz on 2016/12/13.
 */
public class ToastUtils {
    private static Toast toast = null;

    public static void showMsg(Context context, String content) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        toast.show();
    }


    public static void showMsg(Context context, int resid) {
        showMsg(context, context.getString(resid));
    }

}
