package com.rand.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Reponsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends  RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnclickListener {
        void onItemClicked(int position);
    }


    public interface  OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnclickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnclickListener clickListener)  {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator  to inflat a view
       View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        // wrap it inside  view holder and  return it
        return new ViewHolder(todoView);
    }

    // responisble for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     //Grab the item at the position

     String item = items.get(position);
     //Bind the item into the specified view holder
     holder.bind(item);
    }
 // Tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy acces to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);

        }
        //update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());

                }
            });




            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener whiich postion was long presssed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
