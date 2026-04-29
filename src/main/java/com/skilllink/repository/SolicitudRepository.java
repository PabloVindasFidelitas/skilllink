package com.skilllink.repository;

import com.skilllink.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    List<Solicitud> findByTutor(String tutor);
    List<Solicitud> findByEstudiante(String estudiante);
}