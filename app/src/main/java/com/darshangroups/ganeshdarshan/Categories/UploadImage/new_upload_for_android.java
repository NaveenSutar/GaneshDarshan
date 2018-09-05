package com.darshangroups.ganeshdarshan.Categories.UploadImage;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.darshangroups.ganeshdarshan.R;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class new_upload_for_android extends AppCompatActivity {

    private static final String TAG = "new_upload_for_android";
    final int PICK_IMAGE_REQUEST = 234;
    EditText ETname, ETaddress;
    TextInputLayout uplname, upladdress;
    private Uri filePath;
    private ImageView teacherImageView;
    private TextView showChooserBtn, sendToMySQLBtn;
    private ProgressBar uploadProgressBar;
    private int GALLERY = 1, CAMERA = 2;

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image To Upload"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                teacherImageView.setVisibility(View.VISIBLE);
                teacherImageView.setImageBitmap(bitmap);
                sendToMySQLBtn.setVisibility(View.VISIBLE);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImagePath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(columnIndex);
        cursor.close();

        return s;
    }

    private boolean validateData() {

        String name = ETname.getText().toString().trim();
        String address = ETaddress.getText().toString().trim();

        if (name.isEmpty() || address.isEmpty()) {
            if (name.isEmpty()) {
                uplname.setError("Fill your name");
                return false;
            } else if (address.isEmpty()) {
                upladdress.setError("Fill your address");
                return false;
            }

        } else {
            uplname.setErrorEnabled(false);
            upladdress.setErrorEnabled(false);
        }
        return true;

        /*String address=descriptionEditText.getText().toString();
        if( name == null || address == null)
        {
            return false;
        }

        if(name == "" || address == ""){  return false;  }

        if(filePath == null){return false;}

        return true;*/
    }

    /*
    System.out.println("Image URL: " + mImageUrl.toString());

            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(mImageUrl, projection,null, null, null);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String absoluteFilePath = cursor.getString(index);
            FileInputStream fis = new FileInputStream(absoluteFilePath);
    * */

    /*
    OnCreate method. When activity is created
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_form);

        isStoragePermissionGranted();

        ETname = findViewById(R.id.upname);
        ETaddress = findViewById(R.id.upaddress);
        uplname = findViewById(R.id.uplname);
        upladdress = findViewById(R.id.upladdress);
        showChooserBtn = findViewById(R.id.upimg);
        sendToMySQLBtn = findViewById(R.id.submit);
        //Button openActivityBtn=findViewById(R.id.openActivityBtn);
        teacherImageView = findViewById(R.id.showimg);
        uploadProgressBar = findViewById(R.id.myProgressBar);

        showChooserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
                //showPictureDialog();
            }
        });

        sendToMySQLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    //GET VALUES
                    String name = ETname.getText().toString();
                    String address = ETaddress.getText().toString();

                    SpiritualTeacher s = new SpiritualTeacher(name, address);

                    //upload data to mysql
                    new MyUploader(new_upload_for_android.this).upload(s, ETname, ETaddress, teacherImageView);
                } else {
                    //Toast.makeText(new_upload_for_android.this, "PLEASE ENTER ALL FIELDS CORRECTLY ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Log.v(TAG,"Permission is granted");
            return true;
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

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(intent, CAMERA);
        startActivityForResult(Intent.createChooser(intent, "Select Image To Upload"), CAMERA);
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(galleryIntent, GALLERY);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Image To Upload"), GALLERY);

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(intent, CAMERA);
    }

    /*
    Our data object
     */
    class SpiritualTeacher {
        private String name, description, imageURL;

        public SpiritualTeacher(String name, String description) {
            this.name = name;
            this.description = description;
            this.imageURL = imageURL;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    /*
    Uploader
     */
    public class MyUploader {
        //YOU CAN USE EITHER YOUR IP ADDRESS OR  10.0.2.2 I depends on the Emulator. Make sure you append
        //the `index.php` when making a POST request
        //Most emulators support this
        //private static final String DATA_UPLOAD_URL="http://192.168.1.13/ganappa/upload/upload_africa.php";
        private static final String DATA_UPLOAD_URL = "http://brainways.in/ganeshdarshan/upload/home_upload.php";
        //if you use genymotion you can use this
        //private static final String DATA_UPLOAD_URL="http://10.0.3.2/php/spiritualteachers/index.php";
        //You can get your ip adrress by typing ipconfig/all in cmd
        //private static final String DATA_UPLOAD_URL="http://192.168.12.2/php/spiritualteachers/index.php";

        //INSTANCE FIELDS
        private final Context c;

        public MyUploader(Context c) {
            this.c = c;
        }

        /*
        SAVE/INSERT
         */
        public void upload(SpiritualTeacher s, final View... inputViews) {
            if (s == null) {
                Toast.makeText(c, "No Data To Save", Toast.LENGTH_SHORT).show();
            } else {
                File imageFile;
                try {
                    imageFile = new File(getImagePath(filePath));

                } catch (Exception e) {
                    Toast.makeText(c, "Please pick an Image From Right Place, maybe Gallery or File Explorer so that we can get its path." + e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                uploadProgressBar.setVisibility(View.VISIBLE);

                AndroidNetworking.upload(DATA_UPLOAD_URL)
                        .addMultipartFile("image", imageFile)
                        .addMultipartParameter("name", s.getName())
                        .addMultipartParameter("address", s.getDescription())
                        .setTag("MYSQL_UPLOAD")
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response != null) {
                                    try {
                                        //SHOW RESPONSE FROM SERVER
                                        String responseString = response.get("message").toString();
                                        Toast.makeText(c, responseString, Toast.LENGTH_LONG).show();

                                        if (responseString.equalsIgnoreCase("Your image has been submitted successfully")) {
                                            //RESET VIEWS
                                            EditText nameEditText = (EditText) inputViews[0];
                                            EditText descriptionEditText = (EditText) inputViews[1];
                                            ImageView teacherImageView = (ImageView) inputViews[2];

                                            nameEditText.setText("");
                                            descriptionEditText.setText("");
                                            teacherImageView.setVisibility(View.GONE);
                                            sendToMySQLBtn.setVisibility(View.GONE);

                                        } else {
                                            Toast.makeText(c, "PHP WASN'T SUCCESSFUL.", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(c, "JSONException " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(c, "NULL RESPONSE. ", Toast.LENGTH_LONG).show();
                                }
                                uploadProgressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(ANError error) {
                                error.printStackTrace();
                                uploadProgressBar.setVisibility(View.GONE);
                                Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : \n" + error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }
    }
}