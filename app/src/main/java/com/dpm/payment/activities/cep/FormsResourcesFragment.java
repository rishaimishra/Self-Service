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
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dpm.payment.adapters.FormsResourceAdapter;
import com.payment.R;

import java.util.ArrayList;

public class FormsResourcesFragment extends Fragment {
    private RecyclerView rvFormsResources;
    private Context mContext;
    private  ArrayList<String> mformsList;

    public static FormsResourcesFragment newInstance() {
        return new FormsResourcesFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forms_resources, container, false);
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
        tvTitle.setText("Forms/Resources");
        ivHome.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
        AppCompatImageView ivProfile = view.findViewById(R.id.ivProfile);
        AppCompatImageView ivNotification = view.findViewById(R.id.ivNotification);
        ivProfile.setOnClickListener(v -> {
            ((ActivityCep)requireActivity()).startFragment(MyProfileFragment.newInstance());
        });
        ivNotification.setOnClickListener(v -> {
            ((ActivityCep)requireActivity()).startFragment(NotificationFragment.newInstance());
        });
    }

    private void initView(View view) {
        rvFormsResources = view.findViewById(R.id.rvFormsResources);
        setData();
    }

    private void setData() {
        mformsList = new ArrayList<>();
        mformsList.add("Business Registration");
        mformsList.add("Business License");
        mformsList.add("Clearance Certificate");
        mformsList.add("Road Naming");
        setAdapter();
    }

    private void setAdapter() {
        FormsResourceAdapter adapter = new FormsResourceAdapter(mContext, mformsList,  (view, position) -> {

        });
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        rvFormsResources.setLayoutManager(layoutManager);
        rvFormsResources.setAdapter(adapter);
    }
}
