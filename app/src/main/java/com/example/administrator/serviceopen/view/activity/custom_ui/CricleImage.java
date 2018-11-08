package com.example.administrator.serviceopen.view.activity.custom_ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.administrator.serviceopen.model.interfaces.RotateListener;

/**
 * Created by ${yx} on 2018/11/8.
 */
@SuppressLint("AppCompatCustomView")
public class CricleImage extends ImageView {
    public CricleImage(Context context) {
        super(context);
    }

    public CricleImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CricleImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int mRaduis = 0;

    private float scale = 0.0f;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 测量 控件 所占用的边长 //长宽中取小的
        int size = Math.min(getMeasuredHeight(), getMeasuredWidth());

        mRaduis = size / 2;

        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 然后就是画图 .
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        Bitmap bitmap = drawableToBitmap(getDrawable());

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        scale = (mRaduis * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());

        Matrix matrix = new Matrix();

        matrix.setScale(scale, scale);

        bitmapShader.setLocalMatrix(matrix);

        paint.setShader(bitmapShader);

        canvas.drawCircle(mRaduis, mRaduis, mRaduis, paint);


    }


    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private RotateListener mRotateListener;

    // 设置对外的方法 . 设置 回调
    public void setRotateListener(RotateListener mRotateListener) {
        this.mRotateListener = mRotateListener;
    }

}
