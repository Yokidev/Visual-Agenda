import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Cita {
    private static final DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter fechaInglesa = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static int id;
    private static LocalDate fecha;
    private static LocalTime hora;
    private static String cita;
    private static String observacion;

    public Cita() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Cita.id = id;
    }

    public static LocalDate getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        Cita.fecha = fecha.toLocalDate();
    }

    public static LocalTime getHora() {
        return hora;
    }

    public static void setHora(Time hora) {
        Cita.hora = hora.toLocalTime();
    }

    public static String getCita() {
        return cita;
    }

    public static void setCita(String cita) {
        Cita.cita = cita;
    }

    public static String getObservacion() {
        return observacion;
    }

    public static void setObservacion(String observacion) {
        Cita.observacion = observacion;
    }

    public static String Datos() {
        int var10000 = getId();
        return "Contacto:\r\n ID: " + var10000 + "\r\n Cita: " + getCita() + "\r\n Hora: " + String.valueOf(getHora()) + "\r\n Fecha: " + String.valueOf(getFecha()) + "\r\n Observacion: " + getObservacion() + "\r\n....................................\r\n";
    }

    public static void VerContacto() {
        System.out.println(Datos());
    }
}
