package com.example.objLoader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.objLoader.R;

/**
 * Created by yc on 2017/4/27.
 */

public class DiscreteScrollViewAdapter extends RecyclerView.Adapter<DiscreteScrollViewAdapter.ViewHolder>{

    private int i;

    public DiscreteScrollViewAdapter(int i) {
        this.i = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_discrete_scrollview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemiv.getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i;
        holder.itemiv.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1 = holder.itemRelBottom.getLayoutParams();
        layoutParams1.width = i;
        holder.itemRelBottom.setLayoutParams(layoutParams1);

        holder.itemiv.setBackgroundResource(position == 0 ? R.drawable.woman_top : R.drawable.man_top);
        holder.itemRelBottom.setBackgroundResource(position == 0 ? R.drawable.woman_bottom : R.drawable.man_bottom);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView itemiv;
        private final RelativeLayout itemRelBottom;

        public ViewHolder(View itemView) {
            super(itemView);
            itemiv = (ImageView) itemView.findViewById(R.id.item_iv_top);
            itemRelBottom = (RelativeLayout) itemView.findViewById(R.id.item_rel_bottom);
        }
    }
}
