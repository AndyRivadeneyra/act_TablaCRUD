import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;



public class ExampleTableThree extends JFrame implements ActionListener{
	private JTable tabla;
	JLabel lblPrecio;
	JLabel lblproducto;
	private DefaultTableModel model;
	private JButton eliminar,editar,agregar;
	private JTextField producto=null;
	private JTextField precio;
	private int posicion=-1;

	public ExampleTableThree() {
		super();
		configurarVentana();
		crearComponentes();
	}

	private void configurarVentana() {
		this.setTitle("Tabla");
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void crearComponentes() {
		producto=new JTextField();
		producto.setBounds(100,20, 100,20);
		this.add(producto);
		
		precio=new JTextField();
		precio.setBounds(100,50, 100, 20);
		this.add(precio);
		
		lblproducto = new JLabel("PRODUCTO:");
		lblproducto.setBounds(30,20,90,30);
		this.add(lblproducto);

		lblPrecio = new JLabel("PRECIO: ");
		lblPrecio.setBounds(30,50,90,30);
		this.add(lblPrecio);

		agregar= new JButton();
		agregar.setText("AGREGAR");
		agregar.setBounds(240,60,100,30);
		agregar.addActionListener(this);
		agregar.setBackground(Color.WHITE);
		this.add(agregar);

		eliminar= new JButton();
		eliminar.setText("ELIMINAR");
		eliminar.setBounds(240,20,100,30);
		eliminar.addActionListener(this);
		eliminar.setBackground(Color.WHITE);
		this.add(eliminar);		

		editar= new JButton();
		editar.setText("EDITAR");
		editar.setBounds(240,100,100,30);
		editar.addActionListener(this);
		editar.setBackground(Color.WHITE);
		this.add(editar);

		String data[][]= {
				{"1","tacos","$12.00"},
				{"2","tamales","$10.00"},
				{"3","tortas","$35.00"},
				{"4","Cochito","$45.00"}
		};

		String label []= {"ID","NOMBRE","PRECIO"};

		model= new DefaultTableModel (data,label);//creacion del modelo de la tabla
		tabla=new JTable(model);
		tabla.setCellSelectionEnabled(true);		
		selection();
		tabla.setBounds(30,100,200,200);
		JScrollPane sp = new JScrollPane(tabla);
		this.add(tabla);
		this.add(sp);
	}

	public void selection() {
		ListSelectionModel select=tabla.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String nombre=null;
				String costo=null;
				String Data=null;
				int [] row=tabla.getSelectedRows();
				int [] columns=tabla.getSelectedColumns();
				for(int i=0;i<row.length;i++) {
					nombre=(String) tabla.getValueAt(row[i],1);
					costo=(String) tabla.getValueAt(row[i],2);
					posicion=row[i];
				}
				producto.setText(nombre);
				precio.setText(costo);
			}
		});
	}

public void addRow (String str1, String str2, String str3) {
	
}
	@Override 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==eliminar) {
			if (model.getRowCount()>0)
				model.removeRow(posicion);
			posicion=-1;
		}
		if(e.getSource()==editar) {
			Object[]	x= new Object[] {posicion+1 ,producto.getText(),precio.getText()};
				model.removeRow(posicion);
				model.insertRow(posicion, x);
		}
		if(e.getSource()==agregar) {
			if (model.getRowCount()>0)
			model.insertRow(tabla.getRowCount(), new Object[] {tabla.getRowCount()+1 ,producto.getText(),precio.getText()});
		}
	}	

	

	public static void main (String args[]) {
		ExampleTableThree ventana= new ExampleTableThree();
		ventana.setVisible(true);
	}
}