package alonedroid.com.mymodule.scene.custom_view;

import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import alonedroid.com.mymodule.R;

@EActivity(R.layout.activity_custom_view)
public class CustomViewActivity extends ActionBarActivity {

    @ViewById
    CustomIndicatorView indicator;

    @ViewById
    TextView prev, add, next;

    private int total, position;

    @Click
    protected void prev() {
        if (position == 0) return;
        indicator.selectAt(--position);
    }

    @Click
    protected void add() {
        indicator.setCount(++total);
    }

    @Click
    protected void next() {
        if (position >= (total - 1)) return;
        indicator.selectAt(++position);
    }
}
