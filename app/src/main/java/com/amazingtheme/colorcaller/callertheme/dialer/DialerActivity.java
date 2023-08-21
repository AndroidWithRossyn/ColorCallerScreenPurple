package com.amazingtheme.colorcaller.callertheme.dialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AdUtils;
import com.amazingtheme.colorcaller.callertheme.ui.CategoriesActivity;

public class DialerActivity extends AppCompatActivity {
    Button callBtn;
    EditText phoneNumber;
    LinearLayout adsView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        adsView0=findViewById(R.id.adsView0);
        AdUtils.showNativeAd(DialerActivity.this, adsView0, false);
        initVariables();

        callBtn.setOnClickListener(v -> {
            String inputNumber = phoneNumber.getText().toString();

            if (!inputNumber.isEmpty()) {

                @SuppressLint("ServiceCast") TelecomManager telecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
                Uri uri = Uri.fromParts("tel", inputNumber, null);
                Bundle extras = new Bundle();
                extras.putBoolean(TelecomManager.EXTRA_START_CALL_WITH_SPEAKERPHONE, false);

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    if (telecomManager.getDefaultDialerPackage().equals(getPackageName())){
                        telecomManager.placeCall(uri, extras);
                    }
                    else{
                        Uri phoneNumber = Uri.parse("tel:" + inputNumber);
                        Intent callIntent = new Intent(Intent.ACTION_CALL, phoneNumber);
                        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                }
                else{
                    Toast.makeText(this, "Please allow permission", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initVariables() {
        callBtn = findViewById(R.id.callBtn);
        phoneNumber = findViewById(R.id.inputNumberET);
    }
}