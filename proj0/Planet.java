import static java.lang.Math.abs;

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)
                + (yyPos - p.yyPos)*(yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos-xxPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dx / r;
    }

    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos-yyPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double num = 0;
        for(Planet p:planets){
            if(this.equals(p)){
                continue;
            }
            num += calcForceExertedByX(p);
        }
        return num;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double num = 0;
        for(Planet p:planets){
            if(this.equals(p)){
                continue;
            }
            num += calcForceExertedByY(p);
        }
        return num;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;

    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+ imgFileName);
    }





}
