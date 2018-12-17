import numpy as np

def normalize(data):
    # Get on only the features
    test = data[:,1:]
    print('line 1 data' + str(data[0]))
    print('line 1 test' + str(test[0]))
    # Normalize the features
    test_normed = (test - test.min(0)) / test.ptp(0)
    print('line 1 test_normed' + str(test_normed[0]))
    # Put the class labels back on first column
    result = np.column_stack((data[:,0],test_normed))
    return result