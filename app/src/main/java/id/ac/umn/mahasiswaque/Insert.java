package id.ac.umn.mahasiswaque;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Insert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void insData(View view) {
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
