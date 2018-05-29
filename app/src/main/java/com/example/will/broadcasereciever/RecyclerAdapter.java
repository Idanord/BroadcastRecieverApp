package com.example.will.broadcasereciever;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Will on 5/28/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    //variables
    private ArrayList<IncomingNumber> mArrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<IncomingNumber> arrayList){
        this.mArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //variables for view class
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent,
                false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //set text on textviews
        holder.id.setText(Integer.toString(mArrayList.get(position).getId()));
        holder.number.setText(mArrayList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //variables for text view
        TextView id, number;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textId);
            number = (TextView) itemView.findViewById(R.id.txtNumber);
        }
    }
}
