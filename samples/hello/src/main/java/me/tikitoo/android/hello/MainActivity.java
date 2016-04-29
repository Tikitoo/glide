package me.tikitoo.android.hello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    public static final String IMG_URL = "http://ww2.sinaimg.cn/large/68622377gw1f1o12qodaoj20760760t7.jpg";
    
    public static final String[] IMG_URLS = new String[] {
            "http://farm2.staticflickr.com/1468/26085151174_3d26414da5_m.jpg",
            "http://farm2.staticflickr.com/1514/26086855813_45a1d43860_m.jpg",
            "http://farm2.staticflickr.com/1537/26085126394_b89bdc67c5_m.jpg",
            "http://farm2.staticflickr.com/1709/26085176674_c938581d2a_m.jpg",
            "http://farm2.staticflickr.com/1620/26085161554_137e084a04_m.jpg",
            "http://farm2.staticflickr.com/1476/26085173654_d3de8be302_m.jpg",
            "http://farm2.staticflickr.com/1565/26085133614_7c5281b929_m.jpg",
            "http://farm2.staticflickr.com/1530/26085155304_ff15b7ca11_m.jpg",
            "http://farm2.staticflickr.com/1461/26085156244_3755395d56_m.jpg",
            "http://farm2.staticflickr.com/1545/26085123394_a8a2b96961_m.jpg",
            "http://farm2.staticflickr.com/1631/26085140664_293d08a523_m.jpg",
            "http://farm2.staticflickr.com/1686/26085155554_72869ac0a1_m.jpg",
            "http://farm2.staticflickr.com/1544/26085172074_dc4850d6cd_m.jpg",
            "http://farm2.staticflickr.com/1560/26085151934_9650ebb3b9_m.jpg",
            "http://farm2.staticflickr.com/1688/26085172204_ba0d688f5b_m.jpg",
            "http://farm2.staticflickr.com/1564/26085149374_49d888d41e_m.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView defImg = (ImageView) findViewById(R.id.image_def);
        LinearLayout topLayot = (LinearLayout) findViewById(R.id.layout_top);
        ImageView imageView;
        for (String imgUrl : IMG_URLS) {
            imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    600));

            showImage(imageView, imgUrl);
            topLayot.addView(imageView);
        }



        /*Glide.with(this)
                .load(IMG_URL)
                .apply(fitCenterTransform(this))
                .into(defImg);*/

    }

    private void showImage(ImageView defImg, Object model) {
        RequestOptions requestOptions = new RequestOptions()
                .override(300, 100)
                .signature(new ObjectKey(model))

                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop(this);


        RequestManager manager = Glide.with(this);

        RequestBuilder builder = manager.load(model)
                .thumbnail(0.5F)
                .apply(requestOptions);
        Target target = builder.into(defImg);
        Target target2 = builder.into(new SimpleTarget<byte[]>(250, 250) {
            @Override
            public void onResourceReady(byte[] resource, Transition<? super byte[]> transition) {

            }
        });
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
