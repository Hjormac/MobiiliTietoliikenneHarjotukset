package com.example.football;

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

public class AreaAdapter extends ArrayAdapter<Area> {

    private Context mContext;
    private List<Area> areaList = new ArrayList<>();

    public AreaAdapter(@NonNull Context context, @LayoutRes ArrayList<Area> list) {
        super(context,0,list);
        mContext = context;
        areaList = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Area current = areaList.get(position);





        TextView type = (TextView) listItem.findViewById(R.id.textView_name);
        type.setText(current.getName());



        return listItem;
    }

}

