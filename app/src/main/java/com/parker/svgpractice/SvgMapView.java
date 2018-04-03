package com.parker.svgpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by ZX on 2017/8/23.
 */

public class SvgMapView extends View {

    private int[] colorArray = new int[]{0xFF239BD7, 0xFF30A9E5, 0xFF80CBF1, 0xFFB0D7F8};

    private Context context;
    private Paint paint;

    //省份数组
    private List<Province> provinceList;
    //选中的省份
    private Province selectItem;
    //缩放比例
    private float scale = 0f;
    private float mapWidth = 773.0f, mapHeight = 568.0f;

    public SvgMapView(Context context) {
        this(context, null);
    }

    public SvgMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SvgMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        provinceList = new ArrayList<>();
        loadThread.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        scale = Math.min(width / mapWidth, height / mapHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (provinceList != null) {
            canvas.scale(scale, scale);
            for (Province item : provinceList) {
                if (item != selectItem) {
                    item.draw(canvas, paint, false);
                }
            }
            if (selectItem != null) {
                selectItem.draw(canvas, paint, true);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (provinceList != null) {
            Province Province = null;
            for (Province item : provinceList) {
                if (item.isContain((int) (event.getX() / scale), (int) (event.getY() / scale))) {
                    Province = item;
                    break;
                }
            }
            if (Province != null) {
                selectItem = Province;
                postInvalidate();
            }
        }
        return true;
    }

    private Thread loadThread = new Thread() {
        @Override
        public void run() {
            InputStream inputStream = context.getResources().openRawResource(R.raw.china_svg);
            try {
                //取得 DocumentBuilderFactory 实例
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                //从 factory 获取 DocumentBuilder 实例
                DocumentBuilder builder = factory.newDocumentBuilder();
                //解析输入流,得到 Document 实例
                Document doc = builder.parse(inputStream);
                Element rootElement = doc.getDocumentElement();
                NodeList items = rootElement.getElementsByTagName("path");
                for (int i = 0; i < items.getLength(); i++) {
                    Element element = (Element) items.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(pathData);
                    provinceList.add(new Province(path));
                }
                handler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (provinceList == null) {
                return;
            }
            for (int i = 0; i < provinceList.size(); i++) {
                int color;
                int flag = i % 4;
                switch (flag) {
                    case 1:
                        color = colorArray[0];
                        break;
                    case 2:
                        color = colorArray[1];
                        break;
                    case 3:
                        color = colorArray[2];
                        break;
                    default:
                        color = colorArray[3];
                        break;
                }
                provinceList.get(i).setDrawColor(color);
            }
            postInvalidate();
        }
    };

}
