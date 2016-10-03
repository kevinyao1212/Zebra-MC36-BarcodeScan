package com.example.kevinyao.barcodescan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinyao on 9/7/16.
 */
public class inventoryAdapter extends ArrayAdapter<product> {
    List<product> products;
    Context context;

    public inventoryAdapter(Context context, int resource, List<product> objects) {
        super(context, resource, objects);
        products = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        productOptionsHolder holder;
        final int pos = position;

        //Determine layout type, items
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.product_ui, parent, false);


            //Create holder with view data
            holder = new productOptionsHolder();
            holder.mainText = (TextView) view.findViewById(R.id.maintext);
            holder.mainText.setText(getItem(pos).getBarcode() + " 库存: "+getItem(pos).getAmount());
            holder.deleteButton = (Button) view.findViewById(R.id.deleteButton);

            initializeClickListeners(holder, pos, view);
            view.setTag(holder);
        }
        else {
            holder = (productOptionsHolder) view.getTag();
        }

        return view;
    }


    public void initializeClickListeners(final productOptionsHolder holder, final int pos, final View currentView) {
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.delete(product.class, products.get(pos).getId());
                products.remove(pos);
                currentView.setVisibility(View.INVISIBLE);
            }
        });
    }

    public static class productOptionsHolder {
        public TextView mainText;
        public Button deleteButton;
    }
}
