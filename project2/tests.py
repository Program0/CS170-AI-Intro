import math
import numpy as np
from random import randint
from leave_one_out import leave_one_out_cross_validation
from search import forward_selection
from normalize import normalize

#from nearest_neighbor import eucledian_distance
#data = open('CS170_SMALLtestdata__32.txt','r')
#data = np.genfromtxt('CS170_SMALLtestdata__32.txt')
data_108 = np.loadtxt('CS170_SMALLtestdata__108.txt')
data_109 = np.loadtxt('CS170_SMALLtestdata__109.txt')
data_110 = np.loadtxt('CS170_SMALLtestdata__110.txt')

# Normalize the data
#test = data_108[:,1:]
#print('line 1 data' + str(data_108[0]))
#print('line 1 test' + str(test[0]))
#test_normed = (test - test.min(0)) / test.ptp(0)
#print('line 1 test_normed' + str(test_normed[0]))
#result = np.column_stack((data_108[:,0],test_normed))
#print('line 1 result' + str(result[0]))
#print('\nSearching small data set 108 with data normalized')
#forward_selection(result)

test_108 = set()
test_108.add(6)
test_108.add(5)
#accuracy = leave_one_out_cross_validation(result, test_108, 4)
#print('Accuracy for 108 normalized from 6 5 4: ' + str(accuracy))
#forward_selection(data_108)

test_109 = set()
test_109.add(7)
test_109.add(9)
#accuracy = leave_one_out_cross_validation(data_109, test_109, 2)
#print('Accuracy for 109 from 7 9 2: ' + str(accuracy))
#data_109_n = normalize(data_109)
#forward_selection(data_109)

test_110 = set()
test_110.add(6)
test_110.add(4)
#accuracy = leave_one_out_cross_validation(data_110, test_110, 9)
#print('Accuracy for 110 from 6 4 9: ' + str(accuracy))
#forward_selection(data_110)

empty_set = set()
print('Test accuracy with empty feature set')
accuracy = leave_one_out_cross_validation(data_110, empty_set, 0)
print('Accuracy for data set 109 with empty set: %.2f' %accuracy)
print('Testing the range function in for loop')
for i in range(1, 11):
    print(i, end=', ')