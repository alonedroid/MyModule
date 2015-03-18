package alonedroid.com.mymodule.scene.gif_player;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import alonedroid.com.mymodule.R;


public class GifMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		
		final GifMovieView gif1 = (GifMovieView) findViewById(R.id.gif1);
		gif1.setMovieResource(R.drawable.gif_heart);
	}

	public void onGifClick(View v) {
		GifMovieView gif = (GifMovieView) v;
		gif.setPaused(!gif.isPaused());
	}

}
