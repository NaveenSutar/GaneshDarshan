package com.darshangroups.ganeshdarshan.Categories.SarvajanikGanesha;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darshangroups.ganeshdarshan.Categories.PreparationGanesha.PreparationActivity;
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

public class SarvajanikActivity //extends ListActivity {
    // @Override
    // public void onCreate(Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState);

        //storing string resources into Array
        // String[] area_list = getResources().getStringArray(R.array.area_list);

        //Binding resources Array to ListAdapter
        // this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_s_g_main, R.id.label, area_list));

        // ListView lv = getListView();

        //listening to single list item on click
        // lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // public void onItemClick(AdapterView<?> parent, View view,
                                    // int position, long id) {

                //selected item
                // String area = ((TextView) view).getText().toString();

                //Launching new Activity on selecting single List Item
                // Intent i = new Intent(getApplicationContext(), SarvaImagesActivity.class);
                //sending data to new activity
                // i.putExtra("area", area);
                // startActivity(i);

            // }
        // });
    // }
// }


extends AppCompatActivity {

    //CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView AngolView, AutoNagarView, BogarvesView, CampView, ChennammaView, FortRoadView, HindalgaView, HonagaView, KangraliView, KhanapurView, NehruNagarView, ShahapurView, TilakwadiView, VadagaonView;
    private SarvajanikGaneshaAdapter mAdapter;

    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_g_main);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
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
        ProgressDialog pdLoading = new ProgressDialog(SarvajanikActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            /*pdLoading.setMessage("\tWait a second...");
            pdLoading.setCancelable(false);
            pdLoading.show();*/

            mShimmerViewContainer.startShimmerAnimation();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://192.168.1.13/ganappa/sarvajanik.php?key=123");

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

            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);

            //Toast.makeText(getApplicationContext(),"myURL "+url, Toast.LENGTH_LONG).show();
            //this method will be running on UI thread

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
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jArrayAngol = jsonObject.getJSONArray("Angol");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayAngol.length(); i++) {
                        JSONObject json_data = jArrayAngol.getJSONObject(i);
                        GaneshaData AD = new GaneshaData();
                        AD.nimg_id = json_data.getString("nimg_id");
                        AD.cimg_path = json_data.getString("cimg_path");
                        AD.cshared_by = json_data.getString("cshared_by");
                        AD.cplace_name = json_data.getString("cplace_name");
                        AD.cimg_caption = json_data.getString("cimg_caption");
                        AD.caddress = json_data.getString("caddress");
                        AngolData.add(AD);
                    }

                    // Setup and Handover data to recyclerview
                    AngolView = (RecyclerView) findViewById(R.id.angol_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, AngolData);
                    AngolView.setAdapter(mAdapter);
                    AngolView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayAutoNagar = jsonObject.getJSONArray("Auto Nagar");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayAutoNagar.length(); i++) {
                        JSONObject json_data = jArrayAutoNagar.getJSONObject(i);
                        GaneshaData AND = new GaneshaData();
                        AND.nimg_id = json_data.getString("nimg_id");
                        AND.cimg_path = json_data.getString("cimg_path");
                        AND.cshared_by = json_data.getString("cshared_by");
                        AND.cplace_name = json_data.getString("cplace_name");
                        AND.cimg_caption = json_data.getString("cimg_caption");
                        AND.caddress = json_data.getString("caddress");
                        AutoNagarData.add(AND);
                    }

                    // Setup and Handover data to recyclerview
                    AutoNagarView = (RecyclerView) findViewById(R.id.autonagar_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, AutoNagarData);
                    AutoNagarView.setAdapter(mAdapter);
                    AutoNagarView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayBogarves = jsonObject.getJSONArray("Bogarves");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayBogarves.length(); i++) {
                        JSONObject json_data = jArrayBogarves.getJSONObject(i);
                        GaneshaData BD = new GaneshaData();
                        BD.nimg_id = json_data.getString("nimg_id");
                        BD.cimg_path = json_data.getString("cimg_path");
                        BD.cshared_by = json_data.getString("cshared_by");
                        BD.cplace_name = json_data.getString("cplace_name");
                        BD.cimg_caption = json_data.getString("cimg_caption");
                        BD.caddress = json_data.getString("caddress");
                        BogarvesData.add(BD);
                    }

                    // Setup and Handover data to recyclerview
                    BogarvesView = (RecyclerView) findViewById(R.id.bogarves_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, BogarvesData);
                    BogarvesView.setAdapter(mAdapter);
                    BogarvesView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayCamp = jsonObject.getJSONArray("Camp");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayCamp.length(); i++) {
                        JSONObject json_data = jArrayCamp.getJSONObject(i);
                        GaneshaData CD = new GaneshaData();
                        CD.nimg_id = json_data.getString("nimg_id");
                        CD.cimg_path = json_data.getString("cimg_path");
                        CD.cshared_by = json_data.getString("cshared_by");
                        CD.cplace_name = json_data.getString("cplace_name");
                        CD.cimg_caption = json_data.getString("cimg_caption");
                        CD.caddress = json_data.getString("caddress");
                        CampData.add(CD);
                    }

                    // Setup and Handover data to recyclerview
                    CampView = (RecyclerView) findViewById(R.id.camp_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, CampData);
                    CampView.setAdapter(mAdapter);
                    CampView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayChennamma = jsonObject.getJSONArray("Chennamma");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayChennamma.length(); i++) {
                        JSONObject json_data = jArrayChennamma.getJSONObject(i);
                        GaneshaData ChD = new GaneshaData();
                        ChD.nimg_id = json_data.getString("nimg_id");
                        ChD.cimg_path = json_data.getString("cimg_path");
                        ChD.cshared_by = json_data.getString("cshared_by");
                        ChD.cplace_name = json_data.getString("cplace_name");
                        ChD.cimg_caption = json_data.getString("cimg_caption");
                        ChD.caddress = json_data.getString("caddress");
                        ChennammaData.add(ChD);
                    }

                    // Setup and Handover data to recyclerview
                    ChennammaView = (RecyclerView) findViewById(R.id.chennamma_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, ChennammaData);
                    ChennammaView.setAdapter(mAdapter);
                    ChennammaView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayFortRoad = jsonObject.getJSONArray("Fort Road");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayFortRoad.length(); i++) {
                        JSONObject json_data = jArrayFortRoad.getJSONObject(i);
                        GaneshaData FRD = new GaneshaData();
                        FRD.nimg_id = json_data.getString("nimg_id");
                        FRD.cimg_path = json_data.getString("cimg_path");
                        FRD.cshared_by = json_data.getString("cshared_by");
                        FRD.cplace_name = json_data.getString("cplace_name");
                        FRD.cimg_caption = json_data.getString("cimg_caption");
                        FRD.caddress = json_data.getString("caddress");
                        FortRoadData.add(FRD);
                    }

                    // Setup and Handover data to recyclerview
                    FortRoadView = (RecyclerView) findViewById(R.id.fortroad_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, FortRoadData);
                    FortRoadView.setAdapter(mAdapter);
                    FortRoadView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayHindalga = jsonObject.getJSONArray("Hindalga");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayHindalga.length(); i++) {
                        JSONObject json_data = jArrayHindalga.getJSONObject(i);
                        GaneshaData HD = new GaneshaData();
                        HD.nimg_id = json_data.getString("nimg_id");
                        HD.cimg_path = json_data.getString("cimg_path");
                        HD.cshared_by = json_data.getString("cshared_by");
                        HD.cplace_name = json_data.getString("cplace_name");
                        HD.cimg_caption = json_data.getString("cimg_caption");
                        HD.caddress = json_data.getString("caddress");
                        HindalgaData.add(HD);
                    }

                    // Setup and Handover data to recyclerview
                    HindalgaView = (RecyclerView) findViewById(R.id.hindalga_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, HindalgaData);
                    HindalgaView.setAdapter(mAdapter);
                    HindalgaView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayHonaga = jsonObject.getJSONArray("Honaga");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayHonaga.length(); i++) {
                        JSONObject json_data = jArrayHonaga.getJSONObject(i);
                        GaneshaData HoD = new GaneshaData();
                        HoD.nimg_id = json_data.getString("nimg_id");
                        HoD.cimg_path = json_data.getString("cimg_path");
                        HoD.cshared_by = json_data.getString("cshared_by");
                        HoD.cplace_name = json_data.getString("cplace_name");
                        HoD.cimg_caption = json_data.getString("cimg_caption");
                        HoD.caddress = json_data.getString("caddress");
                        HonagaData.add(HoD);
                    }

                    // Setup and Handover data to recyclerview
                    HonagaView = (RecyclerView) findViewById(R.id.honaga_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, HonagaData);
                    HonagaView.setAdapter(mAdapter);
                    HonagaView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayKangrali = jsonObject.getJSONArray("Kangrali");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayKangrali.length(); i++) {
                        JSONObject json_data = jArrayKangrali.getJSONObject(i);
                        GaneshaData KD = new GaneshaData();
                        KD.nimg_id = json_data.getString("nimg_id");
                        KD.cimg_path = json_data.getString("cimg_path");
                        KD.cshared_by = json_data.getString("cshared_by");
                        KD.cplace_name = json_data.getString("cplace_name");
                        KD.cimg_caption = json_data.getString("cimg_caption");
                        KD.caddress = json_data.getString("caddress");
                        KangraliData.add(KD);
                    }

                    // Setup and Handover data to recyclerview
                    KangraliView = (RecyclerView) findViewById(R.id.kangrali_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, KangraliData);
                    KangraliView.setAdapter(mAdapter);
                    KangraliView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayKhanapur = jsonObject.getJSONArray("Khanapur");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayKhanapur.length(); i++) {
                        JSONObject json_data = jArrayKhanapur.getJSONObject(i);
                        GaneshaData KhD = new GaneshaData();
                        KhD.nimg_id = json_data.getString("nimg_id");
                        KhD.cimg_path = json_data.getString("cimg_path");
                        KhD.cshared_by = json_data.getString("cshared_by");
                        KhD.cplace_name = json_data.getString("cplace_name");
                        KhD.cimg_caption = json_data.getString("cimg_caption");
                        KhD.caddress = json_data.getString("caddress");
                        KhanapurData.add(KhD);
                    }

                    // Setup and Handover data to recyclerview
                    KhanapurView = (RecyclerView) findViewById(R.id.khanapur_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, KhanapurData);
                    KhanapurView.setAdapter(mAdapter);
                    KhanapurView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayNehruNagar = jsonObject.getJSONArray("Nehru Nagar");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayNehruNagar.length(); i++) {
                        JSONObject json_data = jArrayNehruNagar.getJSONObject(i);
                        GaneshaData NND = new GaneshaData();
                        NND.nimg_id = json_data.getString("nimg_id");
                        NND.cimg_path = json_data.getString("cimg_path");
                        NND.cshared_by = json_data.getString("cshared_by");
                        NND.cplace_name = json_data.getString("cplace_name");
                        NND.cimg_caption = json_data.getString("cimg_caption");
                        NND.caddress = json_data.getString("caddress");
                        NehruNagarData.add(NND);
                    }

                    // Setup and Handover data to recyclerview
                    NehruNagarView = (RecyclerView) findViewById(R.id.nehrunagar_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, NehruNagarData);
                    NehruNagarView.setAdapter(mAdapter);
                    NehruNagarView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayShahapur = jsonObject.getJSONArray("Shahapur");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayShahapur.length(); i++) {
                        JSONObject json_data = jArrayShahapur.getJSONObject(i);
                        GaneshaData SD = new GaneshaData();
                        SD.nimg_id = json_data.getString("nimg_id");
                        SD.cimg_path = json_data.getString("cimg_path");
                        SD.cshared_by = json_data.getString("cshared_by");
                        SD.cplace_name = json_data.getString("cplace_name");
                        SD.cimg_caption = json_data.getString("cimg_caption");
                        SD.caddress = json_data.getString("caddress");
                        ShahapurData.add(SD);
                    }

                    // Setup and Handover data to recyclerview
                    ShahapurView = (RecyclerView) findViewById(R.id.shahpur_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, ShahapurData);
                    ShahapurView.setAdapter(mAdapter);
                    ShahapurView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayTilakwadi = jsonObject.getJSONArray("Tilakwadi");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayTilakwadi.length(); i++) {
                        JSONObject json_data = jArrayTilakwadi.getJSONObject(i);
                        GaneshaData TD = new GaneshaData();
                        TD.nimg_id = json_data.getString("nimg_id");
                        TD.cimg_path = json_data.getString("cimg_path");
                        TD.cshared_by = json_data.getString("cshared_by");
                        TD.cplace_name = json_data.getString("cplace_name");
                        TD.cimg_caption = json_data.getString("cimg_caption");
                        TD.caddress = json_data.getString("caddress");
                        TilakwadiData.add(TD);
                    }

                    // Setup and Handover data to recyclerview
                    TilakwadiView = (RecyclerView) findViewById(R.id.tilakwadi_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, TilakwadiData);
                    TilakwadiView.setAdapter(mAdapter);
                    TilakwadiView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    JSONArray jArrayVadagaon = jsonObject.getJSONArray("Vadagaon");
                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArrayVadagaon.length(); i++) {
                        JSONObject json_data = jArrayVadagaon.getJSONObject(i);
                        GaneshaData VD = new GaneshaData();
                        VD.nimg_id = json_data.getString("nimg_id");
                        VD.cimg_path = json_data.getString("cimg_path");
                        VD.cshared_by = json_data.getString("cshared_by");
                        VD.cplace_name = json_data.getString("cplace_name");
                        VD.cimg_caption = json_data.getString("cimg_caption");
                        VD.caddress = json_data.getString("caddress");
                        VadagaonData.add(VD);
                    }

                    // Setup and Handover data to recyclerview
                    VadagaonView = (RecyclerView) findViewById(R.id.vadagaon_rcview);
                    mAdapter = new SarvajanikGaneshaAdapter(SarvajanikActivity.this, VadagaonData);
                    VadagaonView.setAdapter(mAdapter);
                    VadagaonView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                } catch (JSONException e) {
                    //Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(SarvajanikActivity.this, "Unable to fetch the data.Please try after sometime.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}