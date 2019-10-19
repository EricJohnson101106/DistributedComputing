//  Eric Johnson - 000570883
//  Distributed Computing
//  Spring 2019
//  Project - Berkeley Algorithm
//  A pseudo-simulation of randomly generated nodes with variances in clocks that form an intranet system
//  Will synchronize each node using the Berkeley Algorithm discussed in the textbook and in class

//  Class that will generate the specified number of nodes with random clocks for the simulation

public class ejNode {

    // Node object that stores the Node Number corresponding to the number it is within the simulated system
    // Stores the initial time the node has (will be generated randomly)
    // Stores the timeDifference which is the difference between the average time of all nodes in the system and the
    //  current time the node has. Used to gradually synchronize the time such that the timeDifference is 0
    private double time = 0;
    private int nodeNumber = 0;
    private double timeDifference = 0;

    // Default constructor
    public ejNode() {
        nodeNumber = 0;
        time = 0;
    }

    // Constructor given time and the node number
    public ejNode(double time, int nodeNumber) {
        this.time = time;
        this.nodeNumber = nodeNumber;
    }

    // Constructor given time. Node number doesn't matter. Useful for a server node (non-election)
    public ejNode(double time) {
        this.time = time;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }


    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(double timeDifference) {
        this.timeDifference = timeDifference - time;
    }


}
