package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import br.com.itilh.bdpedidos.sistemapedidos.repository.EstadoRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class EstadoController {

    private final EstadoRepository repositorio;
    public EstadoController(EstadoRepository repositorio){
        this.repositorio = repositorio;
    }



    @GetMapping("/estados")
    public List<Estado> getTodos() {
        return (List<Estado>) repositorio.findAll();
    }

    @PostMapping("/estado")
    public Estado postEstado(@RequestBody Estado entity) {
        return repositorio.save(entity);
    }
    
    

}
