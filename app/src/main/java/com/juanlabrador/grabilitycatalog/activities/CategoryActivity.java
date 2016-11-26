package com.juanlabrador.grabilitycatalog.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class CategoryActivity extends BaseActivity implements AsyncFeedResponse {

    @BindView(R.id.categories)
    RecyclerView recyclerView;
    CategoryAdapter adapter;
    Data data;
    List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data = EventBus.getDefault().getStickyEvent(Data.class);

        if(data != null) {
            createCategories();
        } else {
            final FeedContentTask asyncTask = new FeedContentTask(getApplicationContext());
            asyncTask.delegate = this;
            asyncTask.execute();
        }
    }

    public void createCategories() {
        for (Entry entry : data.getFeed().getEntry()) {
            if (!categories.contains(entry.getCategory())) {
                categories.add(entry.getCategory());
            }
        }

        adapter = new CategoryAdapter(this, categories);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void processFinish(Data data) {
        this.data = data;
        EventBus.getDefault().postSticky(data);
        createCategories();
    }
}
