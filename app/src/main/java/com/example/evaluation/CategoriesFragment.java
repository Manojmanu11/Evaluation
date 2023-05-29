package com.example.evaluation;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class CategoriesFragment extends Fragment  implements BottomsheetClickListnr {
    private MyAdapter myAdapter;
    static TextView itemDescription;
    RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<Categories> categoriesList;
    FrameLayout frameLayout;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        categoriesList = new ArrayList<>();

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchDataFromApi();

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myAdapter = new MyAdapter(categoriesList,this);
        recyclerView.setAdapter(myAdapter);
        frameLayout=view.findViewById(R.id.FrameLayout);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);


    }


    private void fetchDataFromApi() {
        RetrofitApi retrofitApi = RetrofitClient.getRetrofitInstance().create(RetrofitApi.class);
        Call<Response> call = retrofitApi.getCategories();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                progressBar.setVisibility(View.GONE);
                categoriesList.addAll(response.body().getCategories());
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);

                System.out.println("Response Failure" + t.getMessage());
            }
        });
    }


public void showBottomsheet(String strCategoryDescription, Context context) {
    final Dialog dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.bottom_sheet_layout);
    dialog.show();
    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.getWindow().setGravity(Gravity.BOTTOM);
}
ItemTouchHelper.SimpleCallback callback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Snackbar snackbar=Snackbar.make(frameLayout,"Item deleted",Snackbar.LENGTH_LONG);
        snackbar.show();

        categoriesList.remove(viewHolder.getAdapterPosition());
        myAdapter.notifyDataSetChanged();
    }
};

}






