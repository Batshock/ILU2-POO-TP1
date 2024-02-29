package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche{
		private Etal[] etals;
		private int nbEtalMax;
		private Marche(int nbEtal) {
			this.etals = new Etal[nbEtal];
			this.nbEtalMax = nbEtal;
		}
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		private int trouverEtalLibre() {
			int i = 0;
			while(etals[i].isEtalOccupe() && i<nbEtalMax) {
				i += 1;
			}
			if(i==nbEtalMax) {
				return -1;
			}
			return i;
		}
		private Etal[] trouverEtals(String produit) {
			int i = 0;
			Etal[] etalsRecherches = new Etal[nbEtalMax];
			int j = 0;
			while(i<nbEtalMax) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalsRecherches[j] = etals[i];
					j += 1;
				}
				i += 1;
			}
			return etalsRecherches;
		}
		private Etal trouverVendeur(Gaulois gaulois) {
			int i = 0;
			while((i<nbEtalMax) && (etals[i].getVendeur()==gaulois)) {
				i += 1;
			}
			if(i<nbEtalMax) {
				return etals[i];
			}
			return null
		}
	}
}