package com.example.a23;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getString(R.string.dialog_title);
        String message = getString(R.string.dialog_message);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
            .setIconAttribute(android.R.attr.alertDialogIcon)
            .setTitle(title)
            .setMessage(message);

        return builder.create();
    }
}