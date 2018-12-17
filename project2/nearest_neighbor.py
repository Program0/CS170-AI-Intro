import math
import numpy as np

def nearest_neighbor(data, current_set_of_features, point_to_classify):    
    number_of_instances = data.shape[0]
    # Remove the point to classify from the data
    #mask = np.ones(number_of_instances, dtype=bool)
    #mask[[point_to_classify]] = False
    #result = data[mask,...]
    #size = result.shape[0]
    size = data.shape[0]
    class_label = 0
    min_distance = float('inf')

    # Go through all the data set and calculate distances from each point 
    # to the point to classify. 
    for i in range(0, size):
        if not i == point_to_classify:
            point_distance = eucledian_distance(data[i], data[point_to_classify],
                                            current_set_of_features)
            if point_distance < min_distance:
                min_distance = point_distance
                # Set the nearest neighbor 
                class_label = data[i][0]                                  
    return class_label

def eucledian_distance(point_a, point_b, current_set):
    # Iterate through each feature and get the distance
    # between point a and point b for each feature.
    number_of_features = len(current_set)
    distance = 0    
    for i in range(0, number_of_features):
        distance =  distance + (point_a[current_set[i]] - point_b[current_set[i]])**2
    return math.sqrt(distance)
