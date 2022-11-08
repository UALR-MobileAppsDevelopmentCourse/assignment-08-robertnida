package com.ualr.recyclerviewassignment.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListBasic extends RecyclerView.Adapter {
    private ImageView image;
    private TextView name;
    private View lyt_parent;

    public PersonViewHolder(View v){
        super(v);
        this.image = v.findViewById(R.id.image);
        this.name = v.findViewById(R.id.name);

    }

    public aAdapterListBasic(Context context, List<Inbox> items) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        vh = new ViewPersonHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PersonViewHolder viewHolder = (PersonViewHolder)holder;
        Inbox p = mItems.get(Position);
        viewHolder.name.setText(p.getName());
        viewHolder.image.setImageResource(p.image);
    }

    @Override
    public int getItemCount() {
        return this.mItem.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View v;

        ViewHolder(View itemView){
            super(itemView);
            this.image = v.findViewById(R.id.image);
            this.name = v.findViewById(R.id.name);
        }
    }
}
