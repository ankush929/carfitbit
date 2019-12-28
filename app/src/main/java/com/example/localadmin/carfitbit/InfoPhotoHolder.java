package com.example.localadmin.carfitbit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class InfoPhotoHolder extends RecyclerView.ViewHolder {

    ImageView photo1;
    ImageView photo2;
    ImageView photo3;
    ImageView photo4;

    public InfoPhotoHolder(@NonNull View itemView) {

        super(itemView);
        photo1=itemView.findViewById(R.id.photo1);
        photo2=itemView.findViewById(R.id.photo2);
        photo3=itemView.findViewById(R.id.photo3);
        photo4=itemView.findViewById(R.id.photo4);
    }
}
