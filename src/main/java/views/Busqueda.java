package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hotel.jdbc.DAO.ReservaDAO;
import com.hotel.jdbc.controller.huespedesController;
import com.hotel.jdbc.controller.reservasController;
import com.hotel.jdbc.modelo.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private reservasController reservasController;
	private huespedesController huespedesController;
	private ReservasView reservaVista;
	private RegistroHuesped registroHuesped;
	String reserva;
	String huespedes;
	Integer identificadoReserva;
	Integer identificadoHuesped;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		this.reservaVista = new ReservasView();
		this.huespedesController = new huespedesController();
		this.reservasController = new reservasController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 18));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		LlenarTablaReservas();
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
			
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		tbHuespedes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		LlenarTablaHuespedes();
		
		
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				if (txtBuscar.getText().equals("")){
					
					LlenarTablaReservas();
					LlenarTablaHuespedes();
				}
				else {
					mostrarTablaReservasId();
					LlenarTablaHuespedesId();
				}
					
			}
		});
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		
		
			
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				
				if (filaReservas >= 0) {
					
					EditarReserva();
					limpiarTabla();
					LlenarTablaReservas();
					LlenarTablaHuespedes();
				} else if (filaHuespedes >= 0) {
					EditarReserva();
					limpiarTabla();
					LlenarTablaReservas();
					LlenarTablaHuespedes();
				}else {
						JOptionPane.showMessageDialog(null, "¡Hubo un error al modificar, asegúrese de seleccionar un ítem!", "Debe seleccionar un item", JOptionPane.ERROR_MESSAGE);
				}
				}
				});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				int filar=tbReservas.getSelectedRow();
				
				int filah=tbHuespedes.getSelectedRow();
				
				
				System.out.println(filar);
				System.out.println(filah);
				
				if (filar >=0) {
					identificadoReserva= (int)tbReservas.getValueAt(filar, 0);
					reservasController.Eliminar(identificadoReserva);
					JOptionPane.showMessageDialog(contentPane, "¡Registro eliminado con exito!");
					limpiarTabla();
					LlenarTablaReservas();
					LlenarTablaHuespedes();
				}
				else if (filah >=0){
					System.out.println("entro al else filah");
					identificadoHuesped= (int)tbHuespedes.getValueAt(filah, 0);
					huespedesController.Eliminar(identificadoHuesped);
					JOptionPane.showMessageDialog(contentPane, "¡Registro huésped eliminado con exito!");
					limpiarTabla();
					LlenarTablaReservas();
					LlenarTablaHuespedes();
					
				}
			 else {
				JOptionPane.showMessageDialog(null, "¡Hubo un error al eliminar!", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
			}
			}
			});
				
			
				
		
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		
					
	}			
	
	//funciones de reserva y huespedes
	
	

	private List<Reserva> buscarReservas() {
		return this.reservasController.listar();
	}
	
	private List<Reserva> buscarIdReserva() {
		return this.reservasController.buscarId(txtBuscar.getText());
	}	


	private void LlenarTablaReservas() {
		List<Reserva> reserva = buscarReservas();
		modelo.setRowCount(0);
		
		try {
			for (Reserva reservas: reserva) {
				modelo.addRow(new Object[] {
						reservas.getId(),
						reservas.getFechae(),
						reservas.getFechas(),
						reservas.getValor(),
						reservas.getFormaPago()
				});
			}
		}catch (Exception e) {
			throw e;
		}

	}
	
	private void mostrarTablaReservasId() {
		List<Reserva> reserva = buscarIdReserva();
		try {
			for (Reserva reservas : reserva) {
				modelo.addRow(new Object[] {
						reservas.getId(), reservas.getFechae(),
						reservas.getFechas(), reservas.getValor(),
						reservas.getFormaPago()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	private List<Huesped> buscarHuesped() {
		return this.huespedesController.listar();
	}
	
	private List<Huesped> buscarIdHuesped() {
		
		return this.huespedesController.buscarId(txtBuscar.getText().trim());
	}	


	private void LlenarTablaHuespedes() {
		List<Huesped> huesped = buscarHuesped();
		modeloHuesped.setRowCount(0);
		
		try {
			for (Huesped huespedes: huesped) {
				modeloHuesped.addRow(new Object[] {
						huespedes.getId(),
						huespedes.getNombre(),
						huespedes.getApellido(),
						huespedes.getFecha_nacimiento(),
						huespedes.getNacionalidad(),
						huespedes.getTelefono(),
						huespedes.getId_reserva()
				});
			}
		}catch (Exception e) {
			throw e;
		}

	}
	
	private void LlenarTablaHuespedesId() {
		List<Huesped> huesped = buscarIdHuesped();
		try {
			for (Huesped huespedes : huesped) {
				modeloHuesped.addRow(new Object[] {
						huespedes.getId(),
						huespedes.getNombre(),
						huespedes.getApellido(),
						huespedes.getFecha_nacimiento(),
						huespedes.getNacionalidad(),
						huespedes.getTelefono(),
						huespedes.getId_reserva()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	

		private void limpiarTabla() {
			((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
			((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
		}
		 
		
		
		 

		    private void EditarReserva() {
		    	Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresent(fila -> {
					LocalDate fechaEntrada;
					LocalDate fechaSalida;
					
					try {
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						fechaEntrada = LocalDate.parse(
								modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString(), dateFormat);
						fechaSalida = LocalDate.parse(
								modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString(), dateFormat);
					} catch (DateTimeException e) {
						throw new RuntimeException(e);
					}
					this.reservaVista.limpiarValor();
					
					String valor = calcularValorReserva(fechaEntrada, fechaSalida) + " $ ";
					String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4); 
					Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					
					if(tbReservas.getSelectedColumn() == 0) {
						JOptionPane.showMessageDialog(this, "No se puede modificar el id");
					} else {
						this.reservasController.actualizarReserva(fechaEntrada, fechaSalida, valor, formaPago, id);
						JOptionPane.showMessageDialog(this, String.format("¡Registro modificado con éxito!"));
					}
					
				});	
			}
			
			public String calcularValorReserva (LocalDate fechaEntrada, LocalDate fechaSalida) {
				if(fechaEntrada != null && fechaSalida != null) {
					int dias = (int) ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
					int noche = 50;
					int valor = dias * noche;
					return Integer.toString(valor);
				} else {
					return "";
				}
			}
		

	

	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
}

