package Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.ShowDetails;

import java.util.ArrayList;

import Domain.PlantDomain;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    ArrayList<PlantDomain> tree;

    public ListAdapter(ArrayList<PlantDomain> tree) {
        this.tree = tree;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        int drawableResourceId= holder.itemView.getContext().getResources().
                getIdentifier(tree.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.img_product);

        holder.txt_price.setText(new StringBuilder("RM").append(tree.get(position).getPrice()));
        holder.txt_product_name.setText(tree.get(position).getTitle());

        holder.txt_product_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(holder.itemView.getContext(), ShowDetails.class);
                intent.putExtra("object",tree.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tree.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView img_product;
        TextView txt_product_name, txt_price;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = (ImageView)itemView.findViewById(R.id.image);
            txt_product_name = (TextView)itemView.findViewById(R.id.title);
            txt_price = (TextView)itemView.findViewById(R.id.price);

        }
    }
}
