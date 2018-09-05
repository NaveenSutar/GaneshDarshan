package com.darshangroups.ganeshdarshan.Categories.UploadImage;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.darshangroups.ganeshdarshan.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class UploadImageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Bitmap FixBitmap;
    String Image = "image";
    String Uname = "sharedby";
    String Umoor = "createdby";
    String Uaddress = "address";
    String Utitle = "title";
    String Udesc = "desc";
    String Uarea = "area";
    String Ucategory = "category";
    ProgressDialog progressDialog;
    ByteArrayOutputStream byteArrayOutputStream;

    byte[] byteArray;

    String area, ConvertImage, GetUname, GetUmoor, GetUaddress, GetUtitle, GetUdesc, GetUarea, GetUcategory, spinnerarea, StrCategory, urltest;

    HttpURLConnection httpURLConnection;
    URL url;
    OutputStream outputStream;
    BufferedWriter bufferedWriter;
    int RC;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    boolean check = true;

    private EditText uname, umoor, uaddress, utitle, udesc;
    private RadioGroup category;
    private AppCompatSpinner spinner;
    private TextView browse, imgupsubmit, choose;
    private ImageView imageView;

    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_form);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Edit Texts
        uname = findViewById(R.id.upname);
        umoor = findViewById(R.id.upmoorthikaar);
        uaddress = findViewById(R.id.upaddress);
        utitle = findViewById(R.id.uptitle);
        udesc = findViewById(R.id.updesc);
        category = findViewById(R.id.upcategory);

        browse = findViewById(R.id.upimg);
        choose = findViewById(R.id.choose);
        imgupsubmit = findViewById(R.id.submit);
        imageView = findViewById(R.id.showimg);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Angol");
        categories.add("Auto Nagar");
        categories.add("Bogarves");
        categories.add("Camp");
        categories.add("Chennamma");
        categories.add("Fort Road");
        categories.add("Hindalga");
        categories.add("Honaga");
        categories.add("Kangrali");
        categories.add("Khanapur");
        categories.add("Nehru Nagar");
        categories.add("Shahapur");
        categories.add("Tilakwadi");
        categories.add("Vadagaon");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        byteArrayOutputStream = new ByteArrayOutputStream();
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });

        imgupsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catid = category.getCheckedRadioButtonId();
                RadioButton cat = findViewById(catid);
                StrCategory = cat.getText().toString();

                GetUname = uname.getText().toString();
                GetUmoor = umoor.getText().toString();
                GetUaddress = uaddress.getText().toString();
                GetUtitle = utitle.getText().toString();
                GetUdesc = udesc.getText().toString();
                GetUarea = area;

                switch (StrCategory) {
                    case "Home":
                        GetUcategory = "2";
                        break;
                    case "Sarvajanik":
                        GetUcategory = "1";
                        break;
                    case "Preparation":
                        GetUcategory = "3";
                        break;
                }

                //Toast.makeText(UploadImageActivity.this, GetUname+GetUmoor+GetUaddress+GetUtitle+GetUdesc+GetUarea+GetUcategory, Toast.LENGTH_SHORT).show();
                UploadImageToServer();

            }
        });

        if (ContextCompat.checkSelfPermission(UploadImageActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }
        }
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Photo Gallery",
                "Camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    FixBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    // String path = saveImage(bitmap);
                    //Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(FixBitmap);
                    imgupsubmit.setVisibility(View.VISIBLE);
                    browse.setVisibility(View.GONE);
                    choose.setVisibility(View.GONE);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(UploadImageActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            FixBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(FixBitmap);
            imgupsubmit.setVisibility(View.VISIBLE);
            //  saveImage(thumbnail);
            //Toast.makeText(ShadiRegistrationPart5.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }


    public void UploadImageToServer() {
        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byteArray = byteArrayOutputStream.toByteArray();
        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        //Toast.makeText(UploadImageActivity.this, GetUname+GetUmoor+GetUaddress+GetUtitle+GetUdesc+GetUarea+GetUcategory, Toast.LENGTH_SHORT).show();

        class AsyncTaskUploadClass extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(UploadImageActivity.this, "Image is Uploading...", "Please Wait", false, false);
            }

            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                progressDialog.dismiss();
                Toast.makeText(UploadImageActivity.this, string1, Toast.LENGTH_LONG).show();
                //Toast.makeText(UploadImageActivity.this, GetUname + GetUmoor + GetUaddress + GetUtitle + GetUdesc + GetUarea + GetUcategory, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();
                HashMap<String, String> HashMapParams = new HashMap<String, String>();

                HashMapParams.put(Uname, GetUname);
                //HashMapParams.put(Umoor, GetUmoor);
                HashMapParams.put(Uaddress, GetUaddress);
                //HashMapParams.put(Utitle, GetUtitle);
                //HashMapParams.put(Udesc, GetUdesc);
                //HashMapParams.put(Uarea, GetUarea);
                //HashMapParams.put(Ucategory, GetUcategory);
                HashMapParams.put(Image, ConvertImage);

                String FinalData = imageProcessClass.ImageHttpRequest("http://www.brainways.in/ganeshdarshan/upload/upload_mob.php", HashMapParams);
                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerarea = parent.getItemAtPosition(position).toString();

        switch (spinnerarea) {
            case "Angol":
                area = "2";
                break;
            case "Auto Nagar":
                area = "3";
                break;
            case "Bogarves":
                area = "4";
                break;
            case "Camp":
                area = "5";
                break;
            case "Chennamma":
                area = "6";
                break;
            case "Fort Road":
                area = "7";
                break;
            case "Hindalga":
                area = "8";
                break;
            case "Honaga":
                area = "9";
                break;
            case "Kangrali":
                area = "10";
                break;
            case "Khanapur":
                area = "11";
                break;
            case "Nehru Nagar":
                area = "12";
                break;
            case "Shahapur":
                area = "13";
                break;
            case "Tilakwadi":
                area = "14";
                break;
            case "Vadagaon":
                area = "15";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
            } else {
                Toast.makeText(UploadImageActivity.this, "Unable to use Camera..Please Allow us to use Camera", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class ImageProcessClass {

        public String ImageHttpRequest(String requestURL, HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(requestURL);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                outputStream = httpURLConnection.getOutputStream();

                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(bufferedWriterDataFN(PData));
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                RC = httpURLConnection.getResponseCode();
                if (RC == HttpsURLConnection.HTTP_OK) {
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReader.readLine()) != null) {
                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            urltest = stringBuilder.toString();
            return urltest;
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            stringBuilder = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilder.append("&amp;");

                stringBuilder.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }
            return stringBuilder.toString();
        }
    }
}