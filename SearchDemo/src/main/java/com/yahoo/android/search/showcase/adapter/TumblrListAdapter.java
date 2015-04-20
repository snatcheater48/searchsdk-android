package com.yahoo.android.search.showcase.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yahoo.android.search.showcase.R;
import com.yahoo.android.search.showcase.data.TumblrData;

import java.util.List;

public class TumblrListAdapter extends ArrayAdapter<TumblrData> {

    private Context mContext;
    private List<TumblrData> tumblrDataList;

    public TumblrListAdapter(Context context, int resource, List<TumblrData> objects) {
        super(context, resource, objects);
        mContext = context;
        tumblrDataList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tumblr_list_item, parent, false);
            viewHolder = new ViewHolder();
            ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.t_photo);
            TextView tvCaption = (TextView) convertView.findViewById(R.id.t_caption);
            TextView tvBlogName = (TextView) convertView.findViewById(R.id.t_blogname);
            TextView tvTags = (TextView) convertView.findViewById(R.id.t_tags);

            viewHolder.tPhoto = ivPhoto;
            viewHolder.tCaption = tvCaption;
            viewHolder.tBlogname = tvBlogName;
            viewHolder.tTags = tvTags;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tPhoto.setImageBitmap(null);
        }
        viewHolder.tBlogname.setText(tumblrDataList.get(position).getBlog_name());
        viewHolder.tCaption.setText(Html.fromHtml(tumblrDataList.get(position).getCaption()));
        viewHolder.tBlogname.setText(tumblrDataList.get(position).getBlog_name());
        viewHolder.tTags.setText(tumblrDataList.get(position).getHashTagSeparatedTagList());
        ImageLoader.getInstance().displayImage(tumblrDataList.get(position).getPhotoUrl(), viewHolder.tPhoto);

        return convertView;
    }

    private static class ViewHolder {
        public TextView tCaption;
        public TextView tBlogname;
        public TextView tTags;
        public ImageView tPhoto;

    }

}
