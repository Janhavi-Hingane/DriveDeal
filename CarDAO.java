package com.drivedeal.dao;
import com.drivedeal.model.Car; import java.sql.*; import java.util.*;
public class CarDAO {
  public List<Car> search(String make, String fuel, Double minPrice, Double maxPrice) throws SQLException {
    StringBuilder sql = new StringBuilder("SELECT id, make, model, year, price, mileage, fuel_type, description, image_url FROM cars WHERE 1=1");
    List<Object> params = new ArrayList<>();
    if(make != null && !make.isBlank()){ sql.append(" AND make LIKE ?"); params.add("%"+make+"%"); }
    if(fuel != null && !fuel.isBlank()){ sql.append(" AND fuel_type = ?"); params.add(fuel); }
    if(minPrice != null){ sql.append(" AND price >= ?"); params.add(minPrice); }
    if(maxPrice != null){ sql.append(" AND price <= ?"); params.add(maxPrice); }
    sql.append(" ORDER BY created_at DESC");
    try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString())){
      for(int i=0;i<params.size();i++) ps.setObject(i+1, params.get(i));
      try (ResultSet rs = ps.executeQuery()){
        List<Car> list = new ArrayList<>();
        while(rs.next()){
          Car c = new Car();
          c.setId(rs.getInt(1)); c.setMake(rs.getString(2)); c.setModel(rs.getString(3));
          c.setYear(rs.getInt(4)); c.setPrice(rs.getDouble(5)); c.setMileage(rs.getInt(6));
          c.setFuelType(rs.getString(7)); c.setDescription(rs.getString(8)); c.setImageUrl(rs.getString(9));
          list.add(c);
        }
        return list;
      }
    }
  }
  public int create(Car c) throws SQLException {
    String sql = "INSERT INTO cars(make, model, year, price, mileage, fuel_type, description, image_url, created_at) VALUES(?,?,?,?,?,?,?,?,NOW())";
    try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
      ps.setString(1,c.getMake()); ps.setString(2,c.getModel()); ps.setInt(3,c.getYear()); ps.setDouble(4,c.getPrice());
      ps.setInt(5,c.getMileage()); ps.setString(6,c.getFuelType()); ps.setString(7,c.getDescription()); ps.setString(8,c.getImageUrl());
      ps.executeUpdate(); try (ResultSet rs = ps.getGeneratedKeys()){ if(rs.next()) return rs.getInt(1); }
    } return -1;
  }
  public void update(Car c) throws SQLException {
    String sql = "UPDATE cars SET make=?, model=?, year=?, price=?, mileage=?, fuel_type=?, description=?, image_url=? WHERE id=?";
    try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
      ps.setString(1,c.getMake()); ps.setString(2,c.getModel()); ps.setInt(3,c.getYear()); ps.setDouble(4,c.getPrice());
      ps.setInt(5,c.getMileage()); ps.setString(6,c.getFuelType()); ps.setString(7,c.getDescription()); ps.setString(8,c.getImageUrl());
      ps.setInt(9,c.getId()); ps.executeUpdate();
    }
  }
  public void delete(int id) throws SQLException {
    try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement("DELETE FROM cars WHERE id=?")){
      ps.setInt(1, id); ps.executeUpdate();
    }
  }
}