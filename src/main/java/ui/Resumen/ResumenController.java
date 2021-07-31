package ui.Resumen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.*;
import models.filters.Cancha.FxMantenimiento;
import ui.Main;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResumenController implements Initializable {


    @FXML
    private Label disponibilidadAreasLabel;

    @FXML
    private Label mantenimientoAreasLabel;

    @FXML
    private Label disponibilidadCanchasLabel;

    @FXML
    private Label mantenimientoCanchasLabel;

    @FXML
    private Label dimesionLabel;

    @FXML
    private Label cantDeportesLabel;

    @FXML
    private Label cantCanchasLabel;

    @FXML
    private Label cantGenteComplejoLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label gastoMensualLabel;

    private List<Area> areas;

    @FXML
    public void backButtonClicked(ActionEvent event) {
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/main-menu.fxml", "Menú principal");
    }

    public void setAtributos() {
        System.out.println("Cantidad elementos" + this.areas.size());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Obtengo las areas del complejo
        this.areas = (List<Area>) Main.manager.createQuery("FROM Area where nombreArea='general'").getResultList();

        double contadorAreasDisponibles = 0;
        //Cuento cuantas areas estan disponibles
        for (Area area : this.areas) {
            if (area.getMantenimiento()==false) {
                contadorAreasDisponibles++;
            }
        }
        //Convierto a String tanto la cantidad de areas disponibles como las que estan en mantenimiento.
        String porcentajeAreasDisponibles = (Double.toString(((double)contadorAreasDisponibles / this.areas.size() * 100))) + "%";
        String PorcentajeAreasMantenimiento = (Double.toString( (100 - ((double)contadorAreasDisponibles / this.areas.size() * 100)))) + "%";
        //Seteo en los label los String ya convertidos, con el % al final.
        this.disponibilidadAreasLabel.setText(porcentajeAreasDisponibles);
        this.mantenimientoAreasLabel.setText(PorcentajeAreasMantenimiento);

        //Para todas las areas que tengo, me fijo cuantas canchas estan disponibles y cuantas no
        FxMantenimiento fxMantenimiento = new FxMantenimiento();
        int contadorCanchasTotales = 0;
        List<Cancha> canchasDisponibles = new ArrayList<>(); //Voy a guardar las canchas que no se encuentra en mantenimiento.
        for (Area area : this.areas) {
            canchasDisponibles.addAll(area.getCanchasXFiltro(fxMantenimiento));
            contadorCanchasTotales += area.getCanchasXFiltro(null).size();
        }
        if(contadorCanchasTotales!=0) {
            //Convierto a String tanto la cantidad de canchas disponibles como las que estan en mantenimiento.
            String porcentajeCanchasDisponibles = (Double.toString(((double) canchasDisponibles.size() / contadorCanchasTotales * 100))) + "%";
            String porcentajeCanchasMantenimiento = (Double.toString((100 - ((double) canchasDisponibles.size() / contadorCanchasTotales * 100)))) + "%";
            //Seteo en los label los String ya convertidos, con el % al final.
            this.disponibilidadCanchasLabel.setText(porcentajeCanchasDisponibles);
            this.mantenimientoCanchasLabel.setText(porcentajeCanchasMantenimiento);
        }
        else{
            this.disponibilidadCanchasLabel.setText("-");
            this.mantenimientoCanchasLabel.setText("-");
        }
        //Calculo la dimension del complejo como la suma de la dimension de las areas
        double dimensionComplejo = 0;
        for (Area area : this.areas) {
            dimensionComplejo += area.getDimension();
        }
        //Seteo en el label dimension, la dimension del complejo.
        String dimensionC = Double.toString(dimensionComplejo) + " m²";
        this.dimesionLabel.setText(dimensionC);

        //Seteo la cantidad de deportes que tiene mi complejo
        this.cantDeportesLabel.setText(Integer.toString(this.areas.size()));

        //Seteo la cantidad de canchas que tiene mi complejo, ya calculadas anteriormente
        this.cantCanchasLabel.setText(Integer.toString(contadorCanchasTotales));

        //Filtro los turnos por dia y hora, y luego obtengo cuanta gente hay en este momento en el complejo

        //Obtengo dia actual
        LocalDateTime dateHoy = LocalDateTime.now();
        int dia = dateHoy.getDayOfMonth();
        int mes = dateHoy.getMonthValue();
        int anio = dateHoy.getYear();
        LocalDate diaHoy = LocalDate.of(anio, mes, dia);

        //Obtengo hora actual
        int hora = dateHoy.getHour();
        int minutos = dateHoy.getMinute();
        int segundos = dateHoy.getSecond();
        LocalTime horaNow = LocalTime.of(hora, minutos, segundos);

        //Busco turnos por fecha, y que la hora del turno se encuentre en un rango horario(Que no haya pasado 1 hora todavia, o que )
        List<Turno> turnosFiltrados = new ArrayList<>();
        for (Cancha cancha : canchasDisponibles) {
            turnosFiltrados.addAll(cancha.getTurnosXFechaYHora(diaHoy, horaNow));
        }
        //Ya obtuve los turnos filtrados, ahora calculo cuanta gente hay en mi complejo actualmente.
        int cantGenteAct = 0;
        for (Turno turno : turnosFiltrados)
            cantGenteAct += turno.getJugadores().size();
        //Seteo el valor obtenido en el label
        this.cantGenteComplejoLabel.setText(Integer.toString(cantGenteAct));

        //Calculo gasto mensual del complejo, sumando los gastos mensuales de las areas
        double gastoMensualComplejo =0;
        for(Area area: this.areas)
            gastoMensualComplejo+=area.getGastoMensual();

        //Seteo el gasto mensual en el label
        this.gastoMensualLabel.setText(Double.toString(gastoMensualComplejo));

    }

}
