package android.yogi.com.mobilefood.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yogesh.kalwar on 30/09/2016.
 */

public class MFoodDatabaseHelper extends SQLiteOpenHelper {

    public MFoodDatabaseHelper(final Context context){
        super(context, MFoodDBContracts.DATABASE_NAME, null, MFoodDBContracts.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
