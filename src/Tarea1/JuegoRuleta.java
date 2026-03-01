package Tarea1;

import java.util.Scanner;

public class JuegoRuleta {
	
	private int saldo;
	private int tipoDeApuesta;
	private int resultado;
	private int cantidadApostada;
	Scanner entrada = new Scanner (System.in);
	
	// PRIMERA PARTE SOLICITAR RECARGA DE SALDO
		
	public void iniciarJuego(){
		
		
	
		System.out.println("\nRecarga tu saldo para empezar a jugar: ");
		
		do {
			
			try {
				saldo = entrada.nextInt();
			
			if(saldo>0) {System.out.println("\nEl saldo ha sido ingresado con exito! Tienes ingresado en este momento " + saldo + " euros en tu cuenta!");}
			else {System.out.println("\nERROR!!= INTRODUZCA UNA CANTIDAD SUPERIOR A 0");}
			
			//Red de seguridad por si el usuario pone algo que no sea un numero entero
			} catch (Exception e) {System.out.println("\nError: Debes introducir un número entero.");
                entrada.next(); 
                saldo = -1; 
			}
		// Se repetirá mientras el numero introducido sea igual o menor a 0
		} while (saldo <= 0);
						
		}
	
	// SEGUNDA PARTE SOLICITAR CANTIDAD A APOSTAR
	
	public void realizarApuesta() {
        boolean apuestaValida = false;

        do {
            System.out.println("\nIntroduce cantidad que desea apostar:");
            try {
                cantidadApostada = entrada.nextInt();
                
                
                //Mirar si es negativo

                if (cantidadApostada <= 0) {
                    System.out.println("\nERROR!!= INTRODUZCA UNA CANTIDAD SUPERIOR A 0");}
                
                //Si no es negativo miramos el saldo
                else if(cantidadApostada > saldo) { System.out.println("ERROR!!= No tienes suficiente saldo. Tu saldo es de " + saldo + " euros");}

                //Si pasa los dos filtros es true y por lo tanto sale del bucle
                 else {
                    apuestaValida = true; 
                }
                // Aparece este error si el juego explota porque los datos que ponga el usuario no sean numericos

            } catch (Exception e) {
                System.out.println("\nError: Debes introducir un número válido.");
                entrada.next(); 
            }
            
            //Se repetira mientras la apuesta no sea valida.
        } while (!apuestaValida); 
        
        // TERCERA PARTE TIPO DE APUESTA
        
        // HAGO UN ESTRUCTURA DE TRY-CATCH POR SI EL USUARIO PONE PARAMETROS NO VALIDOS COMO NUMEROS SUPERIORES A 2 O PALABRAS.
        
        do {
        	
        	apuestaValida = false;
        try {
		
		System.out.println("\n\nSeleccione tipo de apuesta presionando (1 o 2):\n\n1. Número(1-36)\n2. Par/Impar ");
		
		tipoDeApuesta = entrada.nextInt();
		
		if(tipoDeApuesta == 1 || tipoDeApuesta == 2  ) { apuestaValida = true;
		
		} else {
            System.out.println("Error: Por favor, elija solo la opción 1 o la 2.");
            apuestaValida = false; 
		}
		
        } catch (Exception e) {
            System.out.println("\nError: Debes introducir un número válido.");
            entrada.next(); }
        
        } while (!apuestaValida); 
		
		
		
		switch (tipoDeApuesta) {
		
		//TIPO DE APUESTA POR NUMEROS SIMPLES 1 AL 36
		
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
	                System.out.println("\nElije un número del 0 al 36");
	                numeroApostado = entrada.nextInt();
	                

	                if (numeroApostado >= 0 && numeroApostado <= 36) {
	                    apuestaValida = true; 
	                } else {
	                    System.out.println("Error: El número debe ser entre 0 y 36.");
	                    apuestaValida = false;
	                }
	            

	            } catch (Exception e) {
	                 System.out.println("\nError: Debes introducir un número válido.");
	                 entrada.next(); 
	                 apuestaValida = false;
	            }
	        } while (!apuestaValida);
			
		System.out.println("\nSalió el número " + numeroRuleta);	
		
		// SI GANA SUMAMOS X 36 LO APOSTADO Y LO SUMO AL SALDO
			
		if (numeroApostado == numeroRuleta) { saldo = saldo + cantidadApostada * 36;
			
			System.out.println("\nGanaste!!! Tu saldo actual es de " + saldo + " euros");
		}
		
		// SI PIERDE LE RESTO AL SALDO LA CANTIDAD APOSTADA
		
		else { saldo = saldo - cantidadApostada;
			
			System.out.println("\nPerdiste :( Tu saldo actual es de " + saldo + " euros\n");
		}
		
		if (!revisarSaldo()) break;
		
