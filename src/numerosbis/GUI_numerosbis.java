package numerosbis;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUI_numerosbis extends JFrame implements Runnable{

	private JPanel contentPane;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			GUI_numerosbis frame = new GUI_numerosbis();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * Create the frame.
	 */
	public GUI_numerosbis() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IP", "State"
			}
		));
		scrollPane.setViewportView(table);
	}
	
	public static void addUser(InetAddress addr,String state){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{addr.toString(), state});
	}

	public static void removeUser(InetAddress addr) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = model.getRowCount() - 1; i >= 0; --i) {
			if (model.getValueAt(i, 0).equals(addr.toString())) {
				model.removeRow(i);
            }
		}
	}
	
	public static void changeState(InetAddress addr,String state) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int trouve = 0;
		for (int i = model.getRowCount() - 1; i >= 0; --i) {
			if (model.getValueAt(i, 0).equals(addr.toString())) {
				model.setValueAt(state, i, 1);
				trouve = 1;
            }
		}
		//Si on a pas trouvé l'ip on l'ajoute à la table
		if(trouve ==  0) {
			model.addRow(new Object[]{addr.toString(), state});
		}
	}
}
