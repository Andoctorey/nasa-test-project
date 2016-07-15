package by.yegorov.nasa.core.image;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.IOException;

import by.yegorov.nasa.R;
import by.yegorov.nasa.core.dagger.PerApp;
import by.yegorov.nasa.core.utils.ExceptionUtils;
import dagger.Module;
import dagger.Provides;

@SuppressWarnings("unused")
@Module()
public class ImageModule {

    @Provides
    DisplayImageOptions.Builder provideDisplayImageOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.drawable.ic_image_stub)
                .showImageOnFail(R.drawable.ic_image_stub)
                .showImageOnLoading(R.drawable.ic_image_stub)
                .bitmapConfig(Bitmap.Config.RGB_565);
    }

    @Provides
    @PerApp
    ImageLoader provideImageLoader(Context context, DisplayMetrics metrics, DisplayImageOptions.Builder displayOptions) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(metrics.widthPixels, metrics.heightPixels)
                .diskCacheExtraOptions(metrics.widthPixels, metrics.heightPixels, new BitmapProcessor() {
                    @Override
                    public Bitmap process(Bitmap bitmap) {
                        return bitmap;
                    }
                })
                .threadPoolSize(Runtime.getRuntime().availableProcessors())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCache(new LruMemoryCache((int) (Runtime.getRuntime().maxMemory() / 4)))
                .imageDownloader(new BaseImageDownloader(context, 70 * 1000, 300 * 1000))
                .defaultDisplayImageOptions(provideDisplayImageOptions().build());
        try {
            LruDiskCache cache = new LruDiskCache(StorageUtils.getCacheDirectory(context), new Md5FileNameGenerator(), 300 * 1024 * 1024);
            builder.diskCache(cache);
        } catch (IOException e) {
            ExceptionUtils.handleException(e);
        }
        ImageLoader.getInstance().init(builder.build());
        return ImageLoader.getInstance();
    }
}
