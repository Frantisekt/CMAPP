package com.demo.serverless.application.usecases;

import com.demo.serverless.domain.exceptions.AppointmentNotFoundException;
import com.demo.serverless.domain.exceptions.DentistNotFoundException;
import com.demo.serverless.domain.exceptions.PatientNotFoundException;
import com.demo.serverless.domain.model.Appointment;
import com.demo.serverless.domain.ports.AppointmentRepository;
import com.demo.serverless.domain.ports.DentistRepository;
import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ManageAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    private final DentistRepository dentistRepository;
    private final PatientRepository patientRepository;

    public ManageAppointmentUseCase(
            AppointmentRepository appointmentRepository,
            DentistRepository dentistRepository,
            PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        // Validar que el odontólogo existe
        if (!dentistRepository.existsById(appointment.getDentistId())) {
            throw DentistNotFoundException.byId(appointment.getDentistId().toString());
        }

        // Validar que el paciente existe
        if (!patientRepository.existsById(appointment.getPatientId())) {
            throw PatientNotFoundException.byId(appointment.getPatientId().toString());
        }

        // Validar que no hay otra cita en el mismo horario
        if (appointmentRepository.existsByDentistIdAndDateTime(
                appointment.getDentistId(), appointment.getDateTime())) {
            throw new IllegalArgumentException("El odontólogo ya tiene una cita en este horario");
        }

        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> AppointmentNotFoundException.byId(id.toString()));
    }

    public List<Appointment> getAppointmentsByPatientId(UUID patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        if (appointments.isEmpty()) {
            throw AppointmentNotFoundException.byPatientId(patientId.toString());
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByDentistId(UUID dentistId) {
        List<Appointment> appointments = appointmentRepository.findByDentistId(dentistId);
        if (appointments.isEmpty()) {
            throw AppointmentNotFoundException.byDentistId(dentistId.toString());
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByDentistIdAndDate(UUID dentistId, LocalDateTime date) {
        List<Appointment> appointments = appointmentRepository.findByDentistIdAndDate(dentistId, date);
        if (appointments.isEmpty()) {
            throw AppointmentNotFoundException.byDentistId(dentistId.toString());
        }
        return appointments;
    }

    public Appointment updateAppointment(UUID id, Appointment updatedAppointment) {
        if (!appointmentRepository.existsById(id)) {
            throw AppointmentNotFoundException.byId(id.toString());
        }

        // Validar que el odontólogo existe
        if (!dentistRepository.existsById(updatedAppointment.getDentistId())) {
            throw DentistNotFoundException.byId(updatedAppointment.getDentistId().toString());
        }

        // Validar que el paciente existe
        if (!patientRepository.existsById(updatedAppointment.getPatientId())) {
            throw PatientNotFoundException.byId(updatedAppointment.getPatientId().toString());
        }

        // Validar que no hay otra cita en el mismo horario (excluyendo la cita actual)
        if (appointmentRepository.existsByDentistIdAndDateTime(
                updatedAppointment.getDentistId(), updatedAppointment.getDateTime())) {
            throw new IllegalArgumentException("El odontólogo ya tiene una cita en este horario");
        }

        updatedAppointment.setId(id);
        return appointmentRepository.save(updatedAppointment);
    }

    public void cancelAppointment(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(() -> AppointmentNotFoundException.byId(id.toString()));
        
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(UUID id) {
        if (!appointmentRepository.existsById(id)) {
            throw AppointmentNotFoundException.byId(id.toString());
        }
        appointmentRepository.delete(id);
    }
} 