// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;

import com.example.objLoader.module.personInfo.ChangePwdActivity;

import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChangePwdActivity$$ViewBinder<T extends ChangePwdActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689605, "field 'et_old_pwd'");
    target.et_old_pwd = finder.castView(view, 2131689605, "field 'et_old_pwd'");
    view = finder.findRequiredView(source, 2131689608, "field 'et_new_pwd'");
    target.et_new_pwd = finder.castView(view, 2131689608, "field 'et_new_pwd'");
    view = finder.findRequiredView(source, 2131689606, "field 'ivEyeOldPwd' and method 'onClick'");
    target.ivEyeOldPwd = finder.castView(view, 2131689606, "field 'ivEyeOldPwd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689609, "field 'ivEyeNewPwd' and method 'onClick'");
    target.ivEyeNewPwd = finder.castView(view, 2131689609, "field 'ivEyeNewPwd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689684, "method 'onClick'");
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
    target.et_old_pwd = null;
    target.et_new_pwd = null;
    target.ivEyeOldPwd = null;
    target.ivEyeNewPwd = null;
  }
}
