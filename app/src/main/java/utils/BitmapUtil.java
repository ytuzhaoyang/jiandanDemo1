package utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;

public class BitmapUtil {

	public static Bitmap downSizeBitmap(Bitmap bitmap,int reqSize)  {
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		 
		float scaleWidth = ((float) reqSize) / width;
		float scaleHeight = ((float) reqSize) / height;
		 
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		 
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
		return resizedBitmap;
		 
		/*if(bitmap.getWidth() < reqSize) {
			return bitmap;
		} else {
			return Bitmap.createScaledBitmap(bitmap, reqSize, reqSize, false);
		} */
	}
	
 
    public static byte[] convertBitmapToBytes(Bitmap bitmap) {
      
	  	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  	bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }

}