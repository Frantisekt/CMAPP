package com.demo.serverless.infrastructure.adapters;

import com.demo.serverless.domain.model.Appointment;
import com.demo.serverless.domain.ports.AppointmentRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryAppointmentRepository implements AppointmentRepository {
    private final Map<UUID, Appointment> appointments = new ConcurrentHashMap<>();

    @Override
    public Appointment save(Appointment appointment) {
        appointments.put(appointment.getId(), appointment);
        return appointment;
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return Optional.ofNullable(appointments.get(id));
    }

    @Override
    public List<Appointment> findByPatientId(UUID patientId) {
        return appointments.values().stream()
            .filter(a -> a.getPatientId().equals(patientId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDentistId(UUID dentistId) {
        return appointments.values().stream()
            .filter(a -> a.getDentistId().equals(dentistId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDentistIdAndDate(UUID dentistId, LocalDateTime date) {
        return appointments.values().stream()
            .filter(a -> a.getDentistId().equals(dentistId) &&
                        a.getDateTime().toLocalDate().equals(date.toLocalDate()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByConsultationNumber(String consultationNumber) {
        return appointments.values().stream()
            .filter(a -> a.getConsultationNumber().equals(consultationNumber))
            .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        appointments.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return appointments.containsKey(id);
    }

    @Override
    public boolean existsByDentistIdAndDateTime(UUID dentistId, LocalDateTime dateTime) {
        return appointments.values().stream()
            .anyMatch(a -> a.getDentistId().equals(dentistId) &&
                          a.getDateTime().equals(dateTime) &&
                          !a.getStatus().equals("CANCELLED"));
    }
} 