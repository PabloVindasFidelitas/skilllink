package com.skilllink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El estudiante es obligatorio")
    private String estudiante;

    @NotBlank(message = "El tutor es obligatorio")
    private String tutor;

    @NotBlank(message = "La materia es obligatoria")
    private String materia;

    private String estado = "pendiente";

    public Long getId() { return id; }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }

    public String getTutor() { return tutor; }
    public void setTutor(String tutor) { this.tutor = tutor; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}