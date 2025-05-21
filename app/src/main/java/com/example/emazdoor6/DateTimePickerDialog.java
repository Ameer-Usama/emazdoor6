package com.example.emazdoor6;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimePickerDialog extends Dialog {

    private TextView dateValue, timeValue, totalPriceValue;
    private CardView dateCard, timeCard;
    private Button continueButton;
    private ImageButton closeButton;
    private TextView viewDetailsText;
    private Calendar selectedDate = Calendar.getInstance();
    private Calendar selectedTime = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    private double totalPrice = 150.50; // Default price

    private OnDateTimeSelectedListener listener;

    public interface OnDateTimeSelectedListener {
        void onDateTimeSelected(Calendar date, Calendar time);
    }

    public DateTimePickerDialog(@NonNull Context context) {
        super(context);
    }

    public void setOnDateTimeSelectedListener(OnDateTimeSelectedListener listener) {
        this.listener = listener;
    }

    public void setTotalPrice(double price) {
        this.totalPrice = price;
        if (totalPriceValue != null) {
            totalPriceValue.setText(String.format(Locale.getDefault(), "USD %.2f", totalPrice));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.date_time_picker_dialog);

        // Initialize views
        dateValue = findViewById(R.id.dateValue);
        timeValue = findViewById(R.id.timeValue);
        dateCard = findViewById(R.id.dateCard);
        timeCard = findViewById(R.id.timeCard);
        continueButton = findViewById(R.id.continueButton);
        closeButton = findViewById(R.id.closeButton);
        viewDetailsText = findViewById(R.id.viewDetailsText);
        totalPriceValue = findViewById(R.id.totalPriceValue);

        // Set total price
        totalPriceValue.setText(String.format(Locale.getDefault(), "USD %.2f", totalPrice));

        // Set up click listeners
        dateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        timeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDateTimeSelected(selectedDate, selectedTime);
                }
                dismiss();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Set initial values
        updateDateDisplay();
        updateTimeDisplay();
    }

    private void showDatePicker() {
        int year = selectedDate.get(Calendar.YEAR);
        int month = selectedDate.get(Calendar.MONTH);
        int day = selectedDate.get(Calendar.DAY_OF_MONTH);

        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(
                getContext(),
                (view, year1, month1, dayOfMonth) -> {
                    selectedDate.set(Calendar.YEAR, year1);
                    selectedDate.set(Calendar.MONTH, month1);
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateDisplay();
                }, year, month, day);

        datePickerDialog.show();
    }

    private void showTimePicker() {
        int hour = selectedTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedTime.get(Calendar.MINUTE);

        android.app.TimePickerDialog timePickerDialog = new android.app.TimePickerDialog(
                getContext(),
                (view, hourOfDay, minute1) -> {
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedTime.set(Calendar.MINUTE, minute1);
                    updateTimeDisplay();
                }, hour, minute, false);

        timePickerDialog.show();
    }

    private void updateDateDisplay() {
        dateValue.setText(dateFormat.format(selectedDate.getTime()));
    }

    private void updateTimeDisplay() {
        timeValue.setText(timeFormat.format(selectedTime.getTime()));
    }
}