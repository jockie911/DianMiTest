// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ForgertPwdActivity$$ViewBinder<T extends com.example.objLoader.module.ForgertPwdActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689616, "field 'et_forget_pwd_mobile'");
    target.et_forget_pwd_mobile = finder.castView(view, 2131689616, "field 'et_forget_pwd_mobile'");
    view = finder.findRequiredView(source, 2131689617, "field 'et_forget_pwd_auth_code'");
    target.et_forget_pwd_auth_code = finder.castView(view, 2131689617, "field 'et_forget_pwd_auth_code'");
    view = finder.findRequiredView(source, 2131689619, "field 'et_forget_pwd_password'");
    target.et_forget_pwd_password = finder.castView(view, 2131689619, "field 'et_forget_pwd_password'");
    view = finder.findRequiredView(source, 2131689618, "field 'tv_forget_pwd_send_auth_code' and method 'onClick'");
    target.tv_forget_pwd_send_auth_code = finder.castView(view, 2131689618, "field 'tv_forget_pwd_send_auth_code'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689620, "method 'onClick'");
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
    target.et_forget_pwd_mobile = null;
    target.et_forget_pwd_auth_code = null;
    target.et_forget_pwd_password = null;
    target.tv_forget_pwd_send_auth_code = null;
  }
}
