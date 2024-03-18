package com.dpm.payment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchAssessmentModel {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("property_id")
    @Expose
    private Integer propertyId;
    @SerializedName("property_categories")
    @Expose
    private String propertyCategories;
    @SerializedName("property_wall_materials")
    @Expose
    private String propertyWallMaterials;
    @SerializedName("roofs_materials")
    @Expose
    private String roofsMaterials;
    @SerializedName("property_dimension")
    @Expose
    private String propertyDimension;
    @SerializedName("property_rate_without_gst")
    @Expose
    private String propertyRateWithoutGst;
    @SerializedName("property_gst")
    @Expose
    private String propertyGst;
    @SerializedName("property_rate_with_gst")
    @Expose
    private String propertyRateWithGst;
    @SerializedName("property_use")
    @Expose
    private SearchPropertyUseModel propertyUse;
    @SerializedName("zone")
    @Expose
    private SearchZoneModel zone;
    @SerializedName("no_of_mast")
    @Expose
    private String noOfMast;
    @SerializedName("no_of_shop")
    @Expose
    private String noOfShop;
    @SerializedName("no_of_compound_house")
    @Expose
    private String noOfCompoundHouse;
    @SerializedName("compound_name")
    @Expose
    private String compoundName;
    @SerializedName("gated_community")
    @Expose
    private String gatedCommunity;


    @SerializedName("swimming_id")
    @Expose
    private String swimmingId;
    @SerializedName("assessment_images_2")
    @Expose
    private String assessmentImages2;
    @SerializedName("assessment_images_1")
    @Expose
    private String assessmentImages1;
    @SerializedName("demand_note_delivered_at")
    @Expose
    private String demandNoteDeliveredAt;
    @SerializedName("demand_note_recipient_name")
    @Expose
    private String demandNoteRecipientName;
    @SerializedName("demand_note_recipient_mobile")
    @Expose
    private String demandNoteRecipientMobile;
    @SerializedName("demand_note_recipient_photo")
    @Expose
    private String demandNoteRecipientPhoto;
    @SerializedName("last_printed_at")
    @Expose
    private String lastPrintedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("original_one")
    @Expose
    private String originalOne;
    @SerializedName("small_preview_one")
    @Expose
    private String smallPreviewOne;
    @SerializedName("large_preview_one")
    @Expose
    private String largePreviewOne;
    @SerializedName("original_two")
    @Expose
    private String originalTwo;
    @SerializedName("small_preview_two")
    @Expose
    private String smallPreviewTwo;
    @SerializedName("large_preview_two")
    @Expose
    private String largePreviewTwo;
    @SerializedName("swimming_pool")
    @Expose
    private String swimmingPool;
    @SerializedName("is_demand_note_delivered")
    @Expose
    private Boolean isDemandNoteDelivered;
    @SerializedName("demand_note_recipient_photo_url")
    @Expose
    private String demandNoteRecipientPhotoUrl;
    @SerializedName("current_year_assessment_amount")
    @Expose
    private String currentYearAssessmentAmount;
    @SerializedName("arrear_due")
    @Expose
    private String arrearDue;
    @SerializedName("penalty")
    @Expose
    private String penalty;

    @SerializedName("amount_paid")
    @Expose
    private String amountPaid;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("assessment_year")
    @Expose
    private String assessmentYear;


    @SerializedName("categories")
    @Expose
    private List<SearchCategoryModel> categories = null;



    @SerializedName("types")
    @Expose
    private List<SearchTypeModel> types = null;
    @SerializedName("wall_material")
    @Expose
    private SearchWallMaterialModel wallMaterial;
    @SerializedName("roof_material")
    @Expose
    private SearchRoofMaterialModel roofMaterial;
    @SerializedName("values_added")
    @Expose
    private List<SearchValueAddedModel> valuesAdded = null;
    @SerializedName("dimension")
    @Expose
    private SearchDimensionModel dimension;



    @SerializedName("swimming")
    @Expose
    private AssessmentSwimming swimming;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyCategories() {
        return propertyCategories;
    }

    public void setPropertyCategories(String propertyCategories) {
        this.propertyCategories = propertyCategories;
    }

    public String getPropertyWallMaterials() {
        return propertyWallMaterials;
    }

    public void setPropertyWallMaterials(String propertyWallMaterials) {
        this.propertyWallMaterials = propertyWallMaterials;
    }

    public String getRoofsMaterials() {
        return roofsMaterials;
    }

    public void setRoofsMaterials(String roofsMaterials) {
        this.roofsMaterials = roofsMaterials;
    }

    public String getPropertyDimension() {
        return propertyDimension;
    }

    public void setPropertyDimension(String propertyDimension) {
        this.propertyDimension = propertyDimension;
    }

    public String getPropertyRateWithoutGst() {
        return propertyRateWithoutGst;
    }

    public void setPropertyRateWithoutGst(String propertyRateWithoutGst) {
        this.propertyRateWithoutGst = propertyRateWithoutGst;
    }

    public String getPropertyGst() {
        return propertyGst;
    }

    public void setPropertyGst(String propertyGst) {
        this.propertyGst = propertyGst;
    }

    public String getPropertyRateWithGst() {
        return propertyRateWithGst;
    }

    public void setPropertyRateWithGst(String propertyRateWithGst) {
        this.propertyRateWithGst = propertyRateWithGst;
    }

    public SearchPropertyUseModel getPropertyUse() {
        return propertyUse;
    }

    public void setPropertyUse(SearchPropertyUseModel propertyUse) {
        this.propertyUse = propertyUse;
    }

    public SearchZoneModel getZone() {
        return zone;
    }

    public void setZone(SearchZoneModel zone) {
        this.zone = zone;
    }

    public String getNoOfMast() {
        return noOfMast;
    }

    public void setNoOfMast(String noOfMast) {
        this.noOfMast = noOfMast;
    }

    public String getNoOfShop() {
        return noOfShop;
    }

    public void setNoOfShop(String noOfShop) {
        this.noOfShop = noOfShop;
    }

    public String getNoOfCompoundHouse() {
        return noOfCompoundHouse;
    }

    public void setNoOfCompoundHouse(String noOfCompoundHouse) {
        this.noOfCompoundHouse = noOfCompoundHouse;
    }

    public String getCompoundName() {
        return compoundName;
    }

    public void setCompoundName(String compoundName) {
        this.compoundName = compoundName;
    }

    public String getGatedCommunity() {
        return gatedCommunity;
    }

    public void setGatedCommunity(String gatedCommunity) {
        this.gatedCommunity = gatedCommunity;
    }

    public String getSwimmingId() {
        return swimmingId;
    }

    public void setSwimmingId(String swimmingId) {
        this.swimmingId = swimmingId;
    }

    public String getAssessmentImages2() {
        return assessmentImages2;
    }

    public void setAssessmentImages2(String assessmentImages2) {
        this.assessmentImages2 = assessmentImages2;
    }

    public String getAssessmentImages1() {
        return assessmentImages1;
    }

    public void setAssessmentImages1(String assessmentImages1) {
        this.assessmentImages1 = assessmentImages1;
    }

    public String getDemandNoteDeliveredAt() {
        return demandNoteDeliveredAt;
    }

    public void setDemandNoteDeliveredAt(String demandNoteDeliveredAt) {
        this.demandNoteDeliveredAt = demandNoteDeliveredAt;
    }

    public String getDemandNoteRecipientName() {
        return demandNoteRecipientName;
    }

    public void setDemandNoteRecipientName(String demandNoteRecipientName) {
        this.demandNoteRecipientName = demandNoteRecipientName;
    }

    public String getDemandNoteRecipientMobile() {
        return demandNoteRecipientMobile;
    }

    public void setDemandNoteRecipientMobile(String demandNoteRecipientMobile) {
        this.demandNoteRecipientMobile = demandNoteRecipientMobile;
    }

    public String getDemandNoteRecipientPhoto() {
        return demandNoteRecipientPhoto;
    }

    public void setDemandNoteRecipientPhoto(String demandNoteRecipientPhoto) {
        this.demandNoteRecipientPhoto = demandNoteRecipientPhoto;
    }

    public String getLastPrintedAt() {
        return lastPrintedAt;
    }

    public void setLastPrintedAt(String lastPrintedAt) {
        this.lastPrintedAt = lastPrintedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOriginalOne() {
        return originalOne;
    }

    public void setOriginalOne(String originalOne) {
        this.originalOne = originalOne;
    }

    public String getSmallPreviewOne() {
        return smallPreviewOne;
    }

    public void setSmallPreviewOne(String smallPreviewOne) {
        this.smallPreviewOne = smallPreviewOne;
    }

    public String getLargePreviewOne() {
        return largePreviewOne;
    }

    public void setLargePreviewOne(String largePreviewOne) {
        this.largePreviewOne = largePreviewOne;
    }

    public String getOriginalTwo() {
        return originalTwo;
    }

    public void setOriginalTwo(String originalTwo) {
        this.originalTwo = originalTwo;
    }

    public String getSmallPreviewTwo() {
        return smallPreviewTwo;
    }

    public void setSmallPreviewTwo(String smallPreviewTwo) {
        this.smallPreviewTwo = smallPreviewTwo;
    }

    public String getLargePreviewTwo() {
        return largePreviewTwo;
    }

    public void setLargePreviewTwo(String largePreviewTwo) {
        this.largePreviewTwo = largePreviewTwo;
    }

    public String getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(String swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public Boolean getIsDemandNoteDelivered() {
        return isDemandNoteDelivered;
    }

    public void setIsDemandNoteDelivered(Boolean isDemandNoteDelivered) {
        this.isDemandNoteDelivered = isDemandNoteDelivered;
    }

    public String getDemandNoteRecipientPhotoUrl() {
        return demandNoteRecipientPhotoUrl;
    }

    public void setDemandNoteRecipientPhotoUrl(String demandNoteRecipientPhotoUrl) {
        this.demandNoteRecipientPhotoUrl = demandNoteRecipientPhotoUrl;
    }

    public String getCurrentYearAssessmentAmount() {
        return currentYearAssessmentAmount;
    }

    public void setCurrentYearAssessmentAmount(String currentYearAssessmentAmount) {
        this.currentYearAssessmentAmount = currentYearAssessmentAmount;
    }

    public String getArrearDue() {
        return arrearDue;
    }

    public void setArrearDue(String arrearDue) {
        this.arrearDue = arrearDue;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public List<SearchCategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<SearchCategoryModel> categories) {
        this.categories = categories;
    }

    public List<SearchTypeModel> getTypes() {
        return types;
    }

    public void setTypes(List<SearchTypeModel> types) {
        this.types = types;
    }

    public SearchWallMaterialModel getWallMaterial() {
        return wallMaterial;
    }

    public void setWallMaterial(SearchWallMaterialModel wallMaterial) {
        this.wallMaterial = wallMaterial;
    }

    public SearchRoofMaterialModel getRoofMaterial() {
        return roofMaterial;
    }

    public void setRoofMaterial(SearchRoofMaterialModel roofMaterial) {
        this.roofMaterial = roofMaterial;
    }

    public List<SearchValueAddedModel> getValuesAdded() {
        return valuesAdded;
    }

    public void setValuesAdded(List<SearchValueAddedModel> valuesAdded) {
        this.valuesAdded = valuesAdded;
    }

    public SearchDimensionModel getDimension() {
        return dimension;
    }

    public void setDimension(SearchDimensionModel dimension) {
        this.dimension = dimension;
    }

    public AssessmentSwimming getSwimming() {
        return swimming;
    }

    public void setSwimming(AssessmentSwimming swimming) {
        this.swimming = swimming;
    }


}
