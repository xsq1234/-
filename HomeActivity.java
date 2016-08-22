package cn.xie.zhbj.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import cn.xie.zhbj.R;
import cn.xie.zhbj.fragment.ContentFrament;
import cn.xie.zhbj.fragment.LeftFragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {

	private static final String contentframent = "tag_content";
	private static final String LeftFragment = "tag_left";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(200);
		initView();
	}

	private void initView() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fl_menu, new LeftFragment(), LeftFragment);
		transaction.replace(R.id.fl_home, new ContentFrament(), contentframent);
		transaction.commit();

	}

	// 供外边获取LeftFragment对象方法
	public LeftFragment getLeftFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftFragment fragment = (LeftFragment) fm
				.findFragmentByTag(LeftFragment);
		return fragment;

	}

	// 供外边获取ContentFragment对象方法
	public ContentFrament getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFrament fragment = (ContentFrament) fm
				.findFragmentByTag(contentframent);
		return fragment;

	}

}
