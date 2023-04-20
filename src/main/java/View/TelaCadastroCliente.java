package View;

import java.awt.EventQueue;
import java.awt.Shape;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Controller.ClienteController;
import Controller.EnderecoController;
import Model.exception.CampoInvalidoException;
import Model.exception.CpfJaUtilizadoException;
import Model.exception.EnderecoInvalidoException;
import Model.vo.telefonia.Cliente;
import Model.vo.telefonia.Endereco;

import java.text.ParseException;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;

import java.awt.Color;
import javax.swing.BorderFactory;

public class TelaCadastroCliente {

	private JFrame frmNovoCliente;
	private JTextField txtNome;
	private JFormattedTextField txtCpf;
	private JTextField txtEndereco;

	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblEndereco;

	private MaskFormatter mascaraCpf;
	private JComboBox cbEndereco;
	private JButton btnSalvar;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente window = new TelaCadastroCliente();
					window.frmNovoCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroCliente() throws ParseException {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ParseException {
		frmNovoCliente = new JFrame();
		frmNovoCliente.getContentPane().setBackground(new Color(128, 128, 128));
		frmNovoCliente.setTitle("Novo Cliente");
		frmNovoCliente.setBounds(100, 100, 485, 239);
		frmNovoCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoCliente.getContentPane().setLayout(null);

		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 18, 45, 13);
		frmNovoCliente.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBackground(new Color(192, 192, 192));
		txtNome.setBounds(85, 15, 291, 19);
		frmNovoCliente.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 50, 45, 13);
		frmNovoCliente.getContentPane().add(lblCpf);

		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setBackground(new Color(192, 192, 192));
		txtCpf.setBounds(85, 47, 291, 19);
		frmNovoCliente.getContentPane().add(txtCpf);

		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(10, 95, 81, 13);
		frmNovoCliente.getContentPane().add(lblEndereco);

		txtNome.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		txtCpf.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

		EnderecoController endController = new EnderecoController();
		List<Endereco> enderecosCadastrados = endController.consultarTodos();

		cbEndereco = new JComboBox<Object>(enderecosCadastrados.toArray());
		cbEndereco.setBackground(new Color(192, 192, 192));
		cbEndereco.setBounds(85, 92, 291, 19);
		frmNovoCliente.getContentPane().add(cbEndereco);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(192, 192, 192));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente novoCliente = new Cliente();
				novoCliente.setNome(txtNome.getText());

				try {

					String cpfSemMascara = (String) mascaraCpf.stringToValue(txtCpf.getText());
					novoCliente.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				novoCliente.setEndereco((Endereco) cbEndereco.getSelectedItem());

				ClienteController controller = new ClienteController();

				try {
					controller.inserir(novoCliente);

					JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (CpfJaUtilizadoException | EnderecoInvalidoException | CampoInvalidoException excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(192, 171, 85, 21);
		btnSalvar.setOpaque(true);
		frmNovoCliente.getContentPane().add(btnSalvar);

	}
}
