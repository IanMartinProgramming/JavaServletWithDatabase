package ca.sheridancollege.controllers;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.sheridancollege.dao.DAO;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declare variables, get parameters, and construct a new instance of type DAO
		DAO dao = new DAO();
		String query = request.getParameter("query");
		String userInput = request.getParameter("userInput");
		String errorMessage = "Sorry, we don't have that information, try again";
		String result = null;
		String queryResult = null;

		// call DAO connection method
		dao.makeConnection();

		/*
		 * This if-else statement handles the radio buttons from input.jsp as well as
		 * the input text, in the first case, it will check to see if either field is
		 * null, if so then it will redirect to the error page. for every other case, it
		 * attempts to work with the parameters given, if the parameters are invalid
		 * then it will redirect the user to the error page. If they are valid, but no
		 * proper result is returned from the database, then the queryResult string will
		 * print a custom error message.
		 */
		if (query == null || userInput == null) {
			ServletContext sc = getServletContext();
			sc.getRequestDispatcher("/notFound.html").forward(request, response);

		} else if (query.equals("idName")) {
			// Try to parse Int from userInput, otherwise redirect to error page
			int id = 0;
			try {
				id = Integer.parseInt(userInput);
			} catch (Exception e) {
				ServletContext sc = getServletContext();
				sc.getRequestDispatcher("/notFound.html").forward(request, response);
			}
			// Pass in value and store returned string in 'result'
			result = dao.getNameByID(id);
			if (result == null) {
				queryResult = errorMessage;
			} else {
				queryResult = "Given search parameter: ID = " + id + ".<br>" + "The Rock Star's name is: " + result;
			}

		} else if (query.equals("idSalary")) {
			// try to parse int from userInput, otherwise redirect to error page
			int id = 0;
			try {
				id = Integer.parseInt(userInput);
			} catch (Exception e) {
				ServletContext sc = getServletContext();
				sc.getRequestDispatcher("/notFound.html").forward(request, response);
			}
			// Pass in value and cast returned double to type String and store in 'result'
			BigDecimal bd = new BigDecimal(dao.getSalaryByID(id));
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			result = bd.toString();
			if (result.equals("0.00")) {
				queryResult = errorMessage;
			} else {
				queryResult = "Given search parameter: ID = " + id + ".<br>" + "The Rock Star's salary is: $" + result;
			}

		} else if (query.equals("nameSalary")) {
			// Try to split user input using whitespace as delimiter and execute query,
			// otherwise redirect to error page
			String[] name = null;
			try {
				name = userInput.split(" ");
				BigDecimal bd = new BigDecimal(dao.getSalaryByName(name[0], name[1]));
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				result = bd.toString();
			} catch (Exception e) {
				ServletContext sc = getServletContext();
				sc.getRequestDispatcher("/notFound.html").forward(request, response);
			}

			// Check returned value and print results or error message
			if (result.equals("0.00")) {
				queryResult = errorMessage;
			} else {
				queryResult = "Given the name \"" + name[0] + " " + name[1] + "\".<br>" + "The Rock Star's salary is: $"
						+ result;
			}

		} else if (query.equals("nameId")) {
			// Try to split user input using whitespace as delimiter and execute query,
			// otherwise redirect to error page
			String[] name = null;
			try {
				name = userInput.split(" ");
				result = Integer.toString(dao.getIDByName(name[0], name[1]));
			} catch (Exception e) {
				ServletContext sc = getServletContext();
				sc.getRequestDispatcher("/notFound.html").forward(request, response);
			}

			// Check returned value and print results or error message
			if (result.equals("-1")) {
				queryResult = errorMessage;
			} else {
				queryResult = "Given the name \"" + name[0] + " " + name[1] + "\".<br>" + "The Rock Star's ID is: "
						+ result;
			}

		}

		// Pass results to output.jsp
		request.setAttribute("queryResult", queryResult);

		doGet(request, response);
	}

}
