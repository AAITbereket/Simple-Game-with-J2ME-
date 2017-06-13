
package GameAssignment;


import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;

/**
 * @author bereket
 */
public class Midlet extends MIDlet implements CommandListener {
    Display disp;
    Ourgame can;
    Command exit,Restart;
    
    Midlet(){
        can = new Ourgame(); 
        exit = new Command("Exit",Command.EXIT,2 );
        Restart = new Command("Restart", Command.CANCEL , 3);
        
        can.addCommand(exit);
        can.addCommand(Restart);
        can.setCommandListener(this);
    }
    public void startApp() {
        
        disp= Display.getDisplay(this);
        disp.setCurrent(can);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
       if (c == exit)
       {
           this.destroyApp(true);
           this.notifyDestroyed();
       }
       else if (c == Restart)
       {
           can.restart();
       }
    }
    
    
}

class Ourgame extends Canvas{
    
    int l=getHeight();
    int w=getWidth();
    int x=w/2;
    int y=l-l/8-32;
        
    int p1x=w/8;
    int p1y=l/8;
    
    int p2x=w/2-10;
     int p2y=l/8;
     int p3x =w-w/4;
     int p3y =l/8;
        
     int p4x=w/8;
     int p4y=l/2-10;
     int p5x=w/2-10;
     int p5y=l/2-10;
     int p6x=w-w/4;
     int p6y=l/2-10;
       
     int p7x=w/8;
     int p7y=l-l/8-30;
     int p8x=w/2-8;
     int p8y=p7y;
     int p9x=w-w/4+2;
     int p9y=p7y;
     
     int width=w/8-2;
     int heigth=l/8-10;
     
     int pp1x=w/8;
        int pp1y=l/8;
    
    int pp2x=w/2-10;
    int pp2y=l/8;
    int pp3x =w-w/4;
    int pp3y =l/8;

    int pp4x=w/8;
    int pp4y=l/2-10;
    int pp5x=w/2-10;
    int pp5y=l/2-10;
    int pp6x=w-w/4;
    int pp6y=l/2-10;

    int pp7x=w/8;
    int pp7y=l-l/8-30;
    int pp8x=w/2-8;
    int pp8y=p7y;
    int pp9x=w-w/4+2;
    int pp9y=p7y;

        
    int buttonSelected = 1;
    
    boolean activePlaces[] = {false,false,false,true,true,true,false,false,false};
    
    int respectivePlaces[][] = {{pp1x,pp1y}, {pp2x,pp2y}, {pp3x,pp3y}, {pp4x,pp4y}, {pp5x,pp5y}, {pp6x,pp6y}, {pp7x,pp7y}, {pp8x,pp8y}, {pp9x,pp9y}};
    
    int winningPositions[][] = {{1,4,7},{1,7,4},{4,1,7},{4,7,1},{7,1,4},{7,4,1},
                                {2,5,8},{2,8,5},{8,2,5},{8,5,2},{5,2,8},{5,8,2},
                                {3,6,9},{3,9,6},{6,3,9},{6,9,3},{9,3,6},{9,6,3},
                                {4,5,6},{4,6,5},{5,4,6},{5,6,4},{6,5,4},{6,4,5},
                                {3,5,7},{3,7,5},{5,3,7},{5,7,3},{7,5,3},{7,3,5},
                                {1,5,9},{1,9,5},{5,1,9},{5,9,1},{9,5,1},{9,1,5},
                                };
    
