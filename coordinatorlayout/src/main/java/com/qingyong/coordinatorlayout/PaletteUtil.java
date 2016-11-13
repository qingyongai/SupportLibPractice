package com.qingyong.coordinatorlayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;

/**
 * <b>Project:</b> com.qingyong.coordinatorlayout <br>
 * <b>Create Date:</b> 2016/11/13 <br>
 * <b>Author:</b> Devin <br>
 * <b>Address:</b> qingyong@linghit.com <br>
 * <b>Description:</b> Palette <br>
 */
public class PaletteUtil {

    /**
     * Palette使用
     *
     * @param context  context
     * @param id       图片id
     * @param listener 监听器
     */
    public static void paletteGen(Context context, int id, Palette.PaletteAsyncListener listener) {
        Resources resources = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, id);
        if (bitmap != null) {
            Palette.from(bitmap).generate(listener);
        }
    }

}
