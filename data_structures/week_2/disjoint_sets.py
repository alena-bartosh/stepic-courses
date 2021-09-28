# task 2.3.4 from https://stepik.org/course/1547/syllabus
from typing import List


def main() -> None:
    elements_count, equality_count, inequality_count = list(map(int, input().split(' ')))

    parent: List[int] = [_ for _ in range(elements_count + 1)]

    for _ in range(equality_count):
        el1, el2 = list(map(int, input().split(' ')))
        parent[el2] = el1

    def find_root_parent(el: int) -> int:
        nonlocal parent
        root_parent = el

        while parent[root_parent] != root_parent:
            root_parent = parent[root_parent]

        return root_parent

    for _ in range(inequality_count):
        el1, el2 = list(map(int, input().split(' ')))
        is_system_possible = find_root_parent(el1) != find_root_parent(el2)

        if not is_system_possible:
            print('0')
            return

    print('1')


if __name__ == '__main__':
    main()
