package com.cleancampus.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Chanpreet on 28-09-2016.
 */
public class Dbhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="cleancampus";

    private static final String TABLE_NAME="data";

    private static final String KEY_ID="id";

    private static final String KEY_USERNAME="username";

    private static final String KEY_LOCATION="location";

    private static final String KEY_EMAIL="email";

    private static final String KEY_TITLE="title";

    private static final String KEY_DECRIPTION="description";

    private static final String KEY_STATUS="status";

    private static final String KEY_DATE="date";

    private static  Context context;

    private SQLiteDatabase db = getWritableDatabase() ;
    public Dbhelper( Context context1) {
        super(context1,DATABASE_NAME,null, DATABASE_VERSION);
        context = context1;
    }
    @Override
    public void onCreate(SQLiteDatabase dbname) {

        db= dbname;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERNAME
                + " TEXT, " + KEY_LOCATION+ " TEXT, "+KEY_EMAIL +" TEXT, "
                +KEY_TITLE +" TEXT, "+KEY_DECRIPTION+" TEXT, "+KEY_STATUS+" INTEGER, "+KEY_DATE+" TEXT )";
        dbname.execSQL(sql);
        Data dt = new Data("Chanpreet","","chanpreet.chhabra@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",1,"28 september");
        add(dt);
        Data dt2 = new Data("Mahesh","","mahesh.kabra@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",0,"28 september");
        add(dt2);
        Data dt3 = new Data("Kaushal","","kaushal.nak@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",2,"28 september");
        add(dt3);
        Data dt4 = new Data("Anshul","","ansh.goyal@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",0,"28 september");
        add(dt4);
        Data dt5 = new Data("Jai","","jai.kamchor@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",1,"28 september");
        add(dt5);
        Data dt6 = new Data("Hina","","hina.varshney@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",2,"28 september");
        add(dt6);
        Data dt7 = new Data("Badal","","badal.sharma@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",1,"28 september");
        add(dt7);
        Data dt8 = new Data("Arush","","arush.goyal@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",0,"28 september");
        add(dt8);
        Data dt9 = new Data("Suyash","","suyash.example@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",2,"28 september");
        add(dt9);
        Data dt0 = new Data("Ankit","","ankit.sleepy@gmail.com","ComplaintFragment title","Here is the description of complain that where is the garbage and information",0,"28 september");
        add(dt0);

    }

    public void add(Data data)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_USERNAME,data.getUsername());
        cv.put(KEY_LOCATION,data.getLocation());
        cv.put(KEY_EMAIL,data.getEmail());
        cv.put(KEY_TITLE,data.getTitle());
        cv.put(KEY_DECRIPTION,data.getDescription());
        cv.put(KEY_STATUS,data.getStatus());
        cv.put(KEY_DATE,data.getDate());
        db.insert(TABLE_NAME,null,cv);
        //Toast.makeText(context,"ComplaintFragment Registered",Toast.LENGTH_LONG).show();
    }


    public ArrayList<Data> getData()
    {
        ArrayList<Data> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selected= "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(selected,null);

        if(cursor.moveToFirst()) {
            do {
                Data d = new Data();
                d.setId(cursor.getInt(0));
                d.setUsername(cursor.getString(1));
                d.setLocation(cursor.getString(2));
                d.setEmail(cursor.getString(3));
                d.setTitle(cursor.getString(4));
                d.setDescription(cursor.getString(5));
                d.setStatus(cursor.getInt(6));
                d.setDate(cursor.getString(7));
                list.add(d);

            }
            while(cursor.moveToNext());
        }
        return list;
    }

    public void insertIntoDB(String compliant , String description,Context context){

        if(compliant.equals("") || description.equals("")){
            Toast.makeText(context,"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        String query = "INSERT INTO "+TABLE_NAME+" ("+KEY_TITLE+","+KEY_DECRIPTION+") VALUES('"+compliant+"', '"+description+"');";
        db.execSQL(query);
        //Toast.makeText(context,"Saved Successfully", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
