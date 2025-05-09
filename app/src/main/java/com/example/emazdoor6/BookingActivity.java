package com.example.emazdoor6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize views
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ImageView btnBack = findViewById(R.id.btn_back);

        // Set up back button
        btnBack.setOnClickListener(v -> finish());

        // Set up ViewPager with fragments
        BookingPagerAdapter pagerAdapter = new BookingPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Upcoming");
                    break;
                case 1:
                    tab.setText("History");
                    break;
                case 2:
                    tab.setText("Draft");
                    break;
            }
        }).attach();
    }

    // ViewPager adapter for booking tabs
    private static class BookingPagerAdapter extends FragmentStateAdapter {

        public BookingPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return the appropriate fragment for each tab
            return BookingListFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 3; // Upcoming, History, Draft
        }
    }

    // Fragment to display booking lists
    public static class BookingListFragment extends Fragment {
        private static final String ARG_TAB_POSITION = "tab_position";
        private int tabPosition;

        public static BookingListFragment newInstance(int tabPosition) {
            BookingListFragment fragment = new BookingListFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_TAB_POSITION, tabPosition);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                tabPosition = getArguments().getInt(ARG_TAB_POSITION);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_booking_list, container, false);
            RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Create dummy data based on tab position
            List<BookingModel> bookings = createDummyBookings(tabPosition);
            BookingAdapter adapter = new BookingAdapter(bookings);
            recyclerView.setAdapter(adapter);

            return rootView;
        }

        private List<BookingModel> createDummyBookings(int tabPosition) {
            List<BookingModel> bookings = new ArrayList<>();

            if (tabPosition == 0) { // Upcoming
                bookings.add(new BookingModel(
                        "AC Installation",
                        "#D-571224",
                        "Confirmed",
                        "8:00-9:00 AM, 09 Dec",
                        "Westinghouse",
                        R.drawable.ic_ac_repair,
                        true));
                bookings.add(new BookingModel(
                        "Multi Mask Facial",
                        "#D-571225", // Unique reference code
                        "Pending",
                        "2:00-3:00 PM, 10 Dec", // Different time
                        "Sindenayu",
                        R.drawable.ic_beauty,
                        false));
            } else if (tabPosition == 1) { // History
                bookings.add(new BookingModel(
                        "Home Cleaning",
                        "#D-570124",
                        "Completed",
                        "10:00-12:00 AM, 01 Dec",
                        "CleanMaster",
                        R.drawable.ic_cleaning,
                        true));
                bookings.add(new BookingModel(
                        "Plumbing Service",
                        "#D-569854",
                        "Completed",
                        "2:00-3:00 PM, 28 Nov",
                        "FixItPro",
                        R.drawable.ic_plumbing,
                        true));
                // Add one more completed booking for variety
                bookings.add(new BookingModel(
                        "Electronics Repair",
                        "#D-569123",
                        "Completed",
                        "11:00-12:00 AM, 20 Nov",
                        "TechFix",
                        R.drawable.ic_electronics,
                        true));
            } else { // Draft
                bookings.add(new BookingModel(
                        "Appliance Repair",
                        "#D-Draft",
                        "Draft",
                        "Not scheduled",
                        "Not assigned",
                        R.drawable.ic_appliance,
                        false));
                // Add one more draft booking
                bookings.add(new BookingModel(
                        "Home Painting",
                        "#D-Draft",
                        "Draft",
                        "Not scheduled",
                        "Not assigned",
                        R.drawable.ic_painting,
                        false));
            }

            return bookings;
        }
    }

    // Adapter for the booking list
    public static class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
        private final List<BookingModel> bookings;

        public BookingAdapter(List<BookingModel> bookings) {
            this.bookings = bookings;
        }

        @NonNull
        @Override
        public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
            return new BookingViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
            BookingModel booking = bookings.get(position);
            holder.bind(booking);
        }

        @Override
        public int getItemCount() {
            return bookings.size();
        }

        static class BookingViewHolder extends RecyclerView.ViewHolder {
            private final TextView tvServiceName;
            private final TextView tvReferenceCode;
            private final TextView tvStatus;
            private final TextView tvScheduleTime;
            private final TextView tvProviderName;
            private final ImageView imgServiceIcon;
            private final ImageView imgProvider;
            private final Button btnCall;

            public BookingViewHolder(@NonNull View itemView) {
                super(itemView);
                tvServiceName = itemView.findViewById(R.id.tv_service_name);
                tvReferenceCode = itemView.findViewById(R.id.tv_reference_code);
                tvStatus = itemView.findViewById(R.id.tv_status);
                tvScheduleTime = itemView.findViewById(R.id.tv_schedule_time);
                tvProviderName = itemView.findViewById(R.id.tv_provider_name);
                imgServiceIcon = itemView.findViewById(R.id.img_service_icon);
                imgProvider = itemView.findViewById(R.id.img_provider);
                btnCall = itemView.findViewById(R.id.btn_call);
                
                // Set default profile image
                imgProvider.setImageResource(R.drawable.ic_profile);
            }

            public void bind(BookingModel booking) {
                tvServiceName.setText(booking.getServiceName());
                tvReferenceCode.setText("Reference Code: " + booking.getReferenceCode());
                tvScheduleTime.setText(booking.getScheduleTime());
                tvProviderName.setText(booking.getProviderName());
                imgServiceIcon.setImageResource(booking.getServiceIconResId());
                
                // Set background color for service icon based on service type
                CardView iconBackground = (CardView) imgServiceIcon.getParent();
                if (booking.getServiceName().equals("AC Installation")) {
                    iconBackground.setCardBackgroundColor(0xFFFBD6D2); // Light red for AC
                    imgServiceIcon.setColorFilter(0xFFE57373); // Red tint
                } else if (booking.getServiceName().equals("Multi Mask Facial")) {
                    iconBackground.setCardBackgroundColor(0xFFE0F7FA); // Light blue for beauty
                    imgServiceIcon.setColorFilter(0xFF4FC3F7); // Blue tint
                } else if (booking.getServiceName().equals("Home Cleaning")) {
                    iconBackground.setCardBackgroundColor(0xFFE8F5E9); // Light green for cleaning
                    imgServiceIcon.setColorFilter(0xFF81C784); // Green tint
                } else if (booking.getServiceName().equals("Plumbing Service")) {
                    iconBackground.setCardBackgroundColor(0xFFEDE7F6); // Light purple for plumbing
                    imgServiceIcon.setColorFilter(0xFF9575CD); // Purple tint
                } else if (booking.getServiceName().equals("Appliance Repair")) {
                    iconBackground.setCardBackgroundColor(0xFFFFF3E0); // Light orange for appliance
                    imgServiceIcon.setColorFilter(0xFFFFB74D); // Orange tint
                } else if (booking.getServiceName().equals("Electronics Repair")) {
                    iconBackground.setCardBackgroundColor(0xFFE1F5FE); // Light blue for electronics
                    imgServiceIcon.setColorFilter(0xFF29B6F6); // Blue tint
                } else if (booking.getServiceName().equals("Home Painting")) {
                    iconBackground.setCardBackgroundColor(0xFFF3E5F5); // Light purple for painting
                    imgServiceIcon.setColorFilter(0xFFBA68C8); // Purple tint
                }
                
                // Set status with appropriate color and rounded corners
                tvStatus.setText(booking.getStatus());
                // Create rounded corner background for status
                android.graphics.drawable.GradientDrawable statusBackground = new android.graphics.drawable.GradientDrawable();
                statusBackground.setCornerRadius(8); // 8dp corner radius
                
                switch (booking.getStatus()) {
                    case "Confirmed":
                        statusBackground.setColor(0xFFE8F5E9);
                        tvStatus.setTextColor(0xFF4CAF50);
                        break;
                    case "Pending":
                        statusBackground.setColor(0xFFFFF3E0);
                        tvStatus.setTextColor(0xFFFF9800);
                        break;
                    case "Completed":
                        statusBackground.setColor(0xFFE3F2FD);
                        tvStatus.setTextColor(0xFF2196F3);
                        break;
                    case "Draft":
                        statusBackground.setColor(0xFFF5F5F5);
                        tvStatus.setTextColor(0xFF9E9E9E);
                        break;
                }
                
                tvStatus.setBackground(statusBackground);
                tvStatus.setPadding(24, 8, 24, 8); // Add padding for better appearance

                // Set call button visibility based on booking status
                btnCall.setVisibility(booking.isCallEnabled() ? View.VISIBLE : View.GONE);
                
                // Make the call button more visually appealing
                if (booking.isCallEnabled()) {
                    btnCall.setCompoundDrawablePadding(8);
                }
            }
        }
    }

    // Model class for booking data
    public static class BookingModel {
        private final String serviceName;
        private final String referenceCode;
        private final String status;
        private final String scheduleTime;
        private final String providerName;
        private final int serviceIconResId;
        private final boolean callEnabled;

        public BookingModel(String serviceName, String referenceCode, String status, 
                           String scheduleTime, String providerName, int serviceIconResId,
                           boolean callEnabled) {
            this.serviceName = serviceName;
            this.referenceCode = referenceCode;
            this.status = status;
            this.scheduleTime = scheduleTime;
            this.providerName = providerName;
            this.serviceIconResId = serviceIconResId;
            this.callEnabled = callEnabled;
        }

        public String getServiceName() {
            return serviceName;
        }

        public String getReferenceCode() {
            return referenceCode;
        }

        public String getStatus() {
            return status;
        }

        public String getScheduleTime() {
            return scheduleTime;
        }

        public String getProviderName() {
            return providerName;
        }

        public int getServiceIconResId() {
            return serviceIconResId;
        }

        public boolean isCallEnabled() {
            return callEnabled;
        }
    }
}