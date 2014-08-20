package com.vidi.timetrack.navigation;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vidi.timetrack.R;

@EViewGroup(R.layout.li_navigation_drawer)
public class NavigationItem extends RelativeLayout
{
	@ViewById
	TextView navigationMenuItemText;

	@ViewById
	ImageView navigationMenuItemIcon;

	public NavigationItem(Context context)
	{
		super(context);
	}

	public void bind(NavigationEntry navigationMenuEntry)
	{
		navigationMenuItemText.setText(navigationMenuEntry.getText());
		navigationMenuItemIcon.setImageResource(navigationMenuEntry.getIcon());
	}
}
