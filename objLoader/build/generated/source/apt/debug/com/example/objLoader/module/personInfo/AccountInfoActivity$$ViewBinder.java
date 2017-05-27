// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module.personInfo;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AccountInfoActivity$$ViewBinder<T extends com.example.objLoader.module.personInfo.AccountInfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689601, "field 'rl_change_pwd' and method 'onClick'");
    target.rl_change_pwd = finder.castView(view, 2131689601, "field 'rl_change_pwd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689598, "field 'rl_change_username' and method 'onClick'");
    target.rl_change_username = finder.castView(view, 2131689598, "field 'rl_change_username'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689597, "field 'tv_phone_number'");
    target.tv_phone_number = finder.castView(view, 2131689597, "field 'tv_phone_number'");
    view = finder.findRequiredView(source, 2131689600, "field 'tv_username'");
    target.tv_username = finder.castView(view, 2131689600, "field 'tv_username'");
    view = finder.findRequiredView(source, 2131689596, "field 'ivAvatar' and method 'onClick'");
    target.ivAvatar = finder.castView(view, 2131689596, "field 'ivAvatar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689685, "field 'ivRightTitleBar' and method 'onClick'");
    target.ivRightTitleBar = finder.castView(view, 2131689685, "field 'ivRightTitleBar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689602, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689604, "method 'onClick'");
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
    target.rl_change_pwd = null;
    target.rl_change_username = null;
    target.tv_phone_number = null;
    target.tv_username = null;
    target.ivAvatar = null;
    target.ivRightTitleBar = null;
  }
}
