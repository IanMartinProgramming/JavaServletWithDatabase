package ca.sheridancollege.dao;

public class DAOTest {

	public static void main(String[] args) {
		String firstName = "Adam";
		String lastName = "Levine";
		int id = 5;
		
		DAO dao = new DAO();
		dao.makeConnection();
		String fullName = dao.getNameByID(id);
		String fullName2 = dao.getNameByID(10);
		int id2 = dao.getIDByName(firstName, lastName);
		System.out.println(fullName);
		System.out.println(fullName2);
		System.out.println(id2);
	}

}
