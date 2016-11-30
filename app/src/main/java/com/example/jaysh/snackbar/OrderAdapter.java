package com.example.jaysh.snackbar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jaysh on 11/22/2016.
 */
public class OrderAdapter extends ArrayAdapter<String> implements Serializable {
    String[] mCartList;
    Context context;

    public OrderAdapter(Context context, int textViewResourceId, String[] cartItem) {

        super(context, textViewResourceId, cartItem);
        this.context = context;
        Log.d("OrderAdapter"," OrderAdpater Constructor");






        mCartList = cartItem;
        Log.d("OrderAdapter"," Length of String"+ mCartList.length);


        Log.d("OrderAdapter",String.valueOf(cartItem));
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        ViewHolder viewHolder;
        viewHolder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.order_description_list_view, parent, false);
            viewHolder.tvDishName =(TextView) view.findViewById(R.id.textDNValue);

            viewHolder.txtQuantityOfItem = (TextView) view.findViewById(R.id.textViewQauntityValue);
            //TextView tvDishName = (TextView) view.findViewById(R.id.textDishName);
            //TextView tvDishPrice = (TextView) view.findViewById(R.id.textDishPrice);
            //TextView tvDishName = (TextView) view.findViewById(R.id.textDishN);
            //TextView tvDishName1 = (TextView) view.findViewById(R.id.textDishName);

            view.setTag(viewHolder);

        }


        //view = inflater.inflate(R.layout.cart_list_view, null);

        //viewHolder.tvDishName =(TextView) view.findViewById(R.id.textDishName);
        //viewHolder.tvDishPrice =(TextView) view.findViewById(R.id.textDishPrice);

        //TextView tvDishName = (TextView) view.findViewById(R.id.textDishName);
        //TextView tvDishPrice = (TextView) view.findViewById(R.id.textDishPrice);
        //TextView tvDishName = (TextView) view.findViewById(R.id.textDishN);
        //TextView tvDishName1 = (TextView) view.findViewById(R.id.textDishName);

        //view.setTag(viewHolder);

        viewHolder = (ViewHolder) view.getTag();
        Log.d("OrderAdapter"," position bfore"+ position);


        viewHolder.tvDishName.setText(OrderScreen.itemname[position]);

        Log.d("OrderAdapter"," position after"+ position);
        Log.d("OrderAdapter"," position after"+ OrderScreen.itemquantity[position]);

        viewHolder.txtQuantityOfItem.setText(OrderScreen.itemquantity[position]);


        //Log.d("CartADpater",(mCartList.get(position).getDishPrice().toString()));

        /*viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ViewHolder holder = (ViewHolder)v.getTag();
                holder.txtQuantityOfItem = (TextView) v.findViewById(R.id.textQuantityOfItem);
                //Access the Textview from holder1 like below
                holder.txtQuantityOfItem.setText("50");

            }
        });
*/


        return view;

    }

    class ViewHolder {
        TextView tvDishName;
        TextView tvDishPrice,txtQuantityOfItem;
        Button btnminus;

    }

}
