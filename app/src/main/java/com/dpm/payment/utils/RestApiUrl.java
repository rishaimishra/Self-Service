package com.dpm.payment.utils;

/*import static com.payment.BuildConfig.BASE_URL;
import static com.payment.BuildConfig.BASE_URL_MAIN;
import static com.payment.BuildConfig.BASE_URL_TERMS;*/

import androidx.core.content.ContextCompat;

//import com.payment.BuildConfig;

public interface RestApiUrl {

    public static String BASE_URL ="http://3.134.197.245/apiv2";
    public static String BASE_URL_MAIN ="http://3.134.197.245/apiv2";
    public static String BASE_URL_TERMS ="https:l";
    public static String IMAGE_URL_TERMS ="https://3.134.197.245/";

    String URL_CASHIER_LOGIN = BASE_URL + "/admin/login";
    String URL_CASHIER_SEARCH_PROPERTY= BASE_URL + "/admin/search-property";
    String URL_CASHIER_SAVE_PAYMENT= BASE_URL + "/admin/payment/";
    String URL_CASHIER_LANDLORD_EDIT_PROFILE= BASE_URL + "/admin/landlord/";

    String URL_LANDLORD_EDIT_PROFILE= BASE_URL + "/landlord/landlord/";
    String URL_LANDLORD_PROPERTY_APPROVE= BASE_URL + "/landlord/propertyapprove/";

// user login
    String URL_LANDLORD_LOGIN = BASE_URL + "/landlord/login";
    String URL_LANDLORD_OTP_VERIFY = BASE_URL + "/landlord/otp";
    String URL_LANDLORD_PROPERTY_LIST = BASE_URL + "/landlord/search-property";
    String URL_LANDLORD_PAYMENT = BASE_URL_MAIN + "/paypal?";

    //CEP Council
    String URL_CEP_DISTRICT_DETAILS = BASE_URL + "/get/district";
    String URL_CEP_SEARCH_DISTRICT = BASE_URL + "/area-search/council";
    String URL_CEP_AREA = BASE_URL + "/area-list";

    String URL_GUEST_USER_LOGIN = BASE_URL + "/guest-user/login";
    String URL_GUEST_USER_REGISTER = BASE_URL + "/guest-user/register";
   // http://mrms.sigmaventuressl.com/apiv2/get-recipts/
    String URL_LANDLORD_RECEIPT = BASE_URL + "/get-recipts/";
//http://3.134.197.245/apiv2/landlord/payment/receipt/41839/2024
    String URL_DEMAND_NOTE = BASE_URL + "/landlord/payment/receipt/";
    String URL_OCCUPANCY_TYPE =BASE_URL+"/get-occupency-types";
    String URL_EDIT_OCCUPANCY = BASE_URL+"/edit-occupency";



    String GET_FORM_RESOURCES = BASE_URL+"/get_form_resources";
    String ADD_COMPLAIN = BASE_URL+"/add_complain";
}
