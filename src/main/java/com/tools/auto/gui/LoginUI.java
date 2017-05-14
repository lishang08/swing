package com.tools.auto.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.tools.auto.model.User;
import com.tools.auto.service.UserService;
import com.tools.auto.utils.SpringDBInit;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Author: fulishang
* Create Time  : 2017年5月13日,下午2:24:00
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class LoginUI{


	/** 使用slf4j logger */
	private static Logger logger = LoggerFactory.getLogger(LoginUI.class);
	
	/** 引入spring Applicationcontext */
	private static ApplicationContext context;
	
	/**引入service */
	private UserService userService;
	
	
	private JFrame frame;
	private JTextField textField;
	private JTextField passwordField;
	private JLabel infoLabel;
	
	/**
	 *  初始化时，加载spring配置
	 */
	static {
		try {
			context = SpringDBInit.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
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
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名 ：");
		label.setBounds(97, 70, 76, 16);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("用户登录");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(175, 27, 171, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(164, 65, 224, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("密    码：");
		label_1.setBounds(97, 110, 76, 16);
		frame.getContentPane().add(label_1);
		
		passwordField = new JTextField();
		passwordField.setBounds(164, 103, 224, 26);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("登     录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(textField.getText(), passwordField.getText());
			}
		});
		button.setBounds(90, 157, 117, 29);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("重      置 ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_1.setBounds(243, 157, 117, 29);
		frame.getContentPane().add(button_1);
		
		infoLabel = new JLabel("");
		infoLabel.setBounds(100, 220, 284, 16);
		frame.getContentPane().add(infoLabel).setVisible(true);;
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 */
	private void login(String username, String password) {
		logger.info("Login info : username = {},password={}", username, password);
		userService = (UserService) context.getBean("userService");
		User user = userService.login(context, username, password);
		if (user == null) {
			//提示用户输入有误
			infoLabel.setText("user or password wrong!!!");
			infoLabel.setForeground(Color.RED);
            infoLabel.repaint();
		} else {
			//登录成功跳转页面
			logger.info("Login successfully, move to Processor GUI");
			this.frame.dispose();
			new ProcessorGUI(context);
		}
	}
	
	/**
	 * 重置登录
	 */
	private void reset() {
		textField.setText(null);
		passwordField.setText(null);
		logger.info("reset successfully!!!");
	}
}
