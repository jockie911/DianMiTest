// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailsSizeFragment$$ViewBinder<T extends com.example.objLoader.fragment.DetailsSizeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689711, "field 'mDetailDataLv'");
    target.mDetailDataLv = finder.castView(view, 2131689711, "field 'mDetailDataLv'");
  }

  @Override public void unbind(T target) {
    target.mDetailDataLv = null;
  }
}
