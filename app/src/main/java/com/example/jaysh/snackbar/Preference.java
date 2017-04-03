package com.example.jaysh.snackbar;

/**
 * Created by jaysh on 11/7/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Preference {
    public static final String IS_FIRST_TIME = "isfirsttime";
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

    public boolean loadBooleanKey(String keyString, boolean returnValue) {
        try {
            SharedPreferences pref = mContext.getSharedPreferences(APPPREFS, mode);
            returnValue = pref.getBoolean(keyString, returnValue);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return returnValue;
    }



}

