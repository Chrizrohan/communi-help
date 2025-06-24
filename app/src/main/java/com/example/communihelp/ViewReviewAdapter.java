package com.example.communihelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewReviewAdapter extends RecyclerView.Adapter<ViewReviewAdapter.ViewHolder> {

    private Context context;
    private List<ViewReviewModel> reviewList;

    public ViewReviewAdapter(Context context, List<ViewReviewModel> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCategory, tvDetails, textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            textView2 = itemView.findViewById(R.id.textView2); // Star rating
        }
    }

    @Override
    public ViewReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewreviewitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewReviewAdapter.ViewHolder holder, int position) {
        ViewReviewModel item = reviewList.get(position);

        holder.tvNameCategory.setText("Name : " + item.getName() + "   " + item.getCategory());
        holder.tvDetails.setText("- " + item.getDetails());
        holder.textView2.setText(item.getStars());


    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}
