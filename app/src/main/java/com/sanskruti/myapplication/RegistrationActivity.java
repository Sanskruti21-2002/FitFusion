package com.sanskruti.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private EditText name, contact,dob,pass,email,dobEditText;
    private Button insert;
    Calendar calendar;
    DatabaseHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.usernameEditText);
        contact = findViewById(R.id.contactEditText);
        pass=findViewById(R.id.passwordEditText);
        email=findViewById(R.id.emailEditText);
        insert = findViewById(R.id.registerButton);
        dobEditText = findViewById(R.id.dobEditText);

        DB = new DatabaseHelper(this);

        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        calendar = Calendar.getInstance();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                //String dobTXT = dob.getText().toString();
                String passTXT=pass.getText().toString();
                String emailTXT=email.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dobTXT = sdf.format(calendar.getTime());

                if (isValidPhoneNumber(contactTXT)) {
                    Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT,passTXT,emailTXT);

                    if (checkinsertdata) {
                        Toast.makeText(RegistrationActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Account Not Created", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrationActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidPhoneNumber(String contactTXT) {
        // Define a regular expression for a 10-digit numeric phone number
        String regex = "^[0-9]{10}$";
        return Pattern.matches(regex, contactTXT);
    }
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        dobEditText.setText(sdf.format(calendar.getTime()));
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
}
