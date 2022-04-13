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

@Controller
@RequestMapping(value = "tipo")
public class TipoController {

    @Autowired
    TipoRepository tipoRepository;


    @GetMapping(value = "lista")
    public String listar(Model model){
        return "";
    }

    @GetMapping(value = "crear")
    public String crear() {
        return "";
    }

    @GetMapping(value = "editar")
    public String editar(Model model, @RequestParam("id") String id) {
        return "";
    }

    @PostMapping(value = "guardar")
    public String guardar(Tipo tipo) {
        return "";
    }

    @GetMapping(value = "eliminar")
    public String borrar(@RequestParam("id") String id) {
        return "";
    }
}
