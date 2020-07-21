package com.example.androidapplication.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidapplication.R;

import androidx.fragment.app.Fragment;

public class ContactusFragment extends Fragment {

    TextView phoneNumber, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        phoneNumber = view.findViewById(R.id.phone_number);
        email = view.findViewById(R.id.email);

        phoneNumber.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phone = phoneNumber.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+phone));
                        startActivity(intent);
                    }
                }
        );

        email.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt_email = email.getText().toString();
                        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + txt_email));
                        startActivity(intent);
                    }
                }
        );

        return view;

    }
}
