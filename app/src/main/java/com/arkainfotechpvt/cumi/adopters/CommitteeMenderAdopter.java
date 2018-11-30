package com.arkainfotechpvt.cumi.adopters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfotechpvt.cumi.R;
import com.arkainfotechpvt.cumi.model.CommitteeMemberModel;

import java.util.ArrayList;

public class CommitteeMenderAdopter extends RecyclerView.Adapter<CommitteeMenderAdopter.MyViewHolder> {

   Context context;
   ArrayList<CommitteeMemberModel>committeeMemberModels;

    public CommitteeMenderAdopter(Context context, ArrayList<CommitteeMemberModel> committeeMemberModels) {
        this.context = context;
        this.committeeMemberModels = committeeMemberModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater  layoutInflater=LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.committee_member_items,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        CommitteeMemberModel committeeMemberModel=committeeMemberModels.get(i);
        myViewHolder.textView.setText(committeeMemberModel.getName());
        myViewHolder.imageView.setImageResource(committeeMemberModel.getImage());

    }

    @Override
    public int getItemCount() {
        return committeeMemberModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image);
            textView=(TextView) itemView.findViewById(R.id.text);
        }
    }
}
