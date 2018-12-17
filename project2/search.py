import numpy as np
import time
from random import randint
from leave_one_out import leave_one_out_cross_validation, leave_one_out_modified

def print_detail(data):
    number_of_features = data.shape[1]
    number_of_instances = data.shape[0]
    print('This dataset has %d features (not including ' %(number_of_features-1),
          'the class attribute), with %d instances'  %number_of_instances)
    feature_set = list(range(1,number_of_features))
    accuracy = leave_one_out_cross_validation(data, feature_set, 0)
    print('Running nearest neighbor with all %d features, ' %(number_of_features-1), 
          'using \"leaving-one-out\" evaluation, I get accuracy of %.2f' %(accuracy*100)
          , '%\n')
          
    empty_set = set()
    accuracy = leave_one_out_cross_validation(data, empty_set, 0)
    print('Running nearest neighbor with all %d features, ' %(len(empty_set)), 
          'using \"leaving-one-out\" evaluation, I get accuracy of %.2f' %(accuracy*100)
          , '%\n')


def forward_selection(data):
    print('Beginning Search:')
    current_set_of_features = set()
    
    # Includes the class, but since python for loops
    # are exclusive of the end value the loop terminates
    # at the number of features
    number_of_features = data.shape[1]
    max_accuracy = 0
    best_set_of_features = set()
    start_time = time.time()
    
    for i in range(1, number_of_features):
        print('On the ' + str(i) + 'th level of the search tree')
        feature_to_add_at_this_level = 0
        best_so_far_accuracy = 0
        
        for k in range(1, number_of_features):
            level_set = set(list(current_set_of_features))
            if k not in current_set_of_features:   
                level_set.add(k)
                accuracy = leave_one_out_cross_validation(data, 
                                                          level_set, 
                                                          k)
                print('\tUsing features ', level_set,
                      ' accuracy is %.2f' %(accuracy*100), '%')
                      
                if accuracy > best_so_far_accuracy:
                    best_so_far_accuracy = accuracy
                    feature_to_add_at_this_level = k
                    
                if( max_accuracy < accuracy):
                    max_accuracy = accuracy
                    best_set_of_features = set(list(level_set))                        
        
        
        current_set_of_features.add(feature_to_add_at_this_level)
        print('\n\tFeature set ', current_set_of_features, 
                  ' was best, accuracy is %.2f' %(best_so_far_accuracy*100),
                  '%\n')        
        
        if(max_accuracy > best_so_far_accuracy):
            print('Warning, accuracy has decreased! Continuing ' +
                  'seach in case of local maxima')
            print('Feature set ', best_set_of_features, 
                  ' was best, accuracy is %.2f' %(max_accuracy*100),
                  '%\n')

    print('Best set of features: ', best_set_of_features, 
          ' with accuracy: %.2f' %(max_accuracy*100))
          
    stop_time = time.time() - start_time
    print('Total time searching: %d' %stop_time, ' seconds')
    
    return (best_set_of_features, max_accuracy)

def backward_elimination(data):
    print('Beginning Search:')
    
    # Includes the class, but since python for loops
    # are exclusive of the end value the loop terminates
    # at the number of features
    number_of_features = data.shape[1]    
    max_accuracy = 0
    start_time = time.time()
    # Start out with all features
    current_set_of_features = set(list(range(1,number_of_features)))

    best_set_of_features = set(list(range(1,number_of_features)))
    
    for i in range(1, number_of_features):
        print('On the ' + str(i) + 'th level of the search tree')
        feature_to_remove_at_this_level = 0
        best_so_far_accuracy = 0
        
        for k in range(1, number_of_features):
            level_set = set(list(current_set_of_features))
            
            if k in level_set:                
                level_set.discard(k)
                
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
        
        print('\n\tFeature set ', current_set_of_features, 
                  ' was best, accuracy is %.2f' %(best_so_far_accuracy*100),
                  '%\n')        
        
        if(max_accuracy > best_so_far_accuracy):
            print('Warning, accuracy has decreased! Continuing ' +
                  'seach in case of local maxima')
            print('Feature set ', best_set_of_features, 
                  ' was best, accuracy is %.2f' %(max_accuracy*100),
                  '%\n')
                          
    print('Best set of features: ', best_set_of_features, 
          ' with accuracy: %.2f' %(max_accuracy*100))

    stop_time = time.time() - start_time
    print('Total time searching: %d' %stop_time, ' seconds')
    
    return (best_set_of_features, max_accuracy)
    
def marlo_algorithm(data):
    # To speed up search we use a modified leave_one_out function. 
    # The function breaks out of the loop if a threshhold is reached.
    # This function uses backward elimination search
    print('Beginning Search:')
    
    # Includes the class, but since python for loops
    # are exclusive of the end value the loop terminates
    # at the number of features
    number_of_features = data.shape[1]    
    max_accuracy = 0
    percent = 0.20
    threshhold = int(data.shape[0]*percent)
    start_time = time.time()
    
    # Start out with all features
    current_set_of_features = set(list(range(1,number_of_features)))

    best_set_of_features = set(list(range(1,number_of_features)))
    
    for i in range(1, number_of_features):
        print('On the ' + str(i) + 'th level of the search tree')
        feature_to_remove_at_this_level = 0
        best_so_far_accuracy = 0
        
        for k in range(1, number_of_features):
            level_set = set(list(current_set_of_features))
            
            if k in level_set:                
                level_set.discard(k)
                
                accuracy = leave_one_out_modified(data, level_set, 0, threshhold)

                print('\tUsing features ', level_set,
                      ' accuracy is %.2f' %(accuracy*100), '%')
                if accuracy > best_so_far_accuracy:
                    best_so_far_accuracy = accuracy
                    feature_to_remove_at_this_level = k
                    
                if( max_accuracy < accuracy):
                    max_accuracy = accuracy
                    best_set_of_features = set(list(level_set))
        
        current_set_of_features.discard(feature_to_remove_at_this_level)
        
        print('\n\tFeature set ', current_set_of_features, 
                  ' was best, accuracy is %.2f' %(best_so_far_accuracy*100),
                  '%\n')        
        
        if(max_accuracy > best_so_far_accuracy):
            print('Warning, accuracy has decreased! Continuing ' +
                  'seach in case of local maxima')
            print('Feature set ', best_set_of_features, 
                  ' was best, accuracy is %.2f' %(max_accuracy*100),
                  '%\n')
                          
    print('Best set of features: ', best_set_of_features, 
          ' with accuracy: %.2f' %(max_accuracy*100))

    stop_time = time.time() - start_time
    print('Total time searching: %d' %stop_time, ' seconds')
    
    return (best_set_of_features, max_accuracy)