import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frame extends JFrame {
	JPanel contentPane;
	Image img;
	JTextField elem1;
	JTextField num1;
	JTextField elem2;
	JTextField num2;
	JTextField gnr;
	JTextField right_count;
	JTextField wrong_count;
	JTextField textField;
	source information = new source();

	public frame() throws FileNotFoundException {
	      addMouseListener(new MouseAdapter() {
	          public void mousePressed(MouseEvent me) { 
		    	System.out.println(me.getY());
	            int x = (me.getX() - 60), y = (me.getY() - 130);
	            
	            if (!(x >= 0 && y > 0))
	            	return;
	            
	            x /= 55;
	            y /= 31;
	            
	            if (!(x < 11 && y < 10))
	            	return;
	            
	            if (information.tablem[y][x] == "")
	            	return;
	            
	            if (elem1.getText().isEmpty()) {
	            	elem1.setText(information.tablem[y][x]);
	            } else {
		            if (elem2.getText().isEmpty())
		            	elem2.setText(information.tablem[y][x]);
	            }
	          } 
	        }); 

		setResizable(false);
		setTitle("Бинарные соединения");
		resizemax();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setbackground("/back.jpg");
		
		// making area for element one
		elem1 = new JTextField();
		elem1.setBackground(Color.WHITE);
		elem1.setBounds(828, 208, 110, 94);
		contentPane.add(elem1);
		elem1.setColumns(10);
		elem1.setEditable(false);
		elem1.setFont(new Font("Arial", Font.BOLD, 70));
		elem1.setHorizontalAlignment(JTextField.CENTER);

		num1 = new JTextField();
		num1.setBackground(Color.WHITE);
		num1.setBounds(936, 299, 76, 81);
		contentPane.add(num1);
		num1.setColumns(10);
		num1.setFont(new Font("Arial", Font.BOLD, 50));
		num1.setHorizontalAlignment(JTextField.CENTER);
		
		// making area for element second
		elem2 = new JTextField();
		elem2.setBackground(Color.WHITE);
		elem2.setBounds(1012, 208, 110, 94);
		contentPane.add(elem2);
		elem2.setColumns(10);
		elem2.setEditable(false);
		elem2.setFont(new Font("Arial", Font.BOLD, 70));
		elem2.setHorizontalAlignment(JTextField.CENTER);

		num2 = new JTextField();
		num2.setBackground(Color.WHITE);
		num2.setBounds(1121, 299, 76, 81);
		contentPane.add(num2);
		num2.setColumns(10);
		num2.setFont(new Font("Arial", Font.BOLD, 50));
		num2.setHorizontalAlignment(JTextField.CENTER);
		
		// making window clearing
		JButton clear = new JButton("ОЧИСТИТЬ");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clean();
			}
		});
		clear.setBounds(1072, 403, 201, 49);
		contentPane.add(clear);

		information.load_tasks();
		
		right_count = new JTextField();
		right_count.setFont(new Font("Tahoma", Font.BOLD, 17));
		right_count.setBounds(596, 575, 67, 48);
		contentPane.add(right_count);
		right_count.setColumns(10);

		wrong_count = new JTextField();
		wrong_count.setFont(new Font("Tahoma", Font.BOLD, 17));
		wrong_count.setColumns(10);
		wrong_count.setBounds(596, 634, 67, 49);
		contentPane.add(wrong_count);

		right_count.setText(Integer.toString(0));
		wrong_count.setText(Integer.toString(0));

		JButton check = new JButton("ПРОВЕРИТЬ ОТВЕТ");
		check.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String ans = elem1.getText() + num1.getText() + elem2.getText() + num2.getText();
				String name = gnr.getText();
				
				System.out.println(ans);
				System.out.println(information.tasks.get(name));
				
				if (elem1.getText().isEmpty() && elem2.getText().isEmpty() && num1.getText().isEmpty()
						&& num2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "ВВЕДИТЕ ОТВЕТ!");
				} else if (information.tasks.get(name).equals(ans)) {
					JOptionPane.showMessageDialog(null, "ВЕРНО!");

					right_count.setText(Integer.toString(Integer.parseInt(right_count.getText()) + 1));

					clean();
					new_task();
				} else {
					JOptionPane.showMessageDialog(null, "НЕВЕРНЫЙ ОТВЕТ!");
					wrong_count.setText(Integer.toString(Integer.parseInt(wrong_count.getText()) + 1));
					clean();
				}
			}
		});
		check.setBounds(828, 403, 234, 49);
		contentPane.add(check);

		gnr = new JTextField();
		gnr.setHorizontalAlignment(SwingConstants.LEFT);
		gnr.setFont(new Font("Tahoma", Font.PLAIN, 30));
		gnr.setBackground(Color.WHITE);
		gnr.setEditable(false);
		gnr.setBounds(828, 63, 445, 115);
		contentPane.add(gnr);
		gnr.setColumns(10);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(828, 11, 86, 20);
		contentPane.add(textField);
		textField.setVisible(false);
		textField.setColumns(10);

		JButton newt = new JButton("СГЕНЕРИРОВАТЬ НОВОЕ ЗАДАНИЕ");
		newt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new_task();
			}
		});
		newt.setBounds(828, 463, 234, 49);
		contentPane.add(newt);

		JButton knowans = new JButton("УЗНАТЬ ОТВЕТ");
		knowans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Правильным ответом было: " + information.tasks.get(gnr.getText()));
			}
		});
		knowans.setBounds(1072, 463, 201, 49);
		contentPane.add(knowans);

		JLabel lright = new JLabel("ВЕРНЫХ ОТВЕТОВ");
		lright.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lright.setBounds(388, 574, 168, 49);
		contentPane.add(lright);

		JLabel lwrong = new JLabel("НЕВЕРНЫХ ОТВЕТОВ");
		lwrong.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lwrong.setBounds(366, 634, 190, 49);
		contentPane.add(lwrong);
		
		// making info label, credits
		JButton about = new JButton("О ПРОГРАММЕ");
		String abouttext = "Программа создана Артемом Стрельцовым. В случае обнаружения ошибок попробуйте просто понять и простить автора. Мне страшно переделывать эту игру!";
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, abouttext);
			}
		});
		about.setBounds(828, 526, 445, 49);
		contentPane.add(about);
		
		// making Easter egg
		JButton egg = new JButton("");
		String eggtext = "Здравствуйте, я - молдавский вирус.\nВ виду бедности моего создателя и общей отсталости развития высоких технологий нашей страны, \nя не в силах причинить какой-либо вред вашему компьютеру.\nПожалуйста, сотрите сами несколько самых нужных вам файлов, а затем разошлите меня по почте своим друзьям.\nБлагодарю за понимание и сотрудничество.";
		
		egg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, eggtext);
			}
		});
		egg.setContentAreaFilled(false);
		egg.setBorderPainted(false);
		egg.setBounds(557, 380, 55, 31);
		contentPane.add(egg);
	}

	public void setbackground(String name) {
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 800, 600);

		try {
			img = new ImageIcon(this.getClass().getResource(name)).getImage();
			label.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}

		contentPane.add(label);
	}
	
	public void resizemax() {
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int vert = sSize.height;
		int hor = sSize.width;
		setBounds(0, 0, hor, vert);
	}
	
	public void clean() {
		elem1.setText("");
		elem2.setText("");
		num1.setText("");
		num2.setText("");
	}
	
	public void new_task() {
		final Random rnd = new Random();
		int max = information.task_name.size();
		
		String question = information.task_name.get(rnd.nextInt(max - 1));
		System.out.println(question);
		gnr.setText(question);
	}
}