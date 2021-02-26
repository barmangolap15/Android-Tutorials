package com.codewithgolap.androidtutorial.ModernImageSlider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.View;
import com.codewithgolap.androidtutorial.R;
import java.util.ArrayList;
import java.util.List;

public class ModernImageSliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_image_slider);

        ViewPager2 locationViewPager = findViewById(R.id.locationViewPager);
        List<TravelLocation> travelLocations = new ArrayList<>();

        TravelLocation travelLocationET = new TravelLocation();
        travelLocationET.imageUrl = "https://images.unsplash.com/photo-1500297726361-1715d90aec00?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1047&q=80";
        travelLocationET.title = "China";
        travelLocationET.location = "Grate Wall of China";
        travelLocationET.startRating = 4.8f;
        travelLocations.add(travelLocationET);

        TravelLocation travelLocationMV = new TravelLocation();
        travelLocationMV.imageUrl = "https://images.unsplash.com/photo-1567930009485-07d60c813306?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80";
        travelLocationMV.title = "Mexico";
        travelLocationMV.location = "Chichén Itzá";
        travelLocationMV.startRating = 4.5f;
        travelLocations.add(travelLocationMV);

        TravelLocation travelLocationTM = new TravelLocation();
        travelLocationTM.imageUrl = "https://images.unsplash.com/photo-1589825274556-94746a018766?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80";
        travelLocationTM.title = "Jordan";
        travelLocationTM.location = "Petra";
        travelLocationTM.startRating = 4.7f;
        travelLocations.add(travelLocationTM);

        TravelLocation travelLocationF = new TravelLocation();
        travelLocationF.imageUrl = "https://images.unsplash.com/photo-1456244440184-1d494704a505?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80";
        travelLocationF.title = "Peru";
        travelLocationF.location = "Machu Picchu";
        travelLocationF.startRating = 4.6f;
        travelLocations.add(travelLocationF);

        TravelLocation travelLocationFi = new TravelLocation();
        travelLocationFi.imageUrl = "https://images.unsplash.com/photo-1595688878177-b72dfeeed683?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1151&q=80";
        travelLocationFi.title = "Brazil";
        travelLocationFi.location = "Christ the Redeemer";
        travelLocationFi.startRating = 4.6f;
        travelLocations.add(travelLocationFi);

        TravelLocation travelLocationS = new TravelLocation();
        travelLocationS.imageUrl = "https://images.unsplash.com/photo-1482401204742-eb3c31c24722?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1112&q=80";
        travelLocationS.title = "Italy";
        travelLocationS.location = "Colosseum";
        travelLocationS.startRating = 4.7f;
        travelLocations.add(travelLocationS);

        TravelLocation travelLocationSe = new TravelLocation();
        travelLocationSe.imageUrl = "https://images.unsplash.com/photo-1524492412937-b28074a5d7da?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1051&q=80";
        travelLocationSe.title = "India";
        travelLocationSe.location = "Taj Mahal";
        travelLocationSe.startRating = 4.8f;
        travelLocations.add(travelLocationSe);

        locationViewPager.setAdapter(new TravelLocationAdapter(travelLocations));

        locationViewPager.setClipToPadding(false);
        locationViewPager.setClipChildren(false);
        locationViewPager.setOffscreenPageLimit(3);
        locationViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.90f + r * 0.04f);
            }
        });
        locationViewPager.setPageTransformer(compositePageTransformer);
    }
}




