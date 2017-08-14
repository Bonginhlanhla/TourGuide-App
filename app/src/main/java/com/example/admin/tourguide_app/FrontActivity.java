package com.example.admin.tourguide_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.client.Firebase;

public class FrontActivity extends AppCompatActivity {

    private Button btnAdd, btnRetrive;
    private ImageButton imgResturant, imgHotel, imgCarServ, imgGas_Station,imgHospital,imgShoppMall;
    private Firebase myR;
    private String TAG = "Firebase Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

    }
    public void resturant_onClick(View V) {

        imgResturant = (ImageButton) findViewById(R.id.imgRest);
        imgResturant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intNext = new Intent(FrontActivity.this, ImageListActivity.class);
                startActivity(intNext);
            }
        });
    }
    public void btnShowResturanr_click(View V) {

        imgHotel = (ImageButton) findViewById(R.id.imgHotels);
        imgHotel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intNext = new Intent(FrontActivity.this, RestntImageActivity.class);
                startActivity(intNext);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.admin,menu);
        return true;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getGroupId();
//
//        if(id==R.id.btnAdmin);
//
//            Intent intentAdmin = new Intent(FrontActivity.this,HotelsActivity.class);
//            startActivity(intentAdmin);
//
//
//        if(id==R.id.btnHotels);
//
//
//
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.btnAdmin:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btnHotels:
                Intent intentAdmin = new Intent(FrontActivity.this,HotelsActivity.class);
                startActivity(intentAdmin);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

}

