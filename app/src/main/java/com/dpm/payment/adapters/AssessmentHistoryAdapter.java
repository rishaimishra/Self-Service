package com.dpm.payment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.models.AssessmentHistory;
import com.dpm.payment.models.TransactionModel;
import com.dpm.payment.utils.DataUtils;
import com.dpm.payment.utils.LogUtils;
import com.dpm.payment.utils.StringUtils;
import com.payment.R;

import java.math.BigDecimal;
import java.util.List;


public class AssessmentHistoryAdapter extends RecyclerView.Adapter<AssessmentHistoryAdapter.MyViewHolder> {

    private List<AssessmentHistory> listOfNav;



    public AssessmentHistoryAdapter(List<AssessmentHistory> listOfNav) {
        this.listOfNav = listOfNav;

    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_assisment_history, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final AssessmentHistory mAssessmentHistory = listOfNav.get(position);



        try {

            String mAssessmentYear = (mAssessmentHistory.getAssessmentYear() == null) ? "0" : "" +mAssessmentHistory.getAssessmentYear();

            holder.tvAssessmentYearValue.setText(mAssessmentYear);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {



            String mCurrentYearAssessmentAmount = (mAssessmentHistory.getCurrentYearAssessmentAmount() == null) ? "0" : "" +getSeparatedByComma(mAssessmentHistory.getCurrentYearAssessmentAmount());
            holder.AssessmentAmount.setText(mCurrentYearAssessmentAmount);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {

            String mArrearDue = (mAssessmentHistory.getArrearDue() == null) ? "0" : "" +getSeparatedByComma(mAssessmentHistory.getArrearDue());
            holder.tvArrearValue.setText(mArrearDue);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            String mPenalty = (mAssessmentHistory.getPenalty() == null) ? "0" : "" +getSeparatedByComma(mAssessmentHistory.getPenalty());
            holder.tvPenaltyValue.setText(mPenalty);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            String mPenalty = (mAssessmentHistory.getPenalty() == null) ? "0" : "" +getSeparatedByComma(mAssessmentHistory.getPenalty());
            holder.tvPenaltyValue.setText(mPenalty);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {

            String mAmountPaid = (mAssessmentHistory.getAmountPaid() == null) ? "0" : "" +getSeparatedByComma(mAssessmentHistory.getAmountPaid());
            holder.tvAmountPaid.setText(mAmountPaid);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            String mAmountPaid = (mAssessmentHistory.getAmountPaid() == null) ? "0" : "" +getSeparatedByComma(mAssessmentHistory.getAmountPaid());
            holder.tvAmountPaid.setText(mAmountPaid);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            // TODO: Amount due Amount Due from balance key  //
            String mDueAmount = (mAssessmentHistory.getBalance() == null) ? "0" : "" +getSeparatedByComma(mAssessmentHistory.getBalance());

            holder.tvDueValue.setText(mDueAmount);
            LogUtils.showErrorLog(" mDue amount "," mDue amount "+mDueAmount);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {


        return listOfNav == null ? 0 : listOfNav.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvAssessmentYearValue;
        TextView AssessmentAmount;
        TextView tvArrearValue;
        TextView tvPenaltyValue;
        TextView tvAmountPaid;
        TextView tvDueValue;



        // view create //
        MyViewHolder(View view) {
            super(view);

            tvAssessmentYearValue = view.findViewById(R.id.tvAssessmentYearValue);
            AssessmentAmount = view.findViewById(R.id.AssessmentAmount);
            tvArrearValue = view.findViewById(R.id.tvArrearValue);
            tvPenaltyValue = view.findViewById(R.id.tvPenaltyValue);
            tvAmountPaid = view.findViewById(R.id.tvAmountPaid);
            tvDueValue = view.findViewById(R.id.tvDueValue);

        }
    }

    public String getSeparatedByComma(String value){
        return StringUtils.AmountWithComma(StringUtils.roundStringValue("" + new BigDecimal(value)));
    }

}