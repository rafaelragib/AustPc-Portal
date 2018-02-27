package com.aust.austpc.austpcbeta6.notice_pack;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aust.austpc.austpcbeta6.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class NoticeActivity extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Notice> listItems;
    //private Notice notice;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Notice Board");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_notice,container,false);
        recyclerView=(RecyclerView) v.findViewById(R.id.noticeview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems=new ArrayList<>();
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseget();
        return v;
    }

    private void databaseget(){
        databaseReference=firebaseDatabase.getReference("Notice");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Notice notice=new Notice();
                notice=dataSnapshot.getValue(Notice.class);
                listItems.add(notice);
                adapter=new NoticeAdapter(listItems,getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }


            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        }
        );
    }
}
        class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
            private List<Notice> listItems;
            private Context context;

            public NoticeAdapter(List<Notice> listItems, Context context) {
                this.listItems = listItems;
                this.context = context;
            }


            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.notice_row,parent,false);
                return new NoticeAdapter.ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                Notice notice=listItems.get(position);
                holder.textDate.setText(notice.getDate());
                holder.textDetails.setText(notice.getDetails());
            }

            @Override
            public int getItemCount() {
                return listItems.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder{
                public TextView textDate;
                public TextView textDetails;
                public ViewHolder(View itemView) {
                    super(itemView);

                    textDate=(TextView)itemView.findViewById(R.id.date);
                    textDetails=(TextView)itemView.findViewById(R.id.details);
                }
            }
        }

