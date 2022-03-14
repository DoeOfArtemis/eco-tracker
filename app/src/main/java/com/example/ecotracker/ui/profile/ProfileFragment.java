package com.example.ecotracker.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.ecotracker.LoginActivity;
import com.example.ecotracker.AccountSettingsActivity;
import com.example.ecotracker.R;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.model.User;

public class ProfileFragment extends Fragment {

    EcoTrackerDatabase db;
    User user;
    TextView userName, name, email, level;
    Button logOutButton, changeAccountSettingsButton;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        db = EcoTrackerDatabase.getDatabase(this.getContext());
        String userNameFromLogin = getArguments().getString("userName");
        user = db.userDao().findByUserName(userNameFromLogin);

        userName = (TextView) view.findViewById(R.id.profile_userName);
        userName.setText(user.getUserName());
        name = (TextView) view.findViewById(R.id.profile_name);
        name.setText(user.getName());
        email = (TextView) view.findViewById(R.id.profile_email);
        email.setText(user.getEmail());
        level = (TextView) view.findViewById(R.id.profile_level);
        level.setText(user.getLevel());

        logOutButton = (Button) view.findViewById(R.id.profile_logout_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


        changeAccountSettingsButton = (Button) view.findViewById(R.id.accountSettingsButton);
        changeAccountSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AccountSettingsActivity.class);
                intent.putExtra("user", user.getUserName());
                intent.putExtra("password", user.getPassword());
                startActivity(intent);
            }
        });

        return view;
    }

}