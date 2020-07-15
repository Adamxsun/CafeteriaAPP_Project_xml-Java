package hangman.groupone.projects.aucsc220.augcafe;

import android.app.DialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adam Sun on 2018-01-12.
 */

public class IngredientsDialog extends DialogFragment{
    //Data
    String text_data;//the data before splite
    String[] text_data2;//the data after splite
    Map ingredientMap = new HashMap();//the map which contains ingredient information
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {   View view = inflater.inflate(R.layout.ingredient_dialog,null);
        updateMenuView(view);
        return view;
    }//onCreateView
    /**
     * Read the txt file
     * Dicide and show ingredients on ingredientsDialog
     * @param view FragmentView instance.
     */
    private void updateMenuView(View view) {
        TextView ingredientTextview = view.findViewById(R.id.ingredients_text2);
        InputStream is = getResources().openRawResource(R.raw.augcafrecipe);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try{
            i = is.read();
            while(i != -1){
                byteArrayOutputStream.write(i);
                i = is.read();
            }//try
            is.close();
        }catch(IOException e){
            e.printStackTrace();
        }//catch
        text_data = byteArrayOutputStream.toString();//get txt
        text_data2 = text_data.split("/");//split
        putElementInMap(text_data2);//put into map
        //get menu
        String meal = MainActivity.getInstance().getMenuCalendar().getActiveMenu().getBreakfastTitle();
        meal = meal + MainActivity.getInstance().getMenuCalendar().getActiveMenu().getLunchTitle();
        meal = meal + MainActivity.getInstance().getMenuCalendar().getActiveMenu().getSupperTitle();

        String []mealList = meal.split("-");//split menu data
        trim(mealList);//delete space

        meal = meal.replace("-","");//replace -
        String result = "";
        result = marchMealData(mealList,result);//march menudata and map
        result = emptyException(result);//check if it is empty
        ingredientTextview.setText(result);//output
    }//updateMenuView
    /**
     * Return information when the String is empty
     * @param result
     */
    public String emptyException (String result)
    {
        result.trim();
        if (result == "")
        {
            return "No ingredient information";
        }//if
        else
        {
            return result;
        }//else
    }//EmptyException
    /**
     * Return String Array after using String.trim() in each line
     * @param mealList
     */
    public String[] trim (String[] mealList)
    {
        for (int counter = 0; counter < mealList.length; counter++)
        {
            mealList[counter] = mealList[counter].trim();
        }//for
        return mealList;
    }//end of trim
    /**
     * Return ingredient when it is stored in txt data
     * @param mealList
     * @param result
     */
    public String marchMealData (String[] mealList, String result)
    {
        for (int i2 = 0; i2 < mealList.length; i2 ++)
        {
            if (ingredientMap.get(mealList[i2])!= null)
            {
                result = result + mealList[i2] + ":" + ingredientMap.get(mealList[i2])+ "\n";
            }//if
        }//for
        return result;
    }//end of marchMealData
    /**
     * Put ingredient data into map
     * @param firstArray
     */
    public void putElementInMap (String[] firstArray)
    {
        for (int i=1 ; i < firstArray.length ; i++)
        {
            String [] Secondsplit = firstArray[i].split(",");
            String foodtitleData = Secondsplit[0];
            String ingredientData = "";
            for (int i2 = 1; i2 < Secondsplit.length ; i2++)
            {
                ingredientData = ingredientData + Secondsplit[i2];
            }//for
            ingredientMap.put(foodtitleData,ingredientData);
        }//for
    }//end of PutElementInMap
}//IngredientsDialog
