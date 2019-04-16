package cn.com.food.measure.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import cn.com.food.measure.R;
import cn.com.food.measure.base.BaseActivity;
import cn.com.food.measure.ui.backup.BackupActivity;
import cn.com.food.measure.ui.business.BusinessActivity;
import cn.com.food.measure.ui.login.LoginActivity;
import cn.com.food.measure.util.SharedPreUtil;

public class MainActivity extends BaseActivity {

    private TextView tvMainBack;
    private TextView tvMainTitle;
    private TextView tvMainAction;
    private Banner mainBanner;
    private TextView tvMainBusiness;
    private TextView tvMainBackup;

    private List<Integer> bannerList;

    @Override
    protected Settings setActivitySettings(View contentView) {
        return new Settings();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View contentView) {
        tvMainBack = contentView.findViewById(R.id.custom_back);
        tvMainTitle = contentView.findViewById(R.id.custom_title);
        tvMainAction = contentView.findViewById(R.id.custom_action);
        mainBanner = contentView.findViewById(R.id.main_banner);
        tvMainBusiness = contentView.findViewById(R.id.main_business);
        tvMainBackup = contentView.findViewById(R.id.main_backup);
    }

    @Override
    protected void bindEvent(View contentView) {
        tvMainAction.setOnClickListener(this);
        tvMainBusiness.setOnClickListener(this);
        tvMainBackup.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void initData() {
        tvMainTitle.setText(getString(R.string.main_title));
        tvMainAction.setText(getString(R.string.main_login_out));
        tvMainBack.setVisibility(View.GONE);

        bannerList = new ArrayList<>();
        bannerList.add(R.drawable.main_banner1);
        setBanner(bannerList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_action:
                SharedPreUtil.saveLogin(false);
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.main_business:
                startActivity(new Intent(this, BusinessActivity.class));
                break;
            case R.id.main_backup:
                startActivity(new Intent(this, BackupActivity.class));
                break;
            default:
                break;
        }
    }

    private void setBanner(List<Integer> bannerList) {
        mainBanner.setDelayTime(3500);
        // 设置图片加载器
        mainBanner.setImageLoader(new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View view) {
                ImageView imageView = (ImageView) view;
                imageView.setImageResource((Integer) path);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }

            @Override
            public View createImageView(Context context) {
//                ImageView imageView = new ImageView(context);
                return null;
            }
        });
        // 设置图片集合
        mainBanner.setImages(bannerList);
        // banner设置方法全部调用完毕时最后
        mainBanner.start();
    }
}
