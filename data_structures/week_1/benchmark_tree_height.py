from typing import Optional, List, Dict

from tree_height import get_height


class Node:
    def __init__(self, parent: Optional['Node'] = None):
        self.depth: int = parent.depth + 1 if parent else 1
        self.parent: Optional['Node']


def get_node(tree: List[int], nodes: Dict['int', Node], value: int, parent_value: int) -> Node:
    node = nodes.get(value)

    if node is not None:
        return node

    if parent_value != -1:
        grandparent_node_value = tree[parent_value]
        parent_node = get_node(tree, nodes, parent_value, grandparent_node_value)
    else:
        parent_node = None

    node = Node(parent_node)

    nodes[value] = node

    return node


def tree_depth(tree: List[int]) -> int:
    nodes = {}
    depth = 1

    for value, parentIndex in enumerate(tree):
        node = get_node(tree, nodes, value, parentIndex)
        depth = max(depth, node.depth)

    return depth


def string_to_int_list(array_as_string: str) -> List[int]:
    return [int(i) for i in array_as_string.split(' ')]


RAW_INPUT_DATA = ''  # generated list with n=10_000 from create_adjacency_list_for_random_tree.py
INPUT_DATA = string_to_int_list(RAW_INPUT_DATA)


def test_dima():
    tree_depth(INPUT_DATA)


def test_alena():
    get_height(INPUT_DATA)


def main() -> None:
    import timeit
    print(timeit.timeit("test_alena()", globals=locals(), number=10000))  # 47.022 A win :)
    print(timeit.timeit("test_dima()", globals=locals(), number=10000))   # 78.219 D lose :(
