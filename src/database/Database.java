package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.CashRegisterType;

public class Database {

    final String URL = "jdbc:derby:Database;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";

    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;

    public Database() {
        //Megpróbáljuk életre kelteni
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection sikeres");
        } catch (SQLException ex) {
            System.out.println("Connection error a létrehozásakor.");
            System.out.println("" + ex);
        }

        //Ha életre kelt, csinálunk egy megpakolható teherautót
        if (conn != null) {
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van van a createStatament létrehozásakor.");
                System.out.println("" + ex);
            }
        }

        //Megnézzük, hogy üres-e az adatbázis? Megnézzük, létezik-e az adott adattábla.
        try {
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a DatabaseMetaData (adatbázis leírása) létrehozásakor..");
            System.out.println("" + ex);
        }

        try {
            ResultSet rs = dbmd.getTables(null, "APP", "CASHREGISTERTYPES", null);
            if (!rs.next()) {
                createStatement.execute("CREATE TABLE cashregistertypes(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),licenseNumber varchar(4), typeName varchar(100))");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az adattáblák létrehozásakor.");
            System.out.println("" + ex);
        }
    }
    

    public ArrayList<CashRegisterType> getAllCashRegiseterType() {
        String sql = "SELECT * FROM cashregistertypes";
        ArrayList<CashRegisterType> cashRegisterTypes = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            cashRegisterTypes = new ArrayList<>();

            while (rs.next()) {
                CashRegisterType actualCashRegisterType = new CashRegisterType(rs.getInt("id"), rs.getString("licenseNumber"), rs.getString("typeName"));
                cashRegisterTypes.add(actualCashRegisterType);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a userek kiolvasásakor");
            System.out.println("" + ex);
        }
        return cashRegisterTypes;
    }

    public void addCashRegisterType(CashRegisterType cashRegisterType) {
        try {
            String sql = "INSERT INTO cashregistertypes (licensenumber, typename) VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cashRegisterType.getLicenseNumber());
            preparedStatement.setString(2, cashRegisterType.getTypeName());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a CashRegisterType hozzáadásakor");
            System.out.println("" + ex);
        }
    }

    public void updateCashRegisterType(CashRegisterType cashRegisterType) {
        try {
            String sql = "UPDATE cashregistertypes SET liceseNumber = ?, typeName = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cashRegisterType.getLicenseNumber());
            preparedStatement.setString(2, cashRegisterType.getTypeName());
            preparedStatement.setInt(4, Integer.parseInt(cashRegisterType.getId()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a CashRegisterType módosításakor");
            System.out.println("" + ex);
        }
    }
/*
    public void removeContact(CashRegisterType person) {
        try {
            String sql = "delete from contacts where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(person.getId()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a contact törlésekor");
            System.out.println("" + ex);
        }
    }
*/
}
