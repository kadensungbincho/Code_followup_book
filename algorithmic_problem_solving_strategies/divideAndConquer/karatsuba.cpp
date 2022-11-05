void normalize(vector<int>& num) {
    num.push_back(0);

    for(int i = 0; i < num.size(); ++i) {
        if(num[i] < 0) {
            int borrow = (abs(num[i]) + 9) / 10; 
            num[i+1] -= borrow;
            num[i] += borrow * 10;
        }
        else {
            num[i+1] += num[i] / 10;
            num[i] %= 10;
        }
    }
    while(nim.size() > 1 && num.back() == 0) num.pop_back();
}

vector<int> multiply(const vector<int>& a, const vector<int>& b) {
    vector<int> c(a.size() + b.size() + 1, 0);
    for(int i = 0; i < a.size(); ++i) {
        for(int j = 0; j < b.size(); ++j) {
            c[i+j] += a[i] * b[j];
        }
    }
    normalize(c);
    return c;
}