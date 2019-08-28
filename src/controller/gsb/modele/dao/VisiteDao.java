package controller.gsb.modele.dao;

import controller.gsb.modele.Medecin;
import controller.gsb.modele.Visite;
import controller.gsb.modele.Visiteur;

import java.sql.ResultSet;
import java.util.ArrayList;

public class VisiteDao {
	/*
	 * Fonction pour trouver une visite dans la base de donn�es
	 */
	public static Visite rechercher(String referenceVisite) {
		Visite uneVisite = null;
		Medecin unMedecin = null;
		Visiteur unVisiteur = null;
		ResultSet reqSelection = ConnexionMySql
				.execReqSelection("select * from VISITE where REFERENCE='" + referenceVisite + "'");//Requette de selection d'une visite
		try {
			//Fonction de cr�ation de l'objet visite depuis les infos de la base de donn�es
			//String reference, Date date, String commentaire, Medecin unMedecin, Visiteur unVisiteur
			//(`REFERENCE`, `DATEVISITE`, `COMMENTAIRE`, `MATRICULE`, `CODEMED`)
			if (reqSelection.next()) {
				unMedecin = MedecinDao.Rechercher(reqSelection.getString(4));
				unVisiteur = VisiteurDao.rechercher(reqSelection.getString(5));
				uneVisite = new Visite(reqSelection.getString(1), reqSelection.getDate(2),
						reqSelection.getString(3), unMedecin, unVisiteur);
			}
			;
		} catch (Exception e) {
			System.out.println("erreur reqSelection.next() pour la requ�te - select * from VISITE where REFERENCE='"
					+ referenceVisite + "'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return uneVisite;
	}
	/* 
	 * Fonction de suppresion d'une viste
	 */
	public static int SupprimerVisite(Visite visite){
		
		int result = 0;
		//Requette de suppression de la visite dans la base de donn�es
		String requette = "DELETE FROM VISITE WHERE REFERENCE='" + visite.getReference() + "';";
		result = ConnexionMySql.execReqMaj(requette);
		ConnexionMySql.fermerConnexionBd();
		return result;		
	}
	/*
	 * Fonction d'ajout d'une visite
	 */
	public static int AjouterVisite(Visite visite){
		
		int result = 0;
		//Requette d'insertion de la visite dans la base de donn�es
		String requette = "INSERT INTO `VISITE` (`REFERENCE`, `DATEVISITE`, `COMMENTAIRE`, `MATRICULE`, `CODEMED`) VALUES ('" + visite.getReference() + "', '" + visite.getDate().toString() + "', '" + visite.getCommentaire() + "', '" + visite.getUnVisiteur().getMatricule() + "', '" + visite.getUnMedecin().getCodeMed() + "')";
		result = ConnexionMySql.execReqMaj(requette);
		ConnexionMySql.fermerConnexionBd();
		return result;
	}

	public static boolean VisiteExist(String ref){
		
		boolean result = false;;
		String requette = "SELECT COUNT(*) FROM VISITE WHERE `REFERENCE`= '" + ref + "';";
		ResultSet reqSelection = ConnexionMySql.execReqSelection(requette);
		try {
			if(reqSelection.next()) {
				if(reqSelection.getInt(1) > 0){
					
					result = true;
					
				}
			}
			;
		} catch (Exception e) {
			System.out.println("erreur reqSelection.next() pour la requ�te - " + requette);
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();			
		
		
		return result;
		
	}
	
	public static int ModifierVisite(Visite visite){
		
		int result = 0;
		String requette = "UPDATE VISITE SET `DATEVISITE` = '"+ visite.getDate() +"', `COMMENTAIRE` = '"+ visite.getCommentaire() +"', `MATRICULE`= '"+ visite.getUnVisiteur().getMatricule() +"', `CODEMED` = '" + visite.getUnMedecin().getCodeMed() + "' WHERE REFERENCE='" + visite.getReference() + "';";
		result = ConnexionMySql.execReqMaj(requette);
		ConnexionMySql.fermerConnexionBd();
		return result;		
	}

	public static ArrayList<Visite> rechercheVisiteParCodeMed(String codeMed) {
		ArrayList<Visite> list = new ArrayList<>();

		Visite uneVisite = null;
		Medecin unMedecin = null;
		Visiteur unVisiteur = null;

		ResultSet reqSelection = ConnexionMySql
				.execReqSelection("select * from VISITE where CODEMED ='"+codeMed+"';");

		try {
			while(reqSelection.next()) {
				unMedecin = MedecinDao.Rechercher(reqSelection.getString(5));
				unVisiteur = VisiteurDao.rechercher(reqSelection.getString(4));
				list.add(new Visite(reqSelection.getString(1), reqSelection.getDate(2),
						reqSelection.getString(3), unMedecin, unVisiteur));
			}
			;
		} catch (Exception e) {
			System.out.println("erreur reqSelection.next() pour la requ�te ");
			e.printStackTrace();
		}

		return list;
	}
	

	public static ArrayList<Visite> rechercherTouteVisite() {
		ArrayList<Visite> list = new ArrayList<>();
		Visite uneVisite = null;
		Medecin unMedecin = null;
		Visiteur unVisiteur = null;
		ResultSet reqSelection = ConnexionMySql
				.execReqSelection("select * from VISITE");
		try {
			while(reqSelection.next()) {
				unMedecin = MedecinDao.Rechercher(reqSelection.getString(5));
				unVisiteur = VisiteurDao.rechercher(reqSelection.getString(4));
				list.add(new Visite(reqSelection.getString(1), reqSelection.getDate(2),
						reqSelection.getString(3), unMedecin, unVisiteur));
			}
			;
		} catch (Exception e) {
			System.out.println("erreur reqSelection.next() pour la requ�te ");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return list;
	}
}
