package com.yahoo.android.search.showcase.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {

    private static DisplayMetrics displayMetrics = null;

    public static int getScreenWidth(Context c) {
        displayMetrics = getDisplayMetrics(c);
        return displayMetrics.widthPixels;
    }

    public static synchronized DisplayMetrics getDisplayMetrics(Context c) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }
}
