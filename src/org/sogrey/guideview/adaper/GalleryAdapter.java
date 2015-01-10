/**
 * 
 */
package org.sogrey.guideview.adaper;

import org.sogrey.guideview.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * @author Sogrey
 *
 */
public class GalleryAdapter extends BaseAdapter{

	Context mContext;
	int[] mPics;
	
	public GalleryAdapter(Context context,int[] pics){
		mContext = context;
		mPics  = pics;
	}
	@Override
	public int getCount() {
		return mPics.length;
	}

	@Override
	public Object getItem(int position) {
		return mPics[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ImageView view;
		if (convertView == null) {
			view = new ImageView(mContext);
			view.setMaxHeight(120);
			view.setMaxWidth(200);
		} else {
			view = (ImageView) convertView;
		}
		view.setImageResource(mPics[position]);
		return view;
	}
}
