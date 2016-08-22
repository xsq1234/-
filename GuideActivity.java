package cn.xie.zhbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.xie.zhbj.R;
import cn.xie.zhbj.global.GlobalContant;
import cn.xie.zhbj.utlis.PrefUtils;

public class GuideActivity extends Activity {

	private ViewPager viewPager;
	private int[] arr = { R.drawable.guide_1, R.drawable.guide_2,
			R.drawable.guide_3 };
	private Button btnStart;
	private LinearLayout llContainer;
	private ImageView ivPoint;
	private ImageView ivRedPoint;
	private int totalmove;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		btnStart = (Button) findViewById(R.id.btn_start);
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		ivRedPoint = (ImageView) findViewById(R.id.iv_red_piont);
		initData();
	}

	private void initData() {
		viewPager.setAdapter(new MyAdapter());
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arr.length - 1 == arg0) {
					btnStart.setVisibility(View.VISIBLE);
				} else {
					btnStart.setVisibility(View.GONE);
				}

			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				int pos = (int) (totalmove * arg1) + totalmove * position;
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint
						.getLayoutParams();
				params.leftMargin = pos;
				ivRedPoint.setLayoutParams(params);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PrefUtils.setBoolean(getApplicationContext(),
						GlobalContant.PREF_START, true);
				startActivity(new Intent(getApplicationContext(),
						HomeActivity.class));
				finish();
			}
		});
		for (int i = 0; i < arr.length; i++) {
			ivPoint = new ImageView(this);
			if (i > 0) {
				LinearLayout.LayoutParams parmas = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				parmas.leftMargin = 10;
				ivPoint.setLayoutParams(parmas);
			}
			ivPoint.setImageResource(R.drawable.shape_point_gray);
			llContainer.addView(ivPoint);

		}
		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						totalmove = llContainer.getChildAt(1).getLeft()
								- llContainer.getChildAt(0).getLeft();
					}
				});
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return arr.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setBackgroundResource(arr[position]);
			container.addView(imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}
}
