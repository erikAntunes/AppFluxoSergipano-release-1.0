package com.titanz.fluxosergipano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.tabs.TabLayout;
import com.titanz.fluxosergipano.adapters.MenuAdapter;
import com.titanz.fluxosergipano.db.EntradaDatabase;
import com.titanz.fluxosergipano.db.SaidaDatabase;
import com.titanz.fluxosergipano.view.EntradaFragment;
import com.titanz.fluxosergipano.view.SaidaFragment;
import com.titanz.fluxosergipano.view.TotalFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    public static EntradaDatabase entradaDatabase;
    public static SaidaDatabase saidaDatabase;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        entradaDatabase = Room.databaseBuilder(getApplicationContext(),EntradaDatabase.class,"entradadb").allowMainThreadQueries().build();
        saidaDatabase = Room.databaseBuilder(getApplicationContext(), SaidaDatabase.class,"saidadb").allowMainThreadQueries().build();

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
        pDialog.setTitleText("Carregando");

        pDialog.setCancelable(true);

        pDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
            }
        },900);

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
