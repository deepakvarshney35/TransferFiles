package com.example.deepak.firstexample;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Deepak on 18-Dec-15.
 */
public class mainfragment extends android.support.v4.app.Fragment {

    Button bupload,bdownload;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=(View)inflater.inflate(R.layout.fragment_main,container,false);
        bupload = (Button) v.findViewById(R.id.buttonUploadMain);
        bdownload = (Button) v.findViewById(R.id.buttonDownloadMain);
        bdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                startActivity(intent);
            }
        });

        bupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), UploadActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


}
