package com.arkainfotechpvt.cumi.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arkainfotechpvt.cumi.model.Products;

/**
 * Created by Grepthor_9 on 2/19/2018.
 */
public  class MyDBHandler extends SQLiteOpenHelper {
private static final int DATABASE_VERSION = 1;
   public static int trww=6;
    String query;
private static final String DATABASE_NAME = "productD" +
        ".db";
public static final String TABLE_PRODUCTS = "productse";
public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PRODUCTNAME = "productname";
        public static final String COLUMN_PRODUCTNAMEA = "productnamea";
        public static final String COLUMN_PRODUCTNAMEB = "productnameb";
        public static final String COLUMN_PRODUCTNAMEC = "productnamec";
        public static final String COLUMN_PRODUCTNAMED = "productnamed";

//We need to pass database information along to superclass
public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

@Override
public void onCreate(SQLiteDatabase db) {

        String query =    "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME + " TEXT," + COLUMN_PRODUCTNAMEA + " TEXT," + COLUMN_PRODUCTNAMEB + " TEXT," + COLUMN_PRODUCTNAMEC + " TEXT,"
                + COLUMN_PRODUCTNAMED + " TEXT" + ")";
        db.execSQL(query);
        }
//Lesson 51
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DELETE FROM  " + TABLE_PRODUCTS);

        onCreate(db);
        }


//Add a new row to the database
public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        values.put(COLUMN_PRODUCTNAMEA, product.get_productnamea());
        values.put(COLUMN_PRODUCTNAMEB, product.get_productnameb());
        values.put(COLUMN_PRODUCTNAMEC, product.get_productnamec());
        values.put(COLUMN_PRODUCTNAMED, product.get_productnamed());
             SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        }



// this is goint in record_TextView in the Main activity.
public String databaseToStringTwo(){
   String dbString="";
        SQLiteDatabase db = getWritableDatabase();

       int limit=2;
   /* String Ans = "";//string that contains the required field  note that Ans is just a local string not related to Answer or Option...
    Cursor c = sqlite.rawQuery("SELECT " + Question + " FROM " + Table_name + " WHERE " + uid + " = " + i + "", null);//cursor to that query
    if (c.moveToFirst())
        Ans = c.getString(0);
    else
        Ans = "";
    return Ans;*/
    //String query = "SELECT * FROM " + TABLE_PRODUCTS ;// why not leave out the WHERE  clause?
    //query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE _id="+ limit;
    query = "SELECT * FROM " + TABLE_PRODUCTS ;
  //  query = "select* from productse";

    Cursor recordSet = db.rawQuery(query, null);

        //Move to the first row in your results
        recordSet.moveToFirst();

        while (!recordSet.isAfterLast()) {

            if (recordSet.getString(recordSet.getColumnIndex("productname")) != null) {

                dbString += recordSet.getString(recordSet.getColumnIndex("_id")) + "-" + recordSet.getString(recordSet.getColumnIndex("productname")) + "-" + recordSet.getString(recordSet.getColumnIndex("productnamea")) + "-" + recordSet.getString(recordSet.getColumnIndex("productnameb")) + "-" + recordSet.getString(recordSet.getColumnIndex("productnamec")) + "-" + recordSet.getString(recordSet.getColumnIndex("productnamed"));
                dbString += "\n";

            }
            recordSet.moveToNext();
            recordSet.getCount();

        }

    //db.close();
      return dbString;
        }
        public int deleteAllData()
        {
                SQLiteDatabase db=getWritableDatabase();
                int count= db.delete(TABLE_PRODUCTS,null,null);
                db.close();
                return count;
        }
        }
