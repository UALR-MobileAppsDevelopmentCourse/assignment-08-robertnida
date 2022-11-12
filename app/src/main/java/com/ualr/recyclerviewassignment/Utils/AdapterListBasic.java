package com.ualr.recyclerviewassignment.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.text.BreakIterator;
import java.util.List;

public class AdapterListBasic extends RecyclerView.Adapter {
    private List<Inbox> mItems;
    private Context mContext;

    public AdapterListBasic(Context context, List<Inbox> items){
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        vh = new PersonViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PersonViewHolder viewHolder = (PersonViewHolder)holder;
        Inbox p = mItems.get(position);
        viewHolder.name.setText(p.getFrom());
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView image;
        private View lyt_parent;

        ViewHolder(View v){
            super(v);
            this.image = v.findViewById(R.id.image);
            this.name = v.findViewById(R.id.name);
            this.lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }
}
