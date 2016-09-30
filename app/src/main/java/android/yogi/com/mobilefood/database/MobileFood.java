package android.yogi.com.mobilefood.database;

/**
 * Created by yogesh.kalwar on 30/09/2016.
 */

public class MobileFood {
    public static final String _ID = "_id";
    public static final String LOCATION_ID = "locid";
    public static final String APPLICANT = "applicant";
    public static final String FACILITY_TYPE = "facility_type";
    public static final String LOCATION_DESC = "location_desc";
    public static final String ADDRESS = "address";
    public static final String BLOCK = "block";
    public static final String LOT = "lot";
    public static final String PERMIT = "permit";
    public static final String PERMIT_STATUS = "status";
    public static final String FOOD_ITEMS = "items";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String DAYS_HOURS = "days_hours";
    public static final String APPROVED = "approved";
    public static final String EXPIRATION = "expiration";

    public static final String[] PROJECTION_ALL = {_ID, LOCATION_ID,APPLICANT,FACILITY_TYPE,LOCATION_DESC,
                                    ADDRESS,BLOCK,LOT,PERMIT,PERMIT_STATUS,FOOD_ITEMS,LATITUDE,LONGITUDE,
                                    DAYS_HOURS,APPROVED,EXPIRATION};
    public static final String SORT_ORDER_DEFAULT = APPLICANT + " DESC";
}
