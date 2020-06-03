package id.ac.umn.mahasiswaque;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Handler;

public class HomeActivity extends AppCompatActivity {
    EditText inputSearch;
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

        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                HomeActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
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
//                Collections.sort(arrayList);
//                Collections.reverse(arrayList);
//                for (int i = 0; i < arrayList.size(); i++) {
//                    System.out.println(arrayList.get(i));
//                }
//                adapter = new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1, arrayList);
//                listView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
                return true;
            case R.id.sortNama:
//                Collections.sort(arrayList);
//                Collections.reverse(arrayList);
//                for (int i = 0; i < arrayList.size(); i++) {
//                    System.out.println(arrayList.get(i));
//                }
//                adapter = new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1, arrayList);
//                listView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
                return true;
            case R.id.abtMe:
                Intent intent = new Intent(HomeActivity.this, Profile.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
