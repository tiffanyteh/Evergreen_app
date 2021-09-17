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
import com.example.myapplication.OrderDetails;
import com.example.myapplication.R;
import com.example.myapplication.Receipt;
import com.example.myapplication.ShowDetails;

import java.util.ArrayList;

import Domain.Order;
import Domain.PlantDomain;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList <Order> orders;
    public OrderAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order,parent,false);
        return new OrderAdapter.OrderViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

        holder.txt_orderid.setText(new StringBuilder("Order ID: ").
                append(orders.get(position).getOrderid()));
        holder.txt_totalprice.setText(new StringBuilder("Total Price(RM): ").
                append(orders.get(position).getTotal()));

        holder.txt_orderid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(holder.itemView.getContext(), OrderDetails.class);
                intent.putExtra("object1",orders.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView txt_orderid, txt_totalprice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_orderid = (TextView)itemView.findViewById(R.id.orderid);
            txt_totalprice = (TextView)itemView.findViewById(R.id.totalprice);

        }
    }
}
