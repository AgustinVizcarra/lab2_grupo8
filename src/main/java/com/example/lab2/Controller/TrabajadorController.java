package com.example.lab2.Controller;

import com.example.lab2.Entity.Sede;
import com.example.lab2.Entity.Tipo;
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
@RequestMapping(value = "trabajador")
public class TrabajadorController {
    @Autowired TrabajadorRepository trabajadorRepository;
    @Autowired SedeRepository sedeRepository;

    @GetMapping(value = "lista")
    public String listar(Model model){
        List<Trabajador> trabajadorLista = trabajadorRepository.findAll();
        model.addAttribute("trabajadorLista",trabajadorLista);
        System.out.println(trabajadorLista);
        return "trabajador/lista";
    }

    @GetMapping({"nuevo"})
    public String crear(Model model) {
        List<Sede> sedeLista = sedeRepository.findAll();
        model.addAttribute("sedeLista",sedeLista);
        return "trabajador/nuevo";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") String id) {
        List<Sede> sedeLista = sedeRepository.findAll();
        model.addAttribute("sedeLista",sedeLista);

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
    public String guardar(Trabajador trabajador, RedirectAttributes attr) {
        System.out.println(trabajador.getId());
        if(trabajadorRepository.existsById(trabajador.getId())){
            Optional<Trabajador> opt = trabajadorRepository.findById(trabajador.getId());
            if (opt.isPresent()) {
                Trabajador trabajador_guardar = opt.get();
                trabajador_guardar.setId(trabajador.getId());
                trabajador_guardar.setCorreo(trabajador.getCorreo());
                trabajador_guardar.setNombres(trabajador.getNombres());
                trabajador_guardar.setApellidos(trabajador.getApellidos());
                trabajador_guardar.setIdsede(trabajador.getIdsede());
                trabajadorRepository.save(trabajador_guardar);
                attr.addFlashAttribute("msgEdit", "Trabajador editado exitosamente");
                return "redirect:/trabajador/lista";
            }
        }else{
            trabajadorRepository.save(trabajador);
            attr.addFlashAttribute("msgSave", "Trabajador creado exitosamente");
        }
        return "redirect:/trabajador/lista";
    }

    @GetMapping(value = "borrar")
    public String borrar(@RequestParam("id") String id, RedirectAttributes attr) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepository.findById(id);

        if (optionalTrabajador.isPresent()) {
            trabajadorRepository.deleteById(id);
            attr.addFlashAttribute("msgDelete", "Trabajador borrado exitosamente");
        }
        return "redirect:/trabajador/lista";
    }
}