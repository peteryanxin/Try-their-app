package com.example.administrator.serviceopen.view.activity.custom_ui;

import android.animation.ObjectAnimator;
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
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.example.administrator.serviceopen.model.interfaces.RotateListener;

/**
 * Created by ${yx} on 2018/11/8.
 */
@SuppressLint("AppCompatCustomView")
public class CricleImage extends ImageView {

    public static final int STATE_PLAYING = 1;//正在播放
    public static final int STATE_PAUSE = 2;//暂停
    public static final int STATE_STOP = 3;//停止
    public int state;
    private float angle;//记录RotateAnimation中受插值器数值影响的角度
    private float angle2;//主要用来记录暂停时停留的角度，即View初始旋转角度
    private int viewWidth;
    private int viewHeight;
    private MusicAnim musicAnim;

    public CricleImage(Context context) {
        super(context);

        init();
    }

    private void init() {

        state = STATE_STOP;
        angle = 0;
        angle2 = 0;
        viewWidth = 0;
        viewHeight = 0;
    }

    public CricleImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CricleImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
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

        canvas.rotate(angle2, mRaduis, mRaduis);
        canvas.drawCircle(mRaduis, mRaduis, mRaduis, paint);

//        canvas.ro

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


    public class MusicAnim extends RotateAnimation {
        public MusicAnim(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
            super(fromDegrees, toDegrees, pivotX, pivotY);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            angle = interpolatedTime * 360;
        }
    }


    public void stopMusic() {
        angle2 = 0;
        clearAnimation();
        state = STATE_STOP;
        invalidate();
    }


    public void playMusic() {
        if (state == STATE_PLAYING) {
            angle2 = (angle2 + angle) % 360;//可以取余也可以不取，看实际的需求
            musicAnim.cancel();
            state = STATE_PAUSE;
            invalidate();
        } else {
            musicAnim = new MusicAnim(0, 360, viewWidth / 2, viewHeight / 2);
            musicAnim.setDuration(3000);
            musicAnim.setInterpolator(new LinearInterpolator());//动画时间线性渐变
            musicAnim.setRepeatCount(ObjectAnimator.INFINITE);
            startAnimation(musicAnim);
            state = STATE_PLAYING;
        }
    }


    private RotateListener mRotateListener;

    // 设置对外的方法 . 设置 回调
    public void setRotateListener(RotateListener mRotateListener) {
        this.mRotateListener = mRotateListener;
    }

}
