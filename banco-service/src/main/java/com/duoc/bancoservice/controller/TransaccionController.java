package com.duoc.bancoservice.controller;

import com.duoc.bancoservice.model.Transaccion;
import com.duoc.bancoservice.repository.TransaccionRepository;
import com.duoc.bancoservice.dto.TransaccionEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.jms.core.JmsTemplate;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionRepository repository;
    private final JmsTemplate jmsTemplate; // Herramienta para enviar mensajes

    // Inyectamos ambas dependencias en el constructor
    public TransaccionController(TransaccionRepository repository, JmsTemplate jmsTemplate) {
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping
    public List<Transaccion> obtenerTransacciones() {
        return repository.findAll();
    }

    // --- NUEVO ENDPOINT QUE GUARDA Y ENVÍA EL MENSAJE JMS ---
    @PostMapping
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion) {
        // 1. Guardamos la transacción en la base de datos local
        Transaccion transaccionGuardada = repository.save(transaccion);

// 2. Armamos el evento con los datos (Ajusta los get() si tus atributos se llaman distinto)
    TransaccionEvent evento = new TransaccionEvent(
        transaccionGuardada.getId().toString(),
        "CTA-GENERICA", // Tu modelo no tiene cuenta, enviamos un texto por defecto
        BigDecimal.valueOf(transaccionGuardada.getMonto()), // Convertimos de Double a BigDecimal
        transaccionGuardada.getTipo()
    );

        // 3. Enviamos el mensaje a la cola
        System.out.println("==> [PRODUCIENDO EVENTO] Enviando transacción ID " + evento.getIdTransaccion() + " a la cola JMS...");
        jmsTemplate.convertAndSend("cola.transacciones.banco", evento);

        return transaccionGuardada;
    }
}