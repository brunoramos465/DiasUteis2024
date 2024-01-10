package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class Months {
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	List<Months> list = new ArrayList<Months>(); 

	private LocalDate date;
	private String week;
	private Integer du;

	public Months() {
		
	}
	
	public Months(LocalDate date, String week, Integer du) {
		super();
		this.date = date;
		this.week = week;
		this.du = du;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	
	
	
	public Integer getDu() {
		return du;
	}


	public void setDu(Integer du) {
		this.du = du;
	}


	public void addList(Months days) {
		list.add(days);
	}
	
	public void removeList(Months days) {
		list.remove(days);
	}
	
	public List<Months> getList() {
		return list;
	}

	public void setList(List<Months> list) {
		this.list = list;
	}
	
	
	public String toString() { 
		StringBuilder sc = new StringBuilder();
		
		//IMPRIMIR OS DADOS DA LISTA
		for (Months lista : list) {
			sc.append(fmt.format(lista.getDate())+ " - " + lista.getWeek() + " - " + lista.getDu() + "DU\n");
		}
		
		return sc.toString();
	}
	

	
	
	

}
