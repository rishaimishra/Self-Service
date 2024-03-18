package com.dpm.payment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.models.TransactionModel;
import com.dpm.payment.utils.DataUtils;
import com.dpm.payment.utils.LogUtils;
import com.dpm.payment.utils.StringUtils;
import com.payment.R;

import java.util.List;

//TODO Create by Debabrata.
public class TransactionDetailAdapter extends RecyclerView.Adapter<TransactionDetailAdapter.MyViewHolder> {

    private List<TransactionModel> listOfNav;



    public TransactionDetailAdapter(List<TransactionModel> listOfNav) {
        this.listOfNav = listOfNav;

    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview_payment_details, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final TransactionModel mTransactionModel = listOfNav.get(position);


        //TODO Need work on data mapping
        try {

            holder.tvAmountPaid.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue(mTransactionModel.getAmount())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {


            LogUtils.showErrorLog("First Name : ","First Name : "+mTransactionModel.getAdminTransactionModel().getFirstName());
            String mfrstName = ((mTransactionModel.getAdminTransactionModel().getFirstName() == null) ? "" : mTransactionModel.getAdminTransactionModel().getFirstName());
            String mlastName = ((mTransactionModel.getAdminTransactionModel().getLastName() == null) ? "" :mTransactionModel.getAdminTransactionModel().getLastName());

            holder.tvCashierName.setText( mfrstName+ " " +mlastName);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            String Transaction_id = ((mTransactionModel.getId()== null) ? "" : mTransactionModel.getId());

            holder.tvTranId.setText(Transaction_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {

            String Property_id = ((mTransactionModel.getProperty_id()== null) ? "" : mTransactionModel.getProperty_id());
            holder.tvPropertyId.setText(Property_id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            String CreatedAt = ((mTransactionModel.getCreated_at()== null) ? "" : mTransactionModel.getCreated_at());
            holder.tvTranDate.setText(DataUtils.getYYYYMMDDFormatFromString(CreatedAt));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            String Payee_name = ((mTransactionModel.getPayee_name()== null) ? "" : mTransactionModel.getPayee_name());

            holder.tvPayeeName.setText(Payee_name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            String Cheque_number = ((mTransactionModel.getCheque_number()== null) ? "" : mTransactionModel.getCheque_number());
            holder.tvChequeNo.setText(Cheque_number);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            String Payment_type = ((mTransactionModel.getPayment_type()== null) ? "" : mTransactionModel.getPayment_type());

            holder.tvPaymentType.setText(Payment_type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            String mBalance = ((mTransactionModel.getBalance()== null) ? "" : mTransactionModel.getBalance());

            holder.tvRemainingBalance.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue(mBalance)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            String mDueBalance = ((mTransactionModel.getAssessment()== null) ? "" : mTransactionModel.getAssessment());

            holder.tvAmountDue.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue(mDueBalance)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }




    }

    @Override
    public int getItemCount() {


        return listOfNav == null ? 0 : listOfNav.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvAmountPaid,
                tvAmountDue,
                tvCashierName,
                tvTranId,
                tvPropertyId,
                tvTranDate,
                tvPayeeName,
                tvChequeNo,
                tvPaymentType,
                tvRemainingBalance;
       // View viewLine;


        // view create //
        MyViewHolder(View view) {
            super(view);

            tvAmountPaid = view.findViewById(R.id.tvAmountPaid);
            tvAmountDue = view.findViewById(R.id.tvAmountDue);
            tvCashierName = view.findViewById(R.id.tvCashierName);
            tvTranId = view.findViewById(R.id.tvTranId);
            tvPropertyId = view.findViewById(R.id.tvPropertyId);
            tvTranDate = view.findViewById(R.id.tvTranDate);
            tvPayeeName = view.findViewById(R.id.tvPayeeName);
            tvChequeNo = view.findViewById(R.id.tvChequeNo);
            tvPaymentType = view.findViewById(R.id.tvPaymentType);
            tvRemainingBalance = view.findViewById(R.id.tvRemainingBalance);
            //viewLine= view.findViewById(R.id.viewLine);


        }
    }
}