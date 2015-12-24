package com.example.deepak.firstexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Deepak on 19-Dec-15.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private ArrayList<String> dataSet;

    public MyRecyclerAdapter(ArrayList<String> myDataSet) {
        this.dataSet=myDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        //ImageView iv;
        public ViewHolder(View v){
            super(v);
            tv=(TextView)v.findViewById(R.id.textView3);
           // iv=(ImageView)v.findViewById(R.id.row_image);
        };
    }


    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.bucket_item2,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.tv.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
