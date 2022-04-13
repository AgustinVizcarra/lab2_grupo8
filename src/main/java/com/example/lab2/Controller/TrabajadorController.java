package com.example.lab2.Controller;

import com.example.lab2.Entity.Tipo;
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

    @GetMapping(value = "lista")
    public String listar(Model model){
        List<Trabajador> trabajadorLista = trabajadorRepository.findAll();
        model.addAttribute("lista",trabajadorLista);
        System.out.println(trabajadorLista);
        return "trabajador/lista";

    }

    @GetMapping({"crear"})
    public String crear() {
        return "trabajador/nuevo";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") String id) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(id);

        if (optionalTrabajador.isPresent()) {
            Trabajador trabajador= optionalTrabajador.get();
            model.addAttribute("trabajador", trabajador);
            return "trabajador/editar";
        } else {
            return "redirect:/trabajador/lista";
        }
    }
    @PostMapping(value = "guardar")
    public String guardar(Trabajador trabajador, RedirectAttributes redirectAttributes) {
        this.trabajadorRepository.save(trabajador);
        redirectAttributes.addFlashAttribute("msg", "Se ha creado un nuevo Trabajador");
        return "redirect:/trabajador/lista";
    }

    @GetMapping(value = "eliminar")
    public String borrar(@RequestParam("id") String id) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(id);

        if (optionalTrabajador.isPresent()) {
            trabajadorRepository.deleteById(id);
        }
        return "redirect:/trabajador/lista";
    }
}