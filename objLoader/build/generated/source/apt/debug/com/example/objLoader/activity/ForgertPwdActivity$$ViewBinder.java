// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ForgertPwdActivity$$ViewBinder<T extends com.example.objLoader.activity.ForgertPwdActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558543, "field 'et_forget_pwd_mobile'");
    target.et_forget_pwd_mobile = finder.castView(view, 2131558543, "field 'et_forget_pwd_mobile'");
    view = finder.findRequiredView(source, 2131558544, "field 'et_forget_pwd_auth_code'");
    target.et_forget_pwd_auth_code = finder.castView(view, 2131558544, "field 'et_forget_pwd_auth_code'");
    view = finder.findRequiredView(source, 2131558546, "field 'et_forget_pwd_password'");
    target.et_forget_pwd_password = finder.castView(view, 2131558546, "field 'et_forget_pwd_password'");
    view = finder.findRequiredView(source, 2131558547, "field 'btn_forget_pwd_confirm' and method 'onClick'");
    target.btn_forget_pwd_confirm = finder.castView(view, 2131558547, "field 'btn_forget_pwd_confirm'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558545, "field 'tv_forget_pwd_send_auth_code' and method 'onClick'");
    target.tv_forget_pwd_send_auth_code = finder.castView(view, 2131558545, "field 'tv_forget_pwd_send_auth_code'");
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
    target.btn_forget_pwd_confirm = null;
    target.tv_forget_pwd_send_auth_code = null;
  }
}
