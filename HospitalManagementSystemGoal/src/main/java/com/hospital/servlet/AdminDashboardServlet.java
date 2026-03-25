package com.hospital.servlet;

import com.hospital.store.HospitalStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HospitalStore store = HospitalStore.getInstance();
        request.setAttribute("doctors", store.getDoctors());
        request.setAttribute("patients", store.getPatients());
        request.setAttribute("appointments", store.getAppointments());
        request.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HospitalStore store = HospitalStore.getInstance();
        String entityType = request.getParameter("entityType");

        if ("doctor".equals(entityType)) {
            store.addDoctor(
                    request.getParameter("name"),
                    request.getParameter("specialization"),
                    request.getParameter("availability")
            );
        } else if ("patient".equals(entityType)) {
            int age = Integer.parseInt(request.getParameter("age"));
            store.addPatient(
                    request.getParameter("name"),
                    age,
                    request.getParameter("gender"),
                    request.getParameter("contact")
            );
        }

        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }
}
