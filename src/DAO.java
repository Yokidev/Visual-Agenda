import java.awt.Component;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DAO {
    public DAO() {
    }

    public static void NuevoCto(Connection conex) throws SQLException {
        String var10000 = Cita.getCita();
        String sql = "CALL Nuevo_CTO('" + var10000 + "','" + String.valueOf(Cita.getFecha()) + "','" + String.valueOf(Cita.getHora()) + "','" + Cita.getObservacion() + Cita.getId() + ")";
        CallableStatement callstmt = null;

        try {
            callstmt = conex.prepareCall(sql);
            callstmt.execute(sql);
            JOptionPane.showMessageDialog((Component)null, "Se ha registrado el nuevo contacto", "Información", 1);
        } catch (SQLException var7) {
            JOptionPane.showMessageDialog((Component)null, "Error: fracasó la orden", "Información de Error", 0);
            var7.printStackTrace();
        } finally {
            callstmt.close();
        }

    }

    public static void ModifiCto(Connection conex) throws SQLException {
        String var10000 = Cita.getCita();
        String sql = "CALL MODIFI_CTO('" + var10000 + "','" + String.valueOf(Cita.getFecha()) + "','" + String.valueOf(Cita.getHora()) + "','" + Cita.getObservacion() + Cita.getId() + ")";
        CallableStatement callstmt = null;

        try {
            callstmt = conex.prepareCall(sql);
            callstmt.execute(sql);
            JOptionPane.showMessageDialog((Component)null, "El contacto ha sido actualizado", "Información", 1);
        } catch (SQLException var7) {
            System.out.println("Error: No se pudo modificar el dato ");
            JOptionPane.showMessageDialog((Component)null, "Error: fracas� la orden", "Información de Error", 0);
            var7.printStackTrace();
        } finally {
            callstmt.close();
        }

    }

    public static void BorrarCto(Connection conex) throws SQLException {
        String sql = "CALL BORRA_CTO('" + Cita.getCita() + "');";
        System.out.println("BorrarCto -> Instruccion SQL: " + sql);
        CallableStatement callstmt = null;

        try {
            callstmt = conex.prepareCall(sql);
            callstmt.execute(sql);
            JOptionPane.showMessageDialog((Component)null, "Se ha registrado el nuevo contacto", "Información", 1);
        } catch (SQLException var7) {
            System.out.println("Error: No se pudo borrar la cita ");
            JOptionPane.showMessageDialog((Component)null, "Error: fracas� la orden", "Información de Error", 0);
            var7.printStackTrace();
        } finally {
            callstmt.close();
        }

    }

    public static int BuscarCto(int op, Connection conex, String nom, String loc, String cp, int ref) throws SQLException {
        String sql = null;
        int nr = 0;
        switch (op) {
            case 0:
                sql = "CALL BUSCAR_CTOxID(" + ref + ");";
                break;
            case 1:
                sql = "CALL BUSCAR_CTO('" + nom + "');";
                break;
            case 2:
                sql = "CALL BUSCAR_CTOxLOCALIDAD('" + loc + "');";
                break;
            case 3:
                sql = "CALL BUSCAR_CTOxCP('" + cp + "');";
        }

        CallableStatement callstmt = null;

        try {
            callstmt = conex.prepareCall(sql);

            for(ResultSet rs = callstmt.executeQuery(sql); rs.next(); ++nr) {
                Cita.setId(rs.getInt("id"));
                Cita.setCita(rs.getString("cita"));
                Cita.setFecha(Date.valueOf(rs.getString("fecha")));
                Cita.setHora(Time.valueOf(rs.getString("hora")));
                Cita.setObservacion(rs.getString("Observacion"));
                Cita.VerContacto();
            }

            if (nr == 0) {
                JOptionPane.showMessageDialog((Component)null, "No se encontraron contactos para ese filtro", "Informando sobre....", 1);
            } else {
                JOptionPane.showMessageDialog((Component)null, "Fueron encontrados " + nr + " datos", "exito en la operación", 1);
            }
        } catch (SQLException var13) {
            JOptionPane.showMessageDialog((Component)null, "Error: fracaso la orden", "Información de Error", 0);
            var13.printStackTrace();
        } finally {
            callstmt.close();
        }

        return nr;
    }

    public static void RellenarTabla(int op, Connection conex, String nom, String loc, String cp, DefaultTableModel mod) throws SQLException {
        String sql = null;
        switch (op) {
            case 0:
                sql = "CALL BUSCAR_CTO('%');";
                break;
            case 1:
                if (nom == null) {
                    nom = "%";
                }

                sql = "CALL BUSCAR_CTO('" + nom + "');";
                break;
            case 2:
                sql = "CALL BUSCAR_CTOxLOCALIDAD('" + loc + "');";
                break;
            case 3:
                sql = "CALL BUSCAR_CTOxCP('" + cp + "');";
        }

        CallableStatement callstmt = null;
        int nr = 0;

        try {
            callstmt = conex.prepareCall(sql);
            ResultSet rs = callstmt.executeQuery(sql);
            mod.setNumRows(0);

            while(rs.next()) {
                Object[] fila = new Object[7];

                for(int i = 0; i < 7; ++i) {
                    fila[i] = rs.getObject(i + 1);
                }

                mod.addRow(fila);
                ++nr;
            }

            if (op != 0) {
                if (mod.getColumnCount() == 0) {
                    JOptionPane.showMessageDialog((Component)null, "No se encontraron contactos para ese filtro", "Informando sobre....", 1);
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Fueron encontrados " + nr + " datos", "Exito en la operacion", 1);
                }
            }
        } catch (SQLException var15) {
            JOptionPane.showMessageDialog((Component)null, "No se pudo buscar resultados", "Mensaje de Error", 0);
            var15.printStackTrace();
        } finally {
            callstmt.close();
        }

    }
}
