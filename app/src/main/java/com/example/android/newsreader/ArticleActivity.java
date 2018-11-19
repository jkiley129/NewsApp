package com.example.android.newsreader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Article>> {

    // MARK:- Properties
    private static final String LOG_TAG = ArticleActivity.class.getName();
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?";
    private static final String GUARDIAN_API_KEY = "a96b99a2-4824-4a13-9a78-a49c9b7dc20d";
    private static final int GUARDIAN_LOADER_ID = 1;
    private ArticleAdapter adapter;
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity);

        ListView articleListView = findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateTextView);

        adapter = new ArticleAdapter(this, new ArrayList<Article>());

        articleListView.setAdapter(adapter);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(GUARDIAN_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {

        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", "tech");
        uriBuilder.appendQueryParameter("api-key", GUARDIAN_API_KEY);

        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_articles);

        if (articles != null && !articles.isEmpty()) {
            adapter.addAll(articles);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        adapter.clear();
    }
}
