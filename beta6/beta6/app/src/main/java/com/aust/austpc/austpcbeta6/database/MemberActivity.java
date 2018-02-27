package com.aust.austpc.austpcbeta6.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aust.austpc.austpcbeta6.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by USER on 2/3/2018.
 */

public class MemberActivity extends Fragment {
     RecyclerView recyclerView;
     RecyclerView.Adapter adapter;
     RecyclerView.LayoutManager layoutManager;
     ArrayList<MemberDesc> arrayList=new ArrayList<MemberDesc>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_member,container,false);

        recyclerView=(RecyclerView) v.findViewById(R.id.member_activity);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DatabaseHandler databaseHandler=new DatabaseHandler(getContext());
        SQLiteDatabase sqLiteDatabase =databaseHandler.getReadableDatabase();
        Cursor cursor=databaseHandler.getAllData(sqLiteDatabase);
        if(cursor.getCount() !=0) {
            cursor.moveToFirst();
            do {
                MemberDesc memberDesc = new MemberDesc(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                arrayList.add(memberDesc);
            } while (cursor.moveToNext());
            databaseHandler.close();
        }
        else {
            Toast.makeText(getContext(),"There is no member in the list.",Toast.LENGTH_SHORT).show();
            Log.i(TAG, "KHALI MAMA");
        }
        adapter=new MemAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Member");
    }
    }



