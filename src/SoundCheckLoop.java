import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
/**
 * 
 * Spielt Sounds in einer Schleife ab
 * 
 *
 */
	   
public class SoundCheckLoop extends Thread{
	
	            
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