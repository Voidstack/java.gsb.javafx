/*
 * Cr�� le 22 f�vr. 2015
 *
 * TODO Pour changer le mod�le de ce fichier g�n�r�, allez � :
 * Fen�tre - Pr�f�rences - Java - Style de code - Mod�les de code
 */
package controller.gsb.modele.dao;

import controller.gsb.modele.Localite;

import java.sql.ResultSet;

/**
 * @author Isabelle 22 f�vr. 2015 TODO Pour changer le mod�le de ce commentaire
 *         de type g�n�r�, allez � : Fen�tre - Pr�f�rences - Java - Style de
 *         code - Mod�les de code
 */
public class LocaliteDao {

	public static Localite rechercher(String codeLocalite) {
		Localite uneLocalite = null;
		ResultSet reqSelection = ConnexionMySql
				.execReqSelection("select * from LOCALITE where CODEPOSTAL='" + codeLocalite + "'");
		try {
			if (reqSelection.next()) {
				uneLocalite = new Localite(reqSelection.getString(1), reqSelection.getString(2));
			}
			;
		} catch (Exception e) {
			System.out.println("erreur reqSelection.next() pour la requ�te - select * from LOCALITE where CODEPOSTAL='"
					+ codeLocalite + "'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return uneLocalite;
	}

	public static int AjouterLocalite(Localite loc){
			
			int result = 0;
			String requette = "INSERT INTO `LOCALITE` (`CODEPOSTAL`, `VILLE`) VALUES ('" + loc.getCodePostal() + "','" + loc.getVille() + "');";
			result = ConnexionMySql.execReqMaj(requette);
			ConnexionMySql.fermerConnexionBd();
			return result;
			
	
	}
	
	public static int SupprimerLocalite(Localite loc){
		
		int result = 0;
		String requette = "DELETE FROM LOCALITE WHERE CODEPOSTAL='" + loc.getCodePostal() + "';";
		result = ConnexionMySql.execReqMaj(requette);
		ConnexionMySql.fermerConnexionBd();
		return result;		
	}
	
}
