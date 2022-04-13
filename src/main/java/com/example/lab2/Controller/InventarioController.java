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
    public String listar(Model model, RedirectAttributes attr){
        List<Inventario> listaInventario = inventarioRepository.findAll();
        System.out.println(listaInventario.isEmpty()?"No se encontraron listar":"");
        attr.addFlashAttribute("msg",listaInventario.isEmpty()?"No se encontraron listar":"");
        model.addAttribute("listaInventario",listaInventario);
        return "/inventario/listaInventario";
    }

    @GetMapping(value = "crear")
    public String crear(Model model) {
        model.addAttribute("listaMarcas",marcaRepository.findAll());
        model.addAttribute("listaTipos",tipoRepository.findAll());
        model.addAttribute("listaSedes",sedeRepository.findAll());
        return "/inventario/nuevoInventario";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") String id) {

        return "/inventario/editarInventario";
    }

    @PostMapping(value = "guardar")
    public String guardar(Inventario inventario, RedirectAttributes attr) {
        return "redirect:/inventario/listarInventario";
    }

    @GetMapping(value = "eliminar")
    public String borrar(@RequestParam("id") String id, RedirectAttributes attr) {
        return "redirect:/inventario/listarInventario";
    }
}
