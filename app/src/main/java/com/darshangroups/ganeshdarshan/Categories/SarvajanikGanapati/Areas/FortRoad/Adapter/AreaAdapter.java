package com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.FortRoad.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.FortRoad.FortRoadActivity;
import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<GaneshaData> data;
    private List<GaneshaData> dataFiltered;
    private AreaAdapterListener listener;

    public AreaAdapter(Context context, List<GaneshaData> contactList, FortRoadActivity listener) {
        this.context = context;
        this.listener = listener;
        this.data = contactList;
        this.dataFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_s_g_container_new, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final GaneshaData data = dataFiltered.get(position);
        holder.cshared_by.setText(data.getcshared_by());
        /*holder.cplace_name.setText(data.getcplace_name());
        holder.cimg_caption.setText(data.getcimg_caption());*/
        //Glide.with(context).load(data.cimg_path).placeholder(R.drawable.img_err_two).error(R.drawable.img_err_two).into(holder.cimg_path);

        Picasso.with(context).load(data.cimg_path).fit().noFade().centerCrop().placeholder(R.drawable.img_err_two).error(R.drawable.img_err_two).into(holder.cimg_path);

        //Glide.with(context).load(data.getcimg_path()).apply(RequestOptions.circleCropTransform()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return dataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataFiltered = data;
                } else {
                    List<GaneshaData> filteredList = new ArrayList<>();
                    for (GaneshaData row : data) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        /*if (row.getcshared_by().toLowerCase().startsWith(charString.toLowerCase()) || row.getcplace_name().contains(charSequence)) {
                            filteredList.add(row);
                        }*/
                        if (row.getcshared_by().toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    dataFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataFiltered = (ArrayList<GaneshaData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface AreaAdapterListener {
        void onDataSelected(GaneshaData data);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView cshared_by, cplace_name, cimg_caption;
        private ImageView cimg_path;

        MyViewHolder(View view) {
            super(view);

            cimg_path = itemView.findViewById(R.id.pgimg);
            cshared_by = itemView.findViewById(R.id.pgsharedby);
            /*cplace_name = itemView.findViewById(R.id.pgplacename);
            cimg_caption = itemView.findViewById(R.id.pgcaption);*/

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected data in callback
                    listener.onDataSelected(dataFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}