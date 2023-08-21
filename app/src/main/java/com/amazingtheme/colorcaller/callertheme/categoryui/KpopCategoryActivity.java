package com.amazingtheme.colorcaller.callertheme.categoryui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AdUtils;
import com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.Theme_GifActivity_Calling_Theme_Preview;
import com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory.LinearKpopVideoAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class KpopCategoryActivity extends AppCompatActivity/* implements RecyclerInterface*/ {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private static final String TAG = "KpopCategoryActivity";
    private ImagesAdapter imagesAdapter;
    private ImagesVideoAdapter imagesVideoAdapter;
    private ArrayList<Images> imagesListImages = new ArrayList<>();
    private ArrayList<Images> imagesListTrending = new ArrayList<>();
    private ArrayList<Images> imagesListChristmas = new ArrayList<>();
    private ArrayList<Images> imagesListAnimals = new ArrayList<>();
    private ArrayList<Images> imagesListNature = new ArrayList<>();
    private ArrayList<Images> imagesListModern = new ArrayList<>();
    private ArrayList<Images> imagesListCute = new ArrayList<>();
    private ArrayList<Images> imagesListSoccer = new ArrayList<>();
    private ArrayList<Images> imagesListNeon = new ArrayList<>();
    private ArrayList<Images> imagesListLove = new ArrayList<>();
    private ArrayList<Images> imagesListCall = new ArrayList<>();
    private ArrayList<Images> imagesListAnime = new ArrayList<>();
    private ArrayList<Images> imagesListLiveKpop = new ArrayList<>();
    private ArrayList<Images> imagesListLiveChristmas = new ArrayList<>();
    private ArrayList<Images> imagesListLiveAnimals = new ArrayList<>();
    private ArrayList<Images> imagesListLiveNature = new ArrayList<>();
    private ArrayList<Images> imagesListLiveModern = new ArrayList<>();
    private ArrayList<Images> imagesListLiveCute = new ArrayList<>();
    private ArrayList<Images> imagesListLiveSoccer = new ArrayList<>();
    private ArrayList<Images> imagesListLiveNeon = new ArrayList<>();
    private ArrayList<Images> imagesListLiveLove = new ArrayList<>();
    private ArrayList<Images> imagesListLiveCall = new ArrayList<>();
    private ArrayList<Images> imagesListLiveAnime = new ArrayList<>();
    private ArrayList<Images> imagesListLiveTrending = new ArrayList<>();
    LinearLayout adsView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neon_category);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        Drawable background = getResources().getDrawable(R.drawable.status_gradient);
        getWindow().setBackgroundDrawable(background);
        adsView0 = findViewById(R.id.adsView0);
        AdUtils.showNativeAd(KpopCategoryActivity.this, this.adsView0, false);
        recyclerView = findViewById(R.id.neon_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ArrayList<Images> categories = getIntent().getParcelableArrayListExtra("categories");
        if (categories != null) {
            for (Images image : categories) {
                String category = image.getUrl(); // Assuming you have a getCategory() method in the Images class

                switch (category) {

                    case "Images":

                        init();

                        break;
                    case "Trending":

                        loadTrendingItems();


                        break;
                    case "Animals":

                        loadChristmasItems();


                        break;
                    case "Christmas":

                        loadAnimalsItems();


                        break;
                    case "Nature":

                        loadNatureItems();

                        break;
                    case "Modern":

                        loadModernItems();

                        break;
                    case "CuteFunny":

                        loadCuteItems();

                        break;
                    case "Soccer":

                        loadSoccerItems();

                        break;
                    case "Neon":

                        loadNeonItems();


                        break;
                    case "Love":

                        loadLoveItems();

                        break;
                    case "CallOfDuty":

                        loadCallItems();


                        break;
                    case "Anime":

                        loadAnimeItems();


                        break;
                    case "LiveTrending":
                        loadLiveTrendingItems();

                        break;
                    case "LiveKpop":

                        loadLiveKpopItems();


                        break;
                    case "LiveChristmas":
                        loadLiveChristmasItems();

                        break;
                    case "LiveAnimals":

                        loadLiveAnimalsItems();

                        break;
                    case "LiveNature":

                        loadLiveNatureItems();


                        break;
                    case "LiveModern":

                        loadLiveModernItems();


                        break;
                    case "LiveCuteFunny":

                        loadLiveCuteItems();


                        break;
                    case "LiveSoccer":

                        loadLiveSoccerItems();

                        break;
                    case "LiveNeon":

                        loadLiveNeonItems();


                        break;
                    case "LiveLove":

                        loadLiveLoveItems();

                        break;
                    case "LiveCallOfDuty":

                        loadLiveCallItems();


                        break;
                    case "LiveAnime":

                        loadLiveAnimeItems();

                        break;

                }
            }
        }


    }


    private void loadLiveTrendingItems() {
        clearAll();
        Query query = databaseReference.child("LiveTrending");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveTrending.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveTrending);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveAnimeItems() {
        clearAll();
        Query query = databaseReference.child("LiveAnime");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveAnime.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveAnime);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveCallItems() {
        clearAll();
        Query query = databaseReference.child("LiveCallOfDuty");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveCall.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveCall);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveLoveItems() {
        clearAll();
        Query query = databaseReference.child("LiveLove");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveLove.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveLove);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveNeonItems() {
        clearAll();
        Query query = databaseReference.child("LiveNeon");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveNeon.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveNeon);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveSoccerItems() {
        clearAll();
        Query query = databaseReference.child("LiveSoccer");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveSoccer.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveSoccer);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveCuteItems() {
        clearAll();
        Query query = databaseReference.child("LiveCuteFunny");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveCute.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveCute);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveModernItems() {
        clearAll();
        Query query = databaseReference.child("LiveModern");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveModern.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveModern);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveNatureItems() {
        clearAll();
        Query query = databaseReference.child("LiveNature");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveNature.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveNature);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveAnimalsItems() {
        clearAll();
        Query query = databaseReference.child("LiveAnimals");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveAnimals.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveAnimals);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveChristmasItems() {
        clearAll();
        Query query = databaseReference.child("LiveChristmas");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveChristmas.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveChristmas);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLiveKpopItems() {
        clearAll();
        Query query = databaseReference.child("LiveKpop");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLiveKpop.add(images);
                }
                imagesVideoAdapter = new ImagesVideoAdapter(getApplicationContext(), imagesListLiveKpop);
                recyclerView.setAdapter(imagesVideoAdapter);
                imagesVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadAnimeItems() {
        clearAll();
        Query query = databaseReference.child("Anime");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListAnime.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListAnime);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadCallItems() {
        clearAll();
        Query query = databaseReference.child("CallOfDuty");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListCall.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListCall);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLoveItems() {
        clearAll();
        Query query = databaseReference.child("Love");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListLove.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListLove);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadNeonItems() {
        clearAll();
        Query query = databaseReference.child("Neon");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListNeon.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListNeon);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadSoccerItems() {
        clearAll();
        Query query = databaseReference.child("Soccer");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListSoccer.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListSoccer);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadCuteItems() {
        clearAll();
        Query query = databaseReference.child("CuteFunny");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListCute.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListCute);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadModernItems() {
        clearAll();
        Query query = databaseReference.child("Modern");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListModern.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListModern);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadNatureItems() {
        clearAll();
        Query query = databaseReference.child("Nature");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListNature.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListNature);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadAnimalsItems() {
        clearAll();
        Query query = databaseReference.child("Animals");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListAnimals.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListAnimals);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadChristmasItems() {
        clearAll();
        Query query = databaseReference.child("Christmas");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListChristmas.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListChristmas);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadTrendingItems() {
        clearAll();
        Query query = databaseReference.child("Trending");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListImages.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListImages);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
                precacheRecyclerViewItems(imagesListImages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void init() {
        clearAll();
        Query query = databaseReference.child("Images");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Images images = new Images();
                    images.setUrl(snapshot.child("url").getValue().toString());
                    imagesListTrending.add(images);
                }
                imagesAdapter = new ImagesAdapter(getApplicationContext(), imagesListTrending/*,KpopCategoryActivity.this*/);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearAll() {
        imagesListImages.clear();
        imagesListTrending.clear();
        imagesListChristmas.clear();
        imagesListAnimals.clear();
        imagesListNature.clear();
        imagesListModern.clear();
        imagesListNeon.clear();
        imagesListCute.clear();
        imagesListSoccer.clear();
        imagesListLove.clear();
        imagesListCall.clear();
        imagesListAnime.clear();
        imagesListLiveKpop.clear();
        imagesListLiveChristmas.clear();
        imagesListLiveAnimals.clear();
        imagesListLiveNature.clear();
        imagesListLiveModern.clear();
        imagesListLiveCute.clear();
        imagesListLiveNeon.clear();
        imagesListLiveSoccer.clear();
        imagesListLiveLove.clear();
        imagesListLiveCall.clear();
        imagesListLiveAnime.clear();
        imagesListLiveTrending.clear();
        if (imagesAdapter != null) {
            imagesAdapter.notifyDataSetChanged();
        }
        if (imagesVideoAdapter != null) {
            imagesVideoAdapter.notifyDataSetChanged();
        }
    }

    private void precacheRecyclerViewItems(ArrayList<Images> items) {
        for (Images item : items) {
            String imageUrl = item.getUrl();
            Picasso.get().load(imageUrl).fetch();
        }
    }
}
