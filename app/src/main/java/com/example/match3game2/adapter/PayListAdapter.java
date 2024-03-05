package com.example.match3game2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.match3game2.R;
import com.example.match3game2.model.PayListModel;

import java.util.ArrayList;

public class PayListAdapter extends RecyclerView.Adapter<PayListAdapter.PayListViewHolder>{

    Context context;
    ArrayList<PayListModel> payListAdapter;

    public PayListAdapter(Context context,ArrayList<PayListModel> payListAdapter){
        this.context = context;
        this.payListAdapter = payListAdapter;
    }

    View view;

    @Override
    public PayListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.item_latest_withdrawal, parent,false);
        PayListViewHolder rvViewHolder = new PayListViewHolder(view);
        return rvViewHolder;
    }

    @Override
    public void onBindViewHolder(PayListViewHolder holder, final int position)
    {
        PayListModel data = payListAdapter.get(position);

        final int id = data.getId();
        final String name = data.getUsername();
        String amount = data.getAmount();

        int leader = position + 1;

        holder.idView.setText("" + leader);
        holder.nameView.setText(name);
        holder.scoreView.setText("" + amount);

    }

    @Override
    public int getItemCount() {
        return payListAdapter.size();
    }

    public class PayListViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, idView, scoreView;

        public PayListViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.NameTextView);
            idView = itemView.findViewById(R.id.IdTextView);
            scoreView = itemView.findViewById(R.id.ScoreTextView);
        }
    }
}