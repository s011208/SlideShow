
package com.bj4.yhh.slideshow;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

public class SlidingFonts {
    public static final ArrayList<String> FONTS = new ArrayList<String>();

    public static synchronized void initIfNeeded(Context context) {
        if (FONTS.size() > 0)
            return;
        try {
            for (String textType : context.getAssets().list("fonts")) {
                FONTS.add("fonts/" + textType);
            }
        } catch (IOException e) {
        }
    }
}
