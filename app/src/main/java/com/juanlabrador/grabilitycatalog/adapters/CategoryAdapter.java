package com.juanlabrador.grabilitycatalog.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juanlabrador.grabilitycatalog.R;
import com.juanlabrador.grabilitycatalog.activities.ApplicationActivity;
import com.juanlabrador.grabilitycatalog.activities.BaseActivity;
import com.juanlabrador.grabilitycatalog.models.Category;
import com.juanlabrador.grabilitycatalog.models.Entry;
import com.juanlabrador.grabilitycatalog.models.Link;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Category> entries;
    private BaseActivity parent;

    public CategoryAdapter(Context context, List<Category> entries) {
        this.context = context;
        this.entries = entries;
        parent = (BaseActivity) context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_category, parent, false);
        return new ViewHolderCategory(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Category entry = entries.get(position);
        ((ViewHolderCategory) holder).view.setTag(entry);
        ((ViewHolderCategory) holder).category.setText(entry.getAttributes().getLabel());
        ((ViewHolderCategory) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category = (Category) ((ViewHolderCategory) holder).view.getTag();
                parent.startActivity(new Intent(parent, ApplicationActivity.class)
                        .putExtra("id", category.getAttributes().getId())
                        .putExtra("name", category.getAttributes().getLabel()));
                parent.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class ViewHolderCategory extends RecyclerView.ViewHolder {

        @BindView(R.id.category)
        TextView category;
        View view;

        public ViewHolderCategory(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;

        }
    }
}
