package co.edu.uniquindio.unimotor.beans;



import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;



/**
 * Clase para hacer reset del password.
 *
 */
@WebServlet("/reset_password")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String host;
	private String port;
	private String email;
	private String name;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		email = context.getInitParameter("email");
		name = context.getInitParameter("name");
		pass = context.getInitParameter("pass");
	}

	public ResetPasswordServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "reset_password.jsp";
		request.getRequestDispatcher(page).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recipient = request.getParameter("email");
		String subject = "Your Password has been reset";

		
		String newPassword = resetCustomerPassword(recipient);

		String content = "Hi, this is your new password: " + newPassword;
		content += "\nNote: for security reason, "
				+ "you must change your password after logging in.";

		String message = "";

		try {
			System.out.println("email="+email);
			System.out.println("name"+name);
			System.out.println("pass"+pass);
			EmailUtility.sendEmail(host, port, email, name, pass,
					recipient, subject, content);
			message = "Tu contraseña ha sido cambiada.Por favor revisa tu email.";
		} catch (Exception ex) {
			ex.printStackTrace();
			message = "There were an error: " + ex.getMessage();
		} finally {
			request.setAttribute("message", message);
			request.getRequestDispatcher("indexRecuperarPassword.xhtml").forward(request, response);
		}
	}

	/**
	 * Método para generar un string aleatorio dado un email.
	 */

	
	public String resetCustomerPassword(String email) {
	   
	     
	    String randomPassword = RandomStringUtils.randomAlphanumeric(10);
	      
	    return randomPassword;
	}
	
}

