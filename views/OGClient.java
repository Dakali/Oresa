package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import domain.Connecter;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.List;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.JTable;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class OGClient {
	
	Connecter con = new Connecter();
	Statement stm;
	ResultSet Rs;
	DefaultTableModel model = new DefaultTableModel();
	
	
	
	private JFrame frmGestionDesClients;
	private JTable table;
	private JTextField tFId;

	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OGClient window = new OGClient();
					window.frmGestionDesClients.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OGClient() {
		initialize();
		
	}


	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionDesClients = new JFrame();
		frmGestionDesClients.getContentPane().setBackground(Color.WHITE);
		frmGestionDesClients.setTitle("Gestion des clients");
		frmGestionDesClients.setBounds(100, 100, 870, 458);
		frmGestionDesClients.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDesClients.getContentPane().setLayout(null);
		
		JPanel panGauche = new JPanel();
		panGauche.setBorder(new LineBorder(new Color(51, 153, 51)));
		panGauche.setBackground(Color.WHITE);
		panGauche.setBounds(10, 41, 334, 262);
		frmGestionDesClients.getContentPane().add(panGauche);
		panGauche.setLayout(null);
		
		Label lNom = new Label("Nom du client :");
		lNom.setFont(new Font("Dialog", Font.PLAIN, 18));
		lNom.setBounds(10, 10, 133, 30);
		panGauche.add(lNom);
		
		Label lNCni = new Label("Num\u00E9ro CNI :");
		lNCni.setFont(new Font("Dialog", Font.PLAIN, 18));
		lNCni.setBounds(10, 46, 112, 30);
		panGauche.add(lNCni);
		
		Label lTel = new Label("T\u00E9l\u00E9phone :");
		lTel.setFont(new Font("Dialog", Font.PLAIN, 18));
		lTel.setBounds(10, 82, 100, 30);
		panGauche.add(lTel);
		
		Label lLocalation = new Label("Location :");
		lLocalation.setFont(new Font("Dialog", Font.PLAIN, 18));
		lLocalation.setBounds(10, 118, 82, 30);
		panGauche.add(lLocalation);
		
		Label lDEntree = new Label("Date d'entr\u00E9e :");
		lDEntree.setFont(new Font("Dialog", Font.PLAIN, 18));
		lDEntree.setBounds(10, 154, 121, 30);
		panGauche.add(lDEntree);
		
		Label lDSortie = new Label("Date de sortie : ");
		lDSortie.setFont(new Font("Dialog", Font.PLAIN, 18));
		lDSortie.setBounds(10, 190, 126, 30);
		panGauche.add(lDSortie);
		
		TextField tFNom = new TextField();
		tFNom.setBounds(169, 18, 150, 22);
		panGauche.add(tFNom);
		
		TextField tFNCni = new TextField();
		tFNCni.setBounds(169, 54, 150, 22);
		panGauche.add(tFNCni);
		
		TextField tFTel = new TextField();
		tFTel.setBounds(169, 90, 150, 22);
		panGauche.add(tFTel);
		
		TextField tFDEntree = new TextField();
		tFDEntree.setBounds(169, 162, 150, 22);
		panGauche.add(tFDEntree);
		
		TextField tFDSortie = new TextField();
		tFDSortie.setBounds(169, 198, 150, 22);
		panGauche.add(tFDSortie);
		
		JComboBox cbLocation = new JComboBox();
		cbLocation.setModel(new DefaultComboBoxModel(new String[] {"Appartement RDC", "Appartement R+1", "Studio RDC", "Chambre RDC"}));
		cbLocation.setBounds(169, 128, 150, 20);
		panGauche.add(cbLocation);
		
		Label lMtnt = new Label("Versement :");
		lMtnt.setFont(new Font("Dialog", Font.PLAIN, 18));
		lMtnt.setBounds(10, 222, 126, 30);
		panGauche.add(lMtnt);
		
		TextField tFMtnt = new TextField();
		tFMtnt.setBounds(169, 226, 150, 22);
		panGauche.add(tFMtnt);
		
		tFId = new JTextField();
		tFId.setVisible(false);
		tFId.setEditable(false);
		tFId.setBounds(205, -8, 86, 20);
		panGauche.add(tFId);
		tFId.setColumns(10);
		
		Button bAjout = new Button("Ajouter");
		bAjout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				String nom = tFNom.getText();
				String cni = tFNCni.getText();
				String tel = tFTel.getText();
				String dEntree = tFDEntree.getText();
				String dSortie = tFDSortie.getText();
				String mtnt = tFMtnt.getText();
				String local = cbLocation.getSelectedItem().toString();
				
				String req = "INSERT INTO client(Nom,"
						+ "DateEntree,"
						+ "DateSortie,"
						+ "NCni"
						+ ",Montant,"
						+ "Tel,"
						+ "Location) VALUES('"+nom+"','"+dEntree+"','"+dSortie+"','"+cni+"','"+mtnt+"','"+tel+"','"+local+"')";
				
				try { 
					stm=con.recupCon().createStatement();
				//stm.executeQuery(req);
				stm.executeUpdate(req);
				JOptionPane.showMessageDialog(null,"Client Ajouté!!");
				tFNom.setText("");
				tFNCni.setText("");
				tFTel.setText("");
				tFDEntree.setText("");
				tFDSortie.setText("");
				tFMtnt.setText("");
				cbLocation.setSelectedItem(1);
				}
				catch(SQLException e){System.err.println(e);
				JOptionPane.showMessageDialog(null,"Une erreur est survenue, veuillez réessayer!! ");
				}
			}
		});
		bAjout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bAjout.setBounds(10, 309, 70, 22);
		frmGestionDesClients.getContentPane().add(bAjout);
		
		Button bModifier = new Button("Supprimer");
		bModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
		             if(JOptionPane.showConfirmDialog(null,"Vous êtes sur le point de supprimer une ligne. En êtes vous sur?"
		                     ,"Suppression Client",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
		         
		            if(tFId.getText().length() != 0){
		        stm.executeUpdate("Delete From client where idClient = "+tFId.getText());
		        JOptionPane.showMessageDialog(null,"Suppression réussie!");
		            }
		            else { JOptionPane.showMessageDialog(null,"ID");}
		        
		        }catch (Exception e){JOptionPane.showMessageDialog(null,"Erreur de suppression. Veuillez réessayer \n"+e.getMessage());} 
		       
			}
		});
		bModifier.setBounds(110, 309, 70, 22);
		frmGestionDesClients.getContentPane().add(bModifier);
		
		Button bSupp = new Button("Modifier");
		bSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { 
		            if (JOptionPane.showConfirmDialog (null,"Confirmer la modification","Modification client",
		                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

		                stm.executeUpdate("UPDATE client SET  Tel='"+tFTel.getText()+"',DateSortie='"+tFDSortie.getText()+"',DateEntree='"+tFDEntree.getText()+"', Nom='"+tFNom.getText()+"',NCni='"+tFNCni.getText()+
		                        "',Location='"+cbLocation.getSelectedItem().toString()+"',Montant='"+tFMtnt.getText()+
		                        "' WHERE idClient= "+tFId.getText());
		                //afficher ();
		                JOptionPane.showMessageDialog(null,"Modification réussie!");
		            } 
		        } catch (Exception e){JOptionPane.showMessageDialog(null,"Erreur de modification");
		        System.err.println(e);
		        }
			}
		});
		bSupp.setBounds(210, 309, 70, 22);
		frmGestionDesClients.getContentPane().add(bSupp);
		
		Button bUpdate = new Button("Actualiser");
		bUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				model.setRowCount(0);
				try {
					stm = con.recupCon().createStatement();
					Rs = stm.executeQuery("Select * From client");
					while(Rs.next()) {
						model.addRow(new Object[] {
								Rs.getString("idClient"),
								Rs.getString("Nom"),
								Rs.getString("Location"),
								Rs.getString("DateEntree"),
								Rs.getString("DateSortie"),
								});
						
					}
					JOptionPane.showMessageDialog(null, "Tableau actualisé!");
				}
				catch(Exception ex) {
					System.err.println(ex);
					JOptionPane.showMessageDialog(null, "Erreur d'actualisation");
				}
				
				table.setModel(model);
			}
		});
		bUpdate.setBounds(774, 309, 70, 22);
		frmGestionDesClients.getContentPane().add(bUpdate);
		
		
		TextField tFRch = new TextField();
		tFRch.setBounds(370, 309, 170, 22);
		frmGestionDesClients.getContentPane().add(tFRch);
		
		
		
		
		
		Button bRch = new Button("Recherche");
		bRch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           model.setRowCount(0);// pour vider la list des client
			      {
			       Rs = stm.executeQuery("Select * From client WHERE Nom = '"+tFRch.getText()+"'");
			       }while (Rs.next()){
			       
			       Object [] client ={Rs.getInt(1),Rs.getString(2),Rs.getString(8),Rs.getString(3),Rs.getString(4)};
			     model.addRow(client);
			       } if (model.getRowCount () == 0){JOptionPane.showMessageDialog(null,"Aucun client");
			       
			       } else{ int i=0;
			       try {
						tFId.setText(model.getValueAt(i, 0).toString());
						tFNom.setText(model.getValueAt(i, 1).toString());
						tFDEntree.setText(model.getValueAt(i, 3).toString());
						tFDSortie.setText(model.getValueAt(i, 4).toString());
						cbLocation.setSelectedItem(model.getValueAt(i, 2).toString());
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Déplacement impossible");
					}
			       }
			       
			       }catch (Exception ex) { System.err.println(ex);
			       JOptionPane.showMessageDialog(null,ex.getMessage());
			       }
			}
		});
		bRch.setBounds(560, 309, 70, 22);
		frmGestionDesClients.getContentPane().add(bRch);
		
		
		
		Panel panel = new Panel();
		panel.setBounds(370, 41, 474, 240);
		frmGestionDesClients.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(51, 153, 0)));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
				int i = table.getSelectedRow();
						try {
							tFId.setText(model.getValueAt(i, 0).toString());
							tFNom.setText(model.getValueAt(i, 1).toString());
							tFDEntree.setText(model.getValueAt(i, 3).toString());
							tFDSortie.setText(model.getValueAt(i, 4).toString());
							cbLocation.setSelectedItem(model.getValueAt(i, 2).toString());
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null, "Déplacement impossible");
						}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Erreur déplacement "+e.getLocalizedMessage());
			}
				}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"N\u00B0", "Nom du client", "Location", "Date d'entr\u00E9e", "Date de Sortie"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(32);
		
		
		model.addColumn("idClient");
		model.addColumn("Nom");
		model.addColumn("Location");
		model.addColumn("DateEntree");
		model.addColumn("DateSortie");
		
		
		try {
			stm = con.recupCon().createStatement();
			Rs = stm.executeQuery("Select * From client");
			while(Rs.next()) {
				model.addRow(new Object[] {
						Rs.getString("idClient"),
						Rs.getString("Nom"),
						Rs.getString("Location"),
						Rs.getString("DateEntree"),
						Rs.getString("DateSortie"),
						});
			}
		}
		catch(Exception e) {
			System.err.println(e);
		}
		
		table.setModel(model);
		
		
		
	}
}
