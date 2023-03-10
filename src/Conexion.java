import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static final String url = "jdbc:mysql://localhost:3306/";
    public static final String bd = "agenda";
    public static final String user = "root";
    public static final String pass = "kiosko";
    public static Connection conex;

    public Conexion() {
    }

    public static Connection conectar() {
        conex = null;

        try {
            conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "kiosko");
            System.out.println("Conexion Establecida");
        } catch (SQLException var1) {
            System.out.println("Error al conectar a la base de datos agenda");
        }

        return conex;
    }

    public static void desconectar() {
        try {
            conex.close();
            System.out.println("Se ha cerrado correctamente la base de datos agenda");
        } catch (SQLException var1) {
            System.out.println("hubo un error en la desconexi�n a la Base de datos agenda");
            var1.printStackTrace();
        }

    }
}
