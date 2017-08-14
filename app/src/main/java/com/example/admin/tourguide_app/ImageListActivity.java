package com.example.admin.tourguide_app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class ImageListActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> imglist;
    private ListView lv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imglist = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listViews);
        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait while load list image...");
        progressDialog.show();


        mDatabaseRef = FirebaseDatabase.getInstance().getReference(MainActivity.FB_DATABASE_PATH);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                AlertDialog.Builder adb = new AlertDialog.Builder(
//                        ImageListActivity.this);
//                adb.setTitle("List");
//                adb.setMessage(" selected Item is="
//                        +parent.getItemAtPosition(position));
//                adb.setPositiveButton("Ok", null);
//                adb.show();
//            }
//        });

        mDatabaseRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for(com.google.firebase.database.DataSnapshot  snapshot : dataSnapshot.getChildren())
                {
                    ImageUpload img = snapshot.getValue(ImageUpload.class);
                    imglist.add(img);
                }
                adapter = new ImageListAdapter(ImageListActivity.this, R.layout.activity_image_list, imglist);

                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}
