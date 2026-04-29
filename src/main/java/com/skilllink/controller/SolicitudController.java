package com.skilllink.controller;

import com.skilllink.model.Solicitud;
import com.skilllink.service.SolicitudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) String tutor,
                       @RequestParam(required = false) String materia,
                       Model model) {

        Solicitud solicitud = new Solicitud();

        if (tutor != null) {
            solicitud.setTutor(tutor);
        }

        if (materia != null) {
            solicitud.setMateria(materia);
        }

        model.addAttribute("solicitud", solicitud);
        return "solicitud";
    }

    @PostMapping("/save")
    public String guardar(Solicitud solicitud, RedirectAttributes redirect) {
        service.guardar(solicitud);
        redirect.addFlashAttribute("success", "Solicitud enviada correctamente");
        return "redirect:/";
    }

    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("solicitudes", service.listarTodas());
        return "solicitudes";
    }

    @GetMapping("/recibidas")
    public String recibidas(@RequestParam(required = false) String tutor, Model model) {

        if (tutor != null && !tutor.trim().isEmpty()) {
            model.addAttribute("solicitudes", service.obtenerPorTutor(tutor));
        } else {
            model.addAttribute("solicitudes", service.listarTodas());
        }

        return "solicitudes";
    }

    @GetMapping("/enviadas")
    public String enviadas(@RequestParam(required = false) String estudiante, Model model) {

        if (estudiante != null && !estudiante.trim().isEmpty()) {
            model.addAttribute("solicitudes", service.obtenerPorEstudiante(estudiante));
        } else {
            model.addAttribute("solicitudes", service.listarTodas());
        }

        return "solicitudes";
    }

    @GetMapping("/aceptar/{id}")
    public String aceptar(@PathVariable Long id, RedirectAttributes redirect) {
        service.aceptarSolicitud(id);
        redirect.addFlashAttribute("success", "Solicitud aceptada correctamente");
        return "redirect:/solicitudes";
    }

    @GetMapping("/rechazar/{id}")
    public String rechazar(@PathVariable Long id, RedirectAttributes redirect) {
        service.rechazarSolicitud(id);
        redirect.addFlashAttribute("success", "Solicitud rechazada correctamente");
        return "redirect:/solicitudes";
    }
}