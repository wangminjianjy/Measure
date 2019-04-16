package cn.com.food.measure.ui.backup;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.com.food.measure.R;
import cn.com.food.measure.base.BaseActivity;
import cn.com.food.measure.base.BaseConfig;
import cn.com.food.measure.bean.BackupBean;
import cn.com.food.measure.bean.BusinessBean;
import cn.com.food.measure.net.HttpCallback;
import cn.com.food.measure.net.HttpModel;
import cn.com.food.measure.net.HttpResult;
import cn.com.food.measure.ui.adapter.BackupAdapter;
import cn.com.food.measure.util.DateUtil;
import cn.com.food.measure.util.ExcelUtil;
import cn.com.food.measure.util.SharedPreUtil;

public class BackupActivity extends BaseActivity {

    private TextView tvBackupBack;
    private TextView tvBackupTitle;
    private TextView backupManual;
    private TextView backupNull;
    private ListView lvBackup;

    private File file;

    private List<BackupBean> backupList;
    private List<List<String>> businessList;
    private BackupAdapter backupAdapter;

    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int dateMonth;

    @Override
    protected Settings setActivitySettings(View contentView) {
        return new Settings();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_backup;
    }

    @Override
    protected void initView(View contentView) {
        tvBackupBack = contentView.findViewById(R.id.custom_back);
        tvBackupTitle = contentView.findViewById(R.id.custom_title);
        backupManual = contentView.findViewById(R.id.tv_backup_manual);
        backupNull = contentView.findViewById(R.id.tv_backup_null);
        lvBackup = contentView.findViewById(R.id.lv_backup_data);
    }

    @Override
    protected void bindEvent(View contentView) {
        tvBackupBack.setOnClickListener(this);
        backupManual.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvBackupTitle.setText(getString(R.string.backup_title));
        backupNull.setVisibility(View.GONE);

        file = new File(BaseConfig.FILE_BASE);

        backupList = new ArrayList<>();
        businessList = new ArrayList<>();
        backupAdapter = new BackupAdapter(this);
        lvBackup.setAdapter(backupAdapter);

        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        getBackupData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_back:
                finish();
                break;
            case R.id.tv_backup_manual:
                BackupData();
                break;
            default:
                break;
        }
    }

    private void getBackupData() {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length != 0) {
                for (int i = 0; i < files.length; i++) {
                    BackupBean backupBean = new BackupBean();
                    backupBean.setBackupName(files[i].getName());
                    long time = files[i].lastModified();    // 返回文件最后修改时间，是以个long型毫秒数
                    String cTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA).format(new Date(time));
                    backupBean.setBackupDate(cTime);
                    backupBean.setBackupState(getString(R.string.backup_down));
                    backupList.add(backupBean);
                }
                long time = files[files.length - 1].lastModified();
                String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA).format(new Date(time));
                String[] dateMonth = dateStr.split("-");
                SharedPreUtil.saveBackup(Integer.parseInt(dateMonth[1]));
