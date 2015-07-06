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
 * Servlet implementation class OrderHistory
 */
@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHistory() {
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
		String prodID =  (String) session.getAttribute("ProductID");
		Long userID = (Long) session.getAttribute("userid");
		Long orderID = (Long) session.getAttribute("OrderID");
		
		String tableInfo = "";
		
		List<User> Customers = getCustomerInfo(userID);
		List<LifeCorpProduct> products = getProductInfo(prodID);
		List<LifeCorpOrder> orders = getOrderInfo(orderID);
		
		try
		{
			Long Total = (products.get(0).getListPrice().longValue()) * (orders.get(0).getQuantity().longValue());
			
			System.out.println(Total);
			tableInfo += "<tr><th>Customer Name</th><th>Product Name</th><th>List Price</th><th>Quantity</th><th>Order ID</th><th>Total</th></tr>";
			
			tableInfo += "<tr><td>" + Customers.get(0).getFirstname() + " " + Customers.get(0).getLastname() + "</th><th>"
					+ products.get(0).getProduct() + "</th><th>"
					+ products.get(0).getListPrice() + "</th><th>"
					+ orders.get(0).getQuantity() + "</th><th>" 
					+ orders.get(0).getOrderId() + "</th><th>" 
					+ Total + "</td></tr>";
			
			request.setAttribute("tableInfo", tableInfo);
		}
		catch(Exception e)
		{
			request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
		}
		
		getServletContext()
		.getRequestDispatcher("/displayOrders.jsp")
		.forward(request, response);
		
	}

	
	protected static List<User> getCustomerInfo(Long userID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM User c where c.userid = :userID";
		TypedQuery<User> q = em.createQuery(qString, User.class);
		q.setParameter("userID", userID);
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
	
	
	protected static List<LifeCorpOrder> getOrderInfo(Long orderID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT o FROM  LifeCorpOrder o where o.orderId = :orderID ";
		TypedQuery<LifeCorpOrder> q = em.createQuery(qString, LifeCorpOrder.class);
		q.setParameter("orderID", orderID);
		List<LifeCorpOrder> i = null;
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
