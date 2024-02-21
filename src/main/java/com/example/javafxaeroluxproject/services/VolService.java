package com.example.javafxaeroluxproject.services;

import com.example.javafxaeroluxproject.models.Reservation;
import com.example.javafxaeroluxproject.models.Vol;
import com.example.javafxaeroluxproject.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolService implements IService<Vol> {
    private Connection connection;
    public VolService(){
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Vol vol) throws SQLException {
        String req = "insert into vol ( NumVol,LieuArrivee ,piloteId , LieuDepart ,PlaceDispo  ,Description ,DateArrive,DateDepart) " +
                "values( '"+vol.getNumVol()+"','"+vol.getLieuArrivee()+"','"+vol.getPiloteId()+"','"+vol.getLieuDepart()+"','"+vol.getPlaceDispo()+"','"+vol.getDescription()+"','"+vol.getDateArrive()+"','"+vol.getDateDepart()+"')";

        Statement st = connection.createStatement();
        st.executeUpdate(req);

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
    public List<Vol> recuperer() throws  SQLException{
        List <Vol> vols = new ArrayList<>();
        String req ="select * from vol";
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery(req);
        while (result.next()){
            Vol vol = new Vol();
            vol.setId(result.getInt("id"));
            vol .setDescription(result.getString("description"));
            vol.setLieuDepart(result.getString("LieuDepart"));
            vol.setLieuArrivee(result.getString("lieuArrivee"));
            vol.setPlaceDispo(Integer.valueOf(result.getString("placeDispo")));
            vol.setDateDepart(result.getDate("dateDepart"));
            vol.setPiloteId(Integer.parseInt(result.getString("piloteId")));
            vols.add(vol);
        }
        return vols ;
    }
}