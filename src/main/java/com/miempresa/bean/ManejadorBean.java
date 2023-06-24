
package com.miempresa.bean;

import javax.servlet.http.HttpServletRequest;


public class ManejadorBean {
    
    
    private String elemntoJsp;
    private String atributo;
    
    
    public  ManejadorBean(){
        
    }
    
    
    
    
    
    
    public void evaluador(HttpServletRequest request) {

        if (request.getParameter("action").equals("new")) {
            elemntoJsp = "producto.jsp";
            atributo = "producto";
            producto = new Producto();

        } else if (request.getParameter("action").equals("edit")) {
            elemntoJsp = "producto.jsp";
            atributo = "producto";
            try {
                producto = dao.getById(Integer.parseInt(request.getParameter("id")));
            } catch (Exception ex) {
                System.out.println("ERROR en evaluador()" + ex.getMessage());
                ex.getStackTrace();
            }

        } else if (request.getParameter("action").equals("delete")) {
            elemntoJsp = "/Inicio2";

            /*Al momento de enviar esta cadena, lo enviara al index y no al formulario, si que pondre otro valor ya que 
            no me permite que el valor sea nulo*/
            atributo = "valor_para_hacer_dismatch_o_engañar_al_setAtribute('sdf', 'asdf'";

            try {
                dao.delete(Integer.parseInt(request.getParameter("id")));
            } catch (Exception ex) {
                System.out.println("ERROR en evaluador()" + ex.getMessage());
                ex.getStackTrace();
            }

        }

    }
    
}
