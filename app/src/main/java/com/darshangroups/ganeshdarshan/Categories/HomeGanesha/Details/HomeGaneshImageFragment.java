package com.darshangroups.ganeshdarshan.Categories.HomeGanesha.Details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.darshangroups.ganeshdarshan.R;

public class HomeGaneshImageFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater .inflate(R.layout.fragment_home_ganesh_image, container, false);

        ImageView imageView = rootView.findViewById(R.id.iv_home_ganesh);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String imageUrl = bundle.getString("cimg_path", null);
            Glide.with(getActivity()).load(imageUrl).placeholder(R.drawable.pray).error(R.drawable.pray).into(imageView);
        }

        return rootView;
    }
}
