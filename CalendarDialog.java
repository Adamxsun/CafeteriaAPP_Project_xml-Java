/**
 * CalendarDialog.java
 * Created by Justin Ikenouye on 15/01/2018
 *
 * Handles calendar dialog functionality:
 * creates the dialog and its buttons (and their texts)
 * enables button presses to change the fragment
 */
package hangman.groupone.projects.aucsc220.augcafe;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Calendar;

public class CalendarDialog {
    private Dialog calendarDialog;

    private Button week1Sunday;
    private Button week1Monday;
    private Button week1Tuesday;
    private Button week1Wednesday;
    private Button week1Thursday;
    private Button week1Friday;
    private Button week1Saturday;

    private Button week2Sunday;
    private Button week2Monday;
    private Button week2Tuesday;
    private Button week2Wednesday;
    private Button week2Thursday;
    private Button week2Friday;
    private Button week2Saturday;


    /**
     * Constructor that sets the calendarDialog to a new Dialog in the MainActivity context
     * @param context
     */
    public CalendarDialog(Context context){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        calendarDialog = dialog;
    }//CalendarDialog

    /**
     * Creates the calendar dialog and its buttons
     * These buttons correspond to each day in the
     * 14 day calendar of this week and the next.
     */
    public void createCalendarDialog() {
        calendarDialog.setContentView(R.layout.calendar_layout);
        calendarDialog.show();

        week1Sunday = calendarDialog.findViewById(R.id.week1_sunday);
        week1Monday = calendarDialog.findViewById(R.id.week1_monday);
        week1Tuesday = calendarDialog.findViewById(R.id.week1_tuesday);
        week1Wednesday = calendarDialog.findViewById(R.id.week1_wednesday);
        week1Thursday = calendarDialog.findViewById(R.id.week1_thursday);
        week1Friday = calendarDialog.findViewById(R.id.week1_friday);
        week1Saturday = calendarDialog.findViewById(R.id.week1_saturday);

        week2Sunday = calendarDialog.findViewById(R.id.week2_sunday);
        week2Monday = calendarDialog.findViewById(R.id.week2_monday);
        week2Tuesday = calendarDialog.findViewById(R.id.week2_tuesday);
        week2Wednesday = calendarDialog.findViewById(R.id.week2_wednesday);
        week2Thursday = calendarDialog.findViewById(R.id.week2_thursday);
        week2Friday = calendarDialog.findViewById(R.id.week2_friday);
        week2Saturday = calendarDialog.findViewById(R.id.week2_saturday);
    }//createCalendarDialog

    /**
     * Sets the text of each calendar button to the corresponding date
     * Uses Java Calendar class to track dates
     */
    public void setButtonDates() {
        Calendar calendar = Calendar.getInstance();

        //Need to get sunday's date to start off the dates on the calendar
        //Prevents having to find out how far backwards the dates go from the current
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
            calendar.add(Calendar.DATE, -1);

        //Sets button text to date the calendar is currently at
        //Increments the date by one for the next button
        week1Sunday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week1Monday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week1Tuesday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week1Wednesday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week1Thursday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week1Friday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week1Saturday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week2Sunday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week2Monday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week2Tuesday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week2Wednesday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week2Thursday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week2Friday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);

        week2Saturday.setText(Integer.toString(calendar.get(Calendar.DATE)) + " ");
        calendar.add(Calendar.DATE, 1);
    }//setButtonDates

    /**
     * Creates onClick functionality for each button
     * Each buttons function is to set the current page to its date,
     * so the page index gets passed in based on what button it is
     * eg. 0 for the week1Sunday because it is fragment 0
     * @param viewPager
     */
    public void setButtonFunction(ViewPager viewPager) {
        final ViewPager mViewPager = viewPager;
        week1Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
                calendarDialog.dismiss();
            }
        });//week1Sunday onClickListener

        week1Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
                calendarDialog.dismiss();
            }
        });//week1Monday onClickListener

        week1Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(2);
                calendarDialog.dismiss();
            }
        });//week1Tuesday onClickListener

        week1Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(3);
                calendarDialog.dismiss();
            }
        });//week1Wednesday onClickListener

        week1Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(4);
                calendarDialog.dismiss();
            }
        });//week1Thursday onClickListener

        week1Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(5);
                calendarDialog.dismiss();
            }
        });//week1Friday onClickListener);

        week1Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(6);
                calendarDialog.dismiss();
            }
        });//week1Saturday onClickListener

        week2Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(7);
                calendarDialog.dismiss();
            }
        });//week2Sunday onClickListener

        week2Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(8);
                calendarDialog.dismiss();
            }
        });//week2Monday onClickListener

        week2Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(9);
                calendarDialog.dismiss();
            }
        });//week2Tuesday onClickListener

        week2Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(10);
                calendarDialog.dismiss();
            }
        });//week2Wednesday onClickListener

        week2Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(11);
                calendarDialog.dismiss();
            }
        });//week2Thursday onClickListener

        week2Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(12);
                calendarDialog.dismiss();
            }
        });//week2Friday onClickListener

        week2Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(13);
                calendarDialog.dismiss();
            }
        });//week2Saturday onClickListener
    }//createCalendarDialog
}//CalendarDialog


