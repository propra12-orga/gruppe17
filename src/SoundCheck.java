import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
/**
 * 
 * eine Klasse, die ein Musikfinel einal ausführt(als thread)
 *
 */
	   
public class SoundCheck extends Thread{
	/**
	 * 
	 * @param args der name/ort der datei
	 */
	            
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