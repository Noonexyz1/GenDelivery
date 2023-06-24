package com.miempresa.bean;

import com.miempresa.entidades.Camion;
import com.miempresa.entidades.Carga;
import com.miempresa.entidades.Electrodomestico;
import com.miempresa.entidades.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManejadorBean {

    private String atributoName = "";
    private String jspPath = "";
    private Object objetoEnvio = new Object();

    private CamionBean camionBean;
    private CargaBean cargaBean;
    private ElectrodomesticoBean electrodomesticoBean;
    private UsuarioBean usuarioBean;

    public ManejadorBean(CamionBean injeccion) {
        camionBean = injeccion;
    }

    public ManejadorBean(CargaBean injeccion) {
        cargaBean = injeccion;
    }

    public ManejadorBean(ElectrodomesticoBean injeccion) {
        electrodomesticoBean = injeccion;
    }

    public ManejadorBean(UsuarioBean injeccion) {
        usuarioBean = injeccion;
    }

    public void evaluarParametro(HttpServletRequest request, HttpServletResponse response) {

        if (request.getRequestURI().equals("/GenDelivery/CamionControlador")) {

            if (request.getParameter("action") == null) {
                atributoName = "camiones";
                jspPath = "camion.jsp";
                objetoEnvio = camionBean.obtenerCamiones();
            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "camion";
                    jspPath = "frmcamion.jsp";
                    objetoEnvio = new Camion();
                    
                } else if (request.getParameter("action").equals("edit")) {
                    atributoName = "camion";
                    jspPath = "frmcamion.jsp";
                    objetoEnvio = camionBean.traerCamion(Integer.parseInt(request.getParameter("id")));
                } else if (request.getParameter("action").equals("delete")) {
                    atributoName = "camiones";
                    jspPath = "camion.jsp";
                    camionBean.eliminarCamion(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = camionBean.obtenerCamiones();
                }
            }

        } else if (request.getRequestURI().equals("/GenDelivery/CargaControlador")) {
            if (request.getParameter("action") == null) {
                atributoName = "cargas";
                jspPath = "envio.jsp";
                objetoEnvio = cargaBean.obtenerCargas();

            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "carga";
                    jspPath = "frmcarga.jsp";
                    objetoEnvio = new Carga();
                } else if (request.getParameter("action").equals("edit")) {
                    atributoName = "carga";
                    jspPath = "frmcarga.jsp";
                    objetoEnvio = cargaBean.traerCarga(Integer.parseInt(request.getParameter("id")));
                } else if (request.getParameter("action").equals("delete")) {
                    atributoName = "cargas";
                    jspPath = "envio.jsp";
                    cargaBean.eliminarCarga(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = cargaBean.obtenerCargas();
                }
            }

        } else if (request.getRequestURI().equals("/GenDelivery/ElectrodomesticoControlador")) {
            if (request.getParameter("action") == null) {
                atributoName = "electrodomesticos";
                jspPath = "electrodomestico.jsp";
                objetoEnvio = electrodomesticoBean.obtenerElectrodomesticos();

            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "electrodomestico";
                    jspPath = "frmelectrodomestico.jsp";
                    objetoEnvio = new Electrodomestico();
                } else if (request.getParameter("action").equals("edit")) {
                    atributoName = "electrodomestico";
                    jspPath = "frmelectrodomestico.jsp";
                    objetoEnvio = electrodomesticoBean.traerElectrodomestico(Integer.parseInt(request.getParameter("id")));
                } else if (request.getParameter("action").equals("delete")) {
                    atributoName = "electrodomesticos";
                    jspPath = "electrodomestico.jsp";
                    electrodomesticoBean.eliminarElectrodomestico(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = electrodomesticoBean.obtenerElectrodomesticos();
                }
            }

        } else if (request.getRequestURI().equals("/GenDelivery/UsuarioControlador")) {
            if (request.getParameter("action") == null) {
                atributoName = "usuarios";
                jspPath = "usuario.jsp";
                objetoEnvio = usuarioBean.obtenerUsuarios();

            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "usuario";
                    jspPath = "frmusuario.jsp"; 
                    objetoEnvio = new Usuario();
                } else if (request.getParameter("action").equals("edit")) {
                    atributoName = "usuario";
                    jspPath = "frmusuario.jsp";
                    objetoEnvio = usuarioBean.traerUsuario(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = usuarioBean.traerUsuario(Integer.parseInt(request.getParameter("id")));
                } else if (request.getParameter("action").equals("delete")) {
                    atributoName = "usuarios";
                    jspPath = "usuario.jsp";
                    usuarioBean.eliminarUsuario(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = usuarioBean.obtenerUsuarios();
                }
            }

        }

    }

    
    
    public String getAtributoName() {
        return atributoName;
    }

    public void setAtributoName(String atributoName) {
        this.atributoName = atributoName;
    }

    public String getJspPath() {
        return jspPath;
    }

    public void setJspPath(String jspPath) {
        this.jspPath = jspPath;
    }

    public Object getObjetoEnvio() {
        return objetoEnvio;
    }

    public void setObjetoEnvio(Object objetoEnvio) {
        this.objetoEnvio = objetoEnvio;
    }
    

}
