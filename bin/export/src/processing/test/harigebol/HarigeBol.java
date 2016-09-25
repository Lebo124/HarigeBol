package processing.test.harigebol;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class HarigeBol extends PApplet {

class Bol {
  ArrayList<Haar> haren = new ArrayList<Haar>();
  Haar haar;
  int aantal;
  float straal;
  PVector pos, vel;
  boolean shoot;
  int cnt;

  Bol (float straal_, PVector pos_, PVector vel_, int cnt_) {
    this.cnt=cnt_;
    this.straal = straal_;
    this.pos=pos_;
    this.vel=vel_;
    this.aantal= floor(random(400, 800));
    for (int i=0; i<this.aantal; i++) {
      haar = new Haar( random(TWO_PI), this.straal);
      haren.add(haar);
    }
  }

  public void update() {
    this.cnt--;

    this.pos.add(this.vel);
    this.straal -= 0.1f;
    if (this.cnt==0) {
      this.shoot=true;
    }
    if (this.cnt< -100) {
      haren.clear();
    }
  }


  public void display() {
    noStroke();
    pushMatrix();
    noFill();
    lights();
    translate(this.pos.x, this.pos.y, this.pos.z);
    for (int i=0; i<haren.size(); i++) {
      haar = haren.get(i);
      haar.update();
      haar.display();
    }
    sphere(this.straal);
    popMatrix();
  }
  public boolean isEmpty() {
    if (haren.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }
}
class Haar {

  float zet;
  float phi;
  float lengte;
  float theta;
  int aantal;
  float straal;
  float x, y, z, xo, yo, zo, xb, yb, zb;
  float off, offb, thetaff, phff;
  Haar( float phi_, float straal_) {

    this.phi = phi_; 
    this.straal=straal_;
    this.zet = random(-this.straal, this.straal);
    this.lengte= map(this.straal, 30, 70, 0.7f, 1.2f);
    this.aantal= floor(random(100, 300));
    this.theta= asin(this.zet/this.straal);
  }

  public void display() {

    stroke(x*8, y*8, z*5);
    beginShape(LINES); 
    vertex(x, y, z);
    vertex(xb, yb, zb);
    endShape();
    noStroke();
  }
  public void update() {
    if (bol.shoot) {
      this.straal += 8;
    }
    off = (noise(millis() * 0.0005f, sin(this.phi))-0.5f) * 0.3f;
    offb = (noise(millis() * 0.0007f, sin(this.zet) * 0.01f)-0.5f) * 0.3f;

    thetaff = theta+off;
    phff = this.phi+offb;
    x = this.straal * cos(this.theta) * cos(this.phi);
    y = this.straal * cos(this.theta) * sin(this.phi);
    z = this.straal * sin(this.theta);

    //float msx= screenX(x, y, this.z);
    //float msy= screenY(x, y, this.z);

    xo = this.straal * cos(thetaff) * cos(phff);
    yo = this.straal * cos(thetaff) * sin(phff);
    zo = this.straal * sin(thetaff);

    xb = xo * lengte;
    yb = yo * lengte;
    zb = zo * lengte;
  }
}
/** //<>// //<>//
 * Noise Sphere 
 * by David Pena.  
 * 
 * Uniform random distribution on the surface of a sphere. 
 */



ArrayList<Bol> bollen = new ArrayList<Bol>(10);
Bol bol;

float straal;
PVector pos, vel;
int count=0;
int cnt;

public void setup() {
  
  strokeWeight(1);

  noiseDetail(3);
  for (int i=0; i<10; i++) {
    pos = new PVector(width/2, height/2, 0);
    vel = new PVector(random(-0.4f, 0.4f), random(-0.4f, 0.4f), random(-0.4f, 0.4f));
    straal = random(30, 70);
    cnt=floor(random(400, 1000));
    bol = new Bol(straal, pos, vel, cnt);
    bollen.add(bol);
  }
}

public void draw() {
  background(0);


  for (int i = 0; i < bollen.size(); i++) {
    bol=bollen.get(i);
    bol.update();
    bol.display();
    if(bol.isEmpty()){
     bollen.remove(i);
     pos = new PVector(width/2, height/2, 0);
    vel = new PVector(random(-0.4f, 0.4f), random(-0.4f, 0.4f), random(-0.4f, 0.4f));
    straal = random(30, 70);
    cnt=floor(random(600, 1000));
    bol = new Bol(straal, pos, vel, cnt);
    bollen.add(bol);
    }
  }
}

  public int sketchWidth() { return 800; }
  public int sketchHeight() { return 800; }
  public String sketchRenderer() { return P3D; }
}
