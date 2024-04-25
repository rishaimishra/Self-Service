package com.dpm.payment.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.dpm.payment.activities.cep.ActivityCep;
import com.dpm.payment.activities.user.ActivityUserLogin;
import com.payment.R;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private AppCompatTextView btLogin,tvLogin;
    private Context mContext;
    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }


    private void initView(View view) {
        btLogin = view.findViewById(R.id.btLogin);
        tvLogin = view.findViewById(R.id.tvLogin);
        btLogin.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.btLogin:
               startUserLogin();
               break;
            case R.id.tvLogin:
                ((ActivityLogin) requireActivity()).startFragment(RegisterFragment.newInstance());
                break;
        }
    }

    private void startUserLogin(){
        Intent mIntent = new Intent(mContext, ActivityUserLogin.class);
        startActivity(mIntent);
        ((ActivityLogin) requireActivity()).finish();
    }
}
