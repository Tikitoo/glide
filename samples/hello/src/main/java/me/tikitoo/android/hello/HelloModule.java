package me.tikitoo.android.hello;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

public class HelloModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // /storage/emulated/0/Android/data/me.tikitoo.android.hello/cache/image_manager_disk_cache
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));
        customDiskCache(builder);
        MemorySizeCalculator memorySizeCalculator = new MemorySizeCalculator.Builder(context).build();
        int bitPoolSize = memorySizeCalculator.getBitmapPoolSize();
        int memoryCacheSize = memorySizeCalculator.getMemoryCacheSize();
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(bitPoolSize));
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
    }

    private void customDiskCache(GlideBuilder builder) {
        builder.setDiskCache(new DiskCache.Factory() {
            @Nullable
            @Override
            public DiskCache build() {
                File file = new File("");
                file.mkdirs();
                return DiskLruCacheWrapper.get(file, 5 * 1024 * 1024);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Registry registry) {

    }
}
