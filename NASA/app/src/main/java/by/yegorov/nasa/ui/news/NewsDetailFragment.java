package by.yegorov.nasa.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.R;
import by.yegorov.nasa.core.model.DummyContent;
import by.yegorov.nasa.ui.base.BaseBusFragment;

public class NewsDetailFragment extends BaseBusFragment {

    public static final String ARG_ITEM_ID = "item_id";

    @Nullable
    @BindView(R.id.activity_news_toolbar_layout)
    CollapsingToolbarLayout appBarLayout;

    @BindView(R.id.fragment_news_detail_tv_main)
    TextView textView;

    private DummyContent.DummyItem dummyItem;

    public NewsDetailFragment() {
    }

    @Override
    protected void inject() {
        NasaApp.getComponent(getContext()).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            dummyItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            if (appBarLayout != null) {
                appBarLayout.setTitle(dummyItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_detail, container, false);
        if (dummyItem != null) {
            textView.setText(dummyItem.details);
        }
        return rootView;
    }
}
