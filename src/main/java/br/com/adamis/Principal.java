/**
 * @autor Adamis.Rocha
 * @since 1.0, 30 de ago de 2017
 */
package br.com.adamis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import br.com.adamis.resource.Resouces;


/**
 * @author Adamis.Rocha
 * @since 1.0, 30 de ago de 2017
 */
public class Principal extends JFrame {

	private static final long serialVersionUID = 9053400699092736640L;
	private JTextArea tAInput,tAOutput;
	private JButton btnBack;
	private JRadioButton rdbtnAspasSimples,rdbtnAspasDuplas,rdbtnStringbuilder,rdbtnPrePos;
	private JButton btnCopySTRING;
	private JButton btnCopySQL;
	private JButton btnClear;
	private JScrollPane scrollPaneSQL;
	private JScrollPane scrollPaneString;
	private JLabel lblSql;
	private JLabel lblString; 
	private JButton btnRun;
	private JTextField txtSB;
	private JLabel lblNameSB;
	private JLabel lblPr;
	private int height = 500;
	private int width = 500;
	private JButton btnPretty;
	private JLabel lblPos;
	private JTextField txtPre;
	private JTextField txtPos;

	public void visible(boolean control){
		this.setVisible(control);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public Principal() {

		setExtendedState(Frame.MAXIMIZED_BOTH);

		setResizable(true);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		width = gd.getDisplayMode().getWidth();
		height = gd.getDisplayMode().getHeight();		


		addComponentListener(new ComponentListener() {

			public void componentResized(ComponentEvent e) {
				height = getHeight();
				width = getWidth();				
				if(rdbtnAspasSimples != null) {

					int percentW = (width/100);
					int percentH = (height/100);

					rdbtnAspasSimples .setBounds(percentW*10, 21, 143, 23);			    	
					rdbtnAspasDuplas  .setBounds(percentW*40, 21, 143, 23);
					rdbtnStringbuilder.setBounds(percentW*70, 21, 155, 23);
					rdbtnPrePos		  .setBounds(percentW*90, 21, 155, 23);

					//NAME STRINGBUILDER
					lblNameSB.setBounds(percentW*70, 55, 58, 25);
					txtSB.setBounds(percentW*75, 55, 300, 25);

					//PANEL SQL
					lblSql.setBounds(percentW*18, 79, 300, 24);
					scrollPaneSQL.setBounds(percentW*10, 115, percentW*40, percentH*70);
					btnCopySQL.setBounds(percentW*26, percentH*92, 64, 23);

					//PANEL STRING
					lblString.setBounds(percentW*70, 79, 300, 24);
					scrollPaneString.setBounds(percentW*60, 115, percentW*40, percentH*70);
					btnCopySTRING.setBounds(percentW*78, percentH*92, 64, 23);


					//PRE/POS
					lblPr.setBounds(percentW*10, 55, 58, 25);
					txtPre.setBounds(percentW*13, 55, percentW*37, 25);

					lblPos.setBounds(percentW*60, 55, 300, 24);
					txtPos.setBounds(percentW*63, 55, percentW*37, 25);

					btnPretty.setBounds(percentW*51, 133, 93, 41);
					btnRun   .setBounds(percentW*51, 187, 93, 32);
					btnBack  .setBounds(percentW*51, 241, 93, 32);
					btnClear .setBounds(percentW*51, 293, 93, 41);

					getContentPane().repaint();
					getContentPane().revalidate();

				}
			}

			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}


		});

		setIconImage(Resouces.copy());
		setTitle("StringFicador Version: "+Resouces.version);
		getContentPane().setBackground(Color.WHITE);		
		setBounds(100, 100, 1024, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);


		scrollPaneSQL = new JScrollPane();
		scrollPaneSQL.setBackground(Color.LIGHT_GRAY);
		scrollPaneSQL.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSQL.setBounds(36, 114, 300, 350);
		getContentPane().add(scrollPaneSQL);

		tAInput = new JTextArea();
		scrollPaneSQL.setViewportView(tAInput);

		scrollPaneString = new JScrollPane();
		scrollPaneString.setBackground(Color.LIGHT_GRAY);
		scrollPaneString.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneString.setBounds(501, 114, 300, 350);
		getContentPane().add(scrollPaneString);

		tAOutput = new JTextArea();
		scrollPaneString.setViewportView(tAOutput);

		btnRun = new JButton("");		
		btnRun.setIcon(Resouces.front());
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder stringBuilder = new StringBuilder(); 
				int cont = 0;
				String aspas = "";



				if(rdbtnAspasSimples.isSelected()) {
					aspas = "'";

					for (String line : tAInput.getText().split("\\n")){

						line = line.replace("'", "\\'");

						if(line.length()> 0){
							if(cont == 0){
								stringBuilder.append(aspas+" "+line+" "+aspas+"\n"); 
							}else{
								stringBuilder.append("+ "+aspas+" "+line+" "+aspas+"\n");
							}
						}
						cont++;
					}

					stringBuilder.append(";");
				}else if(rdbtnAspasDuplas.isSelected()) {
					aspas = "\"";

					for (String line : tAInput.getText().split("\\n")){
						line = line.replace("\"", "\\\"");											
						if(line.length()> 0){

							if(cont == 0){
								stringBuilder.append(aspas+" "+line+" "+aspas+"\n"); 
							}else{								
								stringBuilder.append("+ "+aspas+" "+line+" "+aspas+"\n");								
							}
						}else {
							if(cont == 0){
								stringBuilder.append(aspas+" "+aspas+"\n");
							}else {
								stringBuilder.append("+ "+aspas+" "+aspas+"\n");
							}
						}
						cont++;
					}
					stringBuilder.append(";");
				}else if(rdbtnStringbuilder.isSelected()) {

					aspas = "\"";					
					String nameSB = txtSB.getText().equals("")?"sb":txtSB.getText();

					stringBuilder.append("StringBuilder "+nameSB+" = new StringBuilder(); \n");

					for (String line : tAInput.getText().split("\\n")){
						line = line.replace("\"", "\\\"");
						stringBuilder.append(nameSB+".append("+aspas+" "+line+" "+aspas+");\n");						
						cont++;						
					}

				}else if(rdbtnPrePos.isSelected()) {

					for (String line : tAInput.getText().split("\\n")){
						stringBuilder.append(txtPre.getText()+line+txtPos.getText()+"\n");
					}

				}

				tAOutput.setText(stringBuilder.toString());	 
			}
		});

		btnPretty = new JButton("");
		btnPretty.setIcon(Resouces.magic());
		btnPretty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String formattedSQL = "";
				//TODO
				try {
					formattedSQL = org.hibernate.jdbc.util.FormatStyle.BASIC.getFormatter().format(tAInput.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(getContentPane(),""+e2.getMessage());
					e2.printStackTrace();					  
				}

				tAInput.setText("");
				if(formattedSQL.startsWith("\n")) {
					tAInput.setText(formattedSQL.substring(1, formattedSQL.length()).trim());	
				}else {
					tAInput.setText(formattedSQL.trim());
				}

			}
		});
		btnPretty.setBounds(371, 164, 93, 41);
		getContentPane().add(btnPretty);
		btnRun.setBounds(371, 221, 93, 32);
		getContentPane().add(btnRun);

		btnBack = new JButton("");		
		btnBack.setIcon(Resouces.back());
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String retorno = "";

				if(rdbtnAspasSimples.isSelected()){
					retorno = processaAspas("'");
				}else if(rdbtnAspasDuplas.isSelected()){					
					retorno = processaAspas("\"");
				}else if(rdbtnStringbuilder.isSelected()) {
					retorno = processaStringBuilder();
				}


				tAInput.setText(retorno);				

			}


		});
		btnBack.setBounds(371, 275, 93, 32);
		getContentPane().add(btnBack);

		@SuppressWarnings("unused")
		ButtonGroup bGSTRINGTOSQL = new ButtonGroup();

		//SQL TO STRING
		rdbtnAspasSimples = new JRadioButton("ASPAS SIMPLES ( ' )");

		rdbtnAspasSimples.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSB.setVisible(false);
				lblNameSB.setVisible(false);
				txtSB.setText("");
				lblPr.setVisible(false);
				txtPre.setVisible(false);
				lblPos.setVisible(false);
				txtPos.setVisible(false);
				btnPretty.setVisible(true);
			}
		});
		rdbtnAspasSimples.setBackground(Color.WHITE);
		rdbtnAspasSimples.setBounds(99, 21, 143, 23);
		getContentPane().add(rdbtnAspasSimples);

		rdbtnAspasDuplas = new JRadioButton("ASPAS DUPLAS ( \" )");
		rdbtnAspasDuplas.setSelected(true);
		rdbtnAspasDuplas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSB.setVisible(false);
				lblNameSB.setVisible(false);
				txtSB.setText("");
				lblPr.setVisible(false);
				txtPre.setVisible(false);
				lblPos.setVisible(false);
				txtPos.setVisible(false);
				btnPretty.setVisible(true);
			}
		});
		rdbtnAspasDuplas.setBackground(Color.WHITE);
		rdbtnAspasDuplas.setBounds(341, 21, 143, 23);
		getContentPane().add(rdbtnAspasDuplas);

		rdbtnStringbuilder = new JRadioButton("StringBuilder");
		rdbtnStringbuilder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSB.setVisible(true);
				lblNameSB.setVisible(true);
				lblPr.setVisible(false);
				txtPre.setVisible(false);
				lblPos.setVisible(false);
				txtPos.setVisible(false);
				btnPretty.setVisible(true);
			}
		});
		rdbtnStringbuilder.setBackground(Color.WHITE);		
		rdbtnStringbuilder.setBounds(583, 21, 155, 23);
		getContentPane().add(rdbtnStringbuilder);

		rdbtnPrePos = new JRadioButton("Pre/Pos");
		rdbtnPrePos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPr.setVisible(true);
				txtPre.setVisible(true);
				lblPos.setVisible(true);
				txtPos.setVisible(true);

				txtSB.setVisible(false);
				lblNameSB.setVisible(false);
				btnPretty.setVisible(false);
			}
		});
		rdbtnPrePos.setBackground(Color.WHITE);
		rdbtnPrePos.setBounds(764, 21, 155, 23);
		getContentPane().add(rdbtnPrePos);

		ButtonGroup bGSQLTOTRING = new ButtonGroup();
		bGSQLTOTRING.add(rdbtnAspasSimples);
		bGSQLTOTRING.add(rdbtnAspasDuplas);
		bGSQLTOTRING.add(rdbtnStringbuilder);
		bGSQLTOTRING.add(rdbtnPrePos);

		btnCopySQL = new JButton("Copy");
		btnCopySQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringSelection stringSelection = new StringSelection(tAInput.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
		});




		btnCopySQL.setBounds(154, 515, 64, 23);
		getContentPane().add(btnCopySQL);

		btnCopySTRING = new JButton("Copy");
		btnCopySTRING.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(tAOutput.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
		});
		btnCopySTRING.setBounds(619, 515, 64, 23);
		getContentPane().add(btnCopySTRING);

		btnClear = new JButton("");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAInput.setText("");
				tAOutput.setText("");
			}
		});
		btnClear.setIcon(Resouces.trash());
		btnClear.setBounds(371, 327, 93, 41);
		getContentPane().add(btnClear);

		lblSql = new JLabel("SQL");
		lblSql.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSql.setHorizontalAlignment(SwingConstants.CENTER);
		lblSql.setBounds(36, 79, 300, 24);
		getContentPane().add(lblSql);

		lblString = new JLabel("STRING");
		lblString.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblString.setHorizontalAlignment(SwingConstants.CENTER);
		lblString.setBounds(501, 79, 300, 24);
		getContentPane().add(lblString);

		txtSB = new JTextField();
		txtSB.setVisible(false);
		txtSB.setBounds(501, 51, 300, 25);
		getContentPane().add(txtSB);
		txtSB.setColumns(10);

		lblNameSB = new JLabel("Nome");
		lblNameSB.setVisible(false);
		lblNameSB.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNameSB.setBounds(443, 51, 58, 25);
		getContentPane().add(lblNameSB);

		lblPr = new JLabel("Pr\u00E9");
		lblPr.setVisible(false);
		lblPr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPr.setBounds(36, 475, 46, 25);
		getContentPane().add(lblPr);

		lblPos = new JLabel("Pos");
		lblPos.setVisible(false);
		lblPos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPos.setBounds(501, 475, 46, 25);
		getContentPane().add(lblPos);

		txtPre = new JTextField();
		txtPre.setVisible(false);
		txtPre.setBounds(70, 475, 266, 25);
		getContentPane().add(txtPre);
		txtPre.setColumns(10);

		txtPos = new JTextField();
		txtPos.setVisible(false);
		txtPos.setColumns(10);
		txtPos.setBounds(531, 475, 270, 25);
		getContentPane().add(txtPos);


	}


	protected String processaStringBuilder() {
		String replaced = tAOutput.getText().replace(";", "");

		String finished = "";

		String sbName = "";

		for (String line : replaced.split("\\n")){
			int coment  = line.indexOf("//");

			if(coment > -1){
				finished += line.replace(line.subSequence(coment, line.length()), "")+"\n";
			}else{
				int sBIdentif  = line.indexOf("StringBuilder ");				

				if(sBIdentif == -1) {

					line = line.replace(sbName+".append(\"", "");										
					line = line.replace("\");", "");
					line = line.replace("\")", "");					
					finished += line;					
					finished +="\n";
				}else{					
					if(txtSB.getText().equals("")){
						int indexOf = line.indexOf(" ",sBIdentif+14);					
						sbName = line.substring(sBIdentif+14, indexOf);
					}else {
						sbName = txtSB.getText();
					}
				}
			}

		}

		return finished;
	}

	public String processaAspas(String aspas) {
		String replaced = tAOutput.getText()
				.replace("+  "+aspas, "")
				.replace("+ "+aspas, "")						
				.replace("+"+aspas, "")

				.replace(aspas+"+", "")
				.replace(aspas+" +", "")
				.replace(aspas+"  +", "")

				.replace(aspas, "")
				.replace(";", "")
				;

		String finished = "";

		for (String line : replaced.split("\\n")){
			int coment  = line.indexOf("//");

			if(coment > -1){
				finished += line.replace(line.subSequence(coment, line.length()), "");
			}else{
				finished += line;
			}
			finished +="\n";
		}
		return finished;
	}
}
