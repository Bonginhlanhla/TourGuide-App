package com.example.admin.tourguide_app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RestntImageActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private List<ResturantUpload> imglist;
    private ListView lv;
    private ResturantAdpter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restnt_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imglist = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait while load list image...");
        progressDialog.show();


        mDatabaseRef = FirebaseDatabase.getInstance().getReference(HotelsActivity.FB_DATABASE_PATH_RE);

        mDatabaseRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for(com.google.firebase.database.DataSnapshot  snapshot : dataSnapshot.getChildren())
                {
                    ResturantUpload img = snapshot.getValue(ResturantUpload.class);
                    imglist.add(img);
                }
                adapter = new ResturantAdpter(RestntImageActivity.this, R.layout.activity_restnt_image, imglist);

                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}
