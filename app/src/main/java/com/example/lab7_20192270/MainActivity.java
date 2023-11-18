package com.example.lab7_20192270;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lab7_20192270.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MenuSalon.class);
            startActivity(intent);
        });
        /*
        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build()
                ))
                .setIsSmartLockEnabled(false)
                .build();

        signInLauncher.launch(intent);
*/
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
            Log.d("msg","uid: " + auth.getCurrentUser().getUid());
            Log.d("msg","name: " + auth.getCurrentUser().getDisplayName());
            Log.d("msg","email: " + auth.getCurrentUser().getEmail());

            auth.getCurrentUser().reload().addOnCompleteListener(task -> {
                if(auth.getCurrentUser().isEmailVerified()){
                    Log.d("msg","correo autenticado");
                } else{
                    auth.getCurrentUser().sendEmailVerification()
                            .addOnCompleteListener(task2 -> Log.d("msg", "correo enviado"));
                }
            });

        }else{
            Intent signedIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build()
                    ))
                    .setIsSmartLockEnabled(false)
                    .build();
        }

    }
    ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Log.d("msg-test", "Firebase uid: " + user.getUid());
                } else {
                    Log.d("msg-test", "Canceló el Log-in");
                }
            }
    );


}