package com.curtesmalteser.radarchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by António "Curtes Malteser" Bastião on 03/03/2018.
 */

public class RadarChart extends View {

    private int width, height = 0;
    int radius = 0;
    private double[] numbers = {1, 2, 3, 4, 5, 6, 7};
    private Rect rect = new Rect();
    private Paint paint = new Paint();
    private Paint paintArea = new Paint();
    private boolean isInit;

    ArrayList<Integer> mRrange = new ArrayList<>();

    public RadarChart(Context context) {
        super(context);
    }

    public RadarChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initChart() {
        height = getHeight();
        width = getWidth();
        radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                getResources().getDisplayMetrics());
        paint = new Paint();
        isInit = true;
        paint.setColor(Color.BLACK);
        paintArea.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit)
            initChart();
        setData();
        drawCircle(canvas);
        drawLines(canvas);
        drawPolygon(canvas);

    }

    private void drawCircle(Canvas canvas) {

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPaint(paint);

        for (float i = 1; i > 0; i -= 0.1) {
            float circle = radius * i;
            canvas.drawCircle(width / 2, height / 2, circle, paint);

        }
    }

    private void drawLines(Canvas canvas) {
        for (double number : numbers) {
            double angle = (Math.PI * number / ((double) numbers.length / 2));
            int x = (int) (width / 2 + Math.cos(angle) * (radius + 20) - rect.width() / 2);
            int y = (int) (height / 2 + Math.sin(angle) * (radius + 20) + rect.height() / 2);

            canvas.drawLine(width / 2,
                    height / 2,
                    x,
                    y,
                    paint);
        }
    }

    private void drawPolygon(Canvas canvas) {

        boolean fill = true;

        if (fill)
            paintArea.setStyle(Paint.Style.FILL);
        else
            paintArea.setStyle(Paint.Style.STROKE);

        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();

        for (int i = 0; i < mRrange.size(); i++) {
            x.add((float) (width / 2 + Math.cos(Math.PI * (i + 1) / ((double) numbers.length / 2)) * mRrange.get(i) - rect.width() / 2));
            y.add((float) (height / 2 + Math.sin(Math.PI * (i + 1) / ((double) numbers.length / 2)) * mRrange.get(i) + rect.height() / 2));
        }

        Path area = new Path();
        area.reset(); // only needed when reusing this path for a new build
        area.moveTo(x.get(0), y.get(0)); // used for first point
        area.lineTo(x.get(1), y.get(1));
        area.lineTo(x.get(2), y.get(2));
        area.lineTo(x.get(3), y.get(3));
        area.lineTo(x.get(4), y.get(4));
        area.lineTo(x.get(5), y.get(5));
        area.lineTo(x.get(6), y.get(6));
        area.lineTo(x.get(0), y.get(0)); // there is a setLastPoint action but i found it not to work as expected

        canvas.drawPath(area, paintArea);
    }

    private void setData() {
        ArrayList<Integer> range = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            Random random = new Random();
            int n = random.nextInt(100 - 50) + 50;
            range.add(n);
        }
        mRrange = range;
    }
}