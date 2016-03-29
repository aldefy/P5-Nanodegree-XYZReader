package com.example.xyzreader.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.interfaces.ItemClickListener;
import com.example.xyzreader.network.model.DataModel;
import com.example.xyzreader.utils.GetPalette;
import com.example.xyzreader.utils.Utils;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by aditlal on 26/03/16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<DataModel> results;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public ItemAdapter(Context mContext, List<DataModel> results, ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.results = results;
        this.itemClickListener = itemClickListener;
    }

    public DataModel getItemForPosition(int pos) {
        return results.get(pos);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, null);
        return new ViewHolder(itemLayoutView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DataModel item = results.get(position);
        holder.itemImage.setTag(item);
        holder.cardView.setTag(item);

        DateTime dateTime = Utils.parseDateTimeFromString(item.getPublished_date());
        holder.timeLabel.setText(dateTime.toString("dd MMM yyyy"));
        holder.articleTitle.setText(item.getTitle());
        holder.articleSubtitle.setText(item.getAuthor());
        holder.articleTitle.setTextColor(ContextCompat.getColor(mContext, R.color.primary_text));
        holder.articleSubtitle.setTextColor(ContextCompat.getColor(mContext, R.color.secondary_text));
        holder.textContentLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        Picasso.with(mContext).load(item.getPhoto()).placeholder(R.drawable.ic_placeholder).fit().into(holder.itemImage, new GetPalette(mContext, holder.mainContentLayout));


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.itemImage)
        ImageView itemImage;
        @Bind(R.id.article_title)
        TextView articleTitle;
        @Bind(R.id.article_subtitle)
        TextView articleSubtitle;
        @Bind(R.id.textContent)
        LinearLayout textContentLayout;
        @Bind(R.id.timeLabel)
        TextView timeLabel;
        @Bind(R.id.mainContent)
        RelativeLayout mainContentLayout;
        @Bind(R.id.cardView)
        CardView cardView;
        private ItemClickListener itemClickListener;

        ViewHolder(View view, ItemClickListener itemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.itemClickListener = itemClickListener;
            itemImage.setOnClickListener(this);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            DataModel model = (DataModel) v.getTag();

            if (itemClickListener != null) {
                itemClickListener.itemClicked(model, v);
            }

        }
    }


}

