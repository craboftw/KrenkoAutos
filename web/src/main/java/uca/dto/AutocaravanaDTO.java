package uca.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AutocaravanaDTO {

    private String matricula;
    private int plazas;
    private String modelo;
    private BigDecimal precioPorDia;
    private int kilometraje;
}
