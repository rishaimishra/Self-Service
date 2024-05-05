package com.dpm.payment.activities.login;

import static com.dpm.payment.utils.ConstantData.REQUEST_KEY_PASSWORD;
import static com.dpm.payment.utils.ConstantData.REQUEST_KEY_USERNAME;
import static com.dpm.payment.utils.ConstantData.TAG_REQUEST_LOGIN;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import com.dpm.payment.activities.user.ActivityUserLogin;
import com.dpm.payment.models.cep.GuestUserResponse;
import com.dpm.payment.utils.LogUtils;
import com.dpm.payment.utils.PrefUtil;
import com.dpm.payment.utils.RestApiRequestListener;
import com.dpm.payment.utils.RestApiUrl;
import com.google.gson.Gson;
import com.payment.R;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private TextView btLogin,tvLogin,tvForgotPassword;
    private EditText etUserName,etPassword;
    private AppCompatImageView ivPassword;
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
        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);
        ivPassword = view.findViewById(R.id.ivPassword);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        btLogin.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        ivPassword.setOnClickListener(this);
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
            case R.id.tvForgotPassword:
                ((ActivityLogin) requireActivity()).startFragment(ForgotPasswordFragment.newInstance());
                break;
            case R.id.ivPassword:
                break;
        }
    }

    private void startUserLogin(){
        Intent mIntent = new Intent(mContext, ActivityUserLogin.class);
        startActivity(mIntent);
        ((ActivityLogin) requireActivity()).finish();
    }

    private void validate(){
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
        reqLogin();
    }
    private String getUserName() {
        return etUserName.getText().toString().trim();
    }

    private String getPassword() {
        return etPassword.getText().toString().trim();
    }

    public void reqLogin() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        Map<String, String> req_params = new HashMap<>();
        req_params.put(REQUEST_KEY_USERNAME, getUserName());
        req_params.put(REQUEST_KEY_PASSWORD, getPassword());

        new RestApiRequestListener(requireActivity(), TAG_REQUEST_LOGIN, RestApiUrl.URL_GUEST_USER_LOGIN, headers, req_params, new RestApiRequestListener.setOnRequestListener() {
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
    }

    private void parseLogInResponse(String response) {
        GuestUserResponse mGuestUserResponse = new Gson().fromJson(response,GuestUserResponse.class);
      if(mGuestUserResponse!=null && mGuestUserResponse.getToken()!=null){
          PrefUtil.saveGuestUser(requireActivity(), mGuestUserResponse);
          startUserLogin();
      }else {
          Toast.makeText(mContext,"Please enter valid username and password",Toast.LENGTH_LONG).show();
      }
    }
}
