package cn.xie.zhbj.activity;

import cn.xie.zhbj.R;
import cn.xie.zhbj.global.GlobalContant;
import cn.xie.zhbj.utlis.PrefUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplishActivity extends Activity {

	private RelativeLayout ivSplishHorse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splish);
		ivSplishHorse = (RelativeLayout) findViewById(R.id.iv_splish_horse);
		initAnimation();
	}

	private void initAnimation() {
		// 渐变动画
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(4000);
		alphaAnimation.setFillAfter(true);
		// ivSplishHorse.startAnimation(alphaAnimation);
		// 旋转动画
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(3000);
		rotateAnimation.setRepeatMode(rotateAnimation.REVERSE);
		alphaAnimation.setFillAfter(true);
		// 缩放动画
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(3000);
		scaleAnimation.setFillAfter(true);
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(scaleAnimation);
		set.addAnimation(alphaAnimation);
		set.addAnimation(rotateAnimation);
		ivSplishHorse.startAnimation(set);
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				boolean start = PrefUtils.getBoolean(getApplicationContext(),
						GlobalContant.PREF_START, false);
				if (start) {

					startActivity(new Intent(getApplicationContext(),
							HomeActivity.class));
				} else {

					startActivity(new Intent(getApplicationContext(),
							GuideActivity.class));
				}
				finish();
			}
		});
	}

}
