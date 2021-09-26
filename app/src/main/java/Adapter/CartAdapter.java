package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Cart;
import Domain.CartItem;
import Domain.Common;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    ArrayList<CartItem> cartItemList;

    public CartAdapter(ArrayList<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        int drawableResourceId= holder.itemView.getContext().getResources().
                getIdentifier(cartItemList.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.img_product);

        holder.txt_amount.setText(String.valueOf(cartItemList.get(position).getAmount()));

        holder.txt_price.setText(String.format("%.2f", cartItemList.get(position).getPrice()));
        holder.txt_product_name.setText(cartItemList.get(position).getName());

        holder.deleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference table_cart = database.getReference("Cart").child("User").
                        child(Common.currentUser.getName());

                table_cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                            String planttitle = Acacia.child("name").getValue(String.class);
                            if(planttitle.equals(cartItemList.get(position).getName())){
                                Acacia.getRef().removeValue();
                                cartItemList.remove(position);
                                notifyDataSetChanged();
                            }
                        }
                        Intent intent = new Intent (holder.itemView.getContext(), Cart.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        holder.itemView.getContext().startActivity(intent);
                        ((Activity)holder.itemView.getContext()).finish();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOrder;
                Double priceOrder;
                priceOrder = cartItemList.get(position).getPrice();
                numberOrder = cartItemList.get(position).getAmount();
                numberOrder = numberOrder + 1;
                priceOrder = priceOrder + cartItemList.get(position).getOriprice();
                holder.txt_price.setText(String.format("%.2f", priceOrder));
                holder.txt_amount.setText(String.valueOf(numberOrder));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference table_cart = database.getReference("Cart").child("User").
                        child(Common.currentUser.getName());

                table_cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                            String planttitle = Acacia.child("name").getValue(String.class);
                            String key = Acacia.getKey();
                            if(planttitle.equals(cartItemList.get(position).getName())){
                                table_cart.child(key).child("amount").setValue(
                                        Integer.parseInt(holder.txt_amount.getText().toString()));
                                table_cart.child(key).child("price").setValue(
                                        Double.parseDouble(holder.txt_price.getText().toString()));
                            }
                        }
                        Intent intent = new Intent (holder.itemView.getContext(), Cart.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        holder.itemView.getContext().startActivity(intent);
                        ((Activity)holder.itemView.getContext()).finish();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOrder;
                Double priceOrder;
                priceOrder = cartItemList.get(position).getPrice();
                numberOrder = cartItemList.get(position).getAmount();
                if(numberOrder>1){
                    numberOrder = numberOrder - 1;
                    priceOrder = priceOrder - cartItemList.get(position).getOriprice();
                    holder.txt_price.setText(String.format("%.2f", priceOrder));
                }
                holder.txt_amount.setText(String.valueOf(numberOrder));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference table_cart = database.getReference("Cart").child("User").
                        child(Common.currentUser.getName());

                table_cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot Acacia: dataSnapshot.getChildren()){
                            String planttitle = Acacia.child("name").getValue(String.class);
                            String key = Acacia.getKey();
                            if(planttitle.equals(cartItemList.get(position).getName())){
                                table_cart.child(key).child("amount").setValue(
                                        Integer.parseInt(holder.txt_amount.getText().toString()));
                                table_cart.child(key).child("price").setValue(
                                        Double.parseDouble(holder.txt_price.getText().toString()));
                            }
                        }
                        Intent intent = new Intent (holder.itemView.getContext(), Cart.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        holder.itemView.getContext().startActivity(intent);
                        ((Activity)holder.itemView.getContext()).finish();

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
        return cartItemList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView img_product,deleteicon, addbtn, minusbtn;
        TextView txt_product_name, txt_price;
        TextView txt_amount;

        public CartViewHolder(View itemView){
            super(itemView);
            img_product = (ImageView)itemView.findViewById(R.id.img_product);
            txt_amount = (TextView) itemView.findViewById(R.id.txt_amount);
            txt_product_name = (TextView)itemView.findViewById(R.id.txt_product_name);
            txt_price = (TextView)itemView.findViewById(R.id.txt_price);
            deleteicon = (ImageView)itemView.findViewById(R.id.deletebtn);
            addbtn = (ImageView)itemView.findViewById(R.id.addBtn);
            minusbtn = (ImageView)itemView.findViewById(R.id.minusBtn);
        }
    }

}
