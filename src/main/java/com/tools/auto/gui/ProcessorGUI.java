package com.tools.auto.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
* Author: fulishang
* Create Time  : 2017年5月14日,上午12:24:17
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class ProcessorGUI extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(ProcessorGUI.class);
	
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ProcessorGUI();
	}

	/**
	 * Create the application.
	 */
	public ProcessorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("文件：");
		lblNewLabel.setBounds(18, 19, 61, 16);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(56, 14, 277, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("打开");
		btnNewButton.setBounds(327, 14, 117, 29);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose();
			}
		});
		this.setVisible(true);
	}
	
    /** 
     * 选择文件
     * @return File
     */
    public File choose() {
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();  
        logger.info(file.getAbsolutePath());
        textField.setText(file.getAbsolutePath());
        return file;
    }
}
