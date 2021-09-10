package com.example.demofir.ui.gallery;

import static com.example.demofir.MyDBHelper.mTableName;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.demofir.MyDBHelper;
import com.example.demofir.R;
import com.example.demofir.databinding.FragmentGalleryBinding;
import com.example.demofir.databinding.FragmentListBinding;

public class GalleryFragment extends Fragment {
    private com.example.demofir.MyDBHelper myDb;
    private SQLiteDatabase db;     //数据库类
    private ContentValues values;
    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private FragmentListBinding binding1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myDb = new MyDBHelper(getActivity());
        galleryViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        binding1=FragmentListBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        View root1 = binding1.getRoot();
        final TextView textView = binding1.itemSection;
        final Button button = binding.addButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                querryData();
            }
        });
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void querryData(){
        db=myDb.getWritableDatabase();
        Cursor cursor = db.query(mTableName,null,null,null,null,null,null);
        if(cursor.getCount()==0){
            Toast.makeText(getActivity(), "当前没有数据", Toast.LENGTH_SHORT).show();
        }else{
            binding.tvShow.setText("当前有["+cursor.getCount()+"]条数据：");
            String strResult ="";
            while (cursor.moveToNext()){
                strResult+=cursor.getInt(0);
                strResult+=".编号"+cursor.getString(1);
                strResult+=".食品名称"+cursor.getString(2);
                strResult+=".生产日期"+cursor.getString(3);
                strResult+=".保质期"+cursor.getString(4);
                strResult+=".截止日期"+cursor.getString(5);
                strResult+="\n";
            }
            binding1.itemSection.setText(strResult);
        }
 }
}