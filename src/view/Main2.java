package view;

import javax.swing.JOptionPane;
import controller.KillController;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KillController killController = new KillController();
		String selectedValueString = "";

		Object[] possibleValues = {  "Lista processos", "Kill process por nome", "Kill process por PID" };

		while (selectedValueString != null) {
			String selectedValue = (String) JOptionPane.showInputDialog(null, "Escolha um comando", "Finaliza processos",
					JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);

			if (selectedValue == null) {
				break;
			} else if (selectedValue.equals("Kill process por nome")) {
				String m = JOptionPane.showInputDialog("Insira o nome do processo:");
				if(m == null)
					continue;

		        killController.mataPid(m);
			} else if (selectedValue.equals("Kill process por PID")) {
				String m = JOptionPane.showInputDialog("Insira o Pid do processo:");
				if(m == null)
					continue;

				killController.mataPid(Integer.parseInt(m));
			} else if (selectedValue.equals("Lista processos")) {
		        killController.listaProcessos();
			}
		}
	}

}