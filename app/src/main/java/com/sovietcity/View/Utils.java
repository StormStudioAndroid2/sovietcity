package com.sovietcity.View;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by Серёга on 27.06.2016.
 */

public class Utils {
    private static final Saver saver = new Saver();
    public static void runOnUiThread(Runnable runnable) {
        final Handler UIHandler = new Handler(Looper.getMainLooper());
        UIHandler.post(runnable);
    }
    public static void init(Context context) {
        saver.setSaves(context);
    }
    public static Saver getSaver() {
        return saver;
    }
}
