package me.tikitoo.android.hello;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class LoadDiffImageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private Button mResBtn;
    private Button mFileBtn;
    private Button mUriBtn;
    private Button mUriResBtn;
    private Button mErrorBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_diff_image);
        initView();
    }

    private void init(Object object) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_http_black);

        RequestManager requestManager = Glide.with(LoadDiffImageActivity.this);

        RequestBuilder requestBuilder = requestManager.load(object)
                .thumbnail(0.5F)
                .apply(requestOptions)
                .transition(withCrossFade());

                requestBuilder.into(mImageView)
        ;
    }

    private void loadDiff() {

    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.image_view);
        mResBtn = (Button) findViewById(R.id.btn_res);
        mFileBtn = (Button) findViewById(R.id.btn_file);
        mUriBtn = (Button) findViewById(R.id.btn_uri);
        mUriResBtn = (Button) findViewById(R.id.btn_uri_res);
        mErrorBtn = (Button) findViewById(R.id.btn_error);
        Button[] buttons = new Button[] {mResBtn, mFileBtn, mUriBtn, mUriResBtn, mErrorBtn};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_res:
                init(R.drawable.ic_accessible_black);
                break;
            case R.id.btn_file:
                String str = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/";
                String fileName = "001.jpg";
                String fileStr = str + fileName;
                init(fileStr);
                break;
            case R.id.btn_uri:
                init(Uri.parse(Constants.IMG_URLS[3]));
                break;
            case R.id.btn_uri_res:
                init(LoadDiffImageActivity.resourceIdToUri(this, R.drawable.ic_accessible_black));
                break;
            case R.id.btn_error:
                init("fdafadf");
                break;
        }
    }

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}



