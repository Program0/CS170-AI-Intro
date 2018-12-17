import math
import numpy as np

# Deprecated - does not yield predicted results. Used simple
# nearest neighbor instead
def k_nearest_neighbor(data, current_set_of_features, 
                       point_to_classify, k_nearest_neighbors):
    
    number_of_instances = data.shape[0]
    # Remove the point to classify from the data
    mask = np.ones(number_of_instances, dtype=bool)
    mask[[point_to_classify]] = False
    result = data[mask,...]
    size = result.shape[0]

    point_distances = np.zeros(shape=(size,2))
    # Go through all the data set and calculate distances from each point 
    # to the point to classify. Set this to a numpy array with two columns
    # the distance is first column and class is second column
    for i in range(0, size): 
        point_distances[i]=[distances(result[i], 
                                      data[point_to_classify],
                                      current_set_of_features),
                            result[i][0]]
    # Sort by distance ascending
    point_distances.view('f8,f8').sort(order=['f0'], axis=0)
    
    # Get the k_nearest_neighbors
    k_neighbors = point_distances[0:k_nearest_neighbors,:]
    # Cast the class labels to int
    class_instances = k_neighbors[:,1].astype(int)
    # Get the class frequency
    class_frequency = np.bincount(class_instances)
    # Get the class values
    class_values = np.nonzero(class_frequency)[0]    
    class_distribution = np.vstack((class_values,class_frequency[class_values])).T
    # Now get the most frequent class label
    max_value = 0
    class_group = 0
    for row in range(0,class_distribution.shape[0]):
        if class_distribution[row][1] > max_value:
            max_value = class_distribution[row][1]
            class_group = class_distribution[row][0]
    return class_group

def distances(point_a, point_b, current_set):
    # Iterate through each feature and get the distance
    # between point a and point b for each feature.
    #print('Point a: ' + str(point_a))
    #print('Point b: ' + str(point_b))
    number_of_features = len(current_set)
    distance = 0
    calculated_distance = 0
    for i in range(0, number_of_features):
        #print('Feature ' + str(current_set[i]) + ' a: ' + 
        #      str(point_a[current_set[i]]) + 
        #      ' b: ' + str(point_b[current_set[i]]))
        calculated_distance +=  (point_a[current_set[i]] 
                                 - point_b[current_set[i]])**2
        distance += calculated_distance
        #print('Calculated distance: ' + str(calculated_distance))
        #print('Total distance: ' + str(distance))
    
    return math.sqrt(distance)
