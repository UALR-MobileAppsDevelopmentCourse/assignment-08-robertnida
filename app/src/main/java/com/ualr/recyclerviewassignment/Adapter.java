package com.ualr.recyclerviewassignment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.model.Inbox;
import java.util.List;
import com.ualr.recyclerviewassignment.Utils.Tools;

import org.w3c.dom.Text;

public class Adapter extends RecyclerView.Adapter
{
    private List<Inbox> mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
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

    public void removeItem(int position)
    {
        if (position >= mItems.size())
        {
            return;
        }
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void addItemToTop()
    {
        Inbox item = DataGenerator.getRandomInboxItem(mContext);
        mItems.add(0, item);
        notifyItemInserted(0);
    }

    public void itemSelected(int position)
    {
        mItems.get(position).toggleSelection();
        notifyItemChanged(position);
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
        Inbox email = (Inbox) item;

        viewHolder.from.setText(email.getFrom());
        viewHolder.message.setText(email.getMessage());
        viewHolder.email.setText(email.getEmail());
        viewHolder.date.setText(email.getDate());

        if (item.isSelected())
        {
            viewHolder.letter.setText("X");
            viewHolder.lyt_parent.setBackgroundColor(mContext.getResources().getColor(R.color.grey_300));
        }
        else
        {
            viewHolder.letter.setText(email.getFrom().substring(0, 1));
            viewHolder.lyt_parent.setBackgroundColor(mContext.getResources().getColor(R.color.overlay_light_90));
        }

    }

    public int getItemCount()
    {
        return this.mItems.size();
    }

    public class EmailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
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

            letter.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (mItems.get(getLayoutPosition()).isSelected())
                    {
                        removeItem(getLayoutPosition());
                    }
                }
            });

        }

        @Override
        public void onClick(View view)
        {
            mOnItemClickListener.onItemClick(getLayoutPosition());

        }

    }
}
