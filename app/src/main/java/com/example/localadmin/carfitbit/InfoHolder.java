package com.example.localadmin.carfitbit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoHolder extends RecyclerView.ViewHolder {

    ImageView photo1;
    ImageView photo2;
    ImageView photo3;
    ImageView photo4;

    TextView amenities1;
    TextView amenities2;
    TextView amenities3;
    TextView amenities4;

    View photos;
    View amenities;
    View time;
    View cancel;

    TextView title;

    public InfoHolder(@NonNull View itemView) {
        super(itemView);

        photos=itemView.findViewById(R.id.photos_include);
        amenities=itemView.findViewById(R.id.amenties_include);
        time=itemView.findViewById(R.id.time_include);
        cancel=itemView.findViewById(R.id.cancel_include);

        photo1=itemView.findViewById(R.id.photo1);
        photo2=itemView.findViewById(R.id.photo2);
        photo3=itemView.findViewById(R.id.photo3);
        photo4=itemView.findViewById(R.id.photo4);

        amenities1=itemView.findViewById(R.id.amenities1);
        amenities2=itemView.findViewById(R.id.amenities2);
        amenities3=itemView.findViewById(R.id.amenities3);
        amenities4=itemView.findViewById(R.id.amenities4);

        title=itemView.findViewById(R.id.title);

    }
}

