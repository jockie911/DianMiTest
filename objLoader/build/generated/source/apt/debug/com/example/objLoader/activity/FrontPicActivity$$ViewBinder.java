// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FrontPicActivity$$ViewBinder<T extends com.example.objLoader.activity.FrontPicActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558549, "field 'iv_front'");
    target.iv_front = finder.castView(view, 2131558549, "field 'iv_front'");
    view = finder.findRequiredView(source, 2131558552, "field 'tv_next_step' and method 'onClick'");
    target.tv_next_step = finder.castView(view, 2131558552, "field 'tv_next_step'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558550, "field 'btn_front_camera' and method 'onClick'");
    target.btn_front_camera = finder.castView(view, 2131558550, "field 'btn_front_camera'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558551, "field 'btn_front_album' and method 'onClick'");
    target.btn_front_album = finder.castView(view, 2131558551, "field 'btn_front_album'");
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
    target.iv_front = null;
    target.tv_next_step = null;
    target.btn_front_camera = null;
    target.btn_front_album = null;
  }
}
