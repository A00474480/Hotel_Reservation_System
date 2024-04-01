package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.data.MockDataProvider;
import com.example.hotel_reservation_system.model.Hotel;

import java.util.List;

public class HotelListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private HotelAdapter adapter;
    private TextView headingTextView;
    private String checkInDate, checkOutDate, numberOfGuests, guestName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hotel_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headingTextView = view.findViewById(R.id.textViewHeading);
        setupUIComponents(view);

        retrieveArguments();

        headingTextView.setText("Welcome "+ guestName +" , displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);

        getHotelsListsData();
    }

    private void setupUIComponents(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewHotels);
        progressBar = view.findViewById(R.id.progressBarLoading);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void retrieveArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            checkInDate = bundle.getString("checkInDate");
            checkOutDate = bundle.getString("checkOutDate");
            numberOfGuests = bundle.getString("numberOfGuests");
            guestName = bundle.getString("guestName");
        }
    }

    private void initHotelListData() {
        List<Hotel> hotels = MockDataProvider.getMockHotels();
        setupRecyclerView(hotels);
    }

    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        initHotelListData();
        progressBar.setVisibility(View.GONE);
    }

    private void setupRecyclerView(List<Hotel> hotels) {
        adapter = new HotelAdapter(hotels);
        recyclerView.setAdapter(adapter);
    }
}
