package cn.com.food.measure.ui.business;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.com.food.measure.R;
import cn.com.food.measure.base.BaseActivity;
import cn.com.food.measure.base.BaseConfig;
import cn.com.food.measure.bean.BusinessBean;
import cn.com.food.measure.net.HttpCallback;
import cn.com.food.measure.net.HttpModel;
import cn.com.food.measure.net.HttpResult;
import cn.com.food.measure.ui.adapter.BusinessAdapter;
import cn.com.food.measure.util.ToastUtil;
import cn.com.food.measure.util.WindowUtil;

public class BusinessActivity extends BaseActivity {

    private TextView tvBusinessBack;
    private TextView tvBusinessTitle;
    private TextView tvBusinessDataNull;
    private ListView lvBusiness;
    private View llBusinessSearch;
    private TextView tvSearchDateStart;
    private TextView tvSearchDateEnd;
    private EditText tvSearchNo;
    private EditText tvSearchTruck;
    private TextView tvSearchCancel;
    private TextView tvSearchSubmit;

    private List<BusinessBean> businessList;
    private BusinessAdapter businessAdapter;

    private PopupWindow mPopupWindow;
    private TimePickerView materialDatePicker;

    private int alertDateFlag = BaseConfig.REQUEST_CODE_FIRST;
    private Date dateStart, dateEnd;

    @Override
    protected Settings setActivitySettings(View contentView) {
        return new Settings();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_business;
    }

    @Override
    protected void initView(View contentView) {
        tvBusinessBack = contentView.findViewById(R.id.custom_back);
        tvBusinessTitle = contentView.findViewById(R.id.custom_title);
        tvBusinessDataNull = contentView.findViewById(R.id.tv_business_data_null);
        lvBusiness = contentView.findViewById(R.id.lv_business_data);
        llBusinessSearch = contentView.findViewById(R.id.ll_search);
    }

    @Override
    protected void bindEvent(View contentView) {
        tvBusinessBack.setOnClickListener(this);
        llBusinessSearch.setOnClickListener(this);
        lvBusiness.setOnItemClickListener(onItemClick);
    }

