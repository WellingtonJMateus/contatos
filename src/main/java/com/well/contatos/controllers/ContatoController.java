package com.well.contatos.controllers;


import com.well.contatos.controllers.exception.ContatoNotFoundException;
import com.well.contatos.controllers.exception.ContatoNotSaveException;
import com.well.contatos.dtos.ContatoDTORequest;
import com.well.contatos.dtos.ContatoDTOResponse;
import com.well.contatos.mapper.ContatoDTORequestMapper;
import com.well.contatos.mapper.ContatoDTOResponseMapper;
import com.well.contatos.entity.ContatoEntity;
import com.well.contatos.services.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@RestController("/contatos")
@RestController
@RequestMapping(path = "/contatos/")
public class ContatoController {


    private final ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping("numberrows")
    public ResponseEntity<Long> getNumeroDeRegistros() {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.getnumberOfRows());
    }

    @GetMapping("paginacao")
    public ResponseEntity<Object> getPaginacao(@RequestParam(name = "pageNumber") int pageNumber,
                                                                 @RequestParam(name = "pageSize") int pageSize,
                                                                 @RequestParam(name = "orderPorCampo") String sortBy) {
        List<ContatoDTOResponse> paginacao = contatoService.getPaginacao(pageNumber, pageSize, sortBy);
        if(paginacao.isEmpty()){
            throw new ContatoNotFoundException("Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(paginacao);
    }

    @GetMapping("numberrowsbyage/{age}")
    public ResponseEntity<Object> getContatosPorIdade(@PathVariable(value = "age") Integer age) {
        List<ContatoDTOResponse> contatoByAge = contatoService.getContatoByAge(age);
        if(contatoByAge.isEmpty()){
            throw new ContatoNotFoundException("Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoByAge);
    }

    @GetMapping("ordenadoPor/{campo}")
    public ResponseEntity<Object> getContatosOrdenadosPorCampo(@PathVariable(value = "campo") String campo) {
        List<ContatoDTOResponse> contatoOrdernadoPor = contatoService.getContatoOrdernadoPor(campo);
        if(contatoOrdernadoPor.isEmpty()){
            throw new ContatoNotFoundException("Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.getContatoOrdernadoPor(campo));
    }

    @GetMapping
    public ResponseEntity<Object> getAllContatos() {
        List<ContatoDTOResponse> contatoList = contatoService.listAll();
        if(contatoList.isEmpty()){
            throw new ContatoNotFoundException("Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoList);
    }

    @GetMapping("buscarpornome/{nome}")
    public ResponseEntity<Object> getAllContatosInicializaCom(@PathVariable(value = "nome") String nome) {
        List<ContatoDTOResponse> contatoList = contatoService.getContatoPorNomeInicializaCom(nome);
        if(contatoList.isEmpty()){
            throw new ContatoNotFoundException("Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoList);
    }

    @PostMapping
    public ResponseEntity<Object> saveContato(@RequestBody @Valid ContatoDTORequest contatoDTORequest) {
        ContatoEntity contatoEntity = ContatoDTORequestMapper.mapTo(contatoDTORequest);
        if(contatoEntity == null){
            throw new ContatoNotSaveException("Not Saved");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.save(ContatoDTORequestMapper.mapTo(contatoDTORequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContato(@PathVariable(value = "id") UUID id) {
        Optional<ContatoEntity> contatoModel = contatoService.get(id);
        if (contatoModel.isEmpty()) {
            throw new ContatoNotFoundException("Not found");
        }
        contatoService.delete(contatoModel.get().getIdContato());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneContato(@PathVariable(value = "id") UUID id) {
        Optional<ContatoEntity> contatoModel = contatoService.get(id);
        if (contatoModel.isEmpty()) {
            throw new ContatoNotFoundException("Not found");
        }
        contatoModel.get().add(linkTo(methodOn(ContatoController.class).getAllContatos()).withRel("Lista de Contatos"));
        ContatoDTOResponse contatoDTOResponse = ContatoDTOResponseMapper.mapTo(contatoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(contatoDTOResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContato(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ContatoDTORequest contatoDTORequest) {
        Optional<ContatoEntity> contatoModel = contatoService.get(id);
        if (contatoModel.isEmpty()) {
            throw new ContatoNotFoundException("Not found");
        }
        ContatoEntity model = ContatoDTORequestMapper.mapTo(contatoDTORequest);
        model.setIdContato(id);
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.save(model));
    }


}
