package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.myapp.databinding.ActivityMainBinding;
import com.example.myapp.fragments.FragmentLodowka;
import com.example.myapp.fragments.FragmentPrzepisy;
import com.example.myapp.fragments.FragmentZakupy;
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FragmentZakupy());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_zakupy) {
                replaceFragment(new FragmentZakupy());
                return true;
            } else if (itemId == R.id.navigation_lodowka) {
                replaceFragment(new FragmentLodowka());
                return true;
            } else if (itemId == R.id.navigation_przepisy) {
                replaceFragment(new FragmentPrzepisy());
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId, fragment);
        fragmentTransaction.commit();
    }
}