package cn.com.food.measure.ui.business;

import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import cn.com.food.measure.R;
import cn.com.food.measure.base.BaseActivity;
import cn.com.food.measure.base.BaseConfig;
import cn.com.food.measure.bean.BusinessBean;

public class BusinessDetailActivity extends BaseActivity {

    private TextView tvDetailBack;
    private TextView tvDetailTitle;
    private TextView tvDetailNo;
    private TextView tvDetailTruck;
    private TextView tvDetailMaterial;
    private TextView tvDetailSender;
    private TextView tvDetailReceiver;
    private TextView tvDetailCompany;
    private TextView tvDetailPound1;
    private TextView tvDetailPound2;
    private TextView tvDetailNet;
    private TextView tvDetailGross;
    private TextView tvDetailTare;

    @Override
    protected Settings setActivitySettings(View contentView) {
        return new Settings();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_business_detail;
    }

    @Override
    protected void initView(View contentView) {
        tvDetailBack = contentView.findViewById(R.id.custom_back);
        tvDetailTitle = contentView.findViewById(R.id.custom_title);
        tvDetailNo = contentView.findViewById(R.id.tv_business_detail_no);
        tvDetailTruck = contentView.findViewById(R.id.tv_business_detail_truck);
        tvDetailMaterial = contentView.findViewById(R.id.tv_business_detail_material);
        tvDetailSender = contentView.findViewById(R.id.tv_business_detail_sender);
        tvDetailReceiver = contentView.findViewById(R.id.tv_business_detail_receiver);
        tvDetailCompany = contentView.findViewById(R.id.tv_business_detail_company);
        tvDetailPound1 = contentView.findViewById(R.id.tv_business_detail_date_pound_one);
        tvDetailPound2 = contentView.findViewById(R.id.tv_business_detail_date_pound_two);
        tvDetailNet = contentView.findViewById(R.id.tv_business_detail_net);
        tvDetailGross = contentView.findViewById(R.id.tv_business_detail_gross);
        tvDetailTare = contentView.findViewById(R.id.tv_business_detail_tare);
    }

    @Override
    protected void bindEvent(View contentView) {
        tvDetailBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvDetailTitle.setText(getString(R.string.business_detail_title));

        BusinessBean businessBean = (BusinessBean) getIntent().getSerializableExtra(BaseConfig.PARAM_BUSINESS_DETAIL);
        setDetailData(businessBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void setDetailData(BusinessBean businessBean) {
        tvDetailNo.setText(businessBean.getJjh());
        tvDetailTruck.setText(businessBean.getTruckno());
        tvDetailMaterial.setText(businessBean.getGoods());
        tvDetailSender.setText(businessBean.getSender());
        tvDetailReceiver.setText(businessBean.getReceiver());
        tvDetailCompany.setText(businessBean.getTransport());
        tvDetailPound1.setText(businessBean.getGross_Datetime().replace("T", " "));
        tvDetailPound2.setText(businessBean.getTare_Datetime().replace("T", " "));
        tvDetailNet.setText(String.format(Locale.CHINA, "%.01f  %s", businessBean.getNet(), businessBean.getUnit()));
        tvDetailGross.setText(String.format(Locale.CHINA, "%.01f  %s", businessBean.getGross(), businessBean.getUnit()));
        tvDetailTare.setText(String.format(Locale.CHINA, "%.01f  %s", businessBean.getTare(), businessBean.getUnit()));
    }
}
