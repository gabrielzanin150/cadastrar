package cadastro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;
import java.awt.Font;

public class view extends JFrame { // criacao da janela principal da aplicacao
	
	

	private JPanel contentPane;
	private JTextField jTxtnome;
	private JFormattedTextField jTxtnascimento;
	private JFormattedTextField jTxtnota;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {    // funcao para abrir a janela
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view() {
		int vetor[] = new int[10]; // vetor onde armazena a quantidade de vezes que uma nota aparece no arquivo
		int i=0;       
		for(i=0; i<9; i++)
			vetor[i]=0;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCadastrar = new JButton("Cadastrar"); // criaçao do botao cadastrar
		btnCadastrar.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 14));
		btnCadastrar.setBounds(10, 147, 171, 31);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Aluno aluno = new Aluno();    // cria um novo cadastro mas ainda nao salva
				aluno.setNome(jTxtnome.getText());
				aluno.setNascimento(jTxtnascimento.getText());
				aluno.setNotafinal(jTxtnota.getText());
			    
				
				JOptionPane.showMessageDialog(null,aluno.salvar()); // salva os dados em um arquivo txt com todos os dados e outro apenas com as notas que irão para o vetor
				
				jTxtnome.setText("");   // apaga os campos preenchidos logo após clicar em cadastrar
				jTxtnascimento.setText("");
				jTxtnota.setText("");
				
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCadastrar);
		
		JButton btnVisualizar = new JButton("Visualizar");   //  criação do botao visualizar
		btnVisualizar.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 14));
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotasVisualizacao notas = new NotasVisualizacao();
				notas.setVisible(true);
				
			}
			
		});
		btnVisualizar.setBounds(10, 189, 171, 31);
		contentPane.add(btnVisualizar);
		
		jTxtnome = new JTextField();
		jTxtnome.addKeyListener(new KeyAdapter() { // formatação do campo nome para aceitar apenas letras
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
                if(Character.isDigit(vchar) && !e.isAltDown()) {
                    e.consume();
                }
			}
		});
		jTxtnome.setBounds(10, 33, 171, 20);
		contentPane.add(jTxtnome);
		jTxtnome.setColumns(10);
		try {
            MaskFormatter mascara = new MaskFormatter("##/##/####"); // formatação do campo data de nascimento
            jTxtnascimento = new JFormattedTextField(mascara);
            mascara.setPlaceholderCharacter('_');
            } catch (Exception e) {
            e.printStackTrace();
            }   
		
		jTxtnascimento.setColumns(10);
		jTxtnascimento.setBounds(10, 77, 171, 20);
		contentPane.add(jTxtnascimento);
		
		jTxtnota = new JFormattedTextField();
		jTxtnota.setForeground(SystemColor.windowBorder);
		jTxtnota.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) { // formatação do campo notas
				char vchar = evt.getKeyChar();
                if(Character.isLetter(vchar) && !evt.isAltDown()) {
                    evt.consume();
                    try {
                        MaskFormatter mask = new MaskFormatter("##.##");
                        jTxtnota = new JFormattedTextField(mask);
                        } catch (Exception e) {
                        e.printStackTrace();
                        }
                    jTxtnota.setText(" ");
                }
			}
		});
		jTxtnota.setColumns(10);
		jTxtnota.setBounds(10, 116, 171, 20);
		contentPane.add(jTxtnota);
		
		JLabel lblNome = new JLabel("Nome");     // daqui até o final do código é reposável por criar os campos e nomes que aparecem na tela principal
		lblNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNome.setBounds(78, 21, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblNascimento = new JLabel("Nascimento");
		lblNascimento.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNascimento.setBounds(68, 64, 67, 14);
		contentPane.add(lblNascimento);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNota.setBounds(78, 101, 46, 14);
		contentPane.add(lblNota);
		
		JLabel lblCadastrador = new JLabel("Cadastrador");
		lblCadastrador.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 20));
		lblCadastrador.setBounds(255, 33, 156, 45);
		lblCadastrador.setForeground(SystemColor.text);
		contentPane.add(lblCadastrador);
		
		JLabel lblDe = new JLabel("de");
		lblDe.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 20));
		lblDe.setBackground(SystemColor.text);
		lblDe.setBounds(292, 77, 46, 14);
		lblDe.setForeground(SystemColor.text);
		contentPane.add(lblDe);
		
		JLabel lblAlunos = new JLabel("Alunos");
		lblAlunos.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 20));
		lblAlunos.setBounds(265, 101, 109, 35);
		lblAlunos.setForeground(SystemColor.text);
		contentPane.add(lblAlunos);
	}
}
