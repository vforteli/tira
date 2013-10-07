/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class StopWatch {

  private long startTime = 0;
  private long stopTime = 0;
  private boolean running = false;


  public void start() {
    this.startTime = System.nanoTime();
    this.running = true;
  }


  public void stop() {
    this.stopTime = System.nanoTime();
    this.running = false;
  }


  //elaspsed time in milliseconds
  public long getElapsedTime() {
    long elapsed;
    if (running) {
      elapsed = (System.nanoTime()- startTime);
    }
    else {
      elapsed = (stopTime - startTime);
    }
    return elapsed;
  }

}
