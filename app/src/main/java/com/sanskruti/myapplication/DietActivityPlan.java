package com.sanskruti.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;


public class DietActivityPlan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DietRVAdapter adapter;
    private List<DietRVModal> dietPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan2);

        recyclerView = findViewById(R.id.rvMealPlan);
        dietPlans = new ArrayList<>(); // Initialize with your diet plan data

        // Add diet plans to the list
        dietPlans.add(new DietRVModal("1. The Mediterranean diet",getResources().getString(R.string.mediterian_plan) , 1500));
        dietPlans.add(new DietRVModal("2. The DASH diet", getResources().getString(R.string.dash_plan), 2000));
        dietPlans.add(new DietRVModal("3. Plant-based and flexitarian diets",getResources().getString(R.string.plant_plan),3000));
        dietPlans.add(new DietRVModal("4. The MIND diet",getResources().getString(R.string.mind_plan),3000));
        dietPlans.add(new DietRVModal("6. Intermittent fasting",getResources().getString(R.string.inter_plan),3000));
        dietPlans.add(new DietRVModal("7. The Volumetrics diet",getResources().getString(R.string.vol_plan),3000));

        // Add more diet plans as needed

        adapter = new DietRVAdapter(dietPlans, item -> {
            // Handle item click: navigate to the DietPlanDetailActivity
            Intent intent = new Intent(this, DietPlanDetailActivity.class);
            intent.putExtra("planName", item.getPlanName());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("calories", item.getCalories());
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
