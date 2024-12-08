//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlanetsSimulation.Planets arrakis = new PlanetsSimulation.Planets("Arrakis",12.0);
        PlanetsSimulation.Planets giediPrime = new PlanetsSimulation.Planets("GiediPrime", 60.0);

        Object obj = new Object();

        PlanetsSimulation.Planets.BaseStation baseStation = new PlanetsSimulation.Planets.BaseStation(arrakis,giediPrime,obj);
        PlanetsSimulation.Planets.Responder responder = new PlanetsSimulation.Planets.Responder(arrakis,giediPrime,obj);

        baseStation.start();
        responder.start();

        try {
            for (int time = 0; time < 100; time++) {
                arrakis.updatePosition(1);
                giediPrime.updatePosition(1);

                // Print positions for debugging
                System.out.println("Time: " + time + " LTU - Arrakis Position: " + arrakis.getPosition() + "°");
                System.out.println("Time: " + time + " LTU - Giedi Prime Position: " + giediPrime.getPosition() + "°");

                // If planets are aligned, notify the responder to take action
                synchronized (obj) {
                    if (arrakis.getPosition() - giediPrime.getPosition() <= PlanetsSimulation.sightRange) {
                        obj.notify();
                    }
                }
                // Simulate time passing (1 LTU unit)
                Thread.sleep(1000); // 1 LTU unit
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}