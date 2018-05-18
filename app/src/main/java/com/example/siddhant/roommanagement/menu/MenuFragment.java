package com.example.siddhant.roommanagement.menu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.siddhant.roommanagement.R;
import com.example.siddhant.roommanagement.create_group.CreateGroupFragment;
import com.example.siddhant.roommanagement.databinding.MenuFragmentBinding;
import com.example.siddhant.roommanagement.insert_saving.InsertSavingFragment;
import com.example.siddhant.roommanagement.menu.MenuAdapter;

public class MenuFragment extends Fragment {


    String[] menuTxt = {"Insert Saving", "Create Group", "Add Group", "Add Personal Transation"};
    int[] menuImage = {R.drawable.ic_people, R.drawable.ic_people, R.drawable.ic_people, R.drawable.ic_people};

    MenuFragmentBinding menuFragmentBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, null, false);
        menuFragmentBinding= DataBindingUtil.bind(view);
        menuFragmentBinding.grid.setAdapter(new MenuAdapter(menuTxt,menuImage,getActivity()));



        menuFragmentBinding.grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (i == 0) {
                    fragmentTransaction.replace(R.id.frame, new InsertSavingFragment());
                    fragmentTransaction.addToBackStack("true");
                    fragmentTransaction.commit();
                } else if (i == 1) {
                    fragmentTransaction.replace(R.id.frame, new CreateGroupFragment());
                    fragmentTransaction.addToBackStack("true");
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }
}
