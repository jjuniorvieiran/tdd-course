package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void criaAvaliador(){
		leiloeiro = new Avaliador();
		joao = new Usuario("João");
		jose = new Usuario("José");
		maria = new Usuario("Maria");
	}
	
	
	@Test
	public void deveEntenderLances() {
		
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();
		
		leiloeiro.avalia(leilao);


		assertEquals(100, leiloeiro.getMenorDeTodos(), 0.00001);
		assertEquals(400, leiloeiro.getMaiorDeTodos(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComUmSoLance() {
		
		Leilao leilao = new Leilao("Play 2 Novinho");
		leilao.propoe(new Lance(joao, 1000.0));
		
		leiloeiro.avalia(leilao);

		assertEquals(1000, leiloeiro.getMaiorDeTodos(), 0.00001);
	}

	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new Leilao("Play 2 Novinho");
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		leiloeiro.avalia(leilao);

		List<Lance> lances = leiloeiro.getTresMaiores();
		
		assertEquals(3, lances.size());
		assertEquals(400, lances.get(0).getValor(), 0.00001);
		assertEquals(300, lances.get(1).getValor(), 0.00001);
		assertEquals(200, lances.get(2).getValor(), 0.00001);
	}

}
