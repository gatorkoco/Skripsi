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
import com.example.gatotkoco.monitoringjantung.adapter.NotificationAdapter;
import com.example.gatotkoco.monitoringjantung.model.Arduino;
import com.example.gatotkoco.monitoringjantung.model.Notification;
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

public class NotificationFragment extends Fragment {
    RecyclerView rvNotification;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Notification notification;
    ArrayList<Notification> notificationArrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        rvNotification = view.findViewById(R.id.rvNotification);
        myRef = FirebaseDatabase.getInstance().getReference();
        layoutManager = new LinearLayoutManager(getActivity());
        rvNotification.setLayoutManager(layoutManager);
        view();
        return view;
    }

    private void view() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading");
        progressDialog.show();
        myRef.child("notifikasi").orderByChild("key").equalTo("-LjJl_zVp_Xy8ayOsiv2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notificationArrayList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    notification = postSnapshot.getValue(Notification.class);
                    notificationArrayList.add(notification);
                }
                adapter = new NotificationAdapter(getActivity(), notificationArrayList);
                rvNotification.setAdapter(adapter);
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
