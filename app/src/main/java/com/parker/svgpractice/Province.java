package com.parker.svgpractice;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

public class Province {
    public Path path;
    public int drawColor = 0xFF000000;

    public Province(Path path) {
        this.path = path;
    }

    public void drawableProvince(Paint paint, Canvas canvas, boolean isSelected) {
        if (isSelected) {
            //当前被选择的省
            //画阴影
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);
            paint.setShadowLayer(8, 0, 0, 0xffffff);
            canvas.drawPath(path, paint);

            //画填充部分
            paint.clearShadowLayer();
            paint.setColor(Color.RED);
            canvas.drawPath(path, paint);
        } else {
            //画填充部分
            paint.setColor(drawColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(1);
            canvas.drawPath(path, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(0xFFD0E8F4);
            canvas.drawPath(path, paint);
        }
    }

    //判断某一点是否包含在该省内
    public boolean isContain(int x, int y) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        Region region = new Region();
        region.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        return region.contains(x, y);
    }
}
