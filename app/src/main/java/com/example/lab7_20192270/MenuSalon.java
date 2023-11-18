package com.example.lab7_20192270;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab7_20192270.databinding.ActivityMenuSalonBinding;

public class MenuSalon extends AppCompatActivity {

    ActivityMenuSalonBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuSalonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonDatos.setOnClickListener(view -> {
            Intent intent = new Intent(MenuSalon.this, DatosActivity.class);
            startActivity(intent);
        });
    }
}