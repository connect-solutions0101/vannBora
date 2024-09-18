package school.sptech.vannbora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import school.sptech.vannbora.entidade.Usuario;
import school.sptech.vannbora.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;  


@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping    
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> lista = service.listar();

        if(lista.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{email}/{senha}")
    public ResponseEntity<Usuario> buscarPorEmailESenha(@PathVariable String email, @PathVariable String senha) {
        return ResponseEntity.status(200).body(service.buscarPorEmailESenha(email, senha));
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(service.cadastrar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(200).body(service.atualizar(id, usuario));
    }   
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
