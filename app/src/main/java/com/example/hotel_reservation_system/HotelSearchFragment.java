package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;

public class HotelSearchFragment extends Fragment{
    private EditText numberOfGuestsEditText;
    private EditText guestNameEditText;
    private DatePicker checkInDatePicker;
    private DatePicker checkOutDatePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hotel_search_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI components
        checkInDatePicker = view.findViewById(R.id.check_in_date_picker);
        checkOutDatePicker = view.findViewById(R.id.check_out_date_picker);
        numberOfGuestsEditText = view.findViewById(R.id.number_of_guests_edit_text);
        guestNameEditText = view.findViewById(R.id.name_edit_text);
        Button searchButton = view.findViewById(R.id.search_button);

        // Set the search button click listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize Calendar instances for the dates
                Calendar checkInCalendar = Calendar.getInstance();
                Calendar checkOutCalendar = Calendar.getInstance();
                Calendar today = Calendar.getInstance();

                // Set the Calendar instances to the selected dates
                checkInCalendar.set(checkInDatePicker.getYear(), checkInDatePicker.getMonth(), checkInDatePicker.getDayOfMonth());
                checkOutCalendar.set(checkOutDatePicker.getYear(), checkOutDatePicker.getMonth(), checkOutDatePicker.getDayOfMonth());

                // Convert EditText input to integer for number of guests
                String numberOfGuestsStr = numberOfGuestsEditText.getText().toString();
                int numberOfGuests = numberOfGuestsStr.isEmpty() ? 0 : Integer.parseInt(numberOfGuestsStr);

                // Get the guest name
                String guestName = guestNameEditText.getText().toString().trim();

                // Validation logic
                if (checkInCalendar.before(today)) {
                    Toast.makeText(getContext(), "Check-in date cannot be in the past.", Toast.LENGTH_SHORT).show();
                } else if (checkOutCalendar.before(checkInCalendar) || checkOutCalendar.equals(checkInCalendar)) {
                    Toast.makeText(getContext(), "Check-out date must be after check-in date.", Toast.LENGTH_SHORT).show();
                } else if (numberOfGuests <= 0) {
                    Toast.makeText(getContext(), "Number of guests must be at least 1.", Toast.LENGTH_SHORT).show();
                } else if (!guestName.matches("[a-zA-Z ]+") || guestName.length() > 50) {
                    Toast.makeText(getContext(), "Name should contain only alphabets and be less than 50 characters.", Toast.LENGTH_SHORT).show();
                } else {
                    // All validations passed, proceed with creating a bundle and fragment transaction
                    String checkInDate = formatDate(checkInCalendar);
                    String checkOutDate = formatDate(checkOutCalendar);

                    // Bundle the data
                    Bundle bundle = new Bundle();
                    bundle.putString("checkInDate", checkInDate);
                    bundle.putString("checkOutDate", checkOutDate);
                    bundle.putString("numberOfGuests", numberOfGuestsStr);
                    bundle.putString("guestName", guestName);

                    // Create a new instance of HotelsListFragment and set its arguments
                    HotelListFragment hotelsListFragment = new HotelListFragment();
                    hotelsListFragment.setArguments(bundle);

                    // Perform the fragment transaction to replace this fragment with HotelsListFragment
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, hotelsListFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });


    }
    private String formatDate(Calendar calendar) {
        return String.format("%02d-%02d-%d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }
    private String getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        return String.format("%02d-%02d-%d", day, month, year);
    }

}
