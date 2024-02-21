package com.example.javafxaeroluxproject.services;
import com.example.javafxaeroluxproject.models.Reservation;
import com.example.javafxaeroluxproject.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements IService<Reservation> {
    private Connection connection;
    public ReservationService() {
        connection = MyDatabase.getInstance().getConnection();
    }
    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (agency_name, trip_id, status, nb_seat, price, reservation_date) VALUES ('" +
                reservation.getAgency_name() + "', '" +
                reservation.getTrip_id() + "', '" +
                reservation.getStatus() + "', " +
                reservation.getNb_seat() + ", '" +
                reservation.getPrice() + "', '" +
                new java.sql.Timestamp(reservation.getReservation_date().getTime()) + "')";

        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Reservation reservation) throws  SQLException{
        String req = "UPDATE reservation Set nb_place = ? WHERE id = ? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,reservation.getNb_seat());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id)  throws  SQLException{
        String req = "DELETE FROM reservation WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();
    }
    @Override
    public List<Reservation> recuperer() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String req = "SELECT * FROM reservation";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setTrip_id(rs.getInt("trip_id"));
            reservation.setAgency_name(rs.getString("agency_name"));
            reservation.setStatus(rs.getString("status"));
            reservation.setNb_seat(rs.getInt("nb_seat"));
            reservation.setPrice(rs.getFloat("price"));
            reservation.setReservation_date(rs.getDate("reservation_date"));
            // Add the Reservation object to the list
            reservations.add(reservation);
        }
        // Close resources
        rs.close();
        st.close();
        // Return the list of reservations
        return reservations;
    }
    public void updateStatusToRejected(int reservationId) throws SQLException {
        String req = "UPDATE reservation SET status = 'REJECTED' WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, reservationId);
        ps.executeUpdate();
    }

    public void updateStatusToAccepted(int id) throws SQLException {
        String req = "UPDATE reservation SET status = 'ACCEPTED' WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }



}
