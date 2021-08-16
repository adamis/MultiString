/**
 * @autor Adamis.Rocha
 * @since 1.0, 31 de ago de 2017
 */
package br.com.adamis.resource;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * @author Adamis.Rocha
 * @since 1.0, 31 de ago de 2017
 */
public class Resouces {
	public static String version = "1.5";

	public static String version(){
		return version;
	}

	public static Image copy(){
		return toolkit("copy.png");		
	}

	public static ImageIcon back(){
		return toolkitIcon("back.png");		
	}

	public static ImageIcon front(){
		return toolkitIcon("front.png");		
	}
	public static ImageIcon trash(){
		return toolkitIcon("trash.png");		
	}

	public static ImageIcon magic(){
		return toolkitIcon("magic.png");		
	}

	private static Image toolkit(String img) {

		Class<?> clazz = Resouces.class;
		ClassLoader classLoader = clazz.getClassLoader();
		return Toolkit.getDefaultToolkit()
				.getImage(classLoader.getResource(img));		
	}

	private static ImageIcon toolkitIcon(String img) {

		Class<?> clazz = Resouces.class;
		ClassLoader classLoader = clazz.getClassLoader();
		return new ImageIcon(classLoader.getResource(img));

	}

}
