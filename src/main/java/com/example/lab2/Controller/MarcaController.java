package com.example.lab2.Controller;

import com.example.lab2.Entity.Marca;
import com.example.lab2.Repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String guardar(Marca marca) {

        if (marca.getId() != null) {
            try {
                Optional<Marca> opt = marcaRepository.findById(marca.getId());
                if (opt.isPresent()) {
                    Marca marca_guardar = opt.get();
                    marca_guardar.setNombre(marca.getNombre());

                    marcaRepository.save(marca_guardar);
                    System.out.println("se edito exitosamente la marca exitosamente");
                } else {
                    System.out.println("No se encontro la marca :(");
                }
            } catch (Exception e) {
                System.out.println("Ingreso un Nombre invalido");
            }
        } else {
            //no tiene id
            marcaRepository.save(marca);
            System.out.println("Se guardo exitosamente la Marca");
        }

        return "redirect:/trabajador/lista";
    }

    @GetMapping(value = "borrar")
    public String borrar(@RequestParam("id") Integer id) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);

        if (optionalMarca.isPresent()) {
            marcaRepository.deleteById(id);
        }
        return "redirect:/trabajador/lista";
    }
}
