package com.example.gatotkoco.monitoringjantung.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gatotkoco.monitoringjantung.R;
import com.example.gatotkoco.monitoringjantung.adapter.HistoryAdapter;
import com.example.gatotkoco.monitoringjantung.model.Arduino;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.ContentValues.TAG;

/**
 * Created by GATOT KOCO on 17/07/2019.
 */

public class HistoryFragment extends Fragment{

    RecyclerView rvHistory;
    TextView btnAsc,btnDesc;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Arduino arduino;
    ArrayList<Arduino> arduinoArrayList;
    int valueSort = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        rvHistory = view.findViewById(R.id.rvHistory);
        btnAsc = view.findViewById(R.id.btnAsc);
        btnDesc = view.findViewById(R.id.btnDesc);
        myRef = FirebaseDatabase.getInstance().getReference();
        layoutManager = new LinearLayoutManager(getActivity());
        rvHistory.setLayoutManager(layoutManager);
        btnAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueSort = 1 ;
                btnAsc.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnDesc.setTextColor(getResources().getColor(R.color.black));
                view();
            }
        });
        btnDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueSort = 2 ;
                btnAsc.setTextColor(getResources().getColor(R.color.black));
                btnDesc.setTextColor(getResources().getColor(R.color.colorPrimary));
                view();
            }
        });
        view();
        return view;
    }

    private void view() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading");
        progressDialog.show();
        myRef.child("biodata/-LjJl_zVp_Xy8ayOsiv2/timestamp/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arduinoArrayList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    arduino = postSnapshot.getValue(Arduino.class);
                    arduinoArrayList.add(arduino);
                    //sorting data asc desc berdasarkan tanggal
                    if (valueSort == 1){
                        Collections.sort(arduinoArrayList, new Comparator<Arduino>() {
                            @Override
                            public int compare(Arduino o1, Arduino o2) {
                                return o1.getCreated_at().compareTo(o2.getCreated_at());
                            }
                        });
                    }
                    else {
                        Collections.sort(arduinoArrayList, new Comparator<Arduino>() {
                            @Override
                            public int compare(Arduino o1, Arduino o2) {
                                return o2.getCreated_at().compareTo(o1.getCreated_at());
                            }
                        });
                    }

                }
                adapter = new HistoryAdapter(getActivity(), arduinoArrayList);
                rvHistory.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Connection problem", error.toException());
                progressDialog.dismiss();
            }
        });
    }

}
