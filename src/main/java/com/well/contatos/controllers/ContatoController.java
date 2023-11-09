package com.well.contatos.controllers;


import com.well.contatos.dtos.ContatoDTORequest;
import com.well.contatos.dtos.ContatoDTOResponse;
import com.well.contatos.mapper.ContatoDTORequestMapper;
import com.well.contatos.mapper.ContatoDTOResponseMapper;
import com.well.contatos.models.ContatoModel;
import com.well.contatos.services.ContatoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<ContatoDTOResponse>> getAllContatos(){
        List<ContatoModel> contatoList = contatoService.listAll();
        if(!contatoList.isEmpty()) {
            for(ContatoModel contatoModel : contatoList) {
                UUID id = contatoModel.getIdContato();
                contatoModel.add(linkTo(methodOn(ContatoController.class).getOneContato(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ContatoDTOResponseMapper.mapTo(contatoList));
    }

    @PostMapping("/contatos")
    public ResponseEntity<ContatoDTOResponse> saveContato(@RequestBody @Valid ContatoDTORequest contatoDTORequest) {
        ContatoDTOResponse contatoDTOResponse = ContatoDTOResponseMapper.mapTo(contatoService.save(ContatoDTORequestMapper.mapTo(contatoDTORequest)));
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoDTOResponse);
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

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Object> getOneContato(@PathVariable(value="id") UUID id){
        Optional<ContatoModel> contatoModel = contatoService.get(id);
        if(contatoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
        }
        contatoModel.get().add(linkTo(methodOn(ContatoController.class).getAllContatos()).withRel("Lista de Contatos"));
        ContatoDTOResponse contatoDTOResponse = ContatoDTOResponseMapper.mapTo(contatoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(contatoDTOResponse);
    }


    @PutMapping("/contatos/{id}")
    public ResponseEntity<Object> updateContato(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid  ContatoDTORequest contatoDTORequest) {
        Optional<ContatoModel> contatoModel = contatoService.get(id);
        if(contatoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado..");
        }
        ContatoModel model = ContatoDTORequestMapper.mapTo(contatoDTORequest);
        model.setIdContato(id);
        ContatoDTOResponse contatoDTOResponse = ContatoDTOResponseMapper.mapTo(contatoService.save(model));
        return ResponseEntity.status(HttpStatus.OK).body(contatoDTOResponse);
    }
}
