package com.arkainfotechpvt.cumi.adopters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfotechpvt.cumi.R;
import com.arkainfotechpvt.cumi.model.CommitteLeaderModel;
import com.arkainfotechpvt.cumi.model.CommitteeMemberModel;

import java.util.ArrayList;

public class CommitteeLeaderAdopter extends RecyclerView.Adapter<CommitteeLeaderAdopter.MyViewHolder> {
    Context context;
    ArrayList<CommitteLeaderModel>committeLeaderModels;

    public CommitteeLeaderAdopter(Context context, ArrayList<CommitteLeaderModel> committeLeaderModels) {
        this.context = context;
        this.committeLeaderModels = committeLeaderModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.committee_leader_items,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CommitteLeaderModel committeLeaderModel=committeLeaderModels.get(i);
        myViewHolder.textView.setText(committeLeaderModel.getName());
        myViewHolder.imageView.setImageResource(committeLeaderModel.getImage());
    }

    @Override
    public int getItemCount()
    {
        return committeLeaderModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Typeface tf_regular1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tf_regular1 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/AGaramondPro_Bold.otf");
            /*  Typeface type1 = Typeface.createFromAsset(getAssets(),"fonts/AGaramondPro_Bold.otf");*/
             imageView=(ImageView)itemView.findViewById(R.id.image1);
            textView=(TextView) itemView.findViewById(R.id.text1);
            this.textView.setTypeface(tf_regular1);

        }
    }
}
