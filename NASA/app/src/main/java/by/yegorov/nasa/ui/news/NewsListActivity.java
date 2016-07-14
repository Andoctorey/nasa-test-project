package by.yegorov.nasa.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import butterknife.BindView;
import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.R;
import by.yegorov.nasa.core.model.DummyContent;
import by.yegorov.nasa.ui.base.BaseBusActivity;

@SuppressWarnings({"WeakerAccess", "unused"})
public class NewsListActivity extends BaseBusActivity implements NewsAdapter.ViewHolder.OnViewHolderClicksListener {

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

        recyclerView.setAdapter(new NewsAdapter(getBaseContext(), DummyContent.ITEMS, this));

        if (container != null) {
            twoPane = true;
        }
    }

    @Override
    public void onNewsItemClick(int position) {
        if (twoPane) {
            NewsDetailFragment fragment = NewsDetailFragment.create(DummyContent.ITEMS.get(position).id);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_news_list_fl_container, fragment)
                    .commit();
        } else {
            NewsDetailActivity.start(getBaseContext(), DummyContent.ITEMS.get(position).id);
        }
    }
}
