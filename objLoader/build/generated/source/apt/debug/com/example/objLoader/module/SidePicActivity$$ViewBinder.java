// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.module;

import android.view.View;

import com.example.objLoader.module.measure.SidePicActivity;

import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SidePicActivity$$ViewBinder<T extends SidePicActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689676, "field 'iv_side'");
    target.iv_side = finder.castView(view, 2131689676, "field 'iv_side'");
    view = finder.findRequiredView(source, 2131689677, "field 'btn_side_camera' and method 'onClick'");
    target.btn_side_camera = finder.castView(view, 2131689677, "field 'btn_side_camera'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689678, "field 'btn_side_album' and method 'onClick'");
    target.btn_side_album = finder.castView(view, 2131689678, "field 'btn_side_album'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689646, "field 'tv_start_measure' and method 'onClick'");
    target.tv_start_measure = finder.castView(view, 2131689646, "field 'tv_start_measure'");
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
    target.iv_side = null;
    target.btn_side_camera = null;
    target.btn_side_album = null;
    target.tv_start_measure = null;
  }
}
