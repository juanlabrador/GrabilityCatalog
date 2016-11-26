package com.juanlabrador.grabilitycatalog.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juanlabrador.grabilitycatalog.R;
import com.juanlabrador.grabilitycatalog.adapters.ApplicationAdapter;
import com.juanlabrador.grabilitycatalog.interfaces.AsyncFeedResponse;
import com.juanlabrador.grabilitycatalog.models.Data;
import com.juanlabrador.grabilitycatalog.models.Entry;
import com.juanlabrador.grabilitycatalog.taks.FeedContentTask;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationInfoActivity extends BaseActivity implements AsyncFeedResponse {

    @BindView(R.id.icon)
    RoundedImageView icon;
    @BindView(R.id.application)
    TextView application;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.salesman)
    TextView salesman;
    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.updated)
    TextView updated;
    @BindView(R.id.company)
    TextView company;

    Data data;
    String idApplication;
    String nameApplication;
    Entry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_info);
        ButterKnife.bind(this);

        idApplication = getIntent().getStringExtra("id");
        nameApplication = getIntent().getStringExtra("name");

        getSupportActionBar().setTitle(nameApplication);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            if (entry.getId().getAttributes().getId().equals(idApplication)) {
                this.entry = entry;
                break;
            }
        }

        Glide.with(this).load(entry.getImages().get(2).getLabel()).into(icon);
        application.setText(entry.getName().getLabel());
        summary.setText(entry.getSummary().getLabel());
        salesman.setText(entry.getArtist().getLabel());
        updated.setText(entry.getReleaseDate().getAttributes().getLabel());
        category.setText(entry.getCategory().getAttributes().getLabel());
        company.setText(entry.getArtist().getLabel());
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
