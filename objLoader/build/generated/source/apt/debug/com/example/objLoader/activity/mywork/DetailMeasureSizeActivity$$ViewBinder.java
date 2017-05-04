// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity.mywork;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailMeasureSizeActivity$$ViewBinder<T extends com.example.objLoader.activity.mywork.DetailMeasureSizeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558540, "field 'tableLayout'");
    target.tableLayout = finder.castView(view, 2131558540, "field 'tableLayout'");
    view = finder.findRequiredView(source, 2131558541, "field 'vpLove'");
    target.vpLove = finder.castView(view, 2131558541, "field 'vpLove'");
  }

  @Override public void unbind(T target) {
    target.tableLayout = null;
    target.vpLove = null;
  }
}
