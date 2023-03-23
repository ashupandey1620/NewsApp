package com.ashu.newsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {


    private ArrayList<CategoryRVModule> categoryRVModules;
    private Context context;
    private CategorClickInterface categorClickInterface;

    public CategoryRVAdapter(ArrayList<CategoryRVModule> categoryRVModules, Context context, CategorClickInterface categorClickInterface) {
        this.categoryRVModules = categoryRVModules;
        this.context = context;
        this.categorClickInterface = categorClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_items,parent,false);
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
          CategoryRVModule categoryRVModule = categoryRVModules.get(position);
          holder.categoryTV.setText(categoryRVModule.getCategory());
        Picasso.get().load(categoryRVModule.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
categorClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return categoryRVModules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private ImageView categoryIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.idTvCategories);
            categoryIV = itemView.findViewById(R.id.idIVcategory);
        }
    }

    public interface CategorClickInterface{
        void onCategoryClick(int position);

    }
}
