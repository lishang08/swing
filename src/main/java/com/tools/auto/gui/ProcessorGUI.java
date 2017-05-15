package com.tools.auto.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.tools.auto.service.FileService;
import com.tools.auto.utils.Constants;

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
	private JLabel infoLabel;
	
	private File file;
	
	private FileService fileService;
	
	private static ApplicationContext context;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ProcessorGUI(context);
	}

	/**
	 * Create the application.
	 */
	public ProcessorGUI(ApplicationContext context) {
		ProcessorGUI.context = context;
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
		
		JButton open = new JButton("打开");
		open.setBounds(327, 14, 117, 29);
		getContentPane().add(open);
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file = choose();
			}
		});
		
		JButton generateXmlButton = new JButton("生成xml");
		generateXmlButton.setBounds(22, 70, 117, 29);
		getContentPane().add(generateXmlButton);
		
		infoLabel = new JLabel("");
		infoLabel.setBounds(18, 158, 426, 54);
		getContentPane().add(infoLabel);
		
		JButton btnddl = new JButton("生成DDL");
		btnddl.setBounds(167, 70, 117, 29);
		getContentPane().add(btnddl);
		
		generateXmlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText() == null || textField.getText().equals("")) {
					repaintInfoLabel("Can not proceed with NULL input!!!");
				} else {
					generateXml(file);
				}
			}
		});
		
		btnddl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText() == null || textField.getText().equals("")) {
					repaintInfoLabel("Can not proceed with NULL input!!!");
				} else {
					generateDDL(file);
				}
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
        File f=jfc.getSelectedFile();  
        logger.info(f.getAbsolutePath());
        textField.setText(f.getAbsolutePath());
        return f;
    }
    
    /**
     *  生成xml
     * @param file
     */
    public void generateXml(File file) {
    	fileService = (FileService) context.getBean("fileService");
    	String info = fileService.loadFile(file, Constants.EVENT_GENERATE_XML);
		repaintInfoLabel(info);
    }
    
    /**
     * 生成ddl
     * @param file
     */
    public void generateDDL(File file) {
    	fileService = (FileService) context.getBean("fileService");
    	String info = fileService.loadFile(file, Constants.EVENT_GENERATE_DDL);
    	repaintInfoLabel(info);
    }
    
    
    
    
    /**
     * 刷新显示信息
     * @param info
     */
    public void repaintInfoLabel(String info) {
    	infoLabel.setText(info);
    	infoLabel.setForeground(Color.RED);
    	infoLabel.repaint();
    }
}
