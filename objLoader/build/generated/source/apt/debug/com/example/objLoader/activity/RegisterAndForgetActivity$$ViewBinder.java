// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterAndForgetActivity$$ViewBinder<T extends com.example.objLoader.activity.RegisterAndForgetActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689666, "field 'et_mobile'");
    target.et_mobile = finder.castView(view, 2131689666, "field 'et_mobile'");
    view = finder.findRequiredView(source, 2131689667, "field 'et_auth_code'");
    target.et_auth_code = finder.castView(view, 2131689667, "field 'et_auth_code'");
    view = finder.findRequiredView(source, 2131689669, "field 'et_register_password'");
    target.et_register_password = finder.castView(view, 2131689669, "field 'et_register_password'");
    view = finder.findRequiredView(source, 2131689670, "field 'et_confirm_password'");
    target.et_confirm_password = finder.castView(view, 2131689670, "field 'et_confirm_password'");
    view = finder.findRequiredView(source, 2131689668, "field 'tv_send_auth_code' and method 'onClick'");
    target.tv_send_auth_code = finder.castView(view, 2131689668, "field 'tv_send_auth_code'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689637, "field 'ivEyePwd' and method 'onClick'");
    target.ivEyePwd = finder.castView(view, 2131689637, "field 'ivEyePwd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689671, "field 'tvRegister' and method 'onClick'");
    target.tvRegister = finder.castView(view, 2131689671, "field 'tvRegister'");
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
    target.tv_send_auth_code = null;
    target.ivEyePwd = null;
    target.tvRegister = null;
  }
}
