package com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class GradientProgressBar extends ProgressBar {
    private Paint gradientPaint;

    public GradientProgressBar(Context context) {
        super(context);
        init();
    }

    public GradientProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gradientPaint = new Paint();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        float progress = getProgress() / (float) getMax();
        int width = getWidth();
        int height = getHeight();
        int startColor = Color.parseColor("#D653EB");
        int endColor = Color.parseColor("#2B56EF");
        Shader shader = new LinearGradient(0, 0, width * progress, height, startColor, endColor, Shader.TileMode.CLAMP);
        gradientPaint.setShader(shader);

        // Draw the progress bar with the gradient
        canvas.drawRect(0, 0, width * progress, height, gradientPaint);
    }
}