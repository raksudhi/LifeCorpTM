package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class CreateOrders
 */
@WebServlet("/CreateOrders")
public class CreateOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrders() {
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
		//System.out.println("Helloworld");
		HttpSession session = request.getSession();
		String prodID =  (String) session.getAttribute("ProductID");
		Long userID = (Long) session.getAttribute("userid");
		String quantity = (String) session.getAttribute("Quantity");
		BigDecimal bd = new BigDecimal(quantity);
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		try
		{
			User cust = em.find(User.class, userID);
			LifeCorpProduct prod = em.find(LifeCorpProduct.class, Long.parseLong(prodID));
			LifeCorpOrder ord = new LifeCorpOrder();
			Date date = new Date();
			
			
			ord.setUser(cust);
			ord.setLifeCorpProduct(prod);
			ord.setOrderDate(date);
			ord.setQuantity(bd);
			
			em.persist(ord);
			em.flush();
			//em.persist(order);
			//System.out.println(ord.getOrderId());
			trans.commit();
			
			request.setAttribute("UserInfo", cust);
			request.setAttribute("ProductInfo", prod);
			session.setAttribute("OrderID", ord.getOrderId());
			
		//	System.out.println(ord.getOrderId());
		//	session.setAttribute("orderid", ord.getOrderId());
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
		.getRequestDispatcher("/DisplayOrders")
		.forward(request, response);
	}
	
}
