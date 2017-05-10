// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SizeActivity$$ViewBinder<T extends com.example.objLoader.activity.SizeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624139, "field 'rb_mate' and method 'onClick'");
    target.rb_mate = finder.castView(view, 2131624139, "field 'rb_mate'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624141, "field 'rb_my_log' and method 'onClick'");
    target.rb_my_log = finder.castView(view, 2131624141, "field 'rb_my_log'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624140, "field 'rb_model' and method 'onClick'");
    target.rb_model = finder.castView(view, 2131624140, "field 'rb_model'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624138, "field 'rb_details_size' and method 'onClick'");
    target.rb_details_size = finder.castView(view, 2131624138, "field 'rb_details_size'");
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
    target.rb_mate = null;
    target.rb_my_log = null;
    target.rb_model = null;
    target.rb_details_size = null;
  }
}
