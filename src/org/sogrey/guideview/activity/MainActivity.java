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

	//����ͼƬ��Դ
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
	
	private boolean whoTouch=false;//˭����������  false ��up    true��down

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(org.sogrey.guideview.R.layout.activity_main);
		initViews();
		initData();
	}

	private void initViews() {
		// ʵ����ViewPager
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		// ʵ����Gallery
		Gallery = (Gallery) findViewById(R.id.gallery);
//		Gallery.setSpacing(-250);

		// ʵ����ArrayList����
		views = new ArrayList<View>();
		// ʵ����ViewPager������
		vpAdapter = new org.sogrey.guideview.adaper.ViewPagerAdapter(views);
		
		GAdapter = new GalleryAdapter(this,pics);
	}

	private void initData() {

		//����һ�����ֲ����ò���
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                														  LinearLayout.LayoutParams.FILL_PARENT);
        //��ʼ������ͼƬ�б�
        for(int i=0; i<pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            views.add(iv);
        } 
        
        //��������
        mViewPager.setAdapter(vpAdapter);
        //���ü���
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
	 * ������״̬�ı�ʱ����
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
	
	/**
	 * ����ǰҳ�汻����ʱ����
	 */

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}
	
	/**
	 * ���µ�ҳ�汻ѡ��ʱ����
	 */

	@Override
	public void onPageSelected(int position) {
//		whoTouch = false;
		//���õײ�С��ѡ��״̬
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
