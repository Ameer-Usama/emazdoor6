package com.example.homeservices;

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

import com.example.emazdoor6.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// R کلاس automatically generated ہوتی ہے، لیکن یہاں ہمیں اسے import کرنا پڑے گا کیونکہ پیکیج نام مختلف ہے

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

        viewDetailsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle view details click
                // This could show additional pricing information
            }
        });
    }

    private void showDatePicker() {
        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(Calendar.YEAR, year);
                    selectedDate.set(Calendar.MONTH, month);
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateDisplay();
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));

        // Set minimum date to today
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        android.app.TimePickerDialog timePickerDialog = new android.app.TimePickerDialog(
                getContext(),
                (view, hourOfDay, minute) -> {
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedTime.set(Calendar.MINUTE, minute);
                    updateTimeDisplay();
                },
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE),
                false);
        timePickerDialog.show();
    }

    private void updateDateDisplay() {
        dateValue.setText(dateFormat.format(selectedDate.getTime()));
    }

    private void updateTimeDisplay() {
        timeValue.setText(timeFormat.format(selectedTime.getTime()));
    }
}