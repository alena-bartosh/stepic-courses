# task 1.2.2 from https://stepik.org/course/1547/syllabus
from typing import List


def get_height(parents: List[int]) -> int:

    storage = dict()

    for ind, el in enumerate(parents):

        height = 1
        parent = el

        storage[ind] = height

        while parent != -1:
            height += 1
            parent = parents[parent]

            if parent in storage:
                height += storage[parent]
                break

        height = max(height, storage[ind])
        storage[ind] = height

    return max(storage.values())


def main() -> None:
    n = int(input())
    parents = [int(number) for number in input().split(' ')]
    height = get_height(parents)

    print(height)


if __name__ == '__main__':
    # assert get_height([4, -1, 4, 1, 1]) == 3
    # assert get_height([-1, 0, 4, 0, 3]) == 4
    # assert get_height([9, 7, 5, 5, 2, 9, 9, 9, 2, -1]) == 4

    # data example [4, -1, 4, 1, 1], where
    #   parent for 0 vertex is 4
    #   1 vertex is a root of a tree
    #   parent for 2 vertex is 4
    #   parent for 3 vertex is 1
    #   parent for 4 vertex is 1

    main()
