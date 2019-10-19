//  Eric Johnson - 000570883
//  Distributed Computing
//  Spring 2019
//  Project - Berkeley Algorithm
//  A pseudo-simulation of randomly generated nodes with variances in clocks that form an intranet system
//  Will synchronize each node using the Berkeley Algorithm discussed in the textbook and in class

//  Main Class coordinating the program

//  Time server periodically sends its time to all the computers and polls them for the time diff
//  The computers compute the time difference the reply
//  The server computes an average time difference for each computer
//  The server commands all the computers to update their time (by gradual time synchronization)


import java.util.Scanner;

public class BerkeleyAlgorithm {

    private static int arraySize = 0;
    private static int upperBoundForTime = 0;
    private static double timeDifference = 0;
    private static Scanner scan = new Scanner(System.in);

    private static ejNode[] nodes;

    public static void main(String[] args) {

        //Ask user for the number of nodes in simulation
        System.out.println("Enter amount of nodes: ");
        arraySize = scan.nextInt();

        //Ask user for an upper bound for the random times that will be assigned to each node.
        System.out.println("Enter an upper bound for random time generation: ");
        upperBoundForTime = scan.nextInt();

        //Create a node to act as the server. Will be half of the upper bound so it's roughly in the middle
        ejNode server = new ejNode(upperBoundForTime / 2); //no election for master node, simply assigning one
        nodes = new ejNode[arraySize];

        //Initialize array of ejNodes to the size specified by user input. Will assigned ejNode a number corresponding
        //to instantiation and assign it a random time between 0 - upperBoundForTime which is specified by user input
        for (int i = 0; i < arraySize; i++) {
            double randomTime =  (Math.random() * upperBoundForTime);
            nodes[i] = new ejNode(randomTime, i);
        }

        //Output each ejNode within the nodes array showing the node number and the time it currently has.
        //Afterwards will display the server time
        for (int i = 0; i < arraySize; i++) {
            System.out.println("Node: " + nodes[i].getNodeNumber() + " \t time: " + nodes[i].getTime());
        }
        System.out.println("Server Time: " + server.getTime());

        //Call method to determine the time difference of all nodes present in system then outputs
        DetermineTimeDifference(nodes.length, server.getTime());
        System.out.println("Average Time: " + timeDifference);

        //Begin to output the node number as well as the time the node has in addition to the amount the node needs to
        //change gradually in order to be synchronized. Will do all nodes present and then output the server.
        //If the time difference is a negative number, node needs to reduce its clock.
        //If the time difference is 0, the node is already synchronized
        //Else, node needs to increase the clock
        for (int i = 0; i < arraySize; i++) {
            nodes[i].setTimeDifference(timeDifference);
            System.out.print("Node: " + nodes[i].getNodeNumber() + " \t time: " + nodes[i].getTime());
            if (nodes[i].getTimeDifference() < 0) {
                System.out.print("\t Clock will reduce " + Math.abs(nodes[i].getTimeDifference()) + " seconds gradually\n");
            } else if (nodes[i].getTimeDifference() == 0) {
                System.out.print("\t Clock is synced. No changes\n");
            } else {
                System.out.print("\t Clock will increase " + Math.abs(nodes[i].getTimeDifference()) + " seconds gradually\n");
            }
        }

        //output the server time and the time difference it needs to gradually change in order to be synchronized
        server.setTimeDifference(timeDifference);
        System.out.print("Server time: " + server.getTime());
        if (server.getTimeDifference() < 0) {
            System.out.print("\t Clock will reduce by " + Math.abs(server.getTimeDifference()) + "\n");
        } else if (server.getTimeDifference() == 0) {
            System.out.print("\t Clock is synced. No changes\n");
        } else {
            System.out.print("\t Clock will increase by " + Math.abs(server.getTimeDifference()) + "\n");
        }
    } //end psvm



    //  Compute the average of all the clocks within the simulated network by adding together the times from each
    //  node in the generated array as well as the server time.
    //  Add all nodes and server time together, divide by the amount of nodes + 1 (for the server)
    private static void DetermineTimeDifference(int nodeCount, double serverTime) {
        double hold = 0;
        for (int i = 0; i < nodeCount; i++) {
            hold += nodes[i].getTime();
        }
        timeDifference = ((hold + serverTime) / (nodeCount + 1));
    }

}