//                if (currentMonth > files.length) {
//                    for (int j = files.length; j < currentMonth; j++) {
//                        BackupBean backupBean = new BackupBean();
//                        backupBean.setBackupName(String.format(Locale.CHINA, "%d%02d%s", currentYear, j + 1, BaseConfig.FILE_NAME));
//                        backupBean.setBackupDate(getString(R.string.backup_date_null));
//                        backupBean.setBackupState(getString(R.string.backup_no));
//                        backupList.add(backupBean);
//                    }
//                }
                backupAdapter.setData(backupList);
                backupAdapter.notifyDataSetChanged();
            }
        }
    }

    private void BackupData() {
        if (!file.exists()) {
            file.mkdir();
        }
        if (SharedPreUtil.getBackup() == 1) {
            dateMonth = 1;
        } else {
            dateMonth = SharedPreUtil.getBackup();
        }
        businessList.clear();
        String fileName = String.format(Locale.CHINA, "%s%d%02d%s", BaseConfig.FILE_BASE, currentYear, dateMonth, BaseConfig.FILE_NAME);
        File excelFile = new File(fileName);
        if (excelFile.exists()) {
            excelFile.delete();
        }
        try {
            excelFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateBackup();
//        CreateBack();
    }

    private void CreateBack() {
        dateMonth = 11;
        if (dateMonth == 11) {
            businessList.clear();
            for (int i = 0; i < 100; i++) {
                List<String> list = new ArrayList<>();
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                list.add("100" + i);
                businessList.add(list);
            }
            List<String> list1 = new ArrayList<>();
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            list1.add("200");
            List<String> list2 = new ArrayList<>();
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            list2.add("300");
            businessList.add(list1);
            businessList.add(list2);

            String fileName = String.format(Locale.CHINA, "%s%d%02d%s", BaseConfig.FILE_BASE, currentYear, dateMonth, BaseConfig.FILE_NAME);
            File excelFile = new File(fileName);
            if (excelFile.exists()) {
                excelFile.delete();
            }
            try {
                excelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] title = {"主键", "业务号", "车号", "一磅时间", "二磅事件", "物料", "毛重", "皮重", "净重", "收货方", "发货方", "运输方", "物料单位", "备注"};
            ExcelUtil.initExcel(fileName, title);
            ExcelUtil.writeObjListToExcel(businessList, fileName);

            backupList.clear();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
            Date date = new Date(System.currentTimeMillis());
            String currentDateStr = simpleDateFormat.format(date);

            BackupBean backupBean = new BackupBean();
            backupBean.setBackupName(excelFile.getName());
            backupBean.setBackupDate(currentDateStr);
            backupBean.setBackupState(getString(R.string.backup_down));

            backupList.add(backupBean);
            backupAdapter.setData(backupList);
            backupAdapter.notifyDataSetChanged();
        }
    }

    private void CreateBackup() {
        final String dateStart = currentYear + "-" + dateMonth + "01 00:00:00";
        String dateEnd = currentYear + "-" + dateMonth + DateUtil.getDays(currentYear, dateMonth) + " 23:59:59";
        SharedPreUtil.saveBackup(dateMonth);
        HttpModel.getInstance().getBusinessData(dateStart, dateEnd, "", "", new HttpCallback(this) {
            @Override
            public void onSuccessStr(HttpResult httpResult) {
                try {
                    if (httpResult.getData().size() != 0) {
                        for (int i = 0; i < httpResult.getData().size(); i++) {
                            Gson gson = new Gson();
                            BusinessBean businessBean = gson.fromJson(gson.toJson(httpResult.getData().get(i)), BusinessBean.class);
                            List<String> list = new ArrayList<>();
                            list.add(String.format(Locale.CHINA, "%d", businessBean.getId()));
                            list.add(businessBean.getJjh());
                            list.add(businessBean.getTruckno());
                            list.add(businessBean.getGross_Datetime());
                            list.add(businessBean.getTare_Datetime());
                            list.add(businessBean.getGoods());
                            list.add(String.format(Locale.CHINA, "%.02f", businessBean.getGross()));
                            list.add(String.format(Locale.CHINA, "%.02f", businessBean.getTare()));
                            list.add(String.format(Locale.CHINA, "%.02f", businessBean.getNet()));
                            list.add(businessBean.getSender());
                            list.add(businessBean.getReceiver());
                            list.add(businessBean.getTransport());
                            list.add(businessBean.getUnit());
                            list.add(businessBean.getNote1());
                            businessList.add(list);
                        }
                        String fileName = String.format(Locale.CHINA, "%s%d%02d%s", BaseConfig.FILE_BASE, currentYear, dateMonth, BaseConfig.FILE_NAME);
                        File excelFile = new File(fileName);
                        if (excelFile.exists()) {
                            excelFile.delete();
                        }
                        excelFile.createNewFile();

                        String[] title = {"主键", "业务号", "车号", "一磅时间", "二磅事件", "物料", "毛重", "皮重", "净重", "收货方", "发货方", "运输方", "物料单位", "备注"};
                        ExcelUtil.initExcel(fileName, title);
                        ExcelUtil.writeObjListToExcel(businessList, fileName);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
                        Date date = new Date(System.currentTimeMillis());
                        String currentDateStr = simpleDateFormat.format(date);

                        boolean backupFlag = false;
                        for (int i = 0; i < backupList.size(); i++) {
                            if (backupList.get(i).getBackupName().equals(excelFile.getName())) {
                                backupList.get(i).setBackupState(getString(R.string.backup_down));
                                backupList.get(i).setBackupDate(currentDateStr);
                                backupFlag = true;
                                break;
                            }
                        }
                        if (!backupFlag) {
                            BackupBean backupBean = new BackupBean();
                            backupBean.setBackupName(excelFile.getName());
                            backupBean.setBackupDate(currentDateStr);
                            backupBean.setBackupState(getString(R.string.backup_down));
                            backupList.add(backupBean);
                        }
                        backupAdapter.setData(backupList);
                        backupAdapter.notifyDataSetChanged();
                    }
                    if (dateMonth < currentMonth) {
                        dateMonth = dateMonth + 1;
                        CreateBackup();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
