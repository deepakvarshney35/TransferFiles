package com.example.deepak.firstexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Deepak on 18-Dec-15.
 */
public class selectionFragment extends Fragment {
    RecyclerView listView;
    MyRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private AmazonS3Client s3;
    ArrayList<String> sumName;
    private ArrayList<HashMap<String, Object>> transferRecordMaps;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        s3 = Util.getS3Client(getActivity());
        transferRecordMaps = new ArrayList<HashMap<String, Object>>();
        sumName= new ArrayList<String>();

        View v=(View)inflater.inflate(R.layout.fragment_listview,container,false);

        new GetFileListTask().execute();

        listView=(RecyclerView)v.findViewById(R.id.my_recycler_view);
        listView.setHasFixedSize(true);


        layoutManager=new LinearLayoutManager(getActivity());
        listView.setLayoutManager(layoutManager);


        adapter=new MyRecyclerAdapter(sumName);

        return v;
    }
    private class GetFileListTask extends AsyncTask<Void, Void, Void> {
        // The list of objects we find in the S3 bucket
        private List<S3ObjectSummary> s3ObjList;
        // A dialog to let the user know we are retrieving the files
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(getActivity(),
                    "refreshing",
                    "plz wait");
        }

        @Override
        protected Void doInBackground(Void... inputs) {
            // Queries files in the bucket from S3.
            s3ObjList = s3.listObjects(Constants.BUCKET_NAME).getObjectSummaries();
            transferRecordMaps.clear();

            for (S3ObjectSummary summary : s3ObjList) {
                sumName.add((String)summary.getKey());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            dialog.dismiss();
            Log.d("size", sumName.size() + "");
            listView.setAdapter(adapter);
           // Log.d("1 Name",(String)sumName.get(1));

           // adapter.notifyDataSetChanged();
        }
    }

}
