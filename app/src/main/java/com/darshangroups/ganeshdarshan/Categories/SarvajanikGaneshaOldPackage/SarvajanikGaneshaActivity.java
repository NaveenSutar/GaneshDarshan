package com.darshangroups.ganeshdarshan.Categories.SarvajanikGaneshaOldPackage;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.darshangroups.ganeshdarshan.Categories.SarvajanikGaneshaOldPackage.Adapter.SarvajanikGaneshaAdapter;
import com.darshangroups.ganeshdarshan.Data.GaneshaData;
import com.darshangroups.ganeshdarshan.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SarvajanikGaneshaActivity extends AppCompatActivity {

    //CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    private RecyclerView AngolView, AutoNagarView, BogarvesView, CampView, ChennammaView,
            FortRoadView, HindalgaView, HonagaView, KangraliView, KhanapurView, NehruNagarView,
            ShahapurView, TilakwadiView, VadagaonView;
    private SarvajanikGaneshaAdapter mAdapter;
    private CardView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14;
    private TextView t1;

    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_g_main);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);
        c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);
        c11 = findViewById(R.id.c11);
        c12 = findViewById(R.id.c12);
        c13 = findViewById(R.id.c13);
        c14 = findViewById(R.id.c14);
        t1 = findViewById(R.id.t1);
        //Make call to AsyncTask
        new AsyncFetch().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View child, int childPosition);
    }

    public class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(SarvajanikGaneshaActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mShimmerViewContainer.startShimmerAnimation();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://brainways.in/ganeshdarshan/sarvajanik.php?key=123");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return ("Unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(),"myURL "+url, Toast.LENGTH_LONG).show();
            //this method will be running on UI thread

            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);

            pdLoading.dismiss();

            List<GaneshaData> AngolData = new ArrayList<>();
            List<GaneshaData> AutoNagarData = new ArrayList<>();
            List<GaneshaData> BogarvesData = new ArrayList<>();
            List<GaneshaData> CampData = new ArrayList<>();
            List<GaneshaData> ChennammaData = new ArrayList<>();
            List<GaneshaData> FortRoadData = new ArrayList<>();
            List<GaneshaData> HindalgaData = new ArrayList<>();
            List<GaneshaData> HonagaData = new ArrayList<>();
            List<GaneshaData> KangraliData = new ArrayList<>();
            List<GaneshaData> KhanapurData = new ArrayList<>();
            List<GaneshaData> NehruNagarData = new ArrayList<>();
            List<GaneshaData> ShahapurData = new ArrayList<>();
            List<GaneshaData> TilakwadiData = new ArrayList<>();
            List<GaneshaData> VadagaonData = new ArrayList<>();

            if (result.equals("no")) {
                Toast.makeText(getApplicationContext(), "Sorry, Could not connect! Please Try again Later.", Toast.LENGTH_LONG).show();
            } else {
                try {
                    int i, j, k, l, m, n, o, p, q, r, s, t, u, v;

                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jArrayAngol = jsonObject.getJSONArray("Angol");
                    // Extract data from json and store into ArrayList as class objects
                    for (v = 0; v < jArrayAngol.length(); v++) {
                        JSONObject json_data = jArrayAngol.getJSONObject(v);
                        GaneshaData AD = new GaneshaData();
                        AD.nimg_id = json_data.getString("nimg_id");
                        AD.cimg_path = json_data.getString("cimg_path");
                        AD.cshared_by = json_data.getString("cshared_by");
                        AD.cplace_name = json_data.getString("cplace_name");
                        AD.cimg_caption = json_data.getString("cimg_caption");
                        AD.caddress = json_data.getString("caddress");
                        AD.cimage_name = json_data.getString("cimage_name");
                        AD.ccreated_by = json_data.getString("ccreated_by");
                        AD.cimg_caption = json_data.getString("cimg_caption");
                        AD.ctitle = json_data.getString("ctitle");
                        AngolData.add(AD);
                    }

                    if (v == 0) {
                        c1.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c1.setVisibility(View.VISIBLE);
                        AngolView = findViewById(R.id.angol_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, AngolData);
                        AngolView.setAdapter(mAdapter);
                        AngolView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayAutoNagar = jsonObject.getJSONArray("Auto Nagar");

                    // Extract data from json and store into ArrayList as class objects
                    for (i = 0; i < jArrayAutoNagar.length(); i++) {
                        JSONObject json_data = jArrayAutoNagar.getJSONObject(i);
                        GaneshaData AND = new GaneshaData();
                        AND.nimg_id = json_data.getString("nimg_id");
                        AND.cimg_path = json_data.getString("cimg_path");
                        AND.cshared_by = json_data.getString("cshared_by");
                        AND.cplace_name = json_data.getString("cplace_name");
                        AND.cimg_caption = json_data.getString("cimg_caption");
                        AND.caddress = json_data.getString("caddress");
                        AND.cimage_name = json_data.getString("cimage_name");
                        AND.ccreated_by = json_data.getString("ccreated_by");
                        AND.cimg_caption = json_data.getString("cimg_caption");
                        AND.ctitle = json_data.getString("ctitle");
                        AutoNagarData.add(AND);
                    }

                    if (i == 0)
                        c2.setVisibility(View.GONE);

                    else {
                        // Setup and Handover data to recyclerview
                        c2.setVisibility(View.VISIBLE);
                        AutoNagarView = findViewById(R.id.autonagar_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, AutoNagarData);
                        AutoNagarView.setAdapter(mAdapter);
                        AutoNagarView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayBogarves = jsonObject.getJSONArray("Bogarves");
                    // Extract data from json and store into ArrayList as class objects
                    for (j = 0; j < jArrayBogarves.length(); j++) {
                        JSONObject json_data = jArrayBogarves.getJSONObject(j);
                        GaneshaData BD = new GaneshaData();
                        BD.nimg_id = json_data.getString("nimg_id");
                        BD.cimg_path = json_data.getString("cimg_path");
                        BD.cshared_by = json_data.getString("cshared_by");
                        BD.cplace_name = json_data.getString("cplace_name");
                        BD.cimg_caption = json_data.getString("cimg_caption");
                        BD.caddress = json_data.getString("caddress");
                        BD.cimage_name = json_data.getString("cimage_name");
                        BD.ccreated_by = json_data.getString("ccreated_by");
                        BD.cimg_caption = json_data.getString("cimg_caption");
                        BD.ctitle = json_data.getString("ctitle");
                        BogarvesData.add(BD);
                    }

                    if (j == 0) {
                        c3.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c3.setVisibility(View.VISIBLE);
                        BogarvesView = findViewById(R.id.bogarves_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, BogarvesData);
                        BogarvesView.setAdapter(mAdapter);
                        BogarvesView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayCamp = jsonObject.getJSONArray("Camp");
                    // Extract data from json and store into ArrayList as class objects
                    for (k = 0; k < jArrayCamp.length(); k++) {
                        JSONObject json_data = jArrayCamp.getJSONObject(k);
                        GaneshaData CD = new GaneshaData();
                        CD.nimg_id = json_data.getString("nimg_id");
                        CD.cimg_path = json_data.getString("cimg_path");
                        CD.cshared_by = json_data.getString("cshared_by");
                        CD.cplace_name = json_data.getString("cplace_name");
                        CD.cimg_caption = json_data.getString("cimg_caption");
                        CD.caddress = json_data.getString("caddress");
                        CD.cimage_name = json_data.getString("cimage_name");
                        CD.ccreated_by = json_data.getString("ccreated_by");
                        CD.cimg_caption = json_data.getString("cimg_caption");
                        CD.ctitle = json_data.getString("ctitle");
                        CampData.add(CD);
                    }

                    if (k == 0) {
                        c4.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c4.setVisibility(View.VISIBLE);
                        CampView = findViewById(R.id.camp_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, CampData);
                        CampView.setAdapter(mAdapter);
                        CampView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayChennamma = jsonObject.getJSONArray("Chennamma");
                    // Extract data from json and store into ArrayList as class objects
                    for (l = 0; l < jArrayChennamma.length(); l++) {
                        JSONObject json_data = jArrayChennamma.getJSONObject(l);
                        GaneshaData ChD = new GaneshaData();
                        ChD.nimg_id = json_data.getString("nimg_id");
                        ChD.cimg_path = json_data.getString("cimg_path");
                        ChD.cshared_by = json_data.getString("cshared_by");
                        ChD.cplace_name = json_data.getString("cplace_name");
                        ChD.cimg_caption = json_data.getString("cimg_caption");
                        ChD.caddress = json_data.getString("caddress");
                        ChD.cimage_name = json_data.getString("cimage_name");
                        ChD.ccreated_by = json_data.getString("ccreated_by");
                        ChD.cimg_caption = json_data.getString("cimg_caption");
                        ChD.ctitle = json_data.getString("ctitle");
                        ChennammaData.add(ChD);
                    }

                    if (l == 0) {
                        c5.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c5.setVisibility(View.VISIBLE);
                        ChennammaView = findViewById(R.id.chennamma_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, ChennammaData);
                        ChennammaView.setAdapter(mAdapter);
                        ChennammaView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayFortRoad = jsonObject.getJSONArray("Fort Road");
                    // Extract data from json and store into ArrayList as class objects
                    for (m = 0; m < jArrayFortRoad.length(); m++) {
                        JSONObject json_data = jArrayFortRoad.getJSONObject(m);
                        GaneshaData FRD = new GaneshaData();
                        FRD.nimg_id = json_data.getString("nimg_id");
                        FRD.cimg_path = json_data.getString("cimg_path");
                        FRD.cshared_by = json_data.getString("cshared_by");
                        FRD.cplace_name = json_data.getString("cplace_name");
                        FRD.cimg_caption = json_data.getString("cimg_caption");
                        FRD.caddress = json_data.getString("caddress");
                        FRD.cimage_name = json_data.getString("cimage_name");
                        FRD.ccreated_by = json_data.getString("ccreated_by");
                        FRD.cimg_caption = json_data.getString("cimg_caption");
                        FRD.ctitle = json_data.getString("ctitle");
                        FortRoadData.add(FRD);
                    }

                    if (m == 0) {
                        c6.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c6.setVisibility(View.VISIBLE);
                        FortRoadView = findViewById(R.id.fortroad_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, FortRoadData);
                        FortRoadView.setAdapter(mAdapter);
                        FortRoadView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayHindalga = jsonObject.getJSONArray("Hindalga");
                    // Extract data from json and store into ArrayList as class objects
                    for (n = 0; n < jArrayHindalga.length(); n++) {
                        JSONObject json_data = jArrayHindalga.getJSONObject(n);
                        GaneshaData HD = new GaneshaData();
                        HD.nimg_id = json_data.getString("nimg_id");
                        HD.cimg_path = json_data.getString("cimg_path");
                        HD.cshared_by = json_data.getString("cshared_by");
                        HD.cplace_name = json_data.getString("cplace_name");
                        HD.cimg_caption = json_data.getString("cimg_caption");
                        HD.caddress = json_data.getString("caddress");
                        HD.cimage_name = json_data.getString("cimage_name");
                        HD.ccreated_by = json_data.getString("ccreated_by");
                        HD.cimg_caption = json_data.getString("cimg_caption");
                        HD.ctitle = json_data.getString("ctitle");
                        HindalgaData.add(HD);
                    }

                    if (n == 0) {
                        c7.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c7.setVisibility(View.VISIBLE);
                        HindalgaView = findViewById(R.id.hindalga_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, HindalgaData);
                        HindalgaView.setAdapter(mAdapter);
                        HindalgaView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayHonaga = jsonObject.getJSONArray("Honaga");
                    // Extract data from json and store into ArrayList as class objects
                    for (o = 0; o < jArrayHonaga.length(); o++) {
                        JSONObject json_data = jArrayHonaga.getJSONObject(o);
                        GaneshaData HoD = new GaneshaData();
                        HoD.nimg_id = json_data.getString("nimg_id");
                        HoD.cimg_path = json_data.getString("cimg_path");
                        HoD.cshared_by = json_data.getString("cshared_by");
                        HoD.cplace_name = json_data.getString("cplace_name");
                        HoD.cimg_caption = json_data.getString("cimg_caption");
                        HoD.caddress = json_data.getString("caddress");
                        HoD.cimage_name = json_data.getString("cimage_name");
                        HoD.ccreated_by = json_data.getString("ccreated_by");
                        HoD.cimg_caption = json_data.getString("cimg_caption");
                        HoD.ctitle = json_data.getString("ctitle");
                        HonagaData.add(HoD);
                    }

                    if (o == 0) {
                        c8.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c8.setVisibility(View.VISIBLE);
                        HonagaView = findViewById(R.id.honaga_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, HonagaData);
                        HonagaView.setAdapter(mAdapter);
                        HonagaView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayKangrali = jsonObject.getJSONArray("Kangrali");
                    // Extract data from json and store into ArrayList as class objects
                    for (p = 0; p < jArrayKangrali.length(); p++) {
                        JSONObject json_data = jArrayKangrali.getJSONObject(p);
                        GaneshaData KD = new GaneshaData();
                        KD.nimg_id = json_data.getString("nimg_id");
                        KD.cimg_path = json_data.getString("cimg_path");
                        KD.cshared_by = json_data.getString("cshared_by");
                        KD.cplace_name = json_data.getString("cplace_name");
                        KD.cimg_caption = json_data.getString("cimg_caption");
                        KD.caddress = json_data.getString("caddress");
                        KD.cimage_name = json_data.getString("cimage_name");
                        KD.ccreated_by = json_data.getString("ccreated_by");
                        KD.cimg_caption = json_data.getString("cimg_caption");
                        KD.ctitle = json_data.getString("ctitle");
                        KangraliData.add(KD);
                    }

                    if (p == 0) {
                        c9.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c9.setVisibility(View.VISIBLE);
                        KangraliView = findViewById(R.id.kangrali_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, KangraliData);
                        KangraliView.setAdapter(mAdapter);
                        KangraliView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayKhanapur = jsonObject.getJSONArray("Khanapur");
                    // Extract data from json and store into ArrayList as class objects
                    for (q = 0; q < jArrayKhanapur.length(); q++) {
                        JSONObject json_data = jArrayKhanapur.getJSONObject(q);
                        GaneshaData KhD = new GaneshaData();
                        KhD.nimg_id = json_data.getString("nimg_id");
                        KhD.cimg_path = json_data.getString("cimg_path");
                        KhD.cshared_by = json_data.getString("cshared_by");
                        KhD.cplace_name = json_data.getString("cplace_name");
                        KhD.cimg_caption = json_data.getString("cimg_caption");
                        KhD.caddress = json_data.getString("caddress");
                        KhD.cimage_name = json_data.getString("cimage_name");
                        KhD.ccreated_by = json_data.getString("ccreated_by");
                        KhD.cimg_caption = json_data.getString("cimg_caption");
                        KhD.ctitle = json_data.getString("ctitle");
                        KhanapurData.add(KhD);
                    }

                    if (q == 0) {
                        c10.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c10.setVisibility(View.VISIBLE);
                        KhanapurView = findViewById(R.id.khanapur_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, KhanapurData);
                        KhanapurView.setAdapter(mAdapter);
                        KhanapurView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayNehruNagar = jsonObject.getJSONArray("Nehru Nagar");
                    // Extract data from json and store into ArrayList as class objects
                    for (r = 0; r < jArrayNehruNagar.length(); r++) {
                        JSONObject json_data = jArrayNehruNagar.getJSONObject(r);
                        GaneshaData NND = new GaneshaData();
                        NND.nimg_id = json_data.getString("nimg_id");
                        NND.cimg_path = json_data.getString("cimg_path");
                        NND.cshared_by = json_data.getString("cshared_by");
                        NND.cplace_name = json_data.getString("cplace_name");
                        NND.cimg_caption = json_data.getString("cimg_caption");
                        NND.caddress = json_data.getString("caddress");
                        NND.cimage_name = json_data.getString("cimage_name");
                        NND.ccreated_by = json_data.getString("ccreated_by");
                        NND.cimg_caption = json_data.getString("cimg_caption");
                        NND.ctitle = json_data.getString("ctitle");
                        NehruNagarData.add(NND);
                    }

                    if (r == 0) {
                        c11.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c11.setVisibility(View.VISIBLE);
                        NehruNagarView = findViewById(R.id.nehrunagar_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, NehruNagarData);
                        NehruNagarView.setAdapter(mAdapter);
                        NehruNagarView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayShahapur = jsonObject.getJSONArray("Shahapur");
                    // Extract data from json and store into ArrayList as class objects
                    for (s = 0; s < jArrayShahapur.length(); s++) {
                        JSONObject json_data = jArrayShahapur.getJSONObject(s);
                        GaneshaData SD = new GaneshaData();
                        SD.nimg_id = json_data.getString("nimg_id");
                        SD.cimg_path = json_data.getString("cimg_path");
                        SD.cshared_by = json_data.getString("cshared_by");
                        SD.cplace_name = json_data.getString("cplace_name");
                        SD.cimg_caption = json_data.getString("cimg_caption");
                        SD.caddress = json_data.getString("caddress");
                        SD.cimage_name = json_data.getString("cimage_name");
                        SD.ccreated_by = json_data.getString("ccreated_by");
                        SD.cimg_caption = json_data.getString("cimg_caption");
                        SD.ctitle = json_data.getString("ctitle");
                        ShahapurData.add(SD);
                    }

                    if (s == 0) {
                        c12.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c12.setVisibility(View.VISIBLE);
                        ShahapurView = findViewById(R.id.shahpur_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, ShahapurData);
                        ShahapurView.setAdapter(mAdapter);
                        ShahapurView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayTilakwadi = jsonObject.getJSONArray("Tilakwadi");
                    // Extract data from json and store into ArrayList as class objects
                    for (t = 0; t < jArrayTilakwadi.length(); t++) {
                        JSONObject json_data = jArrayTilakwadi.getJSONObject(t);
                        GaneshaData TD = new GaneshaData();
                        TD.nimg_id = json_data.getString("nimg_id");
                        TD.cimg_path = json_data.getString("cimg_path");
                        TD.cshared_by = json_data.getString("cshared_by");
                        TD.cplace_name = json_data.getString("cplace_name");
                        TD.cimg_caption = json_data.getString("cimg_caption");
                        TD.caddress = json_data.getString("caddress");
                        TD.cimage_name = json_data.getString("cimage_name");
                        TD.ccreated_by = json_data.getString("ccreated_by");
                        TD.cimg_caption = json_data.getString("cimg_caption");
                        TD.ctitle = json_data.getString("ctitle");
                        TilakwadiData.add(TD);
                    }

                    if (t == 0) {
                        c13.setVisibility(View.GONE);
                    } else {
                        // Setup and Handover data to recyclerview
                        c13.setVisibility(View.VISIBLE);
                        TilakwadiView = findViewById(R.id.tilakwadi_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, TilakwadiData);
                        TilakwadiView.setAdapter(mAdapter);
                        TilakwadiView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    JSONArray jArrayVadagaon = jsonObject.getJSONArray("Vadagaon");
                    // Extract data from json and store into ArrayList as class objects
                    for (u = 0; u < jArrayVadagaon.length(); u++) {
                        JSONObject json_data = jArrayVadagaon.getJSONObject(u);
                        GaneshaData VD = new GaneshaData();
                        VD.nimg_id = json_data.getString("nimg_id");
                        VD.cimg_path = json_data.getString("cimg_path");
                        VD.cshared_by = json_data.getString("cshared_by");
                        VD.cplace_name = json_data.getString("cplace_name");
                        VD.cimg_caption = json_data.getString("cimg_caption");
                        VD.caddress = json_data.getString("caddress");
                        VD.cimage_name = json_data.getString("cimage_name");
                        VD.ccreated_by = json_data.getString("ccreated_by");
                        VD.cimg_caption = json_data.getString("cimg_caption");
                        VD.ctitle = json_data.getString("ctitle");
                        VadagaonData.add(VD);
                    }

                    if (u == 0) {
                        c14.setVisibility(View.GONE);
                    } else {
                        c14.setVisibility(View.VISIBLE);
                        VadagaonView = findViewById(R.id.vadagaon_rcview);
                        mAdapter = new SarvajanikGaneshaAdapter(SarvajanikGaneshaActivity.this, VadagaonData);
                        VadagaonView.setAdapter(mAdapter);
                        VadagaonView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }
                    // Setup and Handover data to recyclerview


                } catch (JSONException e) {
                    c1.setVisibility(View.GONE);
                    c2.setVisibility(View.GONE);
                    c3.setVisibility(View.GONE);
                    c4.setVisibility(View.GONE);
                    c5.setVisibility(View.GONE);
                    c6.setVisibility(View.GONE);
                    c7.setVisibility(View.GONE);
                    c8.setVisibility(View.GONE);
                    c9.setVisibility(View.GONE);
                    c10.setVisibility(View.GONE);
                    c11.setVisibility(View.GONE);
                    c12.setVisibility(View.GONE);
                    c13.setVisibility(View.GONE);
                    c14.setVisibility(View.GONE);
                    t1.setVisibility(View.GONE);
                    //Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(SarvajanikGaneshaActivity.this, "Unable to fetch the data.Please try after sometime.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}