import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

	   /**
	    * 
	    * eine musikdatei wird als endlosscheife gespielt
	    * ein thread wird dafür verwendet	     
	  */
public class SoundCheckLoop extends Thread{
	/**
	 * 
	 * @param args der speicherort der datei und sein name
	 */
	            
				public SoundCheckLoop(String args) {           
	    
		         try {
			         
			         URL url = this.getClass().getClassLoader().getResource(args);
			         AudioInputStream AIS_1 = AudioSystem.getAudioInputStream(url);
			        
			         Clip clip = AudioSystem.getClip();
			         			         
			         clip.open(AIS_1);
			         clip.loop(Clip.LOOP_CONTINUOUSLY);  
			         			                  
			      } catch (UnsupportedAudioFileException e) {
			         e.printStackTrace();
			      } catch (IOException e) {
			         e.printStackTrace();
			      } catch (LineUnavailableException e) {
			         e.printStackTrace();
			      }
		         
		          
			}
}