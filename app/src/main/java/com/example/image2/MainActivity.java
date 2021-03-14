package com.example.image2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GalleryAdapter galleryAdapter;
    List<String> images;
    TextView gallery_number;
    Button btn;
    final int PNG=1;
    final int JPG=2;
    final int JPEG=3;

    private static final int MY_READ_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.button);
        btn.setOnCreateContextMenuListener(this);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        gallery_number = findViewById(R.id.gallery_number);
        recyclerView=findViewById(R.id.recyclerview_gallery_images);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_PERMISSION_CODE);
        }else {
            loadImages();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.button:
                menu.add(0, PNG, 0, "png");
                menu.add(1, JPG, 1, "jpg");
                menu.add(2, JPEG, 2, "jpeg");
        }
    }

    int format=0;
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        ImagesGallery imagesGallery = new ImagesGallery();
//        Intent intent = new Intent();
        switch (item.getItemId()){
            case PNG:
                format = PNG;
//                imagesGallery.listOfImages(this, PNG);
//                intent.putExtra("format", PNG);
//                intent.setClass(MainActivity.this, ImagesGallery.class);
//                startActivity(intent);
                break;
            case JPG:
                format=JPG;
//                imagesGallery.listOfImages(this, JPG);
//                intent.putExtra("format", JPG);
//                intent.setClass(MainActivity.this, ImagesGallery.class);
//                startActivity(intent);
                break;
            case JPEG:
                format=JPEG;
//                imagesGallery.listOfImages(this, JPEG);
//                intent.putExtra("format", JPEG);
//                intent.setClass(MainActivity.this, ImagesGallery.class);
//                startActivity(intent);
                break;

        }
        loadImages();
        return super.onContextItemSelected(item);
    }

    public void loadImages(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        images = ImagesGallery.listOfImages(this, format);
        galleryAdapter = new GalleryAdapter(this, images, new GalleryAdapter.PhotoListener() {
            @Override
            public void onPhotoClick(String path) {
                //Toast.makeText(MainActivity.this, ""+path, Toast.LENGTH_SHORT).show();
                String[] way= path.split("\\.");
                Toast.makeText(MainActivity.this, ""+way[way.length-1], Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(galleryAdapter);

        gallery_number.setText("Photos ("+images.size()+")");
    }

//    public void format1(int a){
//        loadImages();
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==MY_READ_PERMISSION_CODE){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Read external storage permission granted", Toast.LENGTH_SHORT).show();
                loadImages();
            }else{
                Toast.makeText(this, "Read external storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}