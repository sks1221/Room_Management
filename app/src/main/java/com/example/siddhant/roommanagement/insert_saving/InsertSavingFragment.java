package com.example.siddhant.roommanagement.insert_saving;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.siddhant.roommanagement.R;
import com.example.siddhant.roommanagement.RoomMngmt;
import com.example.siddhant.roommanagement.databinding.FragmentInsertSavingBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertSavingFragment extends Fragment {

    FragmentInsertSavingBinding insertSavingBinding;
    JsonObject object;
    RoomMngmt roomMngmt;
    String saving;
    ArrayList<String> arrayList;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_insert_saving, container, false);
        insertSavingBinding = DataBindingUtil.bind(v);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://androindian.com/apps/fm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        roomMngmt = retrofit.create(RoomMngmt.class);

        insertSavingBinding.btnInsertSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please wait...");

                if (saving.equals("s") || saving.equals("e")) {
                    progressDialog.show();
                    try {
                        insertSaving();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "Select Saving Type", Toast.LENGTH_SHORT).show();
                }


            }
        });

        try {
            sendData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }

    public void sendData() throws JSONException {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading....");
        progressDialog.show();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "get_saving_types");

        object = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

        Call<SpinnerPojo> spinnerPojoCall = roomMngmt.addSavingpojo(object);
        spinnerPojoCall.enqueue(new Callback<SpinnerPojo>() {
            @Override
            public void onResponse(Call<SpinnerPojo> call, Response<SpinnerPojo> response) {
                progressDialog.dismiss();
                if (response.body().getResponse().equalsIgnoreCase("success")) {
                    String s1 = response.body().getData().get(0).getName();
                    String s2 = response.body().getData().get(1).getName();

                    arrayList = new ArrayList<>();
                    arrayList.add("Insert Saving Type");
                    arrayList.add(s1);
                    arrayList.add(s2);

                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.activity_list_item, arrayList);
                    insertSavingBinding.insetSpiner.setAdapter(arrayAdapter);

                    insertSavingBinding.insetSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                saving = "";
                            } else if (position == 1) {
                                saving = "s";
                            } else if (position == 1) {
                                saving = "e";
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<SpinnerPojo> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }


    public void insertSaving() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reason", insertSavingBinding.insertSavingReason.getText().toString().trim());
        jsonObject.put("amount", insertSavingBinding.insertSavingAmount.getText().toString().trim());
        jsonObject.put("start_date", insertSavingBinding.insertSavingEdate.getText().toString().trim());
        jsonObject.put("end_date", insertSavingBinding.insertSavingSdate.getText().toString().trim());
        jsonObject.put("stype", saving);
        jsonObject.put("mobile", "9533928284");
        jsonObject.put("action", "put_saving");

        object = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

        Call<InsertSavingPojo> insertSavingpojoCall = roomMngmt.insertSaving(object);
        insertSavingpojoCall.enqueue(new Callback<InsertSavingPojo>() {
            @Override
            public void onResponse(Call<InsertSavingPojo> call, Response<InsertSavingPojo> response) {
                progressDialog.dismiss();
                if (response.body().getResponse().equalsIgnoreCase("success")) {
                    Toast.makeText(getActivity(), "Insert Successfully", Toast.LENGTH_SHORT).show();

                    insertSavingBinding.insertSavingReason.setText("");
                    insertSavingBinding.insertSavingAmount.setText("");
                    insertSavingBinding.insertSavingSdate.setText("");
                    insertSavingBinding.insertSavingEdate.setText("");
                }
            }

            @Override
            public void onFailure(Call<InsertSavingPojo> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}