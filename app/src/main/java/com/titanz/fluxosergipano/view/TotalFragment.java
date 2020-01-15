package com.titanz.fluxosergipano.view;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.MenuAdapter;


public class TotalFragment extends Fragment {

    private TabLayout tabLayoutTotal;
    private ViewPager viewPagerTotal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_total,container,false);

        tabLayoutTotal = view.findViewById(R.id.tablayout_total_id);
        viewPagerTotal = view.findViewById(R.id.viewpager_total_id);

        MenuAdapter adapter = new MenuAdapter(getChildFragmentManager());
        adapter.AddFragment(new Total_Final_Fragment(),"Balanço" );
        adapter.AddFragment(new Total_Entrada_Fragment(),"Entrada" );
        adapter.AddFragment(new Total_Saida_Fragment(),"Saída" );

        viewPagerTotal.setAdapter(adapter);
        tabLayoutTotal.setupWithViewPager(viewPagerTotal);



        return view;

    }
}
