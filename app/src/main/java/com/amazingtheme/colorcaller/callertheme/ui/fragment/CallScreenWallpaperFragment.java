package com.amazingtheme.colorcaller.callertheme.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazingtheme.colorcaller.MainActivity;
import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AdUtils;
import com.amazingtheme.colorcaller.callertheme.OS.ActivityPerM;
import com.amazingtheme.colorcaller.callertheme.ui.CategoriesActivity;
import com.amazingtheme.colorcaller.callertheme.ui.Config;
import com.amazingtheme.colorcaller.callertheme.ui.Hit;
import com.amazingtheme.colorcaller.callertheme.ui.MoreItemActivity;
import com.amazingtheme.colorcaller.callertheme.ui.PopularActivity;
import com.amazingtheme.colorcaller.callertheme.ui.RandomGenerateActivity;
import com.amazingtheme.colorcaller.callertheme.ui.RecyclerAdapter;
import com.amazingtheme.colorcaller.callertheme.ui.ResultFormat;
import com.amazingtheme.colorcaller.callertheme.ui.SearchActivity;
import com.amazingtheme.colorcaller.callertheme.ui.SliderCardAdapter;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CallScreenWallpaperFragment extends Fragment {
    private Activity activity;

    public CallScreenWallpaperFragment() {

    }


    SearchView searchView;
    FloatingActionButton download_video_fab, download_audio_fab, download_images_fab, offline_generator_fab;
    CheckBox download_fab;
    TextView download_video_fab_text, download_audio_fab_text, download_images_fab_text, offline_generato_fab_text;
    LinearLayout ll1;

    private List<Hit> categoryList, sliderList, verticalList, horizontalList;
    private RecyclerView categoriesRecycler, portraitRecycler, landscapeRecycler;
    private HorizontalInfiniteCycleViewPager slideCardView;
    private Button viewAllCategories, viewAllPortrait, viewAllLandscape;
    private RecyclerAdapter categoryAdapter, verticalAdapter, horizontalAdapter;
    private SliderCardAdapter sliderAdapter;
    private static final String URL = new Config().getAPI_URL();
    LinearLayout adsView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call_screen_wallpaper, container, false);
        viewAllCategories = view.findViewById(R.id.view_all_categories);
        viewAllPortrait = view.findViewById(R.id.view_all_portrait);
        viewAllLandscape = view.findViewById(R.id.view_all_landscape);
        slideCardView = view.findViewById(R.id.card_slide);
        categoriesRecycler = view.findViewById(R.id.categories_recycler);
        portraitRecycler = view.findViewById(R.id.portrait_recycler);
        landscapeRecycler = view.findViewById(R.id.landscape_recycler);
        download_fab = view.findViewById(R.id.download_fab);
        download_video_fab = view.findViewById(R.id.download_video_fab);
        download_audio_fab = view.findViewById(R.id.download_audio_fab);
        download_images_fab = view.findViewById(R.id.download_images_fab);
        offline_generator_fab = view.findViewById(R.id.offline_generator_fab);
        download_video_fab_text = view.findViewById(R.id.download_video_fab_text);
        download_audio_fab_text = view.findViewById(R.id.download_audio_fab_text);
        download_images_fab_text = view.findViewById(R.id.download_images_fab_text);
        offline_generato_fab_text = view.findViewById(R.id.offline_generato_fab_text);
        adsView = view.findViewById(R.id.adsView);
        AdUtils.showNativeAd(requireActivity(), adsView, true);
        ll1 = view.findViewById(R.id.ll1);
        download_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (download_fab.isChecked() == true) {
                    download_video_fab.setVisibility(View.VISIBLE);
                    download_audio_fab.setVisibility(View.VISIBLE);
                    download_images_fab.setVisibility(View.VISIBLE);
                    offline_generator_fab.setVisibility(View.VISIBLE);
                    download_video_fab_text.setVisibility(View.VISIBLE);
                    download_audio_fab_text.setVisibility(View.VISIBLE);
                    download_images_fab_text.setVisibility(View.VISIBLE);
                    offline_generato_fab_text.setVisibility(View.VISIBLE);
                    ll1.setVisibility(View.GONE);
                } else {
                    download_video_fab.setVisibility(View.GONE);
                    download_audio_fab.setVisibility(View.GONE);
                    download_images_fab.setVisibility(View.GONE);
                    offline_generator_fab.setVisibility(View.GONE);
                    download_video_fab_text.setVisibility(View.GONE);
                    download_audio_fab_text.setVisibility(View.GONE);
                    download_images_fab_text.setVisibility(View.GONE);
                    offline_generato_fab_text.setVisibility(View.GONE);
                    ll1.setVisibility(View.VISIBLE);
                }

            }
        });
        download_video_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(requireActivity(), state_load -> {
                    startActivity(new Intent(getActivity(), PopularActivity.class));
                });

            }
        });
        download_audio_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(requireActivity(), state_load -> {
                    startActivity(new Intent(getActivity(), CategoriesActivity.class));
                });

            }
        });
        download_images_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), MoreItemActivity.class);
                intent2.putExtra("title", "Gallery");
                startActivity(intent2);


            }
        });
        offline_generator_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), RandomGenerateActivity.class);
                startActivity(intent1);
            }
        });
        searchView = view.findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                hideKeyboard(getActivity());
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("query", s);
                intent.putExtra("extra", "&q=" + s);
                startActivity(intent);
                searchView.setIconified(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        categoryList = new ArrayList<>();
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/17/18/46/domestic-cat-4484279__340.jpg","ANIMALS"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/18/17/55/architecture-4487358__340.jpg", "ARCHITECTURE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/08/26/12/32/abstract-4431599__340.jpg","TEXTURES"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/13/17/48/hat-4474522__340.jpg", "FASHION"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2018/02/16/10/52/beverage-3157395__340.jpg", "BUSINESS"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/07/05/06/51/library-4317851__340.jpg","EDUCATION"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/08/19/13/autumn-4461685__340.jpg","EMOTIONS"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/07/26/coffee-4488464__340.jpg", "FOOD+DRINK"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2016/11/10/15/24/runner-1814460__340.jpg","HEALTH"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/09/21/26/tractor-4464681__340.jpg", "INDUSTRY/CRAFT"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/17/21/32/piano-4484621__340.jpg","MUSIC"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/16/35/landscape-4489716__340.jpg", "NATURE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/15/18/35/novice-4479081__340.jpg","PEOPLE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/12/52/eiffel-tower-4489225__340.jpg","PLACES"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/08/27/05/04/cross-4433376__340.jpg","RELIGION"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/15/13/06/aerospace-4478233__340.jpg", "SCIENCE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/07/26/extreme-4488462__340.jpg","SPORTS"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/09/08/23/internet-4463031__340.jpg","TECHNOLOGY"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/20/22/30/bmw-4492705__340.jpg", "TRANSPORTATION"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/17/12/54/landscape-4483412__340.jpg","TRAVEL"));

        sliderList = new ArrayList<>();
        verticalList = new ArrayList<>();
        horizontalList = new ArrayList<>();


        categoryAdapter = new RecyclerAdapter(getContext(), R.layout.categories_layout, R.layout.fragment_home, categoryList);
        verticalAdapter = new RecyclerAdapter(getContext(), R.layout.portrait_layout, R.layout.fragment_home, verticalList);
        horizontalAdapter = new RecyclerAdapter(getContext(), R.layout.landscape_layout, R.layout.fragment_home, horizontalList);
        sliderAdapter = new SliderCardAdapter(getContext(), sliderList);

        categoriesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(categoryAdapter);

        portraitRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        portraitRecycler.setAdapter(verticalAdapter);

        landscapeRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        landscapeRecycler.setAdapter(horizontalAdapter);

        new BgTask().execute(new String[]{"&orientation=vertical", "ver"});
        new BgTask().execute(new String[]{"&orientation=horizontal", "hor"});
        new BgTask().execute(new String[]{"&q=wallpaper&per_page=7", "sli"});

        /*fetchData(2, "&q=wallpaper&per_page=7", 0);
        fetchData(1, "&orientation=vertical", 1);
        fetchData(1, "&orientation=horizontal", 2);*/

        viewAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(requireActivity(), state_load -> {
                    startActivity(new Intent(requireActivity(), CategoriesActivity.class));
                });
            }
        });
        viewAllPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(requireActivity(), state_load -> {
                    Intent intent = new Intent(requireActivity(), MoreItemActivity.class);
                    intent.putExtra("title", "Potrait Wallpapers");
                    intent.putExtra("extra", "&orientation=vertical");
                    startActivity(intent);
                });

            }
        });
        viewAllLandscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(requireActivity(), state_load -> {
                    Intent intent = new Intent(requireActivity(), MoreItemActivity.class);
                    intent.putExtra("title", "Landscape Wallpapers");
                    intent.putExtra("extra", "&orientation=horizontal");
                    startActivity(intent);
                });

            }
        });
        return view;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class BgTask extends AsyncTask<String[], String[], List<Hit>> {

        @Override
        protected List<Hit> doInBackground(final String[]... strings) {
            for (final String[] string : strings) {

                StringRequest request = new StringRequest(URL + string[0] + "&page=" + 3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("CODE", response);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        ResultFormat result = gson.fromJson(response, ResultFormat.class);
                        List<Hit> hit = result.getHits();
                        if (string[1].equals("sli")) {
                            for (int i = 0; i < hit.size(); i++) {
                                sliderList.add(new Hit(hit.get(i).getLargeImageURL(), hit.get(i).getId(), hit.get(i).getTags(), hit.get(i).getWebformatURL(), hit.get(i).getPreviewURL()));
                            }
                            slideCardView.setAdapter(sliderAdapter);
                        }
                        if (strings[0][1].equals("ver")) {
                            for (int i = 0; i < hit.size(); i++) {
                                verticalList.add(new Hit(hit.get(i).getLargeImageURL(), hit.get(i).getId(), hit.get(i).getTags(), hit.get(i).getWebformatURL(), hit.get(i).getPreviewURL()));
                            }
                            verticalAdapter.notifyDataSetChanged();
                        }
                        if (strings[0][1].equals("hor")) {
                            for (int i = 0; i < hit.size(); i++) {
                                horizontalList.add(new Hit(hit.get(i).getLargeImageURL(), hit.get(i).getId(), hit.get(i).getTags(), hit.get(i).getWebformatURL(), hit.get(i).getPreviewURL()));
                            }
                            horizontalAdapter.notifyDataSetChanged();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
                queue.add(request);
            }
            return null;
        }
    }

    @Override
    public void onResume() {
        int i;
        super.onResume();

        AdUtils.loadInitialInterstitialAds(requireActivity());
        AdUtils.loadAppOpenAds(requireActivity());
        AdUtils.loadInitialNativeList(requireActivity());
    }
}