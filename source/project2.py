import math
import numpy as np
from random import randint
from nearest_neighbor import nearest_neighbor
from leave_one_out import leave_one_out_cross_validation

#data = open('CS170_SMALLtestdata__32.txt','r')
#data = np.genfromtxt('CS170_SMALLtestdata__32.txt')
data_32 = np.loadtxt('CS170_SMALLtestdata__32.txt')

data_108 =np.loadtxt('CS170_SMALLtestdata__108.txt')
data_109 =np.loadtxt('CS170_SMALLtestdata__109.txt')
data_110 = np.loadtxt('CS170_SMALLtestdata__110.txt')


#def leave_one_out_cross_validation(data, current_set, feature_to_add):
#    accuracy = randint(0, 100)
#    return accuracy

def add_feature(data, current_set_of_features, feature_to_add):
    print('hello')
    
def feature_search_demo(data):
    current_set_of_features = set()
    
    number_of_features = data.shape[1]
    print('Number of features:' + str(number_of_features))
    max_accuracy = 0
    best_set_of_features = set()
    
    for i in range(1, number_of_features):
        print('On the ' + str(i) + 'th level of the search tree')
        feature_to_add_at_this_level = 0
        best_so_far_accuracy = 0
        
        for k in range(1, number_of_features):
            if k not in current_set_of_features:
                #print('Current set of features: ' + str(current_set_of_features))
                
                print('--Considering adding ' + str(k) + ' feature')
                
                
                accuracy = leave_one_out_cross_validation(data, current_set_of_features, k)
                print('Accuracy from adding ' + str(k) + ': ' +str(accuracy))
                
                if accuracy == best_so_far_accuracy:
                    best_set_of_features.add(frozenset([k]))
                
                if accuracy > best_so_far_accuracy:
                    best_so_far_accuracy = accuracy
                    feature_to_add_at_this_level = k
                    if( max_accuracy < accuracy):
                        max_accuracy = accuracy            
                    
        if feature_to_add_at_this_level > 0: 
            current_set_of_features.add(feature_to_add_at_this_level)
        
        if(max_accuracy <= best_so_far_accuracy):
              print('max accuracy: ' + str(max_accuracy) + 
                    ' <= current best: ' + str(best_so_far_accuracy))              
              
        if(max_accuracy > best_so_far_accuracy):
              print('max accuracy: ' + str(max_accuracy) + 
                    ' > current best: ' + str(best_so_far_accuracy))                            
              
        #print('Current set of features: ' + str(current_set_of_features))
        print('On level ' + str(i) + ' I added feature ' + 
              str(feature_to_add_at_this_level) + ' to current set' + 
              ' with accuracy ' + str(best_so_far_accuracy))
              
    return current_set_of_features

#feature_set_38 = feature_search_demo(data_32)
#print('Final features: ' + str(feature_set_38))

feature_set = feature_search_demo(data_108)
print('Final features: ' + str(feature_set))
