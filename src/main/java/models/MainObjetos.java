package models;

import models.filters.FxDeporte;

public class MainObjetos{

public static void main(String[] args) {

    Area basquet = new Area(1, 300, "Basquet",4,1);
    Area futbol = new Area(2, 400, "Futbol",5,2);



    Cancha c = new Cancha(
            3,
            15,
            "Basquet",
            10,
            false,
            1000,
            false,
            500
    );

    Cancha c2 = new Cancha(
            4,
            20,
            "Futbol",
            15,
            false,
            1000,
            false,
            600
    );

    basquet.setElementos(c);
    futbol.setElementos(c2);

    System.out.println("Capacidad area basquet "+basquet.getCapacidad());
    System.out.println("Capacidad area futbol "+futbol.getCapacidad());


    FxDeporte f1 = new FxDeporte("Basquet");

    System.out.println("En el area "+basquet.toString()+" Hay "+basquet.getCanchasXFiltro(f1).size()+" de basquet");


}}