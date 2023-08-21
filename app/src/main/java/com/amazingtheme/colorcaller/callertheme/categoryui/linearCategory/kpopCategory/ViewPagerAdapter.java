package com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory;

import static com.amazingtheme.colorcaller.callertheme.dialer.RingtoneHelper.stopRingtone;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.PagerAdapter;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.callertheme.categoryui.Images;
import com.amazingtheme.colorcaller.callertheme.categoryui.ImagesAdapter;
import com.amazingtheme.colorcaller.callertheme.categoryui.Theme_Activity_Calling_Theme_Preview;
import com.amazingtheme.colorcaller.callertheme.dialer.helpers.CallListHelper;
import com.amazingtheme.colorcaller.callertheme.dialer.helpers.CallManager;
import com.amazingtheme.colorcaller.utils.SharedPreferenceManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private Context context;
    private List<Images> lstImages;
    private LayoutInflater layoutInflater;
    GradientProgressBar progressBar;
    Dialog dialog;
    private String downloadedImageUrl;
    private SharedPreferenceManager preferenceManager;
    LinearLayout leftArrowContainer, rightArrowContainer;


    public ViewPagerAdapter(Context context, List<Images> lstImages) {
        this.context = context;
        this.lstImages = lstImages;
        this.layoutInflater = LayoutInflater.from(context);
        preferenceManager = new SharedPreferenceManager(context);
    }

    @Override
    public int getCount() {
        return lstImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_page, container, false);
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView tv_apply = view.findViewById(R.id.tv_apply);
        ImageView receive = view.findViewById(R.id.receive);
        ImageView end = view.findViewById(R.id.end);
        leftArrowContainer = view.findViewById(R.id.left_arrow_container);
        rightArrowContainer = view.findViewById(R.id.right_arrow_container);
// Create the animations for the left arrows
        ObjectAnimator leftArrow1Animator = ObjectAnimator.ofFloat(leftArrowContainer.getChildAt(0), "translationX", -40f);
        ObjectAnimator leftArrow2Animator = ObjectAnimator.ofFloat(leftArrowContainer.getChildAt(1), "translationX", -40f);


// Create the animations for the right arrows
        ObjectAnimator rightArrow1Animator = ObjectAnimator.ofFloat(rightArrowContainer.getChildAt(0), "translationX", 40f);
        ObjectAnimator rightArrow2Animator = ObjectAnimator.ofFloat(rightArrowContainer.getChildAt(1), "translationX", 40f);



        AnimatorSet leftArrowAnimatorSet = new AnimatorSet();
        leftArrowAnimatorSet.playSequentially(
                leftArrow1Animator,
                leftArrow2Animator

        );
        leftArrowAnimatorSet.setDuration(500);
        leftArrowAnimatorSet.setStartDelay(200); // Adjust the start delay as needed

// Create an AnimatorSet for the right arrows
        AnimatorSet rightArrowAnimatorSet = new AnimatorSet();
        rightArrowAnimatorSet.playSequentially(
                rightArrow1Animator,
                rightArrow2Animator

        );
        rightArrowAnimatorSet.setDuration(500);
        rightArrowAnimatorSet.setStartDelay(200);


        receive.setOnTouchListener(new SwipeTouchListener(leftArrowContainer, rightArrowContainer, receive));
        end.setOnTouchListener(new SwipeTouchListener(leftArrowContainer, rightArrowContainer, end));


        String imageUrl = lstImages.get(position).getUrl();

        Picasso.get().load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        saveImageToSharedPreferences(bitmap);
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        // Handle bitmap load failure
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        // Handle bitmap loading preparation
                    }
                });

        tv_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String imageUrl = lstImages.get(position).getUrl();
                downloadDialog(imageUrl);
            }
        });

        container.addView(view);
        return view;
    }


    private void downloadDialog(final String imageUrl) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.custom_downloading_dialog);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(17170445);
        TextView downloading = dialog.findViewById(R.id.txt3);
        downloading.setText("Downloading...");
