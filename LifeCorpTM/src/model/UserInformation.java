package model;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserInformation
 */
@WebServlet("/UserInformation")
public class UserInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String UserFirstName = request.getParameter("firstname");
		System.out.println(UserFirstName);
		String UserLastName = request.getParameter("lastname");
		System.out.println(UserLastName);
		String UserEmailID = request.getParameter("emailid");
		String UserPassword = request.getParameter("password");
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		

		try
		{
			
			model.User cust = new model.User();
			
			cust.setFirstname(UserFirstName);
			cust.setLastname(UserLastName);
			cust.setEmailid(UserEmailID);
			cust.setPassword(UserPassword);
			
			em.persist(cust);
			//em.persist(order);
			
			trans.commit();
		}
		catch(Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
		
		getServletContext()
		.getRequestDispatcher("/DispatchUserID")
		.forward(request, response);

	}

}
