// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PhotoCommandFragment$$ViewBinder<T extends com.example.objLoader.fragment.PhotoCommandFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624187, "field 'ivTakePhoto' and method 'onClick'");
    target.ivTakePhoto = finder.castView(view, 2131624187, "field 'ivTakePhoto'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624185, "field 'mCameraContainer'");
    target.mCameraContainer = finder.castView(view, 2131624185, "field 'mCameraContainer'");
    view = finder.findRequiredView(source, 2131624186, "method 'onClick'");
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
    target.ivTakePhoto = null;
    target.mCameraContainer = null;
  }
}
