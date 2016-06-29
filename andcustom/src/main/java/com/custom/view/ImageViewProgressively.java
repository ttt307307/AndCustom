package com.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.custom.andcustom.R;


public class ImageViewProgressively extends ImageView{

	Context mContext;
	
	//图片展开或收缩方向，默认为horizontal;
	private int orientation_progress = 1;
	//图片展开形式，默认从top到bottom
	private int expend_type = 0x30;
	//递增或递减的幅度（level的范围在0-10000），可以为负数
	private int level_change_step = 1000;
	//为了使动画效果明显，设置延迟修改的时间间隔,默认为50ms
	private int level_change_delayed = 50;
	
	
	public ImageViewProgressively(Context context) {
		super(context);
		mContext = context;
		// TODO Auto-generated constructor stub
		
	}
	
	public ImageViewProgressively(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ImageViewProgressively);
		orientation_progress = array.getInt(R.styleable.ImageViewProgressively_orientation_progress, 1);
		expend_type = array.getInt(R.styleable.ImageViewProgressively_expend_type, 0x30);
		level_change_step = array.getInt(R.styleable.ImageViewProgressively_level_change_step, 1000);
		level_change_delayed = array.getInt(R.styleable.ImageViewProgressively_level_change_delayed, 50);
		
		Drawable d = this.getDrawable();
        if (d != null) {
            setImageDrawable(d);
        }
        array.recycle();
	}

	@Override
	public void setImageDrawable(Drawable drawable) {
		// TODO Auto-generated method stub
		ClipDrawable mClipDrawable = new ClipDrawable(drawable, expend_type, orientation_progress);
		super.setImageDrawable(mClipDrawable);
		if(level_change_step > 0){
			mClipDrawable.setLevel(0);
		}else{
			mClipDrawable.setLevel(10000);
		}
		
		this.post(animateImage);
	}
	
	@Override
	public void setImageBitmap(Bitmap bm) {
		// TODO Auto-generated method stub
		setImageDrawable(new BitmapDrawable(mContext.getResources(), bm));
	}
	
	private Runnable animateImage = new Runnable() {
		
		@Override
		public void run() {
			doTheAnimation(getDrawable());
		}
	};
	
    private void doTheAnimation(Drawable mDrawable){
    	mDrawable.setLevel(mDrawable.getLevel() + level_change_step);
    	if (mDrawable.getLevel() <= 10000 && mDrawable.getLevel() >=0) {
			this.postDelayed(animateImage, level_change_delayed);
		}else {
			this.removeCallbacks(animateImage);
		}
    }
}
