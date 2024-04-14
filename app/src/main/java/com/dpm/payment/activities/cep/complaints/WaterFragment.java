package com.dpm.payment.activities.cep.complaints;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesgood.views.JustifiedTextView;
import com.dpm.payment.adapters.CEPImagesAdapter;
import com.dpm.payment.adapters.RadioButtonAdapter;
import com.payment.R;

import java.util.ArrayList;

public class WaterFragment extends Fragment {
    private RecyclerView rvWater;
    private ArrayList<String> waterList;
    private RecyclerView rvImages;
    private JustifiedTextView tvInfo;
    public static WaterFragment newInstance(){
        return new WaterFragment();
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
        tvTitle.setText("Water");
        ivHome.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

    private void initView(View view) {
        rvWater = view.findViewById(R.id.rvDemandNote);
        rvImages = view.findViewById(R.id.rvImages);
        tvInfo = view.findViewById(R.id.tvInfo);
        tvInfo.setText(getString(R.string.water_info));
        setData();
    }
    private void setData() {
        waterList = new ArrayList<>();
        waterList.add("No pipe bourne water in my Ward");
        waterList.add("No pipe bourne water in my Area/Section");
        waterList.add("Water Hazard/Waste");
        setAdapter();
        setImagesAdapter();
    }

    private void setAdapter(){
        RadioButtonAdapter adapter=new RadioButtonAdapter(requireActivity(), waterList, (view, position) -> {


        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(requireActivity());
        rvWater.setLayoutManager(layoutManager);
        rvWater.setAdapter(adapter);
    }

    private void setImagesAdapter(){
        CEPImagesAdapter adapter=new CEPImagesAdapter(requireActivity(), (view, position) -> {

        });
        GridLayoutManager layoutManager=new GridLayoutManager(requireActivity(),2);
        rvImages.setLayoutManager(layoutManager);
        rvImages.setAdapter(adapter);
    }
}
