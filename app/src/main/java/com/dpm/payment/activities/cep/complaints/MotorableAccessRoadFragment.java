package com.dpm.payment.activities.cep.complaints;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesgood.views.JustifiedTextView;
import com.dpm.payment.activities.cep.ActivityCep;
import com.dpm.payment.activities.cep.NotificationFragment;
import com.dpm.payment.activities.cep.ProfileFragment;
import com.dpm.payment.adapters.CEPImagesAdapter;
import com.dpm.payment.adapters.RadioButtonAdapter;
import com.payment.R;

import java.util.ArrayList;

public class MotorableAccessRoadFragment extends Fragment {
    private RecyclerView rvMotorableAccessRoad;
    private ArrayList<String> motorableAccessRoadList;
    private RecyclerView rvImages;
    private JustifiedTextView tvInfo;
    public static MotorableAccessRoadFragment newInstance(){
        return new MotorableAccessRoadFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demand_note,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(view);
        initView(view);
    }

    @SuppressLint("SetTextI18n")
    private void initToolbar(View view){
        AppCompatTextView tvTitle = view.findViewById(R.id.toolbar_tv_header);
        ImageView ivHome = view.findViewById(R.id.toolbar_iv_home);
        tvTitle.setText("Motorable Access Road");
        ivHome.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
        AppCompatImageView ivProfile = view.findViewById(R.id.ivProfile);
        AppCompatImageView ivNotification = view.findViewById(R.id.ivNotification);
        ivProfile.setOnClickListener(v -> {
            ((ActivityCep)requireActivity()).startFragment(ProfileFragment.newInstance());
        });
        ivNotification.setOnClickListener(v -> {
            ((ActivityCep)requireActivity()).startFragment(NotificationFragment.newInstance());
        });
    }

    private void initView(View view) {
        rvMotorableAccessRoad = view.findViewById(R.id.rvDemandNote);
        rvImages = view.findViewById(R.id.rvImages);
        tvInfo = view.findViewById(R.id.tvInfo);
        tvInfo.setText(getString(R.string.motorable_access_road_info));
        setData();
    }
    private void setData() {
        motorableAccessRoadList = new ArrayList<>();
        motorableAccessRoadList.add("No Motorable Access Road to my house");
        motorableAccessRoadList.add("No Motorable Access Road in my Area/Section");
        motorableAccessRoadList.add("Access Road Hazard");
        setAdapter();
        setImagesAdapter();
    }

    private void setAdapter(){
        RadioButtonAdapter adapter=new RadioButtonAdapter(requireActivity(), motorableAccessRoadList, (view, position) -> {


        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(requireActivity());
        rvMotorableAccessRoad.setLayoutManager(layoutManager);
        rvMotorableAccessRoad.setAdapter(adapter);
    }

    private void setImagesAdapter(){
        CEPImagesAdapter adapter=new CEPImagesAdapter(requireActivity(), (view, position) -> {

        });
        GridLayoutManager layoutManager=new GridLayoutManager(requireActivity(),2);
        rvImages.setLayoutManager(layoutManager);
        rvImages.setAdapter(adapter);
    }
}
