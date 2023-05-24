package uca.core.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estados")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Transient
    public static Estado EstadoNulo = new Estado();
    String tipo;
    String valor;
    public Estado(String tip, String val)
    {
        tipo = tip;
        valor = val;
    }

    public Estado()
    {
        id = -1L;
        tipo = "";
        valor = "";
    }
}
