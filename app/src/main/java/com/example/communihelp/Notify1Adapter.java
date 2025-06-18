package com.example.communihelp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Notify1Adapter extends RecyclerView.Adapter<Notify1Adapter.Notify1ViewHolder> {

    private List<Notify1Model> notifyList;

    public Notify1Adapter(List<Notify1Model> notifyList) {
        this.notifyList = notifyList;
    }

    @NonNull
    @Override
    public Notify1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notify1_item, parent, false);
        return new Notify1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notify1ViewHolder holder, int position) {
        Notify1Model model = notifyList.get(position);

        holder.titleTextView.setText(model.getTitle());
        holder.messageTextView.setText(model.getMessage());
        holder.yesTextView.setText("Yes"); // Or model.getYesText() if dynamic
        holder.noTextView.setText("No");   // Or model.getNoText() if dynamic
    }

    @Override
    public int getItemCount() {
        return notifyList.size();
    }

    public static class Notify1ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, messageTextView, yesTextView, noTextView;

        public Notify1ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            yesTextView = itemView.findViewById(R.id.yesTextView);
            noTextView = itemView.findViewById(R.id.noTextView);
        }
    }
}

