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

    // FORMULARIO
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("solicitud", new Solicitud());
        return "solicitud";
    }

    // GUARDAR SOLICITUD (HU-09)
    @PostMapping("/save")
    public String guardar(Solicitud solicitud, RedirectAttributes redirect) {
        service.guardar(solicitud);
        redirect.addFlashAttribute("success", "Solicitud enviada correctamente");
        return "redirect:/";
    }

    // VER RECIBIDAS (HU-10)
    @GetMapping("/recibidas")
    public String recibidas(@RequestParam(required = false) String tutor, Model model) {

        if (tutor != null) {
            model.addAttribute("solicitudes", service.obtenerPorTutor(tutor));
        } else {
            model.addAttribute("solicitudes", service.listarTodas());
        }

        return "solicitudes";
    }

    // VER ENVIADAS (HU-11)
    @GetMapping("/enviadas")
    public String enviadas(@RequestParam String estudiante, Model model) {
        model.addAttribute("solicitudes", service.obtenerPorEstudiante(estudiante));
        return "solicitudes";
    }
}