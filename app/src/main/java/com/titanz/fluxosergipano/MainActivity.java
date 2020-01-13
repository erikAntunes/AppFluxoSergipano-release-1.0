package com.titanz.fluxosergipano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.titanz.fluxosergipano.adapters.MenuAdapter;
import com.titanz.fluxosergipano.view.EntradaFragment;
import com.titanz.fluxosergipano.view.SaidaFragment;
import com.titanz.fluxosergipano.view.TotalFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tablayout_main_id);
        viewPager = findViewById(R.id.viewpager_main_id);


        //Adicionando Fragments

        MenuAdapter adapter = new MenuAdapter(getSupportFragmentManager());
        adapter.AddFragment(new EntradaFragment(),"Entrada" );
        adapter.AddFragment(new SaidaFragment(),"Saída" );
        adapter.AddFragment(new TotalFragment(),"Total" );

        // Adapter Setup

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
