package com.rajjim.xisuidan;

import android.widget.ImageButton;
import android.widget.TextView;

public class IbHelper {
    public ImageButton et;
    public int res;
    public int index;
    public boolean selected;
    public int lastindex;
    public TextView tv;
    public IbHelper(ImageButton ib,int resource,int i,boolean s,int last,TextView t){
        et=ib;
        res=resource;
        index=i;
        selected=s;
        lastindex=last;
        tv=t;
    }
}
