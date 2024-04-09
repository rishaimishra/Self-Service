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

import com.dpm.payment.adapters.CEPAdapter;
import com.dpm.payment.adapters.ComplaintsAdapter;
import com.dpm.payment.interfaces.OnItemClickListener;
import com.dpm.payment.models.cep.CepModel;
import com.dpm.payment.models.cep.ComplaintsModel;
import com.payment.R;

import java.util.ArrayList;

public class ComplaintsFragment extends Fragment {
    private RecyclerView rvCep;
    private ArrayList<ComplaintsModel> complaintsList;

    public static ComplaintsFragment newInstance(){
        return new ComplaintsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complaints,container,false);
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
        tvTitle.setText("Complaints");
        ivHome.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

    private void initView(View view) {
        rvCep = view.findViewById(R.id.rvCep);
        setData();
    }
    private void setData(){
        complaintsList = new ArrayList<>();
        complaintsList.add(new ComplaintsModel("Demand Note",R.drawable.ic_complaints));
        complaintsList.add(new ComplaintsModel("Electricity",R.drawable.ic_forms_resources));
        complaintsList.add(new ComplaintsModel("Water",R.drawable.ic_forms_resources));
        complaintsList.add(new ComplaintsModel("Motorable Access Road",R.drawable.ic_information));
        complaintsList.add(new ComplaintsModel("Damaged/Flooding Roads",R.drawable.ic_information));
        complaintsList.add(new ComplaintsModel("Drainage",R.drawable.ic_places));
        complaintsList.add(new ComplaintsModel("Waste Management",R.drawable.ic_disaster_management));
        complaintsList.add(new ComplaintsModel("Garbage Dumping",R.drawable.ic_newsletter));
        complaintsList.add(new ComplaintsModel("Market",R.drawable.ic_blog));
        complaintsList.add(new ComplaintsModel("Sand Mining",R.drawable.ic_reporting));
        complaintsList.add(new ComplaintsModel("Logging",R.drawable.ic_reporting));
        setAdapter();
    }

    private void setAdapter(){
        ComplaintsAdapter adapter=new ComplaintsAdapter(requireActivity(), complaintsList, (view, position) -> {


        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(requireActivity());
        rvCep.setLayoutManager(layoutManager);
        rvCep.setAdapter(adapter);
    }
}
