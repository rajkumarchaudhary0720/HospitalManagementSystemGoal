package com.hospital.model;

public class Appointment {
    private final int id;
    private final int patientId;
    private final int doctorId;
    private final String appointmentDate;
    private final String notes;
    private final String status;

    public Appointment(int id, int patientId, int doctorId, String appointmentDate, String notes, String status) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.notes = notes;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }
}
