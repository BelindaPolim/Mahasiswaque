package id.ac.umn.mahasiswaque;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class Insert extends AppCompatActivity {
    ImageView profPic;
    Button btnChange;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        profPic = (ImageView) findViewById(R.id.profPic);
        btnChange = (Button)findViewById(R.id.btnChange) ;
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle(R.string.app_name);
//                builder.setMessage("Choose where to load");
//                builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//
//                    }
//
//                });
//                builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                        openGallery();
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            profPic.setImageURI(imageUri);
        }
    }

    public void insData(View view) {
        final ImageView profpic = (ImageView)findViewById(R.id.profPic);

        final EditText nimField = (EditText) findViewById(R.id.etNIM);
        String NIM = nimField.getText().toString();

        final EditText nameField = (EditText) findViewById(R.id.etNama);
        String name = nameField.getText().toString();

        final Spinner majorSp = (Spinner) findViewById(R.id.spProdi);
        String major = majorSp.getSelectedItem().toString();

        final Spinner yearSp = (Spinner) findViewById(R.id.spTahun);
        String year = yearSp.getSelectedItem().toString();

        final EditText bioField = (EditText) findViewById(R.id.etBio);
        String bio = bioField.getText().toString();

        if(TextUtils.isEmpty(NIM)){
            nimField.setError("NIM is required");
        }
        if(TextUtils.isEmpty(name)){
            nameField.setError("Name is required");
        }
        if(TextUtils.isEmpty(bio)){
            bioField.setError("Please write something");
        }
    }
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
