import numpy as np
from random import randint
from leave_one_out import leave_one_out_cross_validation

def print_detail(data):
    number_of_features = data.shape[1]
    number_of_instances = data.shape[0]
    print('This dataset has %d features (not including ' %(number_of_features-1),
          'the class attribute), with %d instances'  %number_of_instances)
    feature_set = list(range(1,number_of_features))
    accuracy = leave_one_out_cross_validation(data, feature_set, 0)
    print('Running nearest neighbor with all %d features, ', 
          (number_of_features-1), 
          'using \"leaving-one-out\" evaluation, I get and accuracy of 

def forward_selection(data):
    print('Beginning Search:')
    current_set_of_features = set()
    
    # Includes the class, but since python for loops
    # are exclusive of the end value the loop terminates
    # at the number of features
    number_of_features = data.shape[1]
    max_accuracy = 0
    best_set_of_features = set()
    accuracy_dict = {}
    for i in range(1, number_of_features):
        print('On the ' + str(i) + 'th level of the search tree')
        feature_to_add_at_this_level = 0
        best_so_far_accuracy = 0
        
        for k in range(1, number_of_features):
            if k not in current_set_of_features:                
                accuracy = leave_one_out_cross_validation(data, 
                                                          current_set_of_features, 
                                                          k)

                if accuracy > best_so_far_accuracy:
                    best_so_far_accuracy = accuracy
                    feature_to_add_at_this_level = k
                    
                if( max_accuracy < accuracy):
                    max_accuracy = accuracy
                    best_set_of_features.add(feature_to_add_at_this_level)                        
        
        
        current_set_of_features.add(feature_to_add_at_this_level)
        print('Feature set ', current_set_of_features, 
                  ' was best, accuracy is %.2f' %(best_so_far_accuracy*100),
                  '%\n')        
        
        if(max_accuracy > best_so_far_accuracy):
            print('max accuracy: ' + str(max_accuracy) + 
                  ' > current best: ' + str(best_so_far_accuracy))
            print('Warning, accuracy has decreased! Continuing ' +
                  'seach in case of local maxima')
            print('Feature set ', best_set_of_features, 
                  ' was best, accuracy is %.2f' %(max_accuracy*100),
                  '%\n')


        if(max_accuracy < best_so_far_accuracy):
            print('max accuracy: ' + str(max_accuracy) + 
                  ' <= current best: ' + str(best_so_far_accuracy))            
            
              
    print('Best set of features: ' +str(best_set_of_features))

def backward_elimination(data):
    print('Beginning Search:')
    
    # Includes the class, but since python for loops
    # are exclusive of the end value the loop terminates
    # at the number of features
    number_of_features = data.shape[1]    
    max_accuracy = 0
    
    # Start out with all features
    current_set_of_features = set(list(range(1,number_of_features)))

    best_set_of_features = set(list(range(1,number_of_features)))
    
    for i in range(1, number_of_features):
        print('On the ' + str(i) + 'th level of the search tree')
        feature_to_remove_at_this_level = 0
        best_so_far_accuracy = 0
        
        for k in range(1, number_of_features):
            level_set = set(list(current_set_of_features))
            print('\tk is %d and Current set: ' %k, current_set_of_features)
            if k in level_set:                
                level_set.discard(k)
                print('\tTemporary set: ', level_set)
                accuracy = leave_one_out_cross_validation(data, level_set, 0)
                print('\tUsing features ', level_set,
                      ' accuracy is %.2f' %(accuracy*100), '%')
                if accuracy > best_so_far_accuracy:
                    best_so_far_accuracy = accuracy
                    feature_to_remove_at_this_level = k
                    
                if( max_accuracy < accuracy):
                    max_accuracy = accuracy
                    best_set_of_features = set(list(level_set))
        
        current_set_of_features.discard(feature_to_remove_at_this_level)
        
        print('Feature set ', current_set_of_features, 
                  ' was best, accuracy is %.2f' %(best_so_far_accuracy*100),
                  '%\n')        
        
        if(max_accuracy > best_so_far_accuracy):
            print('max accuracy: ' + str(max_accuracy) + 
                  ' > current best: ' + str(best_so_far_accuracy))
            print('Warning, accuracy has decreased! Continuing ' +
                  'seach in case of local maxima')
            print('Feature set ', best_set_of_features, 
                  ' was best, accuracy is %.2f' %(max_accuracy*100),
                  '%\n')

        if(max_accuracy < best_so_far_accuracy):
            print('max accuracy: ' + str(max_accuracy) + 
                  ' <= current best: ' + str(best_so_far_accuracy))            
                          
    print('Best set of features: ', best_set_of_features, 
          ' with accuracy: %.2f' %(max_accuracy*100))
    
def marlo_algorithm(data):
    print('Marlo\'s Special Algorithm stub')