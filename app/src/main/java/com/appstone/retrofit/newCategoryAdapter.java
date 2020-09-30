package com.appstone.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class newCategoryAdapter extends RecyclerView.Adapter<newCategoryAdapter.NewsCategoryHolder> {
    private Context context;
    private String[] newscategories;
    private NewsCategoryClickListener listener;
    private int selectedPosition = 0;

    public void setListener(NewsCategoryClickListener listener){
        this.listener = listener;
    }

    public newCategoryAdapter(Context context){
        this.context = context;
        newscategories = context.getResources().getStringArray(R.array.news_category);
    }

    @NonNull
    @Override
    public NewsCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsCategoryHolder(LayoutInflater.from(context).inflate(R.layout.cell_news_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryHolder holder, int position) {
        String categoryname = newscategories[position];
        holder.mTvNewsCategory.setText(categoryname);

        if (selectedPosition == position){
            holder.mRlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_cat_selected,null));
            holder.mTvNewsCategory.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.colorWhite,null));
        }else {
            holder.mRlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_cat_unselected,null));
            holder.mTvNewsCategory.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.colorBlack,null));
        }

        holder.mRlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.OnNewsCategoryClicked(categoryname);
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return newscategories.length;
    }

    class NewsCategoryHolder extends RecyclerView.ViewHolder{
        private RelativeLayout mRlRoot;
        private TextView mTvNewsCategory;

        public NewsCategoryHolder(@NonNull View itemView) {
            super(itemView);
            mRlRoot=itemView.findViewById(R.id.rl_news_category);
            mTvNewsCategory = itemView.findViewById(R.id.tv_news_category);
        }
    }
    public  interface NewsCategoryClickListener {
        void OnNewsCategoryClicked(String categoryName);
    }
}
