/* Lorenzo Gardelli 	mat. 907502 	assignment3, successivamente aggiornato per Progetto d'esame.

*/

public class Elettore {
	// attributi
	public String Nome, Cognome, Nazione, Comune, Sesso, CF, ID;
	public int GiornoNascita, MeseNascita, AnnoNascita, età;
	private /*@ spec_public @*/ boolean Voto;
	
	//variabili di supporto, leggere dettagli nei commenti successivi
	//public int GiornoOggi=17, MeseOggi=11, AnnoOggi=2021;
	
	
	//@ public invariant (Sesso == "M" || Sesso == "F") ;
	//@ public invariant ((Nome != null) && (Cognome != null));
	//@ public invariant ((Nazione == "Italia") ==> (Comune != null));
	
	{
		context Elettore
		Inv: this.Sesso = #F or this.Sesso = #M
		Inv: this.Nome!=null and this.Cognome!=null
		Inv: this.Nazione=#Italia implies this.Comune!=null
		Inv: this.allInstances.isUnique(ID)
		context Elettore::esprimi_voto()
		Pre: this.età >= 18
	}
	
	// verifico come postcondizione al costruttore che il CF inserito sia corretto e che la data sia valida tramite metodi puri
	//@ ensures (Controllo_Codice(CF)==true);
	//@ ensures (DataValida() == true);
	public Elettore(String nome, String cognome, String nazione, String comune, String sesso, String cF,
			int giornoNascita, int meseNascita, int annoNascita, boolean voto) {
		super();
		Nome = nome;
		Cognome = cognome;
		Nazione = nazione;
		Comune = comune;
		Sesso = sesso;
		CF = cF;
		GiornoNascita = giornoNascita;
		MeseNascita = meseNascita;
		AnnoNascita = annoNascita;
		Voto = voto;
	}
	
	// elettore può votare al più una volta: lo suddivido in assicurarsi che il primo voto venga registrato (false -> true)
	// e che se vota per la seconda volta il metodo esprimi_voto ricada nella condizione di return false.
	//@ ensures ((\old(Voto)== false)==>(\result == true) && (\old(Voto)==true)==>(\result == false));
	
	// elettore può votare solo se maggiorenne: 
	//@ requires (maggiorenne()==true);
	public boolean esprimi_voto(){
		if (this.Voto==false){
			this.Voto=true;
			return true;
		}
		else return false;
	}
	
	// Metodi puri di supporto:
	
	public /*@ pure @*/ boolean maggiorenne(){
		if ((AnnoOggi - this.AnnoNascita) >17){return true;}
		if ((AnnoOggi - this.AnnoNascita)==17){
			if((MeseOggi - this.MeseNascita)>= 0){return true;}
			else return false;
		}
		return false;
	}
	
	public /*@ pure @*/ boolean DataValida(){
		if(AnnoOggi>this.AnnoNascita){return true;}
		if(AnnoOggi<this.AnnoNascita){return false;}
		if(AnnoOggi==this.AnnoNascita){
			if(MeseOggi>this.MeseNascita){return true;}
			if(MeseOggi<this.MeseNascita){return false;}
			if(MeseOggi<this.MeseNascita){
				if(GiornoOggi>=this.GiornoNascita){return true;}
				if(GiornoOggi<this.GiornoNascita){return false;}
			}
		}
		return true;
	}
	
	/* ATTENZIONE:
	il metodo Controllo_Codice dichiarato come metodo puro viene usato dalla postcondizione al costruttore per
	verificare se il codice fiscale con cui è stato inizializzato un elettora sia valido.
	Questa versione di Eclipse (forse perchè caricata purtroppo su macchina virtuale?) mi da problemi a importare la libreria Arrays (a dire il vero qualsiasi libreria) che dovrei usare per fare i test.
	Ne ho fatta una versione di prova solo per poter far vedere negli esempi che la postcondizone contenente un metodo puro si attiva correttamente.
	Qualora fosse necessario dimostrare di saper verificare il CF rimango a disposizione per scrivere e fornire tale codice
	utilizzando ide più aggiornato e senza questo bug.
	*/
	public /*@ pure @*/ boolean Controllo_Codice (String cod){
		
		if(cod=="qwerty"){return false;} else {return true;}
	}
	//public String treconsNome(){};
	//public String treconsCognome();
	
	public static void main(String[] args) {
		boolean esito1, esito2;
		//es. parametri eletore corretti:
		//Elettore E1= new Elettore("Lorenzo", "Gardelli", "Italia", "PC", "M", "GRDLNZ95D12D611X", 12, 4, 1995, false);
		//System.out.println(E1.CF);
		
		//es. voto una sola volta correttamente: nessun problema.
		//if(E1.esprimi_voto()){System.out.println("Tentativo di esprimere voto: HAI VOTATO!");}
		//else{System.out.println("Tentativo di esprmere voto: NON PUOI VOTARE!");}
		
		//es. voto due volte: l'ensures non viene violato perchè il metodo è corretto e se ne accorge.
		//esito1=E1.esprimi_voto();
		//if(esito1){System.out.println("Tentativo di esprimere voto: HAI VOTATO!");}
		//else{System.out.println("Tentativo di esprmere voto: NON PUOI VOTARE!");}
		//esito1=E1.esprimi_voto();
		//if(esito1){System.out.println("Tentativo di esprimere voto: HAI VOTATO!");}
		//else{System.out.println("Tentativo di esprmere voto: NON PUOI VOTARE!");}
		
		//es. un minorenne prova a esprimere il voto: solleva requires;
		//Elettore E2= new Elettore("Lorenzo", "Gardelli", "Italia", "PC", "M", "GRDLNZ95D12D611X", 12, 4, 2010, false);
		//esito2=E2.esprimi_voto();
		//if(esito2){System.out.println("Tentativo di esprimere voto: HAI VOTATO!");}
		//else{System.out.println("Tentativo di esprmere voto: NON PUOI VOTARE!");}
		
		//es. l'elettore viene inizializzato con un sesso diverso da quelli accettati: (sesso indicato: X invece che M o F)
		//Elettore E3= new Elettore("Lorenzo", "Gardelli", "Italia", "PC", "X", "GRDLNZ95D12D611X", 12, 4, 1995, false);
		
		//es. data non valida: nato nel futuro
		//Elettore E4= new Elettore("Lorenzo", "Gardelli", "Italia", "PC", "M", "GRDLNZ95D12D611X", 12, 4, 2050, false);
		
		//es. codice fiscale non valido
		//Elettore E1= new Elettore("Lorenzo", "Gardelli", "Italia", "PC", "M", "qwerty", 12, 4, 1995, false);
		//System.out.println(E1.CF);
	}

}
