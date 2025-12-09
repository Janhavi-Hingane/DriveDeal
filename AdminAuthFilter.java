package com.drivedeal.filter;
import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException; import com.drivedeal.model.User;
public class AdminAuthFilter implements Filter {
  @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request; HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession s = req.getSession(false); User u = (s==null)?null:(User) s.getAttribute("user");
    if(u==null){ resp.sendRedirect(req.getContextPath()+"/auth/login"); return; } chain.doFilter(request, response);
  }
}