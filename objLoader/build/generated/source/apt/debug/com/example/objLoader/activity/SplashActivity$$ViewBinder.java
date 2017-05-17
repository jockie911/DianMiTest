// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SplashActivity$$ViewBinder<T extends com.example.objLoader.activity.SplashActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689679, "field 'ivSplash'");
    target.ivSplash = finder.castView(view, 2131689679, "field 'ivSplash'");
  }

  @Override public void unbind(T target) {
    target.ivSplash = null;
  }
}
