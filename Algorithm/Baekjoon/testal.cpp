#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
int cost[10001];
int t[10001];
vector<int> v[10001];

int main()
{
    cin >> N;
    for (int cnt, i = 1; i <= N; ++i) {
        cin >> cost[i];
        cin >> cnt;
        for (int tmp, j = 1; j <= cnt; ++j) {
            cin >> tmp;
            v[i].push_back(tmp);
        }
    }
    
    for (int i = 1; i <= N; ++i) {
        int cur = 0;
        for (int j = 0; j < v[i].size(); ++j) {
            cur = max(cur, t[v[i][j]]);
        }
        t[i] = cur + cost[i];
    }

    int res = 0;
    for (int i = 1; i <= N; ++i) {
        res = max(res, t[i]);
    }

    cout << res;;
}