package de.swm;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class SQLHandler {
    private String url;
    private String user;
    private String password;
    public static SQLHandler instance;

    public static SQLHandler getInstance(String url, String user, String password) {
        if (instance == null) {
            instance = new SQLHandler(url, user, password);
        }
        return instance;
    }

    SQLHandler(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection openConnection() throws SQLException {
        Connection con = null;
        String jdbcConnectionString = "jdbc:mariadb://" + url + "/time";
        con = (Connection) DriverManager.getConnection(jdbcConnectionString,
                user,
                password);
        return con;
    }

    public void sqlPushTime(TimeModel timeModel) {
        Connection con = null;
        Time timeBeg = java.sql.Time.valueOf(timeModel.getTimeStart());
        Time timeEnd = java.sql.Time.valueOf(timeModel.getTimeEnd());
        int userID = timeModel.getUserId();

        double diffMinutes = timeModel.getTimeWorkedMinutes();
        try {
            con = openConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO time (time_start, time_end, time_total, UserID) VALUES (?, ?, ?, ?);");
            stmt.setTime(1, timeBeg);
            stmt.setTime(2, timeEnd);
            stmt.setDouble(3, diffMinutes);
            stmt.setInt(4,userID );
            stmt.execute();
        } catch (SQLException SqlEx) {
            System.out.println("SQL Error" + SqlEx.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqlEx) {
                    System.out.println("couldnt close connection " + sqlEx.getMessage());
                }
            }
        }

    }

    public ArrayList<TimeModel> sqlGetTime(int userID) {
        Connection con = null;
        ArrayList<TimeModel> timeTables = new ArrayList<>();
        try {
            con = openConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM time WHERE UserID = ?");
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                timeTables.add(new TimeModel(LocalTime.parse(rs.getTime(1).toString()),LocalTime.parse(rs.getTime(2).toString()),rs.getInt(4)));
            }
        } catch (SQLException | ParseException SqlEx) {
            System.out.println("SQL Error" + SqlEx.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqlEx) {
                    System.out.println("couldnt close connection " + sqlEx.getMessage());
                }
            }
        }
return timeTables;
    }
}
