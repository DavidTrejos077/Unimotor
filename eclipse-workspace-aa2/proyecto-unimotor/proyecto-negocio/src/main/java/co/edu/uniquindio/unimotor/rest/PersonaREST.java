package co.edu.uniquindio.unimotor.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Persona;



@Path("/personas")
public class PersonaREST {

	@EJB
	private UnimotorEJB unimotorEJB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")

	public Response obtenerTodaLasPersonas() {

		return Response.status(200).entity(unimotorEJB.obtenerListaPersonas()).build();
	}

	@GET

	@Produces(MediaType.APPLICATION_JSON)

	@Path("/{id}")

	public Response obtenerPersona(@PathParam("id") Integer id) throws Exception {
		try {
			Persona persona = unimotorEJB.obtenerPersona(id);

			return Response.status(200).entity(persona).build();
		} catch (Exception e) {
			return Response.status(500).entity("\"mensaje\" : \"" + e.getMessage() + "\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	@POST 

	@Consumes(MediaType.APPLICATION_JSON) 

	@Produces(MediaType.APPLICATION_JSON)

	@Path("/")

	public Response RegistrarPersona(Persona persona) {
		try {

			unimotorEJB.registrarPersona(persona);
			return Response.status(200).entity("{\"mensaje\" :\" La persona se registr� correctamente\"}")
					.type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {

			return Response.status(500).entity("\"mensaje\" : \"" + e.getMessage() + "\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@PUT 

	@Consumes(MediaType.APPLICATION_JSON) 

	@Produces(MediaType.APPLICATION_JSON)

	@Path("/") 

	public Response ActualizarPersona(Persona persona) {
		try {
			unimotorEJB.actualizarPersona(persona);
			return Response.status(200).entity("{\"mensaje\" :\" La persona se actualiz� correctamente\"}")
					.type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {

			return Response.status(500).entity("\"mensaje\" : \"" + e.getMessage() + "\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@DELETE 

	@Produces(MediaType.APPLICATION_JSON)

	@Path("/{id}") 

	public Response EliminarPersona(@PathParam("id") Integer id) {
		try {
			unimotorEJB.eliminarPersona(id);
			return Response.status(200).entity("{\"mensaje\" :\" La persona se elimin� correctamente\"}")
					.type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {

			return Response.status(500).entity("\"mensaje\" : \"" + e.getMessage() + "\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

}
