int n, board[100][100];
bool jump(int y, int x) {

    if(y >= n || x >= n) return false;

    if(y == n-1 && x == n-1) return true;
    
    int jumpSize = board[y][x];
    return jump(y + jumpSize, x) || jump(y, x + jumpSize);
}

