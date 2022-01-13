package utils;

import data.ChildUpdate;
import entities.Child;
import entities.Gift;
import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;
import java.util.ArrayList;

/**
 * The class contains static methods that helps with parsing.
 *
 * We suggest you add your static methods here or in a similar class.
 */
public final class Utils {
    /**
     * for coding style
     */
    private Utils() {
    }

    /**
     Transforma o categorie din enumul de categorii intr-un string.

     @return Genul sub forma de String.
     */
    public static String categoryToString(final Category category) {
        return switch (category) {
            case BOARD_GAMES -> "Board Games";
            case BOOKS -> "Books";
            case CLOTHES -> "Clothes";
            case SWEETS -> "Sweets";
            case TECHNOLOGY  -> "Technology";
            case TOYS -> "Toys";
        };
    }

    /**
     Transforma un oras din enumul de orase intr-un string.

     @return Genul sub forma de String.
     */
    public static String cityToString(final Cities city) {
        return switch (city) {
            case BUCURESTI -> "Bucuresti";
            case TIMISOARA -> "Timisoara";
            case CONSTANTA -> "Constanta";
            case BUZAU -> "Buzau";
            case CLUJ  -> "Cluj-Napoca";
            case IASI -> "Iasi";
            case CRAIOVA -> "Craiova";
            case BRASOV -> "Brasov";
            case BRAILA -> "Braila";
            case ORADEA -> "Oradea";
        };
    }
    /**
     * Transforms a string into a category
     */
    public static Category stringToCategory(final String category) {
        return switch (category) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * Transforms a string into a city
     */
    public static Cities stringToCities(final String city) {
        return switch (city) {
            case "Bucuresti" -> Cities.BUCURESTI;
            case "Constanta" -> Cities.CONSTANTA;
            case "Buzau" -> Cities.BUZAU;
            case "Timisoara" -> Cities.TIMISOARA;
            case "Cluj-Napoca" -> Cities.CLUJ;
            case "Iasi" -> Cities.IASI;
            case "Craiova" -> Cities.CRAIOVA;
            case "Brasov" -> Cities.BRASOV;
            case "Braila" -> Cities.BRAILA;
            case "Oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * Transforms an array of JSON's into an array of strings
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<String> convertJSONArrayString(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
    /**
     * Transforms an array of JSON's into an array of Gifts
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<Gift> convertJSONArrayGift(final JSONArray array) {
        if (array != null) {
            ArrayList<Gift> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((Gift) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
    /**
     * Transforms an array of JSON's into an array of Child
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<Child> convertJSONArrayChild(final JSONArray array) {
        if (array != null) {
            ArrayList<Child> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((Child) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * Transforms an array of JSON's into an array of ChildUpdate
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<ChildUpdate> convertJSONArrayChildUpdate(final JSONArray array) {
        if (array != null) {
            ArrayList<ChildUpdate> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((ChildUpdate) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
    /**
     * Transforms an array of JSON's into an array of Double
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<Double> convertJSONArrayDouble(final JSONArray array) {
        if (array != null) {
            ArrayList<Double> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((Double) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
}