    boolean toggleP1 = true;
    boolean toggleP2 = false;
    boolean toggleP3 = false;
    
    
    public void Moving(Graphics g){
        
        
        g.fillRect(0,0,w,l);
        g.setColor(0xFF0000);
        g.drawRect(w/8, l/8, w-w/4,l-l/4);
        g.drawLine(w/8,l/8, w-w/8,l-l/8);
        g.drawLine(w/8,l-l/8,w-w/8,l/8);
        g.drawLine(w/8, l/2, w-w/8,l/2);
        g.drawLine(w/2, l/8, w/2,l-l/8);
        
        
        if (toggleP1 == true)
        {
            g.setColor(0xFF0000); 
        g.fillRect(p1x, p1y, width,heigth);
            g.setColor(0x0FF000);
        g.fillRect(p2x, p2y,  width,heigth);
        g.fillRect(p3x,p3y,  width,heigth);
        }
        else if (toggleP2 == true)
        {
            g.setColor(0x0FF000);
        g.fillRect(p1x, p1y, width,heigth);
        g.fillRect(p3x,p3y,  width,heigth);
             g.setColor(0xFF0000);
        g.fillRect(p2x, p2y,  width,heigth);
        }
        else 
        {
            g.setColor(0x0FF000);
        g.fillRect(p1x, p1y, width,heigth);
        g.fillRect(p2x, p2y,  width,heigth);
            g.setColor(0xFF0000);
        g.fillRect(p3x,p3y,  width,heigth);
        }
        
        g.setColor(0xFFFF00);
        g.fillRect(p7x,p7y,width,heigth);
        g.fillRect(p8x,p8y,width,heigth);
        g.fillRect(p9x, p9y,width,heigth);
        
        Font font = g.getFont();
        int fontHeight = font.getHeight();
        int fontWidth = font.stringWidth("You Win");
        
        g.setColor(0xffffff);
        
        int userPattern[] = {determinePosition(p1x,p1y),determinePosition(p2x,p2y),determinePosition(p3x,p3y)};
        int computerPattern[] = {determinePosition(p7x,p7y),determinePosition(p8x,p8y),determinePosition(p9x,p9y)};
        
        
        
        for (int i  = 0 ; i <= winningPositions.length ; i++)
        {

            if (matches(winningPositions[i], userPattern))
            {
                g.drawString("You Win", (w - fontWidth)/2  , (l - fontHeight)/2 , g.TOP|g.LEFT);
                repaint();
                stop();   
            }
            else if (matches(winningPositions[i], computerPattern))
            {
                g.drawString("Computer Win", (w - fontWidth)/2  , (l - fontHeight)/2 , g.TOP|g.LEFT);
                repaint();
                stop();
            }
        }

    }
    
    
    public static boolean matches(int[] first, int[] second) {
       
       int numMatches = 0;
       
       for(int i=0;i<first.length;i++)
       {
           
           for(int j=0;j<second.length;j++){
               if(first[i]==second[j]){
                   numMatches++;
                   break;
                }
           }   
       }
       return numMatches==3;
   }
    
