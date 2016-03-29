// Generated code from Butter Knife. Do not modify!
package com.example.xyzreader.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.xyzreader.activities.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755117, "field 'appbar'");
    target.appbar = finder.castView(view, 2131755117, "field 'appbar'");
    view = finder.findRequiredView(source, 2131755128, "field 'coordinator'");
    target.coordinator = finder.castView(view, 2131755128, "field 'coordinator'");
    view = finder.findRequiredView(source, 2131755120, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755120, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.appbar = null;
    target.coordinator = null;
    target.toolbar = null;
  }
}
