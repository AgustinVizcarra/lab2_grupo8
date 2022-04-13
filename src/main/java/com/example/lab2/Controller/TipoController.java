package com.example.lab2.Controller;

import com.example.lab2.Entity.Tipo;
import com.example.lab2.Repository.TipoRepository;
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
@RequestMapping(value = "tipo")
public class TipoController {

    @Autowired
    TipoRepository tipoRepository;


    @GetMapping(value = "lista")
    public String listar(Model model){
        List<Tipo> tipoLista = tipoRepository.findAll();
        model.addAttribute("tipoLista", tipoLista);
        return "tipo/lista";
    }

    @GetMapping(value = "crear")
    public String crear() {
        return "tipo/nuevo";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") Integer id) {
        Optional<Tipo> optionalTipo = tipoRepository.findById(id);

        if (optionalTipo.isPresent()) {
            Tipo tipo = optionalTipo.get();
            model.addAttribute("tipo", tipo);
            return "tipo/editar";
        } else {
            return "redirect:/tipo/lista";
        }
    }

    @PostMapping(value = "guardar")
    public String guardar(Tipo tipo, RedirectAttributes attributes) {
        tipoRepository.save(tipo);
        attributes.addFlashAttribute("msg", "Tipo creado exitosamente");
        return "redirect:/tipo/lista";
    }

    @GetMapping(value = "eliminar")
    public String borrar(@RequestParam("id") Integer id) {
        Optional<Tipo> optionalTipo = tipoRepository.findById(id);

        if (optionalTipo.isPresent()) {
            tipoRepository.deleteById(id);
        }
        return "redirect:/tipo/lista";
    }
}
