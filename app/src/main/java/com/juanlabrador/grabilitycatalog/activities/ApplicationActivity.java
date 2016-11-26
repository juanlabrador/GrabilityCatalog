package com.juanlabrador.grabilitycatalog.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.juanlabrador.grabilitycatalog.R;
import com.juanlabrador.grabilitycatalog.adapters.ApplicationAdapter;
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

public class ApplicationActivity extends BaseActivity implements AsyncFeedResponse {

    @BindView(R.id.categories)
    RecyclerView recyclerView;
    ApplicationAdapter adapter;
    Data data;
    List<Entry> entries = new ArrayList<>();
    String idCategory;
    String nameCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        ButterKnife.bind(this);

        idCategory = getIntent().getStringExtra("id");
        nameCategory = getIntent().getStringExtra("name");

        getSupportActionBar().setTitle(nameCategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data = EventBus.getDefault().getStickyEvent(Data.class);

        if(data != null) {
            createApplication();
        } else {
            final FeedContentTask asyncTask = new FeedContentTask(getApplicationContext());
            asyncTask.delegate = this;
            asyncTask.execute();
        }
    }

    public void createApplication() {
        for (Entry entry : data.getFeed().getEntry()) {
            if (entry.getCategory().getAttributes().getId().equals(idCategory)) {
                entries.add(entry);
            }
        }

        adapter = new ApplicationAdapter(this, entries, nameCategory);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void processFinish(Data data) {
        this.data = data;
        EventBus.getDefault().postSticky(data);
        createApplication();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }
}
