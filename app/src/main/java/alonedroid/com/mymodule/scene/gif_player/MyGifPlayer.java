package alonedroid.com.mymodule.scene.gif_player;

import android.app.ActionBar;
import android.graphics.Movie;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.InputStream;

import alonedroid.com.mymodule.R;

public class MyGifPlayer extends ActionBarActivity {
    private LinearLayout imgContainer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gif_player);

        InputStream stream =
                getBaseContext()
                        .getResources()
                        .openRawResource(R.raw.test3);

        GifMovieView gifView = new GifMovieView(this, stream);
        gifView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imgContainer = (LinearLayout) findViewById(R.id.ImgContainer);
        imgContainer.addView(gifView);
    }
}
