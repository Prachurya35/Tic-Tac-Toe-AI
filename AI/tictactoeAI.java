import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.EventObject;
import java.util.ArrayList;

public class tictactoeAI{
	public int [][][] grid = new int [3][4][4];
	JFrame frame;
	String turn="X",player1name,player2name;
	int player1turn = 0,player2turn = 0;
	ButtonPanel bp1,bp2,bp3;
	ArrayList<PointScore> childScores = new ArrayList<PointScore>();


	public tictactoeAI(){
		for(int i =0;i<3;i++){
			for(int v =0;v<4;v++){
				for(int t = 0;t<4;t++){
					grid[i][v][t] = 0;
				}
			}
		}
		setUpFrame();
	}

	public static void main(String [] args){
		tictactoeAI t = new tictactoeAI();
	}

	public void setUpFrame(){
		frame = new JFrame("3D Tic-Tac-Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(1500,1500);
		String s = (String)JOptionPane.showInputDialog("Enter player 1's name:");
		player1name = s;
		player2name = "AI";
		Panel p = new Panel();
		bp1 = new ButtonPanel(0,"Bottom");
		bp2 = new ButtonPanel(1,"Middle");
		bp3 = new ButtonPanel(2,"Top");
		p.add(bp1);
		p.add(bp2);
		p.add(bp3);
		frame.setContentPane(p);
		frame.setVisible(true);
	}

	public void switchTurn(){
		if(turn.equals("X")){
			turn="O";
			player1turn++;
			bp1.player1turns.setOpaque(true);
			bp1.player1turns.setText("Turns: "+player1turn);
			bp2.player.setOpaque(true);
			bp2.player.setText(player2name+"'s turn");
		}
		else{
			turn="X";
			player2turn++;
			bp3.player2turns.setOpaque(true);
			bp3.player2turns.setText("Turns: "+player2turn);
			bp2.player.setText(player1name+"'s turn");	
		}
		int x = checkForWin();
		if(x==10)
			exit(1);
		else if(x==-10)
			exit(2);
		if(turn.equals("O"))
			makeAIMove();
	}

	public void updateBoard(Point p){
		int x = p.x;
		int y = p.y;
		switch(p.z){
			case 0:
				if(x==0&&y==0)
					bp1.thirt.setText("O");
				if(x==0&&y==1)
					bp1.nine.setText("O");
				if(x==0&&y==2)
					bp1.five.setText("O");
				if(x==0&&y==3)
					bp1.one.setText("O");
				if(x==1&&y==0)
					bp1.fourt.setText("O");
				if(x==1&&y==1)
					bp1.ten.setText("O");
				if(x==1&&y==2)
					bp1.six.setText("O");
				if(x==1&&y==3)
					bp1.two.setText("O");
				if(x==2&&y==0)
					bp1.fifth.setText("O");
				if(x==2&&y==1)
					bp1.eleven.setText("O");
				if(x==2&&y==2)
					bp1.seven.setText("O");
				if(x==2&&y==3)
					bp1.three.setText("O");
				if(x==3&&y==0)
					bp1.sixt.setText("O");
				if(x==3&&y==1)
					bp1.twelve.setText("O");
				if(x==3&&y==2)
					bp1.eight.setText("O");
				if(x==3&&y==3)
					bp1.four.setText("O");
				break;
			case 1:
				if(x==0&&y==0)
					bp2.thirt.setText("O");
				if(x==0&&y==1)
					bp2.nine.setText("O");
				if(x==0&&y==2)
					bp2.five.setText("O");
				if(x==0&&y==3)
					bp2.one.setText("O");
				if(x==1&&y==0)
					bp2.fourt.setText("O");
				if(x==1&&y==1)
					bp2.ten.setText("O");
				if(x==1&&y==2)
					bp2.six.setText("O");
				if(x==1&&y==3)
					bp2.two.setText("O");
				if(x==2&&y==0)
					bp2.fifth.setText("O");
				if(x==2&&y==1)
					bp2.eleven.setText("O");
				if(x==2&&y==2)
					bp2.seven.setText("O");
				if(x==2&&y==3)
					bp2.three.setText("O");
				if(x==3&&y==0)
					bp2.sixt.setText("O");
				if(x==3&&y==1)
					bp2.twelve.setText("O");
				if(x==3&&y==2)
					bp2.eight.setText("O");
				if(x==3&&y==3)
					bp2.four.setText("O");
				break;
			case 2:
				if(x==0&&y==0)
					bp3.thirt.setText("O");
				if(x==0&&y==1)
					bp3.nine.setText("O");
				if(x==0&&y==2)
					bp3.five.setText("O");
				if(x==0&&y==3)
					bp3.one.setText("O");
				if(x==1&&y==0)
					bp3.fourt.setText("O");
				if(x==1&&y==1)
					bp3.ten.setText("O");
				if(x==1&&y==2)
					bp3.six.setText("O");
				if(x==1&&y==3)
					bp3.two.setText("O");
				if(x==2&&y==0)
					bp3.fifth.setText("O");
				if(x==2&&y==1)
					bp3.eleven.setText("O");
				if(x==2&&y==2)
					bp3.seven.setText("O");
				if(x==2&&y==3)
					bp3.three.setText("O");
				if(x==3&&y==0)
					bp3.sixt.setText("O");
				if(x==3&&y==1)
					bp3.twelve.setText("O");
				if(x==3&&y==2)
					bp3.eight.setText("O");
				if(x==3&&y==3)
					bp3.four.setText("O");
				break;
			}
		}

	public int returnMin(ArrayList<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int returnMax(ArrayList<Integer> list){
		int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public ArrayList<Point> getOpen(){
		ArrayList<Point> x = new ArrayList<Point>();
		for(int i =0;i<4;i++){
			for(int v = 0;v<4;v++){
				for(int r = 0;r<3;r++){
					if(grid[r][i][v]==0)
						x.add(new Point(i,v,r));
				}
			}
		}
		return x;
	}


    public Point returnBestMove() {
        int MAX = -100000;
        int best = 0;

        for (int i = 0; i < childScores.size(); ++i) { 
        	PointScore p = childScores.get(i);
            if (MAX < p.score&&grid[p.p.z][p.p.x][p.p.y]==0) {
                MAX = p.score;
                best = i;
            }
        }
        try{
        	return childScores.get(best).p;
        }catch(IndexOutOfBoundsException e){
        	return new Point(0,0,0);
        }
    }

    public void callMiniMax(int depth, int turn){
    	childScores = new ArrayList<PointScore>();
    	minimax(depth,turn);
    }

    public void makeAIMove(){
    	Point p;
    	if(grid[1][1][1]==0&&player2turn==0)
    		p = new Point(1,1,1);	
    	else if(grid[1][1][2]==0&&player2turn==0)
    		p = new Point(1,2,1);
    	else if(grid[1][2][1]==0&&player2turn==0)
    		p = new Point(2,1,1);
    	else if(grid[1][2][2]==0&&player2turn==0)
    		p = new Point(2,2,1);
    	else{
	       	callMiniMax(0,2);
	    	p = returnBestMove();
    	}
    	if(p.x!=-1){
    		grid[p.z][p.x][p.y]=4;
    		updateBoard(p);
    		switchTurn();
    	}
    }

    public int checkGridStrength(){
    	for(int i =0;i<3;i++){
			int x;
			for(int v =0;v<4;v++){
				x = grid[i][v][0]+grid[i][v][1]+grid[i][v][2];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[i][v][1]+grid[i][v][2]+grid[i][v][3];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		}
		for(int v =0;v<4;v++){
				int x = grid[0][v][0]+grid[1][v][1]+grid[2][v][2];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[0][v][1]+grid[1][v][2]+grid[2][v][3];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[2][v][0]+grid[1][v][1]+grid[0][v][2];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[2][v][1]+grid[1][v][2]+grid[0][v][3];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		for(int i =0;i<3;i++){
			for(int v =0;v<4;v++){
				int x = grid[i][0][v]+grid[i][1][v]+grid[i][2][v];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[i][1][v]+grid[i][2][v]+grid[i][3][v];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		}
		for(int v =0;v<4;v++){
				int x = grid[0][0][v]+grid[1][1][v]+grid[2][2][v];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[0][1][v]+grid[1][2][v]+grid[2][3][v];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[2][0][v]+grid[1][1][v]+grid[0][2][v];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
				x = grid[2][1][v]+grid[1][2][v]+grid[0][3][v];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		for(int i =0;i<3;i++){
			for(int x = 0;x<2;x++){
				for(int v = 0;v<2;v++){
					int y = grid[i][x][v]+grid[i][x+1][v+1]+grid[i][x+2][v+2];
					if(y==2)
						return -5;
					else if(y==12)
						return 5;
				}
			}
			for(int x = 2;x<4;x++){
				for(int v = 0;v<2;v++){
					int y = grid[i][x][v]+grid[i][x-1][v+1]+grid[i][x-2][v+2];
					if(y==2)
						return -5;
					else if(y==8)
						return 5;
				}
			}
		}
		for(int i=0;i<2;i++){
			for(int y =0;y<2;y++){
				int x = grid[0][i][y]+grid[1][i+1][y+1]+grid[2][i+2][y+2];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		}
		for(int i=2;i<4;i++){
			for(int y =0;y<2;y++){
				int x = grid[0][i][y]+grid[1][i-1][y+1]+grid[2][i-2][y+2];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		}
		for(int i=0;i<2;i++){
			for(int y =2;y<4;y++){
				int x = grid[0][i][y]+grid[1][i+1][y-1]+grid[2][i+2][y-2];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		}
		for(int i=2;i<4;i++){
			for(int y =2;i<4;i++){
				int x = grid[0][i][y]+grid[1][i-1][y-1]+grid[2][i-2][y-2];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		}
		for(int i =0;i<4;i++){
			for(int v =0;v<4;v++){
				int x = grid[2][i][v]+grid[1][i][v]+grid[0][i][v];
				if(x==2)
					return -5;
				else if(x==8)
					return 5;
			}
		}
		return -1;
    }

    public int minimax(int depth,int turn){
		int x = checkForWin();
		if(x==10)
			return -10;
		else if(x==-10)
			return 10;
		if(depth>=2){
			return checkGridStrength();
		}	
		ArrayList<Point> open = getOpen();
		if(open.size()==0)
			return 0;
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for(Point i:open){
			if(turn==2){
				grid[i.z][i.x][i.y]=4;
				int currentScore = minimax(depth+1,1);
				scores.add(currentScore);
				if(depth==0){
					childScores.add(new PointScore(i,currentScore));
				}
			}else if(turn==1){
				grid[i.z][i.x][i.y]=1;
				scores.add(minimax(depth+1,2));
			}
			grid[i.z][i.x][i.y]=0;
		}
		return turn==2 ? returnMax(scores) : returnMin(scores);
	}

	public void exit(int win){
		if(win==1){
			bp3.player2rekt.setOpaque(true);
			bp3.player2rekt.setText("GG lol "+player2name+" got rekt");
			bp2.winner.setText(player1name+" WINS!!!!!");
		}else{
			bp1.player1rekt.setOpaque(true);
			bp1.player1rekt.setText("GG lol "+player1name+" got rekt");
			bp2.winner.setText(player2name+" WINS!!!!!");
		}
	}

	public int checkForWin(){
		for(int i =0;i<3;i++){
			int x;
			for(int v =0;v<4;v++){
				x = grid[i][v][0]+grid[i][v][1]+grid[i][v][2];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[i][v][1]+grid[i][v][2]+grid[i][v][3];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		}
		for(int v =0;v<4;v++){
				int x = grid[0][v][0]+grid[1][v][1]+grid[2][v][2];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[0][v][1]+grid[1][v][2]+grid[2][v][3];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[2][v][0]+grid[1][v][1]+grid[0][v][2];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[2][v][1]+grid[1][v][2]+grid[0][v][3];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		for(int i =0;i<3;i++){
			for(int v =0;v<4;v++){
				int x = grid[i][0][v]+grid[i][1][v]+grid[i][2][v];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[i][1][v]+grid[i][2][v]+grid[i][3][v];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		}
		for(int v =0;v<4;v++){
				int x = grid[0][0][v]+grid[1][1][v]+grid[2][2][v];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[0][1][v]+grid[1][2][v]+grid[2][3][v];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[2][0][v]+grid[1][1][v]+grid[0][2][v];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
				x = grid[2][1][v]+grid[1][2][v]+grid[0][3][v];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		for(int i =0;i<3;i++){
			for(int x = 0;x<2;x++){
				for(int v = 0;v<2;v++){
					int y = grid[i][x][v]+grid[i][x+1][v+1]+grid[i][x+2][v+2];
					if(y==3)
						return 10;
					else if(y==12)
						return -10;
				}
			}
			for(int x = 2;x<4;x++){
				for(int v = 0;v<2;v++){
					int y = grid[i][x][v]+grid[i][x-1][v+1]+grid[i][x-2][v+2];
					if(y==3)
						return 10;
					else if(y==12)
						return -10;
				}
			}
		}
		for(int i=0;i<2;i++){
			for(int y =0;y<2;y++){
				int x = grid[0][i][y]+grid[1][i+1][y+1]+grid[2][i+2][y+2];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		}
		for(int i=2;i<4;i++){
			for(int y =0;y<2;y++){
				int x = grid[0][i][y]+grid[1][i-1][y+1]+grid[2][i-2][y+2];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		}
		for(int i=0;i<2;i++){
			for(int y =2;y<4;y++){
				int x = grid[0][i][y]+grid[1][i+1][y-1]+grid[2][i+2][y-2];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		}
		for(int i=2;i<4;i++){
			for(int y =2;i<4;i++){
				int x = grid[0][i][y]+grid[1][i-1][y-1]+grid[2][i-2][y-2];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		}
		for(int i =0;i<4;i++){
			for(int v =0;v<4;v++){
				int x = grid[2][i][v]+grid[1][i][v]+grid[0][i][v];
				if(x==3)
					return 10;
				else if(x==12)
					return -10;
			}
		}
		return -1;
	}

	class PointScore {

	    int score;
	    Point p;

	    public PointScore(Point p,int score) {
	        this.score = score;
	        this.p = p;
	    }
	}

	class Point {

	    int x, y,z;

	    public Point(int x, int y, int z) {
	        this.x = x;
	        this.y = y;
	        this.z = z;
	    }

	    @Override
	    public String toString() {
	        return "[" + x + ", " + y + ","+z+"]";
	    }
	}

	class Panel extends JPanel{
		public Panel(){
			GridLayout gl = new GridLayout(1,3);
			setLayout(gl);
		}
	}

	class ButtonPanel extends JPanel implements ActionListener{
		private int location;
		private String loc;
		public JButton one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirt,fourt,fifth,sixt,reset;
		public JLabel player,winner,player1turns,player1rekt,player2turns,player2rekt,player1,player2;

		public ButtonPanel(int x, String n){
			location = x;
			loc = n;
			setLayout(null);
		}

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawLine(100,100,100,400);
			g.drawLine(200,100,200,400);
			g.drawLine(300,100,300,400);
			g.drawLine(25,175,375,175);
			g.drawLine(25,250,375,250);
			g.drawLine(25,325,375,325);
			one = new JButton();
			two = new JButton();
			three = new JButton();
			four = new JButton();
			five = new JButton();
			six = new JButton();
			seven = new JButton();
			eight = new JButton();
			nine = new JButton();
			ten = new JButton();
			eleven = new JButton();
			twelve = new JButton();
			thirt = new JButton();
			fourt = new JButton();
			fifth = new JButton();
			sixt = new JButton();
			one.setBounds(25,100,75,75);
			two.setBounds(100,100,100,75);
			three.setBounds(200,100,100,75);
			four.setBounds(300,100,75,75);
			five.setBounds(25,175,75,75);
			six.setBounds(100,175,100,75);
			seven.setBounds(200,175,100,75);
			eight.setBounds(300,175,75,75);
			nine.setBounds(25,250,75,75);
			ten.setBounds(100,250,100,75);
			eleven.setBounds(200,250,100,75);
			twelve.setBounds(300,250,75,75);
			thirt.setBounds(25,325,75,75);
			fourt.setBounds(100,325,100,75);
			fifth.setBounds(200,325,100,75);
			sixt.setBounds(300,325,75,75);
			JLabel level = new JLabel(loc);
			level.setBounds(200,450,200,100);
			Font f = new Font("Serif",Font.PLAIN,30);
			level.setFont(f);
			if(location==1){
				player = new JLabel(player1name+"'s turn");
				player.setFont(f);
				player.setBounds(100,550,200,100);
				add(player);
				winner = new JLabel();
				winner.setFont(new Font("Serif",Font.PLAIN,40));
				winner.setBounds(0, 600,400,200);
				add(winner);
			}else if(location==0){
				player1 = new JLabel(player1name);
				player1.setFont(f);
				player1.setBounds(100,600,200,100);
				add(player1);
				player1turns = new JLabel("Turns: "+player1turn);
				player1turns.setBounds(100,700,200,100);
				player1turns.setFont(f);
				add(player1turns);
				player1rekt = new JLabel();
				player1rekt.setBounds(100,800,400,100);
				player1rekt.setFont(f);
				add(player1rekt);
			}else if(location==2){
				player2 = new JLabel(player2name);
				player2.setFont(f);
				player2.setBounds(100,600,200,100);
				add(player2);
				player2turns = new JLabel("Turns: "+player2turn);
				player2turns.setBounds(100,700,200,100);
				player2turns.setFont(f);
				add(player2turns);
				player2rekt = new JLabel();
				player2rekt.setBounds(100,800,400,100);
				player2rekt.setFont(f);
				add(player2rekt);
			}
	
			one.setFont(f);
			two.setFont(f);
			three.setFont(f);
			four.setFont(f);
			five.setFont(f);
			six.setFont(f);
			seven.setFont(f);
			eight.setFont(f);
			nine.setFont(f);
			ten.setFont(f);
			eleven.setFont(f);
			twelve.setFont(f);
			thirt.setFont(f);	
			fourt.setFont(f);
			fifth.setFont(f);
			sixt.setFont(f);
			one.addActionListener(this);
			two.addActionListener(this);
			three.addActionListener(this);
			four.addActionListener(this);
			five.addActionListener(this);
			six.addActionListener(this);
			seven.addActionListener(this);
			eight.addActionListener(this);
			nine.addActionListener(this);
			ten.addActionListener(this);
			eleven.addActionListener(this);
			twelve.addActionListener(this);
			thirt.addActionListener(this);
			fourt.addActionListener(this);
			fifth.addActionListener(this);
			sixt.addActionListener(this);
			add(one);
			add(two);
			add(three);
			add(four);
			add(five);
			add(six);
			add(seven);
			add(eight);
			add(nine);
			add(ten);
			add(eleven);
			add(twelve);
			add(thirt);
			add(fourt);
			add(fifth);
			add(sixt);
			add(level);
		}

		public void actionPerformed(ActionEvent e){
			boolean checked = false;
			JButton temp = (JButton)e.getSource();
			if(!temp.getText().equals(""))
				checked = true;
			/*if(temp==reset){
				tictactoe.this.resetEverything();
				resetButtons();
			}*/
			if(!checked){
			if(temp==one){
				if(turn.equals("X"))
					grid[location][0][3] = 1;
				else
					grid[location][0][3] = 4;
				one.setText(turn);
				switchTurn();
			}
			if(temp==two){
				if(turn.equals("X"))
					grid[location][1][3] = 1;
				else
					grid[location][1][3] = 4;
				two.setText(turn);
				switchTurn();
			}
			if(temp==three){
				if(turn.equals("X"))
					grid[location][2][3] = 1;
				else
					grid[location][2][3] = 4;
				three.setText(turn);
				switchTurn();
			}
			if(temp==four){
				if(turn.equals("X"))
					grid[location][3][3] = 1;
				else
					grid[location][3][3] = 4;
				four.setText(turn);
				switchTurn();
			}
			if(temp==five){
				if(turn.equals("X"))
					grid[location][0][2] = 1;
				else
					grid[location][0][2] = 4;
				five.setText(turn);
				switchTurn();
			}
			if(temp==six){
				if(turn.equals("X"))
					grid[location][1][2] = 1;
				else
					grid[location][1][2] = 4;
				six.setText(turn);
				switchTurn();
			}
			if(temp==seven){
				if(turn.equals("X"))
					grid[location][2][2] = 1;
				else
					grid[location][2][2] = 4;
				seven.setText(turn);
				switchTurn();
			}
			if(temp==eight){
				if(turn.equals("X"))
					grid[location][3][2] = 1;
				else
					grid[location][3][2] = 4;
				eight.setText(turn);
				switchTurn();
			}
			if(temp==nine){
				if(turn.equals("X"))
					grid[location][0][1] = 1;
				else
					grid[location][0][1] = 4;
				nine.setText(turn);
				switchTurn();
			}
			if(temp==ten){
				if(turn.equals("X"))
					grid[location][1][1] = 1;
				else
					grid[location][1][1] = 4;
				ten.setText(turn);
				switchTurn();
			}
			if(temp==eleven){
				if(turn.equals("X"))
					grid[location][2][1] = 1;
				else
					grid[location][2][1] = 4;
				eleven.setText(turn);
				switchTurn();
			}
			if(temp==twelve){
				if(turn.equals("X"))
					grid[location][3][1] = 1;
				else
					grid[location][3][1] = 4;
				twelve.setText(turn);
				switchTurn();
			}
			if(temp==thirt){
				if(turn.equals("X"))
					grid[location][0][0] = 1;
				else
					grid[location][0][0] = 4;
				thirt.setText(turn);
				switchTurn();
			}
			if(temp==fourt){
				if(turn.equals("X"))
					grid[location][1][0] = 1;
				else
					grid[location][1][0] = 4;
				fourt.setText(turn);
				switchTurn();
			}
			if(temp==fifth){
				if(turn.equals("X"))
					grid[location][2][0] = 1;
				else
					grid[location][2][0] = 4;
				fifth.setText(turn);
				switchTurn();
			}
			if(temp==sixt){
				if(turn.equals("X"))
					grid[location][3][0] = 1;
				else
					grid[location][3][0] = 4;
				sixt.setText(turn);
				switchTurn();
			}
			}
		}
	}
}