package com.gmdhody.apiforrajerapt3.services;


import com.gmdhody.apiforrajerapt3.dto.DcvDto;
import com.gmdhody.apiforrajerapt3.entities.DetalleVenta;
import com.gmdhody.apiforrajerapt3.entities.Producto;
import com.gmdhody.apiforrajerapt3.entities.Venta;
import com.gmdhody.apiforrajerapt3.repositories.DetalleVentaRepository;
import com.gmdhody.apiforrajerapt3.repositories.ProductoRepository;
import com.gmdhody.apiforrajerapt3.repositories.VentaRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaService {
    @Autowired
    private DetalleVentaRepository repository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    private Float t;
    private Integer e;

    public DetalleVenta[] create(DcvDto[] detalleVentaDtos) {
        return getDv(detalleVentaDtos);
    }

    public DetalleVenta[] getAllByFolio(String folio) {
        return repository.findByFolio_v(folio);
    }

    public void delete(Integer id) {
        Venta venta = ventaRepository.getReferenceById(repository.findById(id).get().getFolio_cv().getFolio());
        DetalleVenta detalleVenta = repository.getReferenceById(id);
        t = venta.getTotal();
        t -= (detalleVenta.getPrecio() * detalleVenta.getCantidad());
        venta.setTotal(t);
        Producto producto=productoRepository.getReferenceById(repository.findById(id).get().getClave_producto().getClave());
        e=producto.getExistencias()+detalleVenta.getCantidad();
        producto.setExistencias(e);
        productoRepository.save(producto);
        ventaRepository.save(venta);
        repository.deleteById(id);
    }

    private DetalleVenta[] getDv(DcvDto[] dto) {
        Venta venta = ventaRepository.getReferenceById(dto[0].getFolio_cv());
        t = venta.getTotal();
        for (byte i = 0; i < dto.length; i++) {
            DetalleVenta dv=repository.findByClave_producto(dto[i].getClave_producto(),dto[i].getFolio_cv());
            if (dv != null) {
                // Si ya existe, puedes ignorar el registro duplicado o lanzar una excepción
                continue; // Ignorar el registro duplicado y pasar al siguiente
                // throw new Exception("Registro duplicado"); // Lanzar excepción
            }
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setFolio_cv((Venta) Hibernate.unproxy(venta));
            detalleVenta.setClave_producto(productoRepository.findById(dto[i].getClave_producto()).get());
            detalleVenta.setCantidad(dto[i].getCantidad());
            detalleVenta.setPrecio(productoRepository.findById(dto[i].getClave_producto()).get().getPrecio());
            repository.save(detalleVenta);
            t += (detalleVenta.getCantidad() * detalleVenta.getPrecio());
            venta.setTotal(t);
            ventaRepository.save(venta);
            Producto producto=productoRepository.getReferenceById(dto[i].getClave_producto());
            e=producto.getExistencias()-dto[i].getCantidad();
            producto.setExistencias(e);
            productoRepository.save(producto);

        }
        return repository.findByFolio_v(dto[0].getFolio_cv());
    }
}
