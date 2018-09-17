
// Starter code for lp1.
// Version 1.0 (8:00 PM, Wed, Sep 5).

// Change following line to your NetId
package axc173730;

import java.util.Arrays;


public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
    long base = defaultBase;  // Change as needed
    long[] arr;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in arr[0..len-1]

    public Num(String s) {
    	
    	if(s.charAt(0) == '-') {
			this.isNegative = true;
			s = s.substring(1);
		}
    	len = s.length();
    	arr = new long[len];
    	for(int i=0;i<len;i++) {
    		
    		arr[i]=Long.parseLong(s.charAt(len-i-1)+"");
    	}
    }

    private Num(long arr[], long base) {
    	this.arr=arr;
    	this.base=base;
    	this.len=arr.length;
    }
    public Num(long x) {
    	this(String.valueOf(x));
    }

    private static long[] truncate(long arr[]) {
    	int length = arr.length;
    	for(int i=length-1;i>=0;i--) {
    		if(arr[i]==0 && arr[i-1]!=0) {
    			return Arrays.copyOfRange(arr, 0, i);
    		}
    	}
		return arr;
    	
    }
    public static Num add(Num a, Num b) {
    	Num result;
    	if(a.isNegative != b.isNegative) { //case b-a & a-b
    		
    		if(a.compare(b)>0) {
    			result = subtractHelper(a, b);
    			result.isNegative=a.isNegative;
    		}
    		else {
    			result = subtractHelper(b, a);
    			result.isNegative=b.isNegative;
    		}
    	}
    	else {
    		result =addHelper(a, b);
    		result.isNegative=a.isNegative;
    	}
    	
		return result;
    }


    private static Num addHelper(Num a, Num b) {
    
    	Num result;
    	int size =a.len>b.len ? a.len+1:b.len+1;
    	
    	long []resultArr = new long[size];
    	// perform addition
		int i = 0;
		long carry = 0, sum = 0;
		while (i < a.len && i < b.len) {
			sum = a.arr[i] + b.arr[i] + carry;
			resultArr[i] += sum % a.base;
			carry = sum / a.base;
			i++;
		}
		while (i < a.len) {
			sum = a.arr[i] + carry;
			resultArr[i] += sum % a.base;
			carry = sum / a.base;
			i++;
		}
		while (i < b.len) {
			sum = b.arr[i] + carry;
			resultArr[i] += sum % a.base;
			carry = sum / a.base;
			i++;
		}
		if (carry != 0) {
			resultArr[i] += carry;
		}
		result = new Num(truncate(resultArr), a.base);
		
    	return result;
    }
    public static Num subtract(Num a, Num b) {// a-b
    	Num result;
    	if(a.isNegative != b.isNegative) { // if a and b are of different signs
    		//Num num2 = ;
    		result = Num.addHelper(a,b); // result isNegative is false- when b is negative.
    		if(a.isNegative) {// case -a-b
    			result.isNegative = true;
    		}else
    			result.isNegative = false; // case a-(-b)
    	}
    	else if (a.compareTo(b) > 0) { // take care of cases where a=999 and b = 8999
    	//else if(a.len>=b.len) {
			result= subtractHelper(a, b);
    	}
		else {
			result= subtractHelper(b, a);
			result.isNegative = true;
		}
		//}else {
			
		//}

		return result;
	}

    private int compare(Num other) {
		// TODO Auto-generated method stub
    	int size=0;
    	if(this.len>other.len)
			return 1;
		else if(this.len<other.len)
			return -1;
		else {
			size=this.len;
	    	for(int i=size-1;i>=0;i--) {
	    		if(this.arr[i]>other.arr[i])
	    			return 1;
	    		else if(this.arr[i]<other.arr[i])
	    			return -1;
	    		else
	    			continue;
	    	}
		}
    	return 0;
	}

	private static Num subtractHelper(Num a, Num b) {
    	int size =a.len>b.len ? a.len:b.len;
    	Num result;
    	long []resultArr = new long[size];
    	long []minuend;
    	minuend = new long[a.len];
		System.arraycopy(a.arr, 0, minuend, 0, a.len);
		int i = 0;
		long borrow = a.base;
		while(i<a.len && i<b.len) {
			if (minuend[i]>=b.arr[i])
				resultArr[i] = minuend[i]-b.arr[i];
			else {
				minuend[i]+=borrow;
				--minuend[i+1];
				resultArr[i] = minuend[i]-b.arr[i];
			}
			i++;
		}
		while(i<a.len) {
			resultArr[i]=minuend[i];
			i++;
		}
		result = new Num(resultArr, a.base);
		result.isNegative = a.isNegative;
		return result;
    }
    public static Num product(Num a, Num b) {
	return null;
    }

    // Use divide and conquer
    public static Num power(Num a, long n) {
	return null;
    }

    // Use binary search to calculate a/b
    public static Num divide(Num a, Num b) {
	return null;
    }

    // return a%b
    public static Num mod(Num a, Num b) {
	return null;
    }

    // Use binary search
    public static Num squareRoot(Num a) {
	return null;
    }


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
    	int size =0;
    	if(this.isNegative==other.isNegative) {
    		if(this.len>other.len)
    			return 1;
    		else if(this.len<other.len)
    			return -1;
    		else {
    			size=this.len;
		    	for(int i=size-1;i>=0;i--) {
		    		if(this.arr[i]>other.arr[i]) {
		    			return 1;
		    		}else if (this.arr[i]<other.arr[i]) {
		    			return -1;
		    		}else
		    			continue;
		    	}
    		}
    	}
    	else {
    		if(this.isNegative)
    			return -1;
    		if(other.isNegative)
    			return 1;
    	}
	return 0;
    }
    
    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    public void printList() {
    }
    
    // Return number to a string in base 10
    public String toString() {
    	String retString="";
    	for(int i=this.len-1;i>=0;i--) {
    		retString+= this.arr[i]+"";
    	}
    	if(this.isNegative)
    		return "-"+retString;
    	return retString;
    }

    public long base() { return base; }

    // Return number equal to "this" number, in base=newBase
    public Num convertBase(int newBase) {
	return null;
    }

    // Divide by 2, for using in binary search
    public Num by2() {
	return null;
    }

    // Evaluate an expression in postfix and return resulting number
    // Each string is one of: "*", "+", "-", "/", "%", "^", "0", or
    // a number: [1-9][0-9]*.  There is no unary minus operator.
    public static Num evaluatePostfix(String[] expr) {
	return null;
    }

    // Evaluate an expression in infix and return resulting number
    // Each string is one of: "*", "+", "-", "/", "%", "^", "(", ")", "0", or
    // a number: [1-9][0-9]*.  There is no unary minus operator.
    public static Num evaluateInfix(String[] expr) {
	return null;
    }


    public static void main(String[] args) {

    	Num x = new Num("47");
		Num y = new Num("42");
		Num z = Num.add(x, y);
		System.out.println(z);
		Num a = Num.power(x, 8);
		System.out.println(a);
		if (z != null)
			z.printList();
    }
}