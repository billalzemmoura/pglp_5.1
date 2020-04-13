package pglp.pglp51;

public class DAOFactory <T>{
	public  static  DAO <Personnels> getPersonnelDAO() {
		return new personnelDAO();
	}
    public static  DAO <CompositePersonnels> getCompositeDao(){
    	
    	return new CompositeDao();
    } 


}
