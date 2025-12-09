package com.drivedeal.servlet;
import com.drivedeal.dao.*; import com.drivedeal.model.User;
import jakarta.servlet.*; import jakarta.servlet.http.*; import jakarta.servlet.annotation.*; import java.io.IOException;
@WebServlet(name="AuthServlet", urlPatterns={"/auth/*"})
public class AuthServlet extends HttpServlet {
  private UserDAO userDAO = new UserDAO();
  @Override public void init(){ DBUtil.init(getServletContext()); }
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String p = req.getPathInfo();
    if(p==null || "/login".equals(p)) req.getRequestDispatcher("/login.jsp").forward(req, resp);
    else if("/register".equals(p)) req.getRequestDispatcher("/register.jsp").forward(req, resp);
    else if("/logout".equals(p)){ HttpSession s=req.getSession(false); if(s!=null) s.invalidate(); resp.sendRedirect(req.getContextPath()+"/"); }
    else resp.sendError(404);
  }
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String p = req.getPathInfo();
    try {
      if("/login".equals(p)){
        String email = req.getParameter("email"); String pass = req.getParameter("password");
        User u = userDAO.findByEmail(email);
        if(u!=null && userDAO.checkPassword(u, pass)){ req.getSession().setAttribute("user", u); resp.sendRedirect(req.getContextPath()+"/admin/cars"); }
        else { req.setAttribute("error","Invalid credentials"); req.getRequestDispatcher("/login.jsp").forward(req, resp); }
      } else if("/register".equals(p)){
        boolean ok = userDAO.create(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"), "ADMIN");
        if(ok) resp.sendRedirect(req.getContextPath()+"/auth/login"); else { req.setAttribute("error","Registration failed"); req.getRequestDispatcher("/register.jsp").forward(req, resp); }
      } else resp.sendError(404);
    } catch(Exception e){ throw new ServletException(e); }
  }
}