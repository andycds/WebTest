package test;


import static org.junit.Assert.assertEquals;
import model.Cliente;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import service.ClienteService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {
	Cliente cliente, copia;
	ClienteService clienteService;
	static int id = 0;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se também que sobrecarregou o equals na classe
	 * Cliente. 
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome("Batista Cepelos");
		cliente.setFone("(11) 91234-4321");
		cliente.setEmail("(11) 91234-4321");
		copia = new Cliente();
		copia.setId(id);
		copia.setNome("Batista Cepelos");
		copia.setFone("(11) 91234-4321");
		copia.setEmail("(11) 91234-4321");
		clienteService = new ClienteService();
		System.out.println(cliente);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		Cliente fixture = new Cliente();
		fixture.setId(1);
		fixture.setNome("Carlos Drummond de Andrade");
		fixture.setFone("(11) 91234-4321");
		fixture.setEmail("cda@usjt.br");
		ClienteService novoService = new ClienteService();
		Cliente novo = novoService.carregar(1);
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		id = clienteService.criar(cliente);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", cliente, copia);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		cliente.setFone("999999");
		copia.setFone("999999");		
		clienteService.atualizar(cliente);
		cliente = clienteService.carregar(cliente.getId());
		assertEquals("testa atualizacao", cliente, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setFone(null);
		copia.setEmail(null);
		clienteService.excluir(id);
		cliente = clienteService.carregar(id);
		assertEquals("testa exclusao", cliente, copia);
	}
}