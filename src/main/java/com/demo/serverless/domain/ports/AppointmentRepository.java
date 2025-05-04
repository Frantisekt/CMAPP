package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.Appointment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    Optional<Appointment> findById(UUID id);
    List<Appointment> findByPatientId(UUID patientId);
    List<Appointment> findByDentistId(UUID dentistId);
    List<Appointment> findByDentistIdAndDate(UUID dentistId, LocalDateTime date);
    List<Appointment> findByConsultationNumber(String consultationNumber);
    void delete(UUID id);
    boolean existsById(UUID id);
    boolean existsByDentistIdAndDateTime(UUID dentistId, LocalDateTime dateTime);
} 