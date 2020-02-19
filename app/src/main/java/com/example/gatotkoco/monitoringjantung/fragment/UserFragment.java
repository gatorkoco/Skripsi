package com.example.gatotkoco.monitoringjantung.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gatotkoco.monitoringjantung.R;
import com.example.gatotkoco.monitoringjantung.model.Biodata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by GATOT KOCO on 17/07/2019.
 */

public class UserFragment extends Fragment {

    TextView txtNamaLengkap,txtJenisKelamin,txtUsia;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        txtNamaLengkap = view.findViewById(R.id.txtNamaLengkap);
        txtJenisKelamin = view.findViewById(R.id.txtJenisKelamin);
        txtUsia = view.findViewById(R.id.txtUsia);
        myRef = FirebaseDatabase.getInstance().getReference();
        view();
        return view;
    }

    private void view() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading");
        progressDialog.show();
        myRef.child("biodata/-LjJl_zVp_Xy8ayOsiv2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Biodata value = dataSnapshot.getValue(Biodata.class);
                txtNamaLengkap.setText(value.getNama());
                txtJenisKelamin.setText(value.getJenis_kelamin());
                txtUsia.setText(value.getUsia());
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
