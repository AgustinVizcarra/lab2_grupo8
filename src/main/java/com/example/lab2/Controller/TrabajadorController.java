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

@Controller
@RequestMapping(value = "trabajador")
public class TrabajadorController {
    @Autowired
    TrabajadorRepository trabajadorRepository;

    @GetMapping(value = "lista")
    public String listar(Model model){

    }

    @GetMapping(value = "crear")
    public String crear() {

    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") String id) {

    }

    @PostMapping(value = "guardar")
    public String guardar(Trabajador trabajador) {

    }

    @GetMapping(value = "eliminar")
    public String borrar(@RequestParam("id") String id) {

    }
}
