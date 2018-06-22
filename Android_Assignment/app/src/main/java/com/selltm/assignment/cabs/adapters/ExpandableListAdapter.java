package com.selltm.assignment.cabs.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.selltm.assignment.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Asim on 22/06/18.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context) {
        this._context = context;
    }

    public void setData(List<String> listDataHeader, HashMap<String, List<String>> listChildData) {
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        notifyDataSetChanged();
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (null == convertView) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (null != layoutInflater) {
                convertView = layoutInflater.inflate(R.layout.event_name_expandable_list_item, null, false);
            }
        }

        if(null != convertView) {
            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
            txtListChild.setText(childText);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        if(null != _listDataHeader) {
            return this._listDataHeader.size();
        } else {
            return 0;
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (null == convertView) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (null != layoutInflater) {
                convertView = layoutInflater.inflate(R.layout.event_name_expandable_list_header, null, false);
            }
        }

        TextView lblListHeader = null;
        TextView lblListGroupIndicator = null;

        if (null != convertView) {
            lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListGroupIndicator = (TextView) convertView.findViewById(R.id.lblGroupIndicator);
        }
        if (null != lblListHeader) {
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
        }

        if(null != lblListGroupIndicator) {
            if (isExpanded)
                lblListGroupIndicator.setText("▲");
            else
                lblListGroupIndicator.setText("▼");
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
