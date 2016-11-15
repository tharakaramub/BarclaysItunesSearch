package com.apps.barclaysitunes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.barclaysitunes.R;
import com.apps.barclaysitunes.model.ItunesResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItunesListAdapter extends
        RecyclerView.Adapter<ItunesListAdapter.GeoNameViewHolder> {

    private Context context;
    private List<ItunesResponse> placesList;
    private List<ItunesResponse> itunesResponseList = new ArrayList<ItunesResponse>();


    public ItunesListAdapter(List<ItunesResponse> placesList, Context context) {
        this.placesList = placesList;
        this.context = context;
    }

    @Override
    public int getItemCount() {

        if (placesList != null) {
            return placesList.size();
        } else {
            return 0;
        }
    }

    @Override
    public GeoNameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.music_row, viewGroup, false);

        return new GeoNameViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(GeoNameViewHolder placesViewHolder, final int position) {
        ItunesResponse ci = placesList.get(position);
        placesViewHolder.trackName.setText(ci.getTrackName());
        placesViewHolder.artistName.setText(ci.getArtistName());
        Picasso.with(context).load(placesList.get(position).getArtworkUrl100()).resize(200, 200).into(placesViewHolder.imageView);

        // placesViewHolder.capital.setText(ci.getCapital());
        // placesViewHolder.code.setText(ci.getCountryCode());
        placesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItunesResponse response = placesList.get(position);

                Intent detailIntent = new Intent(v.getContext(), com.apps.barclaysitunes.activity.ItunesDetailActivity.class);
                detailIntent.putExtra("artistName", response.getArtistName());
                detailIntent.putExtra("song", response.getTrackName());
                detailIntent.putExtra("artworkURL", response.getArtworkUrl100());
                v.getContext().startActivity(detailIntent);
            }
        });
    }

    public class GeoNameViewHolder extends RecyclerView.ViewHolder {

        protected TextView trackName;
        protected TextView artistName;
        protected ImageView imageView;

        public GeoNameViewHolder(View v) {
            super(v);
            trackName = (TextView) v.findViewById(R.id.trackName);
            artistName = (TextView) v.findViewById(R.id.artistName);
            imageView = (ImageView) v.findViewById(R.id.imageId);
        }
    }
}
