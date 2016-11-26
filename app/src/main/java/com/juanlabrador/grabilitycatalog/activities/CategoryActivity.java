package com.juanlabrador.grabilitycatalog.activities;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.juanlabrador.grabilitycatalog.R;
import com.juanlabrador.grabilitycatalog.adapters.CategoryAdapter;
import com.juanlabrador.grabilitycatalog.interfaces.AsyncFeedResponse;
import com.juanlabrador.grabilitycatalog.models.Category;
import com.juanlabrador.grabilitycatalog.models.Data;
import com.juanlabrador.grabilitycatalog.models.Entry;
import com.juanlabrador.grabilitycatalog.taks.FeedContentTask;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends BaseActivity implements AsyncFeedResponse {

    @BindView(R.id.categories)
    RecyclerView recyclerView;
    @BindView(R.id.emptyContent)
    View emptyContent;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    CategoryAdapter adapter;
    Data data;
    List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_grid);
            GridDividerItemDecoration itemDecoration = new GridDividerItemDecoration(dividerDrawable, dividerDrawable, 3);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        data = EventBus.getDefault().getStickyEvent(Data.class);

        if(data != null) {
            try {
                createCategories();
            } catch (NullPointerException e) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                emptyContent.setVisibility(View.VISIBLE);
            }
        } else {
            if (haveNetworkConnection()) {
                progressBar.setVisibility(View.VISIBLE);
                final FeedContentTask asyncTask = new FeedContentTask(getApplicationContext());
                asyncTask.delegate = this;
                asyncTask.execute();
            } else {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                emptyContent.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.button)
    void retry() {
        if (haveNetworkConnection()) {
            emptyContent.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            final FeedContentTask asyncTask = new FeedContentTask(getApplicationContext());
            asyncTask.delegate = this;
            asyncTask.execute();
        } else {
            Toast.makeText(this, R.string.message_without_internet, Toast.LENGTH_SHORT).show();
        }
    }

    public void createCategories() {
        for (Entry entry : data.getFeed().getEntry()) {
            if (!categories.contains(entry.getCategory())) {
                categories.add(entry.getCategory());
            }
        }

        progressBar.setVisibility(View.GONE);
        adapter = new CategoryAdapter(this, categories);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void processFinish(Data data) {
        this.data = data;
        EventBus.getDefault().postSticky(data);

        try {
            createCategories();
            recyclerView.setVisibility(View.VISIBLE);
            emptyContent.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } catch (NullPointerException e) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            emptyContent.setVisibility(View.VISIBLE);
        }
    }
}
