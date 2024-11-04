package clases_de_apyo;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

//FUENTE-EXTERNA
//URL: (EXAMEN 22-23)
//ADAPTADO (ADAPTADO A NECESIDADES)

public class AsignarRendererEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	private static final long serialVersionUID = 1L;

	private Rutina rutina;
	private JFrame mainWindow;
	
	public AsignarRendererEditor(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	private JButton prepare(JTable table, Object value, boolean isSelected, int row, int column) {
		rutina = (Rutina) value;
		
		JButton button = new JButton("Asignar");
		button.setEnabled(true);
		button.setBackground(table.getBackground());
		
		if (isSelected) {
			button.setBackground(table.getSelectionBackground());
		}
		
		button.addActionListener((e) -> {
			//Se crea el cuadro de diálogo para confirmar la reserva
			Dialogo_asignar dialog = new Dialogo_asignar(rutina);			
			dialog.dispose();
		});
		
		button.setOpaque(true);
		
		return button;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return prepare(table, value, isSelected, row, column);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return prepare(table, value, isSelected, row, column);		
	}
	
	@Override
	public Object getCellEditorValue() {
		return rutina;
	}
	
    @Override
    public boolean shouldSelectCell(EventObject event) {
        return true;
    }
}