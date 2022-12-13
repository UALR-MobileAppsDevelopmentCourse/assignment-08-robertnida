package com.ualr.recyclerviewassignment.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import java.util.List;

public class Adapter extends RecyclerView.Adapter implements View.OnClickListener
{
    private List<Inbox> mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    @Override
    public void onClick(View view) {

    }

    public interface OnItemClickListener
    {
        void OnItemClick(View view, Inbox obj, int position);
        void onIconClick(View view, Inbox obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener)
    {
        this.mOnItemClickListener = mItemClickListener;
    }

    public Adapter(Context context, List<Inbox> items)
    {
        this.mItems = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh = null;
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email, parent, false);
        vh = new EmailViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        Inbox item = mItems.get(position);
        EmailViewHolder viewHolder = (EmailViewHolder) holder;

        viewHolder.from.setText(((Inbox) item).getFrom());
        viewHolder.message.setText(((Inbox) item).getMessage());
        viewHolder.email.setText(((Inbox) item).getEmail());
        viewHolder.date.setText(((Inbox) item).getDate());

        if (item.isSelected())
        {
            viewHolder.letter.setText("X");
            viewHolder.lyt_parent.setBackgroundColor(mContext.getResources().getColor(R.color.grey_300));
        }
        else
        {
            viewHolder.letter.setText(((Inbox) item).getFrom().substring(0, 1));
            viewHolder.lyt_parent.setBackgroundColor(mContext.getResources().getColor(R.color.overlay_light_90));
        }

    }

    public int getItemCount()
    {
        return this.mItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateItems(List<Inbox> inboxList)
    {
        this.mItems = inboxList;
        notifyDataSetChanged();
    }

    public class EmailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView from;
        public TextView letter;
        public TextView email;
        public TextView message;
        public TextView date;
        public View lyt_parent;

        public EmailViewHolder(@NonNull View v)
        {
            super(v);
            from = v.findViewById(R.id.from);
            letter = v.findViewById(R.id.letter);
            email = v.findViewById(R.id.email);
            message = v.findViewById(R.id.message);
            date = v.findViewById(R.id.date);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            lyt_parent.setOnClickListener(this);

            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view, mItems.get(getLayoutPosition()), getLayoutPosition());
                }
            });

            letter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onIconClick(v, mItems.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}
