package com.apps.barclaysitunes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.barclaysitunes.R;
import com.squareup.picasso.Picasso;


public class ItunesDetailActivity extends AppCompatActivity {
    private String artistNameBundle = null;
    private String artworkURL = null;
    private String trackNameBundle = null;
    private TextView artistName = null;
    private ImageView trackImage = null;
    private TextView trackname = null;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("MusicDetails");
        updateUI();
    }

    //update details activity
    private void updateUI() {
        bundle = getIntent().getExtras();
        artistNameBundle = bundle.getString("artistName");
        trackNameBundle = bundle.getString("song");
        artworkURL = bundle.getString("artworkURL");

        artistName = (TextView) this.findViewById(R.id.artistName);
        trackname = (TextView) this.findViewById(R.id.trackName);
        trackImage = (ImageView) this.findViewById(R.id.imageId);

        artistName.setText("Artist: " + artistNameBundle);
        trackname.setText("Track: " + trackNameBundle);
        Picasso.with(getApplicationContext()).load(artworkURL).resize(200, 200).into(trackImage);

    }

    //Back button to navigates to homescreen
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



