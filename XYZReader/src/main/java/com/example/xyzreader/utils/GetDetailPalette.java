package com.example.xyzreader.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xyzreader.R;
import com.squareup.picasso.Callback;

/**
 * Created by aditlal on 28/03/16.
 */
public class GetDetailPalette implements Callback, Palette.PaletteAsyncListener {
    private int mColorFrom;
    private int mPrimaryDarkColor;
    RelativeLayout topBarLayout;
    CollapsingToolbarLayout collapsingToolbar;
    FloatingActionButton shareFab;
    private Context mContext;
    ImageView mImageView;
    String urlPath;

    public GetDetailPalette(Context context, View view,String urlPath) {
        topBarLayout = (RelativeLayout) view.findViewById(R.id.topBarLayout);
        this.urlPath = urlPath;
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        shareFab = (FloatingActionButton) view.findViewById(R.id.shareFab);
        mImageView =(ImageView) view.findViewById(R.id.itemImage);
        this.mContext = context;
        mColorFrom = ContextCompat.getColor(context, android.R.color.white);
        mPrimaryDarkColor = ContextCompat.getColor(context, R.color.primary_dark);
    }



    @Override
    public void onGenerated(Palette palette) {
        Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
        if (vibrant != null) {
            Logger.d("PALEETE", "vibrantDark " + vibrant.getRgb());
            topBarLayout.setBackgroundColor(vibrant.getRgb());
        } else {
            Logger.d("Vibrant not available");
        }
        int color = palette.getDarkMutedColor(ContextCompat.getColor(mContext, R.color.primary));
        if (color != -1) {
            Logger.d("PALEETE", "getLightMutedColor " + color);
            topBarLayout.setBackgroundColor(color);
            collapsingToolbar.setContentScrim(new ColorDrawable(color));
        }

        Palette.Swatch mutedD = palette.getLightMutedSwatch();
        if (mutedD != null) {
            Logger.d("PALEETE", "getLightMutedSwatch " + mutedD.getRgb());
            shareFab.setBackgroundTintList(ColorStateList.valueOf(mutedD.getRgb()));
        }
    }

    @Override
    public void onSuccess() {
        Palette.from(((BitmapDrawable) mImageView.getDrawable()).getBitmap()).generate(this);
    }

    @Override
    public void onError() {
        topBarLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.primary));
    }
}