    @Override
    protected void initData() {
        tvBusinessTitle.setText(getString(R.string.business_title));

        businessList = new ArrayList<>();
        businessAdapter = new BusinessAdapter(this);
        lvBusiness.setAdapter(businessAdapter);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        Date date = new Date(System.currentTimeMillis());
//        String currentDateStr = simpleDateFormat.format(date);
//        String startDate = currentDateStr + " 00:00:00";
//        String endDate = currentDateStr + " 23:59:59";
        getBusinessData("", "", "", "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_back:
                finish();
                break;
            case R.id.ll_search:
                showSearchPopup();
                break;
            case R.id.tv_business_search_date_start:
                alertDateFlag = BaseConfig.REQUEST_CODE_FIRST;
                showAlertDate();
                break;
            case R.id.tv_business_search_date_end:
                alertDateFlag = BaseConfig.REQUEST_CODE_SECOND;
                showAlertDate();
                break;
            case R.id.tv_business_search_cancel:
                mPopupWindow.dismiss();
                break;
            case R.id.tv_business_search_submit:
                checkSelect();
                break;
            default:
                break;
        }
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(BusinessActivity.this, BusinessDetailActivity.class);
            intent.putExtra(BaseConfig.PARAM_BUSINESS_DETAIL, businessList.get(position));
            startActivity(intent);
        }
    };

    private void getBusinessData(String dateStart, String dateEnd, String businessNo, String truckNo) {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        HttpModel.getInstance().getBusinessData(dateStart, dateEnd, businessNo, truckNo, new HttpCallback(this) {
            @Override
            public void onSuccessStr(HttpResult httpResult) {
                try {
                    businessList.clear();
                    if (httpResult.getData().size() == 0) {
                        tvBusinessDataNull.setVisibility(View.VISIBLE);
                    } else {
                        for (int i = 0; i < httpResult.getData().size(); i++) {
                            Gson gson = new Gson();
                            BusinessBean businessBean = gson.fromJson(gson.toJson(httpResult.getData().get(i)), BusinessBean.class);
                            businessList.add(businessBean);
                        }
                        tvBusinessDataNull.setVisibility(View.GONE);
                        businessAdapter.setData(businessList);
                        businessAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showSearchPopup() {
        if (mPopupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.popup_window_search, null);
            mPopupWindow = new PopupWindow(this);
            mPopupWindow.setAnimationStyle(R.style.style_anim_pop_bottom);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setWidth(WindowUtil.getWindowWidth(getApplicationContext()));
            mPopupWindow.setContentView(view);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.update();
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowUtil.windowDark(BusinessActivity.this, 1.0f);
                }
            });

            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            tvSearchDateStart = view.findViewById(R.id.tv_business_search_date_start);
            tvSearchDateEnd = view.findViewById(R.id.tv_business_search_date_end);
            tvSearchNo = view.findViewById(R.id.et_business_search_business);
            tvSearchTruck = view.findViewById(R.id.et_business_search_truck);
            tvSearchCancel = view.findViewById(R.id.tv_business_search_cancel);
            tvSearchSubmit = view.findViewById(R.id.tv_business_search_submit);

            tvSearchDateStart.setOnClickListener(this);
            tvSearchDateEnd.setOnClickListener(this);
            tvSearchCancel.setOnClickListener(this);
            tvSearchSubmit.setOnClickListener(this);
        }
        WindowUtil.windowDark(this, 0.3f);
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    private void showAlertDate() {
        if (materialDatePicker == null) {
            materialDatePicker = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    String dateStr = String.format(Locale.CHINA, "%d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
                    switch (alertDateFlag) {
                        case BaseConfig.REQUEST_CODE_FIRST:
                            if (dateEnd != null) {
                                if (compareDate(date, dateEnd) > 0) {
                                    ToastUtil.showToast("开始时间应小于等于结束时间", Toast.LENGTH_SHORT);
                                } else {
                                    dateStart = date;
                                    tvSearchDateStart.setText(dateStr);
                                }
                            } else {
                                dateStart = date;
                                tvSearchDateStart.setText(dateStr);
                            }
                            break;
                        case BaseConfig.REQUEST_CODE_SECOND:
                            if (dateStart != null) {
                                if (compareDate(dateStart, date) > 0) {
                                    ToastUtil.showToast("结束时间应大于等于开始时间", Toast.LENGTH_SHORT);
                                } else {
                                    dateEnd = date;
                                    tvSearchDateEnd.setText(dateStr);
                                }
                            } else {
                                dateEnd = date;
                                tvSearchDateEnd.setText(dateStr);
                            }
                            break;
                    }
                }
            })
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setCancelText(getString(R.string.btn_cancel))
                    .setSubmitText(getString(R.string.btn_submit))
                    .setTitleText(getString(R.string.business_search_date))
                    .setOutSideCancelable(false)
                    .setDate(Calendar.getInstance())
                    .isCenterLabel(false)
                    .isDialog(true)
                    .build();
        } else {
            materialDatePicker.setDate(Calendar.getInstance());
        }
        materialDatePicker.show();
    }

    public int compareDate(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        if (calendar1.get(Calendar.YEAR) > calendar2.get(Calendar.YEAR)) {
            return 1;
        } else if (calendar1.get(Calendar.YEAR) < calendar2.get(Calendar.YEAR)) {
            return -1;
        } else {
            if (calendar1.get(Calendar.MONTH) > calendar2.get(Calendar.MONTH)) {
                return 1;
            } else if (calendar1.get(Calendar.MONTH) < calendar2.get(Calendar.MONTH)) {
                return -1;
            } else {
                if (calendar1.get(Calendar.DAY_OF_MONTH) > calendar2.get(Calendar.DAY_OF_MONTH)) {
                    return 1;
                } else if (calendar1.get(Calendar.DAY_OF_MONTH) < calendar2.get(Calendar.DAY_OF_MONTH)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    private void checkSelect() {
        String selectDateStart, selectDateEnd, selectBusiness, selectTruck;
        if (TextUtils.isEmpty(tvSearchDateStart.getText())) {
            ToastUtil.showSingleToast(getString(R.string.business_search_date_start_hint), Toast.LENGTH_SHORT);
        } else if (TextUtils.isEmpty(tvSearchDateEnd.getText())) {
            ToastUtil.showSingleToast(getString(R.string.business_search_date_end_hint), Toast.LENGTH_SHORT);
        } else {
            selectDateStart = tvSearchDateStart.getText().toString() + " 00:00:00";
            selectDateEnd = tvSearchDateEnd.getText().toString() + " 23:59:59";
            if (TextUtils.isEmpty(tvSearchNo.getText())) {
                selectBusiness = "";
            } else {
                selectBusiness = tvSearchNo.getText().toString();
            }
            if (TextUtils.isEmpty(tvSearchTruck.getText())) {
                selectTruck = "";
            } else {
                selectTruck = tvSearchTruck.getText().toString();
            }
            getBusinessData(selectDateStart, selectDateEnd, selectBusiness, selectTruck);
        }
    }
}
