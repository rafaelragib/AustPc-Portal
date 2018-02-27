package com.aust.austpc.austpcbeta6.Event;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aust.austpc.austpcbeta6.R;
import com.aust.austpc.austpcbeta6.database.DatabaseHandler;
import com.aust.austpc.austpcbeta6.database.MemAdapter;
import com.aust.austpc.austpcbeta6.database.MemberDesc;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by USER on 2/4/2018.
 */

public class EventActivity extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<EventDesc> arrayList=new ArrayList<EventDesc>();
    private FloatingActionButton floatingActionButton;
    private CardView cardView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_event,container,false);
        RecyclerView recyclerView =(RecyclerView) v.findViewById(R.id.recycle_event);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DatabaseHandler databaseHandler=new DatabaseHandler(getContext());
        SQLiteDatabase sqLiteDatabase =databaseHandler.getReadableDatabase();
        Cursor cursor=databaseHandler.getEventAllData(sqLiteDatabase);
        if(cursor.getCount() !=0) {
            cursor.moveToFirst();
            do {
                EventDesc eventDesc = new EventDesc(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                arrayList.add(eventDesc);
            } while (cursor.moveToNext());
            databaseHandler.close();
        }
        else {
            Toast.makeText(getContext(),"There is no event in the list.",Toast.LENGTH_SHORT).show();
            Log.i(TAG, "KHALI MAMA");
        }
        adapter=new EveAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        floatingActionButton=(FloatingActionButton) v.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),EventCreateActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    /*public void createEvent(View view)
    {
        Intent intent=new Intent(getActivity(),EventCreateActivity.class);
        startActivity(intent);
    }*/
}
