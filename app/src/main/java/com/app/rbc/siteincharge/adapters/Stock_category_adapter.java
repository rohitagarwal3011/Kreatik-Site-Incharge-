package com.app.rbc.siteincharge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.rbc.siteincharge.R;
import com.app.rbc.siteincharge.activities.RequirementActivity;
import com.app.rbc.siteincharge.activities.StockActivity;
import com.app.rbc.siteincharge.fragments.Stock_categories;
import com.app.rbc.siteincharge.models.StockCategories;

import java.util.List;

/**
 * Created by rohit on 15/7/17.
 */

public class Stock_category_adapter  extends RecyclerView.Adapter<Stock_category_adapter.MyViewHolder> {


    private List<StockCategories.CategoryList> data;
    private Context context;
    private String source_activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button category_icon;
        TextView category_name;

        public MyViewHolder(View view) {
            super(view);
            category_name=(TextView)view.findViewById(R.id.category_name);
            category_icon= (Button) view.findViewById(R.id.category_icon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(source_activity.equalsIgnoreCase("StockActivity")) {
                        final Stock_categories info = (Stock_categories) ((StockActivity) context).getSupportFragmentManager().findFragmentByTag(Stock_categories.TAG);
                        info.set_product_type(data.get(getAdapterPosition()).getCategory());
                    }
                    else {
                        final Stock_categories info = (Stock_categories) ((RequirementActivity) context).getSupportFragmentManager().findFragmentByTag(Stock_categories.TAG);
                        info.set_product_type(data.get(getAdapterPosition()).getCategory());
                    }

                }
            });

        }
    }


    public Stock_category_adapter(List<StockCategories.CategoryList> data , Context context ,String source_activity ) {
        this.data = data; this.context = context;this.source_activity =source_activity;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_category_list ,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.category_name.setText(data.get(position).getCategory());
        holder.category_icon.setText(data.get(position).getCategory().substring(0,1));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
