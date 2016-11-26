package com.juanlabrador.grabilitycatalog.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juanlabrador.grabilitycatalog.R;
import com.juanlabrador.grabilitycatalog.activities.ApplicationActivity;
import com.juanlabrador.grabilitycatalog.activities.ApplicationInfoActivity;
import com.juanlabrador.grabilitycatalog.activities.BaseActivity;
import com.juanlabrador.grabilitycatalog.models.Category;
import com.juanlabrador.grabilitycatalog.models.Entry;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class ApplicationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Entry> entries;
    private String nameCategory;
    private BaseActivity parent;

    public ApplicationAdapter(Context context, List<Entry> entries, String nameCategory) {
        this.context = context;
        this.entries = entries;
        parent = (BaseActivity) context;
        inflater = LayoutInflater.from(context);
        this.nameCategory = nameCategory;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_application, parent, false);
        return new ViewHolderApplication(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Entry entry = entries.get(position);
        ((ViewHolderApplication) holder).view.setTag(entry);
        Glide.with(context).load(entry.getImages().get(2).getLabel()).into(((ViewHolderApplication) holder).icon);
        ((ViewHolderApplication) holder).application.setText(entry.getName().getLabel());
        ((ViewHolderApplication) holder).category.setText(nameCategory);
        ((ViewHolderApplication) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Entry entry = (Entry) ((ViewHolderApplication) holder).view.getTag();
                parent.startActivity(new Intent(parent, ApplicationInfoActivity.class)
                        .putExtra("id", entry.getId().getAttributes().getId())
                        .putExtra("name", entry.getName().getLabel()));
                parent.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class ViewHolderApplication extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        RoundedImageView icon;
        @BindView(R.id.application)
        TextView application;
        @BindView(R.id.category)
        TextView category;
        View view;

        public ViewHolderApplication(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }
}
