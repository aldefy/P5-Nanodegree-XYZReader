package com.example.xyzreader.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.network.model.DataModel;
import com.example.xyzreader.utils.GetDetailPalette;
import com.example.xyzreader.utils.Utils;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aditlal on 26/03/16.
 */
public class DetailActivity extends AppCompatActivity {


    @Bind(R.id.itemImage)
    ImageView itemImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.article_body)
    TextView articleBody;
    @Bind(R.id.authorTextView)
    TextView authorTextView;
    @Bind(R.id.timeLabel)
    TextView publishedDate;
    @Bind(R.id.topBarLayout)
    RelativeLayout topBarLayout;
    @Bind(R.id.cardView)
    CardView cardView;
    @Bind(R.id.shareFab)
    FloatingActionButton shareFab;
    @Bind(R.id.rootView)
    RelativeLayout rootView;
    private DataModel model;
    private String imageUrl;
    private String title;
    private int circleColor;
    private DateTime publishedDateTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            imageUrl = savedInstanceState.getString(Utils.PHOTO_URL_SAVE_INSTANCE);
            model = (DataModel) savedInstanceState.get(Utils.MODEL_SAVE_INSTANCE);
            if (model != null) {
                title = model.getTitle();
                publishedDateTime = Utils.parseDateTimeFromString(model.getPublished_date());
                setupToolBar();
                setupBody();
            }
        } else {
            handleExtras();
            setupToolBar();
            setupBody();
        }
    }

    private void setupBody() {
        articleBody.setText(Html.fromHtml(model.getBody()));
    }

    private void setupToolBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarTranslucent(true);
        }
        toolbar.setTitle("");
        collapsingToolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        authorTextView.setText(Html.fromHtml("<font color='#e6ffffff'>" + model.getAuthor() + "</font>"));
        publishedDate.setText(publishedDateTime.toString("dd MMM yyyy"));
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(model.getTitle());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(model.getTitle());
                    isShow = false;
                }
            }
        });

        Picasso.with(DetailActivity.this).load(imageUrl).fit()
                .into(itemImage, new GetDetailPalette(DetailActivity.this, rootView, model.getPhoto()));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void handleExtras() {
        Bundle b = getIntent().getExtras();

        imageUrl = b.getString(Utils.IMAGE_URL);
        model = (DataModel) b.get(Utils.DATA_MODEL);
        title = model.getTitle();
        publishedDateTime = Utils.parseDateTimeFromString(model.getPublished_date());
    }

    @OnClick(R.id.shareFab)
    public void onShareFab() {
        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(DetailActivity.this)
                .setType("text/plain")
                .setText("Check out " + model.getTitle() + " by " + model.getAuthor() + " published " + Utils.toRelativeTime(publishedDateTime) + ". Get the " + getResources().getString(R.string.app_name) + " app now.")
                .getIntent(), getString(R.string.action_share)));
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Utils.MODEL_SAVE_INSTANCE, model);
        outState.putString(Utils.PHOTO_URL_SAVE_INSTANCE, imageUrl);
        super.onSaveInstanceState(outState);

    }
}
