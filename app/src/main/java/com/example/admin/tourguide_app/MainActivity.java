package com.example.admin.tourguide_app;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Uri imgUrl;
    private DatabaseReference myDatabaseRef;
    private StorageReference sStorageRef;
    private EditText txt_name;
    private ImageView imgView;
    private EditText txt_desc;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";



    public static final int REQUEST_CODE  = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        imgView = (ImageView) findViewById(R.id.imgView);
        txt_desc = (EditText) findViewById(R.id.txt_descrip);
        txt_name = (EditText) findViewById(R.id.txt_name);

        sStorageRef = FirebaseStorage.getInstance().getReference();
        myDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

    }
    public void btn_Click_Retrieve(View v)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
//       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//       intent.setAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!= null && data.getData() != null)
        {
            imgUrl = data.getData();
            try{
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUrl);
                imgView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getImageExt(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
    @SuppressWarnings("VisibleForTests")
    public void onClic_upload(View v)
    {
        if(imgUrl != null)
        {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Uploading Image");
            dialog.show();

            //Get Storage Referance
            StorageReference myStorageRef = sStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "," + getImageExt(imgUrl));
            //Add file to referance
            myStorageRef.putFile(imgUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //Dismiss when dialog success
                    dialog.dismiss();

                    //Display on Success
                    Toast.makeText(getApplicationContext(), "Image Uploaded" ,Toast.LENGTH_SHORT).show();

                    ImageUpload imageupload = new ImageUpload(txt_name.getText().toString(), txt_desc.getText().toString(), taskSnapshot.getDownloadUrl().toString());
                    //Save image info in to firebase database

                    String uploadId = myDatabaseRef.push().getKey();
                    myDatabaseRef.child(uploadId).setValue(imageupload);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Dismiss when dialog failure
                            dialog.dismiss();

                            //Display on error
                            Toast.makeText(getApplicationContext(),e.getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            //Show upload progress

                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Upload " + (int)progress + "%");
                        }
                    });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please Select image" ,Toast.LENGTH_SHORT).show();
        }
    }
//    public void btnShow_list_Click(View v)
//    {
//        Intent intent = new Intent(MainActivity.this,ImageListActivity.class);
//        startActivity(intent);
//    }
}
