
package com.miempresa.controlador;

import com.miempresa.bean.CamionBean;
import com.miempresa.bean.ManejadorBean;
import com.miempresa.bean.ReporteBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ReporteControlador", urlPatterns = {"/ReporteControlador"})
public class ReporteControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        ManejadorBean manejadorBean = new ManejadorBean(new ReporteBean());
        manejadorBean.evaluarParametro(request, response);
        
        String atributoName = manejadorBean.getAtributoName();
        String jspPath = manejadorBean.getJspPath();
        Object objetoEnvio = manejadorBean.getObjetoEnvio();
        
        request.setAttribute(atributoName, objetoEnvio);
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManejadorBean manejadorBean = new ManejadorBean(new CamionBean());
        manejadorBean.evaluarAccion(request, response);
        
    }

}
