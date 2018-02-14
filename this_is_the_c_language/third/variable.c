#include <stdio.h>


int main(void)
{
	/*
	int a;
	int b, c;
	a = 10;
	b = a;
	c = a + 20;

	double da;
	da = 3.5;

	char ch;
	ch = 'A';

	printf("%d\n", a);
	printf("%lf\n", da);
	printf("%c\n", ch);
	printf("%d\n", a + b);

	return 0;
	*/

	/*
	char ch;
	int a;
	short sh = 10;
	long ln = 10;
	long long lln = 10;

	ch = 'A'; // 01000001

	printf("%d\n", sh);
	printf("%ld\n", ln);
	printf("%lld\n", lln);

	printf("%c\n", ch); // A
	printf("%d\n", ch); // 65
	*/

	/*
	signed int a;
	unsigned int ua;
	ua = 4294967295;
	
	printf("%u\n", ua); // give 4294967295
	printf("%d\n", ua); // give -1
	*/


	/*
	float ft;
	ft = 3.4f; // cut effective number, f to set the length cutting
	double db = 3.4; // usually use double(8bytes)
	long double ld = 6.5; // too much care on compatibility

	printf("%f\n", ft);
	printf("%lf\n", db);
	printf("%Lf\n", ld);
	*/

	char str[20] = "apple"; // get sequential memory
							// only default declaration avialbal
							// null insertion at the end
	printf("%s\n", str);
	// str = "banana"; // not available
	strcpy(str, "banana"); // change string variable
	printf("%s\n", str);
}