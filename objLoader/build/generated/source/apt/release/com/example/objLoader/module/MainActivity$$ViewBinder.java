// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.objLoader.module.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689647, "field 'rel_person_center'");
    target.rel_person_center = finder.castView(view, 2131689647, "field 'rel_person_center'");
    view = finder.findRequiredView(source, 2131689646, "field 'tvStartMeasure'");
    target.tvStartMeasure = finder.castView(view, 2131689646, "field 'tvStartMeasure'");
    view = finder.findRequiredView(source, 2131689644, "field 'discreteScrollView'");
    target.discreteScrollView = finder.castView(view, 2131689644, "field 'discreteScrollView'");
    view = finder.findRequiredView(source, 2131689645, "field 'relStartmeasure' and method 'onClick'");
    target.relStartmeasure = finder.castView(view, 2131689645, "field 'relStartmeasure'");
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
    target.rel_person_center = null;
    target.tvStartMeasure = null;
    target.discreteScrollView = null;
    target.relStartmeasure = null;
  }
}
