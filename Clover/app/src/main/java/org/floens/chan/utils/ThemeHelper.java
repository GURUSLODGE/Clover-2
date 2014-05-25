package org.floens.chan.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;

import org.floens.chan.R;
import org.floens.chan.core.ChanPreferences;

public class ThemeHelper {
    public enum Theme {
        LIGHT("light", R.style.AppTheme),
        DARK("dark", R.style.AppTheme_Dark),
        BLACK("black", R.style.AppTheme_Dark_Black);

        public String name;
        public int resValue;

        private Theme(String name, int resValue) {
            this.name = name;
            this.resValue = resValue;
        }
    }

    private static ThemeHelper instance;
    private int quoteColor;
    private int linkColor;
    private int spoilerColor;

    public static ThemeHelper getInstance() {
        if (instance == null) {
            instance = new ThemeHelper();
        }

        return instance;
    }

    public static void setTheme(Activity activity) {
        activity.setTheme(ThemeHelper.getInstance().getTheme().resValue);
    }

    public ThemeHelper() {
    }

    public Theme getTheme() {
        String themeName = ChanPreferences.getTheme();

        Theme theme = null;
        if (themeName.equals("light")) {
            theme = Theme.LIGHT;
        } else if (themeName.equals("dark")) {
            theme = Theme.DARK;
        } else if (themeName.equals("black")) {
            theme = Theme.BLACK;
        }

        return theme;
    }

    public void reloadPostViewColors(Context context) {
        TypedArray ta = context.obtainStyledAttributes(null, R.styleable.PostView, R.attr.post_style, 0);
        quoteColor = ta.getColor(R.styleable.PostView_quote_color, 0);
        linkColor = ta.getColor(R.styleable.PostView_link_color, 0);
        spoilerColor = ta.getColor(R.styleable.PostView_spoiler_color, 0);
        ta.recycle();
    }

    public int getQuoteColor() {
        return quoteColor;
    }

    public int getLinkColor() {
        return linkColor;
    }

    public int getSpoilerColor() {
        return spoilerColor;
    }
}
