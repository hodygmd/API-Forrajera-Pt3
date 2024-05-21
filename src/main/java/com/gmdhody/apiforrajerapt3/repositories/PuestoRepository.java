package com.gmdhody.apiforrajerapt3.repositories;


import com.gmdhody.apiforrajerapt3.entities.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto,Integer> {
    @Query("select p from Puesto p where p.status=1 order by p.nombre asc ")
    public List<Puesto> findAllByStatus();
    @Query("select p.nombre from Puesto p where p.status=1")
    public List<String> findNombresByStatus();
}