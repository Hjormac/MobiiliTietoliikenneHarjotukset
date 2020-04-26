package com.example.stockbroker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends ArrayAdapter<Stock> {

    private Context mContext;
    private List<Stock> stockList = new ArrayList<>();

    public StockAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Stock> list) {
        super(context,0,list);
        mContext = context;
        stockList = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listitem,parent,false);

        Stock current = stockList.get(position);





        TextView type = (TextView) listItem.findViewById(R.id.textView_name);
        type.setText(current.getName()+":");

        TextView duration = (TextView) listItem.findViewById(R.id.textView_price);
        duration.setText("" + current.getPrice() + " USD");

        return listItem;
    }


}
