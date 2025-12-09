package com.drivedeal.dao;
import java.sql.*; import jakarta.servlet.ServletContext;
public class DBUtil {
  private static String url,user,pass;
  public static void init(ServletContext ctx){ url=ctx.getInitParameter("JDBC_URL"); user=ctx.getInitParameter("JDBC_USER"); pass=ctx.getInitParameter("JDBC_PASS"); }
  public static Connection getConnection() throws SQLException { return DriverManager.getConnection(url,user,pass); }
}