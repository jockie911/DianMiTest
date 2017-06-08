// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module.measure;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MeasureWeightAndHeightActivity$$ViewBinder<T extends com.example.objLoader.module.measure.MeasureWeightAndHeightActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689689, "field 'pickerHeight'");
    target.pickerHeight = finder.castView(view, 2131689689, "field 'pickerHeight'");
    view = finder.findRequiredView(source, 2131689690, "field 'pickerWeight'");
    target.pickerWeight = finder.castView(view, 2131689690, "field 'pickerWeight'");
    view = finder.findRequiredView(source, 2131689687, "field 'tvHeight'");
    target.tvHeight = finder.castView(view, 2131689687, "field 'tvHeight'");
    view = finder.findRequiredView(source, 2131689688, "field 'tvWeight'");
    target.tvWeight = finder.castView(view, 2131689688, "field 'tvWeight'");
    view = finder.findRequiredView(source, 2131689665, "method 'onClick'");
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
    target.tvHeight = null;
    target.tvWeight = null;
  }
}
