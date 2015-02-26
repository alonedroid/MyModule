package alonedroid.com.mymodule;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    private final String TAG = "DEBUG-APPLICATION";

    @Override
    public void onCreate() {
        /** Called when the Application-class is first created. */
        Log.v(TAG, "--- onCreate() in ---");
    }

    @Override
    public void onTerminate() {
        /** This Method Called when this Application finished. */
        Log.v(TAG, "--- onTerminate() in ---");
    }

}
