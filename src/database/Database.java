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
import model.Component;
import model.Partner;

public class Database {

    private final String URL = "jdbc:derby:RaktarDB;create=true";
    //   final String URL = "jdbc:sqlite:database/raktar.db";

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
            ResultSet reCashRegisterType = dbmd.getTables(null, "APP", "CASHREGISTERTYPES", null);
            ResultSet rsComponent = dbmd.getTables(null, "APP", "COMPONENTS", null);
            ResultSet rsPartner = dbmd.getTables(null, "APP", "PARTNERS", null);

            if (!reCashRegisterType.next()) {
                createStatement.execute("CREATE TABLE cashregistertypes("
                        + "id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "licensenumber varchar(4), "
                        + "typename varchar(100)"
                        + ")");
                System.out.println("cashregistertypes adatbázis létrehozva");
            } else {
                System.out.println("cashregistertypes adatbázis létezik");
            }

            if (!rsComponent.next()) {
                createStatement.execute("CREATE TABLE components ("
                        + "id INT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "itemnumber varchar(50), "
                        + "barcode varchar(50), "
                        + "componentname varchar(50), "
                        + "quantity varchar(10), "
                        + "comment varchar(255)"
                        + ")");
                System.out.println("components adatbázis létrehozva");
            } else {
                System.out.println("components adatbázis létezik");
            }

            if (!rsPartner.next()) {
                createStatement.execute("CREATE TABLE partners("
                        + "id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "partnername varchar(100)"
                        + ")");
                System.out.println("partners adatbázis létrehozva");
            } else {
                System.out.println("partners adatbázis létezik");
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
                CashRegisterType actualCashRegisterType = new CashRegisterType(rs.getInt("id"), rs.getString("licensenumber"), rs.getString("typename"));
                cashRegisterTypes.add(actualCashRegisterType);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a getAllCashRegiseterType kiolvasásakor");
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
            System.out.println(cashRegisterType.getLicenseNumber() + " " + cashRegisterType.getTypeName());
        } catch (SQLException ex) {
            System.out.println("Valami baj van a CashRegisterType hozzáadásakor");
            System.out.println("" + ex);
        }
    }

    public void updateCashRegisterType(CashRegisterType cashRegisterType) {
        try {
            String sql = "UPDATE cashregistertypes SET licensenumber = ?, typename = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, cashRegisterType.getLicenseNumber());
            preparedStatement.setString(2, cashRegisterType.getTypeName());
            preparedStatement.setInt(3, Integer.parseInt(cashRegisterType.getId()));

            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a CashRegisterType módosításakor");
            System.out.println("" + ex);
        }
    }

    /**
     *
     * @return components
     */
    public ArrayList<Component> getAllComponent() {
        String sql = "SELECT * FROM components";
        ArrayList<Component> components = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            components = new ArrayList<>();

            while (rs.next()) {
                Component actualComponents
                        = new Component(rs.getInt("id"), rs.getString("itemnumber"), rs.getString("barcode"), rs.getString("componentname"), rs.getString("comment"));
                components.add(actualComponents);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a component kiolvasásakor");
            System.out.println("" + ex);
        }
        return components;
    }

    public void addComponent(Component component) {
        try {
            String sql = "INSERT INTO components (itemnumber, barcode, componentname, comment) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, component.getItemNumber());
            preparedStatement.setString(2, component.getBarCode());
            preparedStatement.setString(3, component.getComponentName());
            preparedStatement.setString(4, component.getComment());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a Component hozzáadásakor");
            System.out.println("" + ex);
        }
    }

    public void updateComponent(Component component) {
        try {
            String sql = "UPDATE components SET itemnumber = ?, barcode = ?, componentname = ?, comment = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, component.getItemNumber());
            preparedStatement.setString(2, component.getBarCode());
            preparedStatement.setString(3, component.getComponentName());
            preparedStatement.setString(4, component.getComment());
            preparedStatement.setInt(5, Integer.parseInt(component.getId()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a updateComponent módosításakor");
            System.out.println("" + ex);
        }
    }

        /**
     *
     * @return 
     */
    public ArrayList<Partner> getAllPartner() {
        String sql = "SELECT * FROM partners";
        ArrayList<Partner> partners = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            partners = new ArrayList<>();

            while (rs.next()) {
                Partner actualPartner = new Partner(rs.getInt("id"), rs.getString("partnername"));
                partners.add(actualPartner);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a getAllPartner kiolvasásakor");
            System.out.println("" + ex);
        }
        return partners;
    }

    public void addPartner(Partner partner) {
        try {
            String sql = "INSERT INTO partners (partnername) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, partner.getPartnerName());

            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a addPartner hozzáadásakor");
            System.out.println("" + ex);
        }
    }

    public void updatePartner(Partner partner) {
        try {
            String sql = "UPDATE partners SET partnername = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, partner.getPartnerName());
            preparedStatement.setInt(2, Integer.parseInt(partner.getid()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a updatePartner módosításakor");
            System.out.println("" + ex);
        }
    }
}
