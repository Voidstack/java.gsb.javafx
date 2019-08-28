/*
 * Créé le 23 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package controller.gsb.service;

import controller.gsb.modele.Medecin;
import controller.gsb.modele.dao.MedecinDao;
import controller.gsb.tests.ConnexionTest;

import java.util.ArrayList;

/**
 * @author Isabelle 23 févr. 2015 TODO Pour changer le modèle de ce commentaire
 *         de type généré, allez à : Fenêtre - Préférences - Java - Style de
 *         code - Modèles de code
 */
public class MedecinService {

	public static Medecin rechercherMedecin(String unCodeMedecin) throws Exception {

		Medecin unMedecin;
			if (unCodeMedecin == null) {
				throw new Exception("Donnée obligatoire : code");
			}
			if(!MedecinDao.MedecinExist(unCodeMedecin)){
				
				throw new Exception("Medecin inexistant");
				
			}
			
			unMedecin = MedecinDao.Rechercher(unCodeMedecin);
		
		return unMedecin;
	}

	public static ArrayList<Medecin> RechercherToutMedecins() throws Exception{
		
		
		return MedecinDao.retournerCollectionDesMedecins();
		
	}
	
}
