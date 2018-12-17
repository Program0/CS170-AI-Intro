import numpy as np
from random import randint
from leave_one_out import leave_one_out_cross_validation

def forward_search(data):
    current_set_of_features = set()
    
    # Includes the class, but since python for loops
    # are exclusive of the end value the loop terminates
    # at the number of features
    number_of_features = data.shape[1]
    max_accuracy = 0
    best_set_of_features = set()
    
    for i in range(1, number_of_features):
        print('On the ' + str(i) + 'th level of the search tree')
        feature_to_add_at_this_level = 0
        best_so_far_accuracy = 0
        
        for k in range(1, number_of_features):
            if k not in current_set_of_features:
                print('\tCurrent set of features: ' + str(current_set_of_features))
                
                print('\t--Considering adding ' + str(k) + ' feature')
                
                accuracy = leave_one_out_cross_validation(data, current_set_of_features, k+1)
                print('\tAccuracy from adding ' + str(k) + ': ' +str(accuracy))
                
                if accuracy > best_so_far_accuracy:
                    best_so_far_accuracy = accuracy
                    feature_to_add_at_this_level = k
                    if( max_accuracy < accuracy):
                        max_accuracy = accuracy
                        best_set_of_features.add(feature_to_add_at_this_level)                        
        
        if(feature_to_add_at_this_level > 0):
            current_set_of_features.add(feature_to_add_at_this_level)
                    #print('Current set of features: ' + str(current_set_of_features))
            print('On level ' + str(i) + ' I added feature ' + 
                  str(feature_to_add_at_this_level) + ' to current set' + 
                  ' with accuracy ' + str(best_so_far_accuracy))

        else:
            print('Warning, accuracy has decreased! Continuing ' +
                  'seach in case of local maxima')
            print('Feature set ' + str(current_set_of_features) + 
                  ' was best, accuracy is ' + str(best_so_far_accuracy) +
                  '\%')

        
        if(max_accuracy > best_so_far_accuracy):
            print('max accuracy: ' + str(max_accuracy) + 
                  ' > current best: ' + str(best_so_far_accuracy))

        if(max_accuracy < best_so_far_accuracy):
            print('max accuracy: ' + str(max_accuracy) + 
                  ' <= current best: ' + str(best_so_far_accuracy))            
            
              
    print('Best set of features: ' +str(best_set_of_features))
