package com.example.android.newsreader;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapterAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }
}
