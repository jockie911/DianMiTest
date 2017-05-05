// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity.mywork;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.objLoader.activity.mywork.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558570, "field 'rel_person_center'");
    target.rel_person_center = finder.castView(view, 2131558570, "field 'rel_person_center'");
    view = finder.findRequiredView(source, 2131558569, "field 'tvStartMeasure' and method 'onClick'");
    target.tvStartMeasure = finder.castView(view, 2131558569, "field 'tvStartMeasure'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558568, "field 'discreteScrollView'");
    target.discreteScrollView = finder.castView(view, 2131558568, "field 'discreteScrollView'");
  }

  @Override public void unbind(T target) {
    target.rel_person_center = null;
    target.tvStartMeasure = null;
    target.discreteScrollView = null;
  }
}
