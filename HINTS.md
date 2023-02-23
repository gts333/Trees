RedBlackTree:
1. Root and null nodes are black
2. When a new node is added, it is red
3. Parent and children of a red node should be black
4. All paths from the root to a null node should have the same amount of black nodes

Insertion:
1. When parent node's sibling is red, do recoloring
2. When parent node's sibling is black, do rotating & recoloring