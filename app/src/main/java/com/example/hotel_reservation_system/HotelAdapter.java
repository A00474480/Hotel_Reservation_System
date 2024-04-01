package com.example.hotel_reservation_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.HotelViewHolder;
import com.example.hotel_reservation_system.R;
import com.example.hotel_reservation_system.model.Hotel;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelViewHolder> {

    private List<Hotel> hotelList;

    public HotelAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.textViewHotelName.setText(hotel.getName());
        holder.textViewHotelPrice.setText(hotel.getPrice());
        holder.textViewHotelAvailability.setText(hotel.getAvailability());
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}