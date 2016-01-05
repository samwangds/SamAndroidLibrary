package com.sam.lib.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtils {
	
	
	/**
	 * 重新设置位图的大小
	 * @param bitmap 原图
	 * @param w 长
	 * @return Bitmap 新图
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap, int w) {
		try {
			if (bitmap != null) {
				int width = bitmap.getWidth();
				int height = bitmap.getHeight();
//				int newHeight = h;
				float scaleWidth = ((float) w/3) / width;
				float scaleHeight = ((float) w/3) / height;
				Matrix matrix = new Matrix();
				matrix.postScale(scaleWidth, scaleHeight);
                return Bitmap.createBitmap(bitmap, 0, 0, width,
						height, matrix, true);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bitmap;
	}

}
