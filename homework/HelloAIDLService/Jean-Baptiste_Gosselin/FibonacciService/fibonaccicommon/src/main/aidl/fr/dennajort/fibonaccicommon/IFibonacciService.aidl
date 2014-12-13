// IFibonacciService.aidl
package fr.dennajort.fibonaccicommon;

import fr.dennajort.fibonaccicommon.FibonacciRequest;
import fr.dennajort.fibonaccicommon.FibonacciResponse;

interface IFibonacciService {
     FibonacciResponse calculate(in FibonacciRequest request);
}
