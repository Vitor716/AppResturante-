package com.example.atividadepam;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.xml.transform.Result;

public class FetchAddressintentService extends IntentService {

    private ResultReceiver resultReceiver;

    public FetchAddressintentService(){
        super("FetchAddressintentService");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            String errorMessage = "";
            resultReceiver = intent.getParcelableExtra(Constants.RECEIVER);
            Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
            if(location == null){
                return;
            }
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try{
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
            }catch (Exception exception){
                errorMessage = exception.getMessage();
            }
            if(addresses == null || addresses.isEmpty()){
                deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage);
            }else {
                Address address = addresses.get(0);
                ArrayList<String> addressFragments = new ArrayList<>();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                   addressFragments.add(address.getAddressLine(i));
                }
                deliverResultToReceiver(
                        Constants.SUCESS_RESULT,
                        TextUtils.join(
                                Objects.requireNonNull(System.getProperty("separação da linha")),
                                addressFragments
                        )
                );
            }
        }
    }

    private void deliverResultToReceiver(int resultCode, String addressMessage){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, addressMessage);
        resultReceiver.send(resultCode, bundle);
    }
}

























