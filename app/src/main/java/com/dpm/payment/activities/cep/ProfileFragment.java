package com.dpm.payment.activities.cep;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.payment.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfileFragment extends Fragment {
    private Context mContext;
    private AppCompatSpinner spnrDistrict;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initToolbar(view);
    }



    @SuppressLint("SetTextI18n")
    private void initToolbar(View view){
        AppCompatTextView tvTitle = view.findViewById(R.id.toolbar_tv_header);
        ImageView ivHome = view.findViewById(R.id.toolbar_iv_home);
        AppCompatImageView ivProfile = view.findViewById(R.id.ivProfile);
        AppCompatImageView ivNotification = view.findViewById(R.id.ivNotification);
        ivProfile.setVisibility(View.GONE);
        tvTitle.setText("My Profile");
        ivHome.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
        ivNotification.setOnClickListener(v -> {
            ((ActivityCep)requireActivity()).startFragment(NotificationFragment.newInstance());
        });
    }
    private void initView(View view) {
        spnrDistrict = view.findViewById(R.id.spnrDistrict);
        setSpinnerAdapter();
    }

    private void setSpinnerAdapter(){
        ArrayList<String> districtList= new ArrayList<>();
        districtList.add("Kailahun District Council");
        districtList.add("Kenema District Council");
        districtList.add("Kono District Council");
        districtList.add("Bombali District Council");
        districtList.add("Falaba District Council");
        districtList.add("Koinadugu District Council");
        districtList.add("Tonkolili District Council");
        districtList.add("Kambia District Council");
        districtList.add("Karene District Council");
        districtList.add("Port Loko District Council");
        districtList.add("Bo District Council");
        districtList.add("Bonthe District Council");
        districtList.add("Moyamba District Council");
        districtList.add("Pujehun District Council");
        districtList.add("Western Rural District Council");
        districtList.add("Western Area Urban District Council");
        districtList.add("Freetown City Council");
        districtList.add("Bo City Council");
        districtList.add("Bonthe Municipal Council");
        districtList.add("Kenema City Council");
        districtList.add("Port Loko City Council");
        districtList.add("Koidu New Sembehun City Council");
        districtList.add("Makeni City Council");
        ArrayAdapter aa = new ArrayAdapter(mContext,R.layout.adapter_profile_spinner,R.id.text,districtList);
        aa.setDropDownViewResource(R.layout.adapter_profile_spinner);
        spnrDistrict.setAdapter(aa);
    }

}
