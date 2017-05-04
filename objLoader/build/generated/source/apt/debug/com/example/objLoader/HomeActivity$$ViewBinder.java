// Generated code from Butter Knife. Do not modify!
package com.example.objLoader;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeActivity$$ViewBinder<T extends com.example.objLoader.HomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427451, "field 'rb_celiang' and method 'onClick'");
    target.rb_celiang = finder.castView(view, 2131427451, "field 'rb_celiang'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427452, "field 'rb_chicun' and method 'onClick'");
    target.rb_chicun = finder.castView(view, 2131427452, "field 'rb_chicun'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427453, "field 'rb_youxi' and method 'onClick'");
    target.rb_youxi = finder.castView(view, 2131427453, "field 'rb_youxi'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427454, "field 'rb_dingzhi' and method 'onClick'");
    target.rb_dingzhi = finder.castView(view, 2131427454, "field 'rb_dingzhi'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427455, "field 'rb_shop' and method 'onClick'");
    target.rb_shop = finder.castView(view, 2131427455, "field 'rb_shop'");
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
    target.rb_celiang = null;
    target.rb_chicun = null;
    target.rb_youxi = null;
    target.rb_dingzhi = null;
    target.rb_shop = null;
  }
}