    public void keyPressed(int k){
                
        if(k==KEY_NUM4 && activePlaces[3]){
            
            
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) || p1x == p4x )  ) // checks if there is a way
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                
                p1x=p4x;
                p1y=p4y;
                this.activePlaces[3] = false;
                computerMove() ;
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y) || p2x == p4x ) )
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=p4x;
                p2y=p4y;
                this.activePlaces[3] = false;
                computerMove() ;
            }
            else if (toggleP3 == true && ( (p3x == p5x && p3y == p5y)|| p3x == p4x ) )
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=p4x;
                p3y=p4y;
              this.activePlaces[3] = false;
                computerMove() ;
            }
            
            repaint();
        }
        
        else if(k==KEY_NUM1 && activePlaces[0]){
            
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) || (p1x == w/8 && p1y == l/2-10  )|| (p1y == l/8 && p1x == w/2-10)  )  ) 
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=w/8;
                p1y=l/8;
                this.activePlaces[0] = false;
                computerMove();
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y) || (p2x == w/8 && p2y == l/2-10  )|| (p2y == l/8 && p2x == w/2-10)  )  ) 
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=w/8;
                p2y=l/8;
                this.activePlaces[1-1] = false;
                computerMove();
            }
            else if (toggleP3== true && ( (p3x == p5x && p3y == p5y) || (p3x == w/8 && p3y == l/2-10  )|| (p3y == l/8 && p3x == w/2-10)  )  ) 
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=w/8;
                p3y=l/8;
                this.activePlaces[1-1] = false;
                computerMove();
            }
            
            repaint();
            
        }
        else if(k==KEY_NUM5 && activePlaces[4]){
            
            if( toggleP1==true  )
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=p5x;
                p1y=p5y;
                this.activePlaces[4] = false;
                computerMove();
            }
            else if (toggleP2 == true)
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=p5x;
                p2y=p5y;
                this.activePlaces[4] = false;
                computerMove();
            }
            else if (toggleP3== true)
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=p5x;
                p3y=p5y;
                this.activePlaces[4] = false;
                computerMove();
            }    
            repaint();
            

        }
        else if(k==KEY_NUM6 && activePlaces[5] ){
            
             
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) || p1x == p6x  ) ) // checks if there is a way
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=p6x;
                p1y=p6y;
                 this.activePlaces[5] = false;
                computerMove();
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y) || p2x == p6x || p2y == l-l/8-30) )
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=p6x;
                p2y=p6y;
                 this.activePlaces[5] = false;
                 computerMove();
            }
            else if (toggleP3== true && ( (p3x == p5x && p3y == p5y)|| p3x == p6x || p3y == l-l/8-30) )
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=p6x;
                p3y=p6y;
                this.activePlaces[5] = false;
                computerMove();
            }
            
            repaint(); 
        }
        else if(k==KEY_NUM7 && activePlaces[6] ){
            
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) || (p1x == w/8 && p1y == l/2-10) || (p1y == l-l/8-30 && p1x == w/2-8 )))
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=w/8;
                p1y=l-l/8-30;
                 this.activePlaces[6] = false;
                 computerMove();
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y) ||  (p2x == w/8 && p2y == l/2-10) || (p2y == l-l/8-30 && p2x == w/2-8 ) ) )
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=w/8;
                p2y=l-l/8-30;
                this.activePlaces[6] = false;
                 computerMove();
            }
            else if (toggleP3== true && ( (p3x == p5x && p3y == p5y) ||  (p3x == w/8 && p3y == l/2-10) || (p3y == l-l/8-30 && p3x == w/2-8 ) ) )
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=w/8;
                p3y=l-l/8-30;
                 this.activePlaces[6] = false;
                 computerMove();
            }
            
            repaint();
            
         }
        else if(k==KEY_NUM8 && activePlaces[7]){
            
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) || (p1x == w/8 && p1y == l-l/8-30) || (p1y == l-l/8-30 && p1x == w-w/4+2 ))) // 
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=w/2-8;
                p1y=l-l/8-30;
                this.activePlaces[7] = false;
                computerMove();
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y) ||  (p2x == w/8 && p2y == l-l/8-30) || (p2y == l-l/8-30 && p2x == w-w/4+2 ) ) )
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=w/2-8;
                p2y=l-l/8-30;
                this.activePlaces[7] = false;
                computerMove();
            }
            else if (toggleP3== true && ( (p3x == p5x && p3y == p5y)||  (p3x == w/8 && p3y == l-l/8-30) || (p3y == l-l/8-30 && p3x == w-w/4+2 ) ) )
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=w/2-8;
                p3y=l-l/8-30;
                this.activePlaces[7] = false;
                computerMove();
            } 
            repaint();
        }
        
        
        else if (k==KEY_NUM9 && activePlaces[8])
        {
          
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) || (p1x == w/2-8 && p1y == l-l/8-30) || (p1y == l/2-10 && p1x == w-w/4 ))) // checks if there is a way
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=w-w/4+2;
                p1y=l-l/8-30;
                this.activePlaces[8] = false;
                computerMove();
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y) ||  (p2x == w/2-8 && p2y == l-l/8-30) || (p2y == l/2-10 && p2x == w-w/4 ) ) )
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=w-w/4+2;
                p2y=l-l/8-30;
                this.activePlaces[8] = false;
                computerMove();
            }
            else if (toggleP3== true && ( (p3x == p5x && p3y == p5y)||  (p3x == w/2-8 && p3y == l-l/8-30) || (p3y == l/2-10 && p3x == w-w/4 ) ) )
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=w-w/4+2;
                p3y=l-l/8-30;
                this.activePlaces[8] = false;
                computerMove();
            }
             
            repaint();
        }
        
        else if (k == KEY_NUM0)
        {
            buttonSelected++; 
            
           int toggle = buttonSelected % 3;
            
            if ( toggle == 1)
            {
               toggleP1 = true;
               toggleP2= false;
               toggleP3=false;
            }
            else if(toggle == 2) 
              {
               toggleP1 = false;
               toggleP2= true;
               toggleP3=false;
              }
            else if (toggle == 0)
               {
               toggleP1 = false;
               toggleP2= false;
               toggleP3=true;
               }
            repaint();
        }
            
        else if (k==KEY_NUM2 && activePlaces[1])
        {
            
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) ||  p1y == l/8  )  ) // checks if there is a way
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=p5x;
                p1y=l/8;
                this.activePlaces[1] = false;
                computerMove();
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y) ||  p2y == l/8 ) )
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=p5x;
                p2y=l/8;
                this.activePlaces[1] = false;
                computerMove();
            }
            else if (toggleP3== true && ( (p3x == p5x && p3y == p5y)||  p3y == l/8 ) )
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=p5x;
                p3y=l/8;
                this.activePlaces[1] = false;
                computerMove();
            }
             
            repaint();
            
        }
                
        else if (k==KEY_NUM3 && activePlaces[2])
        {
            
            if( toggleP1==true && ( (p1x == p5x && p1y == p5y) || (p1x == w/2-10 && p1y == l/8) ||(p1x == w-w/4 && p1y == l/2-10)  )  ) // checks if there is a way
            {
                this.activePlaces[determinePosition(p1x,p1y)-1] = true;
                p1x=w-w/4;
                p1y=l/8;
                this.activePlaces[2] = false;
                computerMove();
            }
            else if (toggleP2 == true && ( (p2x == p5x && p2y == p5y)|| (p2x == w/2-10 && p2y == l/8) ||(p2x == w-w/4 && p2y == l/2-10)  )  )
            {
                this.activePlaces[determinePosition(p2x,p2y)-1] = true;
                p2x=w-w/4;
                p2y=l/8;
                this.activePlaces[2] = false;
                computerMove();
            }
            else if (toggleP3== true && ( (p3x == p5x && p3y == p5y)|| (p3x == w/2-10 && p3y == l/8) ||(p3x == w-w/4 && p3y == l/2-10)  )  )
            {
                this.activePlaces[determinePosition(p3x,p3y)-1] = true;
                p3x=w-w/4;
                p3y=l/8;
                this.activePlaces[2] = false;
                computerMove();
            }
             repaint();
        }
       }
    

    public static int[] Sort(int[] list) {
            for (int i = 0; i < list.length - 1; i++) {
                int currentMin = list[i];
                int currentMinIndex = i;
                
                for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                currentMin = list[j];
                currentMinIndex = j;
                }
                }

                if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] =  currentMin;
                }
        }
            return list;
    }
    
    public void computerMove() 
    {
        if(activePlaces[4] == true)
        {
            activePlaces[determinePosition(p9x,p9y)-1] = true;
            p9x=p5x;
            p9y=p5y;
            activePlaces[4] = false;
            return;
        }
        else 
        {   
           for (int x = 0; x < checkNearbyPoints(p7x,p7y)[0].length; x++ )
           {
               if ( activePlaces[checkNearbyPoints(p7x,p7y)[0][x] - 1 ])
               {
                    int tempPlaceTOGO = checkNearbyPoints(p7x,p7y)[0][x] - 1;
                   this.activePlaces[determinePosition(p7x,p7y)-1] = true;
                   p7x = respectivePlaces[tempPlaceTOGO][0];
                   p7y = respectivePlaces[tempPlaceTOGO][1];
                   this.activePlaces[tempPlaceTOGO] = false;
                   return;
               }
           }
           
           for (int x = 0; x < checkNearbyPoints(p8x,p8y)[0].length; x++ )
           {
               if ( activePlaces[checkNearbyPoints(p8x,p8y)[0][x] - 1 ])
               {
                    int tempPlaceTOGO = checkNearbyPoints(p8x,p8y)[0][x] - 1;
                   this.activePlaces[determinePosition(p8x,p8y)-1] = true;
                   p8x = respectivePlaces[tempPlaceTOGO][0];
                   p8y = respectivePlaces[tempPlaceTOGO][1];
                   this.activePlaces[tempPlaceTOGO] = false;
                   return;
               }
           }
           
           for (int x = 0; x < checkNearbyPoints(p9x,p9y)[0].length; x++ )
           {
               if ( activePlaces[checkNearbyPoints(p9x,p9y)[0][x] - 1 ])
               {
                   int tempPlaceTOGO = checkNearbyPoints(p9x,p9y)[0][x] - 1;
                   this.activePlaces[determinePosition(p9x,p9y)-1] = true;
                   p9x = respectivePlaces[tempPlaceTOGO][0];
                   p9y = respectivePlaces[tempPlaceTOGO][1];
                   this.activePlaces[tempPlaceTOGO] = false;
                   return;
               }
           }
        } 
        repaint();
    }
    
 
    public int determinePosition(int xx, int yy)
    {
        if(xx == pp1x && yy == pp1y){
            return 1;
        }
        else if(xx == pp2x && yy == pp2y){
            return 2;
        }
        else if(xx == pp3x && yy == pp3y){
            return 3;
        }
        else if(xx == pp4x && yy == pp4y){
            return 4;
        }
        else if(xx == pp5x && yy == pp5y){
            return 5;
        }
        else if(xx == pp6x && yy == pp6y){
            return 6;
        }
        else if(xx == pp7x && yy == pp7y){
            return 7;
        }
        else if(xx == pp8x && yy == pp8y){
            return 8;
        }
        else {
            return 9;
        }
    }

    
    public int[][] checkNearbyPoints(int xx, int yy){
        
        if(xx == pp1x && yy == pp1y){
            int r[][] = {{2,4,5},{1}};
            return r;
        }
        else if(xx == pp2x && yy == pp2y){
            int r[][] = {{1,3,5},{2}};
            return r;
        }
        else if(xx == pp3x && yy == pp3y){
            int r[][] = {{2,5,6},{3}};
            return r;
        }
        else if(xx == pp4x && yy == pp4y){
            int r[][] = {{1,5,7},{4}};
            return r;
        }
        else if(xx == pp5x && yy == pp5y){
            int r[][] = {{1,2,3,4,6,7,8,9},{5}};
            return r;
        }
        else if(xx == pp6x && yy == pp6y){
            int r[][] = {{3,5,9},{6}};
            return r;
        }
        else if(xx == pp7x && yy == pp7y){
            int r[][] = {{4,5,8},{7}};
            return r;
        }
        else if(xx == pp8x && yy == pp8y){
            int r[][] = {{5,7,9},{8}};
            return r;
        }
        else{
            int r[][] = {{5,6,8},{9}};
            return r;}
    }
    protected void paint(Graphics g) {
            this.Moving(g);
        }
    public  void restart() 
    {
        p1x=w/8;
        p1y=l/8;
        p2x=w/2-10;
        p2y=l/8;
        p3x =w-w/4;
        p3y =l/8;
     
        p7x=w/8;
        p7y=l-l/8-30;
        p8x=w/2-8;
        p8y=p7y;
        p9x=w-w/4+2;
        p9y=p7y;
        activePlaces[0]=false;
        activePlaces[1]=false;
        activePlaces[2]=false;
        activePlaces[3]=true;
        activePlaces[4]=true;
        activePlaces[5]=true;
        activePlaces[6]=false;
        activePlaces[7]=false;
        activePlaces[8]=false;
        
     repaint();
    }
    public void stop()
    {
        activePlaces[0]=false;
        activePlaces[1]=false;
        activePlaces[2]=false;
        activePlaces[3]=false;
        activePlaces[4]=false;
        activePlaces[5]=false;
        activePlaces[6]=false;
        activePlaces[7]=false;
        activePlaces[8]=false;
    }
}
