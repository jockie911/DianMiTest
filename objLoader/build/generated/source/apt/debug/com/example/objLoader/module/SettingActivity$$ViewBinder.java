// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;

import com.example.objLoader.module.setting.SettingActivity;

import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SettingActivity$$ViewBinder<T extends SettingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689674, "field 'tvClearCash'");
    target.tvClearCash = finder.castView(view, 2131689674, "field 'tvClearCash'");
    view = finder.findRequiredView(source, 2131689673, "method 'onClick'");
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
    target.tvClearCash = null;
  }
}
