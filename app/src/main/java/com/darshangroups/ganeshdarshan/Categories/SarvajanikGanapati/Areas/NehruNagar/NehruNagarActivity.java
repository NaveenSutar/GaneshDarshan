package com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.NehruNagar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.darshangroups.ganeshdarshan.Categories.HomeGanesha.Adapter.HomeGaneshaVollyInit;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.NehruNagar.Adapter.AreaAdapter;
import com.darshangroups.ganeshdarshan.Categories.SarvajanikGanapati.Areas.NehruNagar.Details.SwipeActivity;
import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class NehruNagarActivity extends AppCompatActivity implements AreaAdapter.AreaAdapterListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = NehruNagarActivity.class.getSimpleName();
    // url to fetch contacts json
    private static final String URL = "http://brainways.in/ganeshdarshan/areas/nehru_nagar.php?key=123";
    private List<GaneshaData> data;
    private RecyclerView recyclerView;
    private AreaAdapter mAreaAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    //private TextView swipeinfo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String mJsonResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_g_main_new);

        data = new ArrayList<>();

        mAreaAdapter = new AreaAdapter(this, data, this);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        swipeRefreshLayout = findViewById(R.id.swifeRefresh);
        recyclerView = findViewById(R.id.ganesha_preparation_list);
        //swipeinfo = (TextView) findViewById(R.id.swipeinfo);

        /*
            //To show list in grid view
            recyclerView.setAdapter(mAreaAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(VadagaonActivity.this, 2));
        */

        /*
            //To show list in single row
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAreaAdapter);
        */

        recyclerView.setAdapter(mAreaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(NehruNagarActivity.this, 3));

        swipeRefreshLayout.setOnRefreshListener(this);
        fetchData();
    }

    @Override
    public void onRefresh() {
        fetchData();
        //swipeinfo.setVisibility(View.GONE);
    }

    /**
     * fetches json by making http calls
     */
    private void fetchData() {
        JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Couldn't fetch the data! Pleas try again.", Toast.LENGTH_LONG).show();
                    return;
                }

                mJsonResponse = response.toString();
                List<GaneshaData> items = new Gson().fromJson(response.toString(), new TypeToken<List<GaneshaData>>() {
                }.getType());

                // adding contacts to contacts list
                data.clear();
                data.addAll(items);

                // refreshing recycler view
                mAreaAdapter.notifyDataSetChanged();

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                //swipeinfo.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        HomeGaneshaVollyInit.getInstance().addToRequestQueue(request);
    }

    @Override
    public void onDataSelected(GaneshaData data) {
        Intent intent = new Intent(this, SwipeActivity.class);
        intent.putExtra("data", mJsonResponse);
        intent.putExtra("nimg_id", data.getnimg_id());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        //swipeinfo.setVisibility(View.INVISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}