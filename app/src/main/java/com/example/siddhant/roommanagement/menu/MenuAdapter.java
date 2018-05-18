package com.example.siddhant.roommanagement.menu;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.siddhant.roommanagement.R;
import com.example.siddhant.roommanagement.databinding.MenuGridBinding;

public class MenuAdapter extends BaseAdapter {

    MenuGridBinding menuGridBinding;
    String[] name;
    int[] img;
    Context c;
    public MenuAdapter(String[] name,int[] img,Context c) {
    this.name=name;
    this.img=img;
    this.c=c;

    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(c);
        View v=inflater.inflate(R.layout.menu_grid,parent,false);
        menuGridBinding= DataBindingUtil.bind(v);

        /*menuGridBinding.gridImage.setImageResource(img[position]);
        menuGridBinding.gridText.setText(name[position]);*/

        menuGridBinding.gridImage.setImageResource(img[position]);
        menuGridBinding.gridText.setText(name[position]);
        return v;
    }
}
