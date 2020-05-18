package _1_to_50_v1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class My_panel extends JPanel implements ActionListener{
	
	// 1to50
	JButton btn_arr[][];
	JButton numBtn;
	JButton reBtn;			// Q.추가하고싶은데 버튼리스너에서 안 먹힌다!! 해결OK!

	int[][] front;
	int[][] back;
	int game_num;
	Random ran;
	
	Color frontColor = new Color(210,250,255);
	Color backColor = new Color(255,255,255);
	
	My_panel(){
		this.setLayout(null);
		game_num = 1;
		ran = new Random();		
		basic_set();	
		num_shuffle();
		btn_shuffle();
	}
	
	public void btn_shuffle() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
			btn_arr[i][j].setText(front[i][j] + "");
			btn_arr[i][j].setBackground(frontColor);
			btn_arr[i][j].setVisible(true);
			}
		}
	}
	public void num_shuffle() {
		for(int i=0; i<1000; i++) {			
			int r = ran.nextInt(5);
			int t = ran.nextInt(5);
			int temp = front[r][t];
			front[r][t] = front[0][0];
			front[0][0] = temp;
			
			r = ran.nextInt(5);
			t = ran.nextInt(5);
			temp = back[r][t];
			back[r][t] = back[0][0];
			back[0][0] = temp;
		}	
	}
	public void basic_set() {
		numBtn = new JButton();
		numBtn.setText("NEXT NUM : " + game_num);
		numBtn.setSize(180, 40);
		numBtn.setLocation(50, 25);
		add(numBtn);
		
		btn_arr = new JButton[5][5];
		front = new int[5][5];
		back =new int[5][5];
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				btn_arr[i][j] = new JButton();
				btn_arr[i][j].setText("0");
				btn_arr[i][j].setSize(75, 75);
				btn_arr[i][j].setLocation(50+j*75, 85+i*75);
				btn_arr[i][j].addActionListener(this);
				add(btn_arr[i][j]);
				
				front[i][j] = i*5+j+1;
				back[i][j] = i*5+j+1+25;
			}
		}		
		reBtn = new JButton();
		reBtn.setText("RePlay!");
		reBtn.setSize(100, 40);
		reBtn.setLocation(325, 25);
		reBtn.addActionListener(this);
		add(reBtn);		
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(e.getSource() == btn_arr[i][j]) {
					if(btn_arr[i][j].getText().equals(game_num +"")) {
						if(game_num < 26) {
							btn_arr[i][j].setText(back[i][j] + "");
							btn_arr[i][j].setBackground(backColor);
							game_num++;
							numBtn.setText("NEXT NUM : " + game_num);
							
						}
						else {
							btn_arr[i][j].setVisible(false);
							game_num++;
							if(game_num > 50) {
								numBtn.setText("WIN!");
								btn_arr[2][2].setText("Clear!");
								btn_arr[2][2].setVisible(true);
							}
							else {
								numBtn.setText("NEXT NUM : " + game_num);
							}						
						}
									
					}
					
				}
				
				if(e.getSource() == reBtn) {
					game_num = 1;
					numBtn.setText("NEXT NUM : "+game_num);
//					basic_set();	// 여기 노노 reBtn 누르면 버튼 중복생성되니까!
					num_shuffle();
					btn_shuffle();
				}
			}
		}		
	}	
}

public class _1_to_50_version1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("1 TO 50");		
		frame.setSize(500, 550);
		
		Toolkit tk3 = Toolkit.getDefaultToolkit();
		Dimension size3 = tk3.getScreenSize();
		frame.setLocation(size3.width/2-250, size3.height/2-300);		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		My_panel mp = new My_panel();
		
		frame.setContentPane(mp);
		frame.revalidate();
	}

}
