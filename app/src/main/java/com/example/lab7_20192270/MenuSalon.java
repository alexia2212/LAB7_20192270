package com.example.lab7_20192270;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lab7_20192270.databinding.ActivityMenuSalonBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.google.firebase.auth.AuthKt;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.interning.qual.Interned;

import java.util.Arrays;

public class MenuSalon extends AppCompatActivity {

    ActivityMenuSalonBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuSalonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonDatos.setOnClickListener(view -> {
            Intent intent2 = new Intent(MenuSalon.this, DatosActivity.class);
            startActivity(intent2);
        });


    }

}
