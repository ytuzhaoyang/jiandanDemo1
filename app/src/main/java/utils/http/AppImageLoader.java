package utils.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;
import com.sina.young.jiandandemo1.base.BaseApplication;

import java.io.File;

import utils.BitmapUtil;

public class AppImageLoader {
	
	private RequestQueue mVolleyQueue;
	private ImageLoader mImageLoader;
	private static AppImageLoader instance;
	
	
	private AppImageLoader(){
		mVolleyQueue = Volley.newRequestQueue(BaseApplication.application);
		int max_cache_size = 1000000;
		mImageLoader = new ImageLoader(mVolleyQueue, new DiskBitmapCache(BaseApplication.application.getExternalCacheDir(),max_cache_size));
	}
	
	public static ImageLoader getLoader(){
		if(instance == null){
			instance = new AppImageLoader();
		}
		
		return instance.mImageLoader;
	}

	
	public class BitmapCache extends LruCache<String,Bitmap> implements ImageCache {
	    public BitmapCache(int maxSize) {
	        super(maxSize);
	    }
	 
	    @Override
	    public Bitmap getBitmap(String url) {
	        return (Bitmap)get(url);
	    }
	 
	    @Override
	    public void putBitmap(String url, Bitmap bitmap) {
	        put(url, bitmap);
	    }
	}
	
	public  class DiskBitmapCache extends DiskBasedCache implements ImageCache {
		 
	    public DiskBitmapCache(File rootDirectory, int maxCacheSizeInBytes) {
	        super(rootDirectory, maxCacheSizeInBytes);
	    }
	 
	    public DiskBitmapCache(File cacheDir) {
	        super(cacheDir);
	    }
	 
	    public Bitmap getBitmap(String url) {
	        final Entry requestedItem = get(url);
	 
	        if (requestedItem == null)
	            return null;
	 
	        return BitmapFactory.decodeByteArray(requestedItem.data, 0, requestedItem.data.length);
	    }
	 
	    public void putBitmap(String url, Bitmap bitmap) {
	        
	    	final Entry entry = new Entry();
	        
/*			//Down size the bitmap.If not done, OutofMemoryError occurs while decoding large bitmaps.
 			// If w & h is set during image request ( using ImageLoader ) then this is not required.
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap downSized = BitmapUtil.downSizeBitmap(bitmap, 50);
			
			downSized.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] data = baos.toByteArray();
	        entry.data = data ; */
			
	        entry.data = BitmapUtil.convertBitmapToBytes(bitmap) ;
	        put(url, entry);
	    }
	}
	
	
}
