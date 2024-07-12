package com.dpm.payment;

import static com.dpm.payment.utils.StringUtils.getAppendListDataWithSpacialCharacter;

import com.dpm.payment.adapters.DataViewAdapter;
import com.dpm.payment.models.DataModel;
import com.dpm.payment.models.SearchLandlordModel;
import com.dpm.payment.models.SearchOccupancyModel;
import com.dpm.payment.models.SearchPropertyModel;
import com.dpm.payment.utils.CommonUtils;
import com.dpm.payment.utils.LogUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TabDataInitializer {
    public static void setLandloardData(JSONObject landloadObject, JSONObject mMainObject,List<DataModel> listLandload, DataViewAdapter adapter) {

        try {
            if (landloadObject != null) {

                SearchLandlordModel  landlordModel = (SearchLandlordModel) CommonUtils.getObjectFromJson(landloadObject.toString().trim(), SearchLandlordModel.class);


             /*   try {
                    if (landlordModel.getImage() != null) {
                        Picasso.get()
                                .load("" + landlordModel.getSmallPreview())
                                .placeholder(R.drawable.ic_my_profile)
                                .error(R.drawable.ic_my_profile)
                                .into(ivProfilePicLandload);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

              /*  try {

                    DataModel model0 = new DataModel();
                    model0.setKey("Property ID");
                    model0.setValue("" + landlordModel.getPropertyId());
                    listLandload.add(model0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }*/


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

                        try {

                            String tin = ((mMainObject.getJSONObject("landlord").getJSONObject("property").optString("organization_tin") == null) ? "" : "" + mMainObject.getJSONObject("landlord").getJSONObject("property").optString("organization_tin"));

                            DataModel modeltin = new DataModel();
                            modeltin.setKey("Tin");
                            modeltin.setValue("" + tin);
                            listLandload.add(modeltin);


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                        if (OrganizationType.equalsIgnoreCase("School")) {
                            listLandload.add(new DataModel("School Type", mMainObject.optString("organization_school_type")));

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }



                  /*  try {

                        String OrganizationType = ((mMainObject.optString("organization_addresss") == null) ? "" : "" + mMainObject.optString("organization_addresss"));

                        DataModel model110 = new DataModel();
                        model110.setKey("Organization Address");
                        model110.setValue("" + OrganizationType);
                        listLandload.add(model110);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }*/


                } else {

                    try {

                        DataModel model0 = new DataModel();
                        model0.setKey("Title");
                        model0.setValue("" + landlordModel.getTitles().getLabel());
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
                        if (landlordModel.getMiddleName() == null ||
                                landlordModel.getMiddleName().trim().equals("")) {
                            model2.setValue("--");
                        } else
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
                        DataModel model4 = new DataModel();
                        model4.setKey("Gender");
                        model4.setValue(landlordModel.getSex().toUpperCase());
                        listLandload.add(model4);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try {

                        String OrganizationType = ((mMainObject.getJSONObject("landlord").optString("nin_number") == null) ? "" : "" + mMainObject.getJSONObject("landlord").optString("nin_number"));

                        DataModel model19 = new DataModel();
                        model19.setKey("Nin");
                        model19.setValue("" + OrganizationType);
                        listLandload.add(model19);


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
                    model5.setValue(landlordModel.getStreetNumber());
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


                try {
                    DataModel model3 = new DataModel();
                    model3.setKey("Additional Address");
                    model3.setValue(mMainObject.getJSONObject("landlord").optString("additional_address_id"));
                    listLandload.add(model3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    DataModel model3 = new DataModel();
                    model3.setKey("Area");
                    model3.setValue(mMainObject.getJSONObject("landlord").optString("property_area"));
                    listLandload.add(model3);
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

                try {
                    String mEmail = ((landlordModel.getEmail() == null) ? "" : "" + landlordModel.getEmail());
                    DataModel model18 = new DataModel();
                    model18.setKey("Email Address");
                    model18.setValue("" + mEmail);
                    listLandload.add(model18);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

             /*   try {
                    String mEmail = ((landlordModel.getEmail() == null) ? "" : "" + landlordModel.getEmail());
                    DataModel model18 = new DataModel();
                    model18.setKey("Email");
                    model18.setValue("" + mEmail);
                    listLandload.add(model18);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }*/

                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setPropertyData(JSONObject propertyObject,List<DataModel> listProperty,DataViewAdapter adapter) {
        try {
            if (propertyObject != null) {

                SearchPropertyModel  propertyModel = (SearchPropertyModel) CommonUtils.getObjectFromJson(propertyObject.toString().trim(), SearchPropertyModel.class);

                try {

                    DataModel model0 = new DataModel();
                    model0.setKey("Property ID");
                    model0.setValue("" + propertyModel.getId());
                    listProperty.add(model0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                /*DataModel model22 = new DataModel();
                model22.setKey("New Street Number");
                model22.setValue(propertyModel.getStreet_numbernew());
                listProperty.add(model22);*/


                DataModel model1 = new DataModel();
                model1.setKey("Street Number");
                model1.setValue(propertyModel.getStreetNumber());
                listProperty.add(model1);


                DataModel model2 = new DataModel();
                model2.setKey("Street Name");
                model2.setValue(propertyModel.getStreetName());
                listProperty.add(model2);

                try {
                    DataModel model3 = new DataModel();
                    model3.setKey("Additional Address");
                    model3.setValue(propertyObject.getJSONObject("landlord").optString("additional_address_id"));
                    listProperty.add(model3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    DataModel model3 = new DataModel();
                    model3.setKey("Area");
                    model3.setValue(propertyObject.getJSONObject("landlord").optString("property_area"));
                    listProperty.add(model3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                DataModel model4 = new DataModel();
                model4.setKey("Section");
                model4.setValue(propertyModel.getSection());
                listProperty.add(model4);


                DataModel model3 = new DataModel();
                model3.setKey("Ward");
                model3.setValue(String.valueOf(propertyModel.getWard()));
                listProperty.add(model3);


                String Constituency = ((propertyModel.getConstituency() == null) ? "" : propertyModel.getConstituency());


                DataModel model12 = new DataModel();
                model12.setKey("Constituency");
                model12.setValue(Constituency);
                listProperty.add(model12);


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


                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String setOccupancyData(JSONObject occupancyObject, JSONObject JSONMainObj,List<DataModel> listOccupancy,DataViewAdapter adapter) {
        String OccupancyType = "";
        try {
            if (occupancyObject != null) {

                SearchOccupancyModel  occupancyModel = (SearchOccupancyModel) CommonUtils.getObjectFromJson(occupancyObject.toString().trim(), SearchOccupancyModel.class);


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

                    OccupancyType = mFinalStr;
                    DataModel model1 = new DataModel();
                    model1.setKey("Occupancy Type");
                    model1.setValue(mFinalStr);
                    listOccupancy.add(model1);
                    LogUtils.showErrorLog("Occupancy Type", "" + occupancyObject.optString("type"));

                    /*TODO adding school type*/
                    // listOccupancy.add(new DataModel("School Type",occupancyObject.isNull("organizational_school_type") ? "": occupancyObject.optString("organizational_school_type")));


                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                if (JSONMainObj.optBoolean("is_organization")) {

                    try {

                        String organization_name = ((JSONMainObj.optString("organization_name") == null) ? "" : "" + JSONMainObj.optString("organization_name"));

                        DataModel model18 = new DataModel();
                        model18.setKey("Organization Name");
                        model18.setValue("" + organization_name);
                        listOccupancy.add(model18);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {

                        String OrganizationType = ((JSONMainObj.optString("organization_type") == null) ? "" : "" + JSONMainObj.optString("organization_type"));

                        DataModel model19 = new DataModel();
                        model19.setKey("Organization Type");
                        model19.setValue("" + OrganizationType);
                        listOccupancy.add(model19);


                        if (OrganizationType.equalsIgnoreCase("School")) {
                            listOccupancy.add(new DataModel("School Type", JSONMainObj.optString("organization_school_type")));

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                  /*  try {

                        String OrganizationType = ((mMainObject.optString("organization_addresss") == null) ? "" : "" + mMainObject.optString("organization_addresss"));

                        DataModel model110 = new DataModel();
                        model110.setKey("Organization Address");
                        model110.setValue("" + OrganizationType);
                        listLandload.add(model110);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }*/


                }
                else {

                    DataModel model22 = new DataModel();
                    model22.setKey("Tenant Title");
                 //   model22.setValue(landlordModel.getTitles().getLabel());
                    model22.setValue(occupancyModel.getTitles().getLabel());

                    listOccupancy.add(model22);


                    DataModel model2 = new DataModel();
                    model2.setKey("Tenant First Name");
                    model2.setValue(occupancyModel.getTenantFirstName());
                    listOccupancy.add(model2);


                    DataModel model3 = new DataModel();
                    model3.setKey("Middle Name");
                    if (occupancyModel.getMiddleName() == null ||
                            occupancyModel.getMiddleName().trim().equals("")) {
                        model3.setValue("--");
                    } else
                        model3.setValue(occupancyModel.getMiddleName());
                    listOccupancy.add(model3);


                    DataModel model4 = new DataModel();
                    model4.setKey("Surname");
                    model4.setValue(occupancyModel.getSurname());
                    listOccupancy.add(model4);

                }




                DataModel model5 = new DataModel();
                model5.setKey("Mobile Number 1");
                model5.setValue(occupancyModel.getMobile1());
                listOccupancy.add(model5);

                DataModel model6 = new DataModel();
                model6.setKey("Mobile Number 2");
                model6.setValue(occupancyModel.getMobile2());
                listOccupancy.add(model6);

                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  OccupancyType;
    }


}
