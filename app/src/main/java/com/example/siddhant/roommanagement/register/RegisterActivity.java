package com.example.siddhant.roommanagement.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.siddhant.roommanagement.R;
import com.example.siddhant.roommanagement.RoomMngmt;
import com.example.siddhant.roommanagement.databinding.ActivityRegisterBinding;
import com.example.siddhant.roommanagement.login.LoginActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding registerBinding;
    JsonObject object=null;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding= DataBindingUtil.setContentView(RegisterActivity.this, R.layout.activity_register);

        registerBinding.btnRregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog=new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Please Wait...");

                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/reminder/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RoomMngmt roomMngmt=retrofit.create(RoomMngmt.class);

                if (registerBinding.userRname.getText().toString().equals("") && registerBinding.userRmob.getText().toString().equals("")
                        && registerBinding.userRemail.getText().toString().equals("") && registerBinding.userRpass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Insert Fields",Toast.LENGTH_SHORT).show();
                }
                else {

                    progressDialog.show();

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", registerBinding.userRname.getText().toString().trim());
                        jsonObject.put("mobile", registerBinding.userRmob.getText().toString().trim());
                        jsonObject.put("email", registerBinding.userRemail.getText().toString().trim());
                        jsonObject.put("pswrd", registerBinding.userRpass.getText().toString().trim());
                        jsonObject.put("action", "register_user");

                        object = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Call<RegisterResponsePojo> registerResponsePojoCall = roomMngmt.registerresponsepojo(object);
                    registerResponsePojoCall.enqueue(new Callback<RegisterResponsePojo>() {
                        @Override
                        public void onResponse(Call<RegisterResponsePojo> call, Response<RegisterResponsePojo> response) {


                            if (response.body().getResponse().equalsIgnoreCase("success")) {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            } else if (response.body().getResponse().equalsIgnoreCase("failed")) {
                                Toast.makeText(RegisterActivity.this, "Already member", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "User Not Created", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<RegisterResponsePojo> call, Throwable t) {

                            progressDialog.dismiss();

                        }
                    });
                }

            }
        });
    }
}
