package com.skilllink.service;

import com.skilllink.model.Solicitud;
import com.skilllink.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository repo;

    public SolicitudService(SolicitudRepository repo) {
        this.repo = repo;
    }

    public void guardar(Solicitud s) {
        if (s.getTutor() == null || s.getEstudiante() == null || s.getMateria() == null) {
            throw new RuntimeException("Datos incompletos");
        }
        repo.save(s);
    }

    public List<Solicitud> obtenerPorTutor(String tutor) {
        return repo.findByTutor(tutor);
    }

    public List<Solicitud> obtenerPorEstudiante(String estudiante) {
        return repo.findByEstudiante(estudiante);
    }

    public List<Solicitud> listarTodas() {
        return repo.findAll();
    }

    public Solicitud obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void aceptarSolicitud(Long id) {
        Solicitud s = obtenerPorId(id);
        if (s != null) {
            s.setEstado("aceptada");
            repo.save(s);
        }
    }

    public void rechazarSolicitud(Long id) {
        Solicitud s = obtenerPorId(id);
        if (s != null) {
            s.setEstado("rechazada");
            repo.save(s);
        }
    }
}