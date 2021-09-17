package Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.ShowDetails;

import java.util.ArrayList;

import Domain.CategoryDomain;
import Domain.PlantDomain;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<PlantDomain> plantDomains;

    public PopularAdapter(ArrayList<PlantDomain> plantDomains) {
        this.plantDomains = plantDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(plantDomains.get(position).getTitle());
        holder.price.setText(String.valueOf(plantDomains.get(position).getPrice()));

        int drawableResourceId= holder.itemView.getContext().getResources().
                getIdentifier(plantDomains.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(holder.itemView.getContext(), ShowDetails.class);
                intent.putExtra("object",plantDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, price, addBtn;
        ImageView pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            pic= itemView.findViewById(R.id.pic);
            price= itemView.findViewById(R.id.price);
            addBtn= itemView.findViewById(R.id.addBtn);
        }
    }
}
