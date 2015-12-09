
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

class connectionManager implements Runnable{
    static int MAX_BUFFER_SIZE=1024;
    long Tstart;
    long Tend;
    int Tstatus=0; //0 for idle 1 for busy 2 for complete
    int idx; //index of the part that thread is downoading 
    int iflag=0; //interrupt flag set by the primary thread when its time is ove
    private Download did;
    Thread t;
    connectionManager(int size,Download did){
        this.did=did;
        idx=size;
        return;
    }
    public void set(int size, long start, long end) {
        //System.out.println("Divided file into"+size);
            this.Tstart=start;
            this.Tend=end;
        //System.out.println("Divided file into"+size);
            return;
        }
    void makeThread(){
         //System.out.println("made subthred");
        this.t=new Thread(this);
        this.t.start();
        try {
            sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run(){//System.out.println("in thread");
        HttpURLConnection httpConn;
        String saveFilePath = "C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\Partial Downloads\\"+idx+did.getFileName();
        FileOutputStream outputStream=null;
        InputStream inputStream=null ;
        boolean closedF=true,closedS=true;
        String proxyIP="172.31.102.29";
        String proxyPort="3128";
        String proxyUsername="edcguest";
        String proxyPassword="edcguest";
        Properties systemSettings = System.getProperties();
        systemSettings.put("http.proxyHost", proxyIP);
        systemSettings.put("http.proxyPort", proxyPort);
        System.setProperties(systemSettings);
        
        try {
            httpConn = (HttpURLConnection) (did.getUrl()).openConnection();
            String encoded = new String(new sun.misc.BASE64Encoder().encode(new String( proxyUsername + ":" + proxyPassword).getBytes()));
            httpConn.setRequestProperty("Proxy-Authorization", "Basic " + encoded);
            httpConn.setRequestProperty("Range", "Bytes="+this.Tstart+"-"+this.Tend);
            int responseCode=httpConn.getResponseCode();
            
            // -----------------------------check HTTP response code first--------------------------------------
            if (responseCode == 200 ||responseCode==206) {
               
                
            // opens input stream from the HTTP connection
            inputStream = httpConn.getInputStream();
            closedS=false;
            System.out.println("hey run"+idx+" "+Tstart+" "+Tend);
             
            // opens an output stream to save into file
            outputStream = new FileOutputStream(saveFilePath,true);
            closedF=false;
            int bytesRead = -1;
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            long stop=System.nanoTime()+TimeUnit.MILLISECONDS.toNanos(1000000);
            while ((bytesRead = inputStream.read(buffer)) != -1&&stop>System.nanoTime()) {
                outputStream.write(buffer, 0, bytesRead);
                Tstart+=bytesRead;
                did.downloaded+=bytesRead;
                if(iflag==1)
                    {System.out.println("interrupted"+idx);
                    break;
                    }
                }
            outputStream.close();
            closedF=true;
            inputStream.close();
            closedS=true;
            httpConn.disconnect();
            System.out.println("disconnected"+idx);
            } 
            }catch (IOException ex) {
                    Logger.getLogger(tempDown.class.getName()).log(Level.SEVERE, null, ex);
            }finally{ System.out.println("File downloaded"+did.getFileName()+" returning now");
            did.pid.return_back();
            if(closedF==false)
                try {
                    outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(closedS==false)
                try {
                    inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Tstart>=Tend)
                    {Tstatus=2;
                    did.dsum+=2;
                    }
                else
                    Tstatus=0;
            //did.stateChanged(2);
            }
    }
  void joinThread() {
            
        try {
            this.t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("joining threads");
            return;
        }

       
    };
