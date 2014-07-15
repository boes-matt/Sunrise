package com.boes.sunrise;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AspectRatioImageView extends ImageView {

    private int imageWidth;
    private int imageHeight;
    private int maxViewDimension;

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDimensions(int imageWidth, int imageHeight, int maxViewDimension) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.maxViewDimension = maxViewDimension;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();

        if (viewWidth != 0) {
            viewHeight = (viewWidth * imageHeight) / imageWidth;
        } else if (viewHeight != 0) {
            viewWidth = (viewHeight * imageWidth) / imageHeight;

        // For when both viewWidth and viewHeight == 0, such as
        // when the view layout is set to wrap content, or
        // when this view is inside a scroll view.
        } else if (imageWidth > imageHeight) {
            viewWidth = Math.min(imageWidth, maxViewDimension);
            viewHeight = (viewWidth * imageHeight) / imageWidth;
        } else {
            viewHeight = Math.min(imageHeight, maxViewDimension);
            viewWidth = (viewHeight * imageWidth) / imageHeight;
        }

        setMeasuredDimension(viewWidth, viewHeight);
    }

}
