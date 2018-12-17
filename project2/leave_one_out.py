import math
import numpy as np
from nearest_neighbor import nearest_neighbor

def leave_one_out_cross_validation(data, current_set, feature_to_add):
    rows = data.shape[0]
    number_of_features = data.shape[1] - 1
    number_correct = 0
    actual_class_label = 0
    classified_label = 0
    set_of_features = set(list(current_set))
    if feature_to_add <= number_of_features and feature_to_add > 0:
        set_of_features.add(feature_to_add)
    list_of_features = list(set_of_features)
    # For each point in data, go through each data point and 
    # calculate it's nearest neighbor. Count how many classifications
    # are correct
    for row in range(0, rows):
        class_label = data[row][0]
        classified_label = nearest_neighbor(data, list_of_features, row)
        if(class_label == classified_label):
            number_correct += 1
    # Get the accuracy for the nearest neighbor classifier    
    accuracy = float(number_correct/rows)
    return accuracy
