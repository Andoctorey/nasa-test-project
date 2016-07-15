package by.yegorov.nasa.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.R;
import by.yegorov.nasa.core.model.News;
import by.yegorov.nasa.ui.base.BaseBusFragment;

@SuppressWarnings({"WeakerAccess", "unused"})
public class NewsDetailFragment extends BaseBusFragment {

    public static final String ARG_ITEM = "item";

    @Nullable
    @BindView(R.id.activity_news_toolbar_layout)
    CollapsingToolbarLayout appBarLayout;

    @BindView(R.id.fragment_news_detail_tv_date)
    TextView tvDate;

    @BindView(R.id.fragment_news_detail_tv_description)
    TextView tvDescription;

    private News item;

    public static NewsDetailFragment create(News item) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(NewsDetailFragment.ARG_ITEM, item);
        fragment.setArguments(arguments);
        return fragment;
    }

    public NewsDetailFragment() {
    }

    @Override
    protected void inject() {
        NasaApp.getComponent(getContext()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM)) {
            item = getArguments().getParcelable(ARG_ITEM);
            if (appBarLayout != null) {
                appBarLayout.setTitle(item.getSafeTitle());
            }
        }

        tvDescription.setText(item.getSafeDescription());
        if (item.getPubDate() != null) {
            tvDate.setText(DateUtils.formatDateTime(getContext(), item.getPubDate().getTime(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE));
        }
    }
}
