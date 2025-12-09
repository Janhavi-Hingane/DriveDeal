package com.drivedeal.servlet;
import com.drivedeal.dao.*; import com.drivedeal.model.Car;
import jakarta.servlet.*; import jakarta.servlet.http.*; import jakarta.servlet.annotation.*; import java.io.IOException;
@WebServlet(name="AdminCarServlet", urlPatterns={"/admin/cars"})
public class AdminCarServlet extends HttpServlet {
  private CarDAO carDAO = new CarDAO();
  @Override public void init(){ DBUtil.init(getServletContext()); }
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try { req.setAttribute("cars", carDAO.search(null,null,null,null)); req.getRequestDispatcher("/admin/cars.jsp").forward(req, resp); }
    catch(Exception e){ throw new ServletException(e); }
  }
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    try {
      if("create".equals(action)){ Car c = build(req); carDAO.create(c); }
      else if("update".equals(action)){ Car c = build(req); c.setId(Integer.parseInt(req.getParameter("id"))); carDAO.update(c); }
      else if("delete".equals(action)){ int id = Integer.parseInt(req.getParameter("id")); carDAO.delete(id); }
      else { resp.sendError(400); return; }
      resp.sendRedirect(req.getContextPath()+"/admin/cars");
    } catch(Exception e){ throw new ServletException(e); }
  }
  private Car build(HttpServletRequest req){
    Car c = new Car(); c.setMake(req.getParameter("make")); c.setModel(req.getParameter("model"));
    c.setYear(Integer.parseInt(req.getParameter("year"))); c.setPrice(Double.parseDouble(req.getParameter("price")));
    c.setMileage(Integer.parseInt(req.getParameter("mileage"))); c.setFuelType(req.getParameter("fuelType"));
    c.setDescription(req.getParameter("description")); c.setImageUrl(req.getParameter("imageUrl")); return c;
  }
}