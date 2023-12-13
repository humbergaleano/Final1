package auth;

import java.sql.SQLException;
import Conections.Conexiones;
import contactos.Personas;
import Models.UsuariosModel;
 

public class Authentication {
       private Conexiones conexion;
       private Personas persona ;

    public Authentication(String userBD, String passDB, String hostDB, String portDB, String dataBase) {
        this.conexion = new Conexiones(userBD, passDB, hostDB, portDB, dataBase);
        this.persona = new Personas(userBD, passDB, hostDB, portDB, dataBase);
    }
       
    public UsuariosModel Autenticar(String usuario){
        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            conexion.setResultadoQuery(conexion.getQuerySQL().executeQuery("Select * from usuarios where estado = 'Activo' and usuario = '" + usuario + "'"));
            if(conexion.getResultadoQuery().next()){
                return new UsuariosModel(
                        conexion.getResultadoQuery().getInt("idUsuario"), 
                        persona.consultarPersona(conexion.getResultadoQuery().getInt("idPersona")),
                        conexion.getResultadoQuery().getString("usuario"),
                        conexion.getResultadoQuery().getString("contraseña"),
                        conexion.getResultadoQuery().getString("estado")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

      public String registrarUsuario(UsuariosModel usuario){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("INSERT INTO usuarios(" +
                    "idPersona," +
                    "usuario," +
                    "contraseña, " +
                    "estado)" +
                    " values(" + usuario.getPersona().getIdPersona() + ", '" + usuario.getUsuario() + "', '" + usuario.getContraseña() + "', 'Activo')");
            conexion.conexionDB().close();
            return "El usuario " + usuario.getUsuario() + " fue registrado correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
