package com.example.evaluation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryItemsFragment extends Fragment {
    private MyAdapter2 myAdapter2;
    TextView itemDescription2;
    RecyclerView recyclerView2;
    FrameLayout frameLayout2;
    private List<MealsItem>MealsItemlist ;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryItemsFragment() {
        // Required empty public constructor
    }


    public static CategoryItemsFragment newInstance(String param1, String param2) {
        CategoryItemsFragment fragment = new CategoryItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categoryitems, container, false);
        MealsItemlist = new ArrayList<>();


        return view;



    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchDataFromApi();
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter2 = new MyAdapter2(MealsItemlist,this);
        recyclerView2.setAdapter(myAdapter2);
        frameLayout2=view.findViewById(R.id.FrameLayout2);
        ItemTouchHelper helper2= new ItemTouchHelper(callback2);
        helper2.attachToRecyclerView(recyclerView2);

    }

    private void fetchDataFromApi(){
        RetrofitApi2 retrofitApi2=RetrofitClient.getRetrofitInstance().create(RetrofitApi2.class);
        Call<Response2> call= retrofitApi2.getMeals();
        call.enqueue(new Callback<Response2>() {
            @Override
            public void onResponse(Call<Response2> call, retrofit2.Response<Response2> response2) {

                MealsItemlist.addAll(response2.body().getMeals());
                myAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Response2> call, Throwable t) {

                System.out.println("Response Failure"+t.getMessage());
            }
        });
    }
    ItemTouchHelper.SimpleCallback callback2= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Snackbar snackbar=Snackbar.make(frameLayout2,"Category item is removed",Snackbar.LENGTH_LONG);
            snackbar.show();
            MealsItemlist.remove(viewHolder.getAdapterPosition());
            myAdapter2.notifyDataSetChanged();

        }
    };

}