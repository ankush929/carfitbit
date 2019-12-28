package com.example.localadmin.carfitbit;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.localadmin.carfitbit.Models.Info;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoHolder> {

    private Context context;
    private ArrayList<Info> infoArrayList;
    TextView title;
    View content;


    public InfoAdapter(Context context, ArrayList<Info> infoArrayList) {
        this.infoArrayList = infoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public InfoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.main_card, viewGroup, false);

        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoHolder infoHolder, int i) {

        LinearLayout linearLayout = infoHolder.itemView.findViewById(R.id.main_card_layout);
        switch (i) {
            case 0:
                infoHolder.title.setText("PHOTOS");
                infoHolder.photos.setVisibility(View.VISIBLE);
                break;

            case 1:
                infoHolder.title.setText("AMENITIES");
                infoHolder.amenities.setVisibility(View.VISIBLE);
                break;

            case 2:
                infoHolder.title.setText("TIMINGS");
                infoHolder.time.setVisibility(View.VISIBLE);
                break;

            case 3:
                infoHolder.title.setText("CANCELLATION POLICY");
                infoHolder.cancel.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
