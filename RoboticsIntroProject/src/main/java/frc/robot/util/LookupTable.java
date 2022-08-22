// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import java.util.List;

/** Add your docs here. */
public class LookupTable {
    List<Double> distances;
    List<Double> rpms;

    public LookupTable(List<Double> distances, List<Double> rpms) {
        this.distances = distances;
        this.rpms = rpms;
    }

    public int findLowerBound(double distance) {
        int index = 0;
        double smallestDifference = 0;

        for (int i = 0; i < distances.size(); i++) {
            if (distance - distances.get(i) <= smallestDifference && distance - distances.get(i) > 0) {
                index = i;
                smallestDifference = distance - distances.get(i);
            }
        }
        return index;
    }

    public double calculateRPM(double distance) {
        int smallerIndex = findLowerBound(distance);
        int higherIndex = smallerIndex + 1;
        double slope = (rpms.get(smallerIndex) - rpms.get(higherIndex)) / (distances.get(smallerIndex) - distances.get(higherIndex));
        double b = rpms.get(smallerIndex) - slope * distances.get(smallerIndex);
        return distance * slope + b;
    }
}
