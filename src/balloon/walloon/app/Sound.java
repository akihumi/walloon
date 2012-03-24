package balloon.walloon.app;

import android.media.MediaPlayer;


public class Sound {
    private Walloon _wa;
	private MediaPlayer[] mp = new MediaPlayer[Const.WALLOONN_NUMBER];
	private MediaPlayer[] voice_mp = new MediaPlayer[Const.WALLOONN_NUMBER];
 	private MediaPlayer[] laugh_voice = new MediaPlayer[Const.LAUGH_NUMBER];
	int laugh_change = 0;
	

	
	public Sound(Walloon wa){		
		_wa = wa;
	    //  笑い声の生成
		for(int i = 0; i < Const.LAUGH_NUMBER; i++)
			laugh_voice[i] = MediaPlayer.create(_wa, Const.LAUGH_ID[i]);
	    //  破裂音の生成
		for(int i = 0; i < Const.WALLOONN_NUMBER; i++){
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

	public int laughChange(boolean flag){
		if(flag){
		   laugh_change = 1;
		}else{
		   laugh_change = 0; 
	    }
		return laugh_change;
	}

}
