package com.example.lab2.Controller;


import com.example.lab2.Entity.Inventario;
import com.example.lab2.Repository.InventarioRepository;
import com.example.lab2.Repository.MarcaRepository;
import com.example.lab2.Repository.SedeRepository;
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
@RequestMapping(value = "inventario")
public class InventarioController {

    @Autowired
    InventarioRepository inventarioRepository;
    @Autowired
    TipoRepository tipoRepository;
    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    SedeRepository sedeRepository;

    @GetMapping(value = "lista")
    public String listar(Model model, RedirectAttributes attr) {
        List<Inventario> listaInventario = inventarioRepository.findAll();
        System.out.println(listaInventario.isEmpty() ? "No se encontraron listar" : "");
        attr.addFlashAttribute("msg", listaInventario.isEmpty() ? "No se encontraron listar" : "");
        model.addAttribute("listaInventario", listaInventario);
        return "/inventario/listaInventario";
    }

    @GetMapping(value = "crear")
    public String crear(Model model) {
        model.addAttribute("listaMarcas", marcaRepository.findAll());
        model.addAttribute("listaTipos", tipoRepository.findAll());
        model.addAttribute("listaSedes", sedeRepository.findAll());
        return "/inventario/nuevoInventario";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") String id) {
        model.addAttribute("listaMarcas", marcaRepository.findAll());
        model.addAttribute("listaTipos", tipoRepository.findAll());
        model.addAttribute("listaSedes", sedeRepository.findAll());
        try {
            Optional<Inventario> inventario = inventarioRepository.findById(Integer.parseInt(id));
            if (inventario.isPresent()) {
                model.addAttribute("inventario", inventario.get());
            } else {
                System.out.println("No se ha encontrado un inventario con ese id");
            }
        } catch (Exception e) {
            System.out.println("Ha ingresado un id invalido");
        }
        return "/inventario/editarInventario";
    }

    @PostMapping(value = "guardar")
    public String guardar(Inventario inventario, RedirectAttributes attr) {
        if (inventario.getId() == null) {
            inventarioRepository.save(inventario);
            System.out.println("El inventario se guardo exitosamente");
            attr.addFlashAttribute("msgSave", "El inventario se creo exitosamente");
        } else {
            try {
                Optional<Inventario> opt = inventarioRepository.findById(inventario.getId());
                if (opt.isPresent()) {
                    Inventario inventario_guardar = opt.get();
                    inventario_guardar.setEstado(inventario.getEstado());
                    inventario_guardar.setIdmarca(inventario.getIdmarca());
                    inventario_guardar.setIdtipo(inventario.getIdtipo());
                    inventario_guardar.setNumeroserie(inventario.getNumeroserie());
                    inventario_guardar.setNombre(inventario.getNombre());
                    inventario_guardar.setIdsede(inventario.getIdsede());
                    //finalmente se guarda
                    inventarioRepository.save(inventario_guardar);
                    System.out.println("El inventario se edito exitosamente");
                    attr.addFlashAttribute("msgEdit", "El inventario se edito exitosamente");
                }
            } catch (Exception e) {
                System.out.println("Se envio un id invalido en el cuerpo de inventario");
            }
        }
        return "redirect:/inventario/lista";
    }

    @GetMapping(value = "eliminar")
    public String borrar(@RequestParam("id") String id, RedirectAttributes attr) {
        try {
            inventarioRepository.deleteById(Integer.parseInt(id));
            System.out.println("El inventario se ha eliminado exitosamente");
            attr.addFlashAttribute("msgDelete", "El inventario se ha eliminado exitosamente");
        } catch (Exception e) {
            System.out.println("Se ha ingresado un id invalido");
        }
        return "redirect:/inventario/lista";
    }
}
