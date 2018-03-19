package com.mbox.administrator.namecardexchange.feature.cards;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;


import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.CardAlertDialogManage;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardDataMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardViewPositionMdl;
import java.lang.reflect.Field;


/**
 * Created by Mangkorn Supparphai on 12/15/2017 AD.
 */

public class CustomView extends LinearLayout implements CardAttributeDefault {
    private static final String TAG = CustomView.class.getSimpleName();

    LinearLayout ly_custom;
    TextView tv_data_text, tv_title;
    ImageView img_icon;
    Rect hitRect;
    Context context;
    CardDataMdl cardDataMdl;
    CardViewPositionMdl cardViewPositionMdl;
    OnCustomItemClickListener onCustomItemClickListener;
    int cardOrientation;


    public CustomView(Context context) {
        super(context);
        this.context = context;
        init(context,null);
    }

    public CustomView(Context context, CardDataMdl cardDataMdl, CardViewPositionMdl cardViewPositionMdl, int cardOrientation) {
        super(context);
        this.context = context;
        this.cardViewPositionMdl = cardViewPositionMdl;
        this.cardDataMdl = cardDataMdl;
        this.cardOrientation = cardOrientation;
        init(context,null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs){
        View.inflate(context, R.layout.item_custom_view, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);


        ly_custom = findViewById(R.id.ly_custom);
        tv_data_text =  findViewById(R.id.tv_data);
        tv_title = findViewById(R.id.tv_title);
        img_icon = findViewById(R.id.img_icon);
        hitRect = new Rect();

        ly_custom.setOnTouchListener(onTouchCustomView);

//        Typeface typeface = ResourcesCompat.getFont(context, R.font.supermarket_font);
//        tv_data_text.setTypeface(typeface);


        // Assign custom attributes
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.CustomView,
                    0, 0);

            String telephoneNumber = "";

            try {
                telephoneNumber = a.getString(R.styleable.CustomView_telephonenumber);
            } catch (Exception e) {
                Log.e("MyCheckBox", "There was an error loading attributes.");
            } finally {
                a.recycle();
            }
        }

