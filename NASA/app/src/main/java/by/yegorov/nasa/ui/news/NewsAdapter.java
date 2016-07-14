package by.yegorov.nasa.ui.news;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.R;
import by.yegorov.nasa.core.model.News;
import by.yegorov.nasa.ui.base.BaseRecyclerViewAdapter;

@SuppressWarnings({"WeakerAccess", "unused"})
public class NewsAdapter extends BaseRecyclerViewAdapter {

    private final ViewHolder.OnViewHolderClicksListener listener;
    private List<News> items = new ArrayList<>();

    public NewsAdapter(Context context, List<News> items, ViewHolder.OnViewHolderClicksListener listener) {
        super(context);
        this.items = items;
        this.listener = listener;
    }

    public void onChanged(List<News> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    public static class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder implements View.OnClickListener {

        private final OnViewHolderClicksListener listener;

        @BindView(R.id.view_news_list_content_tv_id)
        TextView tvId;

        @BindView(R.id.view_news_list_content_tv_content)
        TextView tvContent;

        public ViewHolder(View view, OnViewHolderClicksListener listener) {
            super(view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNewsItemClick(getLayoutPosition());
        }

        public interface OnViewHolderClicksListener {
            void onNewsItemClick(int position);
        }
    }

    @Override
    protected void inject() {
        NasaApp.getComponent(context).inject(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = inflater.inflate(R.layout.view_news_list_content, viewGroup, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        News item = items.get(position);
        viewHolder.tvId.setText(item.getSafeTitle());
        viewHolder.tvContent.setText(item.getLink());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
