package com.dpm.payment.activities.cep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dpm.payment.activities.cep.complaints.ComplaintsFragment;
import com.dpm.payment.adapters.CEPAdapter;
import com.dpm.payment.models.cep.CepModel;
import com.payment.R;

import java.util.ArrayList;

public class CEPMenuFragment extends Fragment {
    private RecyclerView rvCep;
    private ArrayList<CepModel> cepList;

    public static CEPMenuFragment newInstance(){
        return new CEPMenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cep_menu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


    }

    private void initView(View view) {
        rvCep = view.findViewById(R.id.rvCep);
        setData();
    }
    private void setData(){
        cepList = new ArrayList<>();
        cepList.add(new CepModel("Complaints & Reporting",R.drawable.ic_complaints));
        cepList.add(new CepModel("Forms & Resources",R.drawable.ic_forms_resources));
        cepList.add(new CepModel("Schedule Appointment",R.drawable.ic_forms_resources));
        cepList.add(new CepModel("Information & Tips",R.drawable.ic_information));
        cepList.add(new CepModel("Garbage Collection",R.drawable.ic_information));
        cepList.add(new CepModel("Places",R.drawable.ic_places));
        cepList.add(new CepModel("Disaster Management",R.drawable.ic_disaster_management));
        cepList.add(new CepModel("Newsletter",R.drawable.ic_newsletter));
        cepList.add(new CepModel("Community Blog",R.drawable.ic_blog));
        cepList.add(new CepModel("Emergency Services",R.drawable.ic_reporting));
        setAdapter();
    }

    private void setAdapter(){
        CEPAdapter adapter=new CEPAdapter(requireActivity(), cepList, (view, position) -> {
           switch (position){
               case 0: ((ActivityCep)requireActivity()).startFragment(ComplaintsFragment.newInstance());
               break;
           }
        });
        GridLayoutManager layoutManager=new GridLayoutManager(requireActivity(),2);
        rvCep.setLayoutManager(layoutManager);
        rvCep.setAdapter(adapter);
    }
}
