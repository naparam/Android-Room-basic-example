package com.example.appiness.architecture_component.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appiness.architecture_component.models.EntityModel;
import com.example.appiness.architecture_component.R;

import java.util.List;

/**
 * Created by appiness on 2/3/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>  {
    private List<EntityModel> entityModelList;
    private View.OnClickListener deleteClickListener;

    public RecyclerViewAdapter(List<EntityModel> entityModelList, View.OnClickListener deleteClickListener) {
        this.entityModelList = entityModelList;
        this.deleteClickListener = deleteClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        EntityModel entityModel = entityModelList.get(position);
        holder.itemTextView.setText(entityModel.getItemName());
        holder.nameTextView.setText(entityModel.getPersonName());
        holder.dateTextView.setText(entityModel.getBorrowDate().toLocaleString().substring(0, 11));
        holder.itemView.setTag(entityModel);
//        holder.itemView.setOnLongClickListener(longClickListener);

        holder.ivDelete.setTag(entityModel);
        holder.ivDelete.setOnClickListener(deleteClickListener);
    }

    @Override
    public int getItemCount() {
        return entityModelList.size();
    }

    public void addItems(List<EntityModel> entityModelList) {
        this.entityModelList = entityModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;
        private ImageView ivDelete;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.itemTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
            dateTextView = view.findViewById(R.id.dateTextView);
            ivDelete = view.findViewById(R.id.ivDelete);
        }
    }
}
