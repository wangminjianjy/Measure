package cn.com.food.measure.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.food.measure.R;
import cn.com.food.measure.bean.BackupBean;

/**
 * Created by wangm on 2018/11/22.
 */

public class BackupAdapter extends BaseAdapter {

    private Context mContext;
    private List<BackupBean> backupList;

    public BackupAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<BackupBean> backupList) {
        this.backupList = backupList;
    }

    public void clearData() {
        if (backupList != null) {
            backupList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return backupList == null ? 0 : backupList.size();
    }

    @Override
    public Object getItem(int position) {
        return backupList == null ? null : backupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater _LayoutInflater = LayoutInflater.from(mContext);
            convertView = _LayoutInflater.inflate(R.layout.item_backup, null);
            viewHolder = new ViewHolder();
            viewHolder.backupName = convertView.findViewById(R.id.tv_backup_name);
            viewHolder.backupDate = convertView.findViewById(R.id.tv_backup_date);
            viewHolder.backupState = convertView.findViewById(R.id.tv_backup_state);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BackupBean backupBean = backupList.get(position);
        viewHolder.backupName.setText(backupBean.getBackupName());
        viewHolder.backupDate.setText(backupBean.getBackupDate());
        viewHolder.backupState.setText(backupBean.getBackupState());
        if (backupBean.getBackupState().equals(mContext.getString(R.string.backup_no))) {
            viewHolder.backupState.setTextColor(mContext.getResources().getColor(R.color.colorAlarm));
        } else if (backupBean.getBackupState().equals(mContext.getString(R.string.backup_on))) {
            viewHolder.backupState.setTextColor(mContext.getResources().getColor(R.color.colorNormal));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView backupName;
        TextView backupDate;
        TextView backupState;
    }
}
