package alonedroid.com.mymodule.scene.volley_jackson;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

public class NoCache implements ImageLoader.ImageCache {

    NoCache(){
    }

    // ImageCacheのインターフェイス実装
    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

    }
}
