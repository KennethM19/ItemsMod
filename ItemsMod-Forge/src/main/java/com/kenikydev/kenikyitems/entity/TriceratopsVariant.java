package com.kenikydev.kenikyitems.entity;

import java.util.Arrays;
import java.util.Comparator;

//define las diferentes variantes (apariencias) que puede tener tu entidad Triceratops
public enum TriceratopsVariant {
    GREY(0), //Variante gris id = 0
    GREEN(1); //Variante verde id = 1

    //Array ordenado de todas las variantes por su ID
    private static final TriceratopsVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(TriceratopsVariant::getId)).toArray(TriceratopsVariant[]::new);

    //Almacena el ID numérico de cada variante
    private final int id;

    //Asigna el ID a cada variante cuando se crea
    TriceratopsVariant(int id) {
        this.id = id;
    }

    //Getter simple para obtener el ID de la variante
    public int getId() {
        return id;
    }

    //Obtiene una variante por su ID numérico
    public static TriceratopsVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
