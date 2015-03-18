package alonedroid.com.mymodule.scene.volley_jackson;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

import alonedroid.com.mymodule.R;

public class VolleyActivity extends ActionBarActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d("volley", "test");

        // 東京都の天気情報
        String url =
                "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010";

        mQueue = Volley.newRequestQueue(this);
        mQueue.add(new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // JSONObjectのパース、List、Viewへの追加等

                        Toast.makeText(VolleyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("gyao", response.toString());

                        ObjectMapper map = new ObjectMapper();
                        try {
                            JsonObject hoge = map.readValue(response.toString(), JsonObject.class);
                            Log.d("gyao", hoge.forecasts.get(0).image.title);
                            hoge.publicTime="nodata";
                            String json = map.writeValueAsString(hoge);
                            Log.d("gyao", json);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // エラー処理 error.networkResponseで確認
                        // エラー表示など
                        Log.d("volley", "error");
                    }
                }));

        setNetworkImageView();
    }

    private ImageLoader.ImageContainer container;

    private void setNetworkImageView() {
//        final int memClass = ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
//
//        // Use 1/8th of the available memory for this memory cache.
//        final int cacheSize = 1024 * 1024 * memClass / 8;
//
//        LruCache<String, Bitmap> mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
//            @Override
//            protected int sizeOf(String key, Bitmap bitmap) {
//                // The cache size will be measured in bytes rather than number
//                // of items.
//                return bitmap.getByteCount();
//            }
//        };

        String url = "http://techbooster.org/wp-content/uploads/2013/08/densi.png";

        NetworkImageView view = (NetworkImageView) findViewById(R.id.network_image_view);
        view.setImageUrl(url, new ImageLoader(mQueue, BitmapCache_.getInstance_(this)));


        final ImageView iv = (ImageView) findViewById(R.id.network_image_view2);

        this.container = new ImageLoader(mQueue, BitmapCache_.getInstance_(this)).get(url, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {

                    iv.setImageDrawable(new BitmapDrawable(VolleyActivity.this.getResources(), response.getBitmap()));
                    container = null;
                }
            }
        });
    }

    @Override
    public void onLowMemory() {
        if(mQueue != null) {
            mQueue.add(new ClearCacheRequest(mQueue.getCache(), null));
        }
        super.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        if(mQueue != null) {
            mQueue.add(new ClearCacheRequest(mQueue.getCache(), null));
        }
        super.onDestroy();
    }
}
