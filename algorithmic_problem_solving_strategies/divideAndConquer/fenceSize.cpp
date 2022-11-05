int bruteForce(const vector<int>& h) {
    int ret = 0;
    int N = h.size();

    for(int left = 0; left < N; ++left) {
        int minHeight = h[left];
        for(int right = left; right < N; ++right) {
            minHeight = min(minHeight, h[right]);
            ret = max(ret, (right - left + 1) * minHeight);
        }
    }
    return ret;
}

vector<int> h;

int solve(int left, int right) {
    if(left == right) return h[left];

    int mid = (left + right) / 2;

    int ret = max(solve(left, mid), solve(mid+1, right));

    int lo = mid, hi = mid+1;
    int height = min(h[lo], h[hi]);

    ret = max(ret, height * 2);

    while(left < lo || hi < right) {
        if(hi < right && (lo == left || h[lo-1] < h[hi+1])) {
            hi++;
            height = min(height, h[hi]);
        } 
        else {
            --lo;
            height = min(height, h[lo]);
        }
        ret = max(ret, height * (hi - lo + 1));
    }
    return ret;
}