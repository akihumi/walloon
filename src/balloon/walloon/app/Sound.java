package balloon.walloon.app;

import android.media.MediaPlayer;

public class Sound {
    private static final int WALLOONN_NUMBER = 5;
    private Walloon _wa;
	private MediaPlayer[] mp = new MediaPlayer[WALLOONN_NUMBER];
	private MediaPlayer[] voice_mp = new MediaPlayer[WALLOONN_NUMBER];
 	private MediaPlayer[] laugh_voice = new MediaPlayer[3];
	private int laugh_change = 0;
	

	
	public Sound(Walloon wa){		
		_wa = wa;
	    //  笑い声の生成
		laugh_voice[0] = MediaPlayer.create(_wa, R.raw.warai1);
		laugh_voice[1] = MediaPlayer.create(_wa, R.raw.warai2);
		laugh_voice[2] = MediaPlayer.create(_wa, R.raw.warai3);
	    //  破裂音の生成
		for(int i = 0; i < WALLOONN_NUMBER; i++){
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
	public void getKakusiSound(int idx){
		laugh_voice[idx].start();
	}
	public MediaPlayer getKakusiSound(){
		return laugh_voice[2];
	}

	public int laughChange(){
		switch(laugh_change){
		  case 0: laugh_change = 1; break;
		  case 1: laugh_change = 0; break;
	    }
		return laugh_change;
	}

}
