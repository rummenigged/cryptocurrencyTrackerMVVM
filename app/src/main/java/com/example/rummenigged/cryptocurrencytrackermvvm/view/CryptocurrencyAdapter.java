package com.example.rummenigged.cryptocurrencytrackermvvm.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.rummenigged.cryptocurrencytrackermvvm.R;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.util.Paths;

import java.util.List;

/**
 * Created by rummenigged on 22/01/18.
 */

public class CryptocurrencyAdapter extends RecyclerView.Adapter<CryptocurrencyAdapter.CryptocurrencyViewHolder> {

    private List<Cryptocurrency> cryptocurrencyList;
    private Context context;

    public CryptocurrencyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CryptocurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CryptocurrencyViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_cryptocurrency, parent, false));
    }

    @Override
    public void onBindViewHolder(CryptocurrencyViewHolder holder, int position) {

        Glide.with(context)
                .load(Paths.ICONS_BASE_URL
                        + cryptocurrencyList
                        .get(position)
                        .getSymbol()
                        .toLowerCase()
                        +".png")
                .into(holder.ivIconCryptocurrency);

        holder.tvSymbol.setText(cryptocurrencyList.get(position).getSymbol() + " | ");
        holder.tvName.setText(cryptocurrencyList.get(position).getName());
        holder.tvPrice.setText(String.valueOf(cryptocurrencyList.get(position).getPrice()) + "$");
        holder.tvPercentChange24h.setText(String.valueOf(cryptocurrencyList.get(position).getPercentChance24h()) + "%");
        holder.tvPercentChange7d.setText(String.valueOf(cryptocurrencyList.get(position).getPercentChance7d()) + "%");


        holder.tvPercentChange24h.setTextColor(
                cryptocurrencyList.get(position).getPercentChance24h() < 0
                        ? ContextCompat.getColor(context, R.color.negative_percentage)
                        : ContextCompat.getColor(context, R.color.positive_percentage)
        );

        holder.tvPercentChange7d.setTextColor(
                cryptocurrencyList.get(position).getPercentChance7d() < 0
                        ? ContextCompat.getColor(context, R.color.negative_percentage)
                        : ContextCompat.getColor(context, R.color.positive_percentage)
        );
    }

    @Override
    public int getItemCount() {
        return this.cryptocurrencyList != null
                ? this.cryptocurrencyList.size()
                : 0;
    }

    public void swapData(List<Cryptocurrency> list){
        this.cryptocurrencyList = list;
        notifyDataSetChanged();
    }

    class CryptocurrencyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIconCryptocurrency;
        private TextView tvSymbol;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvPercentChange24h;
        private TextView tvPercentChange7d;

        public CryptocurrencyViewHolder(View itemView) {
            super(itemView);
            ivIconCryptocurrency = itemView.findViewById(R.id.iv_icon_cryptocurrency);
            tvSymbol = itemView.findViewById(R.id.tv_symbol);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvPercentChange24h = itemView.findViewById(R.id.tv_percent_change_24h);
            tvPercentChange7d = itemView.findViewById(R.id.tv_percent_change_7d);
        }
    }
}
