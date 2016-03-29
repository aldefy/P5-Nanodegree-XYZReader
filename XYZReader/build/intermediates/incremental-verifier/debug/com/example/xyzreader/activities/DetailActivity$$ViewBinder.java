// Generated code from Butter Knife. Do not modify!
package com.example.xyzreader.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailActivity$$ViewBinder<T extends com.example.xyzreader.activities.DetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755119, "field 'itemImage'");
    target.itemImage = finder.castView(view, 2131755119, "field 'itemImage'");
    view = finder.findRequiredView(source, 2131755120, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755120, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755118, "field 'collapsingToolbar'");
    target.collapsingToolbar = finder.castView(view, 2131755118, "field 'collapsingToolbar'");
    view = finder.findRequiredView(source, 2131755117, "field 'appbar'");
    target.appbar = finder.castView(view, 2131755117, "field 'appbar'");
    view = finder.findRequiredView(source, 2131755121, "field 'nestedScrollView'");
    target.nestedScrollView = finder.castView(view, 2131755121, "field 'nestedScrollView'");
    view = finder.findRequiredView(source, 2131755116, "field 'mainContent'");
    target.mainContent = finder.castView(view, 2131755116, "field 'mainContent'");
    view = finder.findRequiredView(source, 2131755126, "field 'articleBody'");
    target.articleBody = finder.castView(view, 2131755126, "field 'articleBody'");
    view = finder.findRequiredView(source, 2131755123, "field 'authorTextView'");
    target.authorTextView = finder.castView(view, 2131755123, "field 'authorTextView'");
    view = finder.findRequiredView(source, 2131755124, "field 'publishedDate'");
    target.publishedDate = finder.castView(view, 2131755124, "field 'publishedDate'");
    view = finder.findRequiredView(source, 2131755122, "field 'topBarLayout'");
    target.topBarLayout = finder.castView(view, 2131755122, "field 'topBarLayout'");
    view = finder.findRequiredView(source, 2131755125, "field 'cardView'");
    target.cardView = finder.castView(view, 2131755125, "field 'cardView'");
    view = finder.findRequiredView(source, 2131755127, "field 'shareFab' and method 'onShareFab'");
    target.shareFab = finder.castView(view, 2131755127, "field 'shareFab'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onShareFab();
        }
      });
    view = finder.findRequiredView(source, 2131755115, "field 'rootView'");
    target.rootView = finder.castView(view, 2131755115, "field 'rootView'");
  }

  @Override public void unbind(T target) {
    target.itemImage = null;
    target.toolbar = null;
    target.collapsingToolbar = null;
    target.appbar = null;
    target.nestedScrollView = null;
    target.mainContent = null;
    target.articleBody = null;
    target.authorTextView = null;
    target.publishedDate = null;
    target.topBarLayout = null;
    target.cardView = null;
    target.shareFab = null;
    target.rootView = null;
  }
}
