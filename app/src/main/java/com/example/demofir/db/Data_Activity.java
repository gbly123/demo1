package com.example.demofir.db;

import static com.example.demofir.MyDBHelper.mTableName;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import com.example.demofir.MyDBHelper;
import com.example.demofir.ui.home.HomeViewModel;

public class Data_Activity {
    private HomeViewModel MyViewModel;
    private EditText et_food_name,et_product_data;
    private EditText et_keep_data,et_limit_data;
    private Button st_button;
    private MyDBHelper MyDBHelper;



    /***与数据库操作相关的成员变量*/
    private SQLiteDatabase db;     //数据库类
    private ContentValues values;  //数据表操作参数
    //数据表的名字
    public Data_Activity (String list_id, String uname, String product_date, String keep_date, String end_line_date){
        addData(list_id, uname, product_date, keep_date, end_line_date);
    }
    private void addData(@NonNull String list_id, String uname, String product_date, String keep_date, String end_line_date){
        db= MyDBHelper.getWritableDatabase();
        values= new ContentValues();
        values.put("list_id",list_id);
        values.put("uname",uname);
        values.put("product_date",product_date);
        values.put("keep_date",keep_date);
        values.put("end_line_date",end_line_date);
        db.replace(mTableName,null,values);
    }
//    private void edtData(String uname, String identifier){
//        db=myDbHelper.getWritableDatabase();
//        values= new ContentValues();
//        values.put("uname",uname);
//        values.put("identifier",identifier);
//        db.update(mTableName,values,"uname=?",new String[]{uname});
//        showMsg("更新成功");
//    }
//    private void delData(String uname){
//        db=myDbHelper.getWritableDatabase();
//        db.delete(mTableName,"uname=?",new String[]{uname});
//        Toast.makeText(this, "删除此条数据成功", Toast.LENGTH_SHORT).show();
//    }
//    private void delallData(){
//        db=myDbHelper.getWritableDatabase();
//        db.delete(mTableName,null,null);
//        Toast.makeText(this, "删除所有数据成功", Toast.LENGTH_SHORT).show();
//    }
//
}
