// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailMeasureSizeActivity$$ViewBinder<T extends com.example.objLoader.activity.DetailMeasureSizeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689613, "field 'tableLayout'");
    target.tableLayout = finder.castView(view, 2131689613, "field 'tableLayout'");
    view = finder.findRequiredView(source, 2131689614, "field 'vpLove'");
    target.vpLove = finder.castView(view, 2131689614, "field 'vpLove'");
    view = finder.findRequiredView(source, 2131689615, "field 'tvSaveRecord' and method 'onClick'");
    target.tvSaveRecord = finder.castView(view, 2131689615, "field 'tvSaveRecord'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.tableLayout = null;
    target.vpLove = null;
    target.tvSaveRecord = null;
  }
}
