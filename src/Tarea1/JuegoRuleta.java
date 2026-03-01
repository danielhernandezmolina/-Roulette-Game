package Tarea1;

import java.util.Scanner;

public class JuegoRuleta {
	
	private int saldo;
	private int tipoDeApuesta;
	private int resultado;
	private int cantidadApostada;
	Scanner entrada = new Scanner (System.in);
	
	// PART ONE: REQUEST BALANCE
		
	public void iniciarJuego(){
		
		
	
		System.out.println("\nRecharge your balance to start playing: ");
		
		do {
			
			try {
				saldo = entrada.nextInt();
			
			if(saldo>0) {System.out.println("\nBalance successfully added! You currently have " + saldo + " euros in your account!");}
			else {System.out.println("\nERROR!!= PLEASE ENTER AN AMOUNT GREATER THAN 0");}
			
			// Safety net in case the user enters something other than an integer
			} catch (Exception e) {System.out.println("\nError: You must enter an integer number.");
                entrada.next(); 
                saldo = -1; 
			}
		// It will repeat as long as the entered number is equal to or less than 0
		} while (saldo <= 0);
						
		}
	
	// PART TWO: REQUEST AMOUNT TO BET
	
	public void realizarApuesta() {
        boolean apuestaValida = false;

        do {
            System.out.println("\nEnter the amount you want to bet:");
            try {
                cantidadApostada = entrada.nextInt();
                
                
                // Check if it's negative

                if (cantidadApostada <= 0) {
                    System.out.println("\nERROR!!= PLEASE ENTER AN AMOUNT GREATER THAN 0");}
                
                // If it's not negative, check the balance
                else if(cantidadApostada > saldo) { System.out.println("ERROR!!= Not enough balance. Your balance is " + saldo + " euros");}

                // If it passes both filters, it's true and therefore exits the loop
                 else {
                    apuestaValida = true; 
                }
                // This error appears if the game crashes because the data entered by the user is not numeric

            } catch (Exception e) {
                System.out.println("\nError: You must enter a valid number.");
                entrada.next(); 
            }
            
            // It will repeat as long as the bet is not valid.
        } while (!apuestaValida); 
        
        // PART THREE: TYPE OF BET
        
        // I CREATED A TRY-CATCH STRUCTURE IN CASE THE USER ENTERS INVALID PARAMETERS LIKE NUMBERS GREATER THAN 2 OR WORDS.
        
        do {
        	
        	apuestaValida = false;
        try {
		
		System.out.println("\n\nSelect the type of bet by pressing (1 or 2):\n\n1. Number (1-36)\n2. Even/Odd ");
		
		tipoDeApuesta = entrada.nextInt();
		
		if(tipoDeApuesta == 1 || tipoDeApuesta == 2  ) { apuestaValida = true;
		
		} else {
            System.out.println("Error: Please choose only option 1 or 2.");
            apuestaValida = false; 
		}
		
        } catch (Exception e) {
            System.out.println("\nError: You must enter a valid number.");
            entrada.next(); }
        
        } while (!apuestaValida); 
		
		
		
		switch (tipoDeApuesta) {
		
		// TYPE OF BET FOR SINGLE NUMBERS 1 TO 36
		
		case 1: 
			int numeroApostado=-1;
			String respuesta1="";
			int numeroRuleta= 0; 
			
			apuestaValida = false; 
			do {
				if (saldo<=0) {revisarSaldo();}
				numeroRuleta = tirarRuleta();
				apuestaValida = false; 
        do {
            try {
                System.out.println("\nChoose a number from 0 to 36");
                numeroApostado = entrada.nextInt();
                

                if (numeroApostado >= 0 && numeroApostado <= 36) {
                    apuestaValida = true; 
                } else {
                    System.out.println("Error: The number must be between 0 and 36.");
                    apuestaValida = false;
                }
            

            } catch (Exception e) {
                 System.out.println("\nError: You must enter a valid number.");
                 entrada.next(); 
                 apuestaValida = false;
            }
        } while (!apuestaValida);
			
		System.out.println("\nThe number " + numeroRuleta + " came up");	
		
		// IF THEY WIN, MULTIPLY THE BET BY 36 AND ADD IT TO THE BALANCE
			
		if (numeroApostado == numeroRuleta) { saldo = saldo + cantidadApostada * 36;
			
			System.out.println("\nYou won!!! Your current balance is " + saldo + " euros");
		}
		
		// IF THEY LOSE, SUBTRACT THE BET AMOUNT FROM THE BALANCE
		
		else { saldo = saldo - cantidadApostada;
			
			System.out.println("\nYou lost :( Your current balance is " + saldo + " euros\n");
		}
		
		if (!revisarSaldo()) break;
		
		System.out.println("Do you want to bet on another number? (s/n): ");
        respuesta1 = entrada.next();
        

			
		// IF THE ANSWER IS 'S' REGARDLESS OF UPPERCASE OR LOWERCASE AND THE BALANCE IS GREATER THAN 0, THE LOOP REPEATS. IF IT IS ANYTHING ELSE
		// DISPLAY GOODBYE MESSAGE.
			
		}while (respuesta1.equalsIgnoreCase("s") && saldo > 0);
					
		break;
			
		// TYPE OF BET EVEN OR ODD
		
		case 2: 
			
			String respuesta2="";
			int eleccionUsuario=0;
			int numeroRuleta2=0;
			
			do {
				
				if (saldo<=0) {revisarSaldo();}
			
			else {numeroRuleta2 = tirarRuleta();}
			
			do {
			
			try {
			
			System.out.println("\nWhat do you want to bet on? (1 or 2) \n1. EVEN \n2. ODD");
			
			eleccionUsuario = entrada.nextInt();
			
			if(eleccionUsuario == 1 || eleccionUsuario == 2  ){apuestaValida = true;
			
			} else {
            System.out.println("Error: Please choose only option 1 or 2.");
            apuestaValida = false;} 
			}catch (Exception e) {
            System.out.println("\nError: You must enter a valid number.");
            entrada.next(); 
            
            apuestaValida = false;}
			
			} while (!apuestaValida); 
			

			
			respuesta2=entrada.nextLine();
			
			
			// We use % to determine if the number from the roulette is EVEN OR ODD 
			
			if (numeroRuleta2 == 0) { System.out.println("\n0 came up! The house wins.\nYou lose: " + cantidadApostada + " euros\nDo you want to bet again? (s/n):");
			respuesta2 = entrada.nextLine();
			
			// IF THE REMAINDER IS EQUAL TO 0 IT MEANS THE NUMBER IS EVEN
			  
			} else if (numeroRuleta2 % 2 == 0 && eleccionUsuario == 1){saldo = saldo + cantidadApostada * 2;
				System.out.println("\nThe number is EVEN! YOU WON, THE NUMBER " + numeroRuleta2 + " CAME UP!" + " Your current balance is " + saldo + " euros.\nDo you want to bet again? (s/n):" );
				respuesta2 = entrada.nextLine();}
			
			// IF THE REMAINDER IS EQUAL TO 1 IT MEANS THE NUMBER IS ODD
			    
			 else if (numeroRuleta2 % 2 != 0 && eleccionUsuario == 2) {saldo = saldo + cantidadApostada * 2;
				 System.out.println("\nThe number is ODD! YOU WON, THE NUMBER " + numeroRuleta2 + " CAME UP!" + " Your current balance is " + saldo + " euros\nDo you want to bet again? (s/n):");
				 respuesta2 = entrada.nextLine();}
			
			 else {saldo = saldo - cantidadApostada;
                System.out.println("\nI'm sorry, you lost... the number " + numeroRuleta2 + " came up\nYou lose: " + cantidadApostada + " euros.\nDo you want to bet again? (s/n):");
                
                respuesta2 = entrada.nextLine();
                }
						
			if (!revisarSaldo()) break;
			// IF THE ANSWER IS 'S' REGARDLESS OF UPPERCASE OR LOWERCASE AND THE BALANCE IS GREATER THAN 0, THE LOOP REPEATS. IF IT IS ANYTHING ELSE
		    // DISPLAY GOODBYE MESSAGE.
			
			}while (respuesta2.equalsIgnoreCase("s") && saldo > 0);}
		
		System.out.println("\n\nThank you very much for playing: THE WHEEL OF FORTUNE!! Your final balance is " + saldo + " euros");
		
		
			
		}

	
	// I'm adding a small auxiliary method to check when the user runs out of balance and ask if they want to recharge.
	
	public boolean revisarSaldo() {	
		
		
		if (saldo>0) return true;
			
			System.out.println("\n--- YOU HAVE RUN OUT OF BALANCE! ---");
        System.out.println("\nDo you want to recharge your balance to keep playing? (s/n): ");
        String respuesta3 = entrada.next();
        
     // Calls the method to ask for money
        
		if (respuesta3.equalsIgnoreCase("s")) { iniciarJuego(); 
		return true;} // Returns true when recharged
	
    else { System.out.println("\n\nThank you very much for playing: THE WHEEL OF FORTUNE!! Your final balance is " + saldo + " euros");}
		return false;} // Returns false when not recharged


		
		
	// METHOD TO SPIN THE ROULETTE GIVEN AS HELP IN THE ASSIGNMENT. 
		
	public int tirarRuleta() {
		 
        return (int)(Math.random() * 37);
	}	
	
	

	}


