package nianhuibao.com.androidprojects.screen_shot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.os.Build;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rookie on 2017-03-14.
 */

public class FakeWebView extends WebView {
    private Context mContext;
    private WebView webView;

    public static final int MODE_DAY = 100;
    public static final int MODE_NIGHT = 200;

    private final static int CLIP_BP_WIDTH = 1000;
    private final static int CLIP_BP_HEIGHT = 750;

    @IntDef({MODE_DAY, MODE_NIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewMode {
    }

    private int mode = MODE_DAY;

    private String content;


    public FakeWebView(Context context) {
        super(context);
        Init(context);
    }


    public FakeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    public FakeWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context);
    }

    private void Init(Context context) {
        mContext = context;
        webView = this;
        webView.setPadding(10, 10, 10, 10);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
    }

    private boolean isFirstLoad = false;

    public void loadData(HtmlBean bean,String url) {
        assembleData(bean);
        if (Build.VERSION.SDK_INT >= 21) {
            isFirstLoad = true;
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                        if (isFirstLoad) {
                            isFirstLoad = false;
                            Log.e("TAG", "onProgressChanged");
                            updateView();
                        }
                    }
                }
            });
        } else {
            isFirstLoad = true;
            webView.setVisibility(View.INVISIBLE);

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                        updateView();
                        if (!isFirstLoad)
                            webView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        webView.loadUrl(url);
    }

    private void assembleData(HtmlBean bean) {
        final String data = bean.getContent();
        final String title = bean.getTitle();
        final String username = bean.getUsername();
        final String publishTime = bean.getPublishTime();
        String Title = "<h2>" + title + "</h2>";
        String Footer = "<p>" + username + "</p><p>" + publishTime + "</p>";
        content = Title + data + Footer;
    }


    public void updateView() {
        if (mode == MODE_DAY) {
            webView.setBackgroundColor(Color.WHITE);
        } else {
            webView.setBackgroundColor(Color.parseColor("#263238"));
            content = "<div style=\"color: gray;display: inline;\">" + content + "</div>";
        }
        webView.loadUrl("javascript:changeContent(\"" + content.replace("\n", "\\n").replace("\"", "\\\"").replace("'", "\\'") + "\")");
        if (Build.VERSION.SDK_INT < 21) {
            if (isFirstLoad) {
                webView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isFirstLoad = false;
                        webView.reload();//为解决部分手机打开不显示图片问题
                    }
                }, 500);
            }
        }
    }

    /**
     * @param mode
     */
    public void setMode(@ViewMode int mode) {
        this.mode = mode;
        updateView();
    }

    public Bitmap getScreenView() {
        Picture snapShot = webView.capturePicture();
        Bitmap bmp = Bitmap.createBitmap(CLIP_BP_WIDTH, CLIP_BP_HEIGHT, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        return bmp;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        webView.setVisibility(View.GONE);
        this.removeView(webView);
        webView.destroy();
        webView = null;
    }
}
