package view;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Statement;

import controller.Controller;
import model.Person;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class BT5_3_Main {
	String databaseName ="informationperson";
	String url;
	JPanel pnPreview;
	JLabel lbPreview;
	private JFrame frmThngTinLin;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtUri;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BT5_3_Main window = new BT5_3_Main();
					window.frmThngTinLin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BT5_3_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThngTinLin = new JFrame();
		frmThngTinLin.getContentPane().setBackground(Color.WHITE);
		frmThngTinLin.setTitle("Thông tin liên hệ");
		frmThngTinLin.setBounds(100, 100, 620, 334);
		frmThngTinLin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThngTinLin.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "input th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(10, 32, 320, 263);
		frmThngTinLin.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(10, 62, 46, 14);
		panel.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setBounds(104, 59, 206, 20);
		panel.add(txtName);
		txtName.setColumns(10);

		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setBounds(10, 100, 46, 14);
		panel.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(104, 97, 130, 20);
		panel.add(txtPhone);

		JLabel lblAvatar = new JLabel("Avatar :");
		lblAvatar.setBounds(10, 137, 46, 14);
		panel.add(lblAvatar);

		txtUri = new JTextField();
		txtUri.setEnabled(false);
		txtUri.setColumns(10);
		txtUri.setBounds(10, 151, 252, 20);
		panel.add(txtUri);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person person = new Person(txtName.getText(), txtPhone.getText(), txtUri.getText());
				Controller controller = new Controller(databaseName);
				String sql ="insert into informationUser (name,phone,image) values ('"+person.getName()+"','"+person.getPhone()+"','"+person.getImage()+"')";
				controller.CRUDdatabase(sql, frmThngTinLin,1);
		  
		    	
		    };	
		});
		btnSubmit.setBackground(UIManager.getColor("Button.light"));
		btnSubmit.setBounds(104, 216, 89, 23);
		panel.add(btnSubmit);

		JButton btnChooser = new JButton("....");
		btnChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser  chooser = new JFileChooser();
				int result = chooser.showOpenDialog(frmThngTinLin);
				if(result==chooser.APPROVE_OPTION) {
					url = chooser.getSelectedFile().getPath();
					txtUri.setText(url);
					ImageIcon icon = new ImageIcon(url);
					lbPreview.setIcon(icon);
				}


			}
		});
		btnChooser.setBackground(SystemColor.activeCaption);
		btnChooser.setBounds(272, 150, 38, 23);
		panel.add(btnChooser);

		pnPreview = new JPanel();
		pnPreview.setBackground(Color.WHITE);
		pnPreview.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "preview", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnPreview.setBounds(340, 32, 254, 226);
		frmThngTinLin.getContentPane().add(pnPreview);
		pnPreview.setLayout(null);

		lbPreview = new JLabel("");
		lbPreview.setBounds(10, 22, 234, 214);
		pnPreview.add(lbPreview);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.light"));
		menuBar.setBounds(0, 0, 604, 21);
		frmThngTinLin.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnNewMenu.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
		
		JMenu mnThmSch = new JMenu("Thêm Sách");
		menuBar.add(mnThmSch);
		
		JMenuItem mntmThm = new JMenuItem("Thêm");
		mntmThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmBT5_4_ThemSach bt5_4 = new FrmBT5_4_ThemSach();
			}
			
		});
		mnThmSch.add(mntmThm);
		
		JMenu mnTmSch = new JMenu("Tìm Sách");
		menuBar.add(mnTmSch);
		
		JMenuItem mntmTm = new JMenuItem("Tìm");
		mntmTm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmBT5_4_TimSach frmBT5_4_TimSach = new FrmBT5_4_TimSach();
			}
		});
		mnTmSch.add(mntmTm);
	}
}
