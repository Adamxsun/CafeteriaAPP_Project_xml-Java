/**
 * PlaceholderFragment.java
 *
 * This class handles the different portions of the screen or fragments
 * It stores the data from each fragment (meals) and determines what is
 * shown when a swipe occurs
 */
package hangman.groupone.projects.aucsc220.augcafe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import hangman.groupone.projects.aucsc220.augcafe.listeners.ContactInfoButtonListener;
import hangman.groupone.projects.aucsc220.augcafe.listeners.MealButtonListener;
import hangman.groupone.projects.aucsc220.augcafe.menuholders.MenuDay;

/**
 * Responsible for dynamically creating the fragment views
 * as they are required.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_BREAKFAST = "view_breakfast";
    public static final String ARG_LUNCH = "view_lunch";
    public static final String ARG_DINNER = "view_dinner";
    public static final String ARG_WEEKEND = "view_is_weekend";
    public static final String WEEK_BREAKFAST_TIME = "7:30 to 11:00am";
    public static final String WEEK_LUNCH_TIME = "11:45am to 2:00pm";
    public static final String WEEK_SUPPER_TIME = "5:00 to 6:30pm";
    public static final String END_BREAKFAST_TIME = "8:00 to 11:00am";
    public static final String END_LUNCH_TIME = "12:00 to 1:30pm";
    public static final String END_SUPPER_TIME = "4:45 to 6:00pm";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(MainActivity mainActivity, int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        applyPlaceholderFragmentArguments(sectionNumber, mainActivity, fragment);
        return fragment;
    }

    /**
     * Stores any required data in the fragment instance so it may be accessed later.
     * Such data includes what meals are being served on the day the fragment represents.
     * @param sectionNumber Fragment's position.
     * @param mainActivity Instance of MainActivity.
     * @param fragment Instance of the fragment.
     */
    private static void applyPlaceholderFragmentArguments(final int sectionNumber,
                                                          final MainActivity mainActivity,
                                                          final Fragment fragment) {
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        MenuDay menu = mainActivity.getMenuCalendar().getCafeDateAtlndex(sectionNumber - 1);
        args.putString(ARG_BREAKFAST, menu.getBreakfastTitle());
        args.putString(ARG_LUNCH, menu.getLunchTitle());
        args.putString(ARG_DINNER, menu.getSupperTitle());
        setWeekendArgs(args, sectionNumber);
        fragment.setArguments(args);
    }
    
    private static void setWeekendArgs(Bundle args, int sectionNumber) {
        if (isFragmentPageAWeekend(sectionNumber)) {
            args.putBoolean(ARG_WEEKEND, true);
        } else {
            args.putBoolean(ARG_WEEKEND, false);
        }
    }

    /**
     * Determines if a page fragment represents a weekend or not.
     *
     * It is important that the isWeekend variable remains. If multiple
     * return statements are added, a bug ("feature") will merge all
     * return statements into one return statement and the code will
     * run incorrectly during debug while in Android Studio.
     * @param position Position of the page fragment
     * @return true if page represents a weekend, false otherwise.
     */
    private static boolean isFragmentPageAWeekend(int position) {
        boolean isWeekend;
        if (position > 7) {
            position -= 7;
        }
        if (position == 1 || position == 7) {
            isWeekend = true;
        } else {
            isWeekend = false;
        }
        return isWeekend;
    }

    /**
     * Creates the menu page views dynamically as the user swipes between pages.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        updateMenuView(rootView);

        registerMealButtonListeners(rootView);
        return rootView;
    }

    /**
     * Decides and shows what meal the user should see when swiping between days.
     * @param rootView FragmentView instance.
     */
    private void updateMenuView(View rootView) {
        TextView textView = rootView.findViewById(R.id.meal_text_view);
        //track the current time
        String meal_breakfast = getArguments().getString(ARG_BREAKFAST);
        String meal_lunch = getArguments().getString(ARG_LUNCH);
        String meal_supper = getArguments().getString(ARG_DINNER);
        Button breakfastButton = rootView.findViewById(R.id.meal_button_breakfast);
        Button lunchButton = rootView.findViewById(R.id.meal_button_lunch);
        Button supperButton = rootView.findViewById(R.id.meal_button_dinner);
        View firstButtonDivider = rootView.findViewById(R.id.firstDivider);
        View secondButtonDivider = rootView.findViewById(R.id.secondDivider);
        TextView timeTextView = rootView.findViewById(R.id.time_text_view);

        final int TRANSPARENT = Color.parseColor("#00000000");


        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes();

        if(getArguments().getBoolean(ARG_WEEKEND)){
            if(hours > 10 && hours < 13){
                lunchButton.setBackgroundColor(Color.parseColor("#fafafa"));
                firstButtonDivider.setBackgroundColor(TRANSPARENT);
                secondButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_lunch);
                timeTextView.setText(END_LUNCH_TIME);
            }//11:00 - 12:59 lunch
            else if(hours == 13 && minutes <= 29) {
                lunchButton.setBackgroundColor(Color.parseColor("#fafafa"));
                firstButtonDivider.setBackgroundColor(TRANSPARENT);
                secondButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_lunch);
                timeTextView.setText(END_LUNCH_TIME);
            }//13:00 - 13:29 lunch
            else if(hours == 13 && minutes > 29) {
                supperButton.setBackgroundColor(Color.parseColor("#fafafa"));
                secondButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_supper);
                timeTextView.setText(END_SUPPER_TIME);
            }//13:30 - 13:59 supper
            else if(hours > 13 && hours < 18){
                supperButton.setBackgroundColor(Color.parseColor("#fafafa"));
                secondButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_supper);
                timeTextView.setText(END_SUPPER_TIME);
            }//14:00 - 17:59 supper
            else if (hours >= 18 || hours < 11){
                breakfastButton.setBackgroundColor(Color.parseColor("#fafafa"));
                firstButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_breakfast);
                timeTextView.setText(END_BREAKFAST_TIME);
            }//18:00 - 23:59 breakfast
        }//Saturday and Sunday

        else {
            if(hours >= 11 && hours < 14){
                lunchButton.setBackgroundColor(Color.parseColor("#fafafa"));
                firstButtonDivider.setBackgroundColor(TRANSPARENT);
                secondButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_lunch);
                timeTextView.setText(WEEK_LUNCH_TIME);
            }//11:00 -13:59 lunch
            else if(hours >= 14 && hours < 18){
                supperButton.setBackgroundColor(Color.parseColor("#fafafa"));
                secondButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_supper);
                timeTextView.setText(WEEK_SUPPER_TIME);
            }//14:00 - 17:59 supper
            else if(hours == 18 && minutes < 30){
                supperButton.setBackgroundColor(Color.parseColor("#fafafa"));
                secondButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_supper);
                timeTextView.setText(WEEK_SUPPER_TIME);
            }//18:00 - 18:29 supper
            else{
                breakfastButton.setBackgroundColor(Color.parseColor("#fafafa"));
                firstButtonDivider.setBackgroundColor(TRANSPARENT);
                textView.setText(meal_breakfast);
                timeTextView.setText(WEEK_BREAKFAST_TIME);
            }//18:30 - 10:59 breakfast
        }//Monday - Friday

    }

    /**
     * Registers the meal button listeners so the user can see all the meals
     * for each day.
     * @param rootView FragmentView instance.
     */
    private void registerMealButtonListeners(View rootView) {
        MealButtonListener mealButtonListener = new MealButtonListener(rootView, getArguments());
        MainActivity instance = MainActivity.getInstance();
        ContactInfoButtonListener contactInfoButtonListener = new ContactInfoButtonListener(instance);

        Button breakfastButton = rootView.findViewById(R.id.meal_button_breakfast);
        Button lunchButton = rootView.findViewById(R.id.meal_button_lunch);
        Button supperButton = rootView.findViewById(R.id.meal_button_dinner);
        Button contactInfoButton = rootView.findViewById(R.id.contact_info_button);

        breakfastButton.setOnClickListener(mealButtonListener);
        lunchButton.setOnClickListener(mealButtonListener);
        supperButton.setOnClickListener(mealButtonListener);
        contactInfoButton.setOnClickListener(contactInfoButtonListener);
    }
}
