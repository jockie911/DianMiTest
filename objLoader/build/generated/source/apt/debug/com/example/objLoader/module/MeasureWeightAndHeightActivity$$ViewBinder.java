// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;

import com.example.objLoader.module.measure.MeasureWeightAndHeightActivity;

import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MeasureWeightAndHeightActivity$$ViewBinder<T extends MeasureWeightAndHeightActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689649, "field 'pickerHeight'");
    target.pickerHeight = finder.castView(view, 2131689649, "field 'pickerHeight'");
    view = finder.findRequiredView(source, 2131689650, "field 'pickerWeight'");
    target.pickerWeight = finder.castView(view, 2131689650, "field 'pickerWeight'");
    view = finder.findRequiredView(source, 2131689627, "method 'onClick'");
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
    target.pickerHeight = null;
    target.pickerWeight = null;
  }
}
