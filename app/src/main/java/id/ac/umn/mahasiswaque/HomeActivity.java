package id.ac.umn.mahasiswaque;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.prefs.Preferences;

public class HomeActivity extends AppCompatActivity {
    EditText inputSearch;
    ListView listView;
    FirebaseDatabase db;
    DatabaseReference reff;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insert = new Intent(HomeActivity.this, Insert.class);
                startActivity(insert);
            }
        });

        db = FirebaseDatabase.getInstance();
        reff = db.getReference("Users");

        listView = (ListView) findViewById(R.id.lists);

        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                arrayList.add(value);
                adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortNIM:
                Toast.makeText(HomeActivity.this,"Feature Locked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sortNama:
                Toast.makeText(HomeActivity.this,"Not available yet",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.abtMe:
                Intent intent = new Intent(HomeActivity.this, Profile.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new
                        Intent(getBaseContext(),LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
