package com.example.administrator.person;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private RecyclerView contact_list;
    private LetterView letter_view;
    private List<PhoneDto> listbean=new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check();
    }

    private void check() {
        //判断是否有权限
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},201);
        }else{
            initView();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==201){
            initView();
        }else{
            return;
        }
    }
    private void initData() {
        PhoneUtil util=new PhoneUtil(MainActivity.this);
        List<PhoneDto>  phone = util.getPhone();
        listbean.addAll(phone);
        Log.e("TAG","------"+phone.size());
        layoutManager = new LinearLayoutManager(this);
        adapter = new ContactAdapter(this,listbean);
        contact_list.setLayoutManager(layoutManager);
        contact_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        contact_list.setAdapter(adapter);
        letter_view.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {
                layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(character),0);
            }
            //右侧字母列表上边的箭头的点击监听
           /* @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0,0);
            }*/
        });
    }
    private void initView() {
        contact_list = (RecyclerView) findViewById(R.id.contact_list);
        letter_view = (LetterView) findViewById(R.id.letter_view);
        initData();
    }
}
