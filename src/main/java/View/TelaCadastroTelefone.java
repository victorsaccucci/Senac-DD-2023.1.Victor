package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.google.protobuf.TextFormat.ParseException;

import Controller.ClienteController;
import Controller.TelefoneController;
import Model.vo.telefonia.Telefone;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroTelefone {

	private JFrame frmNovoTelefone;
	private JFormattedTextField txtTelefoneFixo;
	private JFormattedTextField txtTelefoneMovel;

	private JLabel lblTipo;
	private JLabel lblNumero;
	private JLabel lblCliente;

	private JComboBox cbClientes;
	private JRadioButton rbMovel;
	private JRadioButton rbFixo;

	private JButton btnSalvar;

	private MaskFormatter mascaraTelefoneFixo;
	private MaskFormatter mascaraTelefoneMovel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroTelefone window = new TelaCadastroTelefone();
					window.frmNovoTelefone.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroTelefone() throws ParseException {
		initialize();
		esconderTodosOsComponentes();
	}

	private void esconderTodosOsComponentes() {

		lblNumero.setVisible(false);
		txtTelefoneFixo.setVisible(false);
		lblCliente.setVisible(false);
		cbClientes.setVisible(false);
		btnSalvar.setVisible(false);
	}

	private void mostrarComponentesComuns() {

		lblNumero.setVisible(true);
		txtTelefoneFixo.setVisible(true);
		lblCliente.setVisible(true);
		cbClientes.setVisible(true);
		btnSalvar.setVisible(true);
	}

	private void initialize() throws ParseException {
		frmNovoTelefone = new JFrame();
		frmNovoTelefone.setTitle("Novo telefone");
		frmNovoTelefone.setBounds(100, 100, 450, 300);
		frmNovoTelefone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoTelefone.getContentPane().setLayout(null);

		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(21, 20, 45, 13);
		frmNovoTelefone.getContentPane().add(lblTipo);

		lblNumero = new JLabel("Número");
		lblNumero.setBounds(21, 77, 45, 13);
		frmNovoTelefone.getContentPane().add(lblNumero);

		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(21, 136, 45, 13);
		frmNovoTelefone.getContentPane().add(lblCliente);

//		mascaraTelefoneFixo = new MaskFormatter("(##)####-####");
//	    mascaraTelefoneMovel = new MaskFormatter("(##)9####-####");
		mascaraTelefoneFixo.setValueContainsLiteralCharacters(false);
		mascaraTelefoneMovel.setValueContainsLiteralCharacters(false);

		rbMovel = new JRadioButton("Móvel");
		rbMovel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarComponentesComuns();
				txtTelefoneMovel.setVisible(false);
				txtTelefoneFixo.setVisible(true);
			}
		});
		rbMovel.setBounds(144, 16, 68, 21);
		frmNovoTelefone.getContentPane().add(rbMovel);

		rbFixo = new JRadioButton("Fixo");
		rbFixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNumero.setVisible(true);
				txtTelefoneFixo.setVisible(true);
				lblCliente.setVisible(true);
				cbClientes.setVisible(true);
				btnSalvar.setVisible(true);
			}
		});
		rbFixo.setBounds(72, 16, 54, 21);
		frmNovoTelefone.getContentPane().add(rbFixo);

		txtTelefoneFixo = new JFormattedTextField(mascaraTelefoneFixo);
		txtTelefoneFixo.setBounds(72, 74, 292, 19);
		frmNovoTelefone.getContentPane().add(txtTelefoneFixo);

		txtTelefoneMovel = new JFormattedTextField(mascaraTelefoneMovel);
		txtTelefoneMovel.setBounds(72, 74, 292, 19);
		txtTelefoneMovel.setVisible(false);
		frmNovoTelefone.getContentPane().add(txtTelefoneMovel);

		ClienteController clienteController = new ClienteController();
		cbClientes = new JComboBox(clienteController.consultarTodos().toArray());
		cbClientes.setBounds(72, 132, 292, 21);
		frmNovoTelefone.getContentPane().add(cbClientes);
		cbClientes.setSelectedItem(null); // inicia sem preenchimento;

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnSalvar.setBounds(72, 201, 292, 21);
		frmNovoTelefone.getContentPane().add(btnSalvar);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbFixo);
		grupo.add(rbMovel);
	}
}
