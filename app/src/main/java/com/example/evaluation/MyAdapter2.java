package com.example.evaluation;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{
    private List<MealsItem>MealsItemlist;
    private Context context2;

    public MyAdapter2(List<MealsItem> mealsItemlist, CategoryItemsFragment categoryItemsFragment) {
        MealsItemlist = mealsItemlist;
        this.context2 = context2;
    }

    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.design2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
     holder.itemName2.setText(MealsItemlist.get(position).getStrMeal());
     String name2=MealsItemlist.get(position).getStrMeal();
     holder.cardView2.setOnClickListener(new View.OnClickListener() {
         @SuppressLint("ResourceType")
         @Override
         public void onClick(View view) {

             final Dialog dialog2=new Dialog(view.getContext());
             dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
             dialog2.setContentView(R.layout.bottom_sheet_layout);
             TextView stringContent2=dialog2.findViewById(R.id.itemDescription);
             if(stringContent2!=null){
             stringContent2.setText(name2);}
             else{
                 Toast.makeText(view.getContext(), "hi", Toast.LENGTH_SHORT).show();
             }
             dialog2.show();
             dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
             dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
             dialog2.getWindow().setGravity(Gravity.BOTTOM);
         }
     });
        Picasso.with(holder.itemView.getContext())
                .load(MealsItemlist.get(position).getStrMealThumb())
                .into(holder.imageView2);
    }

    @Override
    public int getItemCount() {
        return MealsItemlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView2;
        TextView  itemName2;
        ImageView imageView2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          itemName2=itemView.findViewById(R.id.itemname2);
          imageView2=itemView.findViewById(R.id.imageview2);
           cardView2=itemView.findViewById(R.id.cardview2);

        }
    }
}
