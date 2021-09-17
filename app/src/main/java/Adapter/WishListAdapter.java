package Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import Domain.Common;
import com.example.myapplication.R;
import com.example.myapplication.ShowDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Domain.PlantDomain;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder> {
    ArrayList<PlantDomain> tree;

    public WishListAdapter(ArrayList<PlantDomain> tree) {
        this.tree = tree;
    }

    @NonNull
    @Override
    public WishListAdapter.WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav,parent,false);
        return new WishListAdapter.WishListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.WishListViewHolder holder, int position) {

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

        holder.deleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference table_wishlist = database.getReference("Wishlist").
                        child("User").child(Common.currentUser.getName());

                table_wishlist.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                            String planttitle = Acacia.getValue(String.class);
                            if(planttitle.equals(tree.get(position).getTitle())){
                                Acacia.getRef().removeValue();
                                tree.remove(position);
                                notifyDataSetChanged();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        });

    }

    @Override
    public int getItemCount() {
        return tree.size();
    }

    class WishListViewHolder extends RecyclerView.ViewHolder {

        ImageView img_product, deleteicon;
        TextView txt_product_name, txt_price;

        public WishListViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = (ImageView)itemView.findViewById(R.id.image);
            txt_product_name = (TextView)itemView.findViewById(R.id.title);
            txt_price = (TextView)itemView.findViewById(R.id.price);
            deleteicon = (ImageView)itemView.findViewById(R.id.delete);

        }
    }
}
