import org.w3c.dom.ls.LSOutput;

public class Body {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public final static double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {

        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b2){
        return Math.sqrt(Math.pow(this.xxPos - b2.xxPos, 2) +
                Math.pow(this.yyPos - b2.yyPos, 2));
    }

    public double calcForceExertedBy(Body b2){
        return G * this.mass * b2.mass/(Math.pow(this.calcDistance(b2),2));
    }

    public double calcForceExertedByX(Body b2){
        return -1 * this.calcForceExertedBy(b2) * (this.xxPos - b2.xxPos)
                /this.calcDistance(b2);
    }

    public double calcForceExertedByY(Body b2){
        return -1 * this.calcForceExertedBy(b2) * (this.yyPos - b2.yyPos)
                /this.calcDistance(b2);
    }

    public double calcNetForceExertedByX(Body[] Bodies){
        double NetforceX = 0;
        for (Body element: Bodies){
            if (!this.equals(element)){
                NetforceX += this.calcForceExertedByX(element);
            }
        }
        return NetforceX;
    }

    public double calcNetForceExertedByY(Body[] Bodies){
        double NetforceY = 0;
        for (Body element: Bodies){
            if (!this.equals(element)){
                NetforceY += this.calcForceExertedByY(element);
            }
        }
        return NetforceY;
    }

    public void update(double dt, double fX, double fY){
        double accexx = fX/this.mass;
        double acceyy = fY/this.mass;

        this.xxVel += accexx * dt;
        this.yyVel += acceyy *dt;

        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }


}
