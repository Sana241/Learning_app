package com.example.learningproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
public class HomeRecyclerAdapter
        extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>
        implements View.OnClickListener {
    private final LinkedList<String> linkedList;
    private LayoutInflater layoutInflater;

    public HomeRecyclerAdapter(Context context, LinkedList<String> linkedList) {
        this.linkedList = linkedList;
        layoutInflater= LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view=layoutInflater.inflate(R.layout.recycle_list_item,
               parent,false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerAdapter.ViewHolder holder, int position) {

       holder.recyclerTextView.setText(linkedList.get(position));
    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    @Override
    public void onClick(View view) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recyclerTextView;
        HomeRecyclerAdapter HomeRecyclerAdapter;

        public ViewHolder(@NonNull View itemView, HomeRecyclerAdapter HomeRecyclerAdapter) {
            super(itemView);
            recyclerTextView = itemView.findViewById(R.id.recycler_item);
            this.HomeRecyclerAdapter = HomeRecyclerAdapter;
            itemView.setOnClickListener(HomeRecyclerAdapter.this);
        }
    }
}
