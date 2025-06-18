package com.example.communihelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Notify2Adapter extends RecyclerView.Adapter<Notify2Adapter.ViewHolder> {

    private Context context;
    private List<Notify2Model> itemList;

    public Notify2Adapter(Context context, List<Notify2Model> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, detailsTextView, addressTextView;
        Button reviewButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            reviewButton = itemView.findViewById(R.id.reviewButton);
        }
    }

    @NonNull
    @Override
    public Notify2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notify2_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notify2Adapter.ViewHolder holder, int position) {
        Notify2Model item = itemList.get(position);

        holder.nameTextView.setText(item.getName());
        holder.detailsTextView.setText(item.getDetails());
        holder.addressTextView.setText(item.getAddress());

        holder.reviewButton.setOnClickListener(v ->
                Toast.makeText(context, "Review clicked for: " + item.getName(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

