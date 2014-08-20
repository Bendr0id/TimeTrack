package com.vidi.timetrack;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

public class LoadingOverlay extends Dialog
{
    public LoadingOverlay(Context context, int theme)
    {
        super(context, R.style.TransparentOverlay);
    }

    public LoadingOverlay(Context context)
    {
        this(context, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.di_loading_overlay);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onBackPressed()
    {

    }
}
