package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import entities.Months;

public class Program {
	public static void main(String[] args) {
						
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		File file = new File("C:\\CONTROL\\");
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		String path = "C:\\CONTROL\\DU.txt";

		String[] holiday = {"01/01/2024","12/02/2024", "13/02/2024", "29/03/2024", "21/04/2024",
				"01/05/2024", "30/05/2024", "07/09/2024", "12/10/2024", "02/11/2024", "15/11/2024", 
				"20/11/2024", "25/12/2024"};
		
		LocalDate year = LocalDate.MAX.withYear(2024); // CAPTURAR O ANO
		LocalDate day = LocalDate.EPOCH.withYear(2024); // CAPTURAR O DIA
		
		Months months = new Months();
		int numberMonths = 01; // INICIO EM JANEIRO 01
		int DU = 0; // DU INICIANDO EM 0
	
		for(int i = 0; i < year.getDayOfYear();i++) {
			
			boolean isHolidey = false; // FERIADO FALSO
			boolean finalWeek = false; // FERIADO FALSO
			
			try { // CASO FERIADO VERDADEIRO SALVAR EM holidayDate
				for(String holidayDate : holiday) {
					if (LocalDate.from(day).plusDays(i).format(fmt).equals(holidayDate)) {
						isHolidey = true;
						break;
					}
				}
				
			} catch (ArrayIndexOutOfBoundsException e ) {
				System.out.println("Error " + e);
			}
			
			
			try { // CASO FINAL DE SEMANA  VERDADEIRO SALVAR EM finalWeek
				for(int j = 0; j < year.getDayOfYear(); j++) {
					if(LocalDate.from(day).plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase().equals("SÁBADO") || LocalDate.from(day).plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase().equals("DOMINGO")) {
						finalWeek = true;
					}
				}
			} catch(ArrayIndexOutOfBoundsException e ) {
				System.out.println("Error " + e);
			}
			

			if(isHolidey || finalWeek) { //IF VAZIO NÃO SERÁ UTILIZADO O FINAL DE SEMANA E FERIADOS
				
			} else {
								
				String date = LocalDate.from(day).plusDays(i).format(fmt); //OBTER TODOS OS DIAS
				String week = LocalDate.from(day).plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase(); // OBTER TODOS OS DIAS SEMANALMENTE
				
				String[] fields = date.split("/"); 
		
				if (numberMonths == Integer.parseInt(fields[1])) {
					DU = DU + 1; //  CASO O DU FOR IGUAL A FIELDS DU + 1
				} 
				
				if(numberMonths != Integer.parseInt(fields[1])){ // CASO NUMBERMONTHS FOR DIFERENTE DE FIELDS
					numberMonths = numberMonths + 1; //NUMBERMONTHS GANHA UM MES 
					DU = 1; // DU INICIA COM 1
				}
					
				Months monthsList = new Months(LocalDate.parse(date, fmt),week, DU); // CONSTRUDOR MONTHS				

				months.addList(monthsList);	//ADICIONANDO A LISTA
				
			}	
			
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(Months line: months.getList()) {
				String fields = line.getDate() + ";" + line.getWeek() + ";" + line.getDu() + " D.U";				
				bw.write(fields); //ESCREVER NA PASTA PATH CADA LINHA DA STRING FIELDS
				bw.newLine();
			}
		} catch(IOException e ){
			e.printStackTrace();

		}
			
		System.out.println(months.toString()); //IMPRIMIR NA TELA
		
	}
}



