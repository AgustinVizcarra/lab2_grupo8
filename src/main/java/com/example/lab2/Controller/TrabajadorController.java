package com.example.lab2.Controller;

import com.example.lab2.Entity.Trabajador;
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
@RequestMapping(value = "trabajador")
public class TrabajadorController {
    @Autowired
    TrabajadorRepository trabajadorRepository;

    @GetMapping(value = "trabajador/lista")
    public String listar(Model model){
        List<Trabajador> trabajador = trabajadorRepository.findAll();
        model.addAttribute("lista",trabajador);
        return "trabajador/lista";

    }

    @GetMapping({"/trabajador/nuevo"})
    public String nuevoTrabajador() {
        return "trabajador/nuevo";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") String id) {


        return "trabajador/editar";
    }
    @PostMapping(value = "/trabajador/guardar")
    public String guardar(Trabajador trabajador, RedirectAttributes redirectAttributes) {
        this.trabajadorRepository.save(trabajador);
        redirectAttributes.addFlashAttribute("msg", "Se ha creado un nuevo Trabajador");
        return "redirect:/trabajador/lista";
    }

    @GetMapping(value = "trabajador/borrar")
    public String borrar(@RequestParam("id") String id) {
        try{
            trabajadorRepository.deleteById(id);
            System.out.println("Se elimino el Trabajador de manera exitosa!");
        }catch (Exception e) {
            System.out.println("Se ingreso un id invalido");
        }
        return "redirect:/trabajador/lista";
    }
}
