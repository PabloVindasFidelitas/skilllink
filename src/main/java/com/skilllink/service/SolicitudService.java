package com.skilllink.service;

import com.skilllink.model.Solicitud;
import com.skilllink.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository repo;

    public SolicitudService(SolicitudRepository repo){
        this.repo = repo;
    }

    public void save(Solicitud s){
        repo.save(s);
    }

    public List<Solicitud> getAll(){
        return repo.findAll();
    }
}