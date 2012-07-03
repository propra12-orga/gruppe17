import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * Spiel einen Sound ab	   
 * 
 *
 */
public class SoundCheck extends Thread{
	
	            
				public SoundCheck(String args) {           
	    
		         try {
			         
			         URL url = this.getClass().getClassLoader().getResource(args);
			         AudioInputStream AIS_1 = AudioSystem.getAudioInputStream(url);
			        
			         Clip clip = AudioSystem.getClip();
			         			         
			         clip.open(AIS_1);
			         clip.start();       
			         			                  
			      } catch (UnsupportedAudioFileException e) {
			         e.printStackTrace();
			      } catch (IOException e) {
			         e.printStackTrace();
			      } catch (LineUnavailableException e) {
			         e.printStackTrace();
			      }
		         
		           
			}

				
						

				
}