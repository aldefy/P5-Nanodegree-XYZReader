// Generated code from Butter Knife. Do not modify!
package com.example.xyzreader.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ItemAdapter$ViewHolder$$ViewBinder<T extends com.example.xyzreader.adapter.ItemAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755119, "field 'itemImage'");
    target.itemImage = finder.castView(view, 2131755119, "field 'itemImage'");
    view = finder.findRequiredView(source, 2131755132, "field 'articleTitle'");
    target.articleTitle = finder.castView(view, 2131755132, "field 'articleTitle'");
    view = finder.findRequiredView(source, 2131755133, "field 'articleSubtitle'");
    target.articleSubtitle = finder.castView(view, 2131755133, "field 'articleSubtitle'");
    view = finder.findRequiredView(source, 2131755131, "field 'textContentLayout'");
    target.textContentLayout = finder.castView(view, 2131755131, "field 'textContentLayout'");
    view = finder.findRequiredView(source, 2131755124, "field 'timeLabel'");
    target.timeLabel = finder.castView(view, 2131755124, "field 'timeLabel'");
    view = finder.findRequiredView(source, 2131755130, "field 'mainContentLayout'");
    target.mainContentLayout = finder.castView(view, 2131755130, "field 'mainContentLayout'");
    view = finder.findRequiredView(source, 2131755125, "field 'cardView'");
    target.cardView = finder.castView(view, 2131755125, "field 'cardView'");
  }

  @Override public void unbind(T target) {
    target.itemImage = null;
    target.articleTitle = null;
    target.articleSubtitle = null;
    target.textContentLayout = null;
    target.timeLabel = null;
    target.mainContentLayout = null;
    target.cardView = null;
  }
}
