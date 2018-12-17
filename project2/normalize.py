import numpy as np

def normalize(data):
    # Get on only the features
    test = data[:,1:]

    # Normalize the features
    test_normed = (test - test.min(0)) / test.ptp(0)

    # Put the class labels back on first column
    result = np.column_stack((data[:,0],test_normed))
    return result