// Generated code from Butter Knife. Do not modify!
package com.example.xyzreader.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ListFragment$$ViewBinder<T extends com.example.xyzreader.fragments.ListFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755141, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131755141, "field 'mRecyclerView'");
  }

  @Override public void unbind(T target) {
    target.mRecyclerView = null;
  }
}