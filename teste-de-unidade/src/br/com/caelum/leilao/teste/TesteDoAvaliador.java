package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Assert;
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
	public void deveEntenderLancesEmOrdemCrescente() {
		
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();
		
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMaiorDeTodos(), equalTo(400.0));
		assertThat(leiloeiro.getMenorDeTodos(), equalTo(100.0));
	}
	
	@Test
	public void deveEntenderLeilaoComUmSoLance() {
		
		Leilao leilao = new Leilao("Play 2 Novinho");
		leilao.propoe(new Lance(joao, 1000.0));
		
		leiloeiro.avalia(leilao);

		assertEquals(1000, leiloeiro.getMaiorDeTodos(), 0.00001);
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemLances(){
		Leilao leilao = new CriadorDeLeilao().para("PlayStation3").constroi();
		leiloeiro.avalia(leilao);
	}

	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new Leilao("Play 2 Novinho");
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertThat(maiores, hasItems(new Lance(maria, 400.0),
									new Lance(joao, 300.0),
									new Lance(maria, 200.0)));	
	}

}
