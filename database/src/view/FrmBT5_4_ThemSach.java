package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import controller.Controller;
import model.Book;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmBT5_4_ThemSach {
String databseName="book";
	private JFrame frame;
	private JTextField txtNameBook;
	private JTextField txtAuthor;
	private JTextField txtNameCompany;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBT5_4_ThemSach window = new FrmBT5_4_ThemSach();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmBT5_4_ThemSach() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(UIManager.getColor("Button.highlight"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên Sách :");
		lblNewLabel.setBounds(23, 34, 75, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtNameBook = new JTextField();
		txtNameBook.setBounds(123, 31, 220, 20);
		frame.getContentPane().add(txtNameBook);
		txtNameBook.setColumns(10);
		
		JLabel lblTnTcGi = new JLabel("Tên Tác Giả : ");
		lblTnTcGi.setBounds(23, 64, 90, 14);
		frame.getContentPane().add(lblTnTcGi);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(123, 61, 220, 20);
		frame.getContentPane().add(txtAuthor);
		
		JLabel lblNhXb = new JLabel("Nhà XB :");
		lblNhXb.setBounds(23, 95, 75, 14);
		frame.getContentPane().add(lblNhXb);
		
		txtNameCompany = new JTextField();
		txtNameCompany.setColumns(10);
		txtNameCompany.setBounds(123, 92, 220, 20);
		frame.getContentPane().add(txtNameCompany);
		
		JLabel lblGi = new JLabel("Giá :");
		lblGi.setBounds(23, 123, 75, 14);
		frame.getContentPane().add(lblGi);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(123, 120, 220, 20);
		frame.getContentPane().add(txtPrice);
		
		JButton btnAddBook = new JButton("Thêm mới sách");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book book = new Book(txtNameBook.getText(), txtAuthor.getText(), txtNameCompany.getText(), txtPrice.getText());
				String sql ="insert into book (namebook,author,namecompany,price) values ('"+book.getNameBook()+"','"+book.getNameAuthor()+"','"+book.getNameCompany()+"','"+book.getPrice()+"')";
				Controller controller = new Controller(databseName);
				controller.CRUDdatabase(sql, frame, 1);
				
			}
		});
		btnAddBook.setBounds(182, 173, 103, 23);
		frame.getContentPane().add(btnAddBook);
	}

}
