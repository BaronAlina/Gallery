package com.example.image2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ImagesGallery  {

//    public static int format1;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        format1=getIntent().getIntExtra("format", 0);
//        super.onCreate(savedInstanceState);
//    }

    public  static ArrayList<String> listOfImages(Context context, int format1){
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        String orderBy = MediaStore.Video.Media.DATE_TAKEN;
        cursor= context.getContentResolver().query(uri, projection, null, null, orderBy+" DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        if (format1 ==1){
            listOfAllImages.clear();
        }if (format1 == 2){
            listOfAllImages.clear();
        }if (format1==3){
            listOfAllImages.clear();
        }

        while (cursor.moveToNext()) {
//            Intent intent=new Intent();
//            intent.setClass(ImagesGallery.this, MainActivity.class);
            absolutePathOfImage = cursor.getString(column_index_data);
            if (format1==0) {
                listOfAllImages.add(absolutePathOfImage);
            }
            String[] format = absolutePathOfImage.split("\\.");
            if (format1==1){
            if (format[format.length - 1].equals("png")) {
                listOfAllImages.add(absolutePathOfImage);
            }}else if (format1==2){
                if (format[format.length - 1].equals("jpg")) {
                    listOfAllImages.add(absolutePathOfImage);
                }}if (format1==3) {
                        if (format[format.length - 1].equals("jpeg")) {
                            listOfAllImages.add(absolutePathOfImage);
                        }
                    }
                }
        return listOfAllImages;
            }
        }

