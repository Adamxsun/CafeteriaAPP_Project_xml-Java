/**
 * Meal.java
 * Created by Adam Sun on 2018-01-08.
 *
 * Gets and sets each meal from the MenuRepository
 * Also gets and sets information about special diets
 * Meal is an object that gets passed into MenuDay
 */

package hangman.groupone.projects.aucsc220.augcafe;

public class Meal {
    //data
    private String title;
    private String [] ingredients;
    private boolean isVegitarian;
    private boolean isGlutenFree;
    private boolean isLactoseFree;
    private boolean isHalal;
    private boolean hasFish;

    /**
     * Object
     * Store food data
     * @param foodtitle,
     * @param foodIngredients
     * @param Vegitarian
     * @param GlutenFree
     * @param LactoseFree
     * @param Halal
     * @param Fish
     */
    public Meal(String foodtitle,String[] foodIngredients, boolean Vegitarian, boolean GlutenFree, boolean LactoseFree, boolean Halal, boolean Fish)
    {
        title = foodtitle;
        ingredients = foodIngredients;
        isVegitarian = Vegitarian;
        isGlutenFree = GlutenFree;
        isLactoseFree = LactoseFree;
        isHalal = Halal;
        hasFish = Fish;
    }//constructor all argument

    public Meal(String foodtitle,String[] foodIngredients)
    {
        this(foodtitle,foodIngredients,false,false,false,false,false);
    }//constructor without special diets

    public Meal(String foodtitle)
    {
        this(foodtitle,new String[]{"unknown"},false,false,false,false,false);
    }//constructor without ingredients

    public Meal()
    {
        this("unknown food",new String[]{"unknown"},false,false,false,false,false);
    }//default constructor

    // print object element in one string
    public String printTest()
    {   String ingredientsLine="";
        for (int i=0;i<ingredients.length;i++)
        {
            ingredientsLine = ingredientsLine + ingredients[i]+",";
        }
        return "\n" +title + "\n" +ingredientsLine + "  " +
                "Vegitarian :" + isVegitarian + " " + "GlutenFree:" + isGlutenFree + " " + "LactoseFree:" + isLactoseFree
                + " " + "Halal:" +isHalal + " " + "hasfish:" + hasFish;
    }

    public void setVegitarian()
    {
        isVegitarian = true;
    }

    public void setGlutenFree()
    {
        isGlutenFree = true;
    }

    public void setLactoseFree()
    {
        isLactoseFree = true;
    }

    public void setHalal()
    {
        isHalal = true;
    }

    public void setHasFish()
    {
        hasFish = true;
    }

    public String getTitle() {return title;}

    public String[] getIngredients(){return ingredients;}

    public boolean getVegitarian(){return isVegitarian;}

    public boolean getGlutenFree() {return isGlutenFree;}

    public boolean getLactoseFree() {return isLactoseFree;}

    public boolean getHalal() {return isHalal;}

    public boolean getHasFish() {return hasFish;}
}
