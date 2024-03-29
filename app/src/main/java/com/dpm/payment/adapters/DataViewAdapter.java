package com.dpm.payment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dpm.payment.models.DataModel;
import com.payment.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//TODO Create by Debabrata.
public class DataViewAdapter extends RecyclerView.Adapter<DataViewAdapter.MyViewHolder> {

    private List<DataModel> listOfNav;


    public DataViewAdapter(List<DataModel> listOfNav) {
        this.listOfNav = listOfNav;

    }

    public void updateItems(DataModel newItem){
        for (int i=0 ; i<listOfNav.size();i++){
            if (listOfNav.get(i).getKey().equalsIgnoreCase(newItem.getKey())){
                listOfNav.set(i,newItem);
                notifyItemChanged(i);
            }
        }


    }



    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview_details, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final DataModel object = listOfNav.get(position);

        try {
            holder.tvKey.setText(object.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            if (object.getValue()==null){
                holder.tvValue.setText("");
            }else {
                holder.tvValue.setText(object.getValue());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {


        return listOfNav == null ? 0 : listOfNav.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvKey, tvValue;


        // view create //
        MyViewHolder(View view) {
            super(view);
            tvKey = view.findViewById(R.id.tvKey);
            tvValue = view.findViewById(R.id.tvValue);

        }
    }
}