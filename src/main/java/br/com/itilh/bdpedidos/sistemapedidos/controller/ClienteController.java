package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;
import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class ClienteController {

    private final ClienteRepository repositorio;

    public ClienteController(ClienteRepository repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/clientes")
    public List<Cliente> getCliente() {
        return (List<Cliente>) repositorio.findAll();
    }
    
    @GetMapping("/cliente/{id}")
    public Cliente getClientePorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
            () -> new Exception("ID inválido.")     
        );
    }
    
    @PostMapping("/cliente")
    public Cliente postCliente(@RequestBody Cliente entity) throws Exception{
        try{ 
            return repositorio.save(entity);
        }catch (Exception ex){
            throw new Exception("Não foi possível criar um Municipio" + ex.getMessage());
        }    
    }
    
    @PutMapping("/cliente/{id}")
    public Cliente putCliente(@PathVariable String id, @RequestBody Cliente entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {
            throw new Exception("Não foi possível alterar o Cliente." + ex.getMessage());
        }
    }

    @DeleteMapping("/cliente/{id}")
    public String deleteCliente(@PathVariable BigInteger id) throws Exception {
        try {
            repositorio.deleteById(id);
            return "Excluído.";
        } catch (Exception ex) {
            throw new Exception("Não foi possível deletar o id informado." + ex.getMessage());
        }
    }
}
