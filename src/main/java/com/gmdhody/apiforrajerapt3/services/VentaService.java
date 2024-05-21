package com.gmdhody.apiforrajerapt3.services;


import com.gmdhody.apiforrajerapt3.entities.Venta;
import com.gmdhody.apiforrajerapt3.dto.CvDto;
import com.gmdhody.apiforrajerapt3.repositories.EmpleadoRepository;
import com.gmdhody.apiforrajerapt3.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository repository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    public List<Venta> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Venta create(CvDto ventaDto){
        Venta venta=new Venta();
        return getVenta(ventaDto,venta);
    }
    public Venta delete(String folio){
        Venta venta=repository.getReferenceById(folio);
        venta.setStatus((byte)0);
        return repository.save(venta);
    }
    private Venta getVenta(CvDto ventaDto,Venta venta){
        venta.setFolio(ventaDto.getFolio());
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(ventaDto.getTotal());
        venta.setClave_empleado(empleadoRepository.findById(ventaDto.getClave_empleado()).get());
        venta.setStatus(ventaDto.getStatus());
        return repository.save(venta);
    }
}
