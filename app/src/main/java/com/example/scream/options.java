package com.example.scream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



import java.util.ArrayList;

public class options extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private EditText itemET;
    private ListView itemsList;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.rev_btn);
        itemsList = findViewById(R.id.items_list);

        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        itemsList.setAdapter(adapter);

        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.rev_btn:

                String itemEntered = itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText(" ");

                FileHelper.writeData(items,this);

                Toast.makeText(this,"Review Added",Toast.LENGTH_SHORT).show();

                break;


        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Review Deleted",Toast.LENGTH_SHORT).show();
    }

}


