package com.dynamicdudes.rentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Aadhar extends AppCompatActivity {

        private static final int PERMISSION_CODE = 1000;
        private static final int IMAGE_CAPTURE_CODE = 1001;
        private static final int IMAGE_PICK_CODE = 1000;
        private static final int PERMISSION1_CODE = 1001;

        ImageView mImageView1;
        Button mChooseBtn;
        Button mCaptureBtn;
        Button nextBtn;
        ImageView mImageview;
        Uri image_uri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_aadhar);

            mImageview = findViewById(R.id.image_view);
            mCaptureBtn = findViewById(R.id.capture_img_btn);

            mImageView1 = findViewById(R.id.image_view);
            mChooseBtn = findViewById(R.id.choose_image);

            nextBtn = findViewById(R.id.next_btn);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Aadhar.this,Profile.class);
                    startActivity(intent);
                }
            });


            mCaptureBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if (checkSelfPermission(Manifest.permission.CAMERA)==
                                PackageManager.PERMISSION_DENIED ||
                                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                        PackageManager.PERMISSION_DENIED){
                            String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permission,PERMISSION_CODE);
                        }
                        else{
                            openCamera();
                        }
                    }
                    else {
                        openCamera();
                    }
                }
            });

            mChooseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_DENIED){
                            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                            requestPermissions(permissions,PERMISSION1_CODE);
                        }
                        else{
                            pickImageFromGallery1();
                        }
                    }
                    else {
                        pickImageFromGallery1();
                    }
                }
            });
        }


        private void pickImageFromGallery1() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,IMAGE_PICK_CODE);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode){
                case PERMISSION1_CODE:{
                    if(grantResults.length>0 && grantResults[0]==
                            PackageManager.PERMISSION_GRANTED){
                        pickImageFromGallery1();
                    }
                    else {
                        Toast.makeText(this,"Permisson Denied...",Toast.LENGTH_SHORT).show();
                    }
                }
                case PERMISSION_CODE:{
                    if (grantResults.length>0 && grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED){
                        openCamera();
                    }
                    else {
                        Toast.makeText(this,"Denied...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        private void openCamera() {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE,"New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION,"From the Camera");
            image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            Intent cameraIndent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIndent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
            startActivityForResult(cameraIndent,IMAGE_CAPTURE_CODE);

        }



        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                mImageview.setImageURI(image_uri);
            }
            if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
                mImageView1.setImageURI(data.getData());

            }

            String fileName = "default_file_name";
            Cursor returnCursor =
                    getContentResolver().query(image_uri, null, null, null, null);
            try {
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                returnCursor.moveToFirst();
                fileName = returnCursor.getString(nameIndex);
                Log.d("Aadhar", "file name : " + fileName);
            }catch (Exception e){
                Log.e("Aadhar", "error: ", e);
                //handle the failure cases here
            } finally {
                returnCursor.close();
            }

        }







}