// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WebviewActivity$$ViewBinder<T extends com.example.objLoader.module.WebviewActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689718, "field 'webView'");
    target.webView = finder.castView(view, 2131689718, "field 'webView'");
  }

  @Override public void unbind(T target) {
    target.webView = null;
  }
}
