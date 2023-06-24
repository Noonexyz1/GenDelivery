
package com.miempresa.controlador;

import com.miempresa.bean.CamionBean;
import com.miempresa.bean.ManejadorBean;
import com.miempresa.entidades.Camion;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CamionControlador", urlPatterns = {"/CamionControlador"})
public class CamionControlador extends HttpServlet {

    
    private ManejadorBean manejadorBean;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        manejadorBean = new ManejadorBean();
        
        manejadorBeans
        
        CamionBean camionBean = new CamionBean();
        List<Camion> camiones = camionBean.obtenerCamiones();
        
        request.setAttribute("camiones", camiones);
        request.getRequestDispatcher("camion.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
