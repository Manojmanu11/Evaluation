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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Categories>Categorieslist;


   BottomsheetClickListnr bottomsheetClickListnr;

    private Context context;


    public MyAdapter(List<Categories> categorieslist, CategoriesFragment categoriesFragment) {
        Categorieslist = categorieslist;
        this.context = context;

        this.bottomsheetClickListnr = bottomsheetClickListnr;

    }
 class MyViewHolder extends RecyclerView.ViewHolder{

     TextView itemName;
     private ImageView imageView;
     CardView cardView;
     private Context context;
     MyViewHolder(View itemView) {
         super(itemView);
         itemName = itemView.findViewById(R.id.itemname);
         imageView = itemView.findViewById(R.id.imageview);
         cardView = itemView.findViewById(R.id.cardview);

     }


 }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.design, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.itemName.setText(Categorieslist.get(position).getStrCategory());
    String name = Categorieslist.get(position).getStrCategoryDescription();
    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final Dialog dialog = new Dialog(view.getContext());

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.bottom_sheet_layout);
            TextView stringcontent = dialog.findViewById(R.id.itemDescription);
                stringcontent.setText(name);
            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
    });


        Picasso.with(holder.itemView.getContext())
                .load(Categorieslist.get(position).getStrCategoryThumb())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return Categorieslist.size();
    }



    public interface OnItemLongClickListener {
        void OnItemLongClickListener(int position);
    }
}
