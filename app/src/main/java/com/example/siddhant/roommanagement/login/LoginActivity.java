package com.example.siddhant.roommanagement.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.siddhant.roommanagement.menu.MenuActivity;
import com.example.siddhant.roommanagement.R;
import com.example.siddhant.roommanagement.register.RegisterActivity;
import com.example.siddhant.roommanagement.RoomMngmt;
import com.example.siddhant.roommanagement.databinding.ActivityLoginactivityBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginactivityBinding loginactivityBinding;
    JsonObject object;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginactivityBinding= DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_loginactivity);


        loginactivityBinding.btnLlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"U clicked Login",Toast.LENGTH_SHORT).show();

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://androindian.com/apps/reminder/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RoomMngmt roomMngmt=retrofit.create(RoomMngmt.class);

                progressDialog=new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Loading....");


                if (loginactivityBinding.userLmob.getText().toString().equals("") && loginactivityBinding.userLpass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please insert fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.show();
                    JSONObject jsonObject = new JSONObject();
                    try {

                        jsonObject.put("mobile", loginactivityBinding.userLmob.getText().toString());
                        jsonObject.put("pswrd", loginactivityBinding.userLpass.getText().toString());
                        jsonObject.put("action", "login_user");

                        object = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Call<LoginResponsePojo> loginResponsePojoCall = roomMngmt.loginresponsepojo(object);
                    loginResponsePojoCall.enqueue(new Callback<LoginResponsePojo>() {
                        @Override
                        public void onResponse(Call<LoginResponsePojo> call, Response<LoginResponsePojo> response) {


                            if (response.body().getResponse().equalsIgnoreCase("success")) {
                                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                                finish();
                            } else if (response.body().getResponse().equalsIgnoreCase("failed")) {
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<LoginResponsePojo> call, Throwable t) {

                            progressDialog.dismiss();

                        }
                    });

                }
            }
        });


        loginactivityBinding.btnLregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

           }
}