		System.out.println("¿Quieres apostar a otro número? (s/n): ");
        respuesta1 = entrada.next();
        

			
		// SI LA RESPUESTA ES S INDEPENDIENTEMENTE MAYUSCULA O MINUSCULA Y EL SALDO SEA SUPERIOR A 0 SE REPITE EL BUCLE SI ES CUALQUIER OTRA COSA
		// LANZO MENSAJE DE DESPEDIDA.
			
		}while (respuesta1.equalsIgnoreCase("s") && saldo > 0);
					
		break;
			
		// TIPO DE APUESTA PAR O IMPAR
		
		case 2: 
			
			String respuesta2="";
			int eleccionUsuario=0;
			int numeroRuleta2=0;
			
			do {
				
				if (saldo<=0) {revisarSaldo();}
			
			else {numeroRuleta2 = tirarRuleta();}
			
			do {
			
			try {
			
			System.out.println("\n¿A qué quieres apostar?(1 o 2) \n1. PAR \n2. IMPAR");
			
			eleccionUsuario = entrada.nextInt();
			
			if(eleccionUsuario == 1 || eleccionUsuario == 2  ){apuestaValida = true;
			
			} else {
	            System.out.println("Error: Por favor, elija solo la opción 1 o la 2.");
	            apuestaValida = false;} 
			}catch (Exception e) {
	            System.out.println("\nError: Debes introducir un número válido.");
	            entrada.next(); 
	            
	            apuestaValida = false;}
			
			} while (!apuestaValida); 
			

			
			respuesta2=entrada.nextLine();
			
			
			// Utilizamos % para saber si el numero que sale en la ruleta es PAR O IMPAR 
			
			if (numeroRuleta2 == 0) { System.out.println("\n¡Ha salido el 0! La banca gana.\nPierdes: " + cantidadApostada + " euros\n¿Quieres volver a apostar? (s/n):");
			respuesta2 = entrada.nextLine();
			
			// SI ES EL RESTO DA IGUAL A 0 SIGNIFICA QUE EL NUMERO ES PAR
			  
			} else if (numeroRuleta2 % 2 == 0 && eleccionUsuario == 1){saldo = saldo + cantidadApostada * 2;
				System.out.println("\n¡El número es PAR! ¡GANASTE SALIÓ EL NÚMERO " + numeroRuleta2 + "!" + " Tu saldo actual es de " + saldo + " euros.\n¿Quieres volver a apostar? (s/n):" );
				respuesta2 = entrada.nextLine();}
			
			// SI ES EL RESTO DA IGUAL A 1 SIGNIFICA QUE EL NUMERO ES IMPAR
			    
			 else if (numeroRuleta2 % 2 != 0 && eleccionUsuario == 2) {saldo = saldo + cantidadApostada * 2;
				 System.out.println("\n¡El número es IMPAR! ¡GANASTE SALIÓ EL NÚMERO " + numeroRuleta2 + "!" + " Tu saldo actual es de " + saldo + " euros\n¿Quieres volver a apostar? (s/n):");
				 respuesta2 = entrada.nextLine();}
			
			 else {saldo = saldo - cantidadApostada;
			        System.out.println("\nLo siento, perdiste... salió el número " + numeroRuleta2 + "\nPierdes: " + cantidadApostada + " euros.\n¿Quieres volver a apostar? (s/n):");
			        
			        respuesta2 = entrada.nextLine();
			        }
						
			if (!revisarSaldo()) break;
			// SI LA RESPUESTA ES S INDEPENDIENTEMENTE MAYUSCULA O MINUSCULA Y EL SALDO SEA SUPERIOR A 0 SE REPITE EL BUCLE SI ES CUALQUIER OTRA COSA
			// LANZO MENSAJE DE DESPEDIDA.
			
			}while (respuesta2.equalsIgnoreCase("s") && saldo > 0);}
		
		System.out.println("\n\nMuchas gracias por jugar en: LA RULETA DE LA SUERTE!! Su saldo final es de " + saldo + " euros");
		
		
			
		}

	
	// Añado un pequeño método auxiliar para comprobar cuando el usuario se queda sin saldo y le pregunta si quiere recargar.
	
	public boolean revisarSaldo() {	
		
		
		if (saldo>0) return true;
			
			System.out.println("\n--- ¡TE HAS QUEDADO SIN SALDO! ---");
	        System.out.println("\n¿Quieres recargar saldo para seguir jugando? (s/n): ");
	        String respuesta3 = entrada.next();
	        
	     // Llama al método para pedir dinero
	        
		if (respuesta3.equalsIgnoreCase("s")) { iniciarJuego(); 
		return true;} // Devuelve true cuando ha recargado
	
	    else { System.out.println("\n\nMuchas gracias por jugar en: LA RULETA DE LA SUERTE!! Su saldo final es de " + saldo + " euros");}
		return false;} // Devuelve false cuando no ha recargado


		
		
	//METODO DE TIRAR A LA RULETA QUE SE NOS DIO COMO AYUDA EN LA TAREA. 
		
	public int tirarRuleta() {
		 
        return (int)(Math.random() * 37);
	}	
	
	

	}
	



