package com.yahoo.android.search.showcase.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.yahoo.android.search.showcase.data.TumblrData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static DisplayMetrics displayMetrics = null;

    public static int getScreenWidth(Context c) {
        displayMetrics = getDisplayMetrics(c);
        return displayMetrics.widthPixels;
    }

    public static synchronized DisplayMetrics getDisplayMetrics(Context c) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    public static List<TumblrData> parseTumblrDataList(Context context) {

        List<TumblrData> tumblrDataList = new ArrayList<TumblrData>();
        String tumblrResponse = getTumblrResponse(context);
        try {
            JSONObject tumblrJson = new JSONObject(tumblrResponse);
            JSONArray responseObjects = tumblrJson.getJSONArray("response");

            for (int counter = 0; counter < responseObjects.length(); counter++) {

                JSONObject responseObj = responseObjects.getJSONObject(counter);
                String blogName = responseObj.getString("blog_name");
                String caption = responseObj.getString("caption");
                String post_url = responseObj.getString("post_url");
                JSONArray tags = responseObj.getJSONArray("tags");
                List<String> tagList =  new ArrayList<String>();
                for (int index = 0; index < tags.length(); index++) {
                    tagList.add((String)tags.get(index));
                }
                JSONArray photos = responseObj.getJSONArray("photos");
                String photoUrl = "";
                for (int idx = 0; idx < photos.length(); idx++) {
                    JSONObject item = photos.getJSONObject(idx);
                    JSONArray photoSizes = item.getJSONArray("alt_sizes");
                    JSONObject photoSize = photoSizes.getJSONObject(0);
                    photoUrl = photoSize.getString("url");
                }

                TumblrData tumblrData = new TumblrData(blogName, post_url, tagList, caption, photoUrl);
                tumblrDataList.add(tumblrData);
            }

        } catch (JSONException e) {
            Log.e("Utils", e.getMessage());
        }
        return tumblrDataList;
    }

    /**
     *
     * @param context
     * @return response String
     *
     * Reading response from local file.
     * Developers should be able to make network calls or get the response from any source.
     */

    public static String getTumblrResponse(Context context) {

        String tumblrResponse = null;
        try {
            InputStream is = context.getAssets().open("tumblr.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            tumblrResponse = new String(buffer, "UTF-8");
        }
        catch (Exception e) {
            Log.e("Utils", e.getMessage());
        }
        return tumblrResponse.toString();
    }
}
