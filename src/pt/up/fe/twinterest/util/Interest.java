package pt.up.fe.twinterest.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Interest {
	private static final String API_URL = "http://search.twitter.com/search.json";
	private static final String API_BASE_REFRESH_URL = "?include_entities=true&q=%s";
	//dummy json since this is depricated twitter api
	/*
	
	{
    "next_page": "?page=2&max_id=111626724697063424&q=python", 
    "completed_in": 0.099000000000000005, 
    "max_id_str": 111626724697063420, 
    "refresh_url": "?since_id=111626724697063424&q=python", 
    "results": [
        {
            "iso_language_code": "en", 
            "text": "#Win Christian Louboutin Sueded Python Pump http://t.co/vr5C6St via @AddThis", 
            "created_at": "Thu, 08 Sep 2011 02:27:39 +0000", 
            "profile_image_url": "http://xbmc.org/wp-content/uploads/2011/06/zappy-welcome.png", 
            "from_user_name": "testingnamesamsun", 
            "from_user": "samsun", 
            "from_user_id": 32455649, 
            "geo": null, 
            "id": 111626724697063420
        }, 
        {
            "iso_language_code": "en", 
            "text": "#Win Christian Louboutin Sueded Python Pump http://t.co/vr5C6St via @AddThis", 
            "created_at": "Thu, 08 Sep 2011 02:27:39 +0000", 
            "profile_image_url": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ17bxuPZB2jqJkz7Lj9N1k1FaXB984VFA5przcwdKg7XEIqOeQ1TcNNNXL", 
            "from_user_name": "testingnamesachin", 
            "from_user": "sachin", 
            "from_user_id": 107, 
            "geo": null, 
            "id": 97
        }, 
        {
            "iso_language_code": "en", 
            "text": "#Win Christian Louboutin Sueded Python Pump http://t.co/vr5C6St via @AddThis", 
            "created_at": "Thu, 08 Sep 2011 02:27:39 +0000", 
            "profile_image_url": "http://www.lbwtrust.com.au/wp-content/uploads/2012/07/rahul_dravid.png", 
            "from_user_name": "testingnamedravid", 
            "from_user": "dravid", 
            "from_user_id": 108, 
            "geo": null, 
            "id": 98
        }, 
        {
            "iso_language_code": "en", 
            "text": "#Win Christian Louboutin Sueded Python Pump http://t.co/vr5C6St via @AddThis", 
            "created_at": "Thu, 08 Sep 2011 02:27:39 +0000", 
            "profile_image_url": "http://www.bharatdiscovery.org/w/images/6/6c/Sourav-Ganguly.png", 
            "from_user_name": "testingnameganguly", 
            "from_user": "ganguly", 
            "from_user_id": 109, 
            "geo": null, 
            "id": 99
        }, 
        {
            "iso_language_code": "en", 
            "text": "#Win Christian Louboutin Sueded Python Pump http://t.co/vr5C6St via @AddThis", 
            "created_at": "Thu, 08 Sep 2011 02:27:39 +0000", 
            "profile_image_url": "http://xbmc.org/wp-content/uploads/2011/06/zappy-welcome.png", 
            "from_user_name": "testingnamerahane", 
            "from_user": "rahane", 
            "from_user_id": 110, 
            "geo": null, 
            "id": 100
        }
    ], 
    "query": "python", 
    "max_id": 111626724697063420, 
    "page": 1
}
	
	*/
	
	private static final String PREF_NAME = "interest";
	
	private static final String PREF_KEY_KEYWORD = "keyword";
	private static final String PREF_KEY_REFRESH_URL = "refresh_url";
	
	private static Interest sInstance;
	
	public static synchronized Interest getInstance(Context context) {
		if(sInstance == null)
			sInstance = new Interest(context);
		
		return sInstance;
	}
	
	private String mKeyword;
	private String mRefreshUrl;
	
	private Interest(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		mKeyword = prefs.getString(PREF_KEY_KEYWORD, null);
		mRefreshUrl = prefs.getString(PREF_KEY_REFRESH_URL, null);
	}
	
	public String getKeyword() {
		return mKeyword;
	}
	
	public String getRefreshUrl() {
		if(mRefreshUrl == null)
			return API_URL + String.format(API_BASE_REFRESH_URL, Uri.encode(mKeyword));
		else
			return API_URL + mRefreshUrl;
	}
	
	public void setKeyword(Context context, String interest) {
		mKeyword = interest;
		mRefreshUrl = null;
		save(context);
	}
	
	public void setRefreshUrl(Context context, String nextPage) {
		mRefreshUrl = nextPage;
		save(context);
	}
	
	private void save(Context context) {
		SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(PREF_KEY_KEYWORD, mKeyword);
		editor.putString(PREF_KEY_REFRESH_URL, mRefreshUrl);
		editor.commit();
	}
}
