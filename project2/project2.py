import math
import sys
import numpy as np
from random import randint
from search import forward_selection, backward_elimination
from search import marlo_algorithm, print_detail
from normalize import normalize #yields different results  

#data = open('CS170_SMALLtestdata__32.txt','r')
#data = np.genfromtxt('CS170_SMALLtestdata__32.txt')
data_32 = np.loadtxt('CS170_SMALLtestdata__32.txt')

print('Welcome to Marlo Zeroth\'s Feature Selection Algorithm')
try:
    file_name = input('Type in the name of the file to test: ')    
    #data_no_norm = np.loadtxt(file_name) 
    #data = normalize(data_no_norm) # Yields higher accuracy for some data sets
    data = np.loadtxt(file_name)
    
except IOError:
    print('File does not exist. Exiting')
    sys.exit()
    
try:
    print('Type the number of the algorithm you want to run\n' +
          '\t1) Forward Selection\n' +
          '\t2) Backward Elimination\n' +
          '\t3) Marlo\'s Special Algorithm\n')
    option = input('Enter your choice: ')
    
    if  option == str(1):
        print_detail(data)
        forward_selection(data)
        
    elif option == str(2):
        print_detail(data)
        backward_elimination(data)
        
    elif option == str(3):
        print_detail(data)
        marlo_algorithm(data)
        
    else:
        print('Invalid option. Exiting program.')
        sys.exit()
        
except ValueError:
    print('user input something other than a number')
    sys.exit()


