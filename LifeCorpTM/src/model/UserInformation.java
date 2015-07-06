package model;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		String prodID = (String) session.getAttribute("ProductID");
		List<LifeCorpProduct>productinfo = getProductInfo(prodID);
		
		
		
		String UserFirstName = request.getParameter("firstname");
	//	System.out.println(UserFirstName);
		String UserLastName = request.getParameter("lastname");
		String UserEmailID = request.getParameter("emailid");
		String UserPassword = request.getParameter("password");
		
		//List<User> Customers = getCustomerInfo();
		
	//	System.out.println(Customers.get(0).getUserid());
		
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
			
			request.setAttribute("UserInfo", cust);
			request.setAttribute("ProductInfo", productinfo);
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
		.getRequestDispatcher("/getQuantity.jsp")
		.forward(request, response);
		
		//getServletContext()
		//.getRequestDispatcher("/DispatchUserID")
		//.forward(request, response);

	}
	
	
	
	protected static List<LifeCorpProduct> getProductInfo(String prodID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM  LifeCorpProduct p where p.productId = :prodID ";
		TypedQuery<LifeCorpProduct> q = em.createQuery(qString, LifeCorpProduct.class);
		q.setParameter("prodID", Long.parseLong(prodID));
		List<LifeCorpProduct> i = null;
		try
		{
		
			i = q.getResultList();
			if(i == null || i.isEmpty())
			{
				i = null;
			}
		}
		catch(NoResultException e)
		{
			System.out.println(e);
		}
		
		finally 
		{
			em.close();
		}
		
		return i;
	}
	
	
	
	
}
