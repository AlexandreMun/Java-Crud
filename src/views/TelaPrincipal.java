package views;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Facade.ProdutoDAO;
import entidade.Produto;

import java.text.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class TelaPrincipal {

	private JFrame frmCrudProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmCrudProduto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
		frmCrudProduto.setLocationRelativeTo(null);
		atualizarTabela();
	}

//	-------------------------------------------------------
	private JTable table;
	ProdutoDAO dao = new ProdutoDAO();
	private JButton btnAttTabela;
//	-------------------------------------------------------

//	-------------------------------------------------------
	//	Atualiza a tabela
	public void atualizarTabela() {
		dao.getLista(table);
	}
//	-------------------------------------------------------
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrudProduto = new JFrame();
		frmCrudProduto.setTitle("CRUD - Produto");
		frmCrudProduto.setBounds(100, 100, 612, 550);
		frmCrudProduto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudProduto.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRUD - Produto");
		lblNewLabel.setBounds(149, 24, 233, 65);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		frmCrudProduto.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 130, 512, 258);
		frmCrudProduto.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
				dao.getLista(table);
			}
		});
		//	Table non-editable
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
						TelaEditProduto telaEditProduto = new TelaEditProduto();
						telaEditProduto.setVisible(true);
						
						//	Selecionar elemento da tabela ao clicar
						DefaultTableModel df = (DefaultTableModel)table.getModel();
						int selectedId = table.getSelectedRow();

						String id = df.getValueAt(selectedId, 0).toString();
						String nome = df.getValueAt(selectedId, 1).toString();
						String qtd = df.getValueAt(selectedId, 2).toString();
						String preco = df.getValueAt(selectedId, 3).toString();
						String disponivel = df.getValueAt(selectedId, 4).toString();
						
						telaEditProduto.enviaDados(this, id, nome, qtd, preco, disponivel);
				}
				
			    table.setRowSelectionAllowed(true);
			}

		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Quantidade", "Pre\u00E7o", "Disponível", "Data"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(186);
		table.getColumnModel().getColumn(2).setPreferredWidth(128);
		table.getColumnModel().getColumn(3).setPreferredWidth(97);
		
		JButton btnAddProduto = new JButton("Adicionar Produto");
		btnAddProduto.setBounds(239, 426, 129, 40);
		btnAddProduto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAddProduto telaAddProduto = new TelaAddProduto();
				telaAddProduto.setVisible(true);
			}
		});
		frmCrudProduto.getContentPane().add(btnAddProduto);
		
		btnAttTabela = new JButton("Atualizar Tabela");
		btnAttTabela.setBounds(426, 426, 129, 40);
		btnAttTabela.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAttTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		frmCrudProduto.getContentPane().add(btnAttTabela);
		
		JButton btnDltProduto = new JButton("Exluir Produto");
		btnDltProduto.setBounds(43, 426, 129, 40);
		btnDltProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel df = (DefaultTableModel)table.getModel();
					
					int selectedId = table.getSelectedRow();
					String id = df.getValueAt(selectedId, 0).toString();
					
					if(table.getSelectedRowCount() == 1) {
						dao.deletar(Integer.parseInt(id));
						atualizarTabela();
					} else {
						if(table.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "Tabela vazia!!");
						} else {
							JOptionPane.showMessageDialog(null, "Para excluir seleciona apenas uma linha!!");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Para excluir selecione um produto primeiro!!!");
				}
			}
		});
		btnDltProduto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmCrudProduto.getContentPane().add(btnDltProduto);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(480, 46, 75, 32);
		frmCrudProduto.getContentPane().add(btnSair);
	}
}
