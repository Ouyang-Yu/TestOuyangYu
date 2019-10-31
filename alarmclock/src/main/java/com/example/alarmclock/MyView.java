package com.example.alarmclock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.view.View;

/**
 * @author Ouyang Yu
 * @date 2019/10/15 9:07
 */
public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint =new Paint();
        paint.setColor(0xffff6600);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(26);//默认单位sp
        paint.setStyle(Paint.Style.STROKE);//设置填充方式:描边

        Path path=new Path();
        path.addCircle(150, 100, 50, Path.Direction.CW);
        //圆心,半径,方向(CW:Clockwise)

        canvas.drawPath(path, paint);
        canvas.drawTextOnPath("I love you", path, 0, 0, paint);
        //两种显示路径
//
//
//        canvas.drawRect(10,10,280,150,paint);
//
//        canvas.drawText("高锦良", 170, 160, paint);//起始的x与y坐标

//        Paint paint =new Paint();
//        String path = Environment.getExternalStorageDirectory() + "/snail.png";
//        Bitmap bitmap = BitmapFactory.de codeFile(path);
//        //根据已有图片绘制bitmap
//
//        canvas.drawBitmap(bitmap, 0, 0, paint);//显示bitmap
//
//        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 23,89, 150, 168);
//        //截取成为新的bitmap
//        canvas.drawBitmap(bitmap1, 270, 50, paint);

    }
}

