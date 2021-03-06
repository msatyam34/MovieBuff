package com.example.moviebuff;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<ItemClass> itemList;

    public RecyclerViewAdapter(Context mContext, List<ItemClass> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(itemList.get(position).getTextView());

                Glide.with(mContext)
                .load(itemList.get(position).getImageView())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //this is the rawData which will be passed to MovieDetail Activity through intent.
            String data = createParcelableString();

            Intent intent = new Intent(mContext,MovieDetail.class);
            intent.putExtra("Data",data);


            mContext.startActivity(intent);


        }

        //method to convert all data into a string so that it will be easily passed through intent.
        private String createParcelableString(){
            String rawData = itemList.get(getLayoutPosition()).getTextView()+"@"+itemList.get(getLayoutPosition()).getImageView()
                    +"@"+itemList.get(getLayoutPosition()).getDescription()+"@"+itemList.get(getLayoutPosition()).getReleaseDate()
                    +"@"+itemList.get(getLayoutPosition()).getRating()+"@"+itemList.get(getLayoutPosition()).getReviews();
            return rawData;

        }
    }
}
