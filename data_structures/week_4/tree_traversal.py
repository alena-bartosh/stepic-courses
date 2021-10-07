# task 4.4.1 from https://stepik.org/course/1547/syllabus
from typing import List


class Node:
    def __init__(self,
                 key: int,
                 left: int,
                 right: int) -> None:
        self.key = key
        self.left = left
        self.right = right


class TreeTraversal:
    def __init__(self, nodes: List[Node]) -> None:
        self.nodes = nodes

        self.pre_order = []
        self.post_order = []
        self.in_order = []

    def walk(self, index: int) -> None:
        if index == -1:
            return

        # NOTE: Depth-first tree traversal has tree types: in-order, pre-order, post-order

        # print(f'pre_order append [key={self.nodes[index].key}]')
        self.pre_order.append(self.nodes[index].key)

        self.walk(self.nodes[index].left)

        # print(f'in_order append [key={self.nodes[index].key}]')
        self.in_order.append(self.nodes[index].key)

        self.walk(self.nodes[index].right)

        # print(f'post_order append [key={self.nodes[index].key}]')
        self.post_order.append(self.nodes[index].key)


def main() -> None:
    vertex_count = int(input())

    nodes = []
    for _ in range(vertex_count):
        key, left, right = list(map(int, input().split(' ')))
        nodes.append(Node(key, left, right))

    tree_traversal = TreeTraversal(nodes)
    tree_traversal.walk(0)

    print(' '.join(map(str, tree_traversal.in_order)))
    print(' '.join(map(str, tree_traversal.pre_order)))
    print(' '.join(map(str, tree_traversal.post_order)))


if __name__ == '__main__':
    main()
