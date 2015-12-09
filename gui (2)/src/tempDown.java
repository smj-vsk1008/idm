import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.currentThread;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Properties;
import java.util.Queue;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
 

public class tempDown extends Observable implements Runnable{
private String saveDir="C:\\Users\\user\\Desktop";
   // Max size of download buffer.
private static final int MAX_BUFFER_SIZE = 1024;
// These are the status names.
public static final String STATUSES[] = {"Downloading","Paused", "Complete", "Cancelled", "Error"};
// These are the status codes.
public static final int DOWNLOADING = 0;
public static final int PAUSED = 1;
public static final int COMPLETE = 2;
public static final int CANCELLED = 3;
public static final int ERROR = 4;
private URL url; // download URL
private int fileSize; // size of download in bytes
private int downloaded; // number of bytes downloaded
private int status; // current status of download
private static Queue<Integer> reqQ=new LinkedList<Integer>(); //request Q
private static int nthread=-1; //total no. of downloads
private static Vector<Thread> threadArray=new Vector<Thread>();

public class fileInfo{
        long start;
        long end;
        int idx;
        int status;
    }; 
class connectionManager implements Runnable {
    fileInfo[] info=new fileInfo[8];
    Vector<Thread> subThreads; 
    int idx; //points to the index which has to be downloaded
    public String fName;
    void makeThread(int j){
        idx=j;
        subThreads.addElement(new Thread());
        subThreads.elementAt(j).start();
    }
    public void run(){
        int i=idx;
        HttpURLConnection httpConn;
        try {
            httpConn = (HttpURLConnection) (url).openConnection();
            httpConn.setRequestProperty("Range", "Bytes="+info[i].start+"-"+info[i].end);
            //System.out.println("hey run"+fileURL);
            int responseCode=httpConn.getResponseCode();
                
            // -----------------------------check HTTP response code first--------------------------------------
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("hey run"+url);
                
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = i+fName;
            System.out.println("hey run"+url);
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath,true);
 
            int bytesRead = -1;
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            outputStream.close();
            inputStream.close();
            info[i].status=1;
            System.out.println("File downloaded");
            httpConn.disconnect();
            }
            }catch (IOException ex) {
            Logger.getLogger(tempDown.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    };

//Constructor for base
    public tempDown(URL Url){
        saveDir="C:\\Users\\user\\Desktop";
        this.url = Url;
        fileSize=-1;
    downloaded = 0;
    status = DOWNLOADING;
    nthread++;
    System.out.println("hey cons");
// Begin the download.
    reqQ.add(nthread);
    download(nthread);
}
    private void download(int thread_no){System.out.println("hey down");
    if(nthread==thread_no)//means to create a new thread
    {   threadArray.addElement(new Thread(this));
           threadArray.elementAt(nthread).start();
        }
    else //resume an existing thread
    {
        threadArray.elementAt(thread_no).resume();
    }
    }
    public String getURL(){
        return url.toString();
    }
    public int getSize() {
        return fileSize;
    }
    public float getProgress() {
        return ((float) downloaded / fileSize) * 100;
    }
    public void pause() {
        status = PAUSED;
        stateChanged();
    }
    // Resume this download.
    public void resume(int j) {
        status = DOWNLOADING;
        stateChanged();
        download(j);
    }
    // Cancel this download.
    public void cancel() {
        status = CANCELLED;
        stateChanged();
    }
    private void error() {
        status=ERROR;
        stateChanged();
    }
    private void stateChanged() {
        setChanged();
        notifyObservers();
     }
    public int getStatus() {
        return status;
    }
    
    synchronized int request_start(int id){
        if(reqQ.element()!=id)
            return -1;
        else
            return 1;
    } 
@SuppressWarnings("empty-statement")
    public void run() {
        int id=nthread;
 	
	String fileURL=getURL();
	System.out.println("hey run"+fileURL);
        try{
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
             
        //httpConn.setRequestProperty("Range", "Bytes="+downloaded+"-");
            //System.out.println("hey run"+fileURL);
        int responseCode = httpConn.getResponseCode();
        boolean support;
  System.out.println("hey run"+fileURL);
        // -----------------------------check HTTP response code first--------------------------------------
        if (responseCode == HttpURLConnection.HTTP_OK) {
            support=httpConn.getHeaderField("Accept-Ranges").equals("bytes");
            System.out.println("hey run"+fileURL);
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();
            if(contentLength<1)
                error();
            /*if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }*/
             fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                    fileURL.length());
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
            httpConn.disconnect();
        
            //------------now distribute it into segments of size greater than a min size----------------------------
            connectionManager tMan=new connectionManager();
            long MIN=1000;
            long part=contentLength/8;
            long start=0,end=part;
            int size=0;            // gives the no. of segments we have made
            if(support==true){
                while(end!=contentLength){
                    tMan.info[size].start=start;
                    while(end<MIN&&(end+part)<contentLength)
                            end+=part;
                    if(contentLength-end<MIN)
                        end=contentLength;
                    tMan.info[size].end=end;
                    start=end+1;
                    size++;
                    }
                }
            else
            {
                size=1;
                tMan.info[size].start=0;
                tMan.info[size].end=contentLength;
            }
            System.out.println("Divided file into"+size);
            //-----------------allocate thread-----------------------------------------
            tMan.fName=fileName;
            if(request_start(id)==-1)
                currentThread().suspend();
            for(int j=0;j<size;j++)
                tMan.makeThread(j);
            tMan.subThreads.elementAt(size-1).join();//wait for the end thread to complete its download
            //----------------now combine all the files----------------------------------
            FileOutputStream out=new FileOutputStream(fileName,true);
            int bytesRead = -1;
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            for(int j=0;j<size;j++)
            {   FileInputStream in=new FileInputStream(j+fileName);
                bytesRead=-1;
                while ((bytesRead = in.read(buffer)) != -1) {
                  System.out.println("bytes read"+bytesRead);
                  out.write(buffer, 0, bytesRead);
                }
                in.close();
            }
            out.close();

        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            error();
            }
        }catch(Exception e){
            error();
        }
        
    }
}
