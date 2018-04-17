package tema3;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class AFD {
	private Vector<String> Q; // multimea starilor Q
	private String alfabet; //
	private Vector<String> stariFinale;
	private String stareInitiala;
	Vector<Tranzitii> lista; // tranzitii

	public AFD() {
		Q = new Vector<>();
		alfabet = null;
		stariFinale = new Vector<>();
		stareInitiala = null;
		lista = new Vector<>();
	}

	public AFD(Vector<String> Q, String alfabet, Vector<String> stariFinale, String stareInitiala) {
		this.Q = new Vector<>();
		this.alfabet = alfabet;
		this.stariFinale = new Vector<>();
		this.stareInitiala = stareInitiala;
		this.lista = null;
	}

	public Vector<String> getMultimeFinita() {
		return Q;
	}

	public void setMultimeFinita(Vector<String> multimeFinita) {
		this.Q = multimeFinita;
	}

	public String getAlfabet() {
		return alfabet;
	}

	public void setAlfabet(String alfabet) {
		this.alfabet = alfabet;
	}

	public Vector<String> getStariFinale() {
		return stariFinale;
	}

	public void setStariFinale(Vector<String> stariFinale) {
		this.stariFinale = stariFinale;
	}

	public String getStareInitiala() {
		return stareInitiala;
	}

	public void setStareInitiala(String stareInitiala) {
		this.stareInitiala = stareInitiala;
	}

	public void citire() {
		lista = new Vector<Tranzitii>();
		try {
			File myFile = new File("src/fis1.txt");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(myFile);
			String linie = scanner.nextLine();
			String[] splitLinie = linie.split(",");
			for (int i = 0; i < splitLinie.length; i++) {
				Q.add(splitLinie[i]);
			}
			alfabet = scanner.nextLine();
			stareInitiala = scanner.nextLine();
			linie = scanner.nextLine();
			splitLinie = linie.split(",");
			for (int i = 0; i < splitLinie.length; i++)
				stariFinale.add(splitLinie[i]);
			myFile = new File("src/delta.txt");
			scanner = new Scanner(myFile);
			while (scanner.hasNextLine()) {
				String line;
				line = scanner.nextLine();
				String[] sir = line.split(",");
				Tranzitii aux = new Tranzitii(sir[0], sir[1], sir[2]);
				lista.add(aux);
			}
		} catch (Exception ex) {
			System.out.println(" Eroare ! ");
			Q = null;
			alfabet = null;
			stariFinale = null;
			stareInitiala = null;
		}

	}

	public void afisare() {
		System.out.println(" Q : " + " Sigma : " + alfabet + " Stare finala "
				+ " stare initiala " + stareInitiala);
		for(String state: Q)
			System.out.println(state);
		for(String state: stariFinale)
			System.out.println(state);
		for (Tranzitii aux : lista) {
			aux.afisez();
		}
	}

	public Tranzitii selectareTranzitie(String aux, String sir) {
		Vector<Tranzitii> tranzitiiAplicabile = new Vector<>();

		for (Tranzitii index : lista) {
			if (aux.contains(index.getStanga()))
				tranzitiiAplicabile.add(index);
		}

		if (tranzitiiAplicabile.size() > 0) {
			for (Tranzitii curent : tranzitiiAplicabile) {
				if (aux.matches(curent.getStanga()) && (curent.getSimbol().matches(sir))) {
					return curent;
				}
			}
			for (Tranzitii curent : tranzitiiAplicabile) {
				if (!(curent.getSimbol().matches(sir.charAt(0) + "")) && aux.matches(curent.getStanga())) {
					System.out.println(" s-a identificat un blocaj ");
					return null;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	public void start() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String start = stareInitiala;
		System.out.println(" insert the word ");
		String sir = scanner.nextLine();
		if (sir.matches("lambda") == true && eCuvantAcceptat(start)) {
			System.out.println(" Automatul accepta lambda ");
		} else {
			boolean ok = false;
			if (verificCuvantIntrodus(sir)) {
				while (ok == false && sir.matches("") == false) {
					Tranzitii curenta = selectareTranzitie(start, sir.charAt(0) + "");
					if (curenta == null) {
						//System.out.println(" am ajuns la final ");
						ok = true;
					} else {
						curenta.afisez();
						start = start.replaceFirst(start, curenta.getDreapta());
						sir = sir.replaceFirst(sir.charAt(0) + "", "");
					}
				}
			} else {
				System.out.println(" s-a depistat o eroare ");
			}
			if (eCuvantAcceptat(start) && sir.matches(""))
				System.out.println(" Automatul accepta cuvantul ");
			else
				System.out.println(" Automatul nu accepta cuvantul ");
		}
	}

	public boolean eCuvantAcceptat(String aux) {
		if (stariFinale.indexOf(aux) != -1)
			return true;
		else
			return false;
	}

	public boolean validareAFD() {
		if (Q.indexOf(stareInitiala) == -1)
			return false;
		for (String stare : stariFinale)
			if (Q.indexOf(stare) == -1)
				return false;
		for (Tranzitii tranzite : lista) {
			if (Q.indexOf(tranzite.getStanga()) == -1)
				return false;
			if (Q.indexOf(tranzite.getDreapta()) == -1)
				return false;
			if (alfabet.indexOf(tranzite.getSimbol()) == -1)
				return false;
		}
		return true;
	}

	public boolean verificCuvantIntrodus(String aux) {
		if (aux.matches("lambda")) {
			return true;
		}
		for (int i = 0; i < aux.length(); i++) {
			if (alfabet.indexOf(aux.charAt(i)) == -1)
				return false;
		}
		return true;
	}
}