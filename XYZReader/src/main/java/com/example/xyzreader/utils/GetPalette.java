package com.example.xyzreader.utils;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.squareup.picasso.Callback;

/**
 * Created by aditlal on 26/03/16.
 */
public class GetPalette
        implements AnimatorUpdateListener, Callback, Palette.PaletteAsyncListener {
    private LinearLayout mLinearLayout;
    private ImageView mImageView;
    private TextView mTitle, mSubTitle;
    private int mColorFrom;
    private int mPrimaryDarkColor;
    private Context mContext;

    public GetPalette(Context context, View view) {
        mLinearLayout = (LinearLayout) view.findViewById(R.id.textContent);
        mImageView = (ImageView) view.findViewById(R.id.itemImage);
        mTitle = (TextView) view.findViewById(R.id.article_title);
        mSubTitle = (TextView) view.findViewById(R.id.article_subtitle);
        this.mContext = context;
        mColorFrom = ContextCompat.getColor(context, android.R.color.white);
        mPrimaryDarkColor = ContextCompat.getColor(context, R.color.primary_dark);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mLinearLayout.setBackgroundColor((Integer) animation.getAnimatedValue());
    }

    @Override
    public void onGenerated(Palette palette) {
        Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
        ValueAnimator colorAnimation;

        if (vibrant != null) {
            mLinearLayout.setBackgroundColor(vibrant.getRgb());
            mTitle.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            mSubTitle.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            int colorTo = palette.getDarkMutedColor(mPrimaryDarkColor);
            mTitle.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            mSubTitle.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            mLinearLayout.setBackgroundColor(colorTo);

        }
    }

    @Override
    public void onSuccess() {
        Palette.from(((BitmapDrawable) mImageView.getDrawable()).getBitmap()).generate(this);
    }

    @Override
    public void onError() {
        mTitle.setTextColor(ContextCompat.getColor(mContext, R.color.primary_text));
        mSubTitle.setTextColor(ContextCompat.getColor(mContext, R.color.secondary_text));
        mLinearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
    }
}
