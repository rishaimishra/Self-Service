package com.dpm.payment.activities.cep;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.adapters.CEPAdapter;
import com.dpm.payment.models.cep.CepModel;
import com.payment.R;

import java.util.ArrayList;

public class ActivityCep extends AppCompatActivity {

    private RecyclerView rvCep;
    private ArrayList<CepModel> cepList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep);
        initView();
    }

    private void initView(){
        rvCep = findViewById(R.id.rvCep);
        setData();
    }

    private void setData(){
        cepList = new ArrayList<>();
        cepList.add(new CepModel("Complaints",R.drawable.ic_complaints));
        cepList.add(new CepModel("Forms/Resources",R.drawable.ic_forms_resources));
        cepList.add(new CepModel("Information",R.drawable.ic_information));
        cepList.add(new CepModel("Places",R.drawable.ic_places));
        cepList.add(new CepModel("Disaster Management",R.drawable.ic_disaster_management));
        cepList.add(new CepModel("Reporting",R.drawable.ic_reporting));
        cepList.add(new CepModel("Newsletter",R.drawable.ic_newsletter));
        cepList.add(new CepModel("Blog",R.drawable.ic_blog));
        setAdapter();
    }

    private void setAdapter(){
        CEPAdapter adapter=new CEPAdapter(this,cepList);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        rvCep.setLayoutManager(layoutManager);
        rvCep.setAdapter(adapter);
    }
}
