package com.gmdhody.apiforrajerapt3.controllers;


import com.gmdhody.apiforrajerapt3.dto.CvDto;
import com.gmdhody.apiforrajerapt3.entities.Venta;
import com.gmdhody.apiforrajerapt3.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaService service;
    @GetMapping
    public ResponseEntity<List<Venta>> getAllByStatus(){
        return new ResponseEntity<>(service.getAllByStatus(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Venta> create(@RequestBody CvDto ventaDto){
        return new ResponseEntity<>(service.create(ventaDto), HttpStatus.CREATED);
    }
    @PutMapping("/delete/{folio}")
    public ResponseEntity<Venta> delete(@PathVariable("folio")String folio){
        return new ResponseEntity<>(service.delete(folio),HttpStatus.OK);
    }
}
