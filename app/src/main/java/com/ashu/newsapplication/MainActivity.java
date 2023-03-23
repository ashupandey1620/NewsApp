package com.ashu.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategorClickInterface {
//979516355acb487088f68192a85c56af
    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingProgressBar;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModule> categoryRVModuleArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewRVAdapter newRVAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVvertical);
        categoryRV =  findViewById(R.id.idRVhorizonatal);
        loadingProgressBar = findViewById(R.id.idPb);
        articlesArrayList = new ArrayList<>();
        categoryRVModuleArrayList = new ArrayList<>();
        newRVAdapter = new NewRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModuleArrayList,this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
gettingCatagories();
getNews("sports");
newRVAdapter.notifyDataSetChanged();

    }

    private void gettingCatagories()
    {
        categoryRVModuleArrayList.add(new CategoryRVModule("https://images.unsplash.com/photo-1495020689067-958852a7765e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80","All"));
        categoryRVModuleArrayList.add(new CategoryRVModule("https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1172&q=80","Technology"));
        categoryRVModuleArrayList.add(new CategoryRVModule("https://images.unsplash.com/photo-1554475900-0a0350e3fc7b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=417&q=80","Science"));
        categoryRVModuleArrayList.add(new CategoryRVModule("https://images.unsplash.com/photo-1517649763962-0c623066013b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80","Sports"));
        categoryRVModuleArrayList.add(new CategoryRVModule("https://images.unsplash.com/photo-1513542789411-b6a5d4f31634?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1074&q=80","General"));
        categoryRVModuleArrayList.add(new CategoryRVModule("https://images.unsplash.com/photo-1553877522-43269d4ea984?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80","Business"));
        categoryRVModuleArrayList.add(new CategoryRVModule("https://images.unsplash.com/photo-1586899028174-e7098604235b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80","Entertainment"));
        categoryRVModuleArrayList.add(new CategoryRVModule("https://plus.unsplash.com/premium_photo-1663014639685-5ba9af525133?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80","Health"));

        categoryRVAdapter.notifyDataSetChanged();
    }


    private void getNews(String category)
    {
        loadingProgressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=979516355acb487088f68192a85c56af";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=979516355acb487088f68192a85c56af";
        String BASE_URL = "https://newsapi.org";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;


        if(category.equals("All")){
            call = retrofitAPI.getAllNews(url);
        }
        else
        {
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {

                NewsModal newsModal = response.body();
                loadingProgressBar.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                for(int i=0;i<articles.size();i++)
                {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                newRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Fail to get News", Toast.LENGTH_SHORT).show();;
            }
        });
    }




    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVModuleArrayList.get(position).getCategory();
       getNews(category);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}