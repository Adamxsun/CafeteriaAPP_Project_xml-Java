package hangman.groupone.projects.aucsc220.augcafe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final MainActivity mainActivity;

    public SectionsPagerAdapter(FragmentManager fm, MainActivity mainActivity) {
        super(fm);
        this.mainActivity = mainActivity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(mainActivity, position + 1);
    }

    @Override
    public int getCount() {
        return MainActivity.menuTimeSpan;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < 0 || position > MainActivity.menuTimeSpan - 1)
            return null;
        return "SECTION" + (position + 1);
    }
}
