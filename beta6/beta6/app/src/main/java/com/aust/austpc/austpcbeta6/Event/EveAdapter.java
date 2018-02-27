package com.aust.austpc.austpcbeta6.Event;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aust.austpc.austpcbeta6.R;
import com.aust.austpc.austpcbeta6.database.MemAdapter;
import com.aust.austpc.austpcbeta6.database.MemberDesc;
import com.aust.austpc.austpcbeta6.database.ProfileSetupActivity;

import java.util.ArrayList;

/**
 * Created by USER on 2/5/2018.
 */

public  class EveAdapter extends RecyclerView.Adapter<EveAdapter.RecycleViewHolder> {
    ArrayList<EventDesc> arrayList=new ArrayList<>();
    EveAdapter(ArrayList<EventDesc> arrayList)
    {

        this.arrayList=arrayList;


    }
    @Override
    public EveAdapter.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row,parent,false);
        RecycleViewHolder recycleViewHolder=new RecycleViewHolder(view,parent.getContext(),arrayList);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        EventDesc eventDesc=arrayList.get(position);
        holder.textViewTitle.setText(eventDesc.getTitle());
        holder.textViewDate.setText(eventDesc.getDate());


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static  class RecycleViewHolder extends  RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView textViewTitle;
        public TextView textViewDate;
        ArrayList<EventDesc> arrayList =new ArrayList<EventDesc>();
        Context context;
        CardView cardView;
        RecycleViewHolder(View view, Context context, ArrayList<EventDesc> arrayList)
        {
            super(view);
            this.arrayList=arrayList;
            this.context=context;
            textViewTitle=(TextView)itemView.findViewById(R.id.eventtitle);
            textViewDate=(TextView)itemView.findViewById(R.id.eventdate);
            cardView=(CardView)view.findViewById(R.id.row);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            //Toast.makeText(this.context, textViewTitle.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this.context,EventUpdateDelete.class);
            intent.putExtra("Event Title",textViewTitle.getText().toString());
            this.context.startActivity(intent);
        }
    }
}
