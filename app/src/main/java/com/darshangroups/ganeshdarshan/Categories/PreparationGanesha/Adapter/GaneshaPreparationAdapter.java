package com.darshangroups.ganeshdarshan.Categories.PreparationGanesha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.darshangroups.ganeshdarshan.Categories.PreparationGanesha.Details.GaneshaPreparationImagesActivity;
import com.darshangroups.ganeshdarshan.Categories.PreparationGanesha.Details.GaneshaPreparationImagesActivityMain;
import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;

import java.util.Collections;
import java.util.List;

public class GaneshaPreparationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<GaneshaData> data= Collections.emptyList();
    GaneshaData current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from shopOfferMainActivity
    public GaneshaPreparationAdapter(Context context, List<GaneshaData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_p_g_container, parent,false);
        GaneshaPreparationAdapter.MyHolder holder=new GaneshaPreparationAdapter.MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in recyclerview to bind data and assign values from list
        GaneshaPreparationAdapter.MyHolder myHolder= (GaneshaPreparationAdapter.MyHolder) holder;
        GaneshaData current=data.get(position);
        myHolder.cshared_by.setText(current.cshared_by);
        myHolder.cplace_name.setText(current.cplace_name);
        myHolder.cimg_caption.setText(current.cimg_caption);
        // load image into imageview using glide
        Glide.with(context).load(current.cimg_path).placeholder(R.drawable.pray).error(R.drawable.pray).into(myHolder.img_path);
    }

    // return total item from List

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView cshared_by, cplace_name, cimg_caption;
        ImageView img_path;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            img_path = (ImageView) itemView.findViewById(R.id.pgimg);
            cshared_by = (TextView) itemView.findViewById(R.id.pgsharedby);
            cplace_name = (TextView) itemView.findViewById(R.id.pgplacename);
            cimg_caption = (TextView) itemView.findViewById(R.id.pgcaption);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GaneshaData currentItem = data.get(getAdapterPosition());

                    //Toast.makeText(context, "Clicked on\t"+currentItem.getcshared_by()+" Place name is="+currentItem.getcplace_name(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(context, , Toast.LENGTH_LONG).show();
                    //Toast.makeText(context, "You have clicked on\t"+currentItem.getoffer_Name()+currentItem.getoffer_ends(), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(v.getContext(),GaneshaPreparationImagesActivityMain.class);
                    i.putExtra("cshared_by", currentItem.getcshared_by());
                    i.putExtra("cimg_path", currentItem.getcimg_path());
                    i.putExtra("cplace_name", currentItem.getcplace_name());
                    //i.putExtra("cimage_name",currentItem.getcimage_name() );
                    //i.putExtra("caddress", currentItem.getcaddress());
                    //i.putExtra("ccreated_by", currentItem.getccreated_by());
                    //i.putExtra("cimg_caption", currentItem.getcimg_caption());
                    //i.putExtra("ctitle", currentItem.getctitle());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}