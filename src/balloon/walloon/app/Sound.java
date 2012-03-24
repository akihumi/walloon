package balloon.walloon.app;

import android.media.MediaPlayer;

<<<<<<< HEAD

public class Sound {
    private Walloon _wa;
	private MediaPlayer[] mp = new MediaPlayer[Const.WALLOONN_NUMBER];
	private MediaPlayer[] voice_mp = new MediaPlayer[Const.WALLOONN_NUMBER];
 	private MediaPlayer[] laugh_voice = new MediaPlayer[Const.LAUGH_NUMBER];
	int laugh_change = 0;
=======
public class Sound {
    private static final int WALLOONN_NUMBER = 5;
    private Walloon _wa;
	private MediaPlayer[] mp = new MediaPlayer[WALLOONN_NUMBER];
	private MediaPlayer[] voice_mp = new MediaPlayer[WALLOONN_NUMBER];
 	private MediaPlayer[] laugh_voice = new MediaPlayer[3];
	private int laugh_change = 0;
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
	

	
	public Sound(Walloon wa){		
		_wa = wa;
	    //  笑い声の生成
<<<<<<< HEAD
		for(int i = 0; i < Const.LAUGH_NUMBER; i++)
			laugh_voice[i] = MediaPlayer.create(_wa, Const.LAUGH_ID[i]);
	    //  破裂音の生成
		for(int i = 0; i < Const.WALLOONN_NUMBER; i++){
=======
		laugh_voice[0] = MediaPlayer.create(_wa, R.raw.warai1);
		laugh_voice[1] = MediaPlayer.create(_wa, R.raw.warai2);
		laugh_voice[2] = MediaPlayer.create(_wa, R.raw.warai3);
	    //  破裂音の生成
		for(int i = 0; i < WALLOONN_NUMBER; i++){
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
			mp[i] = MediaPlayer.create(_wa, R.raw.bomb);
			voice_mp[i] = MediaPlayer.create(_wa, R.raw.bomb_vo);
		}
	}
	public MediaPlayer getBombSound(int idx ,boolean flag){
		if(flag)
		  return mp[idx];
		else
		  return voice_mp[idx];
	}
	public void getKakusiSound(int idx, int num){
		if(num % 50 == 0 && num != 0)
		  laugh_voice[idx].start();
	}
	public MediaPlayer getKakusiSound(){
		return laugh_voice[2];
	}

<<<<<<< HEAD
	public int laughChange(boolean flag){
		if(flag){
		   laugh_change = 1;
		}else{
		   laugh_change = 0; 
=======
	public int laughChange(){
		switch(laugh_change){
		  case 0: laugh_change = 1; break;
		  case 1: laugh_change = 0; break;
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
	    }
		return laugh_change;
	}

}
