package com.hospital.servlet;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.store.HospitalStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/receptionist/dashboard")
public class ReceptionistDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HospitalStore store = HospitalStore.getInstance();
        request.setAttribute("doctors", store.getDoctors());
        request.setAttribute("patients", store.getPatients());
        request.setAttribute("appointmentViews", buildAppointmentViews(store));
        request.getRequestDispatcher("/WEB-INF/views/receptionist-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HospitalStore store = HospitalStore.getInstance();
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String appointmentDate = request.getParameter("appointmentDate");
        String notes = request.getParameter("notes");

        store.addAppointment(patientId, doctorId, appointmentDate, notes);
        response.sendRedirect(request.getContextPath() + "/receptionist/dashboard");
    }

    private List<AppointmentView> buildAppointmentViews(HospitalStore store) {
        List<AppointmentView> views = new ArrayList<>();
        for (Appointment appointment : store.getAppointments()) {
            Patient patient = store.findPatientById(appointment.getPatientId());
            Doctor doctor = store.findDoctorById(appointment.getDoctorId());
            views.add(new AppointmentView(
                    appointment.getId(),
                    patient == null ? "Unknown Patient" : patient.getName(),
                    doctor == null ? "Unknown Doctor" : doctor.getName(),
                    appointment.getAppointmentDate(),
                    appointment.getStatus(),
                    appointment.getNotes()
            ));
        }
        return views;
    }

    public static class AppointmentView {
        private final int id;
        private final String patientName;
        private final String doctorName;
        private final String appointmentDate;
        private final String status;
        private final String notes;

        public AppointmentView(int id, String patientName, String doctorName, String appointmentDate, String status, String notes) {
            this.id = id;
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.appointmentDate = appointmentDate;
            this.status = status;
            this.notes = notes;
        }

        public int getId() {
            return id;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public String getAppointmentDate() {
            return appointmentDate;
        }

        public String getStatus() {
            return status;
        }

        public String getNotes() {
            return notes;
        }
    }
}
