package com.dpm.payment.activities.cashier;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.payment.activities.user.ActivityUserMainDetails;
import com.dpm.payment.adapters.AssessmentHistoryAdapter;
import com.dpm.payment.adapters.CashierImageAdapter;
import com.dpm.payment.adapters.DataViewAdapter;
import com.dpm.payment.adapters.GeoRegistryDataAdapter;
import com.dpm.payment.adapters.ImageAdapter;
import com.dpm.payment.adapters.ImageTextAdapter;
import com.dpm.payment.adapters.PropertyImageAdapter;
import com.dpm.payment.adapters.TransactionDetailAdapter;
import com.dpm.payment.models.AssessmentHistory;
import com.dpm.payment.models.DataModel;
import com.dpm.payment.models.GeoRegistryModel;
import com.dpm.payment.models.MeterDetailsModel;
import com.dpm.payment.models.SearchAssessmentModel;
import com.dpm.payment.models.SearchLandlordModel;
import com.dpm.payment.models.SearchOccupancyModel;
import com.dpm.payment.models.SearchPropertyModel;
import com.dpm.payment.models.TransactionModel;
import com.dpm.payment.models.propertydetail.Assessment;
import com.dpm.payment.retrofit.Utills.ApiRequest;
import com.dpm.payment.retrofit.Utills.PART;
import com.dpm.payment.retrofit.interfaces.OnCallBackListner;
import com.dpm.payment.utils.CommonUtils;
import com.dpm.payment.utils.LogUtils;
import com.dpm.payment.utils.PrefUtil;
import com.dpm.payment.utils.StringUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.payment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.dpm.payment.activities.user.ActivityMainUserProperty.KEY_PROPERTY_DETAILS;
import static com.dpm.payment.utils.Helper.roundOffDecimals;
import static com.dpm.payment.utils.RestApiUrl.URL_CASHIER_LANDLORD_EDIT_PROFILE;
import static com.dpm.payment.utils.StringUtils.getAppendListDataWithSpacialCharacter;

// Cashier login
public class ActivityMainDetails extends AppCompatActivity implements View.OnClickListener, OnCallBackListner {

    final static String VALUE_FROM_CASHIER = "ActivityMainCashierProperty";
    final static String KEY_FROM = "form";
    final static String VALUE_FROM_LANDLORD = "ActivityMainUserProperty";
    private static final int CODE_VERIFICATION_DOCUMENT = 100;
    private static final int CODE_ADDRESS_PROOF = 101;
    final int KEY_TYPE_LAND = 1;
    final int KEY_TYPE_CASHIER = 2;
    Context mContext;
    /*toolbar*/
    AlertDialog dialog;
    /*toolbar*/
    TextView toolbar_tv_header;
    ImageView toolbar_iv_home, toolbar_iv_search;
    //TODO Landload Ui By Debabrata
    TextView activitySearchDetails_tv_property_images, activitySearchDetails_tv_rate_payable, activitySearchDetails_tv_assessment_history, activitySearchDetails_tv_landlord_details, activitySearchDetails_tv_property_details, activitySearchDetails_tv_occupancy_details,
            activitySearchDetails_tv_assessment_details, activitySearchDetails_tv_geo_registry_details,
            activitySearchDetails_tv_councillor_adjustment, activitySearchDetails_tv_cashier_receipt, activitySearchDetails_tv_pensioner_receipt, activitySearchDetails_tv_disability_receipt,
            activitySearchDetails_tv_council_discount, activitySearchDetails_tv_government_policy;


    Boolean expand_property_image = false, expand__rate_payable = false, expand_assessment_history = false, expand_landlord_details = false, expand_property_details = false,
            expand_occupancy_details = false, expand_assessment_details = false, expand_geo_registry_details = false, expand_payment_trans_details = false,
            expand_councillor_adjustment = false, expand_cashier_receipt_details = false, expand_pensioner_receipt_details = false, expand_disability_receipt_details = false,
            expand_council_discount = false, expand_government_policy = false;


    View include_property_images, include_tv_rate_payable, include_assessment_history, include_landlord_details, include_property_details, include_occupancy_details, include_assessment_details, include_geo_registry_details,
            include_councillor_adjustment, include_tv_cashier_receipt, include_tv_pensioner_receipt, include_tv_disability_receipt,
            include_tv_council_discount, include_tv_government_policy;


    View include_payment_details;
    ImageView ivProfilePicLandload;
    //TODO Property UI By Debabrata
    RecyclerView rvLandload;
    List<DataModel> listLandload = new ArrayList<>();
    DataViewAdapter adapterLandload;
    //TODO Occupancy Ui By Debabrata
    RecyclerView rvProperty;
    List<DataModel> listProperty = new ArrayList<>();
    DataViewAdapter adapterProperty;
    //TODO Assessment Ui By Debabrata
    RecyclerView rvOccupancy;
    List<DataModel> listOccupancy = new ArrayList<>();
    DataViewAdapter adapterOccupancy;
    //TODO Geo Registry Ui By Debabrata
    RecyclerView rvAssesment;
    RecyclerView rvRatePayable;
    List<DataModel> listAssessment = new ArrayList<>();
    DataViewAdapter adapterAssessment;

    //TODO Assesment Geo Registry Ui By Debabrata
    TextView tvDigitalAddress1, tvDigitalAddress2;
    RecyclerView rvGeoregistry, rvGeoRegitryImg;
    List<DataModel> listGeoregistry = new ArrayList<>();
    GeoRegistryDataAdapter adapterGeoregistry;
    List<String> listGeoRegitryImg = new ArrayList<>();
    ImageTextAdapter adapterGeoRegistryImg;


    RecyclerView rvAssessmentImg;

    //-----------------------------------------//
    List<String> listAssessmentImg = new ArrayList<>();
    ImageAdapter adapterAssessmentImg;
    TextView tv_assesment_year_value;
    TextView AssesmentAmount;
    TextView tvPenaltyValue;
    TextView amountPaid;
    TextView tv_dueValue;
    TextView tvPenalty;
    TextView tvarrears;
    int TYPE_OF_REQ = 0;
    JSONObject JsonObject;

    List<TransactionModel> listPayment = new ArrayList<>();
    TransactionDetailAdapter adapterTransaction;
    RecyclerView rvPayment;
    RecyclerView rvAssessmentHistory;
    TextView activitySearchDetails_tv_payment;
    ArrayList<MeterDetailsModel> mListDataMeterDetails = new ArrayList<>();

    ArrayList<AssessmentHistory> mListAssessmentHistory = new ArrayList<>();

    AssessmentHistoryAdapter mAssessmentHistoryAdapter;

    // FIXME: 20-09-2021
    Assessment dataItem;

    private RecyclerView recyclerview_image_cashier, recyclerview_image_disability, recyclerview_image_pensioner, rv_councillor_adjustment, rv_government_policy, recyclerview_image_property;
    ;

    // FIXME: 23-09-2021
    AlertDialog dialogLandlord;

    ApiRequest apiRequest;

    Button btn_edit_landlord, btn_edit_property_details;

    // FIXME: 08-10-2021
    ImageView img_verification_document, img_address_proof;
    File file_verification_document, file_address_proof;
    TextView edt_note_id_proof, edt_note_address_proof;
    LinearLayout lin_id_proof, lin_address_proof;

