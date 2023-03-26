package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aditiyagilang.edifarm_company.design.comment_adapter;
import com.aditiyagilang.edifarm_company.model.model_comment;

import java.util.ArrayList;

public class post_comment extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<model_comment> listcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        ListView listView = findViewById(R.id.listcomment1);
        listcomment = setUserAndComment();
        comment_adapter comment_adapter = new comment_adapter(post_comment.this, listcomment);
        listView.setAdapter(comment_adapter);
        listView.setOnItemClickListener(this);

    }

    private ArrayList<model_comment> setUserAndComment(){
        listcomment = new ArrayList<>();

        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("adit123", "Allah huakbar"));
        listcomment.add(new model_comment("karen34", "Allah huakbar"));
        listcomment.add(new model_comment("wishal12", "Allah huakbar"));
        listcomment.add(new model_comment("andru55", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        listcomment.add(new model_comment("lusy_d", "Allah huakbar"));
        return listcomment;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}