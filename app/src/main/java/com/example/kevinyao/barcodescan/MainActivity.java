package com.example.kevinyao.barcodescan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.symbol.scanning.*;


import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Scanner bScanner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePalApplication.initialize(this);


        final Button outStockButton = (Button)findViewById(R.id.outStockButton);
        if( outStockButton != null ) {
            outStockButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click

                    try {
                        bScanner = new Scanner();
                        bScanner.enable();
                        boolean scannerEnable = bScanner.isEnable();

                        Context context = getApplicationContext();
                        CharSequence text = "Scanner status: " + (scannerEnable ? "good" : "bad");
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        final Scanner.DataListener bDataListener = new Scanner.DataListener() {
                            @Override
                            public void onData(ScanDataCollection scanDataCollection) {
                                Context context = getApplicationContext();
                                Toast toast = Toast.makeText(context, "data extracted", Toast.LENGTH_SHORT);
                                toast.show();

                                String arrayData = "";
                                ArrayList<ScanDataCollection.ScanData> dataList = scanDataCollection.getScanData();
                                for (int i = 0; i < dataList.size(); i++) {
                                    arrayData += dataList.get(i).getData();
                                }

                                SharedPreferences sharedPreferences = getSharedPreferences("product_info", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("productNumber", arrayData);
                                editor.commit();

                                boolean isExist = false;
                                int idToUpdate = 0;
                                List<product> productList = DataSupport.findAll(product.class);
                                System.out.println(productList.size()+"================================================");
                                for(product p : productList) {
                                    if( p.getBarcode().equals(arrayData)) {
                                        isExist = true;
                                        idToUpdate = p.getId();
                                    }
                                }

                                if(!isExist) {
                                    System.out.println("not exist!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                    CharSequence text = "该商品不存在!";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast2 = Toast.makeText(context, text, duration);
                                    toast2.show();
                                }
                                else {
                                    System.out.println("exist!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                    product productToUpdate = DataSupport.find(product.class, idToUpdate);
                                    productToUpdate.setAmount(productToUpdate.getAmount()-1);
                                    productToUpdate.save();
                                }

                                try {
                                    bScanner.disable();

                                } catch (ScannerException e) {

                                }

                            }
                        };

                        if (scannerEnable) {
                            bScanner.addDataListener(bDataListener);
                            bScanner.handsfreeRead();
                        }
                    } catch (ScannerException e) {

                    }
                }
            });
        }

        final Button checkInfoButton = (Button)findViewById(R.id.scanButton);
        if( checkInfoButton != null ) {
            checkInfoButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
//                    try {
//                        bScanner.enable();
//                        boolean scannerEnable = bScanner.isEnable();
//
//                        Context context = getApplicationContext();
//                        CharSequence text = "Scanner status: " + (scannerEnable ? "good" : "bad");
//                        int duration = Toast.LENGTH_SHORT;
//
//                        Toast toast = Toast.makeText(context, text, duration);
//                        toast.show();
//
//                        final Scanner.DataListener bDataListener = new Scanner.DataListener() {
//                            @Override
//                            public void onData(ScanDataCollection scanDataCollection) {
//                                Context context = getApplicationContext();
//                                Toast toast = Toast.makeText(context, "data extracted", Toast.LENGTH_SHORT);
//                                toast.show();
//
//                                String arrayData = "";
//                                ArrayList<ScanDataCollection.ScanData> dataList = scanDataCollection.getScanData();
//                                for (int i = 0; i < dataList.size(); i++) {
//                                    arrayData += dataList.get(i).getData();
//                                }
//
//                                SharedPreferences sharedPreferences = getSharedPreferences("product_info", MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("productNumber", arrayData);
//                                editor.commit();
//
                    startActivity(new Intent(MainActivity.this, infoActivity.class));
//
//                                try {
//                                    bScanner.disable();
//
//                                } catch (ScannerException e) {
//
//                                }
//
//                            }
//                        };
//
//                        if (scannerEnable) {
//                            bScanner.addDataListener(bDataListener);
//                            bScanner.handsfreeRead();
//                        }
//                    } catch (ScannerException e) {
//
//                    }
                }
            });
        }

        final Button inStockButton = (Button)findViewById(R.id.inStockButton);
        if( inStockButton != null ) {
            inStockButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click

                    try {
                        bScanner = new Scanner();
                        bScanner.enable();
                        boolean scannerEnable = bScanner.isEnable();

                        Context context = getApplicationContext();
                        CharSequence text = "Scanner status: " + (scannerEnable ? "good" : "bad");
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        final Scanner.DataListener bDataListener = new Scanner.DataListener() {
                            @Override
                            public void onData(ScanDataCollection scanDataCollection) {
                                Context context = getApplicationContext();
                                Toast toast = Toast.makeText(context, "data extracted", Toast.LENGTH_SHORT);
                                toast.show();

                                String arrayData = "";
                                ArrayList<ScanDataCollection.ScanData> dataList = scanDataCollection.getScanData();
                                for (int i = 0; i < dataList.size(); i++) {
                                    arrayData += dataList.get(i).getData();
                                }

                                SharedPreferences sharedPreferences = getSharedPreferences("product_info", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("productNumber", arrayData);
                                editor.commit();

                                boolean isExist = false;
                                int idToUpdate = 0;
                                List<product> productList = DataSupport.findAll(product.class);
                                System.out.println(productList.size()+"================================================");
                                for(product p : productList) {
                                    if( p.getBarcode().equals(arrayData)) {
                                        isExist = true;
                                        idToUpdate = p.getId();
                                    }
                                }

                                if(!isExist) {
                                    System.out.println("not exist!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                    product p = new product();
                                    p.setBarcode(arrayData);
                                    p.setAmount(p.getAmount() + 1);
                                    p.setId(productList.size() + 1);
                                    p.save();
                                }
                                else {
                                    System.out.println("exist!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                    product productToUpdate = DataSupport.find(product.class, idToUpdate);
                                    productToUpdate.setAmount(productToUpdate.getAmount()+1);
                                    productToUpdate.save();
                                }

                                try {
                                    bScanner.disable();

                                } catch (ScannerException e) {

                                }

                            }
                        };

                        if (scannerEnable) {
                            bScanner.addDataListener(bDataListener);
                            bScanner.handsfreeRead();
                        }
                    } catch (ScannerException e) {

                    }
                }
            });
        }
    }

}
