package com.sanskruti.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DietPlanDetailActivity extends AppCompatActivity {
    private TextView tvPlanName;
    private TextView tvCalories;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_detail);

        // Initialize TextViews
        tvPlanName = findViewById(R.id.idTVPlanName);
        tvCalories = findViewById(R.id.idCaloriesTV);
        tvDescription = findViewById(R.id.idDescriptionTV);

        // Retrieve data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String planName = extras.getString("planName");
            String description = extras.getString("description");
            int calories = extras.getInt("calories");

            // Set the data to TextViews
            tvPlanName.setText(planName);
            tvCalories.setText("Calories: " + calories);
            tvDescription.setText(description);
        }
    }
}
