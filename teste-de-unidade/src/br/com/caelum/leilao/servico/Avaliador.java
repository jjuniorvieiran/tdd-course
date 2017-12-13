package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	
	
	public void avalia(Leilao leilao){

		if(leilao.getLances().size() == 0){
			throw new RuntimeException("Não é possivel avaliar um leilão sem lances!");
		}
		
		
		for (Lance lance : leilao.getLances()) {
			
			if(lance.getValor() > maiorDeTodos)
				maiorDeTodos = lance.getValor();
			
			if(lance.getValor() < menorDeTodos)
				menorDeTodos = lance.getValor();
		}
		
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {


			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()) return 1;
				if(o1.getValor() > o2.getValor()) return -1;
				
				return 0;
			}

			public <T, U extends Comparable<? super U>> Comparator<T> comparing(
					Function<? super T, ? extends U> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T, U> Comparator<T> comparing(
					Function<? super T, ? extends U> arg0,
					Comparator<? super U> arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T> Comparator<T> comparingDouble(
					ToDoubleFunction<? super T> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T> Comparator<T> comparingInt(
					ToIntFunction<? super T> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T> Comparator<T> comparingLong(
					ToLongFunction<? super T> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T> Comparator<T> nullsFirst(
					Comparator<? super T> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T> Comparator<T> nullsLast(Comparator<? super T> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public  <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator<Lance> reversed() {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator<Lance> thenComparing(
					Comparator<? super Lance> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public <U extends Comparable<? super U>> Comparator<Lance> thenComparing(
					Function<? super Lance, ? extends U> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public <U> Comparator<Lance> thenComparing(
					Function<? super Lance, ? extends U> arg0,
					Comparator<? super U> arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator<Lance> thenComparingDouble(
					ToDoubleFunction<? super Lance> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator<Lance> thenComparingInt(
					ToIntFunction<? super Lance> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator<Lance> thenComparingLong(
					ToLongFunction<? super Lance> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			

		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
	}
	
	public double getMaiorDeTodos() {
		return maiorDeTodos;
	}
	
	public double getMenorDeTodos() {
		return menorDeTodos;
	}
}
