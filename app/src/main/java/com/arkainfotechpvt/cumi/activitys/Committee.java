package com.arkainfotechpvt.cumi.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arkainfotechpvt.cumi.R;
import com.arkainfotechpvt.cumi.adopters.CommitteeLeaderAdopter;
import com.arkainfotechpvt.cumi.adopters.CommitteeMenderAdopter;
import com.arkainfotechpvt.cumi.model.CommitteLeaderModel;
import com.arkainfotechpvt.cumi.model.CommitteeMemberModel;

import java.util.ArrayList;

public class Committee extends Fragment {

    RecyclerView recyclerView_leaders,recyclerView_members;
    GridLayoutManager gridLayoutManager_leader,gridLayoutManager_members;

    ArrayList<CommitteLeaderModel>committeLeaderModels;
    ArrayList<CommitteeMemberModel>committeeMemberModels;

    CommitteeLeaderAdopter committeeLeaderAdopter;
    CommitteeMenderAdopter committeeMenderAdopter;
   Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_committee,container,false);


        recyclerView_leaders = (RecyclerView)view.findViewById(R.id.committee_leaders);

        Leader();
        return view;
    }
    public void Leader( ){
        gridLayoutManager_leader=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL, false);
        recyclerView_leaders.setLayoutManager(gridLayoutManager_leader);
        recyclerView_leaders.setHasFixedSize(true);
        committeLeaderModels=new ArrayList<>();
        committeLeaderModels.add(new CommitteLeaderModel("MV Sivakumaran",R.drawable.i1));
        committeLeaderModels.add(new CommitteLeaderModel("NB Rajesh",R.drawable.i2));
        committeLeaderModels.add(new CommitteLeaderModel("C Ayyappan",R.drawable.i3));
        committeLeaderModels.add(new CommitteLeaderModel("KM Natarajan",R.drawable.i4));
        committeLeaderModels.add(new CommitteLeaderModel("Karthik E",R.drawable.i5));
        committeLeaderModels.add(new CommitteLeaderModel("Brahmanand V",R.drawable.i6));
        committeLeaderModels.add(new CommitteLeaderModel("RajKumar Arul",R.drawable.i7));

        committeeLeaderAdopter=new CommitteeLeaderAdopter(getActivity(),committeLeaderModels);
        recyclerView_leaders.setAdapter(committeeLeaderAdopter);

    }



}
