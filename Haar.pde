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
    this.lengte= map(this.straal, 30, 70, 0.7, 1.2);
    this.aantal= floor(random(100, 300));
    this.theta= asin(this.zet/this.straal);
  }

  void display() {

    stroke(x*8, y*8, z*5);
    beginShape(LINES); 
    vertex(x, y, z);
    vertex(xb, yb, zb);
    endShape();
    noStroke();
  }
  void update() {
    if (bol.shoot) {
      this.straal += 8;
    }
    off = (noise(millis() * 0.0005, sin(this.phi))-0.5) * 0.3;
    offb = (noise(millis() * 0.0007, sin(this.zet) * 0.01)-0.5) * 0.3;

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