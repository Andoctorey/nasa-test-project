package by.yegorov.nasa.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.path.android.jobqueue.JobManager;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.R;
import by.yegorov.nasa.core.job.news.GetNewsJob;
import by.yegorov.nasa.core.job.news.event.OnGetNewsEvent;
import by.yegorov.nasa.core.model.News;
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

    @Inject
    JobManager jobManager;

    private boolean twoPane;
    private List<News> news = new ArrayList<>();
    private NewsAdapter newsAdapter;

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

        newsAdapter = new NewsAdapter(getBaseContext(), news, this);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (container != null) {
            twoPane = true;
        }

        loadData();
    }

    private void loadData() {
        jobManager.addJob(new GetNewsJob());
    }

    @Subscribe
    public void onGetNewsEvent(OnGetNewsEvent event) {
        progress(event.isInProgress);
        handleMessage(event.message, event.hasError());
        if (event.newses != null && newsAdapter != null) {
            news = event.newses;
            newsAdapter.onChanged(news);
            if (twoPane && news.size() > 0) {
                onNewsItemClick(0);
            }
        }
    }

    @Override
    public void onNewsItemClick(int position) {
        if (twoPane) {
            NewsDetailFragment fragment = NewsDetailFragment.create(news.get(position));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_news_list_fl_container, fragment)
                    .commit();
        } else {
            NewsDetailActivity.start(this, news.get(position));
        }
    }
}
