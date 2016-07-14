package by.yegorov.nasa.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.R;
import by.yegorov.nasa.core.model.DummyContent;
import by.yegorov.nasa.ui.base.BaseActivity;

@SuppressWarnings({"WeakerAccess", "unused"})
public class NewsListActivity extends BaseActivity {

    @BindView(R.id.activity_news_toolbar)
    Toolbar toolbar;

    @BindView(R.id.fragment_news_list_rv_main)
    RecyclerView recyclerView;

    @Nullable
    @BindView(R.id.fragment_news_list_fl_container)
    FrameLayout container;

    private boolean twoPane;

    @Override
    protected int setContentView() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void inject() {
        NasaApp.getComponent(this).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));

        if (container != null) {
            twoPane = true;
        }
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_news_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (twoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(NewsDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        NewsDetailFragment fragment = new NewsDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_news_list_fl_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, NewsDetailActivity.class);
                        intent.putExtra(NewsDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.view_news_list_content_tv_id);
                mContentView = (TextView) view.findViewById(R.id.view_news_list_content_tv_content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
