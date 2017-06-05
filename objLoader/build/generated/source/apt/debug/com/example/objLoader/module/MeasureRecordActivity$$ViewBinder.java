// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MeasureRecordActivity$$ViewBinder<T extends com.example.objLoader.module.MeasureRecordActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689685, "field 'lvRecordMeasure'");
    target.lvRecordMeasure = finder.castView(view, 2131689685, "field 'lvRecordMeasure'");
    view = finder.findRequiredView(source, 2131689686, "field 'relBottom'");
    target.relBottom = finder.castView(view, 2131689686, "field 'relBottom'");
    view = finder.findRequiredView(source, 2131689719, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689687, "method 'onClick'");
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
    target.lvRecordMeasure = null;
    target.relBottom = null;
  }
}
