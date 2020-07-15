/**
 * MainActivity.java
 *
 * Sets up the screen and handles all main activity's:
 * Methods for next and previous date buttons
 * Methods for action bar and its buttons (clock and calendar)
 */

package hangman.groupone.projects.aucsc220.augcafe;

import android.app.Dialog;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.MissingFormatArgumentException;

import hangman.groupone.projects.aucsc220.augcafe.listeners.ContactInfoButtonListener;
import hangman.groupone.projects.aucsc220.augcafe.listeners.DateChangeListener;
import hangman.groupone.projects.aucsc220.augcafe.menuholders.MenuCalendar;
import hangman.groupone.projects.aucsc220.augcafe.services.DefaultDataRepository;
import hangman.groupone.projects.aucsc220.augcafe.services.MenuRepository;
import hangman.groupone.projects.aucsc220.augcafe.services.ProtoTypeRepository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int menuTimeSpan = 14;
    private MenuCalendar menuCalendar;
    Toast toast;
    CharSequence toastText;

    private static MainActivity instance;

    public MainActivity(){
        instance = this;
    }

    public static MainActivity getInstance(){
        return instance;
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Calendar calendar = Calendar.getInstance();
        menuCalendar = new MenuCalendar(calendar);

        try {
            InputStream inputStream = getAssets().open("cafe_data_dump.csv");
            MenuRepository menuRepository = new DefaultDataRepository(inputStream);
            Calendar startDate = Calendar.getInstance();
            setupStartDateCalendar(menuCalendar.getCalendar(), startDate);
            menuCalendar.setMenuDates(menuRepository.getMenuDays(startDate));
        } catch (IOException e) {
            e.printStackTrace();
            this.finish();
        }
        getSupportActionBar().setTitle("");
        setupMenuView();

        ImageButton nextDateButton = (ImageButton) findViewById(R.id.next_date_button);
        ImageButton previousDateButton = (ImageButton) findViewById(R.id.previous_date_button);
        nextDateButton.setOnClickListener(this);
        previousDateButton.setOnClickListener(this);
    }//onCreate

    private void setupStartDateCalendar(final Calendar referenceCalendar, Calendar startCalendar) {
        startCalendar.set(Calendar.DAY_OF_MONTH, referenceCalendar.get(Calendar.DAY_OF_MONTH));
        startCalendar.set(Calendar.MONTH, referenceCalendar.get(Calendar.MONTH));
        startCalendar.set(Calendar.YEAR, referenceCalendar.get(Calendar.YEAR));
        startCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    }

    /**
     * Handles clicks for the next date and previous date buttons.
     * next_date_button and previous_date_button mimic a swipe to change dates.
     *
     * @param view
     */
    public void onClick(View view) {
        int currentFragment = menuCalendar.getCurrentDateIndex() - 1;//Index is one ahead of position

        //resets toast if button is pressed multiple times
        if (toast != null) {
            toast.cancel();
        }//if

        if (view.getId() == R.id.next_date_button) {
            mViewPager.setCurrentItem(currentFragment + 1);
            //if button is pressed and you are at the last fragment
            //a toast will popup telling the user you are at the beginning
            if (currentFragment == 13) {
                Context context = getApplicationContext();
                toastText = "End of Week";
                int toastDuration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, toastText, toastDuration);
                toast.show();
            }//inner if
        }//if

        else if (view.getId() == R.id.previous_date_button) {
            mViewPager.setCurrentItem(currentFragment - 1);
            //if button is pressed and you are at the first fragment
            //a toast will popup telling the user you are at the beginning
            if (currentFragment == 0) {
                Context context = getApplicationContext();
                toastText = "Beginning of Week";
                int toastDuration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, toastText, toastDuration);
                toast.show();
            }//inner if
        }//else if

        else if (view.getId() == R.id.ingredients_button) {
            AlertDialog.Builder detailsBuilder = new AlertDialog.Builder(this);
            detailsBuilder.setMessage("Ingredients");
            detailsBuilder.show();
        }//else if
    }//onClick

    /**
     * Determines what meal is displayed upon opening the app based on the time
     * If it is past one meal time, it will display the next
     */
    private void setupMenuView() {
        mViewPager.addOnPageChangeListener(new DateChangeListener(this));
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes();
        //if supper is done, menu will move to tomorrow's breakfast
        if(Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek){
            if (hours >= 18){
                mViewPager.setCurrentItem(menuCalendar.getCurrentDateIndex());
            }//Sat and Sun, after 6 pm, the menu changes to next breakfast
            else{
                mViewPager.setCurrentItem(menuCalendar.getCurrentDateIndex() - 1);
            }//Sat and Sun, before supper finish, still in current date's menu
        }//if
        else if(Calendar.FRIDAY == dayOfWeek || Calendar.THURSDAY == dayOfWeek ||
                Calendar.WEDNESDAY == dayOfWeek || Calendar.TUESDAY == dayOfWeek||
                Calendar.MONDAY == dayOfWeek){
            if(hours == 18 && minutes >= 30){
                mViewPager.setCurrentItem(menuCalendar.getCurrentDateIndex());
            }//Mon and Fri, after 6 pm, the menu changes to next breakfast
            else if (hours > 18){
                mViewPager.setCurrentItem(menuCalendar.getCurrentDateIndex());
            }//else if
            else{
                mViewPager.setCurrentItem(menuCalendar.getCurrentDateIndex() - 1);
            }//Mon to Fri, before supper finish, still in current date's menu
        }//else if
    }

    public MenuCalendar getMenuCalendar() {
        return menuCalendar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles action bar clicks
     * Uses id to determine button pressed then uses dialogs to display
     * meal times or the calendar
     * @param item What is clicked
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_clock:
                Dialog hoursOfOperationDialog = new Dialog(this);
                hoursOfOperationDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                hoursOfOperationDialog.setContentView(R.layout.hours_of_operation_dialog);
                hoursOfOperationDialog.show();
                return true;

            case R.id.action_calendar:
                CalendarDialog calendarDialog = new CalendarDialog(this);
                calendarDialog.createCalendarDialog();
                calendarDialog.setButtonDates();
                calendarDialog.setButtonFunction(mViewPager);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }//switch
    }//onOptionsItemSelected

    /**
     * Active the ingredients button and display dialog
     * @param v
     */
    public void showIngredient(View v)
    {
        FragmentManager manager = getFragmentManager();
        IngredientsDialog IngredientDialog = new IngredientsDialog();
        IngredientDialog.show(manager,"ingredients");
    }//showIngredient

}
//MainActivity
