package com.example.gatotkoco.monitoringjantung.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gatotkoco.monitoringjantung.R;
import com.example.gatotkoco.monitoringjantung.model.Arduino;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by GATOT KOCO on 17/07/2019.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Viewholder> {

    Context context;
    ArrayList<Arduino> arduinos;

    public HistoryAdapter(Context context, ArrayList<Arduino> arduinos) {
        this.context = context;
        this.arduinos = arduinos;
    }

    @Override
    public HistoryAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int i) {
        String string = String.valueOf(arduinos.get(i).getCreated_at());
        String[] parts = string.split("T");
        String part1 = parts[0];
        String[] part2 = parts[1].split("Z");
        String part3= part2[0];
        holder.txtJantung.setText("Detak Jantung: "+String.valueOf(arduinos.get(i).getDataJantung()+" bpm"));
        holder.txtOksigen.setText("Oksigen: "+String.valueOf(arduinos.get(i).getDataOksigen())+"%");
        holder.txtLastUpdated.setText("Last Updated: "+ part1 + " " + part3);
    }

    @Override
    public int getItemCount() {
        return arduinos.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView txtJantung,txtOksigen,txtLastUpdated;
        public Viewholder(View itemView) {
            super(itemView);
            txtJantung = itemView.findViewById(R.id.txtJantung);
            txtOksigen = itemView.findViewById(R.id.txtOksigen);
            txtLastUpdated = itemView.findViewById(R.id.txtLastUpdated);

        }
    }
}
