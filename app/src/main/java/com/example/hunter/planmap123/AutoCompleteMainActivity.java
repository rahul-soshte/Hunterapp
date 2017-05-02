package com.example.hunter.planmap123;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunter.planmap123.common.SampleFragmentBase;
import com.example.hunter.planmap123.logger.Log;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

public class AutoCompleteMainActivity extends SampleFragmentBase {

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private TextView mPlaceDetailsText;
    private TextView mPlaceAttriibution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_main);
        Button openButton = (Button) findViewById(R.id.open_button);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAutocompleteActivity();
            }
        });
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttriibution = (TextView) findViewById(R.id.place_attribution);


    }

    private void openAutocompleteActivity() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);


        } catch (GooglePlayServicesRepairableException e) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(), 0).show();

        } catch (GooglePlayServicesNotAvailableException e) {
            String message = "Google Play Services is not available:" + GoogleApiAvailability.getInstance().getErrorString(e.errorCode);
            Log.e(TAG, message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place Selected: " + place.getName());
                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(), place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));
                CharSequence attributions = place.getAttributions();
                if (!TextUtils.isEmpty(attributions)) {
                    mPlaceAttriibution.setText(Html.fromHtml(attributions.toString()));


                } else {
                    mPlaceAttriibution.setText("");
                }
            }
                else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    Log.e(TAG, "Error:Status=" + status.toString());

                } else if (resultCode == RESULT_CANCELED) {

                }
            }
        }

    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id, CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber, websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber, websiteUri));

    }
}

