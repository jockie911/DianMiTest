// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MeasureActivity$$ViewBinder<T extends com.example.objLoader.activity.MeasureActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427471, "field 'iv_measure_right' and method 'onClick'");
    target.iv_measure_right = finder.castView(view, 2131427471, "field 'iv_measure_right'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427473, "field 'et_height' and method 'onClick'");
    target.et_height = finder.castView(view, 2131427473, "field 'et_height'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427475, "field 'et_weight' and method 'onClick'");
    target.et_weight = finder.castView(view, 2131427475, "field 'et_weight'");
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
    target.iv_measure_right = null;
    target.et_height = null;
    target.et_weight = null;
  }
}
