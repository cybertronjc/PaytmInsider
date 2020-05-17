package me.jagdishchoudhary.paytminsider.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import me.jagdishchoudhary.paytminsider.R;
import me.jagdishchoudhary.paytminsider.utils.SliderUtil;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {


    private Context context;
    private LayoutInflater layoutInflater;
    private List<SliderUtil> sliderImg;

    public ViewPagerAdapter(Context context, List<SliderUtil> sliderImg) {
        this.context = context;
        this.sliderImg = sliderImg;
    }

    @Override
    public int getCount() {
        return sliderImg.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_single_item, null);

        SliderUtil utils = sliderImg.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(context).load(utils.getSliderImageUrl()).into(imageView);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position", sliderImg.get(position).getAction_url());
                Log.d("position1", utils.getAction_url());

                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(utils.getAction_url()));
                    context.startActivity(i);
                }
                catch (Exception e){
                    Log.d("error", e.toString());
                }


            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);


        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
