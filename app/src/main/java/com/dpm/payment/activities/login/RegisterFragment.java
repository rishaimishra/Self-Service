package com.dpm.payment.activities.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.adapters.TimeAdapter;
import com.dpm.payment.interfaces.OnItemClickListener;
import com.dpm.payment.models.cep.TimeModel;
import com.hbb20.CountryCodePicker;
import com.payment.R;

import java.util.ArrayList;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private AppCompatTextView tvRegister,tvLogin;
    private CountryCodePicker ccp;
    private Context mContext;
    public static RegisterFragment newInstance(){
        return new RegisterFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }


    private void initView(View view) {
        tvRegister = view.findViewById(R.id.btRegister);
        tvLogin = view.findViewById(R.id.tvLogin);
        ccp = view.findViewById(R.id.ccp);
        tvRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.btRegister:
            case R.id.tvLogin:
                ((ActivityLogin) requireActivity()).onBackPressed();
               break;
        }
    }
}
