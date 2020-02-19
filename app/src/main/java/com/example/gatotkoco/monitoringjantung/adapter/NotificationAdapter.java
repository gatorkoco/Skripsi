package com.example.gatotkoco.monitoringjantung.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gatotkoco.monitoringjantung.R;
import com.example.gatotkoco.monitoringjantung.model.Notification;

import java.util.ArrayList;

/**
 * Created by GATOT KOCO on 17/07/2019.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Viewholder> {

    Context context;
    ArrayList<Notification> notifications;

    public NotificationAdapter(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int i) {
        holder.txtPasien.setText(notifications.get(i).getPasien());
        holder.txtKeterangan.setText(notifications.get(i).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txtPasien, txtKeterangan;
        public Viewholder(View itemView) {
            super(itemView);
            txtPasien = itemView.findViewById(R.id.txtPasien);
            txtKeterangan = itemView.findViewById(R.id.txtKeterangan);
        }
    }
}
