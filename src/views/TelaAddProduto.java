package views;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import Facade.ProdutoDAO;
import entidade.Produto;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class TelaAddProduto extends JFrame {

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
					TelaAddProduto frame = new TelaAddProduto();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ProdutoDAO dao = new ProdutoDAO();
	
	/**
	 * Create the frame.
	 */
	public TelaAddProduto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(25, 23, 405, 334);
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
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//	Cria um novo produto e adiciona os dados no produto
					Produto produto = new Produto();
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
					
					//	Adiciona o produto
					dao.adicionar(produto);
					
					dispose();
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Para adicionar um produto todos os campos precisam ser validos!!!");
				}
			}
		});
		btnAdd.setBounds(161, 264, 89, 36);
		panel.add(btnAdd);
		
		chckbxDisponivel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDisponivel.setBounds(70, 202, 180, 36);
		panel.add(chckbxDisponivel);
	}
}
