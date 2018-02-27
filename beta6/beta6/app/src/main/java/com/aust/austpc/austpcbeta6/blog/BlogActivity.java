package com.aust.austpc.austpcbeta6.blog;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static android.content.ContentValues.TAG;

public class BlogActivity extends Fragment {
    RecyclerView recyclerView;
    List<BlogDesc> listItems;
    MyAdapter adapter=null;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_blog,container,false);
        recyclerView=(RecyclerView) v.findViewById(R.id.blog_activity);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems=new ArrayList<>();
        adapter=new MyAdapter(listItems);
        firebaseDatabase=FirebaseDatabase.getInstance();

        databaseget();



        return v;
    }
    private void databaseget(){
        databaseReference=firebaseDatabase.getReference("Blog");
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BlogDesc blogDesc=new BlogDesc();
                blogDesc=dataSnapshot.getValue(BlogDesc.class);

                listItems.add(blogDesc);
                adapter=new MyAdapter(listItems);
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
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        }
        );

    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Blog");
    }


}
class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<BlogDesc> listItems;
    private Context context;

    public MyAdapter(List<BlogDesc> listItems) {
        this.listItems = listItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BlogDesc blogDesc=listItems.get(position);
        holder.textViewTitle.setText(blogDesc.getTitle());
        holder.textViewDesc.setText(blogDesc.getDescription());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewDesc;
        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle=(TextView)itemView.findViewById(R.id.title);
            textViewDesc=(TextView)itemView.findViewById(R.id.description);
        }
    }
}
