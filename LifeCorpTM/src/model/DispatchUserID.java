package model;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DispatchUserID
 */
@WebServlet("/DispatchUserID")
public class DispatchUserID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchUserID() {
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
	//	String tableinfo = "";
		
		HttpSession session = request.getSession();
		String prodID = (String) session.getAttribute("ProductID");
		
		List<User> Customers = getCustomerInfo();
		List<LifeCorpProduct>productinfo = getProductInfo(prodID);
		
		try
		{			
	
			request.setAttribute("UserInfo", Customers);
			request.setAttribute("ProductInfo", productinfo);
			session.setAttribute("userid", Customers.get(0).getUserid());
		}
		catch(Exception e)
		{
			request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
		}
		
		getServletContext()
		.getRequestDispatcher("/getQuantity.jsp")
		.forward(request, response);
		
	}
	
	
	protected static List<User> getCustomerInfo()
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM User c";
		TypedQuery<User> q = em.createQuery(qString, User.class);
		
		List<User> i = null;
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
