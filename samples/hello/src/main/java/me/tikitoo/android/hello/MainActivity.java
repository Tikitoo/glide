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
import com.bumptech.glide.signature.ObjectKey;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    public static final String IMG_URL = "http://ww2.sinaimg.cn/large/68622377gw1f1o12qodaoj20760760t7.jpg";
    
    public static final String[] IMG_URLS = new String[] {
            "https://static.pexels.com/photos/5854/sea-woman-legs-water-medium.jpg",
            "https://static.pexels.com/photos/6245/kitchen-cooking-interior-decor-medium.jpg",
            "https://static.pexels.com/photos/6770/light-road-lights-night-medium.jpg",
            "https://static.pexels.com/photos/6041/nature-grain-moving-cereal-medium.jpg",
            "https://static.pexels.com/photos/7116/mountains-water-trees-lake-medium.jpg",
            "https://static.pexels.com/photos/6601/food-plate-yellow-white-medium.jpg",
            "https://static.pexels.com/photos/6695/summer-sun-yellow-spring-medium.jpg",
            "https://static.pexels.com/photos/7117/mountains-night-clouds-lake-medium.jpg",
            "https://static.pexels.com/photos/7262/clouds-ocean-seagull-medium.jpg",
            "https://static.pexels.com/photos/5968/wood-nature-dark-forest-medium.jpg",
            "https://static.pexels.com/photos/6101/hands-woman-art-hand-medium.jpg",
            "https://static.pexels.com/photos/6571/pexels-photo-medium.jpeg",
            "https://static.pexels.com/photos/6740/food-sugar-lighting-milk-medium.jpg",
            "https://static.pexels.com/photos/5659/sky-sunset-clouds-field-medium.jpg",
            "https://static.pexels.com/photos/6945/sunset-summer-golden-hour-paul-filitchkin-medium.jpg",
            "https://static.pexels.com/photos/6151/animal-cute-fur-white-medium.jpg",
            "https://static.pexels.com/photos/5696/coffee-cup-water-glass-medium.jpg",
            "https://static.pexels.com/photos/6789/flowers-petals-gift-flower-medium.jpg",
            "https://static.pexels.com/photos/7202/summer-trees-sunlight-trail-medium.jpg",
            "https://static.pexels.com/photos/7147/night-clouds-summer-trees-medium.jpg",
            "https://static.pexels.com/photos/6342/woman-notebook-working-girl-medium.jpg"
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
                .override(300, 200)
                .format(DecodeFormat.PREFER_RGB_565)
                .signature(new ObjectKey(model))
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
