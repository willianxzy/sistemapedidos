package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import br.com.itilh.bdpedidos.sistemapedidos.repository.EstadoRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
public class EstadoController {

    private final EstadoRepository repositorio;

    public EstadoController(EstadoRepository repositorio){
        this.repositorio = repositorio;
    }

    // get para exibir toda a lista de estados presente no bd
    @GetMapping("/estados")
    public List<Estado> getTodos() {
        return (List<Estado>) repositorio.findAll();
    }

    // get para exibir somente um Estado presente dentro do bd
    @GetMapping("/estado/{id}")
    public Estado getPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
            () -> new Exception("ID inválido.")
        );
    }
    

    @PostMapping("/estado")
    public Estado postEstado(@RequestBody Estado entity)  throws Exception{
        try{
        return repositorio.save(entity);
        }catch(Exception e){
            throw new Exception("Erro ao Salvar o Estado.");
        }
    }
    
    @PutMapping("/estado/{id}")
    public Estado alterarEstado(@PathVariable BigInteger id, @RequestBody Estado novosDados) throws Exception {
        Optional<Estado> estadoArmazenado = repositorio.findById(id);
        if (estadoArmazenado.isPresent()) {
            // Atribuir novo nome ao objeto já existente no bd
            estadoArmazenado.get().setNome(novosDados.getNome());
            return repositorio.save(estadoArmazenado.get());
        }
        throw new Exception("Alteração não foi realizada.");
    }

    @DeleteMapping("/estado/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception{

        Optional<Estado> estadoArmazenado = repositorio.findById(id);

        if (estadoArmazenado.isPresent()){
            repositorio.delete(estadoArmazenado.get());
            return "Excluído";
        }
        throw new Exception("ID não encontrado para a exclusão.");
    }

}
