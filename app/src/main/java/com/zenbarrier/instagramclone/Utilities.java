package com.zenbarrier.instagramclone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Anthony on 10/26/2016.
 * This file has misc functions for use in other class files.
 */

class Utilities {
    static void setKeyboardHideOnClick(final Context context, View view){
        if(!(view instanceof EditText)){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            });
        }
        if(view instanceof ViewGroup){
            for(int i = 0 ; i < ((ViewGroup) view).getChildCount() ; i++){
                setKeyboardHideOnClick(context, ((ViewGroup) view).getChildAt(i));
            }
        }
    }
}
