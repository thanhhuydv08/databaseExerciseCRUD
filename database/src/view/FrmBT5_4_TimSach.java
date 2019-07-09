package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Book;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmBT5_4_TimSach {
	String keyUpdateNameBook;
	ArrayList<Book> arrayList;
	JTable jTable;
	private JFrame frmTmKiemsSch;
	private JTextField txtLookForBook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBT5_4_TimSach window = new FrmBT5_4_TimSach();
					window.frmTmKiemsSch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmBT5_4_TimSach() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTmKiemsSch = new JFrame();
		frmTmKiemsSch.setVisible(true);
		frmTmKiemsSch.setTitle("Tìm kiếm sách");
		frmTmKiemsSch.getContentPane().setBackground(UIManager.getColor("Button.highlight"));
		frmTmKiemsSch.setBounds(100, 100, 572, 333);
		frmTmKiemsSch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTmKiemsSch.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tìm Sách :");
		lblNewLabel.setBounds(87, 31, 89, 14);
		frmTmKiemsSch.getContentPane().add(lblNewLabel);
		
		txtLookForBook = new JTextField();
		txtLookForBook.setBounds(200, 28, 217, 20);
		frmTmKiemsSch.getContentPane().add(txtLookForBook);
		txtLookForBook.setColumns(10);
		
		JButton btnLook = new JButton("Tìm");
		btnLook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="select * from book where namebook LIKE '%"+txtLookForBook.getText()+"%'";
				arrayList = new Controller("book").CRUDdatabase(sql, frmTmKiemsSch, 4);
				DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
				int row = dtm.getRowCount();
				for(int i=0;i<row;i++) {
					dtm.removeRow(0);// xoa dong dau lap cho den khi 
					System.out.println("xoa lan"+i);
				}
				for(int i=0;i<arrayList.size();i++) {
					dtm.addRow(new Object [] {arrayList.get(i).getNameBook(),arrayList.get(i).getNameAuthor(),arrayList.get(i).getNameCompany(),arrayList.get(i).getPrice()});	
				}
				
			}
		});
		btnLook.setBackground(UIManager.getColor("Button.light"));
		btnLook.setBounds(119, 73, 89, 23);
		frmTmKiemsSch.getContentPane().add(btnLook);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowDelete = jTable.getSelectedRow();
				String nameBookDelete = (String) jTable.getModel().getValueAt(rowDelete, 0);
				DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
				dtm.removeRow(rowDelete);
				String sql = "DELETE FROM book WHERE namebook='"+nameBookDelete+"'";
				new Controller("book").CRUDdatabase(sql, frmTmKiemsSch, 3);
			}
		});
		btnDelete.setBackground(UIManager.getColor("Button.light"));
		btnDelete.setBounds(240, 73, 89, 23);
		frmTmKiemsSch.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowUpdate = jTable.getSelectedRow();
			    Book book = new Book(jTable.getModel().getValueAt(rowUpdate, 0).toString(), jTable.getModel().getValueAt(rowUpdate, 1).toString(), jTable.getModel().getValueAt(rowUpdate, 2).toString(), jTable.getModel().getValueAt(rowUpdate, 3).toString());
				DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();	
				String sql = "UPDATE book SET namebook = '"+book.getNameBook()+"',author = '"+book.getNameAuthor()+"', namecompany = '"+book.getNameCompany()+"', price = '"+book.getPrice() +"' where namebook='"+keyUpdateNameBook+"'";
				new Controller("book").CRUDdatabase(sql, frmTmKiemsSch, 2);
				
				// xhua xong cho cap nhat
			}
		});
		btnUpdate.setBackground(UIManager.getColor("Button.light"));
		btnUpdate.setBounds(360, 73, 89, 23);
		frmTmKiemsSch.getContentPane().add(btnUpdate);
		
		Object [] columNames = {"Tên sách","Tác Giả","NXB","Giá bìa"};
		
		jTable = new JTable(new DefaultTableModel(columNames,1));
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				keyUpdateNameBook = (String) jTable.getModel().getValueAt(jTable.getSelectedRow(), 0);
				System.out.println("toi da click row co values "+keyUpdateNameBook);
			}
		});
		jTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
		jTable.setBackground(Color.orange);
		jTable.getTableHeader().setBackground(Color.green);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		jScrollPane.setViewportBorder(new LineBorder(new Color(175, 238, 238)));
		jScrollPane.setLocation(0, 118);
		jScrollPane.setSize(556, 143);
		jScrollPane.setBackground(Color.ORANGE);
		frmTmKiemsSch.getContentPane().add(jScrollPane);
		
		
	}

}
