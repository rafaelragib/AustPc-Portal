package com.aust.austpc.austpcbeta6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aust.austpc.austpcbeta6.Event.EventDesc;

import static android.content.ContentValues.TAG;

/**
 * Created by USER on 2/3/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{



    private static final String Database_Name="MEMBER.db";
    private static final String member_table ="MEMBER_TABLE";
    private static final String KEY_ID="ID";
    private static final String KEY_NAME ="NAME";
    private static final String KEY_ROLL="ROLL";
    private static final String KEY_CONTACT="CONTACT";
    private static final String KEY_YEAR="YEAR";
    private static final String KEY_SEMESTER="SEMESTER";
    private static final String KEY_EMAIL="USEREMAIL";
    private static final String event_table="EVENT_TABLE";
    private static final String event_id="ID";
    private static final String event_title="TITLE";
    private static final String event_date="DATE";
    private static final String event_venue="VENUE";
    private static final String event_about="ABOUT";



    public DatabaseHandler(Context context) {
        super(context,Database_Name,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+ member_table +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,ROLL TEXT,CONTACT TEXT,YEAR TEXT,SEMESTER TEXT,USEREMAIL TEXT)");
        db.execSQL("CREATE TABLE "+ event_table +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DATE TEXT,VENUE TEXT,ABOUT TEXT)");

        Log.i(TAG, "Datbase CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ member_table);
        db.execSQL("DROP TABLE IF EXISTS "+ event_table);
        onCreate(db);



    }
    public boolean insertData(String NAME,String  ROLL, String CONTACT, String YEAR, String SEMESTER,String USEREMAIL)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,NAME);
        contentValues.put(KEY_ROLL,ROLL);
        contentValues.put(KEY_CONTACT,CONTACT);
        contentValues.put(KEY_YEAR,YEAR);
        contentValues.put(KEY_SEMESTER,SEMESTER);
        contentValues.put(KEY_EMAIL,USEREMAIL);
        long result = db.insert(member_table,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public boolean insertEventData(String title,String date,String venue,String about)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(event_title,title);
        contentValues.put(event_date,date);
        contentValues.put(event_venue,venue);
        contentValues.put(event_about,about);
        long result=db.insert(event_table,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(SQLiteDatabase db){

        Cursor cursor = db.rawQuery("select * from " + member_table, null);
        return cursor;
    }
    public Cursor getEventAllData(SQLiteDatabase db)
    {
        Cursor cursor=db.rawQuery("select * from " +event_table,null);
    return cursor;
    }
    public EventDesc getSingleEvent(String eventtitle)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String query = "SELECT DATE,VENUE,ABOUT FROM EVENT_TABLE WHERE TITLE =  "+eventtitle;
        //Cursor cursor=db.rawQuery(query, null);
        Cursor cursor = db.query(event_table, new String[] {event_id, event_date,event_venue,event_about}, "TITLE=?",new String[]{eventtitle} ,null, null,null, null);
        EventDesc eventDesc = null;
        if(cursor.moveToFirst())
        {
            eventDesc=new EventDesc(Integer.parseInt(cursor.getString(0)),eventtitle,cursor.getString(1),cursor.getString(2),cursor.getString(3));
        }

        return eventDesc;
    }

    public boolean dataUpdate(int id, String title, String date, String venue, String about)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(event_id, id );
        value.put(event_title, title);
        value.put(event_date, date);
        value.put(event_venue, venue);
        value.put(event_about, about);
        db.update(event_table,value,"ID=?", new String[] { String.valueOf(id) });
        Log.i(TAG, "dataUpdate: ");
        db.close();
        return true;
    }
    public boolean deleteContact(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //String query = "DELETE FromFrom "+TABLE_NAME+" WHERE  ID="+id;
        //db.execSQL(query);

        db.delete(event_table, "ID=?", new String[]{String.valueOf(id)});
        db.close();
        return true;
    }

}
