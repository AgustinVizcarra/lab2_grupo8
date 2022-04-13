package com.example.lab2.Controller;

import com.example.lab2.Entity.Marca;
import com.example.lab2.Entity.Tipo;
import com.example.lab2.Repository.MarcaRepository;
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
@RequestMapping(value = "marca")
public class MarcaController {

    @Autowired
    MarcaRepository marcaRepository;


    @GetMapping(value = "lista")
    public String listar(Model model) {
        List<Marca> marcaLista = marcaRepository.findAll();
        model.addAttribute("lista", marcaLista);
        System.out.println(marcaLista);
        return "marca/lista";
    }

    @GetMapping({"nuevo"})
    public String crear() {
        return "marca/nuevo";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") Integer id) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);

        if (optionalMarca.isPresent()) {
            Marca marca = optionalMarca.get();
            model.addAttribute("marca", marca);
            return "marca/editar";
        } else {
            return "redirect:/marca/lista";
        }
    }

    @PostMapping(value = "guardar")
    public String guardar(Marca marca, RedirectAttributes attributes) {
        marcaRepository.save(marca);
        attributes.addFlashAttribute("msgSave", "Marca creada exitosamente");
        return "redirect:/marca/lista";
    }

    @PostMapping("/actualizar")
    public String actualizar(Marca marcaForm, RedirectAttributes attr) {
        Optional<Marca> optMarca = marcaRepository.findById(marcaForm.getId());
        if (optMarca.isPresent()) {
            Marca marcaFromDb = optMarca.get();
            marcaFromDb.setNombre(marcaForm.getNombre());
            marcaRepository.save(marcaFromDb);
            attr.addFlashAttribute("msgEdit", "Marca editada exitosamente");
        }
        return "redirect:/marca/lista";
    }

    @GetMapping(value = "borrar")
    public String borrar(@RequestParam("id") Integer id, RedirectAttributes attr) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);

        if (optionalMarca.isPresent()) {
            marcaRepository.deleteById(id);
            attr.addFlashAttribute("msgDelete", "Marca borrada exitosamente");
        }
        return "redirect:/marca/lista";
    }
}
