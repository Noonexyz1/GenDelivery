/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.miempresa.controlador;

import com.miempresa.bean.CargaBean;
import com.miempresa.entidades.Carga;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author noone
 */
@WebServlet(name = "CargaControlador", urlPatterns = {"/CargaControlador"})
public class CargaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CargaBean cargaBean = new CargaBean();
        List<Carga> cargas = cargaBean.obtenerCargas();

        request.setAttribute("cargas", cargas);
        request.getRequestDispatcher("envio.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
