
package com.miempresa.bean;

import com.miempresa.dao.UsuarioJpaController;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InicioSesionBean {
    private EntityManagerFactory emf;
    private UsuarioJpaController usuarioDao;
    
    public InicioSesionBean(){
        emf = Persistence.createEntityManagerFactory("GenDelivery");
        usuarioDao = new UsuarioJpaController(emf);
    }
    
    public void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        
        if(usuarioDao.isValidCredentials(correo, contrasena)){
            response.sendRedirect(request.getContextPath() + "/CamionControlador");
        } else {
            response.sendRedirect(request.getContextPath() + "/LoginControlador");
            
        }
        
        
    }
    
    
}
