// Generated code from Butter Knife. Do not modify!
package com.example.objLoader.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailClothesModelFragment$$ViewBinder<T extends com.example.objLoader.fragment.DetailClothesModelFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624163, "field 'ivTopClothes'");
    target.ivTopClothes = finder.castView(view, 2131624163, "field 'ivTopClothes'");
    view = finder.findRequiredView(source, 2131624164, "field 'tvTopMeasureSize'");
    target.tvTopMeasureSize = finder.castView(view, 2131624164, "field 'tvTopMeasureSize'");
    view = finder.findRequiredView(source, 2131624165, "field 'ivBottomClothes'");
    target.ivBottomClothes = finder.castView(view, 2131624165, "field 'ivBottomClothes'");
    view = finder.findRequiredView(source, 2131624166, "field 'tvBottomMeasureSize'");
    target.tvBottomMeasureSize = finder.castView(view, 2131624166, "field 'tvBottomMeasureSize'");
    view = finder.findRequiredView(source, 2131624167, "field 'tvShape'");
    target.tvShape = finder.castView(view, 2131624167, "field 'tvShape'");
  }

  @Override public void unbind(T target) {
    target.ivTopClothes = null;
    target.tvTopMeasureSize = null;
    target.ivBottomClothes = null;
    target.tvBottomMeasureSize = null;
    target.tvShape = null;
  }
}