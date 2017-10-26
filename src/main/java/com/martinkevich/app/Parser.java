package com.martinkevich.app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by katermar on 10/4/2017.
 */
public class Parser {
    private static Parser ourInstance = new Parser();
    private List<Food> foodList;

    public List<Food> getFoodList() {
        return foodList;
    }

    public static Parser getInstance() {
        return ourInstance;
    }

    private Parser() {
    }

    void parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setIgnoringComments(true);
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse("file:///D:/JAVA PROJECTS/labsUniversity/src/sitairis/lab4/XML.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element lib = doc.getDocumentElement();
        if ("breakfast_menu".equals(lib.getTagName())) {
            NodeList foods = lib.getElementsByTagName("food");

            foodList = new ArrayList<>();

            for (int i = 0; i < foods.getLength(); ++i) {
                Element food = (Element) foods.item(i);

                Creator foodCreator = new FoodCreator();

                String name = null;
                String description = null;
                String price = null;
                String calories = null;

                Set<String> ingredients = new TreeSet<>();
                NodeList props = food.getElementsByTagName("*");

                for (int j = 0; j < props.getLength(); ++j) {
                    Element prop = (Element) props.item(j);
                    if ("name".equals(prop.getTagName())) {
                        name = prop.getTextContent();
                    }
                    if ("price".equals(prop.getTagName())) {
                        price = prop.getTextContent();
                    }
                    if ("ingredients".equals(prop.getTagName())) {
                        NodeList all = food.getElementsByTagName("ingredient");
                        for (int k = 0; k < all.getLength(); k++) {
                            ingredients.add(((Element) all.item(k)).getTextContent());
                        }
                    }
                    if ("description".equals(prop.getTagName())) {
                        description = prop.getTextContent();
                        description.trim();
                        calories = prop.getAttribute("calories");
                    }
                }
                Product foodProduct = foodCreator.factoryMethod();

                if (foodProduct.getClass().toString().equals("class sitairis.lab5.com.martinkevich.app.Food")) {
                    foodProduct = (Food)foodProduct;
                    if (name != null) {
                        ((Food) foodProduct).setName(name);
                    }
                    if (ingredients.size() > 0) {
                        ((Food) foodProduct).setIngredients(ingredients);
                    }
                    if (description != null) {
                        ((Food) foodProduct).setDescription(description);
                        if (calories != null) {
                            ((Food) foodProduct).setCalories(Integer.valueOf(calories));
                        }
                    }
                    if (price != null) {
                        ((Food) foodProduct).setPrice(price);
                    }
                    foodList.add((Food) foodProduct);
                }
            }
        }
    }
}
