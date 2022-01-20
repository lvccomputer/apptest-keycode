package com.example.apptest;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cuonglv on 1/20/2022
 */
public class PasscodeView extends RelativeLayout {

    private ViewGroup viewGroup;
    private ArrayList<TextView> tvCodeArrayList;
    private ArrayList<View> viewLineArrayList;
    private int count = 0;

    private PassCodeViewListener mPassCodeViewListener;

    public PasscodeView(Context context) {
        super(context);
        init(context);
    }

    public PasscodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasscodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setPassCodeViewListener(PassCodeViewListener mPassCodeViewListener) {
        this.mPassCodeViewListener = mPassCodeViewListener;
    }

    private void init(Context context) {
        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_passcode, this);
        initTextViews();
        initLine();
    }

    private void initTextViews() {
        if (tvCodeArrayList != null && !tvCodeArrayList.isEmpty()) return;
        tvCodeArrayList = new ArrayList<>();
        tvCodeArrayList.add(viewGroup.findViewById(R.id.tv_1));
        tvCodeArrayList.add(viewGroup.findViewById(R.id.tv_2));
        tvCodeArrayList.add(viewGroup.findViewById(R.id.tv_3));
        tvCodeArrayList.add(viewGroup.findViewById(R.id.tv_4));
        tvCodeArrayList.add(viewGroup.findViewById(R.id.tv_5));
        tvCodeArrayList.add(viewGroup.findViewById(R.id.tv_6));
    }

    private void initLine() {
        if (viewLineArrayList != null && !viewLineArrayList.isEmpty()) return;
        viewLineArrayList = new ArrayList<>();
        viewLineArrayList.add(viewGroup.findViewById(R.id.line1));
        viewLineArrayList.add(viewGroup.findViewById(R.id.line2));
        viewLineArrayList.add(viewGroup.findViewById(R.id.line3));
        viewLineArrayList.add(viewGroup.findViewById(R.id.line4));
        viewLineArrayList.add(viewGroup.findViewById(R.id.line5));
        viewLineArrayList.add(viewGroup.findViewById(R.id.line6));
    }

    public void setPasscodeValue(String value) {
        if (value == null) return;
        if (count == tvCodeArrayList.size()) return;
        for (int i = 0; i < tvCodeArrayList.size(); i++) {
            if (TextUtils.isEmpty(tvCodeArrayList.get(i).getText().toString())) {
                tvCodeArrayList.get(i).setText(value);
                viewLineArrayList.get(i).setBackgroundColor(getContext().getResources().getColor(R.color.selected));
                count++;
                break;
            }
        }
    }

    public void clearPassCodeValue() {
        if (count == 0) return;
        tvCodeArrayList.get(count - 1).setText("");
        viewLineArrayList.get(count - 1).setBackgroundColor(getContext().getResources().getColor(R.color.unselect));
        if (count > 0)
            count--;
    }

    public interface PassCodeViewListener {
        void onPassCodeChanged(String value);
    }
}
