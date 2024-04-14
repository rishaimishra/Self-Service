package com.dpm.payment.activities.cep.emergencyservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dpm.payment.activities.cep.ActivityCep;
import com.dpm.payment.adapters.EmergencyServiceAdapter;
import com.payment.R;
import java.util.ArrayList;

public class EmergencyServicesFragment extends Fragment {
    private RecyclerView rvCep;
    private Context mContext;
    private ArrayList<Integer> emergencyServiceList;

    public static EmergencyServicesFragment newInstance() {
        return new EmergencyServicesFragment();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emergency_service, container, false);
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
        tvTitle.setText("National Emergency Medical Service");
        ivHome.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }


    private void initView(View view) {
        rvCep = view.findViewById(R.id.rvCep);
        setData();
    }

    private void setData() {
        emergencyServiceList = new ArrayList<>();
        emergencyServiceList.add(R.drawable.ic_police);
        emergencyServiceList.add(R.drawable.ic_fire_force);
        emergencyServiceList.add(R.drawable.ic_nems);
        setAdapter();
    }

    private void setAdapter(){
        EmergencyServiceAdapter adapter = new EmergencyServiceAdapter(mContext, emergencyServiceList, (view, position) -> {
            switch (position){
                case 0: ((ActivityCep)requireActivity()).startFragment(PoliceFragment.newInstance());
                    break;
                case 1: ((ActivityCep)requireActivity()).startFragment(FireForceFragment.newInstance());
                    break;
                case 2: ((ActivityCep)requireActivity()).startFragment(NemsFragment.newInstance());
                    break;
            }
        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(requireActivity());
        rvCep.setLayoutManager(layoutManager);
        rvCep.setAdapter(adapter);
    }

}
