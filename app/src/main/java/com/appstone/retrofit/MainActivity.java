package com.appstone.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import model.Article;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements newCategoryAdapter.NewsCategoryClickListener {
    private String categoryName="business";
    private RecyclerView mRcNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mBtnGetResult = findViewById(R.id.btn_get_result);
        RecyclerView mRcNewsCategory = findViewById(R.id.rc_news_category);
        mRcNewsCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));

        newCategoryAdapter newCategoryAdapter = new newCategoryAdapter(MainActivity.this);
        newCategoryAdapter.setListener(this);
        mRcNewsCategory.setAdapter(newCategoryAdapter);

        mRcNews = findViewById(R.id.rc_news);
        mRcNews.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        mBtnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(data);
            }
        });
        getSourcesNews();
    }

    private void getSourcesNews() {
        HashMap<String,Object> queries = new HashMap<>();
        queries.put("apiKey","47c604f4f2e74ce6b660c30bd24d7b24");
        queries.put("category",categoryName);

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("getting news");
        dialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

   /*     Call<String> getSourceNews = apiInterface.getAllSources(queries);

        getSourceNews.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.hide();
               String responseValue = response.body();
                try{
                    JSONObject responseObject = new JSONObject(responseValue);
                    Result resultValue = Result.parseResultResponse(responseObject);
                    ArrayList<Article> articles = resultValue.articles;
                    NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this,articles);
                    mRcNews.setAdapter(newsAdapter);


                }catch (JSONException e){
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.hide();
                Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_LONG).show();
            }
        }); */

        Call<Result> getSourceNews = apiInterface.getAllSources(queries);

        getSourceNews.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                dialog.hide();
                Result result = response.body();
                ArrayList<Article> articles = result.articles;
                NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this,articles);
                mRcNews.setAdapter(newsAdapter);

                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.hide();
                Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void OnNewsCategoryClicked(String categoryName) {
        this.categoryName = categoryName;
        getSourcesNews();
    }
}