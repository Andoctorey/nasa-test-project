package by.yegorov.nasa.ui.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.yegorov.nasa.R;
import by.yegorov.nasa.core.model.DummyContent;

public class NewsDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private DummyContent.DummyItem dummyItem;

    public NewsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            dummyItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.activity_news_toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(dummyItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_detail, container, false);
        if (dummyItem != null) {
            ((TextView) rootView.findViewById(R.id.fragment_news_detail_tv_main)).setText(dummyItem.details);
        }
        return rootView;
    }
}
