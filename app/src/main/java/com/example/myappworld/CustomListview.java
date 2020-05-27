package com.example.myappworld;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
public class CustomListview extends ArrayAdapter<String> {

    private String [] haberbaslik ;
    private  String [] habericerik ;
    private  String [] habertarih ;
    private  String [] sitelink ;
    private  String[] imagetext ;
    private Activity context;


    public CustomListview(Activity context,String [] haberbaslik, String [] habericerik, String [] imagetext,String [] habertarih ,String [] sitelink) {


        super(context, R.layout.listview_layout,haberbaslik);
        this.context = context;
        this.haberbaslik = haberbaslik;
        this.habericerik = habericerik;
        this.habertarih= habertarih;
        this.sitelink= sitelink;

        this.imagetext = imagetext;




    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;

        if (r==null)

        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {

            viewHolder =    (ViewHolder) r.getTag();


        }



        viewHolder.tvw1.setText(haberbaslik[position]);
        viewHolder.tvw2.setText(habericerik[position]);
        viewHolder.tvw3.setText(habertarih[position]);


        Picasso.get().load(imagetext[position]).into(viewHolder.img);

        if(imagetext[position] != null && imagetext[position].length()>0)
        {
            Picasso.get().load(imagetext[position]).placeholder(R.drawable.empty).into(viewHolder.img);
        }else {
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.empty).into(viewHolder.img);
        }


        viewHolder.tvw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Yönlendiriliyor...",Toast.LENGTH_SHORT).show();

                Uri uri = Uri.parse(sitelink[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);



            }
        });

        viewHolder.tvw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Yönlendiriliyor...",Toast.LENGTH_SHORT).show();

                Uri uri = Uri.parse(sitelink[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);



            }
        });


        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Yönlendiriliyor...",Toast.LENGTH_SHORT).show();

                Uri uri = Uri.parse(sitelink[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);



            }
        });






        return r;
    }

    class  ViewHolder
    {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;

        ImageView img;

        ViewHolder (View v)

        {
            tvw1 = v.findViewById(R.id.textView);
            tvw2 = v.findViewById(R.id.textView2);
            tvw3 = v.findViewById(R.id.textView3);
            img = v.findViewById(R.id.imageView2);

        }

    }
}
