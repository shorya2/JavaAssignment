public class PlanetsSimulation {
    static final double ArrakisOrbitTime = 12.0;
    static final double GeidiPrimeOrbitTime = 60.0;
    static final double sightRange = 10.0;

    static class Planets {
        private double position;
        private final double orbitalPeriod;
        private final String planetName;

        Planets(String planetName, double orbitalPeriod) {
            this.planetName = planetName;
            this.orbitalPeriod = orbitalPeriod;
            this.position = 0.0;
        }

        public void updatePosition(int time) {
            position = (position + (360.0 / orbitalPeriod)) % 360;
        }

        public double getPosition() {
            return position;
        }

        static class BaseStation extends Thread{
            private Planets arrakis;
            private Planets giediPrime;
            private final Object obj;
            private final String instructions = "><>><</<<>>/";

            public BaseStation(Planets arrakis, Planets giediPrime, Object obj) {
                this.arrakis = arrakis;
                this.giediPrime = giediPrime;
                this.obj = obj;
            }

            @Override
            public void run(){
                try{
                        synchronized (obj){
                            double positionDifference = Math.abs(arrakis.getPosition() - giediPrime.getPosition());
                            while(positionDifference > sightRange || (360 - positionDifference) > sightRange){
                                obj.wait();
                            }

                            System.out.println("BaseStation is sending the signal: " + instructions);
                            Thread.sleep(1000);
                            obj.notify(); // here notifying the receiver
                        }
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }

        static class Responder extends Thread{
            private Planets arrakis;
            private Planets giediPrime;
            private final Object obj;

            public Responder(Planets arrakis, Planets giediPrime, Object obj) {
                this.arrakis = arrakis;
                this.giediPrime = giediPrime;
                this.obj = obj;
            }

            @Override
            public void run(){
                try{
                    synchronized (obj){
                        obj.wait();

                        double positionDifference = Math.abs(arrakis.getPosition() - giediPrime.getPosition());
                        if(positionDifference > sightRange || (360 - positionDifference) > sightRange){
                            System.out.println("Responder receives the instruction");
                            Thread.sleep(1000);
                            System.out.println("Rwsponder sends Acknowledgement");
                            obj.notify();
                        }
                    }
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
