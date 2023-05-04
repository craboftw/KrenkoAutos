package uca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uca.core.dominio.Autocaravana;
import uca.core.servicio.interfaces.iAutocaravanaServicio;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/autocaravanas")
public class AutocaravanaController {

    @Autowired
    private iAutocaravanaServicio autocaravanaServicio;

    @GetMapping("/")
    public Collection<Autocaravana> listarAutocaravanas() {
        return autocaravanaServicio.getListaAutocaravanas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autocaravana> buscarAutocaravanaPorId(@PathVariable("id") Long id) {
        Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(id);
        if (autocaravana == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(autocaravana);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Autocaravana> altaAutocaravana(@RequestParam String mod, @RequestParam BigDecimal precio, @RequestParam int plaz, @RequestParam String matr, @RequestParam int kilometraj) {
        autocaravanaServicio.altaAutocaravana(mod, precio, plaz, matr, kilometraj);
        return ResponseEntity.created(URI.create("/autocaravanas/" + matr)).build();
    }

    @GetMapping("/numero-autocaravanas")
    public ResponseEntity<Integer> getNumeroAutocaravanas() {
        int numeroAutocaravanas = autocaravanaServicio.getListaAutocaravanas().size();
        return ResponseEntity.ok(numeroAutocaravanas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutocaravanaPorId(@PathVariable("id") Long id) {
        Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(id);
        if (autocaravana == null) {
            return ResponseEntity.notFound().build();
        } else {
            autocaravanaServicio.eliminarAutocaravana(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autocaravana> modificarAutocaravana(@PathVariable("id") Long id, @RequestBody Autocaravana autocaravana) {
        Autocaravana autocaravanaActual = autocaravanaServicio.buscarAutocaravana(id);
        if (autocaravanaActual == null) {
            return ResponseEntity.notFound().build();
        } else {
            autocaravana.setIdA(id);
            autocaravanaServicio.setKilometraje(id, autocaravana.getKilometraje());
            autocaravanaServicio.setEstado(id, autocaravana.getEstadoA());
            autocaravanaServicio.setModelo(id, autocaravana.getModelo());
            autocaravanaServicio.setPrecio(id, autocaravana.getPrecioPorDia());
            autocaravanaServicio.setPlazas(id, autocaravana.getPlazas());
            autocaravanaServicio.guardarAutocaravana(autocaravana);
            return ResponseEntity.ok(autocaravana);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Autocaravana>> buscarAutocaravanaPorTipoDato(@RequestParam(required = false) String tipo,
                                                                                  @RequestParam(required = true) String dato) {
        if (tipo == null) {
            tipo = "idA";
        }
        Collection<Autocaravana> autocaravanas = autocaravanaServicio.buscarAutocaravana(tipo, dato);
        if (autocaravanas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(autocaravanas);
        }
    }



}
