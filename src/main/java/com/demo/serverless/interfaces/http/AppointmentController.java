package com.demo.serverless.interfaces.http;

import com.demo.serverless.application.usecases.ManageAppointmentUseCase;
import com.demo.serverless.domain.model.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final ManageAppointmentUseCase manageAppointmentUseCase;

    public AppointmentController(ManageAppointmentUseCase manageAppointmentUseCase) {
        this.manageAppointmentUseCase = manageAppointmentUseCase;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(manageAppointmentUseCase.createAppointment(appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(manageAppointmentUseCase.getAppointmentById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable UUID patientId) {
        return ResponseEntity.ok(manageAppointmentUseCase.getAppointmentsByPatientId(patientId));
    }

    @GetMapping("/dentist/{dentistId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDentistId(@PathVariable UUID dentistId) {
        return ResponseEntity.ok(manageAppointmentUseCase.getAppointmentsByDentistId(dentistId));
    }

    @GetMapping("/dentist/{dentistId}/date")
    public ResponseEntity<List<Appointment>> getAppointmentsByDentistIdAndDate(
            @PathVariable UUID dentistId,
            @RequestParam LocalDateTime date) {
        return ResponseEntity.ok(manageAppointmentUseCase.getAppointmentsByDentistIdAndDate(dentistId, date));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(manageAppointmentUseCase.getAllAppointments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable UUID id,
            @RequestBody Appointment appointment) {
        return ResponseEntity.ok(manageAppointmentUseCase.updateAppointment(id, appointment));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable UUID id) {
        manageAppointmentUseCase.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
        manageAppointmentUseCase.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
} 