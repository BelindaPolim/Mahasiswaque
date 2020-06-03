package id.ac.umn.mahasiswaque;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class Insert extends AppCompatActivity {
    ImageView profPic;
//    EditText txtNIM, txtName, txtBio;
//    Spinner spMajor, spYear;
    Button btnChange, btnInsert;
    DatabaseReference reff;
    Mahasiswa mhs;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int Image_Request_Code = 7;
    StorageReference str;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        str = FirebaseStorage.getInstance().getReference("Mahasiswa");
        reff = FirebaseDatabase.getInstance().getReference("Mahasiswa");
        profPic = (ImageView) findViewById(R.id.profPic);
        progressDialog = new ProgressDialog(Insert.this);

        btnChange = (Button)findViewById(R.id.btnChange) ;
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Insert.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Select image from");
                builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Intent takePictureIntent = new
                                Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager())
                                != null) {
                            startActivityForResult(takePictureIntent,
                                    REQUEST_IMAGE_CAPTURE);
                        }
                    }
                });
                builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        openGallery();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnInsert = (Button)findViewById(R.id.btnInsert);
        mhs = new Mahasiswa();
        reff = FirebaseDatabase.getInstance().getReference().child("Mahasiswa");
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageView profpic = (ImageView)findViewById(R.id.profPic);

                final EditText nimField = (EditText) findViewById(R.id.etNIM);
                final String NIM = nimField.getText().toString();

                final EditText nameField = (EditText) findViewById(R.id.etNama);
                final String name = nameField.getText().toString();

                final Spinner majorSp = (Spinner) findViewById(R.id.spProdi);
                final String major = majorSp.getSelectedItem().toString();

                final Spinner yearSp = (Spinner) findViewById(R.id.spTahun);
                final String year = yearSp.getSelectedItem().toString();

                final EditText bioField = (EditText) findViewById(R.id.etBio);
                final String bio = bioField.getText().toString();

                if(TextUtils.isEmpty(NIM)){
                    nimField.setError("NIM is required");
                }
                if(TextUtils.isEmpty(name)){
                    nameField.setError("Name is required");
                }
                if(TextUtils.isEmpty(bio)){
                    bioField.setError("Please write something");
                }

                mhs.setNIM(NIM);
                mhs.setName(name);
                mhs.setMajor(major);
                mhs.setYear(year);
                mhs.setBio(bio);

                reff.push().setValue(mhs);
                Toast.makeText(Insert.this, "Data inserted successfully",Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profPic.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
//            imageUri = data.getData();
//            profPic.setImageURI(imageUri);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
