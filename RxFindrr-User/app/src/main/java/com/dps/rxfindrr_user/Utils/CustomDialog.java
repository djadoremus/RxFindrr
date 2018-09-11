package com.dps.rxfindrr_user.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.dps.rxfindrr_user.R;


public class CustomDialog extends Activity {
    Context _context;
    // int _idOfIcon;
    String _title, _message, positivebutton_desc = "Yes",
            negativebutton_desc = "No";
    Runnable _funcOfPositiveButton;
    Runnable _funcOfNegativeButton;
    boolean _okButtonOnly = false;
    // boolean _icon_visibility = true;

    Dialog dialog;
    boolean cancelable_state = true;

    public static Typeface tfR;
    public static Typeface tfB;

    // Empty Constructor
    public CustomDialog() {

    }

    // Constructor for OK button only
    public CustomDialog(Context context, String title, String message) {
        _context = context;
        // _idOfIcon = idOfIcon;
        _title = title;
        _message = message;
        _okButtonOnly = true;
        setPositiveButton_Desc("OK");
//        tfB = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.font_style_bold));
//        tfR = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.font_style_regular));
    }

    // Constructor for Complete Parameter
    public CustomDialog(Context context, String title, String message,
                        final Runnable funcOfPositiveButton,
                        final Runnable funcOfNegativeButton, boolean isOkButtonOnly) {


        _context = context;
        // _idOfIcon = idOfIcon;
        _title = title;
        _message = message;
        _funcOfPositiveButton = funcOfPositiveButton;
        _funcOfNegativeButton = funcOfNegativeButton;
        _okButtonOnly = isOkButtonOnly;

//        tfB = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.font_style_bold));
//        tfR = Typeface.createFromAsset(context.getAssets(), context .getResources().getString(R.string.font_style_regular));

        if (isOkButtonOnly) {
            setPositiveButton_Desc("OK");
        }

    }

    public void setPositiveButton_Desc(String desc) {
        this.positivebutton_desc = desc;
    }

    public void setNoButton_Desc(String desc) {
        this.negativebutton_desc = desc;
    }

    public void setCancelable(boolean state) {
        cancelable_state = state;
    }

    // public void setButtonIconVisibility(boolean visibility) {
    // _icon_visibility = visibility;
    // }

    public void show() {
        // TODO Auto-generated method stub
        LayoutInflater factory = LayoutInflater.from(_context);
        final View dialogView = factory.inflate(R.layout.dialog_layout, null);

        dialog = new Dialog(_context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);


        if (_title != null || _title.equals("")) {
            TextView lbl_title = (TextView) dialogView
                    .findViewById(R.id.lbl_dialog_title);
            lbl_title.setText(_title);
            lbl_title.setTypeface(tfB, Typeface.NORMAL);
            lbl_title.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    lbl_title.getTextSize());
        }
        if (_message != null || _message.equals("")) {
            TextView lbl_message = (TextView) dialogView
                    .findViewById(R.id.lbl_dialog_msg);
            lbl_message.setText(_message);
            lbl_message.setTypeface(tfR, Typeface.NORMAL);
            lbl_message.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    lbl_message.getTextSize());
        }

        if (_okButtonOnly == true) {
            dialogView.findViewById(R.id.btn_dialog_negative).setVisibility(View.GONE);
            TextView btn_yes = (TextView) dialogView.findViewById(R.id.btn_dialog_positive);
            btn_yes.setText(positivebutton_desc);


            btn_yes.setTypeface(tfB, Typeface.NORMAL);
            btn_yes.setTextSize(TypedValue.COMPLEX_UNIT_PX,btn_yes.getTextSize());
            dialogView.findViewById(R.id.btn_dialog_positive)

                    .setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            if (_funcOfPositiveButton != null) {
                                _funcOfPositiveButton.run();
                            }

                        }
                    });

        } else {
            dialogView.findViewById(R.id.btn_dialog_negative).setVisibility( View.VISIBLE);
            TextView btn_yes = (TextView) dialogView.findViewById(R.id.btn_dialog_positive);
            btn_yes.setText(positivebutton_desc);
            btn_yes.setTypeface(tfB, Typeface.NORMAL);
            btn_yes.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    btn_yes.getTextSize());


            TextView btn_no = (TextView) dialogView
                    .findViewById(R.id.btn_dialog_negative);
            btn_no.setText(negativebutton_desc);
            btn_no.setTypeface(tfB, Typeface.NORMAL);
            btn_no.setTextSize(TypedValue.COMPLEX_UNIT_PX, btn_no.getTextSize());


            dialogView.findViewById(R.id.btn_dialog_positive)
                    .setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            if (_funcOfPositiveButton != null) {
                                _funcOfPositiveButton.run();
                            }

                        }
                    });
            dialogView.findViewById(R.id.btn_dialog_negative)
                    .setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            if (_funcOfNegativeButton != null) {
                                _funcOfNegativeButton.run();
                            }

                        }
                    });
        }
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(this.cancelable_state);
        dialog.show();
    }

}
