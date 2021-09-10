package com.example.demofir.ui.home;

import static com.example.demofir.MyDBHelper.mTableName;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demofir.MyApplication;
import com.example.demofir.MyDBHelper;
import com.example.demofir.databinding.FragmentHomeBinding;
import com.example.demofir.db.Data_Activity;
import com.example.demofir.ui.home.HomeViewModel;

public class HomeFragment extends Fragment {
    private HomeViewModel HomeViewModel; //用来储存UI的数据相关的类
    private FragmentHomeBinding binding;
    private MyDBHelper MyDBHelper;
    private SQLiteDatabase db;     //数据库类
    private ContentValues values;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        final Button st_button = binding.stButton;
        MyDBHelper=new MyDBHelper(getActivity());
        binding.stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData("123","wd","dfa","sda","sda");
            }
        });


      HomeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
         public void onChanged(@Nullable String uname ) {
                textView.setText(uname);
           }
      });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }//页面摧毁；
    void showMsg(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
    void addData( String list_id, String uname, String product_date, String keep_date, String end_line_date){
        db= MyDBHelper.getWritableDatabase();
        values= new ContentValues();
        values.put("list_id",list_id);
        values.put("uname",uname);
        values.put("product_date",product_date);
        values.put("keep_date",keep_date);
        values.put("end_line_date",end_line_date);
        db.replace(mTableName,null,values);
        showMsg("食品日期入库成功！");
    }
 }
