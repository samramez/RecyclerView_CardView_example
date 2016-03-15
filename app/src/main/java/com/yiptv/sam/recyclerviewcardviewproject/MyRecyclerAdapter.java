package com.yiptv.sam.recyclerviewcardviewproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    private List<FeedItem> feedItemList;
    private Context mContext;

    public MyRecyclerAdapter(Context context,List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    /**
     * When the Adapter creates its first item, onCreateViewHolder is called. This is what allows
     * the Adapter to reuse a reference to a view instead of re-inflating it as I mentioned earlier.
     * Typically this implementation will just inflate a view and return a ViewHolder object
     */
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,null);

        return new CustomViewHolder(view);
    }

    /**
     * This is where I get an item from your data set and bind it to the list item in your adapter
     */
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        FeedItem feedItem = feedItemList.get(position);

        // Download Image using picasso library
        Picasso.with(mContext)
                .load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        // Setting text view title
        holder.textView.setText(Html.fromHtml(feedItem.getTitle()));

        //Handle click event on both title and image click
        holder.textView.setOnClickListener(clickListener);
        holder.imageView.setOnClickListener(clickListener);

        holder.textView.setTag(holder);
        holder.imageView.setTag(holder);
    }

    @Override
    public int getItemCount() {
        if (feedItemList != null){
            return feedItemList.size();
        } else{
            return 0;
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getAdapterPosition();

            FeedItem feedItem = feedItemList.get(position);
            Toast.makeText(mContext,feedItem.getTitle(),Toast.LENGTH_LONG).show();
        }
    };


    /**
     * The ViewHolder contains the reference to the each of the ui widget on the row.
     */
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }

}
