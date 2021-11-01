package com.ringstechnology.youcloudtask1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.ringstechnology.youcloudtask1.Adapter.AdapterPager;
import com.ringstechnology.youcloudtask1.Model.ItemSlider;
import com.ringstechnology.youcloudtask1.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    Handler slideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);

        List<ItemSlider> itemSliders = new ArrayList<>();

        itemSliders.add(new ItemSlider(R.drawable.image_a));
        itemSliders.add(new ItemSlider(R.drawable.image_b));
        itemSliders.add(new ItemSlider(R.drawable.image_c));

        viewPager.setAdapter(new AdapterPager(itemSliders, viewPager));

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40)) ;
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1
                        - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f) ;
            }
        });

        Runnable sliderRunnable = new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        };

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,5000);
            }
        });

        viewPager.setPageTransformer(compositePageTransformer);
    }
}