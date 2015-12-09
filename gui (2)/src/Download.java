import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.URL;
import static java.nio.file.Files.delete;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Properties;

 

public class Download extends Observable implements Runnable{

private static final int MAX_BUFFER_SIZE = 1024;
public static final String STATUSES[] = {"Downloading","Paused", "Complete", "Cancelled", "Error"};
public static final int DOWNLOADING = 0;
public static final int PAUSED = 1;
public static final int COMPLETE = 2;
public static final int CANCELLED = 3;
public static final int ERROR = 4;

private float time;
private static int nthread=-1;
private int id;
private String saveDir;
private final URL url; // download URL
private int fileSize=0; // size of download in bytes
private String fileName;
int downloaded=0; // number of bytes downloaded
int status=DOWNLOADING; // current status of download
int dsum=0;//if 16 then all prats havr downloaded
Thread t;
PrimaryThread pid;
public connectionManager[] tMan=new connectionManager[8];
       
//Constructor for base
    public Download(URL Url,PrimaryThread pid){
        System.out.println("In download cons no probs\n");
        url = Url;
        time=0;
        this.pid=pid; //for refernece to the primary thread
        String s=url.toString();
        fileName = s.substring(s.lastIndexOf("/") + 1,s.length());
        pid.down.add(this);
        System.out.println("In download cons no probs"+fileName+"\n");
        nthread++;
        id=nthread;
        download();
    }
    
    private void download(){
        t=new Thread(this);
        t.start();
        }
    String getURL(){
        return url.toString();
    }
    URL getUrl(){
        return url;
    }
    String getFileName(){
        return fileName;
    }
    int getSize() {
        return fileSize;
    }
    float getProgress() {
        return ((float) downloaded / fileSize) * 100;
    }
    float getSpeed() {
        if(time==0)
            return 0;
        else
        return  downloaded/((System.nanoTime()-time)/1000000000);
    }
    public void pause() {
        //set the iflag of its threads as 1
        for(int j=0;j<8;j++)
            if(tMan[j]!=null)   
                tMan[j].iflag=1;
            else
                break;
        status = PAUSED;
        stateChanged(4);
    }
    public void resume() {
        status = DOWNLOADING;
         for(int k=0;k<8;k++)
               tMan[k].iflag=0;
        stateChanged(4);
    }
    public void cancel() {
        status = CANCELLED;
        for(int k=0;k<8;k++)
               tMan[k].iflag=1;
        stateChanged(4);
    }
    void error() {
        System.out.println("MY state has changed :( error");
        status=ERROR;
            stateChanged(4);
    }
    void stateChanged(int bool) { //bool to update that column
        setChanged();
        notifyObservers(bool);
     }
    public int getStatus() {
        return status;
    }
   
   
@SuppressWarnings("empty-statement")
    public void run() {
        for(int j=0;j<8;j++)
            tMan[j]=new connectionManager(j,this);
        String fileURL=url.toString();
	System.out.println("Setting proxy");
        String proxyIP="172.31.102.29";
        String proxyPort="3128";
        String proxyUsername="edcguest";
        String proxyPassword="edcguest";
        Properties systemSettings = System.getProperties();
        systemSettings.put("http.proxyHost", proxyIP);
        systemSettings.put("http.proxyPort", proxyPort);
        System.setProperties(systemSettings);
        
        try{boolean support=false;
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            String encoded = new String(new sun.misc.BASE64Encoder().encode(new String( proxyUsername + ":" + proxyPassword).getBytes()));
            httpConn.setRequestProperty("Proxy-Authorization", "Basic " + encoded);
            
            int responseCode = httpConn.getResponseCode();
  
        // -----------------------------check HTTP response code first--------------------------------------
        if (responseCode == 200 || responseCode == 206) {//System.out.println("connection established"+responseCode);
            try
            {String s=httpConn.getHeaderField("Accept-Ranges");
                if(s.equals("bytes"))
                support=true;
            }
            catch(Exception e){
            }
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            fileSize= httpConn.getContentLength();
            stateChanged(1); //since only length has to be updated
            if(fileSize<1)
                error();
            httpConn.disconnect();
            if(contentType.equalsIgnoreCase("zip")||contentType.equalsIgnoreCase("rar"))
                //compressed
                saveDir="C:\\Users\\user\\Desktop\\All Downloads\\Compressed" ;
               //System.out.println("Content-Type = " + contentType);
            else if(contentType.equalsIgnoreCase("png")||contentType.equalsIgnoreCase("jpg")||contentType.equalsIgnoreCase("gif")||contentType.equalsIgnoreCase("jpeg"))
                 saveDir="C:\\Users\\user\\Desktop\\All Downloads\\Images" ;
            else if(contentType.equalsIgnoreCase("flv")||contentType.equalsIgnoreCase("avi")||contentType.equalsIgnoreCase("mp3"))
                saveDir= "C:\\Users\\user\\Desktop\\All Downloads\\Video" ;
            else if(contentType.equalsIgnoreCase("mp4")||contentType.equalsIgnoreCase("wma")||contentType.equalsIgnoreCase("wmv"))
                 saveDir= "C:\\Users\\user\\Desktop\\All Downloads\\Music" ;
            else
                saveDir="C:\\Users\\user\\Desktop\\All Downloads\\Documents" ;   
            // rename the file
            File urltest = new File(saveDir+ fileName);
            // check if file exists
            String s=fileName;
            int j=0;
            while(urltest.exists()){
                j++;
                urltest = new File(saveDir+ fileName);
                fileName = fileName.substring(0, fileName.lastIndexOf(".")) + j + fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());   
                 }      
            //------------now distribute it into segments of size greater than a min size----------------------------
            long MIN=1000;
            long part=fileSize/8;
            long start=0,end=0;      // gives the no. of segments we have made
            for(j=0;j<8;j++){
                     end+=part;
                    tMan[j].Tstart=start;
                    tMan[j].Tend=end;
                    start=end+1;
                    }
          if(end!=fileSize)
              tMan[7].Tend=fileSize;
           
            //-----------------allocate thread-----------------------------------------
            System.out.println(pid.reqQ.size());
            pid.reqQ.add(id);
           // currentThread().suspend();  //wakes up when all its file downloads are complete
            time=System.nanoTime();
            while(dsum!=16){
                setChanged();
                notifyObservers(3);
                setChanged();
                notifyObservers(2);
                sleep(10);
            }
            //----------------now combine all the files----------------------------------
            
            FileOutputStream out=new FileOutputStream(saveDir+fileName,true);
            int bytesRead = -1;
            
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            int sum=0;
            for(j=0;j<8;j++)
            {   FileInputStream in=new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\Partial Downloads\\"+j+fileName);
                bytesRead=-1;
                //System.out.println("wish me luck");
                while ((bytesRead = in.read(buffer)) != -1) {
                  sum+=bytesRead;
                  out.write(buffer, 0, bytesRead);
                }
                in.close();
                s=j+fileName;
                Path path=Paths.get(s);
                 delete(path); 
                fileBrowser.fun(saveDir, fileName);
            }
            out.close();
             System.out.println("sum"+sum);
             downloaded=fileSize;
            status=COMPLETE;
            stateChanged(4);
        }else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            error();
            }
        }catch(Exception e){
            System.out.println("i am caught :(");
            error();
        }
        
    }

    
}
