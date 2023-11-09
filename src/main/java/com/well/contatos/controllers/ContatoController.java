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

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping("/contatos/numberrows")
    public ResponseEntity<Long> getNumeroDeRegistros(){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.getnumberOfRows());
    }

    @GetMapping("/contatos/paginacao")
    public ResponseEntity<List<ContatoDTOResponse>> getPaginacao(@RequestParam(name = "pageNumber") int pageNumber,
                                                                 @RequestParam(name = "pageSize") int pageSize,
                                                                 @RequestParam(name = "orderPorCampo") String sortBy){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.getPaginacao(pageNumber, pageSize, sortBy));
    }

    @GetMapping("/contatos/numberrowsbyage/{age}")
    public ResponseEntity<List<ContatoDTOResponse>> getContatosPorIdade(@PathVariable(value="age") Integer age){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.getContaModelByAge(age));
    }

    @GetMapping("/contatos/ordenadoPor/{campo}")
    public ResponseEntity<List<ContatoDTOResponse>> getContatosOrdenadosPorCampo(@PathVariable(value="campo") String campo){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.getContatoModelOrdernadoPor(campo));
    }

    @GetMapping("/contatos")
    public ResponseEntity<List<ContatoDTOResponse>> getAllContatos(){
        List<ContatoDTOResponse> contatoList = contatoService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(contatoList);
    }

    @PostMapping("/contatos")
    public ResponseEntity<ContatoDTOResponse> saveContato(@RequestBody @Valid ContatoDTORequest contatoDTORequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.save(ContatoDTORequestMapper.mapTo(contatoDTORequest)));
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
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.save(model));
    }
}
