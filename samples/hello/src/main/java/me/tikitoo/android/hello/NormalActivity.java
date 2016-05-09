package me.tikitoo.android.hello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.security.MessageDigest;

public class NormalActivity extends AppCompatActivity {
    public static final String IMG_URL = "http://ww2.sinaimg.cn/large/68622377gw1f1o12qodaoj20760760t7.jpg";
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ImageView defImg = (ImageView) findViewById(R.id.image_def);
        LinearLayout topLayot = (LinearLayout) findViewById(R.id.layout_top);
        ImageView imageView;
        for (String imgUrl : Constants.IMG_URLS) {
            imageView = new ImageView(NormalActivity.this);
            imageView.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    600));

            showImage(imageView, imgUrl);
            topLayot.addView(imageView);
        }
    }

    private void showImage(ImageView defImg, Object model) {
        RequestOptions requestOptions = new RequestOptions()
                .override(300, 200)
                .format(DecodeFormat.PREFER_RGB_565)
//                .signature(new ObjectKey(model))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop(this);


        RequestManager manager = Glide.with(this);

        RequestBuilder builder = manager.load(model)
                .thumbnail(0.5F)
                .apply(requestOptions);
        getTarget(defImg, builder);
    }

    private void getTarget(ImageView defImg, RequestBuilder builder) {
        Target target = builder.into(defImg);
        /*Target target2 = builder.into(new SimpleTarget<byte[]>(250, 250) {
            @Override
            public void onResourceReady(byte[] resource, Transition<? super byte[]> transition) {

            }
        });*/

        /*builder.into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

            }
        });*/
    }

    public class StringSignature implements Key {
        private String url;

        public StringSignature(String url) {
            this.url = url;
        }


        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {
//            messageDigest.update(ByteBuffer.allocate());
        }
    }
}
