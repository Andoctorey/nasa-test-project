package by.yegorov.nasa.ui.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.R;
import by.yegorov.nasa.core.model.News;
import by.yegorov.nasa.ui.base.BaseBusActivity;

@SuppressWarnings({"WeakerAccess", "unused"})
public class NewsDetailActivity extends BaseBusActivity {

    @BindView(R.id.activity_news_detail_toolbar)
    Toolbar toolbar;

    public static void start(Activity activity, News item) {
        Intent intent = new Intent(activity, NewsDetailActivity.class);
        intent.putExtra(NewsDetailFragment.ARG_ITEM, item);
        activity.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void inject() {
        NasaApp.getComponent(this).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        News news = getIntent().getParcelableExtra(NewsDetailFragment.ARG_ITEM);
        toolbar.setTitle(news.getSafeTitle());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Fragment fragment = NewsDetailFragment.create(news);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_news_scroll_view, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, NewsListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
