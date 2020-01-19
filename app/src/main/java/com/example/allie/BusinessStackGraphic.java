package com.example.allie;

import android.content.Context;
import android.graphics.Canvas;

import com.example.allie.MoneyStackGraphic;

public class BusinessStackGraphic extends MoneyStackGraphic {
    BusinessStackGraphic(int height, Context context) {
        int verticalSpacing = 400;
        int size = 400;
        int x = (1316 - (size - 40)) / 2;
        int y = 1500;

        generateRects(height, verticalSpacing, size, x, y);
        setImages(R.drawable.skyscraper, R.drawable.shop_small, context);
    }

}
