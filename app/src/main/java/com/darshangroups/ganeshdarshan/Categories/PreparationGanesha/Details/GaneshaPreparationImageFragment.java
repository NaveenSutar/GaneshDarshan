package com.darshangroups.ganeshdarshan.Categories.PreparationGanesha.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darshangroups.ganeshdarshan.R;

public class GaneshaPreparationImageFragment extends AppCompatActivity {

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_image);
        getInfo();
    }

    private void getInfo(){
        if(getIntent().hasExtra("cimg_path")&& getIntent().hasExtra("cshared_by")&& getIntent().hasExtra("cplace_name")){
            String img = getIntent().getStringExtra("cimg_path");
            String shby = getIntent().getStringExtra("cshared_by");
            String place = getIntent().getStringExtra("cplace_name");
            String title = getIntent().getStringExtra("ctitle");
            setInfo(img,shby,place,title);
        }
    }

    private void setInfo(String img, String shby, String place, String title){
        TextView share = (TextView)findViewById(R.id.sharedby);
        share.setText("Shared By : " + shby);

        TextView jaga = (TextView)findViewById(R.id.placename);
        jaga.setText("From : " + place);

        TextView hesar = (TextView)findViewById(R.id.ctitle);
        hesar.setText(title);

        image = (ImageView)findViewById(R.id.image);

        Glide.with(this).load(img).placeholder(R.drawable.pray).error(R.drawable.pray).into(image);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            image.setScaleX(mScaleFactor);
            image.setScaleY(mScaleFactor);
            return true;
        }
    }
}