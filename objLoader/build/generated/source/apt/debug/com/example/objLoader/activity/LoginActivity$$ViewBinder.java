// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.example.objLoader.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427456, "field 'et_username'");
    target.et_username = finder.castView(view, 2131427456, "field 'et_username'");
    view = finder.findRequiredView(source, 2131427457, "field 'et_password'");
    target.et_password = finder.castView(view, 2131427457, "field 'et_password'");
    view = finder.findRequiredView(source, 2131427459, "field 'tv_forget_pwd' and method 'onClick'");
    target.tv_forget_pwd = finder.castView(view, 2131427459, "field 'tv_forget_pwd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427458, "field 'ivEyePwd' and method 'onClick'");
    target.ivEyePwd = finder.castView(view, 2131427458, "field 'ivEyePwd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427460, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427504, "method 'onClick'");
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
    target.et_username = null;
    target.et_password = null;
    target.tv_forget_pwd = null;
    target.ivEyePwd = null;
  }
}