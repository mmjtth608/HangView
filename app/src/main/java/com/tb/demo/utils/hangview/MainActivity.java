package com.tb.demo.utils.hangview;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tb.demo.utils.hangview.custom.TbScrollView;

public class MainActivity extends Activity implements
        TbScrollView.OnScrollListener {
    private ImageView top_img;
    private ViewGroup bar;
    private ViewGroup bar_fixed;
    private ViewGroup bar_float;
    private TbScrollView tbScrollView;
    private float barTop;
    private ViewGroup rl_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top_img = (ImageView) findViewById(R.id.top_img);
        bar = (ViewGroup) findViewById(R.id.bar);
        bar_fixed = (ViewGroup) findViewById(R.id.bar_fixed);
        bar_float = (ViewGroup) findViewById(R.id.bar_float);
        rl_bar = (ViewGroup) findViewById(R.id.rl_bar);
        tbScrollView = (TbScrollView) findViewById(R.id.myscrollview);
        tbScrollView.setOnScrollListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY > barTop) {
            if (bar.getParent() != bar_float) {
                bar_fixed.removeView(bar);
                bar_float.addView(bar);
            }
        } else {
            if (bar.getParent() != bar_fixed) {
                bar_float.removeView(bar);
                bar_fixed.addView(bar);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            TypedArray actionbarSizeTypedArray = obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
            float actionbarSize = actionbarSizeTypedArray.getDimension(0, 0);

            barTop = top_img.getBottom();
        }
    }
}
