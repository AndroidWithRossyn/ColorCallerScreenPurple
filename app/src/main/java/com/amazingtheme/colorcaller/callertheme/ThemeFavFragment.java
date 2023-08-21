package com.amazingtheme.colorcaller.callertheme;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.callertheme.categoryui.FavouritesAdapter;
import com.amazingtheme.colorcaller.callertheme.categoryui.Images;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ThemeFavFragment extends Fragment {
    private static final String FAVORITES_PREF_NAME = "my_favorites_theme";
    private RecyclerView favorite_image_recyclerview;
    ImageView delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme_fav, container, false);
        favorite_image_recyclerview = view.findViewById(R.id.favorite_image_recyclerview);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(FAVORITES_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> favoriteUrls = sharedPreferences.getStringSet("favorite_urls", new HashSet<>());

        ArrayList<Images> favoriteDataList = new ArrayList<>();
        for (String imageUrl : favoriteUrls) {
            Images data = new Images();
            data.setUrl(imageUrl);
            favoriteDataList.add(data);
        }

        FavouritesAdapter favoriteAdapter = new FavouritesAdapter(requireActivity(), favoriteDataList);
        favorite_image_recyclerview.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        favorite_image_recyclerview.setAdapter(favoriteAdapter);

        return view;
    }
}