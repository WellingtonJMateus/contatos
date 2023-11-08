package com.well.contatos.controllers;


import com.well.contatos.dtos.ContatoRecordDTO;
import com.well.contatos.models.ContatoModel;
import com.well.contatos.services.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping("/contatos")
    public ResponseEntity<List<ContatoModel>> getAllContatos(){
        List<ContatoModel> contatoList = contatoService.listAll();
        if(!contatoList.isEmpty()) {
            for(ContatoModel contatoModel : contatoList) {
                UUID id = contatoModel.getIdContato();
                contatoModel.add(linkTo(methodOn(ContatoController.class).getOneContato(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoList);
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Object> getOneContato(@PathVariable(value="id") UUID id){
        Optional<ContatoModel> contatoModel = contatoService.get(id);
        if(contatoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
        }
        contatoModel.get().add(linkTo(methodOn(ContatoController.class).getAllContatos()).withRel("Lista de Contatos"));
        return ResponseEntity.status(HttpStatus.OK).body(contatoModel.get());
    }

    @PostMapping("/contatos")
    public ResponseEntity<ContatoModel> saveContato(@RequestBody @Valid ContatoRecordDTO contatoRecordDTO) {
        var contatoModel = new ContatoModel();
        BeanUtils.copyProperties(contatoRecordDTO, contatoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.save(contatoModel));
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<Object> deleteContato(@PathVariable(value="id") UUID id) {
        Optional<ContatoModel> contatoModel = contatoService.get(id);
        if(contatoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
        }
        contatoService.delete(contatoModel.get().getIdContato());
        return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso..");
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<Object> updateContato(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ContatoRecordDTO contatoRecordDTO) {
        Optional<ContatoModel> contatoModel = contatoService.get(id);
        if(contatoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado..");
        }
        var contato = contatoModel.get();
        BeanUtils.copyProperties(contatoRecordDTO, contato);
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.save(contato));
    }
}
