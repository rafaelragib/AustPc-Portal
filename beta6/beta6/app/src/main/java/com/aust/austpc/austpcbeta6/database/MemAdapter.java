package com.aust.austpc.austpcbeta6.database;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aust.austpc.austpcbeta6.R;

import java.util.ArrayList;

/**
 * Created by USER on 2/3/2018.
 */

public class MemAdapter extends RecyclerView.Adapter<MemAdapter.RecycleViewHolder> {
    ArrayList<MemberDesc> arrayList=new ArrayList<>();
    MemAdapter(ArrayList<MemberDesc> arrayList)
    {
        this.arrayList=arrayList;
    }
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.member_row,parent,false);
        RecycleViewHolder recycleViewHolder=new RecycleViewHolder(view);
        return recycleViewHolder;
        }
        @Override
        public void onBindViewHolder(RecycleViewHolder holder, int position) {
            MemberDesc memberDesc=arrayList.get(position);
            holder.textViewName.setText(memberDesc.getName());
            holder.textViewRoll.setText(memberDesc.getRoll());
            holder.textViewPhone.setText(memberDesc.getPhone());
            holder.textViewYear.setText(memberDesc.getYear());
            holder.textViewSem.setText(memberDesc.getSem());
            holder.textViewEmail.setText(memberDesc.getEmail());
        }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static  class RecycleViewHolder extends  RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewRoll;
        public TextView textViewPhone;
        public TextView textViewYear;
        public TextView textViewSem;
        public TextView textViewEmail;
        RecycleViewHolder(View view)
        {
            super(view);
            textViewName=(TextView)itemView.findViewById(R.id.memName);
            textViewRoll=(TextView)itemView.findViewById(R.id.memRoll);
            textViewPhone=(TextView)itemView.findViewById(R.id.memPhone);
            textViewYear=(TextView)itemView.findViewById(R.id.memYear);
            textViewSem=(TextView)itemView.findViewById(R.id.memSem);
            textViewEmail=(TextView)itemView.findViewById(R.id.memEmail);
        }
    }
}
/*class MemAdapter extends RecyclerView.Adapter<MemAdapter.ViewHolder>
{
    private ArrayList<MemberDesc> listItems;
    private Context context;

    public MemAdapter(ArrayList<MemberDesc> listItems) {
        this.listItems = listItems;
    }


    @Override
    public MemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_row,parent,false);
        return new MemAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MemAdapter.ViewHolder holder, int position) {
        MemberDesc memberDesc=listItems.get(position);
        holder.textViewName.setText(memberDesc.getName());
        holder.textViewRoll.setText(memberDesc.getRoll());
        holder.textViewPhone.setText(memberDesc.getPhone());
        holder.textViewYear.setText(memberDesc.getYear());
        holder.textViewSem.setText(memberDesc.getSem());
        Log.i(TAG, holder.textViewName.getText().toString());
    }

    @Override
    public int getItemCount() {
        //int i=listItems.size();
        //Log.i(TAG, Integer.toString(i));
        return listItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewRoll;
        public TextView textViewPhone;
        public TextView textViewYear;
        public TextView textViewSem;
        public ViewHolder(View itemView) {
            super(itemView);

            textViewName=(TextView)itemView.findViewById(R.id.memName);
            textViewRoll=(TextView)itemView.findViewById(R.id.memRoll);
            textViewPhone=(TextView)itemView.findViewById(R.id.memPhone);
            textViewYear=(TextView)itemView.findViewById(R.id.memYear);
            textViewSem=(TextView)itemView.findViewById(R.id.memSem);
        }
    }
}*/

