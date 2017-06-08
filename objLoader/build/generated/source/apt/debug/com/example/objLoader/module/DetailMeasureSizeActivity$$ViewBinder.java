// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailMeasureSizeActivity$$ViewBinder<T extends com.example.objLoader.module.DetailMeasureSizeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689651, "field 'tableLayout'");
    target.tableLayout = finder.castView(view, 2131689651, "field 'tableLayout'");
    view = finder.findRequiredView(source, 2131689652, "field 'vpLove'");
    target.vpLove = finder.castView(view, 2131689652, "field 'vpLove'");
    view = finder.findRequiredView(source, 2131689653, "field 'tvSaveRecord' and method 'onClick'");
    target.tvSaveRecord = finder.castView(view, 2131689653, "field 'tvSaveRecord'");
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
