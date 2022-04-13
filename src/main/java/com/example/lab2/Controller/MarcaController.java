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

@Controller
@RequestMapping(value = "marca")
public class MarcaController {

    @Autowired
    MarcaRepository marcaRepository;


    @GetMapping(value = "lista")
    public void listar(Model model){

    };

    @GetMapping(value = "crear")
    public void crear() {

    };

    @GetMapping(value = "editar")
    public void editar(Model model, @RequestParam("id") String id) {

    };

    @PostMapping(value = "guardar")
    public void guardar(Marca marca) {

    };

    @GetMapping(value = "eliminar")
    public void borrar(@RequestParam("id") String id) {

    };
}
