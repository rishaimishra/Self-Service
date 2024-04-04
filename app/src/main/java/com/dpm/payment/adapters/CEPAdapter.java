package com.dpm.payment.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.models.TransactionModel;
import com.dpm.payment.models.cep.CepModel;
import com.payment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CEPAdapter extends RecyclerView.Adapter<CEPAdapter.ViewHolder> {


    private Activity activity;
    private List<CepModel> list;

    public CEPAdapter(Activity activity, List<CepModel> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.adapter_cep, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CepModel item = list.get(position);
        holder.ivCep.setImageResource(item.getCepIcon());
        holder.tvTitle.setText(item.getCepTitle());

    }



    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCep;
        AppCompatTextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCep = itemView.findViewById(R.id.ivCep);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
