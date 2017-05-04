// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.example.objLoader.activity.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558584, "field 'et_mobile'");
    target.et_mobile = finder.castView(view, 2131558584, "field 'et_mobile'");
    view = finder.findRequiredView(source, 2131558585, "field 'et_auth_code'");
    target.et_auth_code = finder.castView(view, 2131558585, "field 'et_auth_code'");
    view = finder.findRequiredView(source, 2131558587, "field 'et_register_password'");
    target.et_register_password = finder.castView(view, 2131558587, "field 'et_register_password'");
    view = finder.findRequiredView(source, 2131558588, "field 'et_confirm_password'");
    target.et_confirm_password = finder.castView(view, 2131558588, "field 'et_confirm_password'");
    view = finder.findRequiredView(source, 2131558589, "field 'btn_register' and method 'onClick'");
    target.btn_register = finder.castView(view, 2131558589, "field 'btn_register'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558586, "field 'tv_send_auth_code' and method 'onClick'");
    target.tv_send_auth_code = finder.castView(view, 2131558586, "field 'tv_send_auth_code'");
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
    target.et_mobile = null;
    target.et_auth_code = null;
    target.et_register_password = null;
    target.et_confirm_password = null;
    target.btn_register = null;
    target.tv_send_auth_code = null;
  }
}
