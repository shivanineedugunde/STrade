package com.project.strade.onboarding;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.project.strade.R;
import com.project.strade.databinding.FragmentScreenTwoBinding;
import com.project.strade.others.Utils;

import org.jetbrains.annotations.NotNull;

public class ScreenTwo extends Fragment {
    FragmentScreenTwoBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScreenTwoBinding.inflate(inflater, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.color_two));
        }

        View view = binding.getRoot();

        ViewPager2 viewPager2 = getActivity().findViewById(R.id.viewPager);

        binding.next2.setOnClickListener(v -> viewPager2.setCurrentItem(2));

        binding.skip2.setOnClickListener(v -> {
            Utils.onBoardingDone(requireContext());
            Navigation.findNavController(v).navigate(R.id.action_viewPagerFragment_to_registerFragment);
        });
        return view;
    }
}