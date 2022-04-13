package com.example.lab2.Controller;

import com.example.lab2.Entity.Sede;
import com.example.lab2.Entity.Trabajador;
import com.example.lab2.Repository.SedeRepository;
import com.example.lab2.Repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "sede")
public class SedeController {

    @Autowired
    SedeRepository sedeRepository;
    @Autowired
    TrabajadorRepository trabajadorRepository;

    @GetMapping(value = "lista")
    public String listar(Model model) {
        List<Sede> sedeLista = sedeRepository.findAll();
        model.addAttribute("sedeLista", sedeLista);
        return "sede/lista";
    }

    @GetMapping(value = "nuevo")
    public String crear() {
        return "sede/nuevo";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") Integer id, Sede idsede) {
        List<Trabajador> trabajadorLista = trabajadorRepository.findTrabajadorByIdsede(idsede);
        model.addAttribute("trabajadorLista", trabajadorLista);

        Optional<Sede> optionalSede = sedeRepository.findById(id);
        if (optionalSede.isPresent()) {
            Sede sede = optionalSede.get();
            model.addAttribute("sede", sede);
            return "sede/editar";
        } else {
            return "redirect:/sede/lista";
        }
    }

    @PostMapping(value = "guardar")
    public String guardar(Sede sede, RedirectAttributes attributes) {
        sedeRepository.save(sede);
        attributes.addFlashAttribute("msgSave", "Sede creada exitosamente");
        return "redirect:/sede/lista";
    }

    @PostMapping("/actualizar")
    public String actualizar(Sede sedeForm, RedirectAttributes attr) {
        Optional<Sede> optSede = sedeRepository.findById(sedeForm.getId());
        if (optSede.isPresent()) {
            Sede sedeFromDb = optSede.get();
            sedeFromDb.setNombreSede(sedeFromDb.getNombreSede());
            sedeFromDb.setDireccion(sedeFromDb.getDireccion());
            sedeRepository.save(sedeFromDb);
            attr.addFlashAttribute("msgEdit", "Sede editada exitosamente");
        }
        return "redirect:/sede/lista";
    }

    @GetMapping(value = "borrar")
    public String borrar(@RequestParam("id") Integer id, RedirectAttributes attr) {
        Optional<Sede> optionalSede = sedeRepository.findById(id);

        if (optionalSede.isPresent()) {
            sedeRepository.deleteById(id);
            attr.addFlashAttribute("msgDelete", "Sede borrada exitosamente");
        }
        return "redirect:/sede/lista";
    }
}