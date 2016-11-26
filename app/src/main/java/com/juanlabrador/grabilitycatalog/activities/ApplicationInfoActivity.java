package com.juanlabrador.grabilitycatalog.activities;

import android.content.pm.ActivityInfo;
import android.os.Build;
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

public class ApplicationInfoActivity extends BaseActivity {

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

    String idApplication;
    String nameApplication;
    Entry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_info);
        ButterKnife.bind(this);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        idApplication = getIntent().getStringExtra("id");
        nameApplication = getIntent().getStringExtra("name");

        getSupportActionBar().setTitle(nameApplication);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        entry = EventBus.getDefault().getStickyEvent(Entry.class);

        if(entry != null) {
            createApplication();
        } else {
            onBackPressed();
        }
    }

    public void createApplication() {
        Glide.with(this).load(entry.getImages().get(2).getLabel()).into(icon);
        application.setText(entry.getName().getLabel());
        summary.setText(entry.getSummary().getLabel());
        salesman.setText(entry.getArtist().getLabel());
        updated.setText(entry.getReleaseDate().getAttributes().getLabel());
        category.setText(entry.getCategory().getAttributes().getLabel());
        company.setText(entry.getArtist().getLabel());
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportFinishAfterTransition();
        } else {
            overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
        }
    }
}
