package com.dpm.payment.activities.cep;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.adapters.CEPAdapter;
import com.dpm.payment.models.cep.CepModel;
import com.payment.R;

import java.util.ArrayList;

public class ActivityCep extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep);
        initView();
    }

    private void initView(){
        startFragment(CEPMenuFragment.newInstance());
    }

    void startFragment(Fragment fragment, Boolean clearBackStack ) {
        doStartFragment( fragment, clearBackStack);
    }

    void startFragment( Fragment fragment) {
        doStartFragment( fragment, false);
    }

    private void doStartFragment(Fragment fragment, Boolean clearBackStack
    ) {
        if (clearBackStack) {
            clearBackStack();
            //showHideBack(false)
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );

        fragmentTransaction.replace(R.id.frmlayout, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());

        fragmentTransaction.commit();
    }

    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count > 1) {
            for (int i=0;i<count;i++) {
                manager.popBackStack();
            }
        }

    }

}
