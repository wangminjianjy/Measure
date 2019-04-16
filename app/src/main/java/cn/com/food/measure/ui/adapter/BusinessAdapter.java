package cn.com.food.measure.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.food.measure.R;
import cn.com.food.measure.bean.BusinessBean;

/**
 * Created by wangm on 2018/11/22.
 */

public class BusinessAdapter extends BaseAdapter {

    private Context mContext;
    private List<BusinessBean> businessList;

    public BusinessAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<BusinessBean> businessList) {
        this.businessList = businessList;
    }

    public void clearData() {
        if (businessList != null) {
            businessList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return businessList == null ? 0 : businessList.size();
    }

    @Override
    public Object getItem(int position) {
        return businessList == null ? null : businessList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater _LayoutInflater = LayoutInflater.from(mContext);
            convertView = _LayoutInflater.inflate(R.layout.item_business, null);
            viewHolder = new ViewHolder();
            viewHolder.businessNo = convertView.findViewById(R.id.tv_business_no);
            viewHolder.businessMaterial = convertView.findViewById(R.id.tv_business_material);
            viewHolder.businessCompany = convertView.findViewById(R.id.tv_business_company);
            viewHolder.businessTruck = convertView.findViewById(R.id.tv_business_truck);
            viewHolder.businessDatePound1 = convertView.findViewById(R.id.tv_business_date_pound_one);
            viewHolder.businessDatePound2 = convertView.findViewById(R.id.tv_business_date_pound_two);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BusinessBean businessBean = businessList.get(position);
        viewHolder.businessNo.setText(businessBean.getJjh());
        viewHolder.businessMaterial.setText(businessBean.getGoods());
        viewHolder.businessCompany.setText(businessBean.getTransport());
        viewHolder.businessTruck.setText(businessBean.getTruckno());
        viewHolder.businessDatePound1.setText(businessBean.getGross_Datetime());
        viewHolder.businessDatePound2.setText(businessBean.getTare_Datetime());

        return convertView;
    }

    static class ViewHolder {
        TextView businessNo;
        TextView businessMaterial;
        TextView businessCompany;
        TextView businessTruck;
        TextView businessDatePound1;
        TextView businessDatePound2;
    }
}
