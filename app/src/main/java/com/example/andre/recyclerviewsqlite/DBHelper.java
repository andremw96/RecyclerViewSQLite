package com.example.andre.recyclerviewsqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andre on 23-Jun-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Contants.DB_NAME, null, Contants.DB_VERSION);
    }

    //WHEN TABLE CREATED
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(Contants.CREATE_TB);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //WHEN TABLE UPGRADED
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+Contants.DB_NAME);
        onCreate(db);
    }
}
