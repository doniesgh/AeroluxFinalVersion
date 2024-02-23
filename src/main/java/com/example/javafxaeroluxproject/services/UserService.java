package com.example.javafxaeroluxproject.services;

import com.example.javafxaeroluxproject.models.User;
import com.example.javafxaeroluxproject.utils.MyDatabase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUser<User>{
  private Connection connection ;

  public UserService(){
      connection = MyDatabase.getInstance().getConnection();
  }


    @Override
    public void add(User user) throws SQLException {
      String req = "insert into user (first_name , last_name , email , password ,role ) " +
              "values( '"+user.getFirst_name()+"','"+user.getLast_name()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getRole()+"')";

          Statement st = connection.createStatement();
          st.executeUpdate(req);

    }

    @Override
    public void update(User user) throws  SQLException{
      String req = "update user set first_name = ? ,  last_name = ?  , email = ? , password = ?, role = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1 , user.getFirst_name());
        ps.setString(2 , user.getLast_name());
        ps.setString(3 , user.getEmail());
        ps.setString(4 , user.getPassword());
        ps.setString(5 , user.getRole());
        ps.setInt(6 , user.getId());
        ps.executeUpdate();





    }
@Override
    public void updateUserPassword(String emailUser, String newPassword) throws SQLException {
        String req = "UPDATE user SET password = ? WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, newPassword);
            ps.setString(2, emailUser);
            ps.executeUpdate();
        }
    }


    @Override
    public void delete(int id) throws  SQLException{
      String req ="delete from user where id = ?";
      PreparedStatement ps = connection.prepareStatement(req);
      ps.setInt(1,id);
      ps.executeUpdate();

    }

    @Override
    public List<User> retrieve() throws  SQLException{
      List < User> users = new ArrayList<>();
        String req ="select * from user";
        Statement st = connection.createStatement();
      ResultSet result = st.executeQuery(req);
      while (result.next()){
          User user = new User();
          user.setId(result.getInt("id"));
          user.setFirst_name(result.getString("first_name"));

          user.setLast_name(result.getString("last_name"));
          user.setEmail(result.getString("email"));
          user.setPassword(result.getString("password"));
          user.setRole(result.getString("role"));

          users.add(user);

      }
      return users ;
    }


    /*public User getUserById(int id) throws  SQLException{
        String req ="select * from user where id = ?";
        Statement st = connection.createStatement();


        ResultSet result = st.executeQuery(req);
        User user = new User();
        user.setFirst_name(result.getString("first_name"));

        user.setLast_name(result.getString("last_name"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
        user.setRole(result.getString("role"));
        return user;


    }*/
    public User getUserById(int id) throws SQLException {
        String req = "select * from user where id = ?";

        try (PreparedStatement st = connection.prepareStatement(req)) {
            // Paramètre pour l'ID
            st.setInt(1, id);

            try (ResultSet result = st.executeQuery()) {
                // S'assurer qu'il y a un enregistrement avant de parcourir les résultats
                if (result.next()) {
                    User user = new User();
                    user.setId(result.getInt("id"));
                    user.setFirst_name(result.getString("first_name"));
                    user.setLast_name(result.getString("last_name"));
                    user.setEmail(result.getString("email"));
                    user.setPassword(result.getString("password"));
                    user.setRole(result.getString("role"));
                    return user;
                } else {
                    // Aucun utilisateur trouvé avec cet ID
                    System.out.println( id );
                    System.out.println("Id introuvable");

                    return null;
                }
            }
        }
    }

}
