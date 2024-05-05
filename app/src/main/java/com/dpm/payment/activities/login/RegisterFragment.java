package com.dpm.payment.activities.login;

import static com.dpm.payment.utils.ConstantData.REQUEST_KEY_PASSWORD;
import static com.dpm.payment.utils.ConstantData.REQUEST_KEY_USERNAME;
import static com.dpm.payment.utils.ConstantData.TAG_REQUEST_LOGIN;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.dpm.payment.utils.RestApiRequestListener;
import com.dpm.payment.utils.RestApiUrl;
import com.hbb20.CountryCodePicker;
import com.payment.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

   /* private void validate(){
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(mContext,"Please enter username",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(mContext,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        reqRegister();
    }
    private String getUserName() {
        return etUserName.getText().toString().trim();
    }

    private String getPassword() {
        return etPassword.getText().toString().trim();
    }

    public void reqRegister() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        Map<String, String> req_params = new HashMap<>();
        req_params.put(REQUEST_KEY_USERNAME, getUserName());
        req_params.put(REQUEST_KEY_PASSWORD, getPassword());

        new RestApiRequestListener(requireActivity(), TAG_REQUEST_LOGIN, RestApiUrl.URL_GUEST_USER_REGISTER, headers, req_params, new RestApiRequestListener.setOnRequestListener() {
            @Override
            public void onPreExecute() {
                ((ActivityLogin) requireActivity()).showLoading(getString(R.string.loading_please_wait));
            }

            @Override
            public void onSuccessListener(String response) {
                ((ActivityLogin) requireActivity()).hideLoading();
                if(!TextUtils.isEmpty(response)) {
                    parseLogInResponse(response);
                }

            }

            @Override
            public void onErrorListener(String errorMessage) {
                ((ActivityLogin) requireActivity()).hideLoading();

            }
        }).request();
    }*/
}
