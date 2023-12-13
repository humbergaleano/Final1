  
package Aut;

import java.util.logging.Logger;
import aut.Permisos;
import Models.PermisosModel;

public class PermisosService {
    private Permisos permisosData;
    public PermisosService(String userBD, String passDB, String hostDB, String portDB, String dataBase){
        permisosData = new Permisos(userBD, passDB, hostDB, portDB, dataBase);
    }
    
    public void RegistrarPermisos(PermisosModel permisos){
        validarDatos(permisos);
        permisosData.registrarPermisos(permisos);
    }
    
    private void validarDatos(PermisosModel permisos){
        try {
            if(permisos.usuario == null){
                new Exception("El modelo de permisos se encuentra vacìo");
            }
        } catch (Exception e) {
            return;
        }
    }
}
