package com.apps.barclaysitunes.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.apps.barclaysitunes.R;
import com.apps.barclaysitunes.adapter.ItunesListAdapter;
import com.apps.barclaysitunes.service.ItunesAPI;
import com.apps.barclaysitunes.service.ServiceConnection;

import com.apps.barclaysitunes.model.Response;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = HomeActivity.class.getName();
    ItunesListAdapter itunesListAdapter;
    private Subscription subscription;
    private ProgressDialog pDialog = null;
    private Button btnSearch;
    private EditText tvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button) findViewById(R.id.searchButton);
        tvSearch = (EditText) findViewById(R.id.searchText);
        
        final Spinner spinner = (Spinner) findViewById(R.id.entity_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.entity_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callServerFetchGeoNamesList(tvSearch.getText().toString(), spinner.getSelectedItem().toString());
            }
        });
    }

    private void callServerFetchGeoNamesList(String enteredString, String selectedEntity) {
        if (!enteredString.isEmpty()) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Searching Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
            final ItunesAPI service = ServiceConnection.createRetrofitClient();

            subscription = service.getAllCountriesGeoNames(enteredString, selectedEntity)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myObserver);
        } else {
            showAlert("please enter some text to search");
        }

    }

    private void showAlert(String message) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        alertDialog.show();
    }

    Observer<Response> myObserver = new Observer<Response>() {

        @Override
        public void onCompleted() {
            subscription.unsubscribe();
            pDialog.dismiss();
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
            Log.d(TAG, ">>>> onError gets called : " + e.getMessage());
            showAlert("There is no search results found for entered text or entity");
            pDialog.dismiss();
        }

        @Override
        public void onNext(Response geoNamesApi) {
            if (!geoNamesApi.getResults().isEmpty()) {
                findViewAndSetAdapter(geoNamesApi);
            } else {
                showAlert("There is no search results found for entered text or entity");
            }
        }
    };

    private void findViewAndSetAdapter(Response geoNamesApi) {

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ItunesListAdapter ca = new ItunesListAdapter(geoNamesApi.getResults(), getBaseContext());
        recList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
