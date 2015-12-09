
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrimaryThread implements Runnable{
    public Vector<Integer> reqQ;//=new Vector<Integer>();;
    public Vector<Download> down;//=new Vector<Download>();; //maintains refrences to all the downloads to give them connections
    private static int ptr=0; //pointing to the req waiting in the q
    private static int con=8;
    Thread t;
    PrimaryThread(){
        System.out.println("In pthread cons no probs\n");
        reqQ=new Vector<Integer>();
        down=new Vector<Download>();
        t=new Thread(this);
        t.start();
    }
    void give_con() throws InterruptedException{
        
        while(true){
            
            while(!reqQ.isEmpty()){
                if(ptr>=reqQ.size())
                    ptr=0;
                if(con==0)
                    t.suspend();
                int top=reqQ.elementAt(ptr);
                
                //try to give con only when its in downloading state and file parts haven't completed
                if(down.elementAt(top).dsum<16&&down.elementAt(top).status==0) {
                 
                    int j; //to check if all the parts of that download have completed and its time to join them
                    connectionManager[] ref=down.elementAt(top).tMan;
                    for(j=0;j<8;j++)
                        if(ref[j].Tstatus==0) //means thread is free
                            break;
                    if(j!=8)
                        {ref[j].Tstatus=1; //busy now
                        con--;
                        System.out.println("give con"+con+"top"+top);
                        ref[j].makeThread();
                        }
                    //else no free point so move to another req in waiting q
                    ptr++;
                    }
                else if(down.elementAt(top).status==2||down.elementAt(top).status==3||down.elementAt(top).status==4) //not in paused state paused
                        {//System.out.println("remove "+top+" "+reqQ.size());
                        reqQ.remove(ptr);
                        }
                }
            }
        }
    //function for those threads who have completed their execution before time
    public void return_back(){
        System.out.println("got it back");
        con++;
        t.resume();
        return;
    }
    
    @Override
    public void run() {
        try {
            give_con();
        } catch (InterruptedException ex) {
            Logger.getLogger(PrimaryThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}

