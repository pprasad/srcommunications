package com.srm.services.dto;

public class ServiceConstant {

    public final static String RESULT_SUCCESS = "success";
    public final static String RESULT_FAIURE = "failure";
    public static final String LOG_CREATED_BY = "createdBy";
    public static final String LOG_CREATED_ON = "createdOn";
    public static final String LOG_PUI_ENTRY_ON = "puiEntryOn";
    public static final String LOG_UPDATED_BY = "updatedBy";
    public static final String LOG_UPDATED_ON = "updatedOn";
    public static final String LOG_CREATED_TIME = "createdTime";
    public static final String SUCCESS_MSG="Infromation Successfully Saved";
    public static final String UPDATE_MSG="Infromation Successfully Updated";
    public static final String DETLE_MSG="Record Successfully Deleted";
    public static final String LOGIN_FAILURE_MSG="Please check UserId/Password";
    public static final Integer ACTION_SAVE=1;
    public static final Integer ACTION_UPDATE=2;
    public static final Integer ACTION_DELETE=3;
    public static final Integer ACTION_LOGIN_FAILURE=4;
    public static final String SELECTED="Select";
    public static final String PARM_PROD_CODE="prodCode";
    public static final String DEFAULT_USE_YES="Y";
    public static final String DEFAULT_USE_NO="N";
    public static final String CATEGORY_PROD_CODE_SQL="SELECT CP.PRODUCT_CODE,CP.PRODUCT_NAME,PS.PROD_QUANTITY,PS.PROD_PRICE"
            + " FROM CATEGORY_PRODUCT CP JOIN "
            + "PRODUCT_STOCK PS ON CP.PRODUCT_CODE=PS.PROD_CODE WHERE PS.PROD_CODE=:prodCode";
}
