// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PhotoCommandFragment$$ViewBinder<T extends com.example.objLoader.fragment.PhotoCommandFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689722, "field 'mCameraContainer'");
    target.mCameraContainer = finder.castView(view, 2131689722, "field 'mCameraContainer'");
    view = finder.findRequiredView(source, 2131689723, "field 'mFlashLight' and method 'onClick'");
    target.mFlashLight = finder.castView(view, 2131689723, "field 'mFlashLight'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689725, "field 'mTakePhoto' and method 'onClick'");
    target.mTakePhoto = finder.castView(view, 2131689725, "field 'mTakePhoto'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689724, "method 'onClick'");
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
    target.mCameraContainer = null;
    target.mFlashLight = null;
    target.mTakePhoto = null;
  }
}
