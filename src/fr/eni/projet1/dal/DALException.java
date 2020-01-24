package fr.eni.projet1.dal;

//Classe permettant de gérer toute les exceptions levées et propagées par les différentes DAO.
 
public class DALException extends Exception
{
	private static final long serialVersionUID = 1031847059109199548L;

	// Constructeurs
	public DALException()
	{
		super();
	}
	
	public DALException(String message)
	{
		super(message);
	}
	
	public DALException(String message, Throwable exception)
	{
		super(message, exception);
	}
	
	
	@Override
	/**
	 * 
	 * @return Le message d'erreur
	 */
	public String getMessage()
	{
		StringBuffer sb = new StringBuffer("Couche DAO - ");
		sb.append(super.getMessage());
		
		return sb.toString();
	}
}

