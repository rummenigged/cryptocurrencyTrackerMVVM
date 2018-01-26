package com.example.rummenigged.cryptocurrencytrackermvvm.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.rummenigged.cryptocurrencytrackermvvm.R;

/**
 * Created by rummenigged on 25/01/18.
 */

public class DialogsFactory {

    public static AlertDialog.Builder showDialogWithPositiveAndNegativeButton
            (Context context, String title, String msg, int style, int icon){
        AlertDialog.Builder builder = new AlertDialog
                .Builder(
                context
                , style);
        return builder
                .setTitle(title)
                .setMessage(msg)
                .setIcon(icon);
    }
}
