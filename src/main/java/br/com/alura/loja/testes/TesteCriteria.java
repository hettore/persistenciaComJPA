package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.now());
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "Top de linha", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "PlayStation 5", new BigDecimal("5000"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook pro retina", new BigDecimal("8000"), informatica);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(informatica);
		categoriaDao.cadastrar(videogames);
		
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		produtoDao.cadastrar(celular);
		
		
		em.getTransaction().commit();
		em.close();
		
	}

}
