# code for creating test cases for task 1.2.2 from https://stepik.org/course/1547/syllabus
# please see solution for this task in tree_height.py

from networkx.generators.trees import random_tree
import networkx as nx


def main() -> None:
    n = 1000
    root = 0  # start from the root vertex that is always a parent

    # NOTE: You can create random tree with random edges or use list of custom edges for that tree
    #       For using list of edges please uncomment
    # n = ...
    # root = -1
    # tree = nx.Graph()
    # tree.add_edges_from([(...), (...), ...])

    child_parent = {-1: -1}  # please comment if create tree from edges

    tree = random_tree(n=n, seed=0)

    all_edges = sorted(tree.edges(), key=lambda x: x[0])
    # print(f'All edges = {all_edges}')

    def find_children(value: int) -> None:
        nonlocal all_edges

        if len(all_edges) == 0:
            return

        # NOTE: For example, if we try to find children for 1 vertex,
        #       then we we could have [(1, 4), (5, 1), (3, 1)] edges
        edges = [(left, right) for left, right in all_edges if left == value or right == value]

        # print(f'Lets take edges={edges}')

        if len(edges) == 0:
            return

        # Remove used edges from the main list
        all_edges = [edge for edge in all_edges if edge not in edges]

        for left, right in edges:
            is_left_parent = left == value
            child = right if is_left_parent else left

            # print(f'Child in [{(left, right)}] is {child}')

            child_parent[child] = left if is_left_parent else right

            find_children(child)

    find_children(root)

    assert len(child_parent) == n

    for ind in sorted(child_parent.keys()):
        print(child_parent[ind], end=' ')


if __name__ == '__main__':
    main()
