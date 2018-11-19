package com.example.android.newsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {

    // MARK:- Properties
    private static final String LOG_TAG = ArticleActivity.class.getName();
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?q=tech&api-key=a96b99a2-4824-4a13-9a78-a49c9b7dc20d";
    private static final int GUARDIAN_LOADER_ID = 1;
    private ArticleAdapter adapter;
    private TextView mEmptyStateTextView;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity);

        adapter = new ArticleAdapter(this, new ArrayList<Article>());
    }
}
