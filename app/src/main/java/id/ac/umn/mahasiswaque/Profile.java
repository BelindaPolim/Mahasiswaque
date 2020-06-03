package id.ac.umn.mahasiswaque;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    private Switch swOnOff;
    View bgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        swOnOff = (Switch)findViewById(R.id.swOnOff);
        bgView = findViewById(R.id.bgView);

        swOnOff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(swOnOff.isChecked()){
                    swOnOff.setChecked(true);
                    bgView.setAlpha(1f);
                    Toast.makeText(getApplicationContext(),"Dark mode",Toast.LENGTH_SHORT).show();
                }
                else {
                    swOnOff.setChecked(false);
                    bgView.setAlpha(0f);
                    Toast.makeText(getApplicationContext(),"Light mode",Toast.LENGTH_SHORT).show();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
