package nianhuibao.com.androidprojects.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BitmapUtils {
    public static String TEMP_FOLDER = "identify/temp";
    private static final String LOG_TAG = BitmapUtils.class.getSimpleName();

    public enum ZoomType {
        FIT_XY, INSIDE, CROP
    }

    public enum GetPointType {
        /**
         * 随机取点
         */
        Radom,
        /**
         * x方向平均取点
         */
        Average_X,
        /**
         * y方向平均取点
         */
        Average_Y
    }

    /**
     * 从资源文件获取bitmap
     */
    public static Bitmap getBitmap(Context context, int resId) {
        return getBitmap(context, resId, null);
    }

    /**
     * 从资源文件获取bitmap
     */
    public static Bitmap getBitmap(Context context, int resId, Options option) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId, option);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 获取空图片,如果bitmap创建失败返回null
     */
    public static Bitmap getEmptyBitmap(int width, int height) {
        return getEmptyBitmap(width, height, Color.BLACK);
    }

    /**
     * 获取空图片,如果bitmap创建失败返回null
     */
    public static Bitmap getEmptyBitmap(int width, int height, int color) {
        return getEmptyBitmap(width, height, color, Config.ARGB_8888);
    }

    /**
     * 获取空图片,如果bitmap创建失败返回null
     */
    public static Bitmap getEmptyBitmap(int width, int height, int color, Config config) {
        Bitmap bitmap = null;
        if (width * height > 0) {
            try {
                bitmap = Bitmap.createBitmap(width, height, config);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        if (bitmap != null) {
            // 创建canvas `   
            Canvas canvas = new Canvas(bitmap);
            // 绘制颜色
            canvas.drawColor(color);
        }
        return bitmap;
    }

    /**
     * 获取指定尺寸的bitmap
     */
    public static Bitmap getBitmap(Bitmap bitmap, int w, int h) {
        Bitmap result = null;
        Matrix matrix = new Matrix();
        float scale = Math.min(((float) w / bitmap.getWidth()), ((float) h / bitmap.getHeight()));
        matrix.postScale(scale, scale);
        matrix.postTranslate((w - bitmap.getWidth() * scale) / 2, (h - bitmap.getHeight() * scale) / 2);
        try {
            result = getEmptyBitmap(w, h, Color.TRANSPARENT, Config.ARGB_8888);
            // 创建canvas `   
            Canvas canvas = new Canvas(result);
            // 根据变换矩阵绘制
            canvas.drawBitmap(bitmap, matrix, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取圆形
     */
    public static Bitmap getCircle(int width, int height, int padding, int color, Bitmap cover, float scale) {
        // 创建bitmap
        Bitmap bitmap = getEmptyBitmap(width, height);
        if (bitmap != null) {
            // 创建canvas
            Canvas canvas = new Canvas(bitmap);
            // 设置画笔
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            // 画背景
            paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
            canvas.drawRect(0, 0, width, height, paint);
            // 画圆
            paint.setColor(color);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
            canvas.drawCircle(width / 2, height / 2, Math.min(width, height) / 2 - padding, paint);
            // 绘制覆盖物
            if (cover != null) {
                float viewWidth = (width - padding * 2) * scale;
                float viewHeight = (height - padding * 2) * scale;
                // 对bitmap进行操作
                Matrix matrix = new Matrix();
                // 缩放
                float scaleX = viewWidth / cover.getWidth();
                float scaleY = viewHeight / cover.getHeight();
                float scaleCover = Math.max(scaleX, scaleY);
                matrix.postScale(scaleCover, scaleCover);
                // 居中
                matrix.postTranslate((width - cover.getWidth() * scaleCover) / 2, (height - cover.getHeight() * scaleCover) / 2);
                canvas.drawBitmap(cover, matrix, paint);
            }
        }
        return bitmap;
    }

    /**
     * 获取圆形
     */
    public static Bitmap getCircle(int width, int height, int padding, int color) {
        return getCircle(width, height, padding, color, null, 1.0f);
    }

    /**
     * 为图片添加倒影
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        return createReflectionImageWithOrigin(bitmap, 10, Color.TRANSPARENT);
    }

    /**
     * 为图片添加倒影
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap, int gapSize, int gapColor) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);
        Bitmap bitmapWithReflection = getEmptyBitmap(width, height * 3 / 2);

        if (bitmapWithReflection != null) {
            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(bitmap, 0, 0, null);
            Paint gapPaint = new Paint();
            gapPaint.setColor(gapColor);
            canvas.drawRect(0, height, width, height + gapSize, gapPaint);

            canvas.drawBitmap(reflectionImage, 0, height + gapSize, null);

            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + gapSize, 0x70ffffff, 0x00ffffff,
                    Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + gapSize, paint);
        }
        return bitmapWithReflection;
    }

    /**
     * 根据尺寸获取压缩图片
     */
    public static final Bitmap getBitmap(Context context, Bitmap.CompressFormat format, InputStream inputStream, int width, int height) {
        byte[] data = inputToByte(inputStream);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;
        int sizeWidth = Math.round((float) bitmapWidth / (float) width);
        int sizeHeight = Math.round((float) bitmapHeight / (float) height);
        options.inSampleSize = Math.max(sizeHeight, sizeWidth);
        if (options.inSampleSize > 32) {
            options.inSampleSize = 64;
        } else if (options.inSampleSize > 16) {
            options.inSampleSize = 32;
        } else if (options.inSampleSize > 8) {
            options.inSampleSize = 16;
        } else if (options.inSampleSize > 4) {
            options.inSampleSize = 8;
        } else if (options.inSampleSize > 2) {
            options.inSampleSize = 4;
        } else if (options.inSampleSize > 1) {
            options.inSampleSize = 2;
        }
        options.inJustDecodeBounds = false;
        try {
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return getBitmap(bitmap, width, height);
    }

    /**
     * 获取图片上面某一矩形区域的平均颜色
     */
    public static final int getAverageColor(Bitmap bitmap, Rect rect, GetPointType type, long count) {
        // 获取图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 确保rect在图片范围之内
        rect.left = rect.left < 0 ? 0 : rect.left > width ? width : rect.left;
        rect.top = rect.top < 0 ? 0 : rect.top > height ? height : rect.top;
        rect.right = rect.right < 0 ? 0 : rect.right > width ? width : rect.right;
        rect.bottom = rect.bottom < 0 ? 0 : rect.bottom > height ? height : rect.bottom;
        // 确保取样点不大于总点数
        count = count < width * height ? count : width * height;
        // 生成采样点列表
        List<Point> points = new ArrayList<Point>();
        switch (type) {
            case Radom:
                Random random = new Random();
                for (int i = 0; i < count; i++) {
                    points.add(new Point(random.nextInt(width), random.nextInt(height)));
                }
                break;
            case Average_X:
                int distanceX = (int) (width * height / count);
                int x = 0;
                for (int y = 0; y < height; y++) {
                    for (x = distanceX % width; x < width; x += distanceX) {
                        points.add(new Point(x, y));
                    }
                }
                break;
            case Average_Y:
                int distanceY = (int) (width * height / count);
                int j = 0;
                for (int i = 0; i < width; i++) {
                    for (j = distanceY % height; j < height; j += distanceY) {
                        points.add(new Point(i, j));
                    }
                }
                break;
            default:
                break;
        }
        return getAverageColor(bitmap, points);
    }

    /**
     * 获取图片上面某些点的平均颜色
     */
    private static int getAverageColor(Bitmap bitmap, List<Point> points) {
        // 如果points的长度为1，则返回这个点的颜色
        if (points.size() == 1) {
            return bitmap.getPixel(points.get(0).x, points.get(0).y);
        }
        // 如果points的长度超过1，则继续分
        int middle = points.size() / 2;
        int leftColor = getAverageColor(bitmap, new ArrayList<Point>(points.subList(0, middle)));
        int rightColor = getAverageColor(bitmap, new ArrayList<Point>(points.subList(middle, points.size())));
        // 计算颜色的平均值
        int red = (Color.red(leftColor) + Color.red(rightColor)) / 2;
        int green = (Color.green(leftColor) + Color.green(rightColor)) / 2;
        int blue = (Color.blue(leftColor) + Color.blue(rightColor)) / 2;
        // 返回平均颜色
        return Color.rgb(red, green, blue);
    }

    /**
     * 获取反色
     */
    public static int getInverseColor(int color) {
        int red = 0xff - Color.red(color);
        int green = 0xff - Color.green(color);
        int blue = 0xff - Color.blue(color);
        return Color.rgb(red, green, blue);
    }

    /**
     * @param bitmap   The picture intent to add round corner
     * @param radius   The radius of the corner
     * @param location Which corner to add round corner:
     *                 1-left_top;2-left_bottom;3-right_top;4-right_bottom
     * @return The picture with round corner
     * @author huyongsheng
     */
    public static Bitmap getRoundCorner(Bitmap bitmap, int radius, int... location) {

        if (bitmap == null) {
            return bitmap;
        }

        final int LEFT_TOP = 1;
        final int LEFT_BOTTOM = 2;
        final int RIGHT_TOP = 3;
        final int RIGHT_BOTTOM = 4;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect completeRect = new Rect(0, 0, width, height);
        Rect leftTopRect = new Rect(0, 0, radius, radius);
        Rect leftBottomRect = new Rect(0, height - radius, radius, height);
        Rect rightTopRect = new Rect(width - radius, 0, width, radius);
        Rect rightBottomRect = new Rect(width - radius, height - radius, width, height);
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        canvas.drawRect(completeRect, paint);
        if (location != null) {
            for (int corner : location) {
                switch (corner) {
                    case LEFT_TOP:
                        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
                        canvas.drawRect(leftTopRect, paint);
                        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
                        canvas.drawCircle(radius, radius, radius, paint);
                        break;
                    case LEFT_BOTTOM:
                        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
                        canvas.drawRect(leftBottomRect, paint);
                        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
                        canvas.drawCircle(radius, height - radius, radius, paint);
                        break;
                    case RIGHT_TOP:
                        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
                        canvas.drawRect(rightTopRect, paint);
                        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
                        canvas.drawCircle(width - radius, radius, radius, paint);
                        break;
                    case RIGHT_BOTTOM:
                        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
                        canvas.drawRect(rightBottomRect, paint);
                        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
                        canvas.drawCircle(width - radius, height - radius, radius, paint);
                        break;
                    default:
                        break;
                }
            }
        }
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return output;
    }

    public static Bitmap getBridge(Bitmap bridge, int width, int padding, int strokeHeight, int backgroundColor, int strokeColor) {
        int height = strokeHeight + padding * 2 + bridge.getHeight();
        Bitmap bitmap = BitmapUtils.getEmptyBitmap(width, height, Color.TRANSPARENT);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 清空画布
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
        // 绘制stroke
        paint.setColor(strokeColor);
        canvas.drawRect(0, strokeHeight + 2 * padding, width, height, paint);
        canvas.drawCircle(width / 2, strokeHeight + bridge.getHeight() / 2, bridge.getHeight() / 2, paint);
        return bitmap;

    }

    /**
     * 获取图片的旋转数据
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     */
    public static Bitmap roteImage(int angle, Bitmap bitmap) {
        //旋转图片
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 获取文件中的图片
     * 图片自动缩放、旋转，如果OOM返回NULL
     */
    public static Bitmap getBitmapFromFile(String filePath, int width, int height) {
        int degree = readPictureDegree(filePath);
        if (degree == 90 || degree == 270) {
            int temp = width;
            width = height;
            height = temp;
        }

        // 读取本地图片尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 设置为true，options依然对应此图片，但解码器不会为此图片分配内存
        options.inJustDecodeBounds = true;
        // 获取到图片的宽高
        BitmapFactory.decodeFile(filePath, options);
        // 图片的原始宽高
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        // 当图片长宽大于屏幕长宽时
        int inSampleSize = 1;
        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        // 在内存中分配
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
            bitmap = roteImage(degree, bitmap);
            return bitmap;
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
        return null;
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 将bitmap存成文件
     *
     * @param context
     * @param bitmap
     * @param fileName
     */
    public static String saveBitmap(Context context, Bitmap bitmap, String fileName) {
        if (context == null || bitmap == null || TextUtils.isEmpty(fileName)) {
            return null;
        }
        String ret = null;
        OutputStream fos = null;
        try {

            Uri uri = Uri.fromFile(new File(BitmapUtils.getTempSaveDir() + fileName));
            File file = new File(uri.getPath());
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fos = context.getContentResolver().openOutputStream(uri);
            if (fos != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            }
            ret = getTempSaveDir() + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String getTempSaveDir() {
        String userDir = "";
        if (isSDCardMounted()) {
            userDir = Environment.getExternalStorageDirectory().toString() + System.getProperty("file.separator");
        } else {
            userDir = System.getProperty("file.separator");
        }
        File file = new File(userDir + TEMP_FOLDER);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + "/";
    }

    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

    }

    /**
     * 截取ContentView
     *
     * @param activity
     * @return
     */
    public static Bitmap getContentViewShot(Activity activity) {
        // 获取windows中最顶层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();
        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);
        Bitmap srcBp = view.getDrawingCache();
        // 去掉状态栏
        Bitmap bmp = Bitmap.createBitmap(srcBp, 0,
                0, srcBp.getWidth(), srcBp.getHeight());
        // 销毁缓存信息
        view.destroyDrawingCache();
        return bmp;
    }


    /**
     * 获取视频的缩略图
     *
     * @param videoPath
     * @param videoWidth
     * @param videoHeight
     * @return
     */
    public static Bitmap getVideoThumbnail(String videoPath, int videoWidth, int videoHeight) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
//        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath,kind);
//        bitmap = ThumbnailUtils.extractThumbnail(bitmap,videoWidth,videoHeight,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
//        return bitmap;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(videoPath, new HashMap<String, String>());
            } else {
                retriever.setDataSource(videoPath);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, videoWidth, videoHeight,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

    /**
     * 对指定路径的图片进行压缩
     *
     * @param srcPath
     * @return
     */
    public static Bitmap compressImageFromFile(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Config.RGB_565;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }


    /**
     * 从裁剪图片文件大小到指定尺寸
     *
     * @param path      图片文件
     * @param reqWidth  指定宽度
     * @param reqHeight 指定高度
     * @return
     */
    public static Bitmap compressImageFromFile(String path, float reqWidth, float reqHeight) {
        Bitmap bitmap;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int sampSize = 1;
        if (w > h && w > reqWidth) {
            sampSize = (int) Math.rint(w / reqWidth);
        } else if (h > w && h > reqHeight) {
            sampSize = (int) Math.rint(h / reqHeight);
        }

        if (sampSize <= 1) {
            sampSize = 1;
        } else if (sampSize < 2) {
            sampSize = 2;
        }
        newOpts.inSampleSize = sampSize;
        bitmap = BitmapFactory.decodeFile(path, newOpts);
        return bitmap;
    }

    /**
     * 图片无损压缩到文件
     *
     * @param bmp  原图片bp
     * @param file 压入的目标文件
     */
    public static void compressBmpToFile(Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        if (context == null || uri == null) return null;
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static final byte[] inputToByte(InputStream inStream) {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        try {
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return swapStream.toByteArray();
    }
}
