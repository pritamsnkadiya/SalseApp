package com.example.pritam.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pritam.salseapp.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context mContext;
    private ArrayList dataSet;

    public CustomAdapter(ArrayList data, Context context) {
        super (context, R.layout.layout_product_list_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return dataSet.size ();
    }

    @Override
    public Product getItem(int position) {
        return (Product) dataSet.get (position);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder ();
            convertView = LayoutInflater.from (parent.getContext ()).inflate (R.layout.layout_product_list_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById (R.id.name_);
            viewHolder.checkBox = (CheckBox) convertView.findViewById (R.id.checkbox);

            result = convertView;
            convertView.setTag (viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag ();
            result = convertView;
        }

        Product item = getItem (position);


        viewHolder.txtName.setText (item.name);
        viewHolder.checkBox.setChecked (item.selected);


        return result;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
    }
}