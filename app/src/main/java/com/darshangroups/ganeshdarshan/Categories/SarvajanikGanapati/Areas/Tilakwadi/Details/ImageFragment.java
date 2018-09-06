package com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.Tilakwadi.Details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darshangroups.ganeshdarshan.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {
    private PhotoView imageView;
    private TextView tvCshared_by, tvCplacename, tvCtitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_image_container, container, false);

        imageView = rootView.findViewById(R.id.image);
        tvCshared_by = rootView.findViewById(R.id.sharedby);
        tvCplacename = rootView.findViewById(R.id.placename);
        tvCtitle = rootView.findViewById(R.id.ctitle);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String cimg_path = bundle.getString("cimg_path", null);
            String cshared_by = bundle.getString("cshared_by", null);
            String caddress = bundle.getString("caddress", null);
            String ctitle = bundle.getString("ctitle", null);


            Picasso.with(getActivity()).load(cimg_path).placeholder(R.drawable.img_err_two).error(R.drawable.img_err_two).into(imageView);
            tvCtitle.setText(ctitle);
            tvCshared_by.setText("Shared By : " + cshared_by);
            tvCplacename.setText("Address : " + caddress);
        }
        return rootView;
    }
}