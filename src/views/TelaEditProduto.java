package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Facade.ProdutoDAO;
import entidade.Produto;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class TelaEditProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtQtd;
	private JTextField txtPreco;
	JCheckBox chckbxDisponivel = new JCheckBox("Dispon\u00EDvel para venda?");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditProduto frame = new TelaEditProduto();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	ProdutoDAO dao = new ProdutoDAO();
	private JTextField txtId;
	public void enviaDados(MouseAdapter mouseAdapter, String id, String nome, String qtd, String preco, String disponivel) {
		txtId.setText(id);
		txtNome.setText(nome);
		txtQtd.setText(qtd);
		txtPreco.setText(preco);
		if(disponivel.contains("Sim")) {
			chckbxDisponivel.setSelected(true);
		} else {
			chckbxDisponivel.setSelected(false);
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaEditProduto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(22, 22, 405, 333);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(70, 28, 133, 44);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantidade:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(70, 83, 133, 44);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Pre\u00E7o:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(70, 138, 133, 44);
		panel.add(lblNewLabel_1_1_1);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(179, 42, 150, 20);
		panel.add(txtNome);
		
		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		txtQtd.setBounds(179, 97, 150, 20);
		panel.add(txtQtd);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(179, 152, 150, 20);
		panel.add(txtPreco);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//	ID recebido
					int id = Integer.parseInt(txtId.getText());
					
					Produto produto = new Produto();
					produto.setId(id);
					produto.setNome(txtNome.getText());
					produto.setQtd(Integer.parseInt(txtQtd.getText()));
					produto.setPreco(Float.parseFloat(txtPreco.getText()));
					
					if(chckbxDisponivel.isSelected()) {
						String checkDisponivel = "Sim";
						produto.setDisponivel(checkDisponivel);
					} else {
						String checkDisponivel = "Não";
						produto.setDisponivel(checkDisponivel);
					}
					
					//	Editar produto
					dao.editar(produto);
					
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Para editar um produto todos os campos precisam ser validos!!!");
				}
			}
		});
		btnEditar.setBounds(156, 271, 94, 36);
		panel.add(btnEditar);
		
		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setEditable(false);
		txtId.setText("ID");
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setBounds(362, 11, 33, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		chckbxDisponivel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDisponivel.setBounds(70, 200, 180, 36);
		panel.add(chckbxDisponivel);
	}
}
