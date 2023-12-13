
package Main;

import Models.PersonaModels;
import Contactos.PersonaService;


public class Main {
    public static void main(String[] args) {
        PersonaService personaService = new PersonaService("postgres", "12345", "localhos", "15432", "postgres");
        PersonaModels persona = new PersonaModels();
        persona.Nombre = "";
        persona.Apellido = "";
        personaService.registrarPersona(persona);
    }
}