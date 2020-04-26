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

public class LeagueAdapter extends ArrayAdapter<League> {

    private Context mContext;
    private List<League> leagueList = new ArrayList<>();

    public LeagueAdapter(@NonNull Context context, @LayoutRes ArrayList<League> list) {
        super(context,0,list);
        mContext = context;
        leagueList = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        League current = leagueList.get(position);





        TextView type = (TextView) listItem.findViewById(R.id.textView_name);
        type.setText(current.getName());



        return listItem;
    }

}