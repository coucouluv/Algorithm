#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <set>
using namespace std;
const int MAX = 100001;
struct Tree {
    int node;
    int x;
    int y;
    Tree* left, * right;
};

void preorder(Tree* tree, vector<int>& answer) {
    if (tree == NULL)
        return;
    answer.push_back(tree->node);
    preorder(tree->left, answer);
    preorder(tree->right, answer);
}
void postorder(Tree* tree, vector<int>& answer) {
    if (tree == NULL)
        return;
    postorder(tree->left, answer);
    postorder(tree->right, answer);
    answer.push_back(tree->node);
}

void maketree(Tree* tree, Tree& tmp) {
    if (tree->x > tmp.x) {
        if (tree->left == NULL) {
            tree->left = &tmp;
            return;
        }
        maketree(tree->left, tmp);
    }
    else {
        if (tree->right == NULL) {
            tree->right = &tmp;
            return;
        }
        maketree(tree->right, tmp);
    }
    return;
}

bool compare(Tree a, Tree b) {
    if (a.y == b.y)
        return a.x < b.x;
    return a.y > b.y;
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<vector<int>> answer;
    vector <Tree> treetmp; //노드
    for (int i = 0; i < nodeinfo.size(); i++) {
        treetmp.push_back({ i + 1, nodeinfo[i][0],nodeinfo[i][1], NULL, NULL });
    }
    sort(treetmp.begin(), treetmp.end(), compare);
    Tree* tree = &treetmp[0];
    for (int i = 1; i < treetmp.size(); i++) {
        maketree(tree, treetmp[i]);
    }
    vector<int> tmp1, tmp2;
    preorder(tree, tmp1);
    answer.push_back(tmp1);
    postorder(tree, tmp2);
    answer.push_back(tmp2);
    return answer;
}