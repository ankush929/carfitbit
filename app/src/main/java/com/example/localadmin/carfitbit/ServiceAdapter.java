package com.example.localadmin.carfitbit;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.localadmin.carfitbit.Models.OrderStatus;
import com.example.localadmin.carfitbit.Models.TimeLineModel;
import com.github.vipulasri.timelineview.TimelineView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{

    private Context context;
    private ArrayList<TimeLineModel> serviceArrayList = new ArrayList<>();

    ServiceAdapter(ArrayList<TimeLineModel> serviceArrayList, Context context) {
        this.context = context;
        this.serviceArrayList=serviceArrayList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.timeline_item, viewGroup, false);

        return new ServiceViewHolder(view,i);
    }
    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i) {

       TimeLineModel timeLineModel   = serviceArrayList.get(i);

            if(timeLineModel.getStatus() == OrderStatus.INACTIVE)
                setMarker(serviceViewHolder, R.drawable.ic_marker_inactive, R.color.colorGrey500);

            else if(timeLineModel.getStatus() == OrderStatus.ACTIVE)
                setMarker(serviceViewHolder, R.drawable.ic_marker_active, R.color.colorGrey500);
            else
                setMarker(serviceViewHolder, R.drawable.ic_marker, R.color.colorGrey500);

        if (!timeLineModel.getDate().isEmpty()) {
        serviceViewHolder.date.setVisibility(View.VISIBLE);
        serviceViewHolder.date.setText(parseDateTime(timeLineModel.getDate(), "yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy"));
    } else
    serviceViewHolder.date.setVisibility(View.GONE);

    serviceViewHolder.message.setText(timeLineModel.getMessage());
}

    String parseDateTime(String dateString,String originalFormat, String outputFromat){

        SimpleDateFormat formatter = new SimpleDateFormat(originalFormat, Locale.US);
        Date date = new Date();

        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(outputFromat, new Locale("US"));

            return dateFormat.format(date);


    }
    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    private void setMarker(ServiceViewHolder serviceViewHolder , int drawableResId, int colorFilter) {
        serviceViewHolder.timelineView.setMarker(this.getDrawable(serviceViewHolder.itemView.getContext(),
                drawableResId, ContextCompat.getColor(serviceViewHolder.itemView.getContext(), colorFilter)));
    }

   Drawable getDrawable(Context context, int drawableResId) {
        return VectorDrawableCompat.create(context.getResources(), drawableResId, context.getTheme());
    }

    Drawable getDrawable(Context context , int drawableResId, int colorFilter ) {
        Drawable drawable = getDrawable(context, drawableResId);
        drawable.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {

        TimelineView timelineView;
        TextView date;
        TextView message;
        ServiceViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);


            timelineView= itemView.findViewById(R.id.timeline);
            timelineView.initLine(viewType);
            date=itemView.findViewById(R.id.text_timeline_date);
            message=itemView.findViewById(R.id.text_timeline_title);
        }
    }

}
