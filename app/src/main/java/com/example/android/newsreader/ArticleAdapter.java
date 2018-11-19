package com.example.android.newsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        TextView sectionTitleView = listItemView.findViewById(R.id.section_name);
        TextView datePublishedView = listItemView.findViewById(R.id.publication_date);
        TextView articleTitleView = listItemView.findViewById(R.id.article_title);

        Article currentArticle = getItem(position);

        sectionTitleView.setText(currentArticle.getSectionName());
        articleTitleView.setText(currentArticle.getTitle());
        datePublishedView.setText(currentArticle.getPublicationDate());

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
