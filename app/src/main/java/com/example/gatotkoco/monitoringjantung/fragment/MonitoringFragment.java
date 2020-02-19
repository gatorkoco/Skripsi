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
import com.example.gatotkoco.monitoringjantung.model.Arduino;
import com.example.gatotkoco.monitoringjantung.model.Biodata;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by GATOT KOCO on 17/07/2019.
 */

public class MonitoringFragment extends Fragment {

    TextView txtJantung,txtOksigen;
    BarChart barChartJantung,barChartOksigen;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring, container, false);
        txtJantung = view.findViewById(R.id.txtJantung);
        txtOksigen = view.findViewById(R.id.txtOksigen);
        barChartJantung = view.findViewById(R.id.barChartJantung);
        barChartOksigen = view.findViewById(R.id.barChartOksigen);
        myRef = FirebaseDatabase.getInstance().getReference();
        view();
        return view;
    }

    private void grafikJantung() {
        myRef.child("biodata/-LjJl_zVp_Xy8ayOsiv2/timestamp/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float barWidth = 12f;
                float interval = 50f;
                int x = 0;
                // Data-data yang akan ditampilkan di Chart
                List<BarEntry> dataPemasukan = new ArrayList<BarEntry>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Arduino value = postSnapshot.getValue(Arduino.class);
                    txtJantung.setText(String.valueOf(value.getDataJantung()+" bpm"));
                    txtOksigen.setText(String.valueOf(value.getDataOksigen()+"%"));
                    x += 60;
                    dataPemasukan.add(new BarEntry(x, value.getDataJantung()));
                }

                // Pengaturan atribut bar, seperti warna dan lain-lain
                BarDataSet dataSet1 = new BarDataSet(dataPemasukan, "Detak Jantung");
                dataSet1.setColor(getResources().getColor(R.color.colorAccent));

                // Membuat Bar data yang akan di set ke Chart
                BarData barData = new BarData(dataSet1);

                // Pengaturan sumbu X
                XAxis xAxis = barChartJantung.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM.BOTTOM);
                xAxis.setCenterAxisLabels(true);

                // Agar ketika di zoom tidak menjadi pecahan
                xAxis.setGranularity(1f);

                // Diubah menjadi integer, kemudian dijadikan String
                // Ini berfungsi untuk menghilankan koma, dan tanda ribuah pada tahun
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return String.valueOf((int) value);
                    }
                });

                //Menghilangkan sumbu Y yang ada di sebelah kanan
                barChartJantung.getAxisRight().setEnabled(false);

                // Menghilankan deskripsi pada Chart
                barChartJantung.getDescription().setEnabled(false);

                // Set data ke Chart
                // Tambahkan invalidate setiap kali mengubah data chart
                barChartJantung.setData(barData);
                barChartJantung.getBarData().setBarWidth(barWidth);
                barChartJantung.getXAxis().setAxisMinimum(interval);
                barChartJantung.setDragEnabled(true);
                barChartJantung.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Connection problem", error.toException());
            }
        });


    }

    private void grafikOksigen() {
        myRef.child("biodata/-LjJl_zVp_Xy8ayOsiv2/timestamp/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float barWidth = 12f;
                float interval = 10f;
                int x = 0;
                // Data-data yang akan ditampilkan di Chart
                List<BarEntry> dataPemasukan = new ArrayList<BarEntry>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Arduino value = postSnapshot.getValue(Arduino.class);
                    txtJantung.setText(String.valueOf(value.getDataJantung()+" bpm"));
                    txtOksigen.setText(String.valueOf(value.getDataOksigen()+"%"));
                    x += 60;
                    dataPemasukan.add(new BarEntry(x, value.getDataOksigen()));
                }

                // Pengaturan atribut bar, seperti warna dan lain-lain
                BarDataSet dataSet1 = new BarDataSet(dataPemasukan, "Oksigen");
                dataSet1.setColor(getResources().getColor(R.color.blue_light));

                // Membuat Bar data yang akan di set ke Chart
                BarData barData = new BarData(dataSet1);

                // Pengaturan sumbu X
                XAxis xAxis = barChartOksigen.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM.BOTTOM);
                xAxis.setCenterAxisLabels(true);

                // Agar ketika di zoom tidak menjadi pecahan
                xAxis.setGranularity(1f);

                // Diubah menjadi integer, kemudian dijadikan String
                // Ini berfungsi untuk menghilankan koma, dan tanda ribuah pada tahun
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return String.valueOf((int) value);
                    }
                });

                //Menghilangkan sumbu Y yang ada di sebelah kanan
                barChartOksigen.getAxisRight().setEnabled(false);

                // Menghilankan deskripsi pada Chart
                barChartOksigen.getDescription().setEnabled(false);

                // Set data ke Chart
                // Tambahkan invalidate setiap kali mengubah data chart
                barChartOksigen.setData(barData);
                barChartOksigen.getBarData().setBarWidth(barWidth);
                barChartOksigen.getXAxis().setAxisMinimum(interval);
                barChartOksigen.setDragEnabled(true);
                barChartOksigen.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Connection problem", error.toException());
            }
        });


    }

    private void view() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading");
        progressDialog.show();
        grafikJantung();
        grafikOksigen();
        myRef.child("biodata/-LjJl_zVp_Xy8ayOsiv2/timestamp/").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Arduino value = postSnapshot.getValue(Arduino.class);
                    txtJantung.setText(String.valueOf(value.getDataJantung()+" bpm"));
                    txtOksigen.setText(String.valueOf(value.getDataOksigen()+"%"));
                }
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
