package com.gmdhody.apiforrajerapt3.controllers;


import com.gmdhody.apiforrajerapt3.dto.DcvDto;
import com.gmdhody.apiforrajerapt3.entities.DetalleVenta;
import com.gmdhody.apiforrajerapt3.services.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dv")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService service;
    @PostMapping("/create")
    public ResponseEntity<DetalleVenta[]> create(@RequestBody DcvDto[] dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{folio}")
    public ResponseEntity<DetalleVenta[]> getAllByFolio(@PathVariable("folio")String folio){
        return new ResponseEntity<>(service.getAllByFolio(folio),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Integer id){
        service.delete(id);
    }
}
