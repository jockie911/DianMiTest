// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyLogDetailsActivity$$ViewBinder<T extends com.example.objLoader.activity.MyLogDetailsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689659, "field 'll_my_log_size' and method 'onClick'");
    target.ll_my_log_size = finder.castView(view, 2131689659, "field 'll_my_log_size'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689661, "field 'll_my_log_model' and method 'onClick'");
    target.ll_my_log_model = finder.castView(view, 2131689661, "field 'll_my_log_model'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689660, "field 'tv_my_log_size'");
    target.tv_my_log_size = finder.castView(view, 2131689660, "field 'tv_my_log_size'");
    view = finder.findRequiredView(source, 2131689655, "field 'tv_title_center'");
    target.tv_title_center = finder.castView(view, 2131689655, "field 'tv_title_center'");
    view = finder.findRequiredView(source, 2131689662, "field 'tv_my_log_model'");
    target.tv_my_log_model = finder.castView(view, 2131689662, "field 'tv_my_log_model'");
    view = finder.findRequiredView(source, 2131689663, "field 'mTabLineIv'");
    target.mTabLineIv = finder.castView(view, 2131689663, "field 'mTabLineIv'");
    view = finder.findRequiredView(source, 2131689657, "field 'mPageVp'");
    target.mPageVp = finder.castView(view, 2131689657, "field 'mPageVp'");
  }

  @Override public void unbind(T target) {
    target.ll_my_log_size = null;
    target.ll_my_log_model = null;
    target.tv_my_log_size = null;
    target.tv_title_center = null;
    target.tv_my_log_model = null;
    target.mTabLineIv = null;
    target.mPageVp = null;
  }
}
