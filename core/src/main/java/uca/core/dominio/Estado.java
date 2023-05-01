package uca.core.dominio;

import lombok.Data;

@Data
public class Estado {
    String tipo;
    String valor;

    public Estado(String tip, String val)
    {
        tipo = tip;
        valor = val;
    }

    public Estado()
    {
        tipo = "";
        valor = "";
    }
}
