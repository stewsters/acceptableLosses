package acceptableLosses.assets;

import java.util.LinkedHashMap;

public class RecipeType {

    public static LinkedHashMap<String, RecipeType> types;

    public String id;
    public String name;
    public LinkedHashMap<String,Integer> inputs;
    public String transformer;
    public LinkedHashMap<String,Integer> outputs;

}