//        progressBar.setVisibility(View.VISIBLE);
        progressBar = dialog.findViewById(R.id.my_progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Perform the image download here

                // Simulate download progress
                final AtomicInteger progress = new AtomicInteger(0);
                while (progress.get() <= 100) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar on the UI thread
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress.get());
                            progressBar.invalidate();
                        }
                    });

                    // Increment the progress
                    progress.addAndGet(10);
                }
                downloadedImageUrl = imageUrl;
                // Store the image URL in SharedPreferences
                preferenceManager.setImageUrl(imageUrl);

                // Update the UI after the download is complete
                progressBar.post(new Runnable() {
                    @Override
                    public void run() {


                        progressBar.setVisibility(View.GONE); // Hide the progress bar

                        // Dismiss the dialog
                        dialog.dismiss();
                        Toast.makeText(context, "Downloaded Successfully", Toast.LENGTH_SHORT).show();

                        // Start the next activity and pass the image URL as an extra
                        Intent intent = new Intent(context, Theme_Activity_Calling_Theme_Preview.class);
                        intent.putExtra("image_url", imageUrl);
                        context.startActivity(intent);
                    }
                });
            }
        }).start();
        dialog.show();
    }

    private void saveImageToSharedPreferences(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        preferenceManager.setImageUrl(encodedImage);

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    class SwipeTouchListener implements View.OnTouchListener {
        private LinearLayout leftArrowContainer;
        private LinearLayout rightArrowContainer;
        private ImageView leftArrow1;
        private ImageView leftArrow2;

        private ImageView rightArrow1;
        private ImageView rightArrow2;
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        private boolean isSwiping = false;
        private float initialX;
        private ImageView buttonView;
        private ObjectAnimator fadeOutAnimator;
        private ObjectAnimator fadeInAnimator;

        SwipeTouchListener(LinearLayout leftArrowContainer, LinearLayout rightArrowContainer, ImageView buttonView) {
            this.leftArrowContainer = leftArrowContainer;
            this.rightArrowContainer = rightArrowContainer;
            this.buttonView = buttonView;

            leftArrow1 = leftArrowContainer.findViewById(R.id.left_arrow_1);
            leftArrow2 = leftArrowContainer.findViewById(R.id.left_arrow_2);
            rightArrow1 = rightArrowContainer.findViewById(R.id.right_arrow_1);
            rightArrow2 = rightArrowContainer.findViewById(R.id.right_arrow_2);

            fadeOutAnimator = ObjectAnimator.ofFloat(buttonView, "alpha", 1f, 0.5f);
            fadeOutAnimator.setDuration(200);
            fadeOutAnimator.setInterpolator(new LinearInterpolator());

            fadeInAnimator = ObjectAnimator.ofFloat(buttonView, "alpha", 0.5f, 1f);
            fadeInAnimator.setDuration(200);
            fadeInAnimator.setInterpolator(new LinearInterpolator());
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isSwiping = false;
                    showArrows();
                    initialX = event.getX();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    if (!isSwiping && Math.abs(event.getX() - initialX) > SWIPE_THRESHOLD) {
                        isSwiping = true;
                    }
                    if (isSwiping) {
                        buttonView.setTranslationX(event.getX() - initialX);
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    hideArrows();
                    if (isSwiping) {
                        float deltaX = event.getX() - initialX;
                        float absDeltaX = Math.abs(deltaX);
                        float velocityX = Math.abs(event.getX() - initialX) / (float) event.getEventTime();
                        if (absDeltaX > SWIPE_THRESHOLD && velocityX > SWIPE_VELOCITY_THRESHOLD) {
                            if (deltaX > 0) {

                            } else {

                            }
                        }
                        buttonView.setTranslationX(0f);
                    }
                    return true;
            }
            return false;
        }


        private void showArrows() {
            leftArrow1.setVisibility(View.VISIBLE);
            leftArrow1.setTranslationX(-50);
            leftArrow1.setAlpha(0f);
            leftArrow1.animate().translationXBy(50).alpha(1f).setDuration(200).start();

            leftArrow2.setVisibility(View.VISIBLE);
            leftArrow2.setTranslationX(-50);
            leftArrow2.setAlpha(0f);
            leftArrow2.animate().translationXBy(50).alpha(1f).setStartDelay(100).setDuration(200).start();



            rightArrow1.setVisibility(View.VISIBLE);
            rightArrow1.setTranslationX(50);
            rightArrow1.setAlpha(0f);
            rightArrow1.animate().translationXBy(-50).alpha(1f).setDuration(200).start();

            rightArrow2.setVisibility(View.VISIBLE);
            rightArrow2.setTranslationX(50);
            rightArrow2.setAlpha(0f);
            rightArrow2.animate().translationXBy(-50).alpha(1f).setStartDelay(100).setDuration(200).start();


        }


        private void hideArrows() {
            leftArrow1.animate().translationXBy(50).alpha(0f).setDuration(200).start();
            leftArrow2.animate().translationXBy(50).alpha(0f).setDuration(200).start();
            rightArrow1.animate().translationXBy(-50).alpha(0f).setDuration(200).start();
            rightArrow2.animate().translationXBy(-50).alpha(0f).setDuration(200).start();
        }
    }
}