/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.miempresa.controlador;

import com.miempresa.bean.ElectrodomesticoBean;
import com.miempresa.entidades.Electrodomestico;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ElectrodomesticoControlador", urlPatterns = {"/ElectrodomesticoControlador"})
public class ElectrodomesticoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ElectrodomesticoBean electrodomesticoBean = new ElectrodomesticoBean();
        List<Electrodomestico> electrodomesticos = electrodomesticoBean.obtenerElectrodomesticos();

        request.setAttribute("electrodomesticos", electrodomesticos);
        request.getRequestDispatcher("electrodomestico.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
