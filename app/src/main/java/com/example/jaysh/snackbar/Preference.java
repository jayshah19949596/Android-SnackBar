package com.example.jaysh.snackbar;

/**
 * Created by jaysh on 11/7/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preference {
    public static final String SORT_ORDER = "sortorder";
    public static final String AUTO_PLAY = "autoPlay";
    public static final String VIDEO_CAPTURE = "videoCapture";
    public static final String CURRENT_CURSOR = "currentlist";
    public static final String CURRENT_SELECTED_TAB = "current_tab";
    public static final String CURRENT_SELECTED_VIEW = "current_view_type";
    public static final String CURRENT_FAVOURITE_TYPE = "current_fav_type";
    public static final String CURRENT_ORDERBY_TYPE_LIST = "current_order_type_list";
    public static final String CURRENT_ORDERBY_TYPE_FOLDER = "current_order_type_folder";
    public static final String CURRENT_ORDERBY_TYPE_SELECT_LIST = "current_order_type_selectlist";
    public static final String CURRENT_SORT_ORDER = "current_sort_order";
    public static final String DEFAULT_ORIENTATION = "default_orientation";
    public static final String FOLDER_INDEX = "folder_index";
    public static final String IS_TABLET = "istablet";
    public static final String IS_FIRST_TIME = "isfirsttime";
    public static final String IS_DATABASE_CREATED = "is_db_created";
    public static final String DELETE_MODE = "delete_mode";
    public static final String THEME = "theme";
    public static final String DEFAULT_PLAYER = "default_player";
    public static final String VR_MODE_SETTING = "vr_mode_setting"; //virtual reality mode
    public static final String VR_PLAYBACK_MODE = "vr_playback_mode"; //virtual reality mode
    public static final String SPLIT_WIDTH = "split_width";
    public static final String LAST_PLAYED_ITEM = "lastPlayedItem";
    public static final String LAST_PLAYED_ITEM_OF_AIA = "lastPlayedItemOfAIA";
    public static final String FIRST_VISIBLE_ITEM = "firstVisibleItem";
    public static final String COLOUR_TONE = "colortone";
    public static final String OUTDOOR_VISIBILITY = "outdoorvisibility";
    public static final String SCREEN_MODE = "screen_mode";
    public static final String SUBTITLE_ACTIVATION = "subtitle_activation";
    public static final String SUBTITLE_FONT_SIZE = "subtitle_font_size";
    public static final String SUBTITLE_SYNC_TIME = "subtitle_synctime";
    public static final String SOUND_EFFECT = "sound_effect";
    public static final String IS_AUTO_BRIGHTNESS = "Is_Auto_Brightness";
    public static final String BRIGHTNESS_LEVEL = "Brightness_Level";
    public static final String VIEW_BY_OPTIONS_IN_CLOUD = "list_view_by_cloud";
    public static final String IS_FIRST_TIME_DROPBOX = "is_first_time_without_dropbox_account";
    public static final String SHOW_SBEAM_POPUP = "showsbeampopup";
    public static final String SHOW_SWITCH_STATE = "showsswitchstate_contextual";
    public static final String CHECK_STATUS = "showscontextualcheckstatus";
    public static final String TEXT_LOCATION_STATUS = "showscontextuallocationstatus";
    public static final String TEXT_DATE_STATUS = "showscontextualdatestatus";
    public static final String TEXT_WEATHER_STATUS = "showscontextualweatherstatus";
    public static final String PLAYER_BRIGHTNESS_LEVEL = "playerbrightnesslevel";
    public static final String PLAYER_VOLUME_LEVEL = "playervolumelevel";
    public static final String IS_PLAYER_VISITED = "isplayervisited";
    public static final String IS_POPUP_PLAY_VISITED = "ispopupplayvisited";
    public static final String IS_LIST_VISITED = "islistvisited";
    public static final String IS_SETTINGS_VISITED = "issettingsvisited";
    public static final String IS_REVIEWED = "isreviewed";
    public static final String IS_REMIND_LATER = "isremindlater";
    public static final String LIST_VISIT_COUNT = "listvisitcount";
    public static final String SUB_SCENE_COUNT = "subscenevisitcount";
    public static final String PLAYER_VISIT_COUNT = "playervisitcount";
    public static final String PLAYER_LAUNCH_FROM = "playerlaunchfrom";
    public static final String CHECK_FOR_NAVIGATIONBAR_EXIST = "check_for_navigationbar";
    public static final String IS_NAVIGATIONBAR_EXIST = "has_navigationbar";
    private static final String TAG = "SharedPreferenceManager";
    private static final String APPPREFS = "SnackBarSharedPreferences";
    private static Context mContext;
    private static Preference mUniqueInstance = null;
    private int mode = Context.MODE_PRIVATE;

    private Preference() {
    }

    public static Preference getInstance(Context context) {
        if (mUniqueInstance == null)
            mUniqueInstance = new Preference();

        mContext = context;
        return mUniqueInstance;

    }


    /**
     * save keyString state call from VideoList
     *
     * @param key changed view by sate
     */
    public void saveState(String keyString, int key) {
        try {

            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            SharedPreferences.Editor editor = pref.edit();
            editor.remove(keyString);
            editor.putInt(keyString, key);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } catch (OutOfMemoryError oome) {
            Log.e(TAG, "saveState" + oome.toString());
        }
    }

    /**
     * save keyString state call from VideoList
     *
     * @param key changed view by sate
     */
    public void saveState(String keyString, String key) {
        try {
            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            SharedPreferences.Editor editor = pref.edit();
            editor.remove(keyString);
            editor.putString(keyString, key);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } catch (OutOfMemoryError oome) {
            Log.e(TAG, "saveState" + oome.toString());
        }
    }

    public void saveState(String keyString, long key) {
        try {

            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            SharedPreferences.Editor editor = pref.edit();
            editor.remove(keyString);
            editor.putLong(keyString, key);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } catch (OutOfMemoryError oome) {
            Log.e(TAG, "saveState" + oome.toString());
        }

    }

    public void saveState(String keyString, boolean key) {
        try {

            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            SharedPreferences.Editor editor = pref.edit();
            editor.remove(keyString);
            editor.putBoolean(keyString, key);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } catch (OutOfMemoryError oome) {
            Log.e(TAG, "saveState" + oome.toString());
        }

    }

    /**
     * load keyString state
     *
     * @return state value
     */
    public String loadStringKey(String keyString) {
        String key = "";
        try {
            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            key = pref.getString(keyString, "");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return key;
    }

    public int loadIntKey(String keyString, int returnValue) {
        int key = 0;
        try {
            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            key = pref.getInt(keyString, returnValue);
        } catch (Exception e) {
            key = returnValue;
            Log.e(TAG, e.toString());
        }
        return key;
    }

    public long loadLongKey(String keyString, long returnValue) {
        try {
            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            returnValue = pref.getLong(keyString, returnValue);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return returnValue;
    }

    public boolean loadBooleanKey(String keyString, boolean returnValue) {
        try {
            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            returnValue = pref.getBoolean(keyString, returnValue);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return returnValue;
    }

    public String loadSettingsStringKey(String keyString, String returnValue) {
        try {
            SharedPreferences pref = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(mContext);
            returnValue = pref.getString(keyString, returnValue);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return returnValue;
    }

    public int loadSettingsIntKey(String keyString, int returnValue) {
        try {
            SharedPreferences pref = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(mContext);
            returnValue = pref.getInt(keyString, returnValue);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return returnValue;
    }

    public long loadSettingsLongKey(String keyString, long returnValue) {
        try {
            SharedPreferences pref = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(mContext);
            returnValue = pref.getLong(keyString, returnValue);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return returnValue;
    }

    public boolean loadSettingsBooleanKey(String keyString, boolean returnValue) {
        try {
            SharedPreferences pref = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(mContext);
            returnValue = pref.getBoolean(keyString, returnValue);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return returnValue;
    }


}

