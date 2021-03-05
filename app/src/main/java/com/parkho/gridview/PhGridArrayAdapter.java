package com.parkho.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class PhGridArrayAdapter extends ArrayAdapter<PhGridItem> {
    private static final int LAYOUT_RESOURCE_ID = R.layout.content_grid_item;

    private Context mContext;
    private List<PhGridItem> mItemList;

    public PhGridArrayAdapter(Context a_context, List<PhGridItem> a_itemList) {
        super(a_context, LAYOUT_RESOURCE_ID, a_itemList);

        mContext = a_context;
        mItemList = a_itemList;
    }

    @Override
    public View getView(int a_position, View a_convertView, ViewGroup a_parent) {
        PhGridItemViewHolder viewHolder;
        if (a_convertView == null) {
            a_convertView = LayoutInflater.from(mContext).inflate(LAYOUT_RESOURCE_ID, a_parent, false);

            viewHolder = new PhGridItemViewHolder(a_convertView);
            a_convertView.setTag(viewHolder);
        } else {
            viewHolder = (PhGridItemViewHolder) a_convertView.getTag();
        }

        final PhGridItem countryItem = mItemList.get(a_position);

        // Icon 설정
        viewHolder.ivIcon.setImageResource(countryItem.getImageResId());

        // Name 설정
        viewHolder.tvName.setText(countryItem.getName());

        return a_convertView;
    }
}