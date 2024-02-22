package com.example.javafxaeroluxproject.services;

import com.example.javafxaeroluxproject.models.Reservation;
import com.example.javafxaeroluxproject.models.Vol;
import com.example.javafxaeroluxproject.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;

public class VolService implements IService<Vol> {
    private Connection connection;
    public VolService(){
        connection = MyDatabase.getInstance().getConnection();
    }
   /* @Override
    public void ajouter(Vol vol) throws SQLException {
        String req = "INSERT INTO vol (NumVol, LieuArrivee, piloteId, LieuDepart, PlaceDispo, Description, DateArrive, DateDepart, ImagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement st = connection.prepareStatement(req);
        st.setString(1, vol.getNumVol());
        st.setString(2, vol.getLieuArrivee());
        st.setInt(3, vol.getPiloteId());
        st.setString(4, vol.getLieuDepart());
        st.setInt(5, vol.getPlaceDispo());
        st.setString(6, vol.getDescription());
        st.setDate(7, vol.getDateArrive());
        st.setDate(8, vol.getDateArrive());
        String base64Image = encodeImageToBase64(vol.getImagePath());
        vol.setImagePath(base64Image);

        st.executeUpdate();
    }*/
   @Override
   public void ajouter(Vol vol) throws SQLException {
       String req = "INSERT INTO vol (NumVol, LieuArrivee, piloteId, LieuDepart, PlaceDispo, Description, DateArrive, DateDepart, Price, ImageData) " +
               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

       try (PreparedStatement st = connection.prepareStatement(req)) {
           st.setString(1, vol.getNumVol());
           st.setString(2, vol.getLieuArrivee());
           st.setInt(3, vol.getPiloteId());
           st.setString(4, vol.getLieuDepart());
           st.setInt(5, vol.getPlaceDispo());
           st.setString(6, vol.getDescription());
           st.setTimestamp(7, new Timestamp(vol.getDateArrivee().getTime()));
           st.setTimestamp(8, new Timestamp(vol.getDateDepart().getTime()));
           st.setFloat(9, vol.getPrice());
           st.setBlob(10, new SerialBlob(vol.getImageData()));

           st.executeUpdate();
       } catch (SQLException e) {
           // Handle SQLException
           e.printStackTrace();
           throw e; // Re-throw the exception to propagate it up the call stack
       }
   }


    @Override
    public void modifier(Vol vol) throws  SQLException{
        /*String req = "update vol set NumVol = ? ,  LieuArrivee = ?  , LieuDepart = ? , piloteId = ? ,PlaceDispo = ?, Description = ?, DateArrive = ?, DateDepart = ?  where id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1 , vol.getNumVol());
        ps.setString(2 , vol.getLieuArrivee());
        ps.setString(3 , vol.getLieuDepart());
        ps.setInt(4,vol.getPiloteId());
        //ps.setString(5 , vol.getPlaceDispo());
        ps.setString(6 , vol.getDescription());
        //ps.setString(7 , vol.getDateArrive());
        //ps.setString(8,vol.getDateDepart());
        ps.setInt(9, vol.getId());
        ps.executeUpdate();*/

    }


    @Override
    public void supprimer(int id) throws  SQLException{
        String req ="delete from vol where id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();

    }

    @Override
    public List<Vol> recuperer() throws SQLException {
        List<Vol> vols = new ArrayList<>();
        String req = "SELECT * FROM vol";
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery(req);
        while (result.next()) {
            Vol vol = new Vol();
            vol.setId(result.getInt("id"));
            vol.setDescription(result.getString("description"));
            vol.setLieuDepart(result.getString("LieuDepart"));
            vol.setLieuArrivee(result.getString("lieuArrivee"));
            vol.setPlaceDispo(result.getInt("placeDispo"));
            vol.setDateDepart(result.getDate("dateDepart"));
            vol.setPrice(result.getFloat("price"));
            vol.setPiloteId(result.getInt("piloteId"));
            Blob imageBlob = result.getBlob("ImageData");
            if (imageBlob != null) {
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
                vol.setImageData(imageData);
            }
            vols.add(vol);
        }
        return vols;
    }

}