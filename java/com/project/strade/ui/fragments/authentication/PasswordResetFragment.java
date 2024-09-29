package com.project.strade.ui.fragments.authentication;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.project.strade.R;
import com.google.firebase.auth.FirebaseAuth;
import com.project.strade.databinding.FragmentPasswordResetBinding;
import com.project.strade.others.Utils;

import org.jetbrains.annotations.NotNull;

public class PasswordResetFragment extends DialogFragment {

    FragmentPasswordResetBinding binding;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPasswordResetBinding.inflate(inflater, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.color_two));
        }

        View view = binding.getRoot();
        binding.dialogConfirm.setOnClickListener(v -> {

            Utils.hideSoftKeyboard(requireActivity());

            if (TextUtils.isEmpty(binding.userEmail.getText().toString())) {
                binding.userEmail.setError("Required");
                return;

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.userEmail.getText().toString()).matches()) {
                binding.userEmail.setError("Invalid email format");
                return;

            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(binding.userEmail.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Check Your Email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Email does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show());
        });
        return view;
    }
}