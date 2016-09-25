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

  void update() {
    this.cnt--;

    this.pos.add(this.vel);
    this.straal -= 0.1;
    if (this.cnt==0) {
      this.shoot=true;
    }
    if (this.cnt< -100) {
      haren.clear();
    }
  }


  void display() {
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
  boolean isEmpty() {
    if (haren.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }
}