package com.darshangroups.ganeshdarshan.Categories.HomeGanesha.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeGaneshaAdapter extends RecyclerView.Adapter<HomeGaneshaAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<GaneshaData> data;
    private List<GaneshaData> dataFiltered;
    private HomeGaneshaTwoAdapterListener listener;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView cshared_by, cplace_name;
        private ImageView img_path;

        MyViewHolder(View view) {
            super(view);

            img_path = itemView.findViewById(R.id.hgimg);
            cshared_by = itemView.findViewById(R.id.hgsharedby);
            cplace_name = itemView.findViewById(R.id.hgplacename);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected data in callback
                    listener.onDataSelected(dataFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public HomeGaneshaAdapter(Context context, List<GaneshaData> contactList, HomeGaneshaTwoAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.data = contactList;
        this.dataFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_h_g_container, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final GaneshaData data = dataFiltered.get(position);
        holder.cshared_by.setText(data.getcshared_by());
        holder.cplace_name.setText(data.getcaddress());
        Picasso.with(context).load(data.cimg_path).fit().noFade().centerCrop().placeholder(R.drawable.img_err).error(R.drawable.img_err).into(holder.img_path);
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

    public interface HomeGaneshaTwoAdapterListener {
        void onDataSelected(GaneshaData data);
    }
}