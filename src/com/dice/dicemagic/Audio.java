package com.dice.dicemagic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.Map;

public class Audio {
    Map<String, Clip> clips = new HashMap<>();
    public Audio(){
        loadAudioClip("/resources/audio/bell.wav");
        loadAudioClip("/resources/audio/pops.wav");
        loadAudioClip("/resources/audio/rolling-dice.wav");
    }

    public void loadAudioClip(String soundName){
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(soundName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clips.put(soundName, clip);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void playAudioClip(String soundName){
        try {
            Clip c = clips.get(soundName);
            c.setFramePosition(0);
            c.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
