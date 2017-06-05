// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module.measure;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FrontPicActivity$$ViewBinder<T extends com.example.objLoader.module.measure.FrontPicActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689656, "field 'ivTarget'");
    target.ivTarget = finder.castView(view, 2131689656, "field 'ivTarget'");
    view = finder.findRequiredView(source, 2131689719, "field 'tvRightTitleBar' and method 'onClick'");
    target.tvRightTitleBar = finder.castView(view, 2131689719, "field 'tvRightTitleBar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689657, "field 'actionMenu'");
    target.actionMenu = finder.castView(view, 2131689657, "field 'actionMenu'");
  }

  @Override public void unbind(T target) {
    target.ivTarget = null;
    target.tvRightTitleBar = null;
    target.actionMenu = null;
  }
}
