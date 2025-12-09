package com.drivedeal.dao;
import com.drivedeal.model.User; import java.sql.*; import org.mindrot.jbcrypt.BCrypt;
public class UserDAO {
  public User findByEmail(String email) throws SQLException {
    try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT id,name,email,password_hash,role FROM users WHERE email=?")){
      ps.setString(1,email);
      try (ResultSet rs = ps.executeQuery()){
        if(rs.next()){
          User u = new User(); u.setId(rs.getInt(1)); u.setName(rs.getString(2)); u.setEmail(rs.getString(3)); u.setPasswordHash(rs.getString(4)); u.setRole(rs.getString(5)); return u;
        }
      }
    } return null;
  }
  public boolean create(String name, String email, String rawPassword, String role) throws SQLException {
    String hash = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO users(name,email,password_hash,role) VALUES(?,?,?,?)")){
      ps.setString(1,name); ps.setString(2,email); ps.setString(3,hash); ps.setString(4,role); return ps.executeUpdate()==1;
    }
  }
  public boolean checkPassword(User u, String raw){ return BCrypt.checkpw(raw, u.getPasswordHash()); }
}