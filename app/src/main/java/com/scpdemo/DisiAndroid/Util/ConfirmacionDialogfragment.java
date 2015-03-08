package com.scpdemo.DisiAndroid.Util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.scpdemo.DisiAndroid.R;

/**
 * Created by rgalvez on 03/03/2015.
 */
public class ConfirmacionDialogfragment extends DialogFragment {

    public final static String TAG = "ConfirmacionDialogfragment";
    private TextView tvConfirmacionSI, tvConfirmacionNO,tvMensaje;

    private ConfirmacionDialogfragmentListener mConfirmacionDialogfragmentListener;

    public interface ConfirmacionDialogfragmentListener {
        void onConfirmacionSI();

        void onConfirmacionNO();
    }

    ;

    public void setmConfirmacionDialogfragmentListener(ConfirmacionDialogfragmentListener mConfirmacionDialogfragmentListener) {
        this.mConfirmacionDialogfragmentListener = mConfirmacionDialogfragmentListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setCancelable(false);

        tvConfirmacionSI = (TextView) view.findViewById(R.id.tvConfirmacionSI);
        tvConfirmacionNO = (TextView) view.findViewById(R.id.tvConfirmacionNO);
        tvMensaje = (TextView) view.findViewById(R.id.tvMensaje);
        tvMensaje.setText(R.string.dialog_save);

        tvConfirmacionSI.setOnClickListener(tvConfirmacionSIOnClickListener);

        tvConfirmacionNO.setOnClickListener(tvConfirmacionNOOnClickListener);

        return view;
    }

    View.OnClickListener tvConfirmacionSIOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (mConfirmacionDialogfragmentListener != null)
                mConfirmacionDialogfragmentListener.onConfirmacionSI();
            dismissAllowingStateLoss();
        }
    };

    View.OnClickListener tvConfirmacionNOOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
//            if (mConfirmacionDialogfragmentListener != null)
//                mConfirmacionDialogfragmentListener.onConfirmacionNO();
            dismissAllowingStateLoss();
        }
    };
}
