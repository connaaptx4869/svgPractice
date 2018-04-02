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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SvgMapView extends View {
    private ArrayList<Province> itemLists = new ArrayList<>();
    private Context mContext;
    private Paint mPaint;
    private Province mSelectedItem;

    public SvgMapView(Context context) {
        super(context);
        init(context);
    }


    public SvgMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SvgMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        parseSvgThread.start();
    }


    //开启一个线程，用于解析xml文件
    private Thread parseSvgThread = new Thread(new Runnable() {
        @Override
        public void run() {
            InputStream inputStream = mContext.getResources().openRawResource(R.raw.china_map);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
                Document doc = builder.parse(inputStream);
                Element rootElements = doc.getDocumentElement();
                //解析path节点
                NodeList items = rootElements.getElementsByTagName("path");
                for (int i = 0; i < items.getLength(); i++) {
                    Element element = (Element) items.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(pathData);
                    //将解析的每一条path创建一个javabean，然后放到容器中
                    Province province = new Province(path);
                    itemLists.add(province);
                }
                //解析完成之后，发送handler 通知解析完成并设置颜色
                mHandler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    });

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (itemLists != null && itemLists.size() > 0) {
                int size = itemLists.size();
                for (int i = 0; i < size; i++) {
                    //简单设置下颜色itemLists.get(i).setDrawColor(colors[i%colors.length]);
                }
                //更新绘制
                postInvalidate();
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1:画选择的
        //2画没别选择的
        if (itemLists != null && itemLists.size() > 0) {
            canvas.save();
//            canvas.scale(scale, scale);
            if (mSelectedItem != null) {
                mSelectedItem.drawableProvince(mPaint, canvas, true);
            }
            for (int i = 0; i < itemLists.size(); i++) {
                if (itemLists.get(i) != mSelectedItem) {
                    itemLists.get(i).drawableProvince(mPaint, canvas, false);
                }
            }
            canvas.restore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Province province : itemLists) {
                    if (province.isContain((int) event.getX(), (int) event.getY())) {
                        if (mSelectedItem != province) {
                            mSelectedItem = province;
                            postInvalidate();
                        }
                        break;
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
