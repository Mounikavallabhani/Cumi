package com.arkainfotechpvt.cumi.adopters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfotechpvt.cumi.R;

import java.util.ArrayList;

/**
 * Created by NarasimhaRao on 10/16/2017.
 */

public class RecentSelfleAdopter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public RecentSelfleAdopter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount()
    {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View view11 = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) view11.findViewById(R.id.image1);
        ImageView image2=(ImageView)view11.findViewById(R.id.image2);
        ImageView image3=(ImageView)view11.findViewById(R.id.image3);
        ImageView image4=(ImageView)view11.findViewById(R.id.image4);

        myImage.setImageResource(images.get(position));
        image2.setImageResource(images.get(position));
        image3.setImageResource(images.get(position));
        image4.setImageResource(images.get(position));

        TextView textView=(TextView)view11.findViewById(R.id.t1);
       if(position>=0){
           textView.setText("Image No"+position+1);

       }

        view.addView(view11, 0);
        return view11;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
