int n;
int cache[101], S[100];

int lis3(int start) {
    int& ret = cache[start+1];
    if(ret != -1) return ret;

    ret = 1;
    for(int next = start+1; next < n; ++next) {
        if(start == -1 || S[start] < S[next]) {
            ret = max(ret, lis3(next) + 1);
        }
    }
    return ret;
}