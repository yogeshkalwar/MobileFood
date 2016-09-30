package android.yogi.com.mobilefood.database;

import android.net.Uri;
import android.provider.BaseColumns;
import android.yogi.com.mobilefood.BuildConfig;

/**
 * Created by yogesh.kalwar on 30/09/2016.
 */

public final class MFoodDBContracts {

    private static final String NAME = "mobilefoods";
    public static final String DATABASE_NAME = NAME + ".db";
    public static final int DATABASE_VERSION = 1;

    public static enum URI_IDS{
      MFOOD_ALL, MFOOD_SINGLE
    };

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + "." + NAME;
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);


    public static final class MobileFoodTable extends MobileFood implements BaseColumns{

        public static final String TABLE_NAME = "mfood";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);


        public static final String getCreateTable(){
            return "CREATE TABLE " + TABLE_NAME + "";
        }
    }
}