        setPreview();
    }

    private void setPreview(){
        if (cardDataMdl != null && cardDataMdl.getShowOnCard().equals("T") && cardViewPositionMdl.getCardDataCode() != null && !TextUtils.isEmpty(cardViewPositionMdl.getCardDataCode())){
            ly_custom.setVisibility(VISIBLE);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(convertViewSizeToPX(cardViewPositionMdl.getLeft(), context), convertViewSizeToPX(cardViewPositionMdl.getTop(), context), 0 , 0);
            ly_custom.setLayoutParams(layoutParams);

            setTitle();
            setImage();
            setTextData();


            //cut out to new class
            if (cardDataMdl.getTypeData().equals(VIDEO_PREVIEW_TYPE)){

                tv_title.setVisibility(GONE);
                img_icon.setVisibility(GONE);
                tv_data_text.setVisibility(GONE);

                Log.d(TAG, "VIDEO_PREVIEW_TYPE");
                VideoView videoView = new VideoView(context);
                videoView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, convertViewSizeToPX(cardViewPositionMdl.getPicWide(), context)));
                videoView.setVideoURI(Uri.parse(cardDataMdl.getLinkURL()));
                ly_custom.addView(videoView);
                videoView.start();
            }


        }else{
            ly_custom.setVisibility(GONE);
        }
    }

    private void setTitle(){
        if (cardViewPositionMdl.getShowTitleText() && cardDataMdl.getTitleText() != null && !TextUtils.isEmpty(cardDataMdl.getTitleText())){
            tv_title.setText(cardDataMdl.getTitleText());
            tv_data_text.setTextColor(Color.parseColor(cardViewPositionMdl.getColor()));
            tv_title.setVisibility(VISIBLE);
        }else {
            tv_title.setVisibility(GONE);
        }
    }

    private void setImage(){
        if (cardViewPositionMdl.getShowImage() && cardDataMdl.getPicPath() != null && !TextUtils.isEmpty(cardDataMdl.getPicPath())){
            setIconPath(cardDataMdl.getPicPath());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(cardViewPositionMdl.getPicWide() != 0 ? convertViewSizeToPX(cardViewPositionMdl.getPicWide(), context) : LayoutParams.WRAP_CONTENT,
                    convertViewSizeToPX(cardViewPositionMdl.getPicHigh(), context));
            img_icon.setLayoutParams(layoutParams);
            img_icon.setAdjustViewBounds(false);

            img_icon.setVisibility(VISIBLE);
        }else {
            img_icon.setVisibility(GONE);
        }
    }

    private void setTextData(){
        if (cardViewPositionMdl.getShowDataText() && cardDataMdl.getDataText() != null && !TextUtils.isEmpty(cardDataMdl.getDataText())){
            tv_data_text.setText(cardDataMdl.getDataText());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setLayoutDirection(TEXT_ALIGNMENT_CENTER);
            tv_title.setLayoutParams(layoutParams);
            tv_data_text.setTextColor(Color.parseColor(cardViewPositionMdl.getColor()));
            tv_data_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, convertTextSizeToPX(cardViewPositionMdl.getTextSize(), context));

            if (!cardViewPositionMdl.getFont().equals("?")) {
                Typeface typeface = ResourcesCompat.getFont(context, getResourceId(cardViewPositionMdl.getFont(), "font", context.getPackageName()));
                tv_data_text.setTypeface(typeface);
                tv_title.setTypeface(typeface);
            }

            tv_data_text.setVisibility(VISIBLE);
        }else {
//            Log.d("setTextData","dataText = GONE" );
            tv_data_text.setVisibility(INVISIBLE);
        }
    }

    View.OnTouchListener onTouchCustomView = new OnTouchListener() {
        private Rect rect;
        private boolean ignore = false;
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if(ignore && motionEvent.getAction() != MotionEvent.ACTION_UP)
                return false;


            if (view == ly_custom) {


                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.setAlpha((float) 0.2);
                        rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());

                        break;
                    case MotionEvent.ACTION_UP:
                        view.setAlpha((float) 1.0);
                        if (!ignore) {
                            if (onCustomItemClickListener != null)
                                onCustomItemClickListener.onCustomItemClickListener(TELEPHONE_TYPE);

//                            new AlertDialogContact(context, cardDataMdl).show();
                            new CardAlertDialogManage(context, cardDataMdl);
                        }

                        ignore = false;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        view.setAlpha((float) 0.2);

                        if (!rect.contains((int) motionEvent.getX()  + view.getLeft(), (int) motionEvent.getY() + view.getTop())){
                            //out side
                            Log.d("ontouch" , "if actionmove" +motionEvent.getX() + " " +  view.getLeft() + " " + view.getRight() + "\n" +
                            motionEvent.getY() + " " +rect.top + " " + rect.bottom );
                            ignore = true;
                            view.setAlpha((float) 1.0);
                        }

                        break;

                    case MotionEvent.ACTION_CANCEL:
                        view.setAlpha((float) 1.0);

                        break;
                }

            }
            return true;
        }
    };



    public interface OnCustomItemClickListener {
        void onCustomItemClickListener(String item);
    }

    public void setOnCustomItemClickListener(OnCustomItemClickListener onCustomItemClickListener){
        this.onCustomItemClickListener = onCustomItemClickListener;
    }

    public TextView getTv_data_text() {
        return tv_data_text;
    }

    public void setTv_data_text(TextView tv_data_text) {
        this.tv_data_text = tv_data_text;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
    }

    public ImageView getImg_icon() {
        return img_icon;
    }

    public void setImg_icon(ImageView img_icon) {
        this.img_icon = img_icon;
    }

    public void setIconPath(String pathImage){
        Glide.with(context).load(pathImage).into(img_icon);
    }

    private int getResourceId(String pVariableName, String pResourceName, String pPackageName) {
        try {
            return getResources().getIdentifier(pVariableName, pResourceName, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int convertViewSizeToPX(int sizeView, Context context){
        String vName = "_"+ (sizeView / 2) + "sdp";
        String rName = "dimen";
        int sizePX = (int) getResources().getDimension(getResourceId(vName , rName, context.getPackageName()));

        return getSizeSupportSmallView(sizePX);
    }

    private int convertTextSizeToPX(int sizeText, Context context){
        String vName = "_"+ (sizeText / 2) + "ssp";
        String rName = "dimen";
        int sizePX = (int) getResources().getDimension(getResourceId( vName , rName, context.getPackageName()));
        return getSizeSupportSmallView(sizePX);
    }

    private int getSizeSupportSmallView(int mSize){
        double sizeSupport = 0;
        if (cardOrientation == ORIENTATION_PORTRAIT && getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ||
                cardOrientation == ORIENTATION_LANDSCAPE && getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            sizeSupport = (mSize / 1.5);

        }else {
            return mSize;
        }
        return (int) Math.round(sizeSupport);
    }



    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }



}
