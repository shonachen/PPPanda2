package com.pppanda.util;

import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * Created by zc on 2017/2/22.
 */

public class PicassoUtil {
    private static volatile Picasso singleton = null;
    private static boolean isFirstDownloadPic = true;

    public static Picasso with(Context context){
        if (singleton == null) {
            synchronized (Picasso.class) {
                if (singleton == null) {
                    singleton = new Picasso.Builder(context)
                            .downloader(new OkHttp3Downloader(context))
                            .build();
                }
            }
        }
        return singleton;
    }

    public static boolean isFirstDownloadPic() {
        return isFirstDownloadPic;
    }

    public static void setIsFirstDownloadPic(boolean isFirstDownloadPic) {
        PicassoUtil.isFirstDownloadPic = isFirstDownloadPic;
    }
}
