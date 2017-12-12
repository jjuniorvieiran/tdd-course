package br.com.caelum.leilao.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaTeste {
	

	@Test
	public void deveReceberUmLance(){
		
		Leilao leilao = new Leilao("MacBook Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Stevie Jobs"), 2000.0));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances(){
		
		Leilao leilao = new Leilao("MacBook Pro 15");
		leilao.propoe(new Lance(new Usuario("Stevie Jobs"), 2000.0));
		leilao.propoe(new Lance(new Usuario("Stevie Wozniak"), 3000.0));
		
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}



	@Test
	public void naoDeveReceberLancesDoMesmoUser(){
		
		Leilao leilao = new Leilao("MacBook Pro 15");
		
		Usuario stevieJobs = new Usuario("Stevie Jobs");
		
		leilao.propoe(new Lance(stevieJobs, 2000.0));
		leilao.propoe(new Lance(stevieJobs, 1000.0));
		
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}


	
	@Test
	public void naoDeveReceberTerMaisDeCincoLancesDoMesmoUser(){
		
		Leilao leilao = new Leilao("MacBook Pro 15");
		
		Usuario stevieJobs = new Usuario("Stevie Jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(stevieJobs, 2000.0));
		leilao.propoe(new Lance(billGates, 3000.0));
		
		leilao.propoe(new Lance(stevieJobs, 4000.0));
		leilao.propoe(new Lance(billGates, 5000.0));

		leilao.propoe(new Lance(stevieJobs, 6000.0));
		leilao.propoe(new Lance(billGates, 7000.0));	

		leilao.propoe(new Lance(stevieJobs, 8000.0));
		leilao.propoe(new Lance(billGates, 9000.0));

		leilao.propoe(new Lance(stevieJobs, 10000.0));
		leilao.propoe(new Lance(billGates, 11000.0));

		
		//não deve permitir
		leilao.propoe(new Lance(stevieJobs, 12000.0));

		
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	
	
	
	
}
