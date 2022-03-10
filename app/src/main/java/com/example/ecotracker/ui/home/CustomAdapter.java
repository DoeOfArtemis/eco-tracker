package com.example.ecotracker.ui.home;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ecotracker.R;
import com.example.ecotracker.model.Task;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList dataSet;
    Context context;

    private static class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }

    public CustomAdapter(ArrayList data, Context context) {
        super(context, R.layout.row_item_in_home_fragment, data);
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Task getItem(int position) {
        return (Task) dataSet.get(position);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_in_home_fragment, parent, false);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.taskName);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            return convertView;
        }
        Task task = getItem(position);
        viewHolder.textView.setText(task.getDescription());
        viewHolder.checkBox.setChecked(task.isCompleted());

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.textView.setPaintFlags(viewHolder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                HomeFragment.getPoints(position);
                HomeFragment.updateItem(position);
                HomeFragment.removeItem(position);
            }
        });
        return result;
    }
}
