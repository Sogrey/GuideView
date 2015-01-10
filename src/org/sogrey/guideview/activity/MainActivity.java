package org.sogrey.guideview.activity;

import java.util.ArrayList;

import org.sogrey.guideview.R;
import org.sogrey.guideview.adaper.GalleryAdapter;
import org.sogrey.guideview.adaper.ViewPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Sogrey
 * 
 */
public class MainActivity extends Activity implements OnPageChangeListener, OnItemSelectedListener, OnItemClickListener{

	//引导图片资源
    int[] pics = {R.drawable.img0,
   		 R.drawable.img1,
   		 R.drawable.img2,
   		 R.drawable.img3,
   		 R.drawable.img4,
   		 R.drawable.img5
   		 };
    
	private ViewPager mViewPager;
	private Gallery Gallery;
	private ArrayList<View> views;
	private ViewPagerAdapter vpAdapter;
	private GalleryAdapter GAdapter;
	
	private boolean whoTouch=false;//谁主动滑动的  false ：up    true：down

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(org.sogrey.guideview.R.layout.activity_main);
		initViews();
		initData();
	}

	private void initViews() {
		// 实例化ViewPager
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		// 实例化Gallery
		Gallery = (Gallery) findViewById(R.id.gallery);
//		Gallery.setSpacing(-250);

		// 实例化ArrayList对象
		views = new ArrayList<View>();
		// 实例化ViewPager适配器
		vpAdapter = new org.sogrey.guideview.adaper.ViewPagerAdapter(views);
		
		GAdapter = new GalleryAdapter(this,pics);
	}

	private void initData() {

		//定义一个布局并设置参数
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                														  LinearLayout.LayoutParams.FILL_PARENT);
        //初始化引导图片列表
        for(int i=0; i<pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            views.add(iv);
        } 
        
        //设置数据
        mViewPager.setAdapter(vpAdapter);
        //设置监听
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				whoTouch = false;
				return false;
			}
		});

        Gallery.setAdapter(GAdapter);
        Gallery.setOnItemSelectedListener(this);
        Gallery.setOnItemClickListener(this);
        Gallery.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				whoTouch = true;
				return false;
			}
		});
	}
	
	/**
	 * 当滑动状态改变时调用
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
	
	/**
	 * 当当前页面被滑动时调用
	 */

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}
	
	/**
	 * 当新的页面被选中时调用
	 */

	@Override
	public void onPageSelected(int position) {
//		whoTouch = false;
		//设置底部小点选中状态
        setCurDow(position);
	}

	private void setCurDow(int position) {
		if (!whoTouch) 
			Gallery.setSelection(position, true);
	}
	
	private void setCurUp(int position) {
		if (whoTouch) 
		mViewPager.setCurrentItem(position, true);
//		mViewPager.setCurrentItem(position);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		 setCurUp(position);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Gallery.setSelection(position-1, true);
		mViewPager.setCurrentItem(position, true);
	}
}
