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

void setup() {
  size(800, 800, P3D); 
  strokeWeight(1);

  noiseDetail(3);
  for (int i=0; i<10; i++) {
    pos = new PVector(width/2, height/2, 0);
    vel = new PVector(random(-0.4, 0.4), random(-0.4, 0.4), random(-0.4, 0.4));
    straal = random(30, 70);
    cnt=floor(random(400, 1000));
    bol = new Bol(straal, pos, vel, cnt);
    bollen.add(bol);
  }
}

void draw() {
  background(0);


  for (int i = 0; i < bollen.size(); i++) {
    bol=bollen.get(i);
    bol.update();
    bol.display();
    if(bol.isEmpty()){
     bollen.remove(i);
     pos = new PVector(width/2, height/2, 0);
    vel = new PVector(random(-0.4, 0.4), random(-0.4, 0.4), random(-0.4, 0.4));
    straal = random(30, 70);
    cnt=floor(random(600, 1000));
    bol = new Bol(straal, pos, vel, cnt);
    bollen.add(bol);
    }
  }
}