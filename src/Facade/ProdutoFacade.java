package Facade;

import java.util.List;

import javax.swing.JTable;

import entidade.Produto;

public interface ProdutoFacade {

	public void adicionar(Produto produto);
	public void editar(Produto produto);
	public void deletar(int id);
	JTable getLista(JTable table);
}
