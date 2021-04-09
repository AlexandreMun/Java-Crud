package Facade;

import java.sql.Connection;

import net.proteanit.sql.DbUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import conexao.ConnectionFactory;
import entidade.Produto;

public class ProdutoDAO implements ProdutoFacade {
	
	private Connection connection;
	private PreparedStatement stmt;

	//	Data Atual
	Calendar calendar = Calendar.getInstance();
	java.util.Date date = calendar.getTime();
	
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	
	
	public ProdutoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void adicionar(Produto produto) {
		try {
			//	SQL para adicionar elementos do BD
			String SQL = "insert into produto (nome, qtd, preco, disponivel, data_criacao) values(?, ?, ?, ?, ?)";
			
			//	Passa o SQL
			stmt = connection.prepareStatement(SQL);
			
			//	Seta os dados no BD
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQtd());
			stmt.setFloat(3, produto.getPreco());
			stmt.setString(4, produto.getDisponivel());
			stmt.setDate(5, sqlDate);
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Produto Adicionado Com Sucesso!!!");
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar adicionar produto. " + ex.toString());
		}
	}

	@Override
	public JTable getLista(JTable table) {
		try {
			//	SQL para selecionar elementos do BD
			String SQL = "select id_produto as 'ID', nome as 'Nome', qtd as 'Quantidade', preco as 'Preço', disponivel as 'Disponível', data_criacao as 'Data' from produto order by id_produto";
			//	Passa o SQL
			stmt = connection.prepareStatement(SQL);
			//	Percorre o BD
			ResultSet rs = stmt.executeQuery();

			//	Preenche os dados selecionados no BD na tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			rs.close();
			stmt.close();
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar carregar dados. " + ex.toString());
		}
		return null;
	}

	@Override
	public void editar(Produto produto) {
		try {
			//	SQL para editar elementos do BD
			String SQL = "update produto set nome=?,qtd=?,preco=?,disponivel=?,data_criacao=? where id_produto=?";

			//	Passa o SQL
			stmt = connection.prepareStatement(SQL);

			//	Seta os dados no BD
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQtd());
			stmt.setFloat(3, produto.getPreco());
			stmt.setString(4, produto.getDisponivel());
			stmt.setDate(5, sqlDate);
			stmt.setInt(6, produto.getId());
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Produto Editado Com Sucesso!!!");
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar editar produto. " + ex.toString());
		}
	}

	@Override
	public void deletar(int id) {
		try {
			//	SQL para deletar elementos do BD
			String SQL = "delete from produto where id_produto=?";

			//	Passa o SQL
			stmt = connection.prepareStatement(SQL);

			//	Passa o id selecionado para deletar
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Produto Excluido Com Sucesso!!!");
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar excluir produto. " + ex.toString());
		}
	}

}
