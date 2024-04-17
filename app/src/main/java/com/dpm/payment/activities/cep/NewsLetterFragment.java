package com.dpm.payment.activities.cep;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.activities.cep.complaints.ComplaintsFragment;
import com.dpm.payment.activities.cep.emergencyservice.EmergencyServicesFragment;
import com.dpm.payment.adapters.CEPAdapter;
import com.dpm.payment.adapters.NewsLetterAdapter;
import com.dpm.payment.models.cep.CepModel;
import com.payment.R;

import java.util.ArrayList;

public class NewsLetterFragment extends Fragment {
    private RecyclerView rvNewsLetter;
    private Context mContext;

    public static NewsLetterFragment newInstance() {
        return new NewsLetterFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_letter, container, false);
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
        tvTitle.setText("Newsletter");
        ivHome.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

    private void initView(View view) {
        rvNewsLetter = view.findViewById(R.id.rvNewsLetter);
        setData();
    }

    private void setData() {

        setAdapter();
    }

    private void setAdapter() {
        NewsLetterAdapter adapter = new NewsLetterAdapter(mContext,  (view, position) -> {

        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvNewsLetter.setLayoutManager(layoutManager);
        rvNewsLetter.setAdapter(adapter);
    }
}
