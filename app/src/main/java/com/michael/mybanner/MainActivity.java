package com.michael.mybanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainActivity extends Activity {
    private static final int Pic_Size = 5;
    private static final int[] Image_Src ={
            R.mipmap.img1 ,
            R.mipmap.img2 ,
            R.mipmap.img3 ,
            R.mipmap.img4 ,
            R.mipmap.img5
    };
    private ImageView[] mIndicators;

    ViewPager mViewPager;
    ImageView mIndicator1, mIndicator2, mIndicator3, mIndicator4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        mIndicators = new ImageView[]{
                (ImageView) findViewById(R.id.indicator1),
                (ImageView) findViewById(R.id.indicator2),
                (ImageView) findViewById(R.id.indicator3),
                (ImageView) findViewById(R.id.indicator4),
                (ImageView) findViewById(R.id.indicator5)
        };
        setIndicator(0);
        mViewPager  = (ViewPager) findViewById(R.id.viewpage);
        mIndicator1 = (ImageView) findViewById(R.id.indicator1);
        mIndicator2 = (ImageView) findViewById(R.id.indicator2);
        mIndicator3 = (ImageView) findViewById(R.id.indicator3);
        mIndicator4 = (ImageView) findViewById(R.id.indicator4);
        BannerAdapter adapter = new BannerAdapter(this);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(adapter);
    }

    protected void setIndicator(int position){
        position %= Pic_Size;
        for(ImageView img : mIndicators){
            img.setImageResource(R.mipmap.indicator_unchecked);
        }
        mIndicators[position].setImageResource(R.mipmap.indicator_checked);
    }



    public class BannerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{
        private LayoutInflater layoutInflater;
        public BannerAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return Pic_Size;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= Pic_Size;
            View view = layoutInflater.inflate(R.layout.item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setImageResource(Image_Src[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
