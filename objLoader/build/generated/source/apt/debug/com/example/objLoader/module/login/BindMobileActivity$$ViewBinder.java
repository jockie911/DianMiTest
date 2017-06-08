// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module.login;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BindMobileActivity$$ViewBinder<T extends com.example.objLoader.module.login.BindMobileActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689646, "field 'et_mobile'");
    target.et_mobile = finder.castView(view, 2131689646, "field 'et_mobile'");
    view = finder.findRequiredView(source, 2131689647, "field 'et_auth_code'");
    target.et_auth_code = finder.castView(view, 2131689647, "field 'et_auth_code'");
    view = finder.findRequiredView(source, 2131689648, "field 'tv_send_auth_code' and method 'onClick'");
    target.tv_send_auth_code = finder.castView(view, 2131689648, "field 'tv_send_auth_code'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689649, "field 'tvRegister' and method 'onClick'");
    target.tvRegister = finder.castView(view, 2131689649, "field 'tvRegister'");
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
    target.tv_send_auth_code = null;
    target.tvRegister = null;
  }
}
