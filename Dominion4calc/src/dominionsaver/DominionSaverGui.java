package dominionsaver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class DominionSaverGui extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DominionSaverGui frame = new DominionSaverGui();
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
	public DominionSaverGui() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		setBounds(600, 0, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout x = new GridBagLayout();
		x.columnWeights = new double[]{0.33,0.33};
		x.rowWeights = new double[]{0.70,0.10};
		contentPane.setLayout(x);
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		list = new JList();
		list.setFont(new Font("Lucida Sans", Font.PLAIN, 9));
		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setViewportView(list);
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill = GridBagConstraints.BOTH;
		contentPane.add(listScrollPane,constraints );
		JScrollPane logScrollPane = new JScrollPane();
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill = GridBagConstraints.BOTH;
		textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		textArea.setPreferredSize(new Dimension(200, 300));
		logScrollPane.setViewportView(textArea);
		contentPane.add(logScrollPane,constraints);
		
		lblStatus = new JLabel("X");
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=2;
		constraints.fill=GridBagConstraints.BOTH;
		
		contentPane.add(lblStatus, constraints);
		list.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					synchronized(DominionSaverGui.this) {
						String selectedValue = String.valueOf(list.getSelectedValue());
						int x = JOptionPane.showConfirmDialog(DominionSaverGui.this,"Confirm restoring " + list.getSelectedValue() + "?", "Dom4 Restore", JOptionPane.YES_NO_OPTION);
						if(x == JOptionPane.YES_OPTION) {
							DominionSaver.restore(selectedValue);
						}
					}
				} else if (e.getKeyChar() == KeyEvent.VK_D) {
					synchronized(DominionSaverGui.this) {
						String selectedValue = String.valueOf(list.getSelectedValue());
						int x = JOptionPane.showConfirmDialog(DominionSaverGui.this,"Confirm deletion of " + list.getSelectedValue() + "?", "Dom4 Restore", JOptionPane.YES_NO_OPTION);
						if(x == JOptionPane.YES_OPTION) {
							DominionSaver.delete(selectedValue);
						}
					}
				}
				
				
			}});
	
	}
	public synchronized void resetList() {
		list.setListData(new Object[0]);
		list.repaint();
	}
	public synchronized void setList(Object[] data) {
		list.setListData(data);
		list.repaint();
	}
	private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	private JLabel lblStatus;
	public synchronized void message(String string) {
		textArea.append("\n");
		textArea.append(format.format(new Date()));
		textArea.append(":");
		textArea.append(string);
		if(textArea.getText().length() > 20000) {
			textArea.setText(textArea.getText().substring(0,20000));
		}
		textArea.setCaretPosition(textArea.getText().length());
		textArea.repaint();
		
	}
	public void setStatus ( String status ) {
		lblStatus.setText(status);
		lblStatus.repaint();
	}
}