    String old_street_flag = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_details);
        apiRequest = new ApiRequest(this, this);

        initializeViews();
        initializeListeners();


        setData();

        //    setDrawerProfile(PrefUtils.getProfile(mContext));
    }

    private void setData() {

        try {

            dataItem = new Gson().fromJson(getIntent().getStringExtra(KEY_PROPERTY_DETAILS + "1"), Assessment.class);


            String mTesting = this.getIntent().getStringExtra(KEY_FROM);

            JsonObject = new JSONObject(PrefUtil.getSearchRequest(mContext));
            setLandloadData(JsonObject.getJSONObject("property").optJSONObject("landlord"), JsonObject.getJSONObject("property"));
            setOccupancyData(JsonObject.getJSONObject("property").optJSONObject("occupancy"), JsonObject.getJSONObject("property"));
            try {
                if (JsonObject.getJSONObject("property").optJSONArray("payments").length() > 0) {
                    setTransactionData(JsonObject.getJSONObject("property").optJSONArray("payments"));
                    activitySearchDetails_tv_payment.setVisibility(View.VISIBLE);

                } else {

                    activitySearchDetails_tv_payment.setVisibility(View.GONE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            setAssessmentData(JsonObject.getJSONObject("property").optJSONObject("assessment"), dataItem);
            setGeoRegistryData(JsonObject.getJSONObject("property").optJSONObject("geo_registry"), JsonObject.getJSONObject("property"));
            setMeterData(JsonObject.getJSONObject("property"));
            setPropertyData(JsonObject.getJSONObject("property"));
            viewAssessmentHistory(JsonObject);
            initCouncillorAdjustment();
            initGovernmentPolicy();
            initAdjustedPayable(JsonObject.getString("discounted_value"));

            List<TransactionModel> pensionerImages = new ArrayList<TransactionModel>();
            List<TransactionModel> disabilityImages = new ArrayList<TransactionModel>();

            String disability_discount = "";
            String pensioner_discount = "";


            if (JsonObject.getJSONObject("property").getJSONArray("assessment_history").length() > 0) {
                disability_discount = JsonObject.getJSONObject("property").getJSONArray("assessment_history").getJSONObject(0).getString("disability_discount");
                pensioner_discount = JsonObject.getJSONObject("property").getJSONArray("assessment_history").getJSONObject(0).getString("pensioner_discount");
            }

            if (disability_discount == null || disability_discount.equalsIgnoreCase("")) {
                disability_discount = "0";
            }

            if (pensioner_discount == null || pensioner_discount.equalsIgnoreCase("")) {
                pensioner_discount = "0";
            }


            if (JsonObject.getJSONObject("pensioner_image_path").getString("pensioner_discount_image_path") != null) {
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setPensioner_discount_image_path(JsonObject.getJSONObject("pensioner_image_path").getString("pensioner_discount_image_path"));
                transactionModel.setPensioner_discount_approve(pensioner_discount);
                pensionerImages.add(transactionModel);
                initPensionerImage(pensionerImages);
            }

            if (JsonObject.getJSONObject("disability_image_path").getString("disability_discount_image_path") != null) {
                TransactionModel transactionModel1 = new TransactionModel();
                transactionModel1.setDisability_discount_image_path(JsonObject.getJSONObject("disability_image_path").getString("disability_discount_image_path"));

                transactionModel1.setDisability_discount_approve(disability_discount);
                disabilityImages.add(transactionModel1);
                initDisabilityImage(disabilityImages);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void initializeViews() {

        //----------------------------------------------------------------------------------//

        btn_edit_landlord = findViewById(R.id.btn_edit_landlord);
        btn_edit_property_details = findViewById(R.id.btn_edit_property_details);
        btn_edit_property_details.setVisibility(View.GONE);

        recyclerview_image_property = findViewById(R.id.recyclerview_image_property);
        recyclerview_image_cashier = findViewById(R.id.recyclerview_image_cashier);
        recyclerview_image_disability = findViewById(R.id.recyclerview_image_disability);
        recyclerview_image_pensioner = findViewById(R.id.recyclerview_image_pensioner);


        recyclerview_image_property.setHasFixedSize(true);
        recyclerview_image_property.setFocusable(false);


        recyclerview_image_cashier.setHasFixedSize(true);
        recyclerview_image_cashier.setFocusable(false);

        recyclerview_image_disability.setHasFixedSize(true);
        recyclerview_image_disability.setFocusable(false);

        recyclerview_image_pensioner.setHasFixedSize(true);
        recyclerview_image_pensioner.setFocusable(false);


        tv_assesment_year_value = findViewById(R.id.activityDetailsAssesmentHistory_tv_assesment_year_value);

        AssesmentAmount = findViewById(R.id.activityDetailsAssesmentHistory_tv_arrear_value);
        tvPenaltyValue = findViewById(R.id.activityDetailsAssesmentHistory_tv_amount_paid_value);
        amountPaid = findViewById(R.id.activityDetailsAssesmentHistory_tv_balance_value);
        tv_dueValue = findViewById(R.id.activityDetailsAssesmentHistory_tv_due_value);
        tvarrears = findViewById(R.id.activityDetailsAssesmentHistory_tv_penalty_value);


        //--------------------------------------------------------------------------------//

        toolbar_tv_header = findViewById(R.id.toolbar_tv_header);
        toolbar_tv_header.setText(R.string.details);

        toolbar_iv_home = findViewById(R.id.toolbar_iv_home);
        toolbar_iv_home.setVisibility(View.VISIBLE);
        toolbar_iv_search = findViewById(R.id.toolbar_iv_search);


        activitySearchDetails_tv_property_images = findViewById(R.id.activitySearchDetails_tv_property_images);
        activitySearchDetails_tv_rate_payable = findViewById(R.id.activitySearchDetails_tv_rate_payable);
        activitySearchDetails_tv_assessment_history = findViewById(R.id.activitySearchDetails_tv_assessment_history);
        activitySearchDetails_tv_landlord_details = findViewById(R.id.activitySearchDetails_tv_landlord_details);
        activitySearchDetails_tv_assessment_details = findViewById(R.id.activitySearchDetails_tv_assessment_details);
        activitySearchDetails_tv_property_details = findViewById(R.id.activitySearchDetails_tv_property_details);
        activitySearchDetails_tv_occupancy_details = findViewById(R.id.activitySearchDetails_tv_occupancy_details);
        activitySearchDetails_tv_geo_registry_details = findViewById(R.id.activitySearchDetails_tv_geo_registry_details);
        activitySearchDetails_tv_cashier_receipt = findViewById(R.id.activitySearchDetails_tv_cashier_receipt);
        activitySearchDetails_tv_pensioner_receipt = findViewById(R.id.activitySearchDetails_tv_pensioner_receipt);
        activitySearchDetails_tv_disability_receipt = findViewById(R.id.activitySearchDetails_tv_disability_receipt);
        activitySearchDetails_tv_councillor_adjustment = findViewById(R.id.activitySearchDetails_tv_councillor_adjustment);
        activitySearchDetails_tv_council_discount = findViewById(R.id.activitySearchDetails_tv_council_discount);
        activitySearchDetails_tv_government_policy = findViewById(R.id.activitySearchDetails_tv_government_policy);

        activitySearchDetails_tv_payment = findViewById(R.id.activitySearchDetails_tv_payment);


        include_tv_rate_payable = findViewById(R.id.include_tv_rate_payable);
        include_property_images = findViewById(R.id.include_property_images);
        include_assessment_history = findViewById(R.id.include_assessment_history);
        include_landlord_details = findViewById(R.id.include_landlord_details);
        include_property_details = findViewById(R.id.include_property_details);
        include_occupancy_details = findViewById(R.id.include_occupancy_details);
        include_assessment_details = findViewById(R.id.include_assessment_details);
        include_geo_registry_details = findViewById(R.id.include_geo_registry_details);
        include_tv_cashier_receipt = findViewById(R.id.include_tv_cashier_receipt);
        include_tv_pensioner_receipt = findViewById(R.id.include_tv_pensioner_receipt);
        include_tv_disability_receipt = findViewById(R.id.include_tv_disability_receipt);
        include_councillor_adjustment = findViewById(R.id.include_councillor_adjustment);
        include_tv_council_discount = findViewById(R.id.include_tv_council_discount);
        include_tv_government_policy = findViewById(R.id.include_tv_government_policy);


        include_payment_details = findViewById(R.id.include_payment_details);


        activitySearchDetails_tv_geo_registry_details = findViewById(R.id.activitySearchDetails_tv_geo_registry_details);
        activitySearchDetails_tv_geo_registry_details = findViewById(R.id.activitySearchDetails_tv_geo_registry_details);
        activitySearchDetails_tv_geo_registry_details = findViewById(R.id.activitySearchDetails_tv_geo_registry_details);
        activitySearchDetails_tv_geo_registry_details = findViewById(R.id.activitySearchDetails_tv_geo_registry_details);


        initAssessmentHistoryView();
        initLandlordView();
        initPropertyView();
        initOccupancyView();
        initAssessmentView();
        initGeoregistryView();
        initPaymentTrans();
        initGeoImg();
        initAssessmentImg();


    }

    private void initCouncillorAdjustment() {
        rv_councillor_adjustment = findViewById(R.id.rv_councillor_adjustment);
        rv_councillor_adjustment.setLayoutManager(new LinearLayoutManager(this));
        rv_councillor_adjustment.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rv_councillor_adjustment, false);

        List<DataModel> councillor_list = new ArrayList<>();
        /*councillor_list.add(new DataModel("window_type", dataItem.getWindowTypeType()));
        councillor_list.add(new DataModel("sanitation", dataItem.getSanitation() + ""));
        councillor_list.add(new DataModel("Window type percentage", dataItem.getWindowTypePercentage() + "%"));
        councillor_list.add(new DataModel("pensioner_discount", dataItem.getPensionerDiscount() == 1 ? "Yes" : "No"));
        councillor_list.add(new DataModel("disability_discount", dataItem.getDisabilityDiscount() == 1 ? "Yes" : "No"));*/
        councillor_list.add(new DataModel("water", dataItem.getWaterPercentage() + "%"));
        councillor_list.add(new DataModel("electricity", dataItem.getElectricityPercentage() + "%"));
        councillor_list.add(new DataModel("waste_management", dataItem.getWasteManagementPercentage() + "%"));
        councillor_list.add(new DataModel("market", dataItem.getMarketPercentage() + "%"));
        councillor_list.add(new DataModel("hazardous", dataItem.getHazardousPrecentage() + "%"));
        councillor_list.add(new DataModel("drainage", dataItem.getDrainagePercentage() + "%"));
        councillor_list.add(new DataModel("informal_settlement", dataItem.getInformalSettlementPercentage() + "%"));
        councillor_list.add(new DataModel("easy_street_access", dataItem.getEasyStreetAccessPercentage() + "%"));
        councillor_list.add(new DataModel("paved_tarred_street", dataItem.getPavedTarredStreetPercentage() + "%"));
        //councillor_list.add(new DataModel("council_group_name", dataItem.getCouncilGroupName() + ""));

        DataViewAdapter adapter = new DataViewAdapter(councillor_list);
        rv_councillor_adjustment.setAdapter(adapter);
    }

    // rv_government_policy
    private void initGovernmentPolicy() {
        rv_government_policy = findViewById(R.id.rv_government_policy);
        rv_government_policy.setLayoutManager(new LinearLayoutManager(this));
        rv_government_policy.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rv_government_policy, false);

        List<DataModel> councillor_list = new ArrayList<>();
        /*councillor_list.add(new DataModel("window_type", dataItem.getWindowTypeType()));
        councillor_list.add(new DataModel("sanitation", dataItem.getSanitation() + ""));
        councillor_list.add(new DataModel("Window type percentage", dataItem.getWindowTypePercentage() + "%"));
        councillor_list.add(new DataModel("pensioner_discount", dataItem.getPensionerDiscount() == 1 ? "Yes" : "No"));
        councillor_list.add(new DataModel("disability_discount", dataItem.getDisabilityDiscount() == 1 ? "Yes" : "No"));*/
    /*    councillor_list.add(new DataModel("water", dataItem.getWaterPercentage() + "%"));
        councillor_list.add(new DataModel("electricity", dataItem.getElectricityPercentage() + "%"));
        councillor_list.add(new DataModel("waste_management", dataItem.getWasteManagementPercentage() + "%"));
        councillor_list.add(new DataModel("market", dataItem.getMarketPercentage() + "%"));
        councillor_list.add(new DataModel("hazardous_precentage", dataItem.getHazardousPrecentage() + "%"));
        councillor_list.add(new DataModel("drainage", dataItem.getDrainagePercentage() + "%"));
        councillor_list.add(new DataModel("informal_settlement", dataItem.getInformalSettlementPercentage() + "%"));
        councillor_list.add(new DataModel("easy_street_access", dataItem.getEasyStreetAccessPercentage() + "%"));
        councillor_list.add(new DataModel("paved_tarred_street", dataItem.getPavedTarredStreetPercentage() + "%"));*/
        String property_taxable_value = "";
        try {
            property_taxable_value = JsonObject.getString("property_taxable_value");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        councillor_list.add(new DataModel("Net Assessed Value", dataItem.getProperty_net_assessed_value()));
        councillor_list.add(new DataModel("Taxable Property Value", property_taxable_value))

        ;
        councillor_list.add(new DataModel("Council_Group/Category", dataItem.getGroupName() + ""));
        councillor_list.add(new DataModel("Mill_Rate", dataItem.getMillRate() + ""));

        councillor_list.add(new DataModel("Rate Payable 2022", StringUtils.AmountWithComma(StringUtils.roundStringValue(dataItem.getRate_payable()))));

        DataViewAdapter adapter = new DataViewAdapter(councillor_list);
        rv_government_policy.setAdapter(adapter);
    }

    private void initAdjustedPayable(String value) {
        rvRatePayable = findViewById(R.id.rvRatePayable);
        rvRatePayable.setLayoutManager(new LinearLayoutManager(this));
        rvRatePayable.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rv_government_policy, false);

        List<DataModel> councillor_list = new ArrayList<>();
        councillor_list.add(new DataModel("Discounted Rate Payable 2022", StringUtils.AmountWithComma(value)));
        DataViewAdapter adapter = new DataViewAdapter(councillor_list);
        rvRatePayable.setAdapter(adapter);
    }

    private void initPropertyImages(List<String> listOfImages) {

        if (listOfImages.size() == 1)
            recyclerview_image_property.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 1));
        else if (listOfImages.size() == 2)
            recyclerview_image_property.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 2));


        recyclerview_image_property.setAdapter(new PropertyImageAdapter(this, listOfImages, "O"));
    }

    private void initDisabilityImage(List<TransactionModel> listOfImages) {

        if (listOfImages.size() == 1)
            recyclerview_image_disability.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 1));
        else if (listOfImages.size() == 2)
            recyclerview_image_disability.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 2));


        recyclerview_image_disability.setAdapter(new CashierImageAdapter(this, listOfImages, "D"));
    }

    private void initPensionerImage(List<TransactionModel> listOfImages) {

        if (listOfImages.size() == 1)
            recyclerview_image_pensioner.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 1));
        else if (listOfImages.size() == 2)
            recyclerview_image_pensioner.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 2));

        recyclerview_image_pensioner.setAdapter(new CashierImageAdapter(this, listOfImages, "P"));

    }

    private void initCashierImage(List<TransactionModel> listOfImages) {


        if (listOfImages.size() == 1)
            recyclerview_image_cashier.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 1));
        else if (listOfImages.size() == 2)
            recyclerview_image_cashier.setLayoutManager(new GridLayoutManager(ActivityMainDetails.this, 2));



        recyclerview_image_cashier.setAdapter(new CashierImageAdapter(this, listOfImages, "C"));

    }

    private void initAssessmentHistoryView() {

        rvAssessmentHistory = findViewById(R.id.rvAssessmentHistory);
        rvAssessmentHistory.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.divider));
        rvAssessmentHistory.addItemDecoration(itemDecorator);

        mAssessmentHistoryAdapter = new AssessmentHistoryAdapter(mListAssessmentHistory);
        rvAssessmentHistory.setAdapter(mAssessmentHistoryAdapter);
        rvAssessmentHistory.setFocusable(false);

    }


    //TODO initPayment
    private void initPaymentTrans() {

        rvPayment = findViewById(R.id.rvPayment);
        rvPayment.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.divider));
        rvPayment.addItemDecoration(itemDecorator);

        adapterTransaction = new TransactionDetailAdapter(listPayment);
        rvPayment.setAdapter(adapterTransaction);
        rvPayment.setFocusable(false);

    }

    //TODO setTransactionData by Debabrata
    private void setTransactionData(JSONArray transArray) {


        try {
            if (transArray != null) {

                LogUtils.printf("I am transArray.length(): " + transArray.length());

                List<TransactionModel> pensionerImages = new ArrayList<TransactionModel>();
                List<TransactionModel> cashierImages = new ArrayList<TransactionModel>();
                List<TransactionModel> disabilityImages = new ArrayList<TransactionModel>();

                for (int i = 0; i < transArray.length(); i++) {
                    try {
                        String obj = transArray.optJSONObject(i).toString().trim();

                        TransactionModel model = (TransactionModel) CommonUtils.getObjectFromJson(obj, TransactionModel.class);
                        listPayment.add(0, model);

                        /*if (model.getDisability_discount_image_path() != null && !model.getDisability_discount_image_path().equalsIgnoreCase(""))
                            if (model.getDisability_discount_approve().equalsIgnoreCase("1"))
                                disabilityImages.add(model);*/

                        if (model.getPhysical_receipt_image_path() != null && !model.getPhysical_receipt_image_path().equalsIgnoreCase(""))
                            cashierImages.add(model);

                       /* if (model.getPensioner_discount_image_path() != null && !model.getPensioner_discount_image_path().equalsIgnoreCase(""))
                            if (model.getPensioner_discount_approve().equalsIgnoreCase("1"))
                                pensionerImages.add(model);*/


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                initCashierImage(cashierImages);


                adapterTransaction.notifyDataSetChanged();


            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void viewAssessmentHistory(JSONObject mJsonObject) {

        if (mListAssessmentHistory != null) {
            mListAssessmentHistory.clear();
        }

        AssessmentHistory mAssessmentHistory = new AssessmentHistory();

        try {
            //  tv_assesment_year_value.setText(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("assessment_year")));

            mAssessmentHistory.setAssessmentYear(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("assessment_year"));
            LogUtils.showErrorLog("assesment_year ", " assesment_year " + mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("assessment_year"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {

            // AssesmentAmount.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("current_year_assessment_amount"))));
            mAssessmentHistory.setCurrentYearAssessmentAmount("" + StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("current_year_assessment_amount"))));
            LogUtils.showErrorLog("current_year_assessment_amount ", " current_year_assessment_amount " + mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("current_year_assessment_amount"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {

            //  tvPenaltyValue.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue((mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("penalty")))));

            mAssessmentHistory.setPenalty("" + StringUtils.AmountWithComma(StringUtils.roundStringValue((mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("penalty")))));
            LogUtils.showErrorLog("penalty ", " penalty " + mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("penalty"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            //    amountPaid.setText("" + StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("amount_paid"))));

            mAssessmentHistory.setAmountPaid("" + StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("amount_paid"))));


            LogUtils.showErrorLog("amountPaid ", " amountPaid KEY_TYPE_CASHIER " + mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("amount_paid"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            //  tvarrears.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("arrear_due"))));

            mAssessmentHistory.setArrearDue("" + StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("arrear_due")));
            LogUtils.showErrorLog("arrear_due ", " arrear_due  " + mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("arrear_due"));


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            // tv_dueValue.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("current_year_assessment_amount"))));
            // TODO: Amount due Amount Due from balance key  //
            mAssessmentHistory.setBalance(StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("balance"))));
            LogUtils.showErrorLog("arrear_due ", " arrear_due  " + mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("balance"));


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        try {

            //  tvPenaltyValue.setText(StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("penalty"))));

            mAssessmentHistory.setPenalty(StringUtils.AmountWithComma(StringUtils.roundStringValue(mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("penalty"))));
            LogUtils.showErrorLog("penalty ", " penalty  " + mJsonObject.getJSONObject("property").getJSONObject("assessment").optString("penalty"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // mListAssessmentHistory.add(0, mAssessmentHistory);

        //----------------------------------------------------- get data form Assessment history -------------------------------------------------------------//

        try {
            JSONArray mJAssessment_History = mJsonObject.getJSONObject("property").getJSONArray("assessment_history");

            for (int i = 0; i < mJAssessment_History.length(); i++) {
                try {

                    AssessmentHistory mlistAssessmentHistory = (AssessmentHistory) CommonUtils.getObjectFromJson(mJAssessment_History.get(i).toString(), AssessmentHistory.class);
                    mListAssessmentHistory.add(mlistAssessmentHistory);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //-----------------------------------------------------------------------------------------------------------------------------------------------------//


        mAssessmentHistoryAdapter.notifyDataSetChanged();

    }


    private void initLandlordView() {

        ivProfilePicLandload = findViewById(R.id.ivProfilePicLandload);
        rvLandload = findViewById(R.id.rvLandload);
        rvLandload.setLayoutManager(new LinearLayoutManager(this));
        adapterLandload = new DataViewAdapter(listLandload);
        rvLandload.setAdapter(adapterLandload);
        rvLandload.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rvLandload, false);
    }

    private void initPropertyView() {


        rvProperty = findViewById(R.id.rvProperty);
        rvProperty.setLayoutManager(new LinearLayoutManager(this));
        adapterProperty = new DataViewAdapter(listProperty);
        rvProperty.setAdapter(adapterProperty);
        rvProperty.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rvProperty, false);
    }

    private void initOccupancyView() {


        rvOccupancy = findViewById(R.id.rvOccupancy);
        rvOccupancy.setLayoutManager(new LinearLayoutManager(this));
        adapterOccupancy = new DataViewAdapter(listOccupancy);
        rvOccupancy.setAdapter(adapterOccupancy);
        rvOccupancy.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rvOccupancy, false);
    }

    private void initAssessmentView() {


        rvAssesment = findViewById(R.id.rvAssesment);
        //rvRatePayable = findViewById(R.id.rvRatePayable);
        rvAssesment.setLayoutManager(new LinearLayoutManager(this));
        adapterAssessment = new DataViewAdapter(listAssessment);
        rvAssesment.setAdapter(adapterAssessment);
        rvAssesment.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rvAssesment, false);
    }

    private void initAssessmentImg() {


        rvAssessmentImg = findViewById(R.id.rvAssessmentImg);
        rvAssessmentImg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterAssessmentImg = new ImageAdapter(listAssessmentImg);
        rvAssessmentImg.setAdapter(adapterAssessmentImg);
        rvAssessmentImg.setFocusable(false);
        //ViewCompat.setNestedScrollingEnabled(rvAssesment, false);
    }

    private void initGeoregistryView() {


        //TODO add digital address1 and 2 textview;

        tvDigitalAddress1 = findViewById(R.id.tvDigitalAddress1);
        tvDigitalAddress2 = findViewById(R.id.tvDigitalAddress2);

        rvGeoregistry = findViewById(R.id.rvGeoregistry);
        rvGeoregistry.setLayoutManager(new LinearLayoutManager(this));
        adapterGeoregistry = new GeoRegistryDataAdapter(listGeoregistry);
        rvGeoregistry.setAdapter(adapterGeoregistry);
        rvGeoregistry.setFocusable(false);
        ViewCompat.setNestedScrollingEnabled(rvGeoregistry, false);
    }

    //TODO Create by debabrata
    private void initGeoImg() {


        rvGeoRegitryImg = findViewById(R.id.rvGeoRegitryImg);
        rvGeoRegitryImg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterGeoRegistryImg = new ImageTextAdapter(mListDataMeterDetails);
        rvGeoRegitryImg.setAdapter(adapterGeoRegistryImg);
        rvGeoRegitryImg.setFocusable(false);

    }

    SearchLandlordModel landlordModel;

    private void setLandloadData(JSONObject landloadObject, JSONObject mMainObject) {
        try {
            if (landloadObject != null) {

                landlordModel = (SearchLandlordModel) CommonUtils.getObjectFromJson(landloadObject.toString().trim(), SearchLandlordModel.class);


              /*  try {
                    if (landlordModel.getImage() != null) {
                        Picasso.get()
                                .load("" + landlordModel.getSmallPreview())
                                .placeholder(R.drawable.ic_my_profile)
                                .error(R.drawable.ic_my_profile)
                                .into(ivProfilePicLandload);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

*/

                try {

                    DataModel model0 = new DataModel();
                    model0.setKey("Property ID");
                    model0.setValue("" + landlordModel.getPropertyId());
                    listLandload.add(model0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                if (mMainObject.optBoolean("is_organization")) {

                    try {

                        String organization_name = ((mMainObject.optString("organization_name") == null) ? "" : "" + mMainObject.optString("organization_name"));

                        DataModel model18 = new DataModel();
                        model18.setKey("Organization Name");
                        model18.setValue("" + organization_name);
                        listLandload.add(model18);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {

                        String OrganizationType = ((mMainObject.optString("organization_type") == null) ? "" : "" + mMainObject.optString("organization_type"));

                        DataModel model19 = new DataModel();
                        model19.setKey("Organization Type");
                        model19.setValue("" + OrganizationType);
                        listLandload.add(model19);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try {

                        String OrganizationType = ((mMainObject.optString("organization_addresss") == null) ? "" : "" + mMainObject.optString("organization_addresss"));

                        DataModel model110 = new DataModel();
                        model110.setKey("Organization Address");
                        model110.setValue("" + OrganizationType);
                        listLandload.add(model110);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try {

                        String mEmail = ((landlordModel.getEmail() == null) ? "" : "" + landlordModel.getEmail());
                        DataModel model18 = new DataModel();
                        model18.setKey("Email Address");
                        model18.setValue("" + mEmail);
                        listLandload.add(model18);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                } else {


                    try {

                        DataModel model0 = new DataModel();
                        model0.setKey("Title");
                        model0.setValue(landlordModel.getTitles().getLabel());
                        listLandload.add(model0);
                    } catch (Exception ex) {
                        DataModel model0 = new DataModel();
                        model0.setKey("Title");
                        model0.setValue("");
                        ex.printStackTrace();
                    }


                    try {
                        DataModel model1 = new DataModel();
                        model1.setKey("First Name");
                        model1.setValue(landlordModel.getFirstName());
                        listLandload.add(model1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    try {
                        DataModel model2 = new DataModel();
                        model2.setKey("Middle Name");
                        model2.setValue(landlordModel.getMiddleName());
                        listLandload.add(model2);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    try {
                        DataModel model3 = new DataModel();
                        model3.setKey("Surname");
                        model3.setValue(landlordModel.getSurname());
                        listLandload.add(model3);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    try {

                        String mEmail = ((landlordModel.getEmail() == null) ? "" : "" + landlordModel.getEmail());
                        DataModel model18 = new DataModel();
                        model18.setKey("Email Address");
                        model18.setValue("" + mEmail);
                        listLandload.add(model18);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    try {
                        DataModel model4 = new DataModel();
                        model4.setKey("Gender");
                        model4.setValue(landlordModel.getSex().toUpperCase());
                        listLandload.add(model4);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

               /* try {
                    DataModel model5 = new DataModel();
                    model5.setKey("Old Street Number");
                    model5.setValue(landlordModel.getStreetNumber());
                    listLandload.add(model5);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }*/

                try {
                    DataModel model5 = new DataModel();
                    model5.setKey("Street Number");
                    model5.setValue(landlordModel.getStreet_numbernew());
                    listLandload.add(model5);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                try {
                    DataModel model6 = new DataModel();
                    model6.setKey("Street Name");
                    model6.setValue(landlordModel.getStreetName());
                    listLandload.add(model6);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                DataModel model12 = new DataModel();
                model12.setKey("Section");
                model12.setValue(landlordModel.getSection());
                listLandload.add(model12);


                DataModel model10 = new DataModel();
                model10.setKey("Ward");
                model10.setValue(landlordModel.getWard());
                listLandload.add(model10);


                DataModel model11 = new DataModel();
                model11.setKey("Constituency");
                model11.setValue(landlordModel.getConstituency());
                listLandload.add(model11);


                DataModel model13 = new DataModel();
                model13.setKey("Chiefdom");
                model13.setValue(landlordModel.getChiefdom());
                listLandload.add(model13);

                DataModel model14 = new DataModel();
                model14.setKey("District");
                model14.setValue(landlordModel.getDistrict());
                listLandload.add(model14);

                DataModel model15 = new DataModel();
                model15.setKey("Province");
                model15.setValue(landlordModel.getProvince());
                listLandload.add(model15);


                String mPostcode = ((landlordModel.getPostcode() == null) ? "" : "" + landlordModel.getPostcode());
                DataModel model19 = new DataModel();
                model19.setKey("Postcode");
                model19.setValue("" + mPostcode);
                listLandload.add(model19);

                DataModel model16 = new DataModel();
                model16.setKey("Mobile Number 1");
                model16.setValue(landlordModel.getMobile1());
                listLandload.add(model16);

                DataModel model17 = new DataModel();
                model17.setKey("Mobile Number 2");
                model17.setValue(landlordModel.getMobile2());
                listLandload.add(model17);

                adapterLandload.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setPropertyData(JSONObject propertyObject) {
        try {
            if (propertyObject != null) {

                SearchPropertyModel propertyModel = (SearchPropertyModel) CommonUtils.getObjectFromJson(propertyObject.toString().trim(), SearchPropertyModel.class);

                DataModel model22 = new DataModel();
                model22.setKey("New Street Number");
                model22.setValue(propertyModel.getStreet_numbernew());
                listProperty.add(model22);


                DataModel model1 = new DataModel();
                model1.setKey("Old Street Number");
                model1.setValue(propertyModel.getStreetNumber());
                listProperty.add(model1);


                DataModel model2 = new DataModel();
                model2.setKey("Street Name");
                model2.setValue(propertyModel.getStreetName());
                listProperty.add(model2);


                DataModel model3 = new DataModel();
                model3.setKey("Ward");
                model3.setValue(String.valueOf(propertyModel.getWard()));
                listProperty.add(model3);


                String Constituency = ((propertyModel.getConstituency() == null) ? "" : propertyModel.getConstituency());


                DataModel model12 = new DataModel();
                model12.setKey("Constituency");
                model12.setValue(Constituency);
                listProperty.add(model12);


                DataModel model4 = new DataModel();
                model4.setKey("Section");
                model4.setValue(propertyModel.getSection());
                listProperty.add(model4);

                DataModel model5 = new DataModel();
                model5.setKey("Chiefdom");
                model5.setValue(propertyModel.getChiefdom());
                listProperty.add(model5);

                DataModel model6 = new DataModel();
                model6.setKey("District");
                model6.setValue(propertyModel.getDistrict());
                listProperty.add(model6);


                DataModel model7 = new DataModel();
                model7.setKey("Province");
                model7.setValue(propertyModel.getProvince());
                listProperty.add(model7);

                DataModel model8 = new DataModel();
                model8.setKey("Post Code");
                model8.setValue(propertyModel.getPostcode());
                listProperty.add(model8);


                adapterProperty.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setGeoRegistryData(JSONObject georegistryObject, JSONObject mMainObj) {
        try {

            //TODO by Debabrata set Geo registry all data.
            if (georegistryObject != null) {

                GeoRegistryModel geoModel = (GeoRegistryModel) CommonUtils.getObjectFromJson(georegistryObject.toString().trim(), GeoRegistryModel.class);

                DataModel model1 = new DataModel();
                model1.setKey("Point 1");
                model1.setValue(geoModel.getPoint1());
                listGeoregistry.add(model1);

                DataModel model2 = new DataModel();
                model2.setKey("Point 2");
                model2.setValue(geoModel.getPoint2());
                listGeoregistry.add(model2);


                DataModel model3 = new DataModel();
                model3.setKey("Point 3");
                model3.setValue(geoModel.getPoint3());
                listGeoregistry.add(model3);

                DataModel model4 = new DataModel();
                model4.setKey("Point 4");
                model4.setValue(geoModel.getPoint4());
                listGeoregistry.add(model4);

                DataModel model5 = new DataModel();
                model5.setKey("Point 5");
                model5.setValue(geoModel.getPoint5());
                listGeoregistry.add(model5);


                DataModel model6 = new DataModel();
                model6.setKey("Point 6");
                model6.setValue(geoModel.getPoint6());
                listGeoregistry.add(model6);


                DataModel model7 = new DataModel();
                model7.setKey("Point 7");
                model7.setValue(geoModel.getPoint7());
                listGeoregistry.add(model7);


                DataModel model8 = new DataModel();
                model8.setKey("Point 8");
                model8.setValue(geoModel.getPoint8());
                listGeoregistry.add(model8);

                //-----------------------------------------------//
                String DorLatLng = ((geoModel.getDor_lat_long() == null) ? "" : geoModel.getDor_lat_long());


                DataModel DorLatLong = new DataModel();
                DorLatLong.setKey("Dor Lat Long");
                DorLatLong.setValue(DorLatLng);
                listGeoregistry.add(DorLatLong);

                String Digital_address = ((geoModel.getDigital_address() == null) ? "" : geoModel.getDigital_address());
                DataModel DigitalAddress = new DataModel();
                DigitalAddress.setKey("Digital Address");
                DigitalAddress.setValue(Digital_address);
                listGeoregistry.add(DigitalAddress);

                try {
                    String sOpenLocationCode = ((geoModel.getOpen_location_code() == null) ? "" : geoModel.getOpen_location_code());

                    String mPostcode = mMainObj.optString("postcode");
                    DataModel mOpenLocationCode = new DataModel();
                    mOpenLocationCode.setKey("Open Location Code");
                    mOpenLocationCode.setValue(mPostcode + " " + sOpenLocationCode);
                    listGeoregistry.add(mOpenLocationCode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMeterData(JSONObject mMainObject) {
        try {

            JSONArray mListArray = mMainObject.getJSONArray("registry_meters");

            if (mMainObject != null) {
                for (int i = 0; i < mListArray.length(); i++) {
                    try {
                        mListDataMeterDetails.add((MeterDetailsModel) CommonUtils.getObjectFromJson(mListArray.getJSONObject(i).toString().trim(), MeterDetailsModel.class));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            adapterGeoRegistryImg.notifyDataSetChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setOccupancyData(JSONObject occupancyObject, JSONObject JSONMainObj) {
        try {
            if (occupancyObject != null) {

                SearchOccupancyModel occupancyModel = (SearchOccupancyModel) CommonUtils.getObjectFromJson(occupancyObject.toString().trim(), SearchOccupancyModel.class);


                try {

                    ArrayList<String> mList = new ArrayList<>();

                    String stOccupancies = "";
                    for (int i = 0; i < JSONMainObj.getJSONArray("occupancies").length(); i++) {
                        try {
                            mList.add(JSONMainObj.getJSONArray("occupancies").getJSONObject(i).optString("occupancy_type"));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    String mFinalStr = getAppendListDataWithSpacialCharacter(mList, ",");

                    DataModel model1 = new DataModel();
                    model1.setKey("Occupancy Type");
                    model1.setValue(mFinalStr);
                    listOccupancy.add(model1);
                    LogUtils.showErrorLog("Occupancy Type", "" + occupancyObject.optString("type"));


                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                DataModel model22 = new DataModel();
                model22.setKey("Tenant Title");
                model22.setValue(occupancyModel.getTitles().getLabel());
                listOccupancy.add(model22);


                DataModel model2 = new DataModel();
                model2.setKey("Tenant First Name");
                model2.setValue(occupancyModel.getTenantFirstName());
                listOccupancy.add(model2);


                DataModel model3 = new DataModel();
                model3.setKey("Middle Name");
                model3.setValue(occupancyModel.getMiddleName());
                listOccupancy.add(model3);


                DataModel model4 = new DataModel();
                model4.setKey("Surname");
                model4.setValue(occupancyModel.getSurname());
                listOccupancy.add(model4);

                DataModel model5 = new DataModel();
                model5.setKey("Mobile Number 1");
                model5.setValue(occupancyModel.getMobile1());
                listOccupancy.add(model5);

                DataModel model6 = new DataModel();
                model6.setKey("Mobile Number 2");
                model6.setValue(occupancyModel.getMobile2());
                listOccupancy.add(model6);

                adapterOccupancy.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAssessmentData(JSONObject assessmentObject, Assessment dataItem) {

        try {
            if (assessmentObject != null) {

                SearchAssessmentModel assessmentModel = (SearchAssessmentModel) CommonUtils.getObjectFromJson(assessmentObject.toString().trim(), SearchAssessmentModel.class);

                List<String> propertyImages = new ArrayList<>();
                // propertyImages.add(assessmentModel.getAssessmentImages1());
                // propertyImages.add(assessmentModel.getAssessmentImages2());
                propertyImages.add(assessmentModel.getOriginalOne());
                propertyImages.add(assessmentModel.getOriginalTwo());
                initPropertyImages(propertyImages);

                String mCategories = "";
                ArrayList<String> mListData = new ArrayList<>();
                for (int i = 0; i < assessmentModel.getCategories().size(); i++) {
                    try {
                        mListData.add(assessmentModel.getCategories().get(i).getLabel());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                try {

                    mCategories = getAppendListDataWithSpacialCharacter(mListData, ",");
                    DataModel model1 = new DataModel();
                    model1.setKey("Property Type");
                    model1.setValue(mCategories);
                    listAssessment.add(model1);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {

                    listAssessment.add(new DataModel("Dimension", "" + dataItem.getSquareMeter()));
                } catch (Exception ex) {
                    listAssessment.add(new DataModel("Dimension", ""));
                    ex.printStackTrace();
                }


           /*     try {
                    DataModel model2_2 = new DataModel();
                    model2_2.setKey("Property Types(Total)");
                    String mPropertyTypeLabel = "";

                    JSONArray types_total = assessmentObject.getJSONArray("types_total");
                    JSONObject objectType = types_total.getJSONObject(0);
                    mPropertyTypeLabel = objectType.optString("label");
                    LogUtils.showErrorLog("Types ", " Types  types_total " + mPropertyTypeLabel);
                    model2_2.setValue("" + mPropertyTypeLabel);
                    listAssessment.add(model2_2);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/


                try {
                    String mLabel = "";
                    DataModel model2 = new DataModel();
                    model2.setKey("Habitable Floors");
                    JSONArray type = assessmentObject.getJSONArray("types");

                    try {
                        if (type.length() == 1) {
                            JSONObject objectType0 = type.getJSONObject(0);
                            mLabel = objectType0.optString("label");
                            LogUtils.showErrorLog("Types ", " Types  types_total " + mLabel);
                        } else if (type.length() == 2) {
                            JSONObject objectType0 = type.getJSONObject(0);
                            JSONObject objectType1 = type.getJSONObject(1);
                            mLabel = objectType0.optString("label") + "," + objectType1.optString("label");

                            LogUtils.showErrorLog("Types ", " Types  types_total " + mLabel);
                        } else if (type.length() == 3) {
                            JSONObject objectType0 = type.getJSONObject(0);
                            JSONObject objectType1 = type.getJSONObject(1);
                            JSONObject objectType2 = type.getJSONObject(2);
                            mLabel = objectType0.optString("label") + "," +
                                    objectType1.optString("label") + "," +
                                    objectType2.optString("label");
                            LogUtils.showErrorLog("Types ", " Types  types_total " + mLabel);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    model2.setValue(mLabel);
                    listAssessment.add(model2);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    DataModel model3 = new DataModel();
                    model3.setKey("Wall Materials");
                    model3.setValue(assessmentModel.getWallMaterial().getLabel());
                    listAssessment.add(model3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                try {
                    DataModel model4 = new DataModel();
                    model4.setKey("Roof Type");
                    model4.setValue(assessmentModel.getRoofMaterial().getLabel());
                    listAssessment.add(model4);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    listAssessment.add(new DataModel("window_type", dataItem.getWindowType().getLabel()));
                } catch (Exception ex) {
                    listAssessment.add(new DataModel("Window_type", ""));
                    ex.printStackTrace();
                }
                try {
                    listAssessment.add(new DataModel("sanitation", dataItem.getSanitationType().getLabel() + ""));
                } catch (Exception ex) {
                    listAssessment.add(new DataModel("sanitation", ""));
                    ex.printStackTrace();
                }


                try {

                    String mStrFinal = "";
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < assessmentModel.getValuesAdded().size(); i++) {
                        try {
                            list.add(assessmentModel.getValuesAdded().get(i).getLabel());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    mStrFinal = getAppendListDataWithSpacialCharacter(list, ",");

                    DataModel model6 = new DataModel();
                    model6.setKey("Value Added Assessment Parameters");
                    model6.setValue("" + mStrFinal);
                    listAssessment.add(model6);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {

                    DataModel model7 = new DataModel();
                    model7.setKey("Property Use");
                    model7.setValue(String.valueOf(assessmentModel.getPropertyUse().getLabel()));
                    listAssessment.add(model7);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                try {
                    DataModel model8 = new DataModel();
                    model8.setKey("Property Zone");
                    model8.setValue(String.valueOf(assessmentModel.getZone().getLabel()));
                    listAssessment.add(model8);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                try {
                    String GatedCommStr = "No";
                    String GatedCommunity = ((assessmentModel.getGatedCommunity() == null) ? "No" : assessmentModel.getGatedCommunity());
                    if (GatedCommunity.equalsIgnoreCase("0")) {
                        GatedCommStr = "No";

                    } else if (GatedCommunity.equalsIgnoreCase("No") || GatedCommunity.equalsIgnoreCase("")) {
                        GatedCommStr = "No";
                    } else {
                        GatedCommStr = "Yes";
                    }

                    DataModel model10 = new DataModel();
                    model10.setKey("Gated Community");
                    model10.setValue(GatedCommStr);
                    listAssessment.add(model10);
                } catch (Exception ex) {
                    DataModel model10 = new DataModel();
                    ex.printStackTrace();
                    model10.setKey("Gated Community");
                    model10.setValue("No");
                    listAssessment.add(model10);
                }


                try {
                    String Swimming = "";

                    LogUtils.showErrorLog("Swimming Pool", "assessmentModel  ");

                    Swimming = ((assessmentModel.getSwimming().getLabel() == null) ? "" : assessmentModel.getSwimming().getLabel());

                    DataModel model11 = new DataModel();
                    model11.setKey("Swimming Pool");
                    model11.setValue(Swimming);
                    listAssessment.add(model11);


                    LogUtils.showErrorLog("Swimming Pool", "Swimming Pool  " + Swimming);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    DataModel model11 = new DataModel();
                    model11.setKey("Swimming Pool");
                    model11.setValue("No");
                    listAssessment.add(model11);
                }


              /*  try {
                    String PropertyDimension = ((assessmentModel.getDimension().getLabel() == null) ? "" : "" + assessmentModel.getDimension().getLabel() + " Sq. Meters");


                    DataModel model5 = new DataModel();
                    model5.setKey("Property Dimension");
                    model5.setValue(PropertyDimension);
                    listAssessment.add(model5);


                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                try {
                    String NoOfShop = ((assessmentModel.getNoOfShop() == null) ? "" : "" + assessmentModel.getNoOfShop());


                    if (NoOfShop.trim().length() != 0) {
                        DataModel model18 = new DataModel();
                        model18.setKey("Number Of Shops");
                        model18.setValue(NoOfShop);
                        listAssessment.add(model18);
                    }
                    LogUtils.showErrorLog("test NoOfShop", "NoOfShop " + NoOfShop);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {

                    String NoOfMast = ((assessmentModel.getNoOfMast() == null) ? "" : "" + assessmentModel.getNoOfMast());

                    DataModel model18 = new DataModel();
                    if (NoOfMast.trim().length() != 0) {
                        model18.setKey("Number Of Mast");
                        model18.setValue(String.valueOf(NoOfMast));
                        listAssessment.add(model18);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {

                    String NoOfCompoundHouse = ((assessmentModel.getNoOfCompoundHouse() == null) ? "" : "" + assessmentModel.getNoOfCompoundHouse());

                    if (NoOfCompoundHouse.trim().length() != 0) {
                        DataModel model18 = new DataModel();
                        model18.setKey("Number Of Compound House");
                        model18.setValue(String.valueOf(NoOfCompoundHouse));
                        listAssessment.add(model18);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {

                    String CompoundName = ((assessmentModel.getCompoundName() == null) ? "" : "" + assessmentModel.getCompoundName());
                    if (CompoundName.trim().length() != 0) {
                        DataModel model18 = new DataModel();
                        model18.setKey("Compound Name");
                        model18.setValue(String.valueOf(CompoundName));
                        listAssessment.add(model18);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }*/


                try {
                    String PropertyRateWithoutGst = ((assessmentModel.getPropertyRateWithoutGst() == null) ? "" : "Le " + StringUtils.AmountWithComma(StringUtils.roundStringValue(assessmentModel.getPropertyRateWithoutGst())));

                    DataModel model9 = new DataModel();
                    model9.setKey("Assessed Value");
                    model9.setValue(PropertyRateWithoutGst);
                    listAssessment.add(model9);
                    LogUtils.showErrorLog("Calculated Property Rate ", "Calculated Property Rate " + PropertyRateWithoutGst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //  listAssessment.add(new DataModel("Window type", dataItem.getWindowTypePercentage() + "%"));
              /*  listAssessment.add(new DataModel("pensioner_discount", dataItem.getPensionerDiscount().equalsIgnoreCase("1") ? "Yes" : "No"));
                listAssessment.add(new DataModel("disability_discount", dataItem.getDisabilityDiscount().equalsIgnoreCase("1") ? "Yes" : "No"));
             */


                adapterAssessment.notifyDataSetChanged();
                // Assessments //

                LogUtils.printf("I am img asment 1: " + assessmentModel.getOriginalOne());
                if (assessmentModel.getOriginalOne() != null) {
                    listAssessmentImg.add(assessmentModel.getOriginalOne());
                }
                LogUtils.printf("I am img asment 2: " + assessmentModel.getOriginalTwo());

                if (assessmentModel.getOriginalTwo() != null) {
                    listAssessmentImg.add(assessmentModel.getOriginalTwo());
                }


                // FIXME: 19-09-2021


                adapterAssessmentImg.notifyDataSetChanged();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initializeListeners() {

        toolbar_iv_home.setOnClickListener(this);
        toolbar_iv_search.setOnClickListener(this);

        btn_edit_landlord.setOnClickListener(this);


        activitySearchDetails_tv_rate_payable.setOnClickListener(this);
        activitySearchDetails_tv_assessment_history.setOnClickListener(this);
        activitySearchDetails_tv_property_images.setOnClickListener(this);
        activitySearchDetails_tv_landlord_details.setOnClickListener(this);
        activitySearchDetails_tv_assessment_details.setOnClickListener(this);
        activitySearchDetails_tv_property_details.setOnClickListener(this);
        activitySearchDetails_tv_occupancy_details.setOnClickListener(this);
        activitySearchDetails_tv_geo_registry_details.setOnClickListener(this);
        activitySearchDetails_tv_payment.setOnClickListener(this);
        activitySearchDetails_tv_cashier_receipt.setOnClickListener(this);
        activitySearchDetails_tv_pensioner_receipt.setOnClickListener(this);
        activitySearchDetails_tv_disability_receipt.setOnClickListener(this);
        activitySearchDetails_tv_councillor_adjustment.setOnClickListener(this);
        activitySearchDetails_tv_council_discount.setOnClickListener(this);
        activitySearchDetails_tv_government_policy.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }


    private void showLogoutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        // Set a title for alert dialog
        builder.setTitle("Logout?");

        // Ask the final question
        builder.setMessage("Are you sure you want to logout?");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
                Intent in = new Intent(mContext, ActivityCashierLogin.class);
                startActivity(in);
                finish();
            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }

    boolean isIdRequired = false;
    boolean isAddressRequired = false;

    public void initLandlordDialog(SearchLandlordModel searchResponseModel) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.dialog_edit_landlord_cashier, null);
        dialogLandlord = new AlertDialog.Builder(this).create();
        dialogLandlord.setView(deleteDialogView);

        WindowManager.LayoutParams params = dialogLandlord.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialogLandlord.getWindow().setAttributes(params);
        dialogLandlord.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        EditText edt_landlord_first_name = deleteDialogView.findViewById(R.id.edt_landlord_first_name);
        EditText edt_landlord_middle_name = deleteDialogView.findViewById(R.id.edt_landlord_middle_name);
        EditText edt_landlord_surname = deleteDialogView.findViewById(R.id.edt_landlord_surname);
        EditText edt_landlord_street_number = deleteDialogView.findViewById(R.id.edt_landlord_street_number);
        EditText edt_landlord_old_street_number = deleteDialogView.findViewById(R.id.edt_landlord_old_street_number);
        EditText edt_landlord_street_name = deleteDialogView.findViewById(R.id.edt_landlord_street_name);
        EditText edt_landlord_email = deleteDialogView.findViewById(R.id.edt_landlord_email);
        EditText edt_landlord_mobile_1 = deleteDialogView.findViewById(R.id.edt_landlord_mobile_1);
        Button btn_save_landlord_info = deleteDialogView.findViewById(R.id.btn_save_landlord_info);
        img_verification_document = deleteDialogView.findViewById(R.id.img_verification_document);
        img_address_proof = deleteDialogView.findViewById(R.id.img_address_proof);
        edt_note_address_proof = deleteDialogView.findViewById(R.id.edt_note_address_proof);
        edt_note_id_proof = deleteDialogView.findViewById(R.id.edt_note_id_proof);

        lin_id_proof = deleteDialogView.findViewById(R.id.lin_id_proof);
        lin_address_proof = deleteDialogView.findViewById(R.id.lin_address_proof);
        //  img_address_proof = deleteDialogView.findViewById(R.id.img_address_proof);


        // FIXME: 20-09-2021
        edt_landlord_first_name.setText(searchResponseModel.getFirstName());
        edt_landlord_middle_name.setText(searchResponseModel.getMiddleName());
        edt_landlord_surname.setText(searchResponseModel.getSurname());
        edt_landlord_street_number.setText(searchResponseModel.getStreet_numbernew());
        edt_landlord_street_name.setText(searchResponseModel.getStreetName());
        edt_landlord_email.setText("" + searchResponseModel.getEmail());
        edt_landlord_mobile_1.setText(searchResponseModel.getMobile1());

        /// newly added
        edt_landlord_old_street_number.setText(searchResponseModel.getStreetNumber());


        dialogLandlord.show();

        img_verification_document.setOnClickListener(v -> OpenCamera(CODE_VERIFICATION_DOCUMENT));
        img_address_proof.setOnClickListener(v -> OpenCamera(CODE_ADDRESS_PROOF));


        edt_landlord_first_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (searchResponseModel.getFirstName() == null || searchResponseModel.getFirstName().isEmpty()) {
                    lin_id_proof.setVisibility(View.VISIBLE);
                    isIdRequired = true;
                } else if (!searchResponseModel.getFirstName().equalsIgnoreCase(s.toString())) {
                    lin_id_proof.setVisibility(View.VISIBLE);
                    isIdRequired = true;
                } else {
                    lin_id_proof.setVisibility(View.GONE);
                    isIdRequired = false;
                }
            }
        });
        edt_landlord_middle_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (searchResponseModel.getMiddleName() == null || searchResponseModel.getMiddleName().isEmpty()) {
                    lin_id_proof.setVisibility(View.VISIBLE);
                    isIdRequired = true;
                } else if (!searchResponseModel.getMiddleName().equalsIgnoreCase(s.toString())) {
                    lin_id_proof.setVisibility(View.VISIBLE);
                    isIdRequired = true;
                } else {
                    lin_id_proof.setVisibility(View.GONE);
                    isIdRequired = false;
                }
            }
        });
        edt_landlord_surname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (searchResponseModel.getSurname() == null || searchResponseModel.getSurname().isEmpty()) {
                    lin_id_proof.setVisibility(View.VISIBLE);
                    isIdRequired = true;
                } else if (!searchResponseModel.getSurname().equalsIgnoreCase(s.toString())) {
                    lin_id_proof.setVisibility(View.VISIBLE);
                    isIdRequired = true;
                } else {
                    lin_id_proof.setVisibility(View.GONE);
                    isIdRequired = false;
                }
            }
        });


        edt_landlord_street_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (searchResponseModel.getStreet_numbernew() == null || searchResponseModel.getStreet_numbernew().isEmpty()) {
                    lin_address_proof.setVisibility(View.VISIBLE);
                    isAddressRequired = true;
                } else if (!searchResponseModel.getStreet_numbernew().equalsIgnoreCase(s.toString())) {
                    lin_address_proof.setVisibility(View.VISIBLE);
                    isAddressRequired = true;
                } else {
                    lin_address_proof.setVisibility(View.GONE);
                    isAddressRequired = false;

                }
            }
        });

        edt_landlord_street_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (searchResponseModel.getStreetName() == null || searchResponseModel.getStreetName().isEmpty()) {
                    lin_address_proof.setVisibility(View.VISIBLE);
                    isAddressRequired = true;
                } else if (!searchResponseModel.getStreetName().equalsIgnoreCase(s.toString())) {
                    lin_address_proof.setVisibility(View.VISIBLE);
                    isAddressRequired = true;
                } else {
                    lin_address_proof.setVisibility(View.GONE);
                    isAddressRequired = false;

                }
            }
        });
        ///// newly added
        edt_landlord_old_street_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (searchResponseModel.getStreetNumber() == null || searchResponseModel.getStreetNumber().isEmpty()) {
                    if (s.length() > 0) {
                        old_street_flag = "1";
                        lin_address_proof.setVisibility(View.VISIBLE);
                        isAddressRequired = true;
                    }
                } else if (!searchResponseModel.getStreetNumber().equalsIgnoreCase(s.toString())) {
                    {
                        old_street_flag = "1";
                        lin_address_proof.setVisibility(View.VISIBLE);
                        isAddressRequired = true;
                    }
                } else {
                    old_street_flag = "0";
                    lin_address_proof.setVisibility(View.GONE);
                    isAddressRequired = false;
                }
            }
        });

        btn_save_landlord_info.setOnClickListener(v -> {
            HashMap<String, String> req_params = new HashMap<>();

            // FIXME: 20-09-2021
            req_params.put("landlord_first_name", "" + edt_landlord_first_name.getText().toString());
            req_params.put("landlord_middle_name", "" + edt_landlord_middle_name.getText().toString());
            req_params.put("landlord_surname", "" + edt_landlord_surname.getText().toString());
            req_params.put("landlord_street_number", "" + edt_landlord_street_number.getText().toString());
            req_params.put("landlord_street_name", "" + edt_landlord_street_name.getText().toString());
            req_params.put("landlord_email", "" + edt_landlord_email.getText().toString());
            req_params.put("landlord_mobile_1", "" + edt_landlord_mobile_1.getText().toString());
            req_params.put("old_street_number", "" + edt_landlord_old_street_number.getText().toString());
            req_params.put("old_street_flag", "" + old_street_flag);
            req_params.put("requested_by", "cashier");


          /*  adapterLandload.updateItems(new DataModel("Email Address", edt_landlord_email.getText().toString()));
            adapterLandload.updateItems(new DataModel("First Name", edt_landlord_first_name.getText().toString()));
            adapterLandload.updateItems(new DataModel("Middle Name", edt_landlord_middle_name.getText().toString()));
            adapterLandload.updateItems(new DataModel("Surname", edt_landlord_surname.getText().toString()));
            adapterLandload.updateItems(new DataModel("Street Number", edt_landlord_street_number.getText().toString()));
            adapterLandload.updateItems(new DataModel("Street Name", edt_landlord_street_name.getText().toString()));
            adapterLandload.updateItems(new DataModel("Mobile Number 1", edt_landlord_mobile_1.getText().toString()));
            adapterLandload.updateItems(new DataModel("Old Street Name", edt_landlord_old_street_number.getText().toString()));*/


            String finalURL = URL_CASHIER_LANDLORD_EDIT_PROFILE + getIntent().getStringExtra("property_id");

            if (isIdRequired && isAddressRequired) {
                if (file_verification_document != null && file_address_proof != null) {

                    apiRequest.callFileUpload(
                            finalURL,
                            req_params,
                            new PART("verification_document", file_verification_document),
                            new PART("address_document", file_address_proof),
                            PrefUtil.getAuthType(mContext) + " " + PrefUtil.getToken(mContext),
                            "upload_data");

                } else
                    Toast.makeText(ActivityMainDetails.this, "Image shouldn't be empty", Toast.LENGTH_SHORT).show();
            } else if (isIdRequired) {
                if (file_verification_document != null) {

                    apiRequest.callFileUpload(
                            finalURL,
                            req_params,
                            new PART("verification_document", file_verification_document),
                            PrefUtil.getAuthType(mContext) + " " + PrefUtil.getToken(mContext),
                            "upload_data");

                } else
                    Toast.makeText(ActivityMainDetails.this, "Image shouldn't be empty", Toast.LENGTH_SHORT).show();
            } else if (isAddressRequired) {
                if (file_address_proof != null) {

                    apiRequest.callFileUpload(
                            finalURL,
                            req_params,
                            new PART("address_document", file_address_proof),
                            PrefUtil.getAuthType(mContext) + " " + PrefUtil.getToken(mContext),
                            "upload_data");

                } else
                    Toast.makeText(ActivityMainDetails.this, "Image shouldn't be empty", Toast.LENGTH_SHORT).show();
            } else {
                apiRequest.callPostFormData(
                        finalURL,
                        req_params,
                        PrefUtil.getAuthType(mContext) + " " + PrefUtil.getToken(mContext),
                        "upload_data"
                );
            }


        });

    }

    // FIXME: 19-09-2021
    private void OpenCamera(int code) {
        ImagePicker.Companion.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .cameraOnly()
                .start(code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == CODE_VERIFICATION_DOCUMENT) {
                img_verification_document.setImageURI(Uri.fromFile(ImagePicker.Companion.getFile(data)));
                file_verification_document = ImagePicker.Companion.getFile(data);
            } else if (requestCode == CODE_ADDRESS_PROOF) {
                img_address_proof.setImageURI(Uri.fromFile(ImagePicker.Companion.getFile(data)));
                file_address_proof = ImagePicker.Companion.getFile(data);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.toolbar_iv_home:
                onBackPressed();
                break;
            case R.id.toolbar_iv_search:
                //  intent = new Intent(mContext, ActDe)
                break;
            case R.id.btn_edit_landlord:
                initLandlordDialog(landlordModel);
                //   if (dialogLandlord != null) dialogLandlord.show();
                break;


            case R.id.activitySearchDetails_tv_assessment_history:
                if (expand_assessment_history) {
                    include_assessment_history.setVisibility(View.GONE);
                    activitySearchDetails_tv_assessment_history.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_assessment_history.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_assessment_history.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand_assessment_history = false;

                } else {
                    include_assessment_history.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_assessment_history.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_assessment_history.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_assessment_history.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand_assessment_history = true;

                }
                break;

            case R.id.activitySearchDetails_tv_rate_payable:
                if (expand__rate_payable) {
                    include_tv_rate_payable.setVisibility(View.GONE);
                    activitySearchDetails_tv_rate_payable.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_rate_payable.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_rate_payable.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand__rate_payable = false;

                } else {
                    include_tv_rate_payable.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_rate_payable.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_rate_payable.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_rate_payable.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand__rate_payable = true;

                }
                break;

            case R.id.activitySearchDetails_tv_property_images:
                if (expand_property_image) {
                    include_property_images.setVisibility(View.GONE);
                    activitySearchDetails_tv_property_images.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_property_images.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_property_images.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand_property_image = false;

                } else {
                    include_property_images.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_property_images.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_property_images.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_property_images.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand_property_image = true;

                }
                break;


            case R.id.activitySearchDetails_tv_landlord_details:
                if (expand_landlord_details) {
                    include_landlord_details.setVisibility(View.GONE);
                    activitySearchDetails_tv_landlord_details.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_landlord_details.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_landlord_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand_landlord_details = false;

                } else {
                    include_landlord_details.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_landlord_details.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_landlord_details.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_landlord_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand_landlord_details = true;

                }
                break;
            case R.id.activitySearchDetails_tv_assessment_details:
                if (expand_assessment_details) {
                    include_assessment_details.setVisibility(View.GONE);
                    activitySearchDetails_tv_assessment_details.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_assessment_details.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_assessment_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand_assessment_details = false;

                } else {
                    include_assessment_details.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_assessment_details.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_assessment_details.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_assessment_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand_assessment_details = true;

                }

                break;
            case R.id.activitySearchDetails_tv_property_details:

                if (expand_property_details) {
                    include_property_details.setVisibility(View.GONE);
                    activitySearchDetails_tv_property_details.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_property_details.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_property_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand_property_details = false;

                } else {
                    include_property_details.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_property_details.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_property_details.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_property_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand_property_details = true;
                }
                break;
            case R.id.activitySearchDetails_tv_occupancy_details:

                if (expand_occupancy_details) {
                    include_occupancy_details.setVisibility(View.GONE);
                    activitySearchDetails_tv_occupancy_details.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_occupancy_details.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_occupancy_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand_occupancy_details = false;

                } else {
                    include_occupancy_details.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_occupancy_details.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_occupancy_details.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_occupancy_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand_occupancy_details = true;
                }
                break;
            case R.id.activitySearchDetails_tv_geo_registry_details:
                if (expand_geo_registry_details) {
                    include_geo_registry_details.setVisibility(View.GONE);
                    activitySearchDetails_tv_geo_registry_details.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                    activitySearchDetails_tv_geo_registry_details.setTextColor(getResources().getColor(R.color.colorBlack));
                    activitySearchDetails_tv_geo_registry_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                    expand_geo_registry_details = false;

                } else {
                    include_geo_registry_details.setVisibility(View.VISIBLE);
                    activitySearchDetails_tv_geo_registry_details.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                    activitySearchDetails_tv_geo_registry_details.setTextColor(getResources().getColor(R.color.colorWhite));
                    activitySearchDetails_tv_geo_registry_details.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);
                    expand_geo_registry_details = true;
                }
                break;
            case R.id.activitySearchDetails_tv_payment:

                try {
                    if (expand_payment_trans_details) {
                        include_payment_details.setVisibility(View.GONE);
                        activitySearchDetails_tv_payment.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                        activitySearchDetails_tv_payment.setTextColor(getResources().getColor(R.color.colorBlack));
                        activitySearchDetails_tv_payment.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                        expand_payment_trans_details = false;

                    } else {
                        include_payment_details.setVisibility(View.VISIBLE);
                        activitySearchDetails_tv_payment.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                        activitySearchDetails_tv_payment.setTextColor(getResources().getColor(R.color.colorWhite));
                        activitySearchDetails_tv_payment.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);

                        expand_payment_trans_details = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;


            // FIXME: 24-09-2021 


            case R.id.activitySearchDetails_tv_cashier_receipt:

                try {
                    if (expand_cashier_receipt_details) {
                        include_tv_cashier_receipt.setVisibility(View.GONE);
                        activitySearchDetails_tv_cashier_receipt.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                        activitySearchDetails_tv_cashier_receipt.setTextColor(getResources().getColor(R.color.colorBlack));
                        activitySearchDetails_tv_cashier_receipt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                        expand_cashier_receipt_details = false;

                    } else {
                        include_tv_cashier_receipt.setVisibility(View.VISIBLE);
                        activitySearchDetails_tv_cashier_receipt.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                        activitySearchDetails_tv_cashier_receipt.setTextColor(getResources().getColor(R.color.colorWhite));
                        activitySearchDetails_tv_cashier_receipt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);

                        expand_cashier_receipt_details = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;


            case R.id.activitySearchDetails_tv_pensioner_receipt:

                try {
                    if (expand_pensioner_receipt_details) {
                        include_tv_pensioner_receipt.setVisibility(View.GONE);
                        activitySearchDetails_tv_pensioner_receipt.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                        activitySearchDetails_tv_pensioner_receipt.setTextColor(getResources().getColor(R.color.colorBlack));
                        activitySearchDetails_tv_pensioner_receipt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                        expand_pensioner_receipt_details = false;

                    } else {
                        include_tv_pensioner_receipt.setVisibility(View.VISIBLE);
                        activitySearchDetails_tv_pensioner_receipt.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                        activitySearchDetails_tv_pensioner_receipt.setTextColor(getResources().getColor(R.color.colorWhite));
                        activitySearchDetails_tv_pensioner_receipt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);

                        expand_pensioner_receipt_details = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;

            case R.id.activitySearchDetails_tv_disability_receipt:

                try {
                    if (expand_disability_receipt_details) {
                        include_tv_disability_receipt.setVisibility(View.GONE);
                        activitySearchDetails_tv_disability_receipt.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                        activitySearchDetails_tv_disability_receipt.setTextColor(getResources().getColor(R.color.colorBlack));
                        activitySearchDetails_tv_disability_receipt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                        expand_disability_receipt_details = false;

                    } else {
                        include_tv_disability_receipt.setVisibility(View.VISIBLE);
                        activitySearchDetails_tv_disability_receipt.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                        activitySearchDetails_tv_disability_receipt.setTextColor(getResources().getColor(R.color.colorWhite));
                        activitySearchDetails_tv_disability_receipt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);

                        expand_disability_receipt_details = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;

            case R.id.activitySearchDetails_tv_councillor_adjustment:

                try {
                    if (expand_councillor_adjustment) {
                        include_councillor_adjustment.setVisibility(View.GONE);
                        activitySearchDetails_tv_councillor_adjustment.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                        activitySearchDetails_tv_councillor_adjustment.setTextColor(getResources().getColor(R.color.colorBlack));
                        activitySearchDetails_tv_councillor_adjustment.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                        expand_councillor_adjustment = false;

                    } else {
                        include_councillor_adjustment.setVisibility(View.VISIBLE);
                        activitySearchDetails_tv_councillor_adjustment.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                        activitySearchDetails_tv_councillor_adjustment.setTextColor(getResources().getColor(R.color.colorWhite));
                        activitySearchDetails_tv_councillor_adjustment.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);

                        expand_councillor_adjustment = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;
            case R.id.activitySearchDetails_tv_council_discount:

                try {
                    if (expand_council_discount) {
                        include_tv_council_discount.setVisibility(View.GONE);
                        activitySearchDetails_tv_council_discount.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                        activitySearchDetails_tv_council_discount.setTextColor(getResources().getColor(R.color.colorBlack));
                        activitySearchDetails_tv_council_discount.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                        expand_council_discount = false;

                    } else {
                        include_tv_council_discount.setVisibility(View.VISIBLE);
                        activitySearchDetails_tv_council_discount.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                        activitySearchDetails_tv_council_discount.setTextColor(getResources().getColor(R.color.colorWhite));
                        activitySearchDetails_tv_council_discount.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);

                        expand_council_discount = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;
            case R.id.activitySearchDetails_tv_government_policy:

                try {
                    if (expand_government_policy) {
                        include_tv_government_policy.setVisibility(View.GONE);
                        activitySearchDetails_tv_government_policy.setBackground(getDrawable(R.drawable.square_corner_solid_grey));
                        activitySearchDetails_tv_government_policy.setTextColor(getResources().getColor(R.color.colorBlack));
                        activitySearchDetails_tv_government_policy.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24), null);
                        expand_government_policy = false;

                    } else {
                        include_tv_government_policy.setVisibility(View.VISIBLE);
                        activitySearchDetails_tv_government_policy.setBackground(getDrawable(R.drawable.square_corner_solid_blue));
                        activitySearchDetails_tv_government_policy.setTextColor(getResources().getColor(R.color.colorWhite));
                        activitySearchDetails_tv_government_policy.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24), null);

                        expand_government_policy = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;


        }
        if (intent != null) {
            startActivity(intent);
        }


    }

    @Override
    public void OnCallBackSuccess(String tag, String response) {

        if (tag.equalsIgnoreCase("upload_data")) {

            file_verification_document = null;
            file_address_proof = null;

            if (dialogLandlord != null && dialogLandlord.isShowing())
                dialogLandlord.dismiss();
            lin_address_proof.setVisibility(View.GONE);
            lin_id_proof.setVisibility(View.GONE);
            img_address_proof.setImageDrawable(getDrawable(R.drawable.placeholder));
            img_verification_document.setImageDrawable(getDrawable(R.drawable.placeholder));

            try {
                JSONObject object = new JSONObject(response);
                Toast.makeText(this, "" + object.getString("status") + " Please wait for approval", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}
