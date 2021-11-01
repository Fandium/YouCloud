package com.ringstechnology.youcloudtask1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ringstechnology.youcloudtask1.Model.ItemSlider;
import com.ringstechnology.youcloudtask1.R;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


public class AdapterPager extends RecyclerView.Adapter<AdapterPager.PagerViewHolder> {
    private List<ItemSlider> itemSliders;
    public ViewPager2 viewPager;

    public AdapterPager(List<ItemSlider> itemSliders, ViewPager2 viewPager){
        this.itemSliders = itemSliders;
        this.viewPager = viewPager;

    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_item, parent, false);
        PagerViewHolder pvh = new PagerViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPager.PagerViewHolder holder, int position) {
        holder.setImage(itemSliders.get(position));
    }


    @Override
    public int getItemCount() {
        return itemSliders.size();
    }

    public static class PagerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        PagerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);

        }

        void setImage(ItemSlider itemSlider){
            image.setImageResource(itemSlider.getImage());
        }
    }
}
