//import java.util.TimerTask;
//
//
//public class loescheBombe extends TimerTask {
//	BombenListe BombNode;
//	
//	public loescheBombe(Bombe B){
//		BombNode = Gamepanel.BombHead;
//		while(BombNode.next != null){
//			if(BombNode.next.getBombe().x == B.x && BombNode.next.getBombe().y == B.y){
//				BombNode.setNext(BombNode.next.getNext());
//				break;
//			}
//			BombNode = BombNode.next;
//			}
//
//		if(BombNode.next == null && BombNode.getBombe().x == B.x && BombNode.getBombe().y == B.y){
//			BombNode.setBombe(null);
//		}
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	}
//}
