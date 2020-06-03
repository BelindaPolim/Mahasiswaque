package id.ac.umn.mahasiswaque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    DatabaseHelper db;
    ViewGroup progressView;
    protected boolean isProgressShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.etUser);
        mTextPassword = (EditText)findViewById(R.id.etPass);
        mButtonLogin = (Button)findViewById(R.id.btnLogin);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String user = mTextUsername.getText().toString().trim();
//                String pwd = mTextPassword.getText().toString().trim();
//                Boolean res = db.checkUser(user, pwd);
//                if(res == true)
//                {
//                    Intent HomePage = new Intent(LoginActivity.this,HomeActivity.class);
//                    startActivity(HomePage);
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this,"Login Failed!", Toast.LENGTH_SHORT).show();
//                }
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

//    public void showProgressingView() {
//
//        if (!isProgressShowing) {
//            View view=findViewById(R.id.progressBar1);
//            view.bringToFront();
//        }
//    }
//
//    public void hideProgressingView() {
//        View v = this.findViewById(android.R.id.content).getRootView();
//        ViewGroup viewGroup = (ViewGroup) v;
//        viewGroup.removeView(progressView);
//        isProgressShowing = false;
//    }
}
