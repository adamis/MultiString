/**
 * 
 */
package br.com.adamis;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.adamis.utils.Styles;



/**
 * @author adamis.rocha
 *
 */
public class Main {
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(Styles.style);
			
			Principal principal = new Principal();
			principal.setVisible(true);
		} catch (UnsupportedLookAndFeelException e) {		
			e.printStackTrace();
		}	
	}

}
