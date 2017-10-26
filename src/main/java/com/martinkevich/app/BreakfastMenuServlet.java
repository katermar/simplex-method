package com.martinkevich.app;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by katermar on 10/25/2017.
 */
@WebServlet(
        name = "com.martinkevich.app.BreakfastMenuServlet",
        urlPatterns = {"/breakfast"}
)
public class BreakfastMenuServlet extends HttpServlet {
    Parser menuParser = Parser.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action != null) {
            if (action == "searchByCalories") {
                searchByCalories(req, resp);
            }
        } else {
            menuParser.parse();
            List<Food> result = menuParser.getFoodList();
            forwardList(req, resp, result);
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void searchByCalories(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Food> foodList;
        menuParser.parse();
        String calories = req.getParameter("caloriesAmount");
        foodList = menuParser.getFoodList()
                .stream()
                .filter(dish -> dish.getCalories() == Integer.valueOf(calories))
                .collect(Collectors.toList());
        forwardList(req, resp, foodList);
    }

    private void forwardList(HttpServletRequest req, HttpServletResponse resp, List foodList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("foodList", foodList);
        dispatcher.forward(req, resp);
    }
}
