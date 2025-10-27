#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

int main()
{
    int m, n;
    int dp[10000];
    int sum = 0;
    int mini = 10000;

    cin >> m >> n;

    for (int i = 1; i <= n; ++i) {
        dp[i] = i+1;
    }

    for (int i = 1; i <= n; ++i) {
        if (m <= dp[i] && dp[i] <= n) {
            sum += dp[i];
            mini = min(mini, dp[i]);
        }
    }

    if (sum == 0) {
        cout << "-1\n";
    } else {
        cout << sum << "\n";
        cout << mini << "\n";
    }

    return 0;
}