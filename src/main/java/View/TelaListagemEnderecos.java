package View;

import java.awt.EventQueue;
import controller.EnderecoController;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class TelaListagemEnderecos {

	private JFrame frameListagemEnderecos;
	private JTable tableEnderecos;
	
	private JButton btnEditar;
	private JButton btnFechar;
	private JButton btnBuscarTodos;
	
	private String[] nomeColunas = {"#", "CEP", "Rua", "Número", "Bairro", "Cidade", "Estado" };
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemEnderecos window = new TelaListagemEnderecos();
					window.frameListagemEnderecos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaListagemEnderecos() {
		initialize();
	}

	private void limparTabela() {
		tableEnderecos.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
		tableEnderecos = new JTable(tableEnderecos.getModel()) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
	}
	private void initialize() {
		frameListagemEnderecos = new JFrame();
		frameListagemEnderecos.setBounds(100, 100, 700, 525);
		frameListagemEnderecos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameListagemEnderecos.getContentPane().setLayout(null);
		
		tableEnderecos = new JTable();
		tableEnderecos.setBackground(Color.LIGHT_GRAY);
		tableEnderecos.setBounds(15, 70, 655, 350);
		frameListagemEnderecos.getContentPane().add(tableEnderecos);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(194, 441, 126, 33);
		frameListagemEnderecos.getContentPane().add(btnEditar);
		
		btnFechar = new JButton("Fechar");
		btnFechar.setBounds(346, 441, 126, 33);
		frameListagemEnderecos.getContentPane().add(btnFechar);
		
		btnBuscarTodos = new JButton("Buscas Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController controller = new EnderecoController();
			}
		});
		btnBuscarTodos.setBounds(250, 20, 167, 40);
		frameListagemEnderecos.getContentPane().add(btnBuscarTodos);
		
	
		
		
	}
}
