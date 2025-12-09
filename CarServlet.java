package com.drivedeal.servlet;
import com.drivedeal.dao.*; import com.drivedeal.model.Car;
import jakarta.servlet.*; import jakarta.servlet.http.*; import jakarta.servlet.annotation.*; import java.io.IOException; import java.util.*;
@WebServlet(name="CarServlet", urlPatterns={"/cars"})
public class CarServlet extends HttpServlet {
  private CarDAO carDAO = new CarDAO();
  @Override public void init(){ DBUtil.init(getServletContext()); }
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String make = req.getParameter("make"); String fuel = req.getParameter("fuel");
    Double minPrice = (req.getParameter("minPrice")==null || req.getParameter("minPrice").isBlank()) ? null : Double.valueOf(req.getParameter("minPrice"));
    Double maxPrice = (req.getParameter("maxPrice")==null || req.getParameter("maxPrice").isBlank()) ? null : Double.valueOf(req.getParameter("maxPrice"));
    try { req.setAttribute("cars", carDAO.search(make, fuel, minPrice, maxPrice)); req.getRequestDispatcher("/index.jsp").forward(req, resp); }
    catch(Exception e){ throw new ServletException(e); }
  }
}