package Cnn2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class Cnn2 extends JFrame implements KeyListener{
	private Graphics g=null; 
	private int x=30; 
	private int y=50; 
	private int width=80; 
	private int height=80; 
	static int y1;
	static int y2;
	static double x1;
	int n=4;
	static double z=-1.0;
	static double errorcount=0.0;
	int type[]={1,-1};
	static double test_correct;
	
	static double detect_correct;
	static double w[]={-1,0,1};
	static double learn;
	static double time;
	static double sumtest;
	static double sum;
	int index=0;
	static int k;
	static int count=0;
    static double testall_count=0.0;
    static double detectall_count=0.0;
    static double testwrong_count=0.0;
    public static double[][] xyd =new double[10000][3];
	
	
	public Cnn2() 
	{ 
	setSize(960,960); 
	setLocation(100,100); 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	setBackground(Color.WHITE); 
	setVisible(true); 
	addKeyListener(this); 
	g=getGraphics(); 

	} 
	
	public void paint(Graphics g) 
	{ 
		int changex;
		int changey;
		int counttest=0;
		g.translate(400, 400);
		g.drawLine(0,-960,0,960);
		g.drawLine(-960,0,960,0);
		for(int i=0;i<k;i++){
			changex=(int)(xyd[i][0]*30);
			changey=(int)(-xyd[i][1]*30);
			g.setColor(Color.black);
			g.drawLine(5*50,-(int)(30*((w[0])-5*w[1])/w[2]),(int)(30*((w[0])-1*w[2])/w[1]),-1);
			g.drawLine(-5*50,-(int)(30*((w[0])+5*w[1])/w[2]),(int)(30*((w[0])-1*w[2])/w[1]),-1);
			if(xyd[i][2]==xyd[0][2]){
				g.setColor(Color.blue);
				g.fillOval(changex, changey,2,2);
			}
			else{
				g.setColor(Color.red);
				g.fillOval(changex, changey,2,2);
			}
			//System.out.println(((-w[0])/w[2])*50);
			//System.out.println(50*(-w[0]-w[1]*50)/w[2]);
			
			
			
		} 
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		FileReader fr = new FileReader("2CloseS.txt"); 
        BufferedReader br = new BufferedReader(fr);
        String line,tempstring;
        String[] tempArray= new String[100];
        ArrayList myList = new ArrayList();
        int i=0;
        while((line = br.readLine())!=null)
        {
        	
             tempstring = line;
             
             tempArray = tempstring.split("\\s");
           
            
            
              for(i=0;i< tempArray.length;i++)
              {      
            	  if(!"".equals(tempArray[i])){
            	  
            		  myList.add(tempArray[i]);
                  
            	  }
            	  
              }
        }
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入學習率");
        learn=scanner.nextDouble();
        System.out.println("請輸入循環次數(跑全部資料一次)");
        time=scanner.nextDouble();
        k = myList.size()/3;
        
        for(int a=0;a<myList.size()/3;a++)
        {
            for(int b=0;b<3;b++)
            {	
            	
                xyd[a][b]=Double.parseDouble( (String) myList.get(count));
                
                count++; //一個index來決定myList讀取值的位置
            }
         
        }
       
		for(int d=0;d<time;d++){
			for(int c=0;c<k;c++){
				
				if(c<k*2/3+1){
					
					testall_count++;
					sumtest=xyd[c][0]*w[1]+xyd[c][1]*w[2]+z*w[0];
					
					
					if(sumtest>0){
						y1=1;
						y2=(int)xyd[0][2]+1;
						
					}
					else{
						y1=-1;
						y2=(int)xyd[0][2];
						
					}
						
						
					if(y2!=xyd[c][2])
					{
						w[0]=w[0]+w[0]*learn*(-y1);
						w[1]=w[1]+xyd[c][0]*learn*(-y1);
						w[2]=w[2]+xyd[c][1]*learn*(-y1);
					   // System.out.println(xyd[c][0]+" "+xyd[c][1]+" "+xyd[c][2]);
						testwrong_count++;
						
						}
				
				}

				
				else{
						;
					    detectall_count++;
						sum=xyd[c][0]*w[1]+xyd[c][1]*w[2]+z*w[0];
						
						if(sum>0){
							x1=(int)xyd[0][2]+1;
							
						}
						else{
							x1=(int)xyd[0][2];
							
						}
						if(x1!=xyd[c][2]){
							
							errorcount++;
						}
							
					
				
					
				}
				
				
				
				
			}
			
		}
		
		
		System.out.println("鍵結值");
		
		System.out.println("w0:"+w[0]);
		System.out.println("w1:"+w[1]);
		System.out.println("w2:"+w[2]);
		System.out.println();
		System.out.println("訓練偵測率:"+(1-(testwrong_count/testall_count)));
		System.out.println("偵測率:"+(1-(errorcount/detectall_count)));
		new Cnn2();
		}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	/*@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}*/
	
	
	
	
	
	
	
	
}

