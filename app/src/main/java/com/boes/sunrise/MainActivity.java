package com.boes.sunrise;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = (LinearLayout) findViewById(R.id.container);

        setImageView(container, (AspectRatioImageView) findViewById(R.id.landscape), R.drawable.landscape, true);
        setPanoramaView(container, (AspectRatioImageView) findViewById(R.id.panorama), R.drawable.panorama);
        setImageView(container, (AspectRatioImageView) findViewById(R.id.portrait), R.drawable.portrait, true);

        setImageView(container, (AspectRatioImageView) findViewById(R.id.thumbnail), R.drawable.landscape, false);
        setImageView(container, (AspectRatioImageView) findViewById(R.id.thumbnail2), R.drawable.landscape, false);

        setImageView(container, null, R.drawable.landscape, false);
        setImageView(container, null, R.drawable.panorama, false);
        setImageView(container, null, R.drawable.portrait, false);
    }

    private void setImageView(ViewGroup container, AspectRatioImageView imageView, int drawable, boolean horizontalScroll) {
        if (imageView == null) {
            imageView = new AspectRatioImageView(this);
            container.addView(imageView);
        }

        BitmapFactory.Options options = getBitmapOptions(drawable);
        DisplayMetrics display = getResources().getDisplayMetrics();

        if (horizontalScroll &&
            options.outWidth > options.outHeight &&
            display.widthPixels < display.heightPixels) {
            wrapImageViewWithHorizontalScroll(container, imageView);
        }

        int maxViewDimension = Math.max(display.widthPixels, display.heightPixels);
        imageView.setDimensions(options.outWidth, options.outHeight, maxViewDimension);
        Picasso.with(this).load(drawable).fit().into(imageView);
    }

    private void setPanoramaView(ViewGroup container, AspectRatioImageView imageView, int drawable) {
        BitmapFactory.Options options = getBitmapOptions(drawable);
        wrapImageViewWithHorizontalScroll(container, imageView);
        int maxViewDimension = 4096;
        imageView.setDimensions(options.outWidth, options.outHeight, maxViewDimension);
        Picasso.with(this).load(drawable).fit().into(imageView);
    }

    private BitmapFactory.Options getBitmapOptions(int drawable) {
        Resources res = getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, drawable, options);
        return options;
    }

    private void wrapImageViewWithHorizontalScroll(ViewGroup container, ImageView imageView) {
        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        int index = container.indexOfChild(imageView);
        container.removeViewAt(index);
        scrollView.addView(imageView);
        container.addView(scrollView, index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
