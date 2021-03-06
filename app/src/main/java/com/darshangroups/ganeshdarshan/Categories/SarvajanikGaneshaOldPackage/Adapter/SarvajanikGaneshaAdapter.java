package com.darshangroups.ganeshdarshan.Categories.SarvajanikGaneshaOldPackage.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darshangroups.ganeshdarshan.Categories.SarvajanikGaneshaOldPackage.Details.SarvajanikGaneshaImageFragment;
import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class SarvajanikGaneshaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<GaneshaData> data = Collections.emptyList();
    GaneshaData current;
    int currentPos = 0;
    private Context context;
    private LayoutInflater inflater;

    // create constructor to innitilize context and data sent from shopOfferMainActivity
    public SarvajanikGaneshaAdapter(Context context, List<GaneshaData> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_s_g_container, parent, false);
        SarvajanikGaneshaAdapter.MyHolder holder = new SarvajanikGaneshaAdapter.MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in recyclerview to bind data and assign values from list
        SarvajanikGaneshaAdapter.MyHolder myHolder = (SarvajanikGaneshaAdapter.MyHolder) holder;
        GaneshaData current = data.get(position);
        myHolder.csharedby.setText(current.cshared_by);
        // load image into imageview using glide
        Picasso.with(context).load(current.cimg_path).placeholder(R.drawable.img_err_two).error(R.drawable.img_err_two).into(myHolder.cimg_path);
    }

    // return total item from List

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView csharedby;
        ImageView cimg_path;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            csharedby = itemView.findViewById(R.id.cshared_by);
            cimg_path = itemView.findViewById(R.id.cimg_path);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GaneshaData currentItem = data.get(getAdapterPosition());

                    Intent i = new Intent(v.getContext(), SarvajanikGaneshaImageFragment.class);
                    i.putExtra("nimg_id", currentItem.getnimg_id());
                    i.putExtra("cshared_by", currentItem.getcshared_by());
                    i.putExtra("cimg_path", currentItem.getcimg_path());
                    i.putExtra("cplace_name", currentItem.getcplace_name());
                    i.putExtra("cimage_name", currentItem.getcimage_name());
                    i.putExtra("caddress", currentItem.getcaddress());
                    i.putExtra("ccreated_by", currentItem.getccreated_by());
                    i.putExtra("cimg_caption", currentItem.getcimg_caption());
                    i.putExtra("ctitle", currentItem.getctitle());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}