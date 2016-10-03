package com.example.kevinyao.barcodescan;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        SQLiteDatabase db = Connector.getDatabase();


        System.out.println("==================================+++++++++++++++++++++++++++++++++++++++++++++++++++++");

        List<product> productList = DataSupport.findAll(product.class);
        for(product p : productList) {
            System.out.println("=================================="+p.getBarcode());
            System.out.println("=================================="+p.getAmount());
            System.out.println("=================================="+p.getId());
        }

        inventoryAdapter adapter = new inventoryAdapter(this,
                R.layout.product_ui, productList);
        //Set list style to custom adapter
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}
