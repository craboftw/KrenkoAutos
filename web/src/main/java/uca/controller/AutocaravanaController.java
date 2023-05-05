package uca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uca.core.dominio.Autocaravana;
import uca.core.servicio.interfaces.iAutocaravanaServicio;
import uca.dto.AutocaravanaDTO;

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
        if (autocaravana == autocaravana.AutocaravanaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(autocaravana);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Autocaravana> altaAutocaravana(@RequestParam AutocaravanaDTO intento) {
        autocaravanaServicio.altaAutocaravana(intento.getModelo(), intento.getPrecioPorDia(), intento.getPlazas(), intento.getMatricula(), intento.getKilometraje());
        Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana("matricula",intento.getMatricula()).stream().findFirst().get();
        return ResponseEntity.created(URI.create("/autocaravanas/" + autocaravana.getIdA())).body(autocaravana);
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

   @PostMapping

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

    @PutMapping("/modelo{id}")
    public ResponseEntity<Autocaravana> modificarModelo(@PathVariable("id") Long id, @RequestParam String modelo) {
        Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(id);
        if (autocaravana == Autocaravana.AutocaravanaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            autocaravanaServicio.setModelo(id, modelo);
            return ResponseEntity.ok(autocaravana);
        }
    }

    @PutMapping("/precioPorDia{id}")
    public ResponseEntity<Autocaravana> modificarPrecioPorDia(@PathVariable("id") Long id, @RequestParam BigDecimal precioPorDia) {
        Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(id);
        if (autocaravana == Autocaravana.AutocaravanaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            autocaravanaServicio.setPrecio(id, precioPorDia);
            return ResponseEntity.ok(autocaravana);
        }
    }

    @PutMapping("/plazas{id}")
    public ResponseEntity<Autocaravana> modificarPlazas(@PathVariable("id") Long id, @RequestParam int plazas) {
        Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(id);
        if (autocaravana == Autocaravana.AutocaravanaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            autocaravanaServicio.setPlazas(id, plazas);
            return ResponseEntity.ok(autocaravana);
        }
    }









}
