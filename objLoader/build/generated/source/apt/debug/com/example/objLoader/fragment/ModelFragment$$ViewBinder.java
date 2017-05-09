// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ModelFragment$$ViewBinder<T extends com.example.objLoader.fragment.ModelFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558639, "field 'tencent_model_webview'");
    target.tencent_model_webview = finder.castView(view, 2131558639, "field 'tencent_model_webview'");
  }

  @Override public void unbind(T target) {
    target.tencent_model_webview = null;
  }
}
