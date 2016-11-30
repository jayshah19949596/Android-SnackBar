package com.example.jaysh.snackbar;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jaysh on 11/21/2016.
 */
public class FoodItem extends CursorAdapter {

    com.example.jaysh.snackbar.DBMgr DBMgr;
    private Cursor mCursor;
    private Context mContext;
    public static int currentPosition;
    public FoodItem(Context context, Cursor cursor, int flags) {
        super(context, cursor, ResourceCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mContext = context;
        mCursor = cursor;

        Log.d("FoodItem","In the constructor ");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("FoodItem","getView() - pos: " + position);
        if (!mCursor.moveToPosition(position)) {
            Log.d("FoodItem", "Can't move to position: " + position);
            return null;
        }

        if (convertView == null) {
            convertView = newView(mContext, mCursor, null);
        }
        bindView(convertView,mContext, mCursor);
        currentPosition = position;
        //convertView.setOnClickListener(new OnItemClickListener(position));
        return convertView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.menu_list_view, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.dishName = (TextView) rowView.findViewById(R.id.tvdishName);
        holder.dishDescription = (TextView) rowView.findViewById(R.id.tvdishDescription);
        holder.dishPrice = (TextView) rowView.findViewById(R.id.tvdishPrice);
        holder.dishCuisinenType = (TextView) rowView.findViewById(R.id.tvcuisinenType);
        holder.dishCheckBox = (CheckBox) rowView.findViewById(R.id.dishCB);
        rowView.setTag(holder);

        Log.d("FoodItem","In newView ");


        Log.d("FoodItem","Description Value : /n"+cursor.getString(cursor.getColumnIndex("description")));
        return rowView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        //TextView tvdishName = (TextView) view.findViewById(R.id.tvdishName);
        //TextView tvdishDescription = (TextView) view.findViewById(R.id.tvdishDescription);
        //TextView tvdishPrice = (TextView) view.findViewById(R.id.tvdishPrice);
        //TextView tvcuisinenType = (TextView) view.findViewById(R.id.tvcuisinenType);

        //String dishDesciption = cursor.getString(cursor.getColumnIndex(DBMgr.MENU_DESCRIPTION));
        //String cuisineType = cursor.getString(cursor.getColumnIndex(DBMgr.MENU_CUISINETYPE));
        //String dishPrice = cursor.getString(cursor.getColumnIndex(DBMgr.MENU_PRICE));
        //String dishName = cursor.getString(cursor.getColumnIndex(DBMgr.MENU_DISHES));


        //tvdishName.setText(dishName);
        //tvdishDescription.setText(dishDesciption);
        //tvdishPrice.setText("$"+dishPrice);
        //tvcuisinenType.setText(cuisineType);

        holder.dishName.setText(cursor.getString(cursor.getColumnIndex(DBMgr.MENU_DISHES)));
        holder.dishDescription.setText(cursor.getString(cursor.getColumnIndex(DBMgr.MENU_DESCRIPTION)));
        holder.dishPrice.setText(cursor.getString(cursor.getColumnIndex(DBMgr.MENU_PRICE)));
        holder.dishCuisinenType.setText(cursor.getString(cursor.getColumnIndex(DBMgr.MENU_CUISINETYPE)));

        ArrayList<String> position_array_list = new ArrayList<String>();

        Log.d("FoodItem","Value of Dishname : " +holder.dishName.getText());

        Log.d("FoodItem","Value of Dishname : " +String.valueOf(MenuActivity.selectedDishes));



        if(MenuActivity.selectedDishes.contains(holder.dishName.getText()))
        {
            Log.d("FoodItem","Dishname is present");

            //position_array_list.add(currentPosition);
            holder.dishCheckBox.setChecked(true);
        }

        else
        {
            Log.d("FoodItem","Dishname not present");

            holder.dishCheckBox.setChecked(false);
        }


    }


    @Override
    public int getCount() {
        Log.d("FoodItem","getCount(): " + (mCursor == null || mCursor.isClosed() ? 0 : mCursor.getCount()));
        return mCursor == null || mCursor.isClosed() ? 0 : mCursor.getCount();
    }

    class ViewHolder {
        TextView dishName, dishDescription,dishPrice,dishCuisinenType;
        CheckBox dishCheckBox;
    }

}
