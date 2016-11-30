package com.example.jaysh.snackbar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jaysh on 11/18/2016.
 */


public class CartAdapter extends ArrayAdapter<Cart> implements Serializable {

    ArrayList<Cart> mCartList = new ArrayList<>();
    Context context;
    public CartAdapter(Context context, int textViewResourceId, ArrayList<Cart> cartItem) {

        super(context, textViewResourceId, cartItem);
        this.context = context;
        Log.d("CartADpater"," CartAdpater Constructor");



        mCartList = cartItem;
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
            view = LayoutInflater.from(getContext()).inflate(R.layout.cart_list_view, parent, false);
            viewHolder.tvDishName =(TextView) view.findViewById(R.id.textDishName);
            viewHolder.tvDishPrice =(TextView) view.findViewById(R.id.textDishPrice);
            viewHolder.btnminus = (Button) view.findViewById(R.id.buttonMinus);
            viewHolder.txtQuantityOfItem = (TextView) view.findViewById(R.id.textQuantityOfItem);
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


        viewHolder.tvDishName.setText(mCartList.get(position).getDishName());
        viewHolder.tvDishPrice.setText(mCartList.get(position).getDishPrice());
        viewHolder.txtQuantityOfItem.setText(mCartList.get(position).getDishQuantity());

        Log.d("CartADpater",(mCartList.get(position).getDishPrice().toString()));

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