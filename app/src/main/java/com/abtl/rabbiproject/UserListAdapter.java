package com.abtl.rabbiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.abtl.rabbiproject.R;
import com.abtl.rabbiproject.UserListModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class UserListAdapter extends ArrayAdapter<UserListModel> {
    ArrayList<UserListModel> customers;

    Context context;
    public UserListAdapter(@NonNull Context context, int resource, ArrayList<UserListModel> customers) {
        super(context, R.layout.service_adapter, customers);
        this.context = context;
        this.customers = customers;
    }
    private static class ViewHolder{
        TextView textcomplain;
        TextView textstatus;
        TextView textcustomerNumber;
        TextView serviceBy;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        final View result;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.service_adapter,parent,false);
            holder.textcomplain = (TextView) convertView.findViewById(R.id.complain);
            holder.textstatus = (TextView) convertView.findViewById(R.id.status);
            holder.textcustomerNumber = (TextView) convertView.findViewById(R.id.customerNumber);
            holder.serviceBy = (TextView) convertView.findViewById(R.id.serviceBy);
            result = convertView;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        holder.textcomplain.setText(customers.get(position).getUser_info_name());
        holder.textstatus.setText("Email : "+customers.get(position).getUser_email());
        holder.textcustomerNumber.setText("Phone : "+customers.get(position).getUser_phone());
        holder.serviceBy.setText("Create Date : "+customers.get(position).getCreate_data());
        return result;
    }
}
