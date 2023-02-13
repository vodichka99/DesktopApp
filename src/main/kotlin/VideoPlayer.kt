package edu.jay.fyp.featureextractor.video

import com.sun.jna.Native
import com.sun.jna.NativeLibrary
import uk.co.caprica.vlcj.binding.LibVlc
import uk.co.caprica.vlcj.binding.RuntimeUtil
import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Color
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JPanel

class VideoPlayer {
    //    public static void main(final String[] args) {
    //        SwingUtilities.invokeLater(new Runnable() {
    //            @Override
    //            public void run() {
    //                new VideoPlayer().playVideo();
    //            }
    //        });
    //    }
    private val instance = libvlc_instance_t()
    fun playVideo() {
        NativeLibrary.addSearchPath(
            RuntimeUtil.getLibVlcLibraryName(),
            "C:\\Program Files\\VideoLAN\\VLC"
        )
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc::class.java)
        val frame = JFrame("vlcj Tutorial")
        val mediaPlayerFactory = MediaPlayerFactory()
        val c = Canvas()
        c.background = Color.black
        val p = JPanel()
        p.layout = BorderLayout()
        p.add(c, BorderLayout.CENTER)
        frame.add(p, BorderLayout.CENTER)
        val mediaPlayer = EmbeddedMediaPlayer(instance)
        mediaPlayer.videoSurface().set(mediaPlayerFactory.videoSurfaces().newVideoSurface(c))
//        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c))
        frame.setLocation(100, 100)
        frame.setSize(1050, 600)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
        val fc = JFileChooser()
        fc.showOpenDialog(null)
        val file = fc.selectedFile
        mediaPlayer.media().play(file.path)
    }

    fun playVideo(dir: String?) {
        NativeLibrary.addSearchPath(
            RuntimeUtil.getLibVlcLibraryName(),
            "C:\\Program Files\\VideoLAN\\VLC"
        )
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc::class.java)
        val frame = JFrame("vlcj Tutorial")
        val mediaPlayerFactory = MediaPlayerFactory()
        val c = Canvas()
        val mediaPlayer: EmbeddedMediaPlayer = EmbeddedMediaPlayer(instance)
        c.background = Color.black
        val p = JPanel()
        val button = JButton("Exit")
        button.addActionListener {
            mediaPlayer.release()
            frame.isVisible = false
        }
        p.layout = BorderLayout()
        p.add(c, BorderLayout.CENTER)
        frame.add(p, BorderLayout.CENTER)
        mediaPlayer.videoSurface().set(mediaPlayerFactory.videoSurfaces().newVideoSurface(c))
        frame.setLocation(100, 100)
        frame.setSize(1050, 600)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(button, BorderLayout.SOUTH)
        frame.isVisible = true
        mediaPlayer.media().play(dir)
    }
}