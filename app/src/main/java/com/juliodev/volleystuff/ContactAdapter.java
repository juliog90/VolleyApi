package com.juliodev.volleystuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<Contact> arrayList;
    private static LayoutInflater inflater;

    public ContactAdapter(Context context, ArrayList<Contact> arrayList){

        this.context = context;
        this.arrayList = arrayList;
        this.inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() { return this.arrayList.size(); }

    @Override
    public Object getItem(int position) { return position; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) view = inflater.inflate(R.layout.list_view,null);

        TextView txtName = (TextView)view.findViewById(R.id.txtNombre);
        TextView txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        TextView txtGenero = (TextView) view.findViewById(R.id.txtGenero);
        ImageView images = (ImageView) view.findViewById(R.id.imgImagen);

        Contact c = this.arrayList.get(position);

        images.setImageBitmap(c.getImage());
        txtGenero.setText(c.getGender());
        txtEmail.setText(c.getEmail());
        txtName.setText(c.getName());

        return view;
    }
}
