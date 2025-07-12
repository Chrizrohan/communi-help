package com.example.communihelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class NotifyPagerAdapter extends FragmentStateAdapter {

    public NotifyPagerAdapter(@NonNull AppCompatActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return (position == 0) ? new Notify1Fragment() : new Notify2Fragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
