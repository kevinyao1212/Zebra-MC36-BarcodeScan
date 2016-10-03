package com.example.kevinyao.barcodescan;

import org.litepal.crud.DataSupport;

/**
 * Created by kevinyao on 9/5/16.
 */
public class product extends DataSupport {

    private String barcode;
    private int id;
    private int amount;

    public void setBarcode( String barcode ) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setAmount( int amount ) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setId(int id) {
        this.id = id;
        //this.assignBaseObjId(id);
    }

    public int getId() {
        return this.id;
    }
}
