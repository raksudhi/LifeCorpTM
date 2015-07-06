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
 * Servlet implementation class ProductsforExisting
 */
@WebServlet("/ProductsforExisting")
public class ProductsforExisting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsforExisting() {
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
		
		String userEmailID = request.getParameter("emailid");
		String userPassword = request.getParameter("password");
		HttpSession session = request.getSession();
       List<LifeCorpProduct> products = getProductInfo();
       List<User> users = getCustomerInfo(userEmailID, userPassword);
       System.out.println(userEmailID);
       request.setAttribute("ProductList", products);
     //  session.setAttribute("ProductID", products.get(0).getProductId());
       session.setAttribute("userid", users.get(0).getUserid());
     //  request.setAttribute("customerList", custs);
       getServletContext().getRequestDispatcher("/displayProductsforExisting.jsp").forward(request, response);
	}

	
	protected static List<LifeCorpProduct> getProductInfo()
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM  LifeCorpProduct p";
		TypedQuery<LifeCorpProduct> q = em.createQuery(qString, LifeCorpProduct.class);
		//q.setParameter("prodID", Long.parseLong(prodID));
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
	
	
	protected static List<User> getCustomerInfo(String userEmailID, String userPassword)
	{
		System.out.println("HelloWorld");
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM User c where c.emailid = :userEmailID and c.password = :userPassword";
		TypedQuery<User> q = em.createQuery(qString, User.class);
		q.setParameter("userEmailID", userEmailID);
		q.setParameter("userPassword", userPassword);
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
}
