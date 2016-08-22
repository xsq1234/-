package cn.xie.zhbj.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.xie.zhbj.R;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class NewsDetailActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.ib_menu)
	private ImageButton ibMenu;
	@ViewInject(R.id.iv_size)
	private ImageView ivSize;
	@ViewInject(R.id.iv_share)
	private ImageView ivShare;
	@ViewInject(R.id.wv_pager)
	private WebView webView;
	@ViewInject(R.id.pb_loading)
	private ProgressBar pbLoad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_detail);
		ViewUtils.inject(this);
		ibMenu.setOnClickListener(this);
		ivShare.setOnClickListener(this);
		ivSize.setOnClickListener(this);
		initData();
	}

	private void initData() {
		String url = getIntent().getStringExtra("url");
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setUseWideViewPort(true);

		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				pbLoad.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				pbLoad.setVisibility(View.GONE);
			}

		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_menu:
			finish();
			break;
		case R.id.iv_size:
			showChooseDialog();
			break;
		case R.id.iv_share:
			showShare();
			break;

		}

	}

	private int mCurrentWhich = 2;
	private int mTempWhich;

	private void showChooseDialog() {
		String[] items = new String[] { "���������", "�������", "��������", "С������",
				"��С������" };
		Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��������");
		builder.setSingleChoiceItems(items, 2,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mTempWhich = which;

					}
				});
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				WebSettings settings = webView.getSettings();
				switch (mTempWhich) {
				case 0:
					settings.setTextSize(TextSize.LARGEST);
					break;
				case 1:
					settings.setTextSize(TextSize.LARGER);
					break;
				case 2:
					settings.setTextSize(TextSize.NORMAL);
					break;
				case 3:
					settings.setTextSize(TextSize.SMALLER);
					break;
				case 4:
					settings.setTextSize(TextSize.SMALLEST);
					break;

				}
				mCurrentWhich = mTempWhich;
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	// ����������
	// ȷ��SDcard������ڴ���ͼƬ
	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();

		oks.setTheme(OnekeyShareTheme.CLASSIC);

		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		// ����ʱNotification��ͼ������� 2.5.9�Ժ�İ汾�����ô˷���
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle("����");
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		oks.setTitleUrl("http://sharesdk.cn");
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		oks.setText("���Ƿ����ı�,������");
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		// oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		oks.setUrl("http://sharesdk.cn");
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		oks.setComment("���ǲ��������ı�");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		oks.setSite(getString(R.string.app_name));
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		oks.setSiteUrl("http://sharesdk.cn");

		// ��������GUI
		oks.show(this);
	}

}
