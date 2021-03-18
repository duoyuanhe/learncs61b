public class NBody {

    public static double readRadius(String filename){
        In in = new In(filename);

        int n_planets = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);

        int n_planets = in.readInt();
        Body[] Bodies = new Body[n_planets];
        in.readDouble();

        for (int i = 0; i < n_planets; i+=1){
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvel = in.readDouble();
            double yvel = in.readDouble();
            double mass = in.readDouble();
            String imgname = in.readString();
            Body new_body = new Body(xpos,ypos,xvel,yvel,mass,imgname);
            Bodies[i] = new_body;
        }
        return Bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] Bodies = readBodies(filename);
        Double uni_radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-uni_radius, uni_radius);
        StdDraw.clear();



        for (double time = 0; time <= T; time += dt){

            double[] xForces = new double[Bodies.length];
            double[] yForces = new double[Bodies.length];

            for (int i = 0; i < Bodies.length; i+=1) {
                xForces[i] = Bodies[i].calcNetForceExertedByX(Bodies);
                yForces[i] = Bodies[i].calcNetForceExertedByY(Bodies);
            }

            for (int i = 0; i < Bodies.length; i+=1) {
                Bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0,0, "./images/starfield.jpg");
            StdDraw.show();

            for (Body element: Bodies){
                element.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", Bodies.length);
        StdOut.printf("%.2e\n", uni_radius);
        for (int i = 0; i < Bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                    Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
        }

    }



}